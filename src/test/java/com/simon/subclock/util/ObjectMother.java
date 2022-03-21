package com.simon.subclock.util;

import com.simon.subclock.model.CallbackDTO;

public class ObjectMother {
    public static CallbackDTO callbackDTO(){
        return new CallbackDTO().callbackUrl("random").frequency(5);
    }
}
