package oncall.domain;

import java.util.List;
import oncall.view.IOHandler;
import oncall.view.InputView;

public class OncallController {
    public TargetMonth requestMonthAndDay() {
        return (TargetMonth) IOHandler.repeatableRead(() -> {
            List<String> monthAndDay = InputView.readMonthAndDay();
            return new TargetMonth(Integer.parseInt(monthAndDay.get(0)), monthAndDay.get(1));
        });
    }
}
