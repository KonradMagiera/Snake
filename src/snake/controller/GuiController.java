package snake.controller;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import snake.body.Node;
import snake.body.Snake;

public class GuiController implements Initializable {

    @FXML
    private Pane gameComponent;
    @FXML
    private Label scoreText;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label lostLabel;
    @FXML
    private Label infoLabel;

    private final int SIZE = 10;
    private Snake snake;
    private Node food;
    private SnakeThread snakeThread;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lostLabel.setVisible(false);
        infoLabel.setVisible(false);
    }

    @FXML
    public void keyPressedAction(KeyEvent event) {
        if (!snake.isGameOver()) { // !gameOver
            if (event.getCode() == KeyCode.UP && snake.getDirectionY() != 1) {
                snake.setDirectionX(0);
                snake.setDirectionY(-1);

            } else if (event.getCode() == KeyCode.DOWN && snake.getDirectionY() != -1) {
                snake.setDirectionX(0);
                snake.setDirectionY(1);


            } else if (event.getCode() == KeyCode.LEFT && snake.getDirectionX() != 1) {
                snake.setDirectionX(-1);
                snake.setDirectionY(0);

            } else if (event.getCode() == KeyCode.RIGHT && snake.getDirectionX() != -1) {
                snake.setDirectionX(1);
                snake.setDirectionY(0);
            }
        } else {
            setGameOver(false);
            restartSnake();
            snakeThread.restart();
            //scoreLabel.setText("0");
        }
    }

    public void draw(int x, int y, Color color) {
        Rectangle r = new Rectangle(SIZE, SIZE, color);
        r.relocate(x * SIZE, y * SIZE);
        r.setStroke(Color.web("242424"));
        gameComponent.getChildren().add(r);
        gameComponent.getChildren().removeAll(lostLabel, infoLabel, scoreText, scoreLabel);
        gameComponent.getChildren().addAll(lostLabel, infoLabel, scoreText, scoreLabel);
    }

    public void clearGame() {
        gameComponent.getChildren().clear();
        gameComponent.getChildren().addAll(scoreText, scoreLabel, lostLabel, infoLabel);
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public void setFood(Node food) {
        this.food = food;
    }

    public void setGameOver(boolean gameOver) {
        snake.setGameOver(gameOver);
        infoLabel.setVisible(gameOver);
        lostLabel.setVisible(gameOver);

    }

    public void restartSnake() {
        snake.removeAll();
        snake.addSnake(46, 30);
        snake.addSnake(45, 30);
        snake.addSnake(44, 30);
        snake.setDirectionX(1);
        snake.setDirectionY(0);
        clearGame();
        infoLabel.setVisible(false);
        lostLabel.setVisible(false);
        for (int i = 0; i < snake.length(); i++) {
            draw(snake.getSnakeNode(i).getX(), snake.getSnakeNode(i).getY(), Color.GREEN);
        }
        drawNewFood();
        snake.setGameOver(false);
        scoreLabel.setText("0");
    }

    public void drawNewFood() {
        Random rand = new Random();
        int i = rand.nextInt(90);
        int j = rand.nextInt(60);
        food.setX(i);
        food.setY(j);
        draw(food.getX(), food.getY(), Color.GOLD);
    }

    public void setSnakeThread(SnakeThread s){
        this.snakeThread = s;
    }
    
    public void setScore(int score){
        this.scoreLabel.setText(Integer.toString(score));
    }
}
