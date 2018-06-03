package util;

import javax.swing.*;
import java.net.URL;

import main.Library;

public class CreatecdIcon {
    public static ImageIcon add(String imageName) {
        String path = "/" + imageName;
        URL iconUrl = Library.class.getResource(path);
        ImageIcon imageIcon = new ImageIcon(iconUrl);
        return imageIcon;
    }
}
