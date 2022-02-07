import javax.swing.JButton;
import java.awt.*;

public class Square {
    boolean IsBomb;
    JButton button;

    public Square(boolean IsBomb) {
        this.IsBomb = IsBomb;
        this.button = new JButton("0");
        button.setPreferredSize(new Dimension(20, 30));
    }

}
