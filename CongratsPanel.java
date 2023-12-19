import javax.swing.*;
import java.awt.*;

public class CongratsPanel extends JPanel {
    private JLabel congratsLabel;

    public CongratsPanel() {
        congratsLabel = new JLabel("Congrats! You've completed the Sudoku!");
        congratsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        congratsLabel.setForeground(Color.GREEN);
        add(congratsLabel);
        setVisible(false); // Sembunyikan panel secara default
    }

    public void showCongrats() {
        congratsLabel.setVisible(true);
    }

    public void hideCongrats() {
        congratsLabel.setVisible(false);
    }
}
