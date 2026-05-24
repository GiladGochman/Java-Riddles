
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JTextField;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Solve {
    /**
     * Overlap technique: for the i-th clue block, find the leftmost possible end position
     * (pushing all preceding blocks as far left as possible) and the rightmost possible start
     * position (pushing all following blocks as far right as possible). Any cells in between
     * those two positions must be filled regardless of the exact placement.
     */
    public static boolean overlap(ArrayList<Integer> info, JTextField[] db) {
        boolean redbool = false;
        int i = 0;
        while (i < info.size()) {
            int k;
            int j;
            int pos = -1;
            int posRev = db.length;
            int k2 = info.size() - 1;
            while (k2 >= info.size() - 1 - i) {
                j = 0;
                while (j < info.get(k2) && pos + 1 < db.length) {
                    j = db[pos + 1].getText().equals("x") ? 0 : ++j;
                    ++pos;
                }
                if (pos + 1 < db.length && db[pos + 1].getBackground().getRed() == 0) {
                    ++pos;
                }
                ++pos;
                --k2;
            }
            --pos;
            k2 = 0;
            while (k2 <= info.size() - 1 - i) {
                j = 0;
                while (j < info.get(k2) && posRev > 0) {
                    j = db[posRev - 1].getText().equals("x") ? 0 : ++j;
                    --posRev;
                }
                if (posRev - 1 >= 0 && db[posRev - 1].getBackground().getRed() == 0) {
                    --posRev;
                }
                --posRev;
                ++k2;
            }
            int diff = pos - ++posRev + 1;
            if (pos >= posRev) {
                k = posRev;
                while (k <= pos) {
                    if (k >= 0 && k < db.length && !db[k].getText().equals("x")) {
                        --diff;
                    }
                    ++k;
                }
            }
            if (diff == 0) {
                k = posRev;
                while (k <= pos) {
                    if (k >= 0 && k < db.length && db[k].getBackground().getRed() != 0 && !db[k].getText().equals("x")) {
                        db[k].setBackground(new Color(0, 0, 0));
                        redbool = true;
                    }
                    ++k;
                }
            }
            ++i;
        }
        return redbool;
    }

    public static boolean compile(JTextField[][] db, ArrayList<Integer>[] horz, ArrayList<Integer>[] vert) {
        BoardFrame bff = Start.input.bf;
        boolean redbool = false;
        int i = 0;
        while (i < bff.width) {
            boolean bl = redbool = Solve.overlap(horz[i], db[i]) || redbool;
            if (db[17][8].getBackground().getRed() == 0) {
                // empty if block
            }
            boolean bl2 = redbool = Solve.markBorders(horz[i], db[i]) || redbool;
            if (db[17][8].getBackground().getRed() == 0) {
                // empty if block
            }
            boolean bl3 = redbool = Solve.allDone(horz[i], db[i]) || redbool;
            if (db[17][8].getBackground().getRed() == 0) {
                // empty if block
            }
            boolean bl4 = redbool = Solve.blockAway(horz[i], db[i]) || redbool;
            if (db[17][8].getBackground().getRed() == 0) {
                // empty if block
            }
            boolean bl5 = redbool = Solve.blackOrXEdgeOfBlank(horz[i], db[i]) || redbool;
            if (db[17][8].getBackground().getRed() == 0) {
                // empty if block
            }
            redbool = Solve.markBorderOfBiggest(horz[i], db[i]) || redbool;
            ++i;
        }
        i = 0;
        while (i < bff.height) {
            JTextField[] dbHorz = new JTextField[bff.width];
            int j = 0;
            while (j < bff.width) {
                dbHorz[j] = db[j][i];
                ++j;
            }
            boolean bl = redbool = Solve.overlap(vert[i], dbHorz) || redbool;
            if (db[17][8].getBackground().getRed() == 0) {
                // empty if block
            }
            boolean bl6 = redbool = Solve.markBorders(vert[i], dbHorz) || redbool;
            if (db[17][8].getBackground().getRed() == 0) {
                // empty if block
            }
            boolean bl7 = redbool = Solve.allDone(vert[i], dbHorz) || redbool;
            if (db[17][8].getBackground().getRed() == 0) {
                // empty if block
            }
            boolean bl8 = redbool = Solve.blockAway(vert[i], dbHorz) || redbool;
            if (db[17][8].getBackground().getRed() == 0) {
                // empty if block
            }
            boolean bl9 = redbool = Solve.blackOrXEdgeOfBlank(vert[i], dbHorz) || redbool;
            if (db[17][8].getBackground().getRed() == 0) {
                // empty if block
            }
            redbool = Solve.markBorderOfBiggest(vert[i], dbHorz) || redbool;
            ++i;
        }
        return redbool;
    }

    // If the number of already-filled black cells equals the sum of all clues, every remaining unknown cell must be empty.
    public static boolean allDone(ArrayList<Integer> info, JTextField[] db) {
        boolean redbool = false;
        if (Solve.countBlacks(db) == Solve.listSum(info)) {
            int i = 0;
            while (i < db.length) {
                if (db[i].getBackground().getRed() != 0 && !db[i].getText().equals("x")) {
                    redbool = true;
                    db[i].setText("x");
                }
                ++i;
            }
        }
        return redbool;
    }

    public static int findListMax(ArrayList<Integer> info) {
        int max = info.get(0);
        int i = 1;
        while (i < info.size()) {
            if (info.get(i) > max) {
                max = info.get(i);
            }
            ++i;
        }
        return max;
    }

    // If a single empty gap between two filled/x cells is too narrow to fit even the smallest clue block, mark it as empty (x).
    public static boolean blockAway(ArrayList<Integer> info, JTextField[] db) {
        boolean redbool = false;
        int max = Solve.findListMax(info);
        int i = 0;
        int countEmpty = 0;
        int indexEmpty = -1;
        int countSt = 0;
        int countNd = 0;
        while (i < db.length) {
            if (db[i].getBackground().getRed() == 0) {
                if (countEmpty == 0) {
                    ++countSt;
                }
                if (countEmpty == 1 && ++countNd + countSt + 1 > max) {
                    db[indexEmpty].setText("x");
                    System.out.println("Aaaaay!");
                    redbool = true;
                }
            }
            if (db[i].getText().length() > 0) {
                countEmpty = 0;
                countNd = 0;
                countSt = 0;
            } else {
                indexEmpty = i;
                if (++countEmpty > 1) {
                    countEmpty = 0;
                    countNd = 0;
                    countSt = 0;
                }
            }
            ++i;
        }
        return redbool;
    }

    public static int countBlacks(JTextField[] db) {
        int sum = 0;
        int i = 0;
        while (i < db.length) {
            if (db[i].getBackground().getRed() == 0) {
                ++sum;
            }
            ++i;
        }
        return sum;
    }

    public static int listSum(ArrayList<Integer> info) {
        int sum = 0;
        int i = 0;
        while (i < info.size()) {
            sum += info.get(i).intValue();
            ++i;
        }
        return sum;
    }

    public static boolean fillCloseXs(ArrayList<Integer> info, JTextField[] db) {
        boolean redbool = false;
        int min = Solve.findListMin(info);
        int i = 0;
        while (i < db.length) {
            if (db[i].getText().equals("x")) {
                int j = i + 1;
                while (j < db.length && j <= i + min) {
                    if (db[j].getText().equals("x")) {
                        int k = i;
                        while (k < j) {
                            if (db[k].getText().equals("x")) {
                                i = j;
                            } else {
                                db[k].setText("x");
                                redbool = true;
                            }
                            ++k;
                        }
                    }
                    ++j;
                }
            }
            ++i;
        }
        return redbool;
    }

    public static int findListMin(ArrayList<Integer> info) {
        int min = info.get(0);
        int i = 1;
        while (i < info.size()) {
            if (info.get(i) < min) {
                min = info.get(i);
            }
            ++i;
        }
        return min;
    }

    public static boolean blackOrXEdgeOfBlank(ArrayList<Integer> info, JTextField[] db) {
        int j2;
        int j;
        boolean b;
        boolean redbool = false;
        int i = 0;
        int count = 0;
        int countBig = info.size() - 1;
        while (countBig >= 0 && i < db.length && (db[i].getText().length() != 0 || db[i].getBackground().getRed() == 0)) {
            if (db[i].getBackground().getRed() == 0) {
                ++count;
            } else {
                if (info.get(countBig) == count) {
                    --countBig;
                }
                count = 0;
            }
            ++i;
        }
        if (i < db.length && countBig >= 0 && count == 0) {
            b = false;
            j = 0;
            while (j < info.get(countBig)) {
                if (i + j < 20) {
                    if (db[i + j].getBackground().getRed() == 0) {
                        b = true;
                    }
                    if (db[i + j].getText().equals("x")) {
                        j2 = j;
                        while (j2 >= 0) {
                            if (!db[i + j2].getText().equals("x")) {
                                db[i + j2].setText("x");
                                redbool = true;
                            }
                            --j2;
                        }
                    }
                    if (b && db[i + j].getBackground().getRed() != 0 && db[i + j].getText().length() == 0) {
                        db[i + j].setBackground(new Color(0, 0, 0));
                        redbool = true;
                    }
                }
                ++j;
            }
        }
        i = db.length - 1;
        count = 0;
        countBig = 0;
        while (countBig < info.size() && i >= 0 && (db[i].getText().length() != 0 || db[i].getBackground().getRed() == 0)) {
            if (db[i].getBackground().getRed() == 0) {
                ++count;
            } else {
                if (info.get(countBig) == count) {
                    ++countBig;
                }
                count = 0;
            }
            --i;
        }
        if (countBig < info.size() && i >= 0 && count == 0) {
            b = false;
            j = 0;
            while (j < info.get(countBig)) {
                if (i - j >= 0) {
                    if (db[i - j].getBackground().getRed() == 0) {
                        b = true;
                    }
                    if (db[i - j].getText().equals("x")) {
                        j2 = j;
                        while (j2 >= 0) {
                            if (!db[i - j2].getText().equals("x")) {
                                db[i - j2].setText("x");
                                redbool = true;
                            }
                            --j2;
                        }
                    }
                    if (b && db[i - j].getBackground().getRed() != 0 && db[i - j].getText().length() == 0) {
                        db[i - j].setBackground(new Color(0, 0, 0));
                        redbool = true;
                    }
                }
                ++j;
            }
        }
        return redbool;
    }

    // If a run of filled cells at the edge of an unknown region exactly matches the next clue,
    // the cell immediately after that run must be empty (it cannot extend the block further).
    public static boolean markBorders(ArrayList<Integer> info, JTextField[] db) {
        boolean redbool = false;
        int i = 0;
        int count = 0;
        int countBig = info.size() - 1;
        while (countBig >= 0 && i < db.length && (db[i].getText().length() != 0 || db[i].getBackground().getRed() == 0)) {
            if (db[i].getBackground().getRed() == 0) {
                ++count;
            } else {
                if (info.get(countBig) == count) {
                    --countBig;
                }
                count = 0;
            }
            ++i;
        }
        if (countBig >= 0 && i < db.length && info.get(countBig) == count) {
            db[i].setText("x");
            redbool = true;
        }
        i = db.length - 1;
        count = 0;
        countBig = 0;
        while (countBig < info.size() && i >= 0 && (db[i].getText().length() != 0 || db[i].getBackground().getRed() == 0)) {
            if (db[i].getBackground().getRed() == 0) {
                ++count;
            } else {
                if (info.get(countBig) == count) {
                    ++countBig;
                }
                count = 0;
            }
            --i;
        }
        if (countBig < info.size() && i >= 0 && info.get(countBig) == count) {
            db[i].setText("x");
            redbool = true;
        }
        return redbool;
    }

    public static boolean isDistinct(int num, ArrayList<Integer> info) {
        int count = 0;
        int i = 0;
        while (i < info.size()) {
            if (info.get(i) == num) {
                ++count;
            }
            ++i;
        }
        return count == 1;
    }

    // When a filled run whose length exactly matches the largest (and unique) clue is found,
    // place empty markers on both sides of that run.
    public static boolean markBorderOfBiggest(ArrayList<Integer> info, JTextField[] db) {
        boolean redbool = false;
        int pos1 = -1;
        int pos2 = -1;
        int biggest = Solve.findListMax(info);
        boolean isDistinct = Solve.isDistinct(biggest, info);
        int i = 0;
        while (i < db.length) {
            if (db[i].getBackground().getRed() == 0) {
                pos1 = i - 1;
                while (i < db.length && db[i].getBackground().getRed() == 0) {
                    pos2 = i + 1;
                    ++i;
                }
                if (isDistinct) {
                    i = db.length - 1;
                }
            }
            if (pos2 - pos1 - 1 == biggest) {
                if (pos1 > -1 && db[pos1].getText().length() == 0) {
                    redbool = true;
                    db[pos1].setText("x");
                }
                if (pos2 < db.length && db[pos2].getText().length() == 0) {
                    redbool = true;
                    db[pos2].setText("x");
                }
            }
            ++i;
        }
        return redbool;
    }
}

