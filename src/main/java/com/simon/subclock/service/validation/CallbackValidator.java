package com.simon.subclock.service.validation;

import com.simon.subclock.model.CallbackDTO;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;

@Component
public class CallbackValidator {

    public void validateFrequency(int frequency){
        if(frequency < 5 ||frequency > 14400) {
            throw new ValidationException("Frequency is not in the range between 5 seconds and 4 hours");
        }
    }
}
