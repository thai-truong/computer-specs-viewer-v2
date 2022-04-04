package org.computerspecsviewer.gui.mainapp;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.computerspecsviewer.gui.assets.AppIcons;
import org.computerspecsviewer.gui.mainapp.approot.AppRootComponent;
import org.computerspecsviewer.gui.views.page.settings.SettingsPages;

public class App extends Application {
    private static final String APP_TITLE = "Computer Specs Viewer 2.0";
    private static final Double APP_WIDTH = 800.0;
    private static final Double APP_HEIGHT = 600.0;

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
        AppIcons appIcons = AppIcons.getInstance();

        Scene scene = new Scene(createAppContent(), APP_WIDTH, APP_HEIGHT);
        scene.getStylesheets().add("css/hyperlink.css");

        stage.setScene(scene);
        stage.setTitle(APP_TITLE);
        stage.getIcons().add(appIcons.getMainAppIcon());

        stage.setOnShown((event) -> {
            SettingsPages settingsPages = SettingsPages.getInstance();
            settingsPages.initializeAllSettings(stage.getScene());
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
