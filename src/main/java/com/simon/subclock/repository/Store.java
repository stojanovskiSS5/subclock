package com.simon.subclock.repository;

import com.simon.subclock.model.Callback;
import com.simon.subclock.model.CallbackDTO;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class Store {

    private final ConcurrentHashMap<String, Callback> urlStore;

    public Store() {
        this.urlStore = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<String, Callback> getStore(){
        return urlStore;
    }

    public void addToStore(Callback callback){
        this.urlStore.put(callback.getCallbackUrl(), callback);
    }

    public void removeFromStore(String callbackUrl){
        this.urlStore.remove(callbackUrl);
    }

    public void updateStore(Callback callback){
        this.urlStore.replace(callback.getCallbackUrl(), callback);
    }

    public boolean existsInStore(String callbackUrl){
        return this.urlStore.containsKey(callbackUrl);
    }
}
