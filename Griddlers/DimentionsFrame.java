
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DimentionsFrame
extends JFrame {
    public JTextField t1 = new JTextField();
    public JTextField t2 = new JTextField();
    public JPanel p;
    public JButton b = new JButton("Build Board");
    public JLabel L2 = new JLabel("Width:");
    public JLabel L1 = new JLabel("Height:");
    public BoardFrame bf;

    public DimentionsFrame() {
        this.setTitle("Black And Solve!");
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setSize(250, 200);
        this.CenterFrame();
        this.setResizable(false);
        this.p = new JPanel(null);
        this.add(this.p);
        this.setVisible(true);
        this.L1.setBounds(10, 20, 70, 30);
        this.L1.setFont(new Font("serif", 1, 20));
        this.p.add(this.L1);
        this.L2.setBounds(this.L1.getX(), this.L1.getY() + 40, this.L1.getWidth(), this.L1.getHeight());
        this.L2.setFont(new Font("serif", 1, 20));
        this.p.add(this.L2);
        this.b.setFont(new Font("Serif", 1, 30));
        this.b.setBounds(this.L2.getX(), this.L2.getY() + 40, this.getWidth() - 50, 60);
        this.p.add(this.b);
        this.t1.setBounds(this.L1.getX() + 75, this.L1.getY(), 50, this.L1.getHeight());
        this.p.add(this.t1);
        this.t1.setHorizontalAlignment(0);
        this.t2.setBounds(this.t1.getX(), this.L2.getY(), 50, this.L1.getHeight());
        this.p.add(this.t2);
        this.t2.setHorizontalAlignment(0);
        this.b.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent arg0) {
                Start.input.bf = new BoardFrame(stringToInt.convert(DimentionsFrame.this.t2.getText()), stringToInt.convert(DimentionsFrame.this.t1.getText()));
                Start.input.dispose();
            }
        });
    }

    public void CenterFrame() {
        Point rv = new Point();
        rv = this.getLocation();
        this.setLocation((int)(rv.getX() - (double)(this.getWidth() / 2)), (int)(rv.getY() - (double)(this.getHeight() / 2)));
    }
}

