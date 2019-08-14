import javax.swing.*;
import java.util.concurrent.Callable;

public class Helper {

    public static String enterString(String message) {
        String s;
        while (true) {
            s = JOptionPane.showInputDialog(message);
            if (s.trim().length() == 0) {
                JOptionPane.showConfirmDialog(null, "Required field");
                continue;
            }
            return s;
        }
    }

    public static int enterInteger(String message) {
        int i;
        while (true) {
            try {
                i = Integer.parseInt(JOptionPane.showInputDialog(message));
                if (i <= 0) {
                    JOptionPane.showConfirmDialog(null, "Number needs to be bigger then zero");
                    continue;
                }

                return i;


            } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, "Input of number is required");
            }

        }

    }
}

