
import java.util.ArrayList;

public class Square {
    private ArrayList<Integer> options;
    public int value;
    public int i;
    public int j;

    public Square(int i, int j) {
        this.i = i;
        this.j = j;
        this.options = new ArrayList();
        int k = 0;
        while (k < 9) {
            this.options.add(k + 1);
            ++k;
        }
    }

    public int getValue() {
        return this.value;
    }

    public boolean isPossible(int p) {
        int flag = this.options.indexOf(p);
        return flag != -1;
    }

    public void setValue(int v) {
        this.value = v;
        this.options.clear();
        this.options.add(v);
    }

    public int removeOption(int op) {
        int index = this.options.indexOf(op);
        try {
            if (index != -1) {
                this.options.remove(index);
                System.out.println("[" + this.i + "," + this.j + "] RemoveOption: " + op);
                if (this.options.size() == 1) {
                    System.out.println("[" + this.i + "," + this.j + "] --------------------------------------------- SET VALUE -------- : " + this.options.get(0));
                }
                return 1;
            }
            return 0;
        }
        finally {
            if (this.options.size() == 1) {
                this.value = this.options.get(0);
            }
        }
    }
}

