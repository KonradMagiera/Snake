package snake.controller;

import java.util.Random;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;
import snake.body.Node;
import snake.body.Snake;

public class SnakeThread extends Service<Void> {

    private Snake snake;
    private GuiController gui;
    private Node food;
    private Color color;
    private int score;

    public SnakeThread(GuiController gui) {
        snake = new Snake();
        snake.addSnake(46, 30);
        snake.addSnake(45, 30);
        snake.addSnake(44, 30);
        this.gui = gui;
        gui.setSnake(snake);
        score = 0;
        gui.setScore(score);
        food = new Node(0, 0);
        gui.setFood(food);
        drawSnake();
        gui.drawNewFood();
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    while (!snake.isGameOver()) {
                        snake.moveSnake();
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                gui.clearGame();
                                color = Color.GOLD;
                                gui.draw(food.getX(), food.getY(), color);
                                drawSnake();

                                if (snake.getSnakeNode(0).equals(food)) {
                                    snake.addSnakeTail();
                                    gui.drawNewFood();
                                    score++;
                                }
                                gui.setScore(score);
                                checkGameOverStatus();

                            }
                        });

                        Thread.sleep(100);
                    }

                } catch (Exception e) {
                    //if error TODO
                }
                
                return null;
            }
        };
    }

//    private void drawNewFood() {
//        Random rand = new Random();
//        int i = rand.nextInt(90);
//        int j = rand.nextInt(60);
//        food.setX(i);
//        food.setY(j);
//        //food = new Node(i, j);
//        color = Color.GOLD;
//        gui.draw(food.getX(), food.getY(), color);
//    }

    private void drawSnake() {
        color = Color.GREEN;
        for (int i = 0; i < snake.length(); i++) {
            gui.draw(snake.getSnakeNode(i).getX(), snake.getSnakeNode(i).getY(), color);
        }
    }
    
    private void checkGameOverStatus(){
        if(snake.getSnakeNode(0).getX() >= 90 || snake.getSnakeNode(0).getX() < 0 || snake.getSnakeNode(0).getY() < 0 || snake.getSnakeNode(0).getY() >= 60){
            gui.setGameOver(true);
            score = 0;
        }
        for(int i = 1; i < snake.length(); i++){
            if(snake.getSnakeNode(0).equals(snake.getSnakeNode(i))){
                gui.setGameOver(true);
            }
        }
    }
}
