package oncall.exception;

import static oncall.exception.ExceptionConstants.ERROR_INVALID_ORDER;

public class InvalidOrderException extends UserIllegalArgumentException {
    public InvalidOrderException() {
        super(ERROR_INVALID_ORDER);
    }
}