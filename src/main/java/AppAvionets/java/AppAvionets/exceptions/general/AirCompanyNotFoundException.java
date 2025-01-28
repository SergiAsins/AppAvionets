package AppAvionets.java.AppAvionets.exceptions.general;

import AppAvionets.java.AppAvionets.exceptions.runtime.AirCompanyRuntimeException;

public class AirCompanyNotFoundException extends AirCompanyRuntimeException {
    public AirCompanyNotFoundException(String message){
        super(message);
    }
}
