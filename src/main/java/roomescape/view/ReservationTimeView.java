package roomescape.view;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import roomescape.dto.ReservationTimeResponse;

public class ReservationTimeView {
    private final Scanner scanner = new Scanner(System.in);

    private static final Pattern TIME_PATTERN = Pattern.compile("^\\d+:\\d+$");

    public LocalTime readStartAt() {
        System.out.println("[INFO] 방탈출 예약이 가능한 시간을 추가해주세요. (ex. 23:30)");
        String rawReservationTime = scanner.nextLine();
        TIME_PATTERN.matcher(rawReservationTime);

        return LocalTime.parse(rawReservationTime);
    }

    public void printSuccessfullyAdded() {
        System.out.println("[INFO] 예약 가능 시간이 추가되었습니다.");
    }

    public int readReservationTimeId(final List<ReservationTimeResponse> reservationTimeResponses) {
        System.out.println("[INFO] 삭제할 방탈출 예약 가능 시간의 번호를 선택해주세요.");
        for (int index = 0; index < reservationTimeResponses.size(); index++) {
            printReservationTime(index + 1, reservationTimeResponses.get(index));
        }
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("[ERROR] 예약 가능 시간의 번호를 올바르게 입력해주세요.");
        }
    }

    public void printReservationTime(final int index, final ReservationTimeResponse response) {
        System.out.printf("%d. %s%n", index, response.startAt().toString());
    }

    public void printSuccessfullyDeleted() {
        System.out.println("[INFO] 예약 가능 시간이 삭제되었습니다.");
    }
}
