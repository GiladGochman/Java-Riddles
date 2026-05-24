
public class Block {
    public int value;
    public Block upperRightNeighbor;
    public Block upperLeftNeighbor;
    public Block lowerLeftNeighbor;
    public Block lowerRightNeighbor;
    public Block leftNeighbor;
    public Block rightNeighbor;
    public int x;
    public int y;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public Block clone() {
        Block c = new Block(this.x, this.y);
        c.setValue(this.getValue());
        return c;
    }
}

