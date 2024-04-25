package roomescape.controller.console;

import java.util.List;
import roomescape.dto.ReservationTimeRequest;
import roomescape.dto.ReservationTimeResponse;
import roomescape.service.ReservationTimeService;
import roomescape.view.ReservationTimeView;

public class ReservationTimeController {
    private final ReservationTimeView reservationTimeView;
    private final ReservationTimeService reservationTimeService;

    public ReservationTimeController() {
        reservationTimeView = new ReservationTimeView();
        this.reservationTimeService = new ReservationTimeService();
    }

    public void saveTime() {
        ReservationTimeRequest reservationTimeRequest = new ReservationTimeRequest(
                reservationTimeView.readStartAt()
        );
        reservationTimeService.saveTime(reservationTimeRequest);
        reservationTimeView.printSuccessfullyAdded();
    }

    public void deleteTime() {
        List<ReservationTimeResponse> reservationTimeResponses = getReservationTimeResponses();
        int reservationTimeId = reservationTimeView.readReservationTimeId(
                reservationTimeResponses
        );
        if (reservationTimeId < 0 || reservationTimeId > reservationTimeResponses.size()) {
            throw new IllegalArgumentException("[ERROR] 올바른 예약 가능 시간을 입력해주세요.");
        }
        reservationTimeService.deleteTime(reservationTimeId);
        reservationTimeView.printSuccessfullyDeleted();
    }

    private List<ReservationTimeResponse> getReservationTimeResponses() {
        return reservationTimeService.getTimes()
                .stream()
                .map(ReservationTimeResponse::from)
                .toList();
    }
}
