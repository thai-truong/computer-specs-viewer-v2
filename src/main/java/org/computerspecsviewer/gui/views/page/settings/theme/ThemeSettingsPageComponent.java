package org.computerspecsviewer.gui.views.page.settings.theme;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.computerspecsviewer.gui.viewmodels.settings.SettingsViewModel;
import org.computerspecsviewer.gui.views.base.PageComponent;
import org.computerspecsviewer.gui.views.page.settings.SettingsPageComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThemeSettingsPageComponent extends PageComponent implements SettingsPageComponent {
    private SettingsViewModel viewModel;
    private Map<String, String> themeCssMap;

    private String title;
    private ToggleGroup radioButtonGroup;
    private Pane themeSelections;
    private String initialTheme;

    public ThemeSettingsPageComponent(String title) {
        themeCssMap = new HashMap<>();
        themeCssMap.put("JavaFX Default", "javafx_default");
        themeCssMap.put("Black", "#000000");
        themeCssMap.put("Gray", "rgba(60, 60, 60, 255)");
        themeCssMap.put("Blue", "#1734d0");

        this.title = title;
        viewModel = SettingsViewModel.getInstance();
        initialTheme = viewModel.getTheme();
    }

    protected void create() {
        radioButtonGroup = new ToggleGroup();

        Label themeLabel = new Label("Pick a theme:");
        themeSelections = new VBox();
        themeSelections.getChildren().addAll(createRadioButtons());

        Label pageTitle = new Label(title);
        page.getChildren().addAll(
                pageTitle,
                new Separator(),
                themeLabel,
                themeSelections,
                createSaveButton()
        );
    }

    // Separating settings initialization logic from creation logic
    public void initWithPrevSaved(Scene scene) {
        setSceneTheme(scene, themeCssMap.get(initialTheme));
    }

    private List<RadioButton> createRadioButtons() {
        List<RadioButton> buttons = new ArrayList<>();

        for(Map.Entry<String, String> themeInfo: themeCssMap.entrySet()) {
            String label = themeInfo.getKey();
            String cssValue = themeInfo.getValue();

            RadioButton button = new RadioButton(label);
            button.setUserData(cssValue);
            button.setToggleGroup(radioButtonGroup);

            if(label.equals(initialTheme)) {
                radioButtonGroup.selectToggle(button);
            }

            buttons.add(button);
        }

        return buttons;
    }

    private Button createSaveButton() {
        Button saveButton = new Button("Save");
        addSaveButtonSelectedListener(saveButton);

        return saveButton;
    }

    private void addSaveButtonSelectedListener(Button button) {
        button.setOnAction((event) -> {
            RadioButton selectedTheme = (RadioButton) radioButtonGroup.getSelectedToggle();
            String themeLabel = selectedTheme.getText();
            String themeValue = selectedTheme.getUserData().toString();

            // Need to change app theme to the theme **value** selected,
            // then save the selected theme **label** to the settings file
            setSceneTheme(button, themeValue);
            viewModel.setTheme(themeLabel);
        });
    }

    private void setSceneTheme(Node node, String theme) {
        Parent sceneRoot = node.getScene().getRoot();
        sceneRoot.setStyle(getSingleLineCssTheme(theme));
    }

    private void setSceneTheme(Scene scene, String theme) {
        Parent sceneRoot = scene.getRoot();
        sceneRoot.setStyle(getSingleLineCssTheme(theme));
    }

    private String getSingleLineCssTheme(String theme) {
        if(theme.equals("javafx_default")) {
            return "";
        }

        return "-fx-base: " + theme + ";";
    }
}
