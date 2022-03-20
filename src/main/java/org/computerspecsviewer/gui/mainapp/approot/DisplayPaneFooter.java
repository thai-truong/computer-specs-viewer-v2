package org.computerspecsviewer.gui.mainapp.approot;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DisplayPaneFooter {
    private final String footerText = "- Computer Specs Viewer 2.0 (Made with Java this time) -";
    private final Integer fontSize = 12;

    private Text footerComponent;

    public DisplayPaneFooter() {}

    public Text get() {
        if(footerComponent == null) {
            create();
        }

        return footerComponent;
    }

    private void create() {
        footerComponent = new Text(footerText);
        footerComponent.setFont(new Font(fontSize));
        footerComponent.setFill(Color.DARKGRAY);
    }
}
