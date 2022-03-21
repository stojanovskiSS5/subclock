package com.simon.subclock.model;

import java.util.Objects;

public class Fault {

    private String status;
    private String message;
    private String dateTime;

    public String getStatus() {
        return status;
    }

    public Fault status(String status){
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Fault message(String message){
        this.message = message;
        return this;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Fault dateTime(String dateTime){
        this.dateTime = dateTime;
        return this;
    }

    public boolean equals(Object o) {
        if (this == o){
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Fault fault = (Fault) o;
            return Objects.equals(this.status, fault.status) &&
                    Objects.equals(this.dateTime, fault.dateTime) &&
                    Objects.equals(this.message, fault.message);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("class Fault {\n");
        sb.append("    suggestedStatus: ").append(this.toIndentedString(this.status)).append("\n");
        sb.append("    message: ").append(this.toIndentedString(this.message)).append("\n");
        sb.append("    dateTime: ").append(this.toIndentedString(this.status)).append("\n");
        sb.append("}");

        return sb.toString();
    }

    private String toIndentedString(Object o){
        return o == null ? "null" : o.toString().replace("\n", "\n     ");
    }
}
