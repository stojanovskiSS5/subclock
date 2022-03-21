package com.simon.subclock.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class CallbackDTO {

    @JsonProperty("callbackUrl")
    @Valid
    private String callbackUrl;
    @JsonProperty("frequency")
    private int frequency;

    public CallbackDTO(){

    }

    public CallbackDTO callbackUrl(String callbackUrl){
        this.callbackUrl = callbackUrl;
        return this;
    }

    public CallbackDTO frequency(int frequency){
        this.frequency = frequency;
        return this;
    }

    @NotNull
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

    public boolean equals(Object o) {
        if (this == o){
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            CallbackDTO callbackDTO = (CallbackDTO) o;
            return Objects.equals(this.callbackUrl, callbackDTO.callbackUrl) &&
                    Objects.equals(this.frequency, callbackDTO.frequency);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("class CallbackDTO {\n");
        sb.append("    callbackUrl: ").append(this.toIndentedString(this.callbackUrl)).append("\n");
        sb.append("    frequency: ").append(this.toIndentedString(this.frequency)).append("\n");
        sb.append("}");

        return sb.toString();
    }

    private String toIndentedString(Object o){
        return o == null ? "null" : o.toString().replace("\n", "\n     ");
    }
}
