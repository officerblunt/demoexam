package ru.officerblunt.Util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class BaseForm extends JFrame {
    public static String APP_NAME = "APP_NAME";
    public static Image APP_ICON = null;
    static {
        try {
            APP_ICON = ImageIO.read(BaseForm.class.getClassLoader().getResource("icon.png"));
        } catch (Exception e) {
            DialogUtil.showError(null,"Ошибка загрузки иконки!");
        }
    }
    public BaseForm (int x, int y) {
        setMinimumSize(new Dimension(x, y));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(
                Toolkit.getDefaultToolkit().getScreenSize().width / 2 - x / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 - y / 2
        );
        if (APP_ICON != null) {
            setIconImage(APP_ICON);
        }
        setTitle(APP_NAME);
    }
}
