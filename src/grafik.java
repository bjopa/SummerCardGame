import javax.swing.*;
import java.awt.*;

public class grafik {
    public static JFrame frame = new JFrame("Hello Swing");

    static void boardInit() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(640, 480));
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
