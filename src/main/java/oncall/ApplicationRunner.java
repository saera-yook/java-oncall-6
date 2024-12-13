package oncall;

import java.util.List;
import oncall.domain.TargetMonth;
import oncall.view.InputView;

public class ApplicationRunner {
    public void run() {
        List<String> monthAndDay = InputView.readMonthAndDay();
        TargetMonth targetMonth = new TargetMonth(Integer.parseInt(monthAndDay.get(0)), monthAndDay.get(1));
    }
}
