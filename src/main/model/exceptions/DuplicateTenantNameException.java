package model.exceptions;

public class DuplicateTenantNameException extends DuplicateNameException {

    public DuplicateTenantNameException(String message) {
        super(message);
    }
}
