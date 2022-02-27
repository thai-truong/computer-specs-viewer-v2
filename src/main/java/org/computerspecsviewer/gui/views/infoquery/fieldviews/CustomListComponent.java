package org.computerspecsviewer.gui.views.infoquery.fieldviews;

import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import org.computerspecsviewer.displaytypes.customlist.CustomList;
import org.computerspecsviewer.displaytypes.customsingleton.CustomSingleton;
import org.computerspecsviewer.gui.utils.ComponentHelpers;
import org.computerspecsviewer.gui.utils.FieldType;
import org.computerspecsviewer.gui.views.infoquery.InfoQueryComponent;

import java.util.ArrayList;
import java.util.List;

public class CustomListComponent {
    List<?> listToDisplay;
    String elemTitle;
    Integer elemIndex;
    Accordion listAccordion;

    public CustomListComponent(CustomList<?> customList) {
        listToDisplay = customList.getTargetList();
        elemTitle = customList.getElemTitle();
        elemIndex = 0;
    }

    public Accordion get() {
        if(listAccordion == null) {
            createListAccordion();
        }

        return listAccordion;
    }

    private void createListAccordion() {
        List<TitledPane> accElemList = new ArrayList<>();

        if(!listToDisplay.isEmpty()) {
            FieldType listElemType = ComponentHelpers.getFieldType(listToDisplay.get(0));

            if(listElemType == FieldType.INFOQUERY) {
                for(Object elem: listToDisplay) {
                    CustomSingleton infoSingleton = (CustomSingleton) elem;
                    InfoQueryComponent infoQueryComponent = new InfoQueryComponent(infoSingleton.getTarget());
                    accElemList.add(new TitledPane(getCurrentTitle(), infoQueryComponent.get()));
                }
            } else {
                for(Object elem: listToDisplay) {
                    NormalFieldComponent elemComponent = new NormalFieldComponent(elem.toString());
                    accElemList.add(new TitledPane(getCurrentTitle(), elemComponent.get()));
                }
            }
        }

        listAccordion = new Accordion();
        listAccordion.getPanes().addAll(accElemList);
    }

    private String getCurrentTitle() {
        String currentTitle = elemTitle + " " + elemIndex;
        elemIndex++;

        return currentTitle;
    }
}
