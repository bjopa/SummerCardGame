import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {

    private Image cardImg;

    public GameBoard() {
        initBoard();
    }

    private void initBoard() {
        loadImage();
        int w = cardImg.getWidth(this);
        int h = cardImg.getHeight(this);
        setPreferredSize(new Dimension(w,h));
    }

    private void loadImage() {
        ImageIcon ii = new ImageIcon("img/DA.jpg");
        cardImg=ii.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
        g.drawImage(cardImg, 0,0, 80,120,null);
    }

}
