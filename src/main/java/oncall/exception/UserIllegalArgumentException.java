package oncall.exception;

import static oncall.exception.ExceptionConstants.ERROR_PREFIX;
import static oncall.exception.ExceptionConstants.ERROR_SUFFIX;

public class UserIllegalArgumentException extends IllegalArgumentException {
    public UserIllegalArgumentException(String message) {
        super(ERROR_PREFIX + message + ERROR_SUFFIX);
    }
}
