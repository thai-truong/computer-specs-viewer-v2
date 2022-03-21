package org.computerspecsviewer.gui.data;

import oshi.util.tuples.Triplet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SectionTextDisplay {
    private static SectionTextDisplay singleton;
    private Map<String, String> textDisplayInfoMap;

    private SectionTextDisplay() {
        textDisplayInfoMap = new HashMap<>();

        textDisplayInfoMap.put("systemInformation", "Contains various available information about " +
                "your system, collected from your system.");
        textDisplayInfoMap.put("settings", "Contains configuration options and settings of this application");
    }

    public static synchronized SectionTextDisplay getInstance() {
        if(singleton == null) {
            singleton = new SectionTextDisplay();
        }

        return singleton;
    }

    public boolean found(String sectionType) {
        return textDisplayInfoMap.containsKey(sectionType);
    }

    public String get(String sectionType) {
        return textDisplayInfoMap.getOrDefault(sectionType, "");
    }

    public List<String> getSectionTypes() {
        List<String> sectionTypes = new ArrayList<>();

        for(String k: textDisplayInfoMap.keySet()) {
            sectionTypes.add(k);
        }

        return sectionTypes;
    }
}
