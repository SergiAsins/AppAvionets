package AppAvionets.java.AppAvionets.exceptions;

import AppAvionets.java.AppAvionets.exceptions.runtime.AirCompanyRuntimeException;

public class AirCompanyAlreadyExistsException extends AirCompanyRuntimeException {
    public AirCompanyAlreadyExistsException(String message) {
        super(message);
    }
}
