package snake.body;

public class Node {

    private int x, y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("x: ").append(x).append(" y: ").append(y);
        return s.toString();
    }
    
    @Override
    public boolean equals(Object o){
        return (o instanceof Node) && ((Node)o).getX() == this.x && ((Node)o).getY() == this.y;
    }
}
