module com.example.surinklietuva {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires junit;

    opens com.example.surinklietuva to javafx.fxml;
    exports com.example.surinklietuva;

    opens com.example.surinklietuva.Controllers to javafx.fxml;
    exports com.example.surinklietuva.Controllers;

    opens com.example.surinklietuva.DataStructures to javafx.fxml;
    exports com.example.surinklietuva.DataStructures;

    opens Test to org.junit.runners.BlockJUnit4ClassRunner;
    exports Test;
}