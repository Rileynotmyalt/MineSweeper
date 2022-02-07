import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        // Difficulties
        int[] difficulties = { 5, 10, 15 };
        int difficulty = 0;

        // create frame
        JFrame frame = new JFrame("Among Us");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);

        ArrayList<Square> squares = new ArrayList<Square>();

        // create buttons
        for (int i = 0; i < Math.pow(difficulties[difficulty], 2); i++) {
            squares.add(new Square(false));
        }

        frame.setLayout(new GridLayout(0, difficulties[difficulty]));

        for (Square square : squares) {
            frame.add(square.button);
        }

        frame.setVisible(true);
    }
}
