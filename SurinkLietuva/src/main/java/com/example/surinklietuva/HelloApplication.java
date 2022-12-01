package com.example.surinklietuva;

import com.example.surinklietuva.Controllers.LoginView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        BigDataManager bigDataManager = new BigDataManager();

        //test
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Prisijungimas");
        stage.setScene(scene);
        LoginView loginView = fxmlLoader.getController();
        loginView.setData(bigDataManager.getAllUserListFromDataBase());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}