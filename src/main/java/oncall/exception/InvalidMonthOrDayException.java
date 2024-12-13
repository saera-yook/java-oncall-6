package oncall.exception;

import static oncall.exception.ExceptionConstants.ERROR_INVALID;

public class InvalidMonthOrDayException extends UserIllegalArgumentException {
    public InvalidMonthOrDayException() {
        super(ERROR_INVALID);
    }
}