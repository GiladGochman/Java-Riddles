
import java.awt.Point;
import java.util.ArrayList;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Advanced {
    public static int rowRemoval(Square[][] sq, int row, int BigCol, int index) {
        int ret = 0;
        System.out.println("rowRemoval: " + row + ", index: " + index + ", BigCol: " + BigCol);
        int m = 0;
        while (m < sq.length) {
            if (m / 3 != BigCol) {
                ret += sq[m][row].removeOption(index);
            }
            ++m;
        }
        return ret;
    }

    public static int colRemoval(Square[][] sq, int col, int BigRow, int index) {
        int ret = 0;
        System.out.println("colRemoval: " + col + ", index: " + index + ", BigRow: " + BigRow);
        int m = 0;
        while (m < sq.length) {
            if (m / 3 != BigRow) {
                ret += sq[col][m].removeOption(index);
            }
            ++m;
        }
        return ret;
    }

    public static int isLine(Square[][] sq, int BigCol, int BigRow, ArrayList<Point> loc, int index) {
        int ret = 0;
        int col = loc.get((int)0).x;
        int row = loc.get((int)0).y;
        boolean flagCol = false;
        boolean flagRow = false;
        int k = 1;
        while (k < loc.size()) {
            if (col != loc.get((int)k).x) {
                flagCol = true;
            }
            if (row != loc.get((int)k).y) {
                flagRow = true;
            }
            ++k;
        }
        if (!flagRow) {
            ret += Advanced.rowRemoval(sq, row, BigCol, index);
        }
        if (!flagCol) {
            ret += Advanced.colRemoval(sq, col, BigRow, index);
        }
        return ret;
    }

    public static int algorithm(Square[][] sq, int BigCol, int BigRow) {
        int ret = 0;
        int index = 1;
        while (index <= sq.length) {
            int counter = 0;
            ArrayList<Point> loc = new ArrayList<Point>();
            boolean doBreak = false;
            int col = 3 * BigCol;
            while (col < 3 + 3 * BigCol) {
                int row = 3 * BigRow;
                while (row < 3 + 3 * BigRow) {
                    if (sq[col][row].getValue() == index) {
                        counter = 0;
                        doBreak = true;
                        break;
                    }
                    if (sq[col][row].isPossible(index)) {
                        ++counter;
                        loc.add(new Point(col, row));
                    }
                    ++row;
                }
                if (doBreak) break;
                ++col;
            }
            if (counter == 1) {
                int i = 1;
                while (i <= sq.length) {
                    if (i != index) {
                        sq[loc.get((int)0).x][loc.get((int)0).y].removeOption(i);
                    }
                    ++i;
                }
            }
            if (counter == 2 || counter == 3) {
                ret += Advanced.isLine(sq, BigCol, BigRow, loc, index);
            }
            ++index;
        }
        return ret;
    }
}

