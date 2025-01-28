package AppAvionets.java.AppAvionets.exceptions.general;

import AppAvionets.java.AppAvionets.exceptions.runtime.AirCompanyRuntimeException;

public class AirCompanyAlreadyExistsException extends AirCompanyRuntimeException {
    public AirCompanyAlreadyExistsException(String message) {
        super(message);
    }
}
