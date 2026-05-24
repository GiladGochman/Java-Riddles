
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

public class Frame
extends JFrame {
    public static JTextField[][] t;
    public static JPanel p;
    boolean USE_TEST_DATA = false;
    private int[][] testData_evil;
    private int[][] testData;

    private void updateSq(Square[][] sq) {
        int i = 0;
        while (i < sq.length) {
            int j = 0;
            while (j < sq.length) {
                if (sq[i][j].getValue() != 0) {
                    t[i][j].setText(String.valueOf(sq[i][j].getValue()));
                }
                ++j;
            }
            ++i;
        }
    }

    public Frame() {
        int j;
        int[][] nArrayArray = new int[9][];
        int[] nArray = new int[9];
        nArray[4] = 6;
        nArray[8] = 9;
        nArrayArray[0] = nArray;
        int[] nArray2 = new int[9];
        nArray2[0] = 5;
        nArray2[2] = 1;
        nArray2[3] = 7;
        nArray2[6] = 4;
        nArrayArray[1] = nArray2;
        int[] nArray3 = new int[9];
        nArray3[8] = 3;
        nArrayArray[2] = nArray3;
        int[] nArray4 = new int[9];
        nArray4[3] = 2;
        nArray4[4] = 9;
        nArray4[6] = 3;
        nArray4[7] = 7;
        nArrayArray[3] = nArray4;
        int[] nArray5 = new int[9];
        nArray5[1] = 2;
        nArray5[4] = 7;
        nArray5[7] = 4;
        nArrayArray[4] = nArray5;
        int[] nArray6 = new int[9];
        nArray6[1] = 9;
        nArray6[2] = 3;
        nArray6[3] = 6;
        nArray6[5] = 1;
        nArrayArray[5] = nArray6;
        int[] nArray7 = new int[9];
        nArray7[0] = 9;
        nArrayArray[6] = nArray7;
        int[] nArray8 = new int[9];
        nArray8[2] = 6;
        nArray8[5] = 4;
        nArray8[6] = 5;
        nArray8[8] = 2;
        nArrayArray[7] = nArray8;
        int[] nArray9 = new int[9];
        nArray9[0] = 8;
        nArray9[4] = 5;
        nArrayArray[8] = nArray9;
        this.testData_evil = nArrayArray;
        int[][] nArrayArray2 = new int[9][];
        int[] nArray10 = new int[9];
        nArray10[0] = 6;
        nArray10[1] = 4;
        nArray10[4] = 1;
        nArray10[5] = 9;
        nArray10[6] = 8;
        nArray10[8] = 7;
        nArrayArray2[0] = nArray10;
        int[] nArray11 = new int[9];
        nArray11[4] = 8;
        nArray11[6] = 4;
        nArray11[8] = 6;
        nArrayArray2[1] = nArray11;
        int[] nArray12 = new int[9];
        nArray12[7] = 3;
        nArrayArray2[2] = nArray12;
        int[] nArray13 = new int[9];
        nArray13[2] = 6;
        nArray13[3] = 5;
        nArray13[6] = 3;
        nArray13[7] = 8;
        nArrayArray2[3] = nArray13;
        int[] nArray14 = new int[9];
        nArray14[2] = 2;
        nArray14[4] = 4;
        nArray14[6] = 9;
        nArrayArray2[4] = nArray14;
        int[] nArray15 = new int[9];
        nArray15[1] = 8;
        nArray15[2] = 7;
        nArray15[5] = 3;
        nArray15[6] = 5;
        nArrayArray2[5] = nArray15;
        int[] nArray16 = new int[9];
        nArray16[1] = 7;
        nArrayArray2[6] = nArray16;
        int[] nArray17 = new int[9];
        nArray17[0] = 8;
        nArray17[2] = 4;
        nArray17[4] = 5;
        nArrayArray2[7] = nArray17;
        int[] nArray18 = new int[9];
        nArray18[0] = 5;
        nArray18[2] = 1;
        nArray18[3] = 8;
        nArray18[4] = 9;
        nArray18[7] = 4;
        nArray18[8] = 3;
        nArrayArray2[8] = nArray18;
        this.testData = nArrayArray2;
        this.setTitle("Sudoku");
        this.setDefaultCloseOperation(3);
        Point rv = new Point();
        this.setLocationRelativeTo(null);
        rv = this.getLocation();
        this.setSize(380, 500);
        this.setLocation((int)(rv.getX() - (double)(this.getWidth() / 2)), (int)(rv.getY() - (double)(this.getHeight() / 2)));
        this.setResizable(false);
        p = new JPanel(null);
        this.add(p);
        t = new JTextField[9][9];
        JButton exe = new JButton("Calculate!");
        exe.setFont(new Font("Arial", 1, 40));
        exe.setBounds(10, this.getHeight() - 130, 350, 80);
        p.add(exe);
        int i = 0;
        while (i < 32) {
            JLabel L1 = new JLabel("||");
            L1.setBounds(121, 11 * i + 5, 20, 20);
            p.add(L1);
            JLabel L2 = new JLabel("||");
            L2.setBounds(241, 11 * i + 5, 20, 20);
            p.add(L2);
            ++i;
        }
        i = 0;
        while (i < 58) {
            JLabel L3 = new JLabel("=");
            L3.setBounds(6 * i + 10, 114, 20, 20);
            p.add(L3);
            JLabel L4 = new JLabel("=");
            L4.setBounds(6 * i + 10, 234, 20, 20);
            p.add(L4);
            ++i;
        }
        Font font = new Font("Arial", 1, 30);
        KeyAdapter keyAdapter = new KeyAdapter(){

            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c <= '0' || c > '9') && c != '\b' && c != '\u007f') {
                    Frame.this.getToolkit().beep();
                    e.consume();
                }
            }
        };
        int i2 = 0;
        while (i2 < t.length) {
            j = 0;
            while (j < t[i2].length) {
                Frame.t[i2][j] = new JTextField();
                t[i2][j].setBounds(40 * i2 + 10, 40 * j + 10, 30, 30);
                t[i2][j].setHorizontalAlignment(0);
                t[i2][j].setFont(font);
                t[i2][j].addKeyListener(keyAdapter);
                p.add(t[i2][j]);
                ++j;
            }
            ++i2;
        }
        if (this.USE_TEST_DATA) {
            i2 = 0;
            while (i2 < this.testData.length) {
                j = 0;
                while (j < this.testData[i2].length) {
                    if (this.testData[i2][j] != 0) {
                        t[j][i2].setText(String.valueOf(this.testData[i2][j]));
                    }
                    ++j;
                }
                ++i2;
            }
        }
        this.setVisible(true);
        Frame _this = this;
        exe.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent arg0) {
                boolean legal = true;
                int i = 0;
                while (i < t.length) {
                    int j = 0;
                    while (j < t.length) {
                        if (t[i][j].getText().length() >= 2) {
                            t[i][j].setBackground(new Color(250, 0, 0));
                            legal = false;
                            Frame.this.getToolkit().beep();
                        } else {
                            t[i][j].setBackground(new Color(250, 250, 250));
                        }
                        ++j;
                    }
                    ++i;
                }
                if (legal) {
                    final Square[][] sq = new Square[t.length][t.length];
                    int i2 = 0;
                    while (i2 < sq.length) {
                        int j = 0;
                        while (j < sq.length) {
                            sq[i2][j] = new Square(i2, j);
                            if (!t[i2][j].getText().equals("")) {
                                sq[i2][j].setValue(stringToInt.convert(t[i2][j].getText()));
                            }
                            ++j;
                        }
                        ++i2;
                    }
                    SwingWorker<Object, Square[][]> task = new SwingWorker<Object, Square[][]>(){

                        @Override
                        protected Object doInBackground() throws Exception {
                            while (Frame.this.check(sq)) {
                                int countRemovals = 0;
                                int i = 0;
                                while (i < sq.length) {
                                    int j = 0;
                                    while (j < sq.length) {
                                        countRemovals += overlap.removeOpions(sq, i, j);
                                        ++j;
                                    }
                                    ++i;
                                }
                                this.publish((V[])new Square[][][]{sq});
                                int BigCol = 0;
                                while (BigCol < 3) {
                                    int BigRow = 0;
                                    while (BigRow < 3) {
                                        countRemovals += Advanced.algorithm(sq, BigCol, BigRow);
                                        ++BigRow;
                                    }
                                    ++BigCol;
                                }
                                System.out.flush();
                                if (countRemovals == 0) {
                                    System.out.println("Can't solve!");
                                    break;
                                }
                                System.out.println("-------------------------------------------");
                                System.out.println(" Removals : " + countRemovals);
                                System.out.println("-------------------------------------------");
                                boolean flag2 = false;
                                int i2 = 0;
                                while (i2 < sq.length) {
                                    int j = 0;
                                    while (j < sq.length) {
                                        if (sq[i2][j].getValue() == 0) {
                                            flag2 = true;
                                        }
                                        ++j;
                                    }
                                    ++i2;
                                }
                                if (!flag2) break;
                                this.publish((V[])new Square[][][]{sq});
                            }
                            Frame.this.updateSq(sq);
                            return null;
                        }

                        @Override
                        protected void process(List<Square[][]> sqs) {
                            for (Square[][] sq2 : sqs) {
                                Frame.this.updateSq(sq2);
                            }
                            super.process(sqs);
                        }
                    };
                    task.execute();
                }
            }
        });
    }

    public boolean check(Square[][] sq) {
        int j;
        boolean b = true;
        int i = 0;
        while (i < t.length) {
            j = 0;
            while (j < t.length) {
                if (t[i][j].getText().length() >= 2) {
                    t[i][j].setBackground(new Color(250, 0, 0));
                    b = false;
                    this.getToolkit().beep();
                } else {
                    t[i][j].setBackground(new Color(250, 250, 250));
                }
                ++j;
            }
            ++i;
        }
        if (b) {
            i = 0;
            while (i < sq.length) {
                j = 0;
                while (j < sq.length) {
                    int k;
                    if (overlap.checkX(sq, i, j)) {
                        k = 0;
                        while (k < sq.length) {
                            b = false;
                            t[k][j].setBackground(new Color(255, 165, 0));
                            ++k;
                        }
                    }
                    if (overlap.checkY(sq, i, j)) {
                        k = 0;
                        while (k < sq.length) {
                            b = false;
                            t[i][k].setBackground(new Color(255, 165, 0));
                            ++k;
                        }
                    }
                    if (overlap.checkSquare(sq, i, j)) {
                        int ry = i / 3;
                        int rx = j / 3;
                        int k2 = ry * 3;
                        while (k2 < ry * 3 + 3) {
                            int index = rx * 3;
                            while (index < rx * 3 + 3) {
                                b = false;
                                t[k2][index].setBackground(new Color(255, 165, 0));
                                ++index;
                            }
                            ++k2;
                        }
                    }
                    ++j;
                }
                ++i;
            }
        }
        return b;
    }
}

