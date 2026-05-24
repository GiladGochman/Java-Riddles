
public class overlap {
    public static boolean checkY(Square[][] sq, int y, int x) {
        Square p = sq[y][x];
        int v = p.getValue();
        if (v == 0) {
            return false;
        }
        int j = 0;
        while (j < sq.length) {
            if (sq[y][j].getValue() == v && j != x) {
                return true;
            }
            ++j;
        }
        return false;
    }

    public static boolean checkX(Square[][] sq, int y, int x) {
        Square p = sq[y][x];
        int v = p.getValue();
        if (v == 0) {
            return false;
        }
        int i = 0;
        while (i < sq.length) {
            if (sq[i][x].getValue() == v && i != y) {
                return true;
            }
            ++i;
        }
        return false;
    }

    public static boolean checkSquare(Square[][] sq, int y, int x) {
        Square p = sq[y][x];
        int v = p.getValue();
        if (v == 0) {
            return false;
        }
        int ry = y / 3;
        int rx = x / 3;
        int i = ry * 3;
        while (i < ry * 3 + 3) {
            int j = rx * 3;
            while (j < rx * 3 + 3) {
                if (sq[i][j].getValue() == v && i != y && j != x) {
                    return true;
                }
                ++j;
            }
            ++i;
        }
        return false;
    }

    // Propagate a placed value: remove it from candidates of every cell in the same row, column, and 3x3 box.
    public static int removeOpions(Square[][] sq, int y, int x) {
        System.out.println("RemoveOptions: " + y + ", " + x);
        int ret = 0;
        Square p = sq[y][x];
        int v = p.getValue();
        int ry = y / 3;
        int rx = x / 3;
        if (v == 0) {
            return 0;
        }
        int j = 0;
        while (j < sq.length) {
            if (j != x) {
                ret += sq[y][j].removeOption(v);
            }
            ++j;
        }
        int i = 0;
        while (i < sq.length) {
            if (i != y) {
                ret += sq[i][x].removeOption(v);
            }
            ++i;
        }
        i = ry * 3;
        while (i < ry * 3 + 3) {
            int j2 = rx * 3;
            while (j2 < rx * 3 + 3) {
                if (i != y || j2 != x) {
                    sq[i][j2].removeOption(v);
                }
                ++j2;
            }
            ++i;
        }
        return ret;
    }
}

