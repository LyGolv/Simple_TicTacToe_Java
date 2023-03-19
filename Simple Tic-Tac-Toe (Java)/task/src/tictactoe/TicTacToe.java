package tictactoe;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class TicTacToe {

    private final String[] grid;
    private final int n = 3;
    private final Scanner scanner = new Scanner(System.in);

    public TicTacToe() {
        this.grid = Symbol.SPACE.value.repeat(n*n).split("");
    }

    public void run() {
        State state;
        int s = 0;
        while ((state = analyseGrid()).equals(State.NOT_FINISH)) {
            System.out.println(this);
            makeMove(Symbol.values()[s]);
            s = s == 0 ? 1 : 0;
        }
        System.out.println(this);
        System.out.println(state.value);
        scanner.close();
    }

    public boolean processInput(String str) {
        for (InputError inputError : InputError.values()) {
            if (inputError.check(str, grid)) return true;
        }
        return false;
    }

    public void makeMove(Symbol s) {
        String str;
        do {
            str = scanner.nextLine();
        } while (processInput(str));
        String[] crd = str.split(" ");
        List<Integer> l = List.of(Integer.parseInt(crd[0]), Integer.parseInt(crd[1]));
        grid[(l.get(0)-1)*n+(l.get(1)-1)] = s.value;
    }

    public boolean isHorizontalWin(Symbol s) {
        return IntStream.iterate(0, i  -> i < 3, i -> i + 1)
                .filter(i -> grid[i*n].equals(s.value) && grid[i*n+1].equals(s.value) && grid[i*n+2].equals(s.value))
                .count() != 0;
    }

    public boolean isVerticalWin(Symbol s) {
        return IntStream.iterate(0, j  -> j < 3, j -> j + 1)
                .filter(j -> grid[j].equals(s.value) && grid[n+j].equals(s.value) && grid[2*n+j].equals(s.value))
                .count() != 0;
    }

    public boolean isDiagonalWin(Symbol s) {
        return (grid[0].equals(s.value) && grid[n+1].equals(s.value) && grid[2*n+2].equals(s.value))
                || (grid[n-1].equals(s.value) && grid[n+1].equals(s.value) && grid[2*n].equals(s.value));
    }

    public boolean isFinish() {
        return Arrays.stream(grid).noneMatch(s -> s.equals(Symbol.SPACE.value));
    }

    public boolean isCheaterFound() {
        int[] sNb = new int[2];
        for (String s : grid) {
            sNb[s.equals(Symbol.X.value) ? 0 : 1] += s.equals(Symbol.X.value) || s.equals(Symbol.O.value) ? 1 : 0;
        }
        return Math.abs(sNb[0] - sNb[1]) >= 2;
    }

    public State analyseGrid() {
        State result = State.NOT_FINISH;
        for (Symbol s : Symbol.values()) {
            if ((isHorizontalWin(s) || isVerticalWin(s) || isDiagonalWin(s)) && !s.equals(Symbol.SPACE)) {
                if (result.equals(State.NOT_FINISH)) result = result.winner(s);
                else return State.IMPOSSIBLE;
            }
        }
        if (isCheaterFound()) return State.IMPOSSIBLE;
        return !result.equals(State.NOT_FINISH) ? result : isFinish() ? State.DRAW : State.NOT_FINISH;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("---------\n");
        IntStream.iterate(0, i -> i < grid.length, i -> i + 3)
                .forEach(i -> str.append("| %s %s %s |\n".formatted(grid[i], grid[i + 1], grid[i + 2])));
        str.append("---------\n");
        return str.toString();
    }
}
