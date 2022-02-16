package model.exceptions;

import java.util.Date;

public class DuplicateCivicAddressException extends DuplicateNameException {

    public DuplicateCivicAddressException(String message) {
        super(message);
    }
}
