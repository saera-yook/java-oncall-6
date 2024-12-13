package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;

public class InputView {
    public static List<String> readMonthAndDay() {
        System.out.println("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        String input = Console.readLine();
        return Arrays.stream(input.split(",")).toList();
    }

    public static List<String> readNormalOrder() {
        System.out.println("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        String input = Console.readLine();
        return Arrays.stream(input.split(",")).toList();
    }

    public static List<String> readHolidayOrder() {
        System.out.println("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        String input = Console.readLine();
        return Arrays.stream(input.split(",")).toList();
    }
}
