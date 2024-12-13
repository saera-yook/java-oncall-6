package oncall.exception;

import static oncall.exception.ExceptionConstants.ERROR_INVALID;

public class InvalidOrderException extends UserIllegalArgumentException {
    public InvalidOrderException() {
        super(ERROR_INVALID);
    }
}