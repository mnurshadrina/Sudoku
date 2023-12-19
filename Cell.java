
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

public class Cell extends JTextField{
    private static final long serialVersionUID = 1L;
    public static final Color BG_GIVEN = new Color(238, 199, 89); // RGB
    public static final Color FG_GIVEN = Color.BLACK;
    public static final Color FG_NOT_GIVEN = Color.BLACK;
    public static final Color BG_TO_GUESS  = new Color(255, 247, 212);
    public static final Color BG_CORRECT_GUESS = new Color(0, 216, 0);
    public static final Color BG_WRONG_GUESS   = new Color(216, 0, 0);
    public static final Font FONT_NUMBERS = new Font("OCR A Extended", Font.PLAIN, 28);

    int row, col;
    int number;
    CellStatus status;
    boolean isLocked;

    public void restartGame() {
        // Reset status menjadi TO_GUESS
        status = CellStatus.TO_GUESS;
        paint();
    }
    public Cell(int row, int col) {
        super();
        this.row = row;
        this.col = col;
        super.setHorizontalAlignment(JTextField.CENTER);
        super.setFont(FONT_NUMBERS);
    }
    public  Cell(int row, int col, int value){
        super();
        this.row = row;
        this.col = col;
        this.number = value;
        super.setHorizontalAlignment(JTextField.CENTER);
        super.setFont(FONT_NUMBERS);
    }
    public void newGame(int number, boolean isGiven) {
        this.number = number;
        status = isGiven ? CellStatus.GIVEN : CellStatus.TO_GUESS;
        paint();
    }
    public void paint() {
        if (status == CellStatus.GIVEN) {
            super.setText(number + "");
            super.setEditable(false);
            super.setBackground(BG_GIVEN);
            super.setForeground(FG_GIVEN);
            super.getCaret().setVisible(false); // Menyembunyikan kursor
        } else if (status == CellStatus.TO_GUESS) {
            super.setText("");
            super.setEditable(true);
            super.setBackground(BG_TO_GUESS);
            super.setForeground(FG_NOT_GIVEN);

        } else if (status == CellStatus.CORRECT_GUESS) {
            super.setBackground(BG_CORRECT_GUESS);
            super.setForeground(Color.BLACK); // Mengubah warna teks menjadi hijau
            super.setEditable(false);
            super.getCaret().setVisible(false); // Menyembunyikan kursor
        } else if (status == CellStatus.WRONG_GUESS) {
            super.setBackground(BG_WRONG_GUESS);
            super.setForeground(Color.BLACK);
            super.getCaret().setVisible(true); // Menampilkan kembali kursor
        }
    }


}
