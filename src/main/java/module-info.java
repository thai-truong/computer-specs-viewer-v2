module com.example.computerspecsviewer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    requires com.github.oshi;

    opens com.example.computerspecsviewer to javafx.fxml;
    exports com.example.computerspecsviewer;
}