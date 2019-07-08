import javax.swing.*;
import java.awt.*;

public class TheGame extends JFrame {

    public TheGame() {
        initUI();
    }

    private void initUI() {
        add(new GameBoard());
        setSize(250, 200);
        setTitle("A Card Game... WOW!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
                    TheGame ex = new TheGame();
                    ex.setVisible(true);
        });
    }
}
