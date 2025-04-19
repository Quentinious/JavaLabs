import org.example.model.GameModel;
import view.GameView;
import org.example.controller.GameController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGame();
            }
        });
    }

    private static void createAndShowGame() {
        GameModel model = new GameModel();
        GameView view = new GameView(model);
        new GameController(model, view);

        JFrame frame = new JFrame("Shooting Cube");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(view);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
