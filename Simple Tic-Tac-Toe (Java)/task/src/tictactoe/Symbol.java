package tictactoe;

public enum Symbol {
    X("X"), O("O"), SPACE(" ");

    public final String value;

    Symbol(String o) {
        value = o;
    }
}
