package AppAvionets.java.AppAvionets.exceptions.reservations;

import AppAvionets.java.AppAvionets.exceptions.runtime.AirCompanyRuntimeException;

public class AirCompanyReservationErrorException extends AirCompanyRuntimeException {
    public AirCompanyReservationErrorException(String message) {
        super(message);
    }
}
