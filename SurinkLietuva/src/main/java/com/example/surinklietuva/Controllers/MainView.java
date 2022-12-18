package com.example.surinklietuva.Controllers;

import com.example.surinklietuva.BigDataManager;
import com.example.surinklietuva.DataStructures.Magnet;
import com.example.surinklietuva.DataStructures.User;
import com.example.surinklietuva.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class MainView {
    @FXML
    public Text nameSurnameField;
    @FXML
    public Text emailField;
    @FXML
    public Text MagnetsNumber;
    @FXML
    public Button logoutButton;
    @FXML
    public Button addButton;
    @FXML
    public Text magnetsNum;
    @FXML
    public Button removeButton;
    @FXML
    public ListView MagnetsListOfView;
    @FXML
    public ListView missingMagnetsListOfView;
    @FXML
    public ChoiceBox regionChoiceBox;
    @FXML
    public ListView regionsListOfView;
    @FXML
    public ListView regionMagnets;
    @FXML
    public Button showButton;

    private User user;
    private List<User> listOfUsers;
    private List<Magnet> missingMagnets;
    private final BigDataManager bigDataManager = new BigDataManager();
    private final List<String> allAreasList = Arrays.asList("Vilniaus apskritis", "Kauno apskritis", "Klaipėdos apskritis", "Šiaulių apskritis", "Telšių apskritis", "Marijampolės apskritis", "Tauragės apskritis", "Utenos apskritis", "Panevėžio apskritis", "Alytaus apskritis");

    public void setData(List<User> listOfUsers, User user) throws FileNotFoundException {
        this.listOfUsers = listOfUsers;
        this.user = user;
        getMissingMagnets();
        fillTables();
        regionChoiceBox.setOnAction(actionEvent -> {
            try {
                checkboxFunc();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void getMissingMagnets() throws FileNotFoundException {
        missingMagnets = bigDataManager.getAllMagnetsListFromDataBase();
        for (int i = missingMagnets.size() - 1; i >= 0; i--) {
            for (int j = user.getMagnetList().size() - 1; j >= 0; j--) {
                if (missingMagnets.get(i).getName().equals(user.getMagnetList().get(j).getName())) {
                    missingMagnets.remove(i);
                    break;
                }
            }
        }

    }

    public void addMagnet(ActionEvent actionEvent) throws IOException {
        String magnetName = missingMagnetsListOfView.getSelectionModel().getSelectedItem().toString();
        if (magnetName != null) {
            Magnet currentMagnet = null;
            for (Magnet m : missingMagnets) {
                if (m.getName().equals(magnetName)) {
                    currentMagnet = m;
                }
            }
            missingMagnets.remove(currentMagnet);
            user.getMagnetList().add(currentMagnet);
            fillTables();
            bigDataManager.updateUserToDataBase(listOfUsers, user);
            regionChoiceBox.setValue("Visi duomenis");
        }
    }

    public void remove(ActionEvent actionEvent) throws IOException {
        String magnetName = MagnetsListOfView.getSelectionModel().getSelectedItem().toString();
        if (magnetName != null) {
            Magnet currentMagnet = null;
            for (Magnet m : user.getMagnetList()) {
                if (m.getName().equals(magnetName)) {
                    currentMagnet = m;
                }
            }
            user.getMagnetList().remove(currentMagnet);
            missingMagnets.add(currentMagnet);
            fillTables();
            bigDataManager.updateUserToDataBase(listOfUsers, user);
            regionChoiceBox.setValue("Visi duomenis");
        }
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Parent root = fxmlLoader.load();
        LoginView loginView = fxmlLoader.getController();
        loginView.setData(listOfUsers);

        Scene scene = new Scene(root);
        Stage stage = (Stage) emailField.getScene().getWindow();
        stage.setTitle("Prisijungimas");
        stage.setScene(scene);
        stage.show();
    }

    public void fillTables() throws FileNotFoundException {

        nameSurnameField.setText(user.getName() + " " + user.getSurname());
        emailField.setText(user.getMail());
        MagnetsNumber.setText(user.getMagnetList().size() + " Magnetukų iš 60");


        missingMagnetsListOfView.getItems().clear();
        List<String> magnetsNames = new ArrayList<>();
        for (Magnet m : missingMagnets) {
            magnetsNames.add(m.getName());
        }
        Collections.sort(magnetsNames);
        for (String s : magnetsNames) {
            missingMagnetsListOfView.getItems().add(s);
        }

        MagnetsListOfView.getItems().clear();
        for (Magnet m : user.getMagnetList()) {
            MagnetsListOfView.getItems().add(m.getName());
        }


        magnetsNum.setText(user.getMagnetList().size() + " / 60");

        regionsListOfView.getItems().clear();
        for (String s : allAreasList) {
            regionsListOfView.getItems().add(s);
        }

    }

    public void showRegionMagnets(MouseEvent mouseEvent) {
        String regionName = regionsListOfView.getSelectionModel().getSelectedItem().toString();
        if (regionName != null) {
            regionMagnets.getItems().clear();
            List<Magnet> sortedMagnets = bigDataManager.getListOfMagnetsByRegion(missingMagnets, regionName);
            for (Magnet m : sortedMagnets) {
                regionMagnets.getItems().add(m.getName());
            }
        }
    }

    public void goToMagnetInfo(ActionEvent actionEvent) throws IOException {
        String magnetName = regionMagnets.getSelectionModel().getSelectedItem().toString();
        Magnet currentMagnet = bigDataManager.getMagnetByName(magnetName);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("magnet-info-view.fxml"));
        Parent root = fxmlLoader.load();
        MagnetInfoView magnetInfoView = fxmlLoader.getController();
        magnetInfoView.setData(listOfUsers, user, currentMagnet);

        Scene scene = new Scene(root);
        Stage stage = (Stage) removeButton.getScene().getWindow();
        stage.setTitle("Magnetuko informacija");
        stage.setScene(scene);
        stage.show();
    }

    public void checkboxFunc() throws FileNotFoundException {
        String regionName = regionChoiceBox.getValue().toString();
        if (regionName.equals("Visi duomenis")) {
            fillTables();
        } else if (regionName != null) {
            missingMagnetsListOfView.getItems().clear();
            List<Magnet> sortedMagnets = bigDataManager.getListOfMagnetsByRegion(missingMagnets, regionName);
            for (Magnet m : sortedMagnets) {
                missingMagnetsListOfView.getItems().add(m.getName());
            }
        }
    }

}
