package tictactoe;

import java.util.List;

public enum InputError {

    WRONG_INPUT {
        @Override
        public boolean check(String s, String[] grid) {
            if (!s.matches("\\d \\d")) {
                System.out.println("You Should enter numbers!");
                return true;
            }
            return false;
        }
    },

    WRONG_COORDINATE {
        @Override
        public boolean check(String s, String[] grid) {
            String[] crd = s.split(" ");
            List<Integer> l = List.of(Integer.parseInt(crd[0]), Integer.parseInt(crd[1]));
            if (l.get(0) < 0 || l.get(1) < 0 || l.get(0) > 3 || l.get(1) > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                return true;
            }
            return false;
        }
    },

    OCCUPIED {
        @Override
        public boolean check(String s, String[] grid) {
            String[] crd = s.split(" ");
            List<Integer> l = List.of(Integer.parseInt(crd[0]), Integer.parseInt(crd[1]));
            if (!grid[(l.get(0)-1)*3+(l.get(1)-1)].equals(Symbol.SPACE.value)) {
                System.out.println("This cell is occupied! Choose another one!");
                return true;
            }
            return false;
        }
    },
    ;

    public abstract boolean check(String s, String[] grid);
}
