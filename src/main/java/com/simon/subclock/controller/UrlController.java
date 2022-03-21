package com.simon.subclock.controller;

import com.simon.subclock.model.CallbackDTO;
import com.simon.subclock.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UrlController {

    private final UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping(value = "v1/url")
    public ResponseEntity<CallbackDTO> register(@Valid @RequestBody CallbackDTO callbackDTO){
        CallbackDTO register = urlService.register(callbackDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(register);
    }

    @DeleteMapping(value = "v1/url")
    public ResponseEntity<CallbackDTO> deregister(@Valid @RequestBody CallbackDTO callbackDTO){
        CallbackDTO deregister = urlService.deregister(callbackDTO);
        return ResponseEntity.status(HttpStatus.OK).body(deregister);
    }

    @PutMapping(value = "v1/url")
    public ResponseEntity<CallbackDTO> updateCallback(@Valid @RequestBody CallbackDTO callbackDTO){
        CallbackDTO updated = urlService.update(callbackDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

}
