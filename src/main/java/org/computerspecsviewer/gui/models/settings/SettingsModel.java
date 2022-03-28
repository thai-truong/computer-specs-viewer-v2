package org.computerspecsviewer.gui.models.settings;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsModel {
    private static SettingsModel singleton;

    private Path configPath;
    private final Map<String, ConfigLineIndex> settingsIndexMap;

    private SettingsModel() {
        settingsIndexMap = new HashMap<>();
        settingsIndexMap.put("theme", ConfigLineIndex.THEME);
    }

    public static SettingsModel getInstance() {
        if(singleton == null) {
            singleton = new SettingsModel();

            try {
                singleton.create();
            } catch (IOException e) {
                // Find better way to handle IOException (i.e. logging)
                System.err.println(e);
            }
        }

        return singleton;
    }

    public String getSettingsValue(String settingsType) {
        if(!settingsIndexMap.containsKey(settingsType)) {
            return null;
        }

        return getAllSettings().get(settingsIndexMap.get(settingsType).getLineIndex());
    }

    public void saveSettingsChanges(Map<String, String> changes) {
        List<String> currSettings = getAllSettings();

        for(Map.Entry<String, String> change: changes.entrySet()) {
            String settingsType = change.getKey();
            String settingsChange = change.getValue();

            if(!settingsIndexMap.containsKey(settingsType)) {
                continue;
            }

            Integer index = settingsIndexMap.get(settingsType).getLineIndex();
            currSettings.set(index, settingsChange);
        }

        writeLines(currSettings);
    }

    private void create() throws IOException {
        try {
            configPath = Paths.get(ClassLoader.getSystemResource("config/settings.conf").toURI());
        } catch (URISyntaxException e) {
            System.err.println("Incorrect filepath to settings configuration file.");
            return;
        }

        if(isFileEmpty()) {
            System.out.println("File is empty");
            addDefaultConfig();
        } else {
            System.out.println(Files.readAllLines(configPath));
        }
    }

    private void addDefaultConfig() throws IOException {
        List<String> defaultConfig = Arrays.asList(
                "black"
        );

        Files.write(configPath, defaultConfig);
    }

    private Boolean isFileEmpty() throws IOException {
        return Files.size(configPath) == 0;
    }

    private List<String> getAllSettings() {
        List<String> allLines = null;

        try {
            allLines = Files.readAllLines(configPath);
        } catch (IOException e) {
            System.err.println(e);
        }

        return allLines;
    }

    private void writeLines(List<String> settingsValues) {
        try {
            Files.write(configPath, settingsValues);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /*public static void main(String[] args) {
        try {
            SettingsModel test = SettingsModel.getInstance();

            Map<String, String> testChanges = new HashMap<>();
            testChanges.put("theme", "white");

            test.saveSettingsChanges(testChanges);
        } catch (IOException e) {
            System.out.println("Something weird happened...");
        }
    }*/
}
