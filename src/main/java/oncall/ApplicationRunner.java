package oncall;

import java.util.LinkedList;
import java.util.List;
import oncall.domain.OncallController;
import oncall.domain.TargetMonth;
import oncall.view.InputView;

public class ApplicationRunner {
    public void run() {
        OncallController controller = new OncallController();
        TargetMonth targetMonth = controller.requestMonthAndDay();
        List<String> normalOrderNames = InputView.readNormalOrder();
        List<String> holidayOrderNames = InputView.readHolidayOrder();
        targetMonth.assignDutyOrder(new LinkedList<>(normalOrderNames), new LinkedList<>(holidayOrderNames));
        System.out.println(targetMonth.makeDutyResult());
    }
}
