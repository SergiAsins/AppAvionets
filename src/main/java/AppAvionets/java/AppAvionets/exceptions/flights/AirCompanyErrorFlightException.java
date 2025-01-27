package AppAvionets.java.AppAvionets.exceptions.flights;

import AppAvionets.java.AppAvionets.exceptions.runtime.AirCompanyRuntimeException;

public class AirCompanyErrorFlightException extends AirCompanyRuntimeException {
    public AirCompanyErrorFlightException(String message) {
        super(message);
    }
}
