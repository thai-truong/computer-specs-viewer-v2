package org.computerspecsviewer.gui.mainapp;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.computerspecsviewer.gui.assets.AppIcons;
import org.computerspecsviewer.gui.mainapp.approot.AppRootComponent;

public class App extends Application {
    // This will be the place to initialize all needed UI components/elements
    private Parent createAppContent() {
        AppRootComponent appRoot = new AppRootComponent();
        return appRoot.get();
    }

    @Override
    public void start(Stage stage) throws Exception {
        initializeStage(stage);
        stage.show();
    }

    private void initializeStage(Stage stage) {
        AppIcons appIcons = new AppIcons();

        Scene scene = new Scene(createAppContent(), 800, 600);
        scene.getStylesheets().add("css/hyperlink.css");

        stage.setScene(scene);
        stage.setTitle("Computer Specs Viewer 2.0");
        stage.getIcons().add(appIcons.getMainAppIcon());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
