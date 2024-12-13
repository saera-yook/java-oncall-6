package oncall.view;

import java.util.function.Supplier;
import oncall.exception.UserIllegalArgumentException;

public class IOHandler {
    public static <T> Object repeatableRead(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (UserIllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
