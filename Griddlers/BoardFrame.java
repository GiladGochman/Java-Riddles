
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

public class BoardFrame
extends JFrame {
    public JPanel p = new JPanel(null);
    public JTextField[][] db;
    public JTextField[] v;
    public JTextField[] h;
    public int width;
    public int height;
    public Point rv;
    public ArrayList<Integer>[] vert;
    public ArrayList<Integer>[] horz;
    public KeyAdapter keyAdapter;
    public KeyAdapter refresh;
    public JButton exe;
    public ArrayList<Integer>[] tryVert;
    public ArrayList<Integer>[] tryHorz;

    public BoardFrame(int width, int height) {
        boolean bl = false;
        this.tryVert = new ArrayList[20];
        int i = 0;
        while (i < this.tryVert.length) {
            this.tryVert[i] = new ArrayList();
            ++i;
        }
        this.tryVert[0].add(1);
        this.tryVert[0].add(1);
        this.tryVert[1].add(3);
        this.tryVert[1].add(2);
        this.tryVert[2].add(4);
        this.tryVert[2].add(5);
        this.tryVert[2].add(3);
        this.tryVert[3].add(17);
        this.tryVert[4].add(19);
        this.tryVert[5].add(3);
        this.tryVert[5].add(7);
        this.tryVert[5].add(2);
        this.tryVert[6].add(2);
        this.tryVert[6].add(4);
        this.tryVert[6].add(3);
        this.tryVert[6].add(4);
        this.tryVert[7].add(4);
        this.tryVert[7].add(3);
        this.tryVert[7].add(1);
        this.tryVert[7].add(3);
        this.tryVert[7].add(3);
        this.tryVert[8].add(2);
        this.tryVert[8].add(4);
        this.tryVert[8].add(3);
        this.tryVert[8].add(4);
        this.tryVert[9].add(3);
        this.tryVert[9].add(7);
        this.tryVert[9].add(2);
        this.tryVert[10].add(10);
        this.tryVert[10].add(9);
        this.tryVert[11].add(9);
        this.tryVert[11].add(8);
        this.tryVert[12].add(7);
        this.tryVert[12].add(7);
        this.tryVert[13].add(1);
        this.tryVert[13].add(17);
        this.tryVert[14].add(1);
        this.tryVert[14].add(12);
        this.tryVert[15].add(2);
        this.tryVert[15].add(3);
        this.tryVert[16].add(2);
        this.tryVert[16].add(5);
        this.tryVert[17].add(1);
        this.tryVert[17].add(7);
        this.tryVert[18].add(1);
        this.tryVert[18].add(8);
        this.tryVert[19].add(11);
        this.tryHorz = new ArrayList[20];
        i = 0;
        while (i < this.tryHorz.length) {
            this.tryHorz[i] = new ArrayList();
            ++i;
        }
        this.tryHorz[0].add(4);
        this.tryHorz[1].add(5);
        this.tryHorz[2].add(4);
        this.tryHorz[3].add(5);
        this.tryHorz[4].add(5);
        this.tryHorz[5].add(5);
        this.tryHorz[6].add(6);
        this.tryHorz[7].add(4);
        this.tryHorz[8].add(7);
        this.tryHorz[9].add(7);
        this.tryHorz[10].add(7);
        this.tryHorz[11].add(4);
        this.tryHorz[12].add(3);
        this.tryHorz[13].add(3);
        this.tryHorz[14].add(2);
        this.tryHorz[15].add(1);
        this.tryHorz[16].add(1);
        this.tryHorz[17].add(4);
        this.tryHorz[18].add(2);
        this.tryHorz[19].add(3);
        this.tryHorz[0].add(1);
        this.tryHorz[1].add(1);
        this.tryHorz[2].add(3);
        this.tryHorz[3].add(1);
        this.tryHorz[4].add(1);
        this.tryHorz[5].add(3);
        this.tryHorz[6].add(1);
        this.tryHorz[7].add(2);
        this.tryHorz[8].add(3);
        this.tryHorz[9].add(8);
        this.tryHorz[10].add(3);
        this.tryHorz[11].add(2);
        this.tryHorz[12].add(6);
        this.tryHorz[13].add(5);
        this.tryHorz[14].add(5);
        this.tryHorz[15].add(4);
        this.tryHorz[16].add(4);
        this.tryHorz[17].add(5);
        this.tryHorz[18].add(9);
        this.tryHorz[19].add(7);
        this.tryHorz[0].add(2);
        this.tryHorz[1].add(3);
        this.tryHorz[2].add(3);
        this.tryHorz[3].add(1);
        this.tryHorz[4].add(1);
        this.tryHorz[5].add(2);
        this.tryHorz[6].add(3);
        this.tryHorz[7].add(3);
        this.tryHorz[8].add(5);
        this.tryHorz[10].add(5);
        this.tryHorz[11].add(3);
        this.tryHorz[12].add(1);
        this.tryHorz[13].add(3);
        this.tryHorz[14].add(1);
        this.tryHorz[15].add(1);
        this.tryHorz[16].add(3);
        this.tryHorz[17].add(1);
        this.tryHorz[3].add(4);
        this.tryHorz[4].add(5);
        this.tryHorz[7].add(1);
        this.tryHorz[11].add(1);
        this.tryHorz[12].add(3);
        this.tryHorz[13].add(2);
        this.tryHorz[14].add(1);
        this.tryHorz[15].add(1);
        this.tryHorz[16].add(5);
        this.tryHorz[17].add(5);
        this.tryHorz[7].add(4);
        this.tryHorz[11].add(4);
        this.tryHorz[14].add(3);
        this.tryHorz[15].add(4);
        this.width = width;
        this.height = height;
        this.vert = new ArrayList[height];
        this.horz = new ArrayList[width];
        i = 0;
        while (i < this.vert.length) {
            this.vert[i] = new ArrayList();
            ++i;
        }
        i = 0;
        while (i < this.horz.length) {
            this.horz[i] = new ArrayList();
            ++i;
        }
        this.setDefaultCloseOperation(3);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        this.setExtendedState(6);
        this.add(this.p);
        this.rv = new Point(this.getWidth() - 20 - 15 * width, this.getHeight() - 40 - 15 * height);
        this.exe = new JButton("Solve!");
        this.p.add(this.exe);
        this.exe.setBounds(this.rv.x - 100, this.rv.y - 100, 100, 100);
        this.db = new JTextField[width][height];
        this.v = new JTextField[height];
        this.h = new JTextField[width];
        this.keyAdapter = new KeyAdapter(){

            public void keyTyped(KeyEvent e) {
                BoardFrame bff = Start.input.bf;
                char c = e.getKeyChar();
                if ((c < '0' || c > '9') && c != '\b' && c != '\u007f' && c != '\n') {
                    BoardFrame.this.getToolkit().beep();
                    e.consume();
                }
                if (c == '\n' && ((JTextField)e.getSource()).getText().length() > 0) {
                    bff.regInfo((JTextField)e.getSource());
                }
            }
        };
        this.buildDrawBoard();
        this.buildLines();
        this.buildInput();
        this.exe.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                SwingWorker<Object, JTextField[][]> task = new SwingWorker<Object, JTextField[][]>(){

                    @Override
                    protected Object doInBackground() throws Exception {
                        boolean isChanged;
                        do {
                            isChanged = false;
                        } while (isChanged = Solve.compile((this).BoardFrame.this.db, (this).BoardFrame.this.horz, (this).BoardFrame.this.vert) || isChanged);
                        return null;
                    }
                };
                task.execute();
            }
        });
    }

    public void regInfo(JTextField t) {
        if (t.getX() > this.rv.x - 15) {
            int info = (t.getX() - this.rv.x) / 15;
            this.horz[info].add(stringToInt.convert(t.getText()));
            JLabel inf = new JLabel(t.getText());
            this.p.add(inf);
            inf.setHorizontalAlignment(0);
            inf.setBounds(t.getBounds());
            t.setBounds(t.getX(), t.getY() - 15, t.getWidth(), t.getHeight());
            t.setText("");
        } else {
            int info = (t.getY() - this.rv.y) / 15;
            this.vert[info].add(stringToInt.convert(t.getText()));
            JLabel inf = new JLabel(t.getText());
            this.p.add(inf);
            inf.setHorizontalAlignment(0);
            inf.setBounds(t.getBounds());
            t.setBounds(t.getX() - 15, t.getY(), t.getWidth(), t.getHeight());
            t.setText("");
        }
    }

    public void buildInput() {
        int i = 0;
        while (i < this.height) {
            this.v[i] = new JTextField();
            this.p.add(this.v[i]);
            this.v[i].setBounds(this.rv.x - 15, this.rv.y + i * 15, 15, 15);
            this.v[i].addKeyListener(this.keyAdapter);
            this.v[i].setHorizontalAlignment(0);
            ++i;
        }
        i = 0;
        while (i < this.width) {
            this.h[i] = new JTextField();
            this.p.add(this.h[i]);
            this.h[i].setBounds(this.rv.x + i * 15, this.rv.y - 15, 15, 15);
            this.h[i].addKeyListener(this.keyAdapter);
            this.h[i].setHorizontalAlignment(0);
            ++i;
        }
    }

    public void buildDrawBoard() {
        int i = 0;
        while (i < this.width) {
            int j = 0;
            while (j < this.height) {
                this.db[i][j] = new JTextField();
                this.db[i][j].setBounds(this.rv.x + i * 15, this.rv.y + j * 15, 15, 15);
                this.p.add(this.db[i][j]);
                this.db[i][j].setEditable(false);
                this.db[i][j].setHorizontalAlignment(0);
                ++j;
            }
            ++i;
        }
    }

    public void buildLines() {
        JLabel L1;
        int j;
        int i = 0;
        while (i < this.width) {
            j = 0;
            while (j < this.height) {
                L1 = new JLabel("|");
                L1.setBounds(this.rv.x + i * 15 - 1, this.rv.y - 11 * j - 15, 3, 20);
                this.p.add(L1);
                ++j;
            }
            ++i;
        }
        i = 0;
        while (i < this.height) {
            j = 0;
            while ((double)j < (double)this.width * 1.5) {
                L1 = new JLabel("_");
                L1.setBounds(this.rv.x - 7 * j - 7, this.rv.y + i * 15 - 16, 20, 20);
                this.p.add(L1);
                ++j;
            }
            ++i;
        }
    }
}

