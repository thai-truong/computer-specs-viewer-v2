package org.computerspecsviewer.gui.mainapp.approot;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DisplayPaneFooter {
    private static final String FOOTER_TEXT = "- Computer Specs Viewer 2.0 (Made with Java this time) -";
    private static final Integer FONT_SIZE = 12;

    private Text footerComponent;

    public DisplayPaneFooter() {}

    public Text get() {
        if(footerComponent == null) {
            create();
        }

        return footerComponent;
    }

    private void create() {
        footerComponent = new Text(FOOTER_TEXT);
        footerComponent.setFont(new Font(FONT_SIZE));
        footerComponent.setFill(Color.DARKGRAY);
    }
}
