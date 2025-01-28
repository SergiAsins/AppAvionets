package AppAvionets.java.AppAvionets.exceptions.profiles;

import AppAvionets.java.AppAvionets.exceptions.runtime.AirCompanyRuntimeException;

public class AirCompanyProfileErrorException extends AirCompanyRuntimeException {
    public AirCompanyProfileErrorException(String message) {
        super(message);
    }
}
