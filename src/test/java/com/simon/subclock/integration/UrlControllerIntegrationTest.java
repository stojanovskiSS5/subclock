package com.simon.subclock.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simon.subclock.model.CallbackDTO;
import com.simon.subclock.service.UrlService;
import com.simon.subclock.util.ObjectMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UrlControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UrlService urlService;

    @Test
    void testRegisterUrlSuccessfully() throws Exception {
        CallbackDTO callbackDTO = ObjectMother.callbackDTO();
        mockMvc.perform(post("/v1/url").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(callbackDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.callbackUrl", is(callbackDTO.getCallbackUrl())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.frequency", is(callbackDTO.getFrequency())));
    }

    @DisplayName("Invalid register url fields")
    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidFieldsRegisterUrl")
    void testInvalidFieldsRegisterUrl(String name, String errorMessage, CallbackDTO callbackDTO) throws Exception {
        mockMvc.perform(post("/v1/url").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(callbackDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", is(errorMessage)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", is(HttpStatus.BAD_REQUEST.name())));
    }

    private static Stream<Arguments> invalidFieldsRegisterUrl() {
        return Stream.of(
                Arguments.of("Invalid null callbackUrl",
                "Constraint validation: callbackUrl must not be null.", ObjectMother.callbackDTO().callbackUrl(null)),
                Arguments.of("Invalid frequency value less then 5 second",
                        "Frequency is not in the range between 5 seconds and 4 hours", ObjectMother.callbackDTO().frequency(0)),
                Arguments.of("Invalid frequency value higher then 4 hours",
                        "Frequency is not in the range between 5 seconds and 4 hours", ObjectMother.callbackDTO().frequency(14444))
        );
    }
}
