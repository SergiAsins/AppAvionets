package AppAvionets.java.AppAvionets.roles.exceptions;

public class RoleException extends RuntimeException{
    public RoleException(String message){
        super(message);
    }

    public RoleException(String message, Throwable cause){
        super(message, cause);
    }
}
