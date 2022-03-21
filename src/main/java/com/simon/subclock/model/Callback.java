package com.simon.subclock.model;

public class Callback {


    private String callbackUrl;
    private int frequency;
    private int cronJobSeconds;

    public Callback(){

    }

    public Callback callbackUrl(String callbackUrl){
        this.callbackUrl = callbackUrl;
        return this;
    }

    public Callback frequency(int frequency){
        this.frequency = frequency;
        return this;
    }

    public Callback cronJobSeconds(int cronJobSeconds){
        this.cronJobSeconds = cronJobSeconds;
        return this;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getCronJobSeconds() {
        return cronJobSeconds;
    }

    public void setCronJobSeconds(int cronJobSeconds) {
        this.cronJobSeconds = cronJobSeconds;
    }
}
