package org.computerspecsviewer.gui.views.infoquery;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.computerspecsviewer.displaytypes.customlist.CustomList;
import org.computerspecsviewer.gui.utils.ComponentHelpers;
import org.computerspecsviewer.gui.utils.FieldType;
import org.computerspecsviewer.gui.views.infoquery.fieldcomponents.CustomListComponent;
import org.computerspecsviewer.gui.views.infoquery.fieldcomponents.NormalFieldComponent;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InfoQueryComponent {
    private static final Integer vBoxSpacing = 4;
    private static final Insets leftPadding = new Insets(0, 0, 0, 20);

    private Map<String, Object> rawFieldsMap;
    private Pane infoQueryComponent;

    public InfoQueryComponent(BaseInfoQuery infoQuery) {
        rawFieldsMap = infoQuery.getRawFieldsAsMap();
    }

    public Pane get() {
        if(infoQueryComponent == null) {
            createComponent();
        }

        return infoQueryComponent;
    }

    private void createComponent() {
        infoQueryComponent = new VBox(vBoxSpacing);
        CustomList<?> foundSingleList = hasOnlySingleList();

        if(foundSingleList != null) {
            CustomListComponent listComponent = new CustomListComponent(foundSingleList);
            infoQueryComponent.getChildren().addAll(listComponent.get());
        } else {
            List<Node> allFields = new ArrayList<>();

            for(Map.Entry<String, Object> entry: rawFieldsMap.entrySet()) {
                String fieldName = entry.getKey();
                Object fieldValue = entry.getValue();

                Node componentToAdd;

                switch(ComponentHelpers.getFieldType(fieldValue)) {
                    case LIST -> componentToAdd = createListField(fieldName, (CustomList<?>) fieldValue);
                    case INFOQUERY -> componentToAdd = createInfoQueryField(fieldName, (BaseInfoQuery) fieldValue);
                    default -> {
                        NormalFieldComponent normalField = new NormalFieldComponent(fieldName, fieldValue);
                        componentToAdd = normalField.get();
                    }
                }

                allFields.add(componentToAdd);
            }

            infoQueryComponent.getChildren().addAll(allFields);
        }
    }

    private Pane createListField(String fieldName, CustomList<?> fieldList) {
        Label label = new Label(fieldName + ":");
        VBox container = new VBox(vBoxSpacing);

        CustomListComponent listComponent = new CustomListComponent(fieldList);
        Accordion listAccordion = listComponent.get();
        listAccordion.setPadding(leftPadding);

        container.getChildren().addAll(label, listAccordion);

        return container;
    }

    private CustomList<?> hasOnlySingleList() {
        CustomList<?> foundList = null;

        if(rawFieldsMap.size() == 1) {
            for(Map.Entry<String, Object> entry: rawFieldsMap.entrySet()) {
                Object fieldValue = entry.getValue();
                FieldType valueType = ComponentHelpers.getFieldType(fieldValue);

                if(valueType == FieldType.LIST) {
                    foundList = (CustomList<?>)fieldValue;
                }
            }
        }

        return foundList;
    }

    private Pane createInfoQueryField(String fieldName, BaseInfoQuery fieldInfoQuery) {
        Label label = new Label(fieldName + ":");
        VBox container = new VBox(vBoxSpacing);

        InfoQueryComponent infoQueryComponent = new InfoQueryComponent(fieldInfoQuery);
        Pane infoQueryVBox = infoQueryComponent.get();
        infoQueryVBox.setPadding(leftPadding);

        container.getChildren().addAll(label, infoQueryVBox);

        return container;
    }
}
