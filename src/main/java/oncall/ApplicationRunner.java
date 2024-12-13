package oncall;

import java.util.List;
import oncall.view.InputView;

public class ApplicationRunner {
    public void run() {
        List<String> monthAndDay = InputView.readMonthAndDay();
    }
}
