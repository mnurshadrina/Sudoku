import java.util.Stack;
import java.util.Random;

public class Puzzle {
    int[][] numbers = new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    Cell[][] puzcel = new Cell[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    boolean[][] isGiven = new boolean[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];

    public Puzzle() {
        super();
    }

    public void newPuzzle(int cellsToGuess) {
        numbers = new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
        int intvalue = 12;
        int intcount = 0;
        do {
            for (int i = 0; i < SudokuConstants.GRID_SIZE; i++) {
                for (int j = 0; j < SudokuConstants.GRID_SIZE; j++) {
                    numbers[1][j] = 0;
                    puzcel[i][j] = new Cell(i, j, numbers[i][j]);
                    isGiven[i][j] = false;
                }
            }
            while (intcount < intvalue) {
                int rrow = getRandomNumber(0,9);
                int rcol = getRandomNumber(0,9);
                int rval = getRandomNumber(1,9);
                if(numbers[rrow][rcol]==0) {
                    if(vval(rrow,rcol,rval)) {
                        numbers[rrow][rcol]=rval;
                        intcount+=1;
                        Cell temp = new Cell(rrow,rcol,numbers[rrow][rcol]);
                        temp.isLocked=true;
                        puzcel[rrow][rcol]=temp;
                    }
                }
            }
        }
        while (!solve());
        int limit = 71;
        int rowcollimit=9;
        int count = 0;
        int [] collimit = new int[9];
        int [] rowlimit = new int[9];
        while(count<limit){
            int rrow = getRandomNumber(0,9);
            int rcol = getRandomNumber(0,9);
            if(isGiven[rrow][rcol]==false){
                if(collimit[rcol]<rowcollimit&&rowlimit[rrow]<rowcollimit){
                    isGiven[rrow][rcol]= true;
                    collimit[rcol]+=1;
                    rowlimit[rrow]+=1;
                    count+=1;
                }
            }
        }
    }
    public int getRandomNumber(int min, int max) {
        Random random = new Random();
        int rnum = random.nextInt(max) + min;
        return rnum;
    }
    public boolean vval(int row, int col, int value) {
        boolean valid = true;
        for(int i=0;i<SudokuConstants.GRID_SIZE;i++) {
            if(numbers[i][col]==value) {
                valid=false;
                break;
            }
        }
        for(int i=0;i<SudokuConstants.GRID_SIZE;i++) {
            if(numbers[row][i]==value) {
                valid=false;
                break;
            }
        }
        int sgrow = row-row%3;
        int sgcol = col-col%3;
        for(int i=0;i<SudokuConstants.SUBGRID_SIZE;i++) {
            for(int j=0;j<SudokuConstants.SUBGRID_SIZE;j++) {
                if(numbers[sgrow+i][sgcol+j]==value) {
                    valid=false;
                    break;
                }
            }
        }
        return valid;
    }
    public boolean solve() {
        Stack<Cell> stack = new Stack<>();
        int crow = 0;
        int ccol = 0;
        int cval = 1;
        while (stack.size()<81){
            if (puzcel[crow][ccol].isLocked){
                Cell lockedcell = puzcel[crow][ccol];
                stack.push(lockedcell);
                ccol+=1;
                if(ccol>=9){
                    crow+=1;
                    ccol=0;
                } continue;
            }
            for (;cval<=10;cval++){
                if (vval(crow,ccol,cval)){
                    break;
                }
            }
            if (cval<=9){
                Cell cell = new Cell(crow,ccol,cval);
                numbers[crow][ccol]=cval;
                stack.push(cell);
                ccol+=1;
                if(ccol>=9){
                    crow+=1;
                    ccol=0;
                }
                cval=1;
            }
            else{
                if (stack.size()>0){
                    Cell cell = stack.pop();
                    while (cell.isLocked){
                        if (stack.size()>0){
                            cell = stack.pop();
                        } else{
                            return false;
                        }
                    }
                    crow = cell.row;
                    ccol = cell.col;
                    cval = cell.number + 1;
                    numbers[crow][ccol]=0;
                } else{
                    return false;
                }
            }
        }
        return true;
    }
}