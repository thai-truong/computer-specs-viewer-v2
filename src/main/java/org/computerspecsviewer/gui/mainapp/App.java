package org.computerspecsviewer.gui.mainapp;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.computerspecsviewer.gui.mainapp.approot.AppRootComponent;

public class App extends Application {
    // This will be the place to initialize all needed UI components/elements
    private Parent createAppContent() {
        AppRootComponent appRoot = new AppRootComponent();
        return appRoot.get();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Placeholder
        stage.setScene(new Scene(createAppContent(), 800, 600));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
