/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2023/2024
 * Group Capstone Project
 * Group #2
 * 1 - 5026221124 - Gita Elizza Larasati
 * 2 - 5026221185 - Mutiara Nurshadrina Rafifah
 * 3 - 5026221208 - Shine Quinn Firdaus
 */

import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {
    private static final long serialVersionUID = 1L;
    GameBoardPanel board = new GameBoardPanel();
    JButton btnRestart = new JButton("Restart");
    JButton btnAbout = new JButton("About");


    public Main() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        JOptionPane.showMessageDialog(this, "Click 'Ok' to start Sudoku!");

        cp.add(board, BorderLayout.CENTER);

        JPanel restartPanel = new JPanel();
        restartPanel.add(btnRestart);
        cp.add(restartPanel, BorderLayout.SOUTH);

        btnRestart.addActionListener(e -> {
            board.restartGame();
        });

        JPanel aboutPanel = new JPanel();
        restartPanel.add(btnAbout);
        cp.add(aboutPanel, BorderLayout.NORTH);

        btnAbout.addActionListener(e -> {
            SwingUtilities.invokeLater(AboutFrame::new);
        });

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sudoku");
        setVisible(true);

        board.newGame();
    }

    public static void main(String[] args) {
        Main main = new Main();
    }
}
