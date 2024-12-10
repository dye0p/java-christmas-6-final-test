package christmas.controller;

import christmas.model.Date;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.function.Supplier;

public class EventController {

    private final InputView inputView;
    private final OutputView outputView;

    public EventController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        //웰컴
        outputView.wellComeMessage();
        Date date = tryReadDate();


    }

    private Date tryReadDate() {
        return requestRead(() -> {
            int date = inputView.readDate();
            return Date.of(date);
        });
    }

    private <T> T requestRead(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception.getMessage());
            }
        }
    }
}
