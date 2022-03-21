package com.simon.subclock.repository;

import com.simon.subclock.model.Callback;
import com.simon.subclock.model.CallbackDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;


@Repository
public class UrlRepository {

    @Autowired
    private final Store store;

    public UrlRepository(Store store){
        this.store = store;
    }

    public CallbackDTO addUrl(CallbackDTO callbackDTO){
        if(store.existsInStore(callbackDTO.getCallbackUrl())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("The resource with the following url %s already exists", callbackDTO.getCallbackUrl()));
        }

        store.addToStore(dtoToObject(callbackDTO));
        return callbackDTO;
    }

    public CallbackDTO removeUrl(CallbackDTO callbackDTO){
        String callbackUrl = callbackDTO.getCallbackUrl();
        if(store.existsInStore(callbackUrl)){
            store.removeFromStore(callbackUrl);
            return callbackDTO;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("The resource with the following url %s doesn't exists", callbackDTO.getCallbackUrl()));
    }

    public CallbackDTO updateUrl(CallbackDTO callbackDTO){
        if(store.existsInStore(callbackDTO.getCallbackUrl())){
            store.updateStore(dtoToObject(callbackDTO));
            return callbackDTO;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("The resource with the following url %s doesn't exists", callbackDTO.getCallbackUrl()));
    }

    private Callback dtoToObject(CallbackDTO callbackDTO){
        return new Callback().callbackUrl(callbackDTO.getCallbackUrl()).frequency(callbackDTO.getFrequency());
    }
}
