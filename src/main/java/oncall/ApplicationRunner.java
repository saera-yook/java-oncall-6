package oncall;

import java.util.LinkedList;
import java.util.List;
import oncall.domain.TargetMonth;
import oncall.view.InputView;

public class ApplicationRunner {
    public void run() {
        List<String> monthAndDay = InputView.readMonthAndDay();
        TargetMonth targetMonth = new TargetMonth(Integer.parseInt(monthAndDay.get(0)), monthAndDay.get(1));
        List<String> normalOrderNames = InputView.readNormalOrder();
        List<String> holidayOrderNames = InputView.readHolidayOrder();
        targetMonth.assignDutyOrder(new LinkedList<>(normalOrderNames), new LinkedList<>(holidayOrderNames));
        System.out.println(targetMonth.makeDutyResult());
    }
}
