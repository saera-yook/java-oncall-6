package oncall;

import java.util.LinkedList;
import java.util.List;
import oncall.domain.OncallController;
import oncall.domain.TargetMonth;

public class ApplicationRunner {
    public void run() {
        OncallController controller = new OncallController();
        TargetMonth targetMonth = controller.requestMonthAndDay();
        List<List<String>> workersOrders = controller.requestWorkersOrder();
        List<String> normalOrderNames = workersOrders.getFirst();
        List<String> holidayOrderNames = workersOrders.getLast();
        targetMonth.assignDutyOrder(new LinkedList<>(normalOrderNames), new LinkedList<>(holidayOrderNames));
        System.out.println(targetMonth.makeDutyResult());
    }


}
