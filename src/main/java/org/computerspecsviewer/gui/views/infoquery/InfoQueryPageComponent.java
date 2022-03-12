package org.computerspecsviewer.gui.views.infoquery;

import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.computerspecsviewer.gui.views.base.PageComponent;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;

public class InfoQueryPageComponent extends PageComponent {
    private static final Integer vBoxSpacing = 2;

    private String title;
    private String description;
    private BaseInfoQuery infoQuery;

    public InfoQueryPageComponent(String title, String description, BaseInfoQuery infoQuery) {
        this.title = title;
        this.description = description;
        this.infoQuery = infoQuery;
    }

    protected void create() {
        page = new VBox(vBoxSpacing);

        Label pageTitle = new Label(title);
        Text pageDescription = new Text(description);
        InfoQueryComponent infoQueryComponent = new InfoQueryComponent(infoQuery);

        page.getChildren().addAll(pageTitle,
                new Separator(), pageDescription,
                new Separator(), infoQueryComponent.get());
    }
}
