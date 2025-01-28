package AppAvionets.java.AppAvionets.exceptions.users;

import AppAvionets.java.AppAvionets.exceptions.runtime.AirCompanyRuntimeException;

public class AirCompanyUserNotFoundException extends AirCompanyRuntimeException {
    public AirCompanyUserNotFoundException(String message) {
        super(message);
    }
}
