import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameBoardPanel extends JPanel {

    private JButton btnNewGame = new JButton("New Game");
    private JButton btnAbout = new JButton("About");

    private static final long serialVersionUID = 1L;
    public static final int CELL_SIZE = 60;
    public static final int BOARD_WIDTH = CELL_SIZE * SudokuConstants.GRID_SIZE;
    public static final int BOARD_HEIGHT = CELL_SIZE * SudokuConstants.GRID_SIZE;
    private Cell[][] cells = new Cell[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    private Puzzle puzzle = new Puzzle();
    private String player1Name;

    public GameBoardPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        super.setLayout(new GridLayout(SudokuConstants.GRID_SIZE, SudokuConstants.GRID_SIZE));

        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                cells[row][col] = new Cell(row, col);
                super.add(cells[row][col]);
            }
        }



        btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Panggil restartGame() untuk semua sel di papan permainan
                for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
                    for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                        cells[row][col].restartGame();
                    }
                }
            }
        });

        CellInputListener listener = new CellInputListener();
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                if (cells[row][col].isEditable()) {
                    cells[row][col].addActionListener(listener);
                    cells[row][col].restartGame();
                }
            }
        }

        super.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        getPlayerNames();
    }

    private void getPlayerNames() {
        player1Name = JOptionPane.showInputDialog(this, "Enter name for Player 1:", "Player 1", JOptionPane.PLAIN_MESSAGE);

        // Ensure that names are not empty or canceled
        if (player1Name == null || player1Name.trim().isEmpty()) {
            player1Name = "Player 1";
        }
    }

    public void newGame() {
        puzzle.newPuzzle(2);
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                cells[row][col].newGame(puzzle.numbers[row][col], puzzle.isGiven[row][col]);
            }
        }
    }

    public boolean isSolved() {
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                if (cells[row][col].status == CellStatus.TO_GUESS || cells[row][col].status == CellStatus.WRONG_GUESS) {
                    return false;
                }
            }
        }
        return true;
    }

    public void restartGame() {
        puzzle.newPuzzle(2); // Menginisialisasi puzzle baru
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                cells[row][col].newGame(puzzle.numbers[row][col], puzzle.isGiven[row][col]);
            }
        }
    }

    private class CellInputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Cell sourceCell = (Cell) e.getSource();
            String inputText = sourceCell.getText().trim();

            if(!inputText.matches("[1-9]")){
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number between 1-9");
                return;
            }

            int numberIn = Integer.parseInt(inputText);
            if (numberIn < 1 || numberIn > 9) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number between 1-9.");
                return;
            }

            if (numberIn == sourceCell.number) {
                sourceCell.status = CellStatus.CORRECT_GUESS;
            } else {
                sourceCell.status = CellStatus.WRONG_GUESS;
            }
            sourceCell.paint();

            // Periksa jika permainan sudah selesai setelah memasukkan angka baru
            if (isSolved()) {
                JOptionPane.showMessageDialog(null, "Congratulation "+ player1Name + "!");
            }

        }
    }
}
