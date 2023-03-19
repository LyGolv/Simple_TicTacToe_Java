package tictactoe;

public enum State {
    X_WIN(Symbol.X.value + " wins"),
    O_WIN(Symbol.O.value + " wins"),
    DRAW("Draw"),
    NOT_FINISH("Game not finished"),
    IMPOSSIBLE("Impossible");

    public final String value;

    State(String s) {
        value = s;
    }

    public State winner(Symbol s) {
        return s.equals(Symbol.X) ? X_WIN : O_WIN;
    }
}
