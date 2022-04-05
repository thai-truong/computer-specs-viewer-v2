package org.computerspecsviewer.gui.models.settings;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class SettingsModel {
    private static SettingsModel singleton;

    private Path configPath;
    private final Map<String, ConfigLineIndex> settingsIndexMap;
    private String[] allSettingsValues;

    private SettingsModel() {
        settingsIndexMap = new HashMap<>();
        settingsIndexMap.put("theme", ConfigLineIndex.THEME);

        allSettingsValues = new String[settingsIndexMap.size()];

        for(int i = 0; i < allSettingsValues.length; i++) {
            allSettingsValues[i] = "";
        }
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

        return allSettingsValues[settingsIndexMap.get(settingsType).getLineIndex()];
    }

    public void saveSettingsChanges(Map<String, String> changes) {
        for(Map.Entry<String, String> change: changes.entrySet()) {
            String settingsType = change.getKey();
            String settingsChange = change.getValue();

            if(!settingsIndexMap.containsKey(settingsType)) {
                continue;
            }

            Integer index = settingsIndexMap.get(settingsType).getLineIndex();
            allSettingsValues[index] = settingsChange;
        }
    }

    public void saveSettingsChange(String settingsType, String newValue) {
        if(!settingsIndexMap.containsKey(settingsType)) {
            return;
        }

        Integer index = settingsIndexMap.get(settingsType).getLineIndex();
        allSettingsValues[index] = newValue;
    }

    public void saveSettingsToFile() {
        writeLines(List.of(allSettingsValues));
    }

    private void create() throws IOException {
        try {
            configPath = Paths.get(ClassLoader.getSystemResource("config/settings.conf").toURI());
        } catch (URISyntaxException e) {
            System.err.println("Incorrect filepath to settings configuration file.");
            return;
        }

        extractInitialSettings();
    }

    private void extractInitialSettings() {
        List<String> readLines = getAllSettings();

        if(readLines == null) {
            return;
        }

        // Size of readLines is always less than or equal to size of allSettingsValues
        for(int i = 0; i < readLines.size(); i++) {
            allSettingsValues[i] = readLines.get(i);
        }
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
}
