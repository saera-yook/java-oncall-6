package oncall.exception;

import static oncall.exception.ExceptionConstants.ERROR_INVALID;

public class InvalidNameLengthException extends UserIllegalArgumentException {
    public InvalidNameLengthException() {
        super(ERROR_INVALID);
    }
}