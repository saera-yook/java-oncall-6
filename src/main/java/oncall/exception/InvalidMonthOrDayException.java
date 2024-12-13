package oncall.exception;

import static oncall.exception.ExceptionConstants.ERROR_INVALID_MONTH_OR_DAY;

public class InvalidMonthOrDayException extends UserIllegalArgumentException {
    public InvalidMonthOrDayException() {
        super(ERROR_INVALID_MONTH_OR_DAY);
    }
}