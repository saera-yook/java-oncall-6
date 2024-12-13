package oncall.domain;

import java.util.HashSet;
import java.util.List;
import oncall.exception.InvalidNameLengthException;
import oncall.exception.InvalidOrderException;
import oncall.view.IOHandler;
import oncall.view.InputView;

public class OncallController {
    public TargetMonth requestMonthAndDay() {
        return (TargetMonth) IOHandler.repeatableRead(() -> {
            List<String> monthAndDay = InputView.readMonthAndDay();
            return new TargetMonth(Integer.parseInt(monthAndDay.get(0)), monthAndDay.get(1));
        });
    }

    public List<List<String>> requestWorkersOrder() {
        return (List<List<String>>) IOHandler.repeatableRead(() -> {
            List<String> normalOrder = InputView.readNormalOrder();
            List<String> holidayOrder = InputView.readHolidayOrder();
            validateNameLength(normalOrder);
            validateDuplicateName(normalOrder);
            validateNameLength(holidayOrder);
            validateDuplicateName(holidayOrder);
            validateAllWorkers(normalOrder, holidayOrder);
            return List.of(normalOrder, holidayOrder);
        });
    }

    private void validateNameLength(List<String> workersOrder) {
        for (String worker : workersOrder) {
            if (worker.length() > 5) {
                throw new InvalidNameLengthException();
            }
        }
    }

    private void validateDuplicateName(List<String> workersOrder) {
        if (new HashSet<>(workersOrder).size() != workersOrder.size()) {
            throw new InvalidNameLengthException();
        }
    }

    private void validateAllWorkers(List<String> normalOrder, List<String> holidayOrder) {
        if (normalOrder.size() != holidayOrder.size() || !isInRange(normalOrder.size())) {
            throw new InvalidOrderException();
        }
        if (!new HashSet<>(normalOrder).containsAll(holidayOrder)) {
            throw new InvalidOrderException();
        }
    }

    private boolean isInRange(int value) {
        return value >= 5 && value <= 35;
    }
}
