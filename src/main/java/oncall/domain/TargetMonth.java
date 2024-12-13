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

public class TargetMonth {
    private static final List<String> koreanDays = Arrays.stream(DayOfWeek.values())
            .map(d -> d.getDisplayName(TextStyle.SHORT, Locale.KOREAN)).toList();
    private static final Map<Integer, Integer> holidays = new HashMap<>();

    private final int month;
    private final String startDay;
    private final int endDay;
    private final List<String> days;

    public TargetMonth(final int month, final String startDay) {
        initializeHolidays();
        this.month = month;
        this.startDay = startDay;
        this.endDay = setEndDay(month);
        this.days = setDays(month);
        System.out.println(days);
    }

    private void initializeHolidays() {
        holidays.put(1, 1);
        holidays.put(3, 1);
        holidays.put(5, 5);
        holidays.put(6, 6);
        holidays.put(8, 15);
        holidays.put(10, 3);
        holidays.put(10, 9);
        holidays.put(12, 25);
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
        int startInt = koreanDays.indexOf(startDay) + 1;
        if (holidays.containsKey(month)) {
            return setHolidayDays(month, startInt);
        }
        return setNonHolidayDays(startInt);
    }

    private List<String> setNonHolidayDays(int startInt) {
        List<String> days = new ArrayList<>();
        List<String> daysQueue = new LinkedList<>();
        for (int i = 0; i < 7; i++) {
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
            if (i + 1 == holidays.get(month)) {
                day = day.concat("(휴일)");
            }
            days.add(day);
        }
        return days;
    }
}
