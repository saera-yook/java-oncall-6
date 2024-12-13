package oncall.domain;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import oncall.exception.InvalidMonthOrDayException;

public class TargetMonth {
    private static final List<String> koreanDays = Arrays.stream(DayOfWeek.values())
            .map(d -> d.getDisplayName(TextStyle.SHORT, Locale.KOREAN)).toList();
    private static final Map<Integer, List<Integer>> holidays = new HashMap<>();

    private final int month;
    private final String startKoreanDay;
    private final int endDay;
    private final List<String> days;
    private final List<String> names;

    public TargetMonth(final int month, final String startKoreanDay) {
        initializeHolidays();
        validateMonth(month);
        this.month = month;
        validateDay(startKoreanDay);
        this.startKoreanDay = startKoreanDay;
        this.endDay = setEndDay(this.month);
        this.days = setDays(this.month);
        names = new ArrayList<>();
    }

    private void initializeHolidays() {
        holidays.put(1, List.of(1));
        holidays.put(3, List.of(1));
        holidays.put(5, List.of(5));
        holidays.put(6, List.of(6));
        holidays.put(8, List.of(15));
        holidays.put(10, List.of(3, 9));
        holidays.put(12, List.of(25));
    }

    private int setEndDay(int month) {
        if (month == 2) {
            return 28;
        }
        if (month < 8 && month % 2 == 0) {
            return 30;
        }
        if (month > 8 && month % 2 == 1) {
            return 30;
        }
        return 31;
    }

    private List<String> setDays(int month) {
        int startInt = koreanDays.indexOf(startKoreanDay) + 1;
        if (holidays.containsKey(month)) {
            return setHolidayDays(month, startInt);
        }
        return setNonHolidayDays(startInt);
    }

    private List<String> setNonHolidayDays(int startInt) {
        List<String> days = new ArrayList<>();
        List<String> daysQueue = new LinkedList<>();
        for (int i = 0; i < 7; i++) {
            if (startInt + i > 7) {
                startInt -= 7;
            }
            daysQueue.add(DayOfWeek.of(startInt + i).getDisplayName(TextStyle.SHORT, Locale.KOREAN));
        }
        for (int i = 0; i < endDay; i++) {
            String day = daysQueue.removeFirst();
            daysQueue.add(day);
            days.add(day);
        }
        return days;
    }

    private List<String> setHolidayDays(int month, int startInt) {
        List<String> days = new ArrayList<>();
        List<String> daysQueue = new LinkedList<>();
        for (int i = 0; i < 7; i++) {
            daysQueue.add(DayOfWeek.of(startInt + i).getDisplayName(TextStyle.SHORT, Locale.KOREAN));
        }
        for (int i = 0; i < endDay; i++) {
            String day = daysQueue.removeFirst();
            daysQueue.add(day);
            if (holidays.get(month).contains(i + 1)) {
                day = day.concat("(휴일)");
            }
            days.add(day);
        }
        return days;
    }

    public void assignDutyOrder(LinkedList<String> normalOrder, LinkedList<String> holidayOrder) {
        for (String day : days) {
            names.add(getWorker(day, normalOrder, holidayOrder));
        }
    }

    private String getWorker(String day, LinkedList<String> normalOrder, LinkedList<String> holidayOrder) {
        if (day.contains("토") || day.contains("일") || day.contains("휴일")) {
            String worker = holidayOrder.removeFirst();
            if (isWorkedYesterday(worker)) {
                String nextWorker = holidayOrder.removeFirst();
                holidayOrder.addFirst(worker);
                holidayOrder.add(worker);
                holidayOrder.add(nextWorker);
                return nextWorker;
            }
            holidayOrder.add(worker);
            return worker;
        }
        String worker = normalOrder.removeFirst();
        if (isWorkedYesterday(worker)) {
            String nextWorker = normalOrder.removeFirst();
            normalOrder.addFirst(worker);
            normalOrder.add(worker);
            normalOrder.add(nextWorker);
            return nextWorker;
        }
        normalOrder.add(worker);
        return worker;
    }

    private boolean isWorkedYesterday(String name) {
        if (names.isEmpty()) {
            return false;
        }
        return names.getLast().equals(name);
    }

    public String makeDutyResult() {
        List<String> monthlyText = makeMonthlyText();
        return monthlyText.stream().map(s -> s.concat(names.get(monthlyText.indexOf(s)))).collect(Collectors.joining("\n"));
    }

    private List<String> makeMonthlyText() {
        List<String> monthlyText = new LinkedList<>();
        for (int i = 1; i <= endDay; i++) {
            monthlyText.add(String.format("%d월 %d일 %s ", month, i, days.get(i - 1)));
        }
        return monthlyText;
    }

    private static void validateMonth(int inputMonth) {
        if (inputMonth < 1 || inputMonth > 12) {
            throw new InvalidMonthOrDayException();
        }
    }

    private static void validateDay(String inputDay) {
        if (!koreanDays.contains(inputDay)) {
            throw new InvalidMonthOrDayException();
        }
    }
}
