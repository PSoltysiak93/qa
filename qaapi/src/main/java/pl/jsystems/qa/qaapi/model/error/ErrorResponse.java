package pl.jsystems.qa.qaapi.model.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.jsystems.qa.qaapi.model.User;

import java.util.List;

public class ErrorResponse {
    public ErrorBody Error;

    public static class ErrorBody {

        @JsonProperty(value = "error.code")
        public int errorCode;

        @JsonProperty(value = "validation_erro")
        public String validationError;
        public String message;


        }
    }
