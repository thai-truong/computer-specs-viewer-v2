package org.computerspecsviewer.gui.assets;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AppIcons {
    private static AppIcons singleton;

    private Image mainAppIcon;
    private ImageView prevIcon;
    private ImageView nextIcon;
    private ImageView homeIcon;

    private AppIcons() {
        mainAppIcon = new Image("icons/appicon.png");

        prevIcon = new ImageView("icons/prev.png");
        nextIcon = new ImageView("icons/next.png");
        homeIcon = new ImageView("icons/home.png");
    }

    public static synchronized AppIcons getInstance() {
        if(singleton == null) {
            singleton = new AppIcons();
        }

        return singleton;
    }

    public Image getMainAppIcon() {
        return mainAppIcon;
    }

    public ImageView getPrevIcon() { return prevIcon; }

    public ImageView getNextIcon() { return nextIcon; }

    public ImageView getHomeIcon() { return homeIcon; }
}
