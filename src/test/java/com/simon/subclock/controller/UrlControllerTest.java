package com.simon.subclock.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simon.subclock.model.CallbackDTO;
import com.simon.subclock.service.UrlService;
import com.simon.subclock.util.ObjectMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
class UrlControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UrlService urlService;
    private CallbackDTO callbackDTO;

    @BeforeEach
    void setup() {
        callbackDTO = ObjectMother.callbackDTO();
    }

    @Test
    void testRegisterUrlSuccessfully() throws Exception {
        when(urlService.register(any())).thenReturn(callbackDTO);
        mockMvc.perform(post("/v1/url").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(callbackDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.callbackUrl", is(callbackDTO.getCallbackUrl())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.frequency", is(callbackDTO.getFrequency())));
    }

    @Test
    void testDeregisterUrlSuccessfully() throws Exception {
        when(urlService.deregister(any())).thenReturn(callbackDTO);
        mockMvc.perform(delete("/v1/url").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(callbackDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.callbackUrl", is(callbackDTO.getCallbackUrl())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.frequency", is(callbackDTO.getFrequency())));
    }

    @Test
    void testUpdateUrlSuccessfully() throws Exception {
        when(urlService.update(any())).thenReturn(callbackDTO);
        mockMvc.perform(put("/v1/url").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(callbackDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.callbackUrl", is(callbackDTO.getCallbackUrl())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.frequency", is(callbackDTO.getFrequency())));
    }

}
