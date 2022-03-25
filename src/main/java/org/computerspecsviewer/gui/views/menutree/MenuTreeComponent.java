package org.computerspecsviewer.gui.views.menutree;

import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.computerspecsviewer.gui.data.SectionTextDisplay;
import org.computerspecsviewer.gui.views.menutree.itemvalue.BaseItemValue;
import org.computerspecsviewer.gui.views.menutree.itemvalue.RootItemValue;
import org.computerspecsviewer.gui.views.menutree.itemvalue.SectionPageItemValue;
import org.computerspecsviewer.gui.views.page.PageNavigationLinks;
import org.computerspecsviewer.infoquery.utils.StringHelpers;
import oshi.util.tuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MenuTreeComponent {
    private TreeView<BaseItemValue> menuTree;
    private Map<String, List<String>> sectionStructure;
    private Map<String, TreeItem<BaseItemValue>> leafSections;
    private Map<String, Boolean> toDisplayLinks;
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
        MenuTreeStructure structure = new MenuTreeStructure();
        Triplet<Map<String, List<String>>, Map<String,
                TreeItem<BaseItemValue>>, Map<String, Boolean>> structureDetails = structure.get();

        sectionStructure = structureDetails.getA();
        leafSections = structureDetails.getB();
        toDisplayLinks = structureDetails.getC();

        addItemSelectedListener();
        addStructure();
        addTreeDefaults();

    }

    private void addItemSelectedListener() {
        menuTree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            TreeItem<BaseItemValue> selectedItem = newValue;
            Node componentToDisplay = selectedItem.getValue().getComponent();

            if(componentToDisplay != null) {
                PageNavigationLinks navLinks = new PageNavigationLinks(selectedItem, menuTree.getSelectionModel());

                appDisplayComponent.setTop(navLinks.get());
                appDisplayComponent.setCenter(componentToDisplay);
            }
        });
    }

    private void addStructure() {
        List<TreeItem<BaseItemValue>> topLevelSections = new ArrayList<>();

        for(String section: sectionStructure.keySet()) {
            TreeItem<BaseItemValue> topLevelSection = createTreeItem(section);
            topLevelSection.getValue().setParent(null);

            topLevelSections.add(topLevelSection);
        }

        treePrimaryItem = topLevelSections.get(0);
        treeRoot.getChildren().addAll(topLevelSections);
    }

    private void addTreeDefaults() {
        treePrimaryItem.setExpanded(true);
        menuTree.getSelectionModel().select(treePrimaryItem);
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
        currSectionItem = new TreeItem<>(new SectionPageItemValue(
                currSectionTitle, sectionTextDisplay.get(currSection)
        ));

        currSectionItem.getChildren().addAll(childrenSections);
        setSectionChildrenConnections(childrenSections);
        setSectionLinks(currSectionItem, toDisplayLinks.get(currSection));

        return currSectionItem;
    }

    private void setSectionChildrenConnections(List<TreeItem<BaseItemValue>> children) {
        for(int i = 0; i < children.size(); i++) {
            TreeItem<BaseItemValue> curr = children.get(i);

            int prevIdx = i - 1;
            int nextIdx = i + 1;

            if(prevIdx >= 0) {
                curr.getValue().setPrev(children.get(prevIdx));
            }

            if(nextIdx < children.size()) {
                curr.getValue().setNext(children.get(nextIdx));
            }

            curr.getValue().setParent(curr.getParent());
        }
    }

    private void setSectionLinks(TreeItem<BaseItemValue> section, Boolean toSet) {
        if(toSet == null || !toSet) {
            return;
        }

        List<Node> quickAccessLinks = new ArrayList<>();
        VBox linkComponent = new VBox(3.0);
        List<TreeItem<BaseItemValue>> children = section.getChildren();

        quickAccessLinks.add(new Text("Here are the quick access links:"));

        for(TreeItem<BaseItemValue> child: children) {
            Hyperlink link = new Hyperlink(child.getValue().toString());
            link.setOnAction((event) -> menuTree.getSelectionModel().select(child));

            quickAccessLinks.add(link);
        }

        linkComponent.getChildren().addAll(quickAccessLinks);
        section.getValue().appendToComponent(linkComponent);
    }
}
