package org.computerspecsviewer.gui.views.menutree;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import org.computerspecsviewer.gui.data.SectionTextDisplay;
import org.computerspecsviewer.gui.views.menutree.itemvalue.BaseItemValue;
import org.computerspecsviewer.gui.views.menutree.itemvalue.RootItemValue;
import org.computerspecsviewer.gui.views.menutree.itemvalue.SectionPageItemValue;
import org.computerspecsviewer.infoquery.utils.StringHelpers;
import oshi.util.tuples.Pair;
import oshi.util.tuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MenuTreeComponent {
    private TreeView<BaseItemValue> menuTree;
    private Map<String, List<String>> sectionStructure;
    private Map<String, TreeItem<BaseItemValue>> leafSections;
    private Map<String, Node> sectionDisplays;
    private SectionTextDisplay sectionTextDisplay;

    private BorderPane appDisplayComponent;
    private TreeItem<BaseItemValue> treeRoot = new TreeItem<>(new RootItemValue());
    private TreeItem<BaseItemValue> treePrimaryItem;

    public MenuTreeComponent(BorderPane appDisplayComponent) {
        this.appDisplayComponent = appDisplayComponent;
        sectionTextDisplay = SectionTextDisplay.getInstance();
    }

    public TreeView<BaseItemValue> getComponent() {
        if(menuTree == null) {
            createComponent();
        }

        return menuTree;
    }

    private void createComponent() {
        menuTree = new TreeView<>(treeRoot);

        // Due to a JavaFX bug where programmatically expanding then selecting parent section item (p1)
        // when root item (r1) is already not shown selects p1 AND another section item that is a child of p1.
        // To get around this weird bug, expand r1 while it is still shown, expand and select p1,
        // then hide r1 afterwards, as below.
        menuTree.getRoot().setExpanded(true);
        configureMenuTree();
        menuTree.setShowRoot(false);
    }

    private void configureMenuTree() {
        MenuTreeStructure structure = new MenuTreeStructure(menuTree.getSelectionModel());
        Triplet<Map<String, List<String>>, Map<String, TreeItem<BaseItemValue>>, Map<String, Node>> structureDetails = structure.get();

        sectionStructure = structureDetails.getA();
        leafSections = structureDetails.getB();
        sectionDisplays = structureDetails.getC();

        addItemSelectedListener();
        addStructure();
        setTreeDefaults();
    }

    private void addItemSelectedListener() {
        menuTree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            TreeItem<BaseItemValue> selectedItem = newValue;
            Node componentToDisplay = selectedItem.getValue().getComponentToDisplay();

            if(componentToDisplay != null) {
                appDisplayComponent.setCenter(componentToDisplay);
            }
        });
    }

    private void addStructure() {
        List<TreeItem<BaseItemValue>> topLevelSections = new ArrayList<>();

        for(String section: sectionStructure.keySet()) {
            topLevelSections.add(createTreeItem(section));
        }

        treePrimaryItem = topLevelSections.get(0);
        treeRoot.getChildren().addAll(topLevelSections);
    }

    private void setTreeDefaults() {
        treePrimaryItem.setExpanded(true);

        Integer primaryItemIdx = menuTree.getRow(treePrimaryItem);
        menuTree.getSelectionModel().select(primaryItemIdx);
    }
    
    private TreeItem<BaseItemValue> createTreeItem(String currSection) {
        TreeItem<BaseItemValue> currSectionItem;
        List<TreeItem<BaseItemValue>> childrenSections = new ArrayList<>();

        // If a section does not have any child sections, then it must be a leaf containing a page component
        if(!sectionStructure.containsKey(currSection)) {
            if(!leafSections.containsKey(currSection)) {
                return null;
            }

            return leafSections.get(currSection);
        }

        for(String childSection: sectionStructure.get(currSection)) {
            TreeItem<BaseItemValue> childTreeItem = createTreeItem(childSection);

            if(childTreeItem != null) {
                childrenSections.add(childTreeItem);
            }
        }

        String currSectionTitle = StringHelpers.transformFieldName(currSection);

        if(sectionDisplays.containsKey(currSection)) {
            currSectionItem = new TreeItem<>(new SectionPageItemValue(
                    currSectionTitle, sectionDisplays.get(currSection)));
        } else {
            currSectionItem = new TreeItem<>(new SectionPageItemValue(
                    currSectionTitle, sectionTextDisplay.get(currSection)
            ));
        }

        currSectionItem.getChildren().addAll(childrenSections);

        return currSectionItem;
    }
}
