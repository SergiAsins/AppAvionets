package AppAvionets.java.AppAvionets.exceptions;

import AppAvionets.java.AppAvionets.exceptions.runtime.AirCompanyRuntimeException;

public class AirCompanyInvalidFormatException extends AirCompanyRuntimeException {
    public AirCompanyInvalidFormatException(String message) {
        super(message);
    }
}
