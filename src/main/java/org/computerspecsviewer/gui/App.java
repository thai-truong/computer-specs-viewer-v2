package org.computerspecsviewer.gui;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import org.computerspecsviewer.gui.views.menutree.treeitemvalue.BaseTreeItemValue;
import org.computerspecsviewer.gui.views.menutree.treeitemvalue.InfoQueryPageTreeItemValue;
import org.computerspecsviewer.infoquery.cpu.CpuInfoQuery;
import org.computerspecsviewer.infoquery.disk.DiskInfoQuery;
import org.computerspecsviewer.infoquery.filesystem.FileSystemInfoQuery;

public class App extends Application {
    // This will be the place to initialize all needed UI components/elements
    private Parent createAppContent() {
        // Placeholder
        //InfoQueryPageComponent testInfoQueryPage = new InfoQueryPageComponent("Test", "Test", new FileSystemInfoQuery());
        //return testInfoQueryPage.get();

        TreeItem<BaseTreeItemValue> testRoot = new TreeItem<>(new InfoQueryPageTreeItemValue("Test root", "Test Desc", new FileSystemInfoQuery()));
        testRoot.setExpanded(true);
        testRoot.getChildren().addAll(
                new TreeItem<>(new InfoQueryPageTreeItemValue("Test Item 1", "Test Desc 1", new CpuInfoQuery())),
                new TreeItem<>(new InfoQueryPageTreeItemValue("Test Item 2", "Test Desc 2", new DiskInfoQuery()))
        );

        return new TreeView<>(testRoot);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Placeholder
        stage.setScene(new Scene(createAppContent(), 300, 300));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
