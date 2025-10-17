import javax.swing.*;
import java.awt.*;

public class SmileyGUI extends JPanel {
    private int x = 0;             // x-position of the smiley
    private int y = 100;           // y-position of the smiley
    private int speed = 5;         // movement speed
    private int direction = 1;     // 1 = right, -1 = left

    public SmileyGUI() {
        // Timer to update the game loop every 50ms
        Timer timer = new Timer(50, e -> {
            x += speed * direction;

            // Bounce off the edges
            if (x > getWidth() - 50 || x < 0) {
                direction *= -1;
            }

            repaint(); // redraw the panel
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Serif", Font.PLAIN, 50));
        g.drawString("😊", x, y); // draw smiley at (x, y)
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Smiley Game");
        SmileyGUI game = new SmileyGUI();
        frame.add(game);
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
