package snake;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import snake.controller.GuiController;
import snake.controller.SnakeThread;

public class SnakeMain extends Application {
    private SnakeThread snakeThread;
    
    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/snake/controller/SnakeGame.fxml"));
        Pane pane = loader.load();
        Scene scene = new Scene(pane, 890, 590);
        GuiController guiController = loader.getController();
        
        scene.setOnKeyPressed(e -> guiController.keyPressedAction(e));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Snake");
        primaryStage.show();
        snakeThread = new SnakeThread(guiController);
        snakeThread.restart();
        guiController.setSnakeThread(snakeThread);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
