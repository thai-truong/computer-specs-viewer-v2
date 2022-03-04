package org.computerspecsviewer.gui.views.infoquery;

import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;

public class InfoQueryPageComponent {
    private static final Integer vBoxSpacing = 2;

    public String title;
    public String description;
    public BaseInfoQuery infoQuery;
    public Pane infoQueryPage;

    public InfoQueryPageComponent(String title, String description, BaseInfoQuery infoQuery) {
        this.title = title;
        this.description = description;
        this.infoQuery = infoQuery;
    }

    public Pane get() {
        if(infoQueryPage == null) {
            createPage();
        }

        return infoQueryPage;
    }

    private void createPage() {
        infoQueryPage = new VBox(vBoxSpacing);

        Label pageTitle = new Label(title);
        Text pageDescription = new Text(description);
        InfoQueryComponent infoQueryComponent = new InfoQueryComponent(infoQuery);

        infoQueryPage.getChildren().addAll(pageTitle,
                new Separator(), pageDescription,
                new Separator(), infoQueryComponent.get());
    }
}
