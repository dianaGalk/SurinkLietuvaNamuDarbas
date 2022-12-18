package com.example.surinklietuva.Controllers;

import com.example.surinklietuva.BigDataManager;
import com.example.surinklietuva.DataStructures.Magnet;
import com.example.surinklietuva.DataStructures.User;
import com.example.surinklietuva.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;

public class MagnetInfoView {

    public Text magnetInfo;
    public Button backButton;
    public ImageView regionImage;
    public Text magnetShops;
    private Magnet magnet;
    private User user;
    private List<User> listOfUsers;

    public void setData(List<User> listOfUsers, User user, Magnet magnet) throws FileNotFoundException {
        this.listOfUsers = listOfUsers;
        this.user = user;
        this.magnet = magnet;
        fillTables();
    }

    private void fillTables() throws FileNotFoundException {
        String imgFolderPath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\example\\surinklietuva\\Images";
        regionImage.setImage(null);
        Image img;
        switch (magnet.getArea()) {
            case "Vilniaus apskritis":
                img = new Image(new FileInputStream(imgFolderPath + "\\Vilnius.png"));
                break;
            case "Kauno apskritis":
                img = new Image(new FileInputStream(imgFolderPath + "\\Kaunas.png"));
                break;
            case "Klaipėdos apskritis":
                img = new Image(new FileInputStream(imgFolderPath + "\\Klaipeda.png"));
                break;
            case "Šiaulių apskritis":
                img = new Image(new FileInputStream(imgFolderPath + "\\Siauliai.png"));
                break;
            case "Telšių apskritis":
                img = new Image(new FileInputStream(imgFolderPath + "\\Telsiai.png"));
                break;
            case "Marijampolės apskritis":
                img = new Image(new FileInputStream(imgFolderPath + "\\Marijampole.png"));
                break;
            case "Tauragės apskritis":
                img = new Image(new FileInputStream(imgFolderPath + "\\Taurage.png"));
                break;
            case "Utenos apskritis":
                img = new Image(new FileInputStream(imgFolderPath + "\\Utena.png"));
                break;
            case "Panevėžio apskritis":
                img = new Image(new FileInputStream(imgFolderPath + "\\Panevezys.png"));
                break;
            case "Alytaus apskritis":
                img = new Image(new FileInputStream(imgFolderPath + "\\Alytus.png"));
                break;
            default:
                img=null;
        }
        regionImage.setImage(img);
        magnetInfo.setText(magnet.getName() + " " + magnet.getArea());
        StringBuilder shops = new StringBuilder();
        for (String s : magnet.getListOfShops()) {
            shops.append(s).append("\n");
        }
        magnetShops.setText(shops.toString());
    }

    public void returnToPrevious(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Parent root = fxmlLoader.load();
        MainView mainView = fxmlLoader.getController();
        mainView.setData(listOfUsers, user);

        Scene scene = new Scene(root);
        Stage stage = (Stage) regionImage.getScene().getWindow();
        stage.setTitle("Pagrindinis");
        stage.setScene(scene);
        stage.show();
    }
}
