
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BoardFrame
extends JFrame {
    public JPanel p;
    public JButton exe;
    public JTextField[][] textArray;
    public Point screenMiddle;
    public Point frameMiddle;
    public ArrayList<Integer> known;
    public Boolean useStartBoard = false;
    public Integer[][] startBoard = new Integer[][]{{0, 0, 37, 0, 39, 0, 0, 0, 0}, {31, 32, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 61, 0, 0, 0}, {0, 0, 23, 0, 0, 0, 0, 0, 0}, {0, 18, 0, 22, 0, 0, 0, 47, 45}, {0, 0, 14, 0, 0, 0, 0, 0, 0}, {0, 13, 0, 0, 0, 51, 0, 0, 0}, {0, 1, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 55, 0, 0, 0, 0, 0, 0}};

    public BoardFrame() {
        int j;
        this.p = new JPanel(null);
        this.known = new ArrayList();
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.add(this.p);
        this.screenMiddle = new Point(this.getLocationOnScreen());
        this.setBounds((int)this.screenMiddle.getX() * 2 / 3, (int)this.screenMiddle.getY() * 2 / 3, (int)this.screenMiddle.getX() * 2 / 3, (int)this.screenMiddle.getX() * 2 / 3);
        int blockSize = (int)this.screenMiddle.getX() * 2 / 30;
        this.frameMiddle = new Point((int)this.screenMiddle.getX() / 3, (int)this.screenMiddle.getX() / 3);
        this.textArray = new JTextField[9][9];
        this.exe = new JButton("Start!");
        this.p.add(this.exe);
        this.exe.setBounds(this.frameMiddle.x * 2 - blockSize * 2, this.frameMiddle.y * 2 - blockSize * 2, blockSize * 2, blockSize * 2);
        this.setSize(this.getWidth() + 6, this.getHeight() + 32);
        int i = 0;
        while (i < 5) {
            j = 0;
            while (j < 5 + i) {
                this.textArray[i][j] = new JTextField();
                if (this.useStartBoard.booleanValue()) {
                    this.textArray[i][j].setText(this.startBoard[i][j].toString());
                }
                this.p.add(this.textArray[i][j]);
                if (i % 2 == 0) {
                    this.textArray[i][j].setBounds((int)((double)this.frameMiddle.x - (2.5 + 0.5 * (double)i - (double)j) * (double)blockSize), (int)((double)this.frameMiddle.y + (-4.5 + (double)i) * (double)blockSize), blockSize, blockSize);
                } else {
                    this.textArray[i][j].setBounds((int)((double)this.frameMiddle.x - (2.5 + 0.5 * (double)i - (double)j) * (double)blockSize), (int)((double)this.frameMiddle.y + (-4.5 + (double)i) * (double)blockSize), blockSize, blockSize);
                }
                ++j;
            }
            ++i;
        }
        i = 5;
        while (i <= 8) {
            j = 0;
            while (j < 13 - i) {
                this.textArray[i][j] = new JTextField();
                if (this.useStartBoard.booleanValue()) {
                    this.textArray[i][j].setText(this.startBoard[i][j].toString());
                }
                this.p.add(this.textArray[i][j]);
                if (i % 2 == 0) {
                    this.textArray[i][j].setBounds((int)((double)this.frameMiddle.x - (6.5 - 0.5 * (double)i - (double)j) * (double)blockSize), (int)((double)this.frameMiddle.y + (-4.5 + (double)i) * (double)blockSize), blockSize, blockSize);
                }
                if (i % 2 == 1) {
                    this.textArray[i][j].setBounds((int)((double)this.frameMiddle.x - (6.5 - 0.5 * (double)i - (double)j) * (double)blockSize), (int)((double)this.frameMiddle.y + (-4.5 + (double)i) * (double)blockSize), blockSize, blockSize);
                }
                ++j;
            }
            ++i;
        }
        this.exe.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                int j;
                Point pos = new Point();
                pos = BoardFrame.this.findStartingP();
                Block[][] c = new Block[9][9];
                int i = 0;
                while (i < 5) {
                    j = 0;
                    while (j < 5 + i) {
                        c[i][j] = new Block(i, j);
                        if (i != 0 && j != 0) {
                            c[i][j].upperLeftNeighbor = c[i - 1][j - 1];
                            c[i - 1][j - 1].lowerRightNeighbor = c[i][j];
                        }
                        if (i != 0 && j != i + 4) {
                            c[i][j].upperRightNeighbor = c[i - 1][j];
                            c[i - 1][j].lowerLeftNeighbor = c[i][j];
                        }
                        if (j != 0) {
                            c[i][j].leftNeighbor = c[i][j - 1];
                            c[i][j - 1].rightNeighbor = c[i][j];
                        }
                        ++j;
                    }
                    ++i;
                }
                i = 5;
                while (i <= 8) {
                    j = 0;
                    while (j < 13 - i) {
                        c[i][j] = new Block(i, j);
                        c[i][j].upperLeftNeighbor = c[i - 1][j];
                        c[i - 1][j].lowerRightNeighbor = c[i][j];
                        if (j != 13 - i) {
                            c[i][j].upperRightNeighbor = c[i - 1][j + 1];
                            c[i - 1][j + 1].lowerLeftNeighbor = c[i][j];
                        }
                        if (j != 0) {
                            c[i][j].leftNeighbor = c[i][j - 1];
                            c[i][j - 1].rightNeighbor = c[i][j];
                        }
                        ++j;
                    }
                    ++i;
                }
                i = 0;
                while (i < 9) {
                    j = 0;
                    while (j < 9) {
                        if (BoardFrame.this.textArray[i][j] != null) {
                            c[i][j].setValue(stringToInt.convert(BoardFrame.this.textArray[i][j].getText()));
                            if (c[i][j].value != 0) {
                                BoardFrame.this.known.add(c[i][j].value);
                            }
                        }
                        ++j;
                    }
                    ++i;
                }
                c = this.recursion(pos.x, pos.y, c);
                i = 0;
                while (i < 9) {
                    j = 0;
                    while (j < 9) {
                        if (BoardFrame.this.textArray[i][j] != null) {
                            BoardFrame.this.textArray[i][j].setText("" + c[i][j].getValue());
                        }
                        ++j;
                    }
                    ++i;
                }
            }

            public Block[][] recursion(int i, int j, Block[][] blockArray) {
                int n = blockArray[i][j].getValue();
                if (n == 61) {
                    return blockArray;
                }
                if (BoardFrame.this.known.indexOf(n + 1) != -1) {
                    if (blockArray[i][j].rightNeighbor != null && blockArray[i][j].rightNeighbor.getValue() == n + 1) {
                        Block[][] c = BoardFrame.this.cloneBlocks(blockArray);
                        c = this.recursion(blockArray[i][j].rightNeighbor.x, blockArray[i][j].rightNeighbor.y, blockArray);
                        if (c != null) {
                            return c;
                        }
                    } else if (blockArray[i][j].lowerRightNeighbor != null && blockArray[i][j].lowerRightNeighbor.getValue() == n + 1) {
                        Block[][] c = BoardFrame.this.cloneBlocks(blockArray);
                        c = this.recursion(blockArray[i][j].lowerRightNeighbor.x, blockArray[i][j].lowerRightNeighbor.y, blockArray);
                        if (c != null) {
                            return c;
                        }
                    } else if (blockArray[i][j].lowerLeftNeighbor != null && blockArray[i][j].lowerLeftNeighbor.getValue() == n + 1) {
                        Block[][] c = BoardFrame.this.cloneBlocks(blockArray);
                        c = this.recursion(blockArray[i][j].lowerLeftNeighbor.x, blockArray[i][j].lowerLeftNeighbor.y, blockArray);
                        if (c != null) {
                            return c;
                        }
                    } else if (blockArray[i][j].leftNeighbor != null && blockArray[i][j].leftNeighbor.getValue() == n + 1) {
                        Block[][] c = BoardFrame.this.cloneBlocks(blockArray);
                        c = this.recursion(blockArray[i][j].leftNeighbor.x, blockArray[i][j].leftNeighbor.y, blockArray);
                        if (c != null) {
                            return c;
                        }
                    } else if (blockArray[i][j].upperLeftNeighbor != null && blockArray[i][j].upperLeftNeighbor.getValue() == n + 1) {
                        Block[][] c = BoardFrame.this.cloneBlocks(blockArray);
                        c = this.recursion(blockArray[i][j].upperLeftNeighbor.x, blockArray[i][j].upperLeftNeighbor.y, blockArray);
                        if (c != null) {
                            return c;
                        }
                    } else if (blockArray[i][j].upperRightNeighbor != null && blockArray[i][j].upperRightNeighbor.getValue() == n + 1) {
                        Block[][] c = BoardFrame.this.cloneBlocks(blockArray);
                        c = this.recursion(blockArray[i][j].upperRightNeighbor.x, blockArray[i][j].upperRightNeighbor.y, blockArray);
                        if (c != null) {
                            return c;
                        }
                    }
                    return null;
                }
                if (BoardFrame.this.known.indexOf(n + 1) == -1) {
                    Block[][] c;
                    if (blockArray[i][j].rightNeighbor != null && blockArray[i][j].rightNeighbor.getValue() == 0) {
                        c = BoardFrame.this.cloneBlocks(blockArray);
                        System.out.println(c[i][j].getValue());
                        c[c[i][j].rightNeighbor.x][c[i][j].rightNeighbor.y].setValue(n + 1);
                        c = this.recursion(blockArray[i][j].rightNeighbor.x, blockArray[i][j].rightNeighbor.y, c);
                        if (c != null) {
                            return c;
                        }
                    }
                    if (blockArray[i][j].lowerRightNeighbor != null && blockArray[i][j].lowerRightNeighbor.getValue() == 0) {
                        c = BoardFrame.this.cloneBlocks(blockArray);
                        System.out.println(c[i][j].getValue());
                        c[blockArray[i][j].lowerRightNeighbor.x][blockArray[i][j].lowerRightNeighbor.y].setValue(n + 1);
                        c = this.recursion(blockArray[i][j].lowerRightNeighbor.x, blockArray[i][j].lowerRightNeighbor.y, c);
                        if (c != null) {
                            return c;
                        }
                    }
                    if (blockArray[i][j].lowerLeftNeighbor != null && blockArray[i][j].lowerLeftNeighbor.getValue() == 0) {
                        c = BoardFrame.this.cloneBlocks(blockArray);
                        System.out.println(c[i][j].getValue());
                        c[blockArray[i][j].lowerLeftNeighbor.x][blockArray[i][j].lowerLeftNeighbor.y].setValue(n + 1);
                        c = this.recursion(blockArray[i][j].lowerLeftNeighbor.x, blockArray[i][j].lowerLeftNeighbor.y, c);
                        if (c != null) {
                            return c;
                        }
                    }
                    if (blockArray[i][j].leftNeighbor != null && blockArray[i][j].leftNeighbor.getValue() == 0) {
                        c = BoardFrame.this.cloneBlocks(blockArray);
                        System.out.println(c[i][j].getValue());
                        c[blockArray[i][j].leftNeighbor.x][blockArray[i][j].leftNeighbor.y].setValue(n + 1);
                        c = this.recursion(blockArray[i][j].leftNeighbor.x, blockArray[i][j].leftNeighbor.y, c);
                        if (c != null) {
                            return c;
                        }
                    }
                    if (blockArray[i][j].upperLeftNeighbor != null && blockArray[i][j].upperLeftNeighbor.getValue() == 0) {
                        c = BoardFrame.this.cloneBlocks(blockArray);
                        System.out.println(c[i][j].getValue());
                        c[blockArray[i][j].upperLeftNeighbor.x][blockArray[i][j].upperLeftNeighbor.y].setValue(n + 1);
                        c = this.recursion(blockArray[i][j].upperLeftNeighbor.x, blockArray[i][j].upperLeftNeighbor.y, c);
                        if (c != null) {
                            return c;
                        }
                    }
                    if (blockArray[i][j].upperRightNeighbor != null && blockArray[i][j].upperRightNeighbor.getValue() == 0) {
                        c = BoardFrame.this.cloneBlocks(blockArray);
                        System.out.println(c[i][j].getValue());
                        c[c[i][j].upperRightNeighbor.x][c[i][j].upperRightNeighbor.y].setValue(n + 1);
                        c = this.recursion(blockArray[i][j].upperRightNeighbor.x, blockArray[i][j].upperRightNeighbor.y, c);
                        if (c != null) {
                            return c;
                        }
                    }
                }
                return null;
            }
        });
    }

    public Block[][] cloneBlocks(Block[][] original) {
        int j;
        Block[][] c = new Block[9][9];
        int i = 0;
        while (i < 9) {
            j = 0;
            while (j < 9) {
                if (original[i][j] != null) {
                    c[i][j] = original[i][j].clone();
                    c[i][j].setValue(original[i][j].getValue());
                }
                ++j;
            }
            ++i;
        }
        i = 0;
        while (i < 5) {
            j = 0;
            while (j < 5 + i) {
                if (i != 0 && j != 0) {
                    c[i][j].upperLeftNeighbor = c[i - 1][j - 1];
                    c[i - 1][j - 1].lowerRightNeighbor = c[i][j];
                }
                if (i != 0 && j != i + 4) {
                    c[i][j].upperRightNeighbor = c[i - 1][j];
                    c[i - 1][j].lowerLeftNeighbor = c[i][j];
                }
                if (j != 0) {
                    c[i][j].leftNeighbor = c[i][j - 1];
                    c[i][j - 1].rightNeighbor = c[i][j];
                }
                ++j;
            }
            ++i;
        }
        i = 5;
        while (i <= 8) {
            j = 0;
            while (j < 13 - i) {
                c[i][j].upperLeftNeighbor = c[i - 1][j];
                c[i - 1][j].lowerRightNeighbor = c[i][j];
                if (j != 13 - i) {
                    c[i][j].upperRightNeighbor = c[i - 1][j + 1];
                    c[i - 1][j + 1].lowerLeftNeighbor = c[i][j];
                }
                if (j != 0) {
                    c[i][j].leftNeighbor = c[i][j - 1];
                    c[i][j - 1].rightNeighbor = c[i][j];
                }
                ++j;
            }
            ++i;
        }
        return c;
    }

    public Point findStartingP() {
        int j;
        int i = 0;
        while (i < 5) {
            j = 0;
            while (j < 5 + i) {
                if (this.textArray[i][j].getText().equals("1")) {
                    return new Point(i, j);
                }
                ++j;
            }
            ++i;
        }
        i = 5;
        while (i <= 8) {
            j = 0;
            while (j < 13 - i) {
                if (this.textArray[i][j].getText().equals("1")) {
                    return new Point(i, j);
                }
                ++j;
            }
            ++i;
        }
        return null;
    }
}

