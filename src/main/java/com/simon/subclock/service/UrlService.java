package com.simon.subclock.service;

import com.simon.subclock.model.CallbackDTO;
import com.simon.subclock.repository.UrlRepository;
import com.simon.subclock.service.validation.CallbackValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    private final UrlRepository urlRepository;
    private final CallbackValidator callbackValidation;

    @Autowired
    public UrlService(UrlRepository urlRepository, CallbackValidator callbackValidation) {
        this.callbackValidation = callbackValidation;
        this.urlRepository = urlRepository;
    }

    public CallbackDTO register(CallbackDTO callbackDTO){
        callbackValidation.validateFrequency(callbackDTO.getFrequency());
        urlRepository.addUrl(callbackDTO);
        return callbackDTO;
    }

    public CallbackDTO deregister(CallbackDTO callbackDTO){
        urlRepository.removeUrl(callbackDTO);
        return callbackDTO;
    }

    public CallbackDTO update(CallbackDTO callbackDTO){
        callbackValidation.validateFrequency(callbackDTO.getFrequency());
        urlRepository.updateUrl(callbackDTO);
        return callbackDTO;
    }
}
