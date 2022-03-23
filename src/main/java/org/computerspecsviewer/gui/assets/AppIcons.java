package org.computerspecsviewer.gui.assets;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AppIcons {
    private Image mainAppIcon;

    private ImageView prevIcon;
    private ImageView nextIcon;
    private ImageView homeIcon;

    public AppIcons() {
        mainAppIcon = new Image("icons/app1.png");

        prevIcon = new ImageView("icons/prev.png");
        nextIcon = new ImageView("icons/next.png");
        homeIcon = new ImageView("icons/home.png");
    }

    public Image getMainAppIcon() {
        return mainAppIcon;
    }

    public ImageView getPrevIcon() { return prevIcon; }

    public ImageView getNextIcon() { return nextIcon; }

    public ImageView getHomeIcon() { return homeIcon; }
}
