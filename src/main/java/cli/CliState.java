package cli;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CliState {
    protected final CliFSM fsm;

    public void init() {

    }

    public void newTicketRequested() {

    }

    public void planetStatsRequested() {

    }

    public void unknownCommand(String cmd) {

    }
}