package ru.officerblunt.Util;

import javax.swing.*;
import java.awt.*;

public class DialogUtil extends JOptionPane {
    public static void showError (Component c, String t) {
        JOptionPane.showMessageDialog(c, t, "ERROR!", JOptionPane.ERROR_MESSAGE);
    }
    public static void showMessage (Component c, String t) {
        JOptionPane.showMessageDialog(c, t, "INFO", JOptionPane.INFORMATION_MESSAGE);
    }
}
