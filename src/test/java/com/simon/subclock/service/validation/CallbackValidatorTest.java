package com.simon.subclock.service.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CallbackValidatorTest {

    @InjectMocks
    private CallbackValidator callbackValidator;

    @Test
    void testValidateFrequencyWithInvalidFields() {
        assertThrows(ValidationException.class, () -> callbackValidator.validateFrequency(2));
        assertThrows(ValidationException.class, () -> callbackValidator.validateFrequency(0));
        assertThrows(ValidationException.class, () -> callbackValidator.validateFrequency(-10));
        assertThrows(ValidationException.class, () -> callbackValidator.validateFrequency(14401));
    }

    @Test
    void testValidateFrequencyWithValidFields() {
        assertDoesNotThrow(() -> callbackValidator.validateFrequency(5));
        assertDoesNotThrow(() -> callbackValidator.validateFrequency(50));
        assertDoesNotThrow(() -> callbackValidator.validateFrequency(150));
        assertDoesNotThrow(() -> callbackValidator.validateFrequency(14400));
    }
}
