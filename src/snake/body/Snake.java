package snake.body;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private final List<Node> snake;
    private int directionX;
    private int directionY;
    private boolean gameOver;

    public Snake() {
        this.snake = new ArrayList<>();
        this.directionX = 1;
        this.directionY = 0;
        this.gameOver = false;
    }

    public Node getSnakeNode(int i) {
        return snake.get(i);
    }

    public void addSnakeTail() {
        int i = snake.size() - 1;
        snake.add(new Node(snake.get(i).getX(), snake.get(i).getY()));
    }

    public void moveSnake() {
        int i = snake.size() - 1;
        while (i > 0) {
            snake.get(i).setX(snake.get(i - 1).getX());
            snake.get(i).setY(snake.get(i - 1).getY());
            i--;
        }
        snake.get(i).setX(snake.get(i).getX() + directionX);
        snake.get(i).setY(snake.get(i).getY() + directionY);
    }

    public void addSnake(int x, int y) {
        snake.add(new Node(x, y));
    }

    public int length() {
        return snake.size();
    }

    public void setDirectionX(int x) {
        this.directionX = x;
    }

    public void setDirectionY(int y) {
        this.directionY = y;
    }

    public int getDirectionX() {
        return this.directionX;
    }

    public int getDirectionY() {
        return this.directionY;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void removeAll() {
        snake.removeAll(snake);
    }

    public static void main(String[] args) {
        List<Node> ss = new ArrayList<>();
        ss.add(new Node(5, 8));
        ss.add(new Node(1, 1));
        ss.add(new Node(2, 2));
        ss.add(new Node(4, 4));
        ss.add(new Node(ss.get(ss.size() - 1).getX(), ss.get(ss.size() - 1).getY()));
        for (Node a : ss) {
            System.out.println(a);
        }

        int i = ss.size() - 1;
        while (i > 0) {
            ss.get(i).setX(ss.get(i - 1).getX());
            ss.get(i).setY(ss.get(i - 1).getY());
            i--;
        }
        ss.get(i).setX(ss.get(i).getX() + 1);
        ss.get(i).setY(ss.get(i).getY() + 0);

        for (Node a : ss) {
            System.out.println(a);
        }
    }
}
