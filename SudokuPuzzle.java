import java.util.ArrayList;
import java.util.Arrays;

public class SudokuPuzzle extends StackOverflowError{
	static int count = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long n1 = System.nanoTime();
		Integer[][] sudoku;

		
		Integer[][] puzzle = {
				{0,0,0,0,0,2,0,0,0},
				{8,0,4,9,3,0,0,0,5},
				{0,6,0,0,0,4,0,1,0},
				{0,1,0,0,5,0,0,9,0},
				{5,0,6,0,0,0,8,0,1},
				{0,2,0,0,1,0,0,0,0},
				{0,3,0,2,0,0,0,7,0},
				{2,0,0,0,6,0,4,0,9},
				{0,0,0,8,0,0,0,0,0},
				
		};
		
		Integer[][] puzzle1 = {
				{0,7,0,0,0,0,0,0,9},
				{5,1,0,4,2,0,6,0,0},
				{0,8,0,3,0,0,7,0,0},
				{0,0,8,0,0,1,3,7,0},
				{0,2,3,0,8,0,0,4,0},
				{4,0,0,9,0,0,1,0,0},
				{9,6,2,8,0,0,0,3,0},
				{0,0,0,0,1,0,4,0,0},
				{7,0,0,2,0,3,0,9,6},
				
		};
		
		Integer[][] puzzle2 = {
				{0,5,0,0,4,3,0,8,7},
				{0,0,4,0,8,0,1,9,0},
				{0,0,0,0,0,0,4,0,6},
				{0,0,7,0,0,8,6,4,0},
				{8,4,5,0,0,0,0,0,0},
				{0,3,6,0,0,4,8,0,0},
				{4,9,0,8,0,5,7,6,1},
				{6,7,1,4,0,9,0,0,8},
				{5,0,0,7,1,6,9,0,4},
				
		};
		
		Integer[][] test = Board.generateSudokuAnswer();
		Integer[][] puzzle3 = Board.createPuzzle(test, "m");
		Board.printPuzzle(test);
		Board.printPuzzle(puzzle3);
		//Integer[][] solution = solveSudoku1(puzzle3, 0, 0, false, false);
		//Board.printPuzzle(solution);
		//Board.isValid(solution);
		
	//	System.out.println("Program speed: " + (System.nanoTime() - n1) / 1000000000.0);
	//	System.out.println("Solution and solved puzzle are the same: " + Board.same(puzzle3, solution));
		System.out.println("Beginning of program!");
		
		Integer[] a = Board.numbersLeft(puzzle3);		
		
		try {
		Integer[][] solution = solveSudoku1(puzzle3, 0, 0, false, false);
		
		System.out.println("\nDONE!");
		Board.printPuzzle(solution);
		System.out.println(Board.isValid(solution)); 
		System.out.println("Spaces left are " + a[0]);
		//System.out.println("Solution and solved puzzle are the same: " + Board.same(puzzle2, solution));

		for(int i = 0; i < 10; i++) {
			//b += a[i]; 
			System.out.print(a[i] + " ");
		}
		System.out.println("Call counts are " + count);
		System.out.println("\n" + (System.nanoTime() - n1) / 1000000000.0);
		}
		catch(StackOverflowError s) {
			System.out.println("\n\nStackoveflow Error!!");
			System.out.println("Call counts are " + count);

			Board.printPuzzle(puzzle3);
			Board.printPuzzle(test);
			//System.out.println("Spaces are " + b);
		}

	}
	
	public static Integer[][] solveSudoku(Integer[][] sudoku, int row, int col, boolean isSolved, boolean repeat){
		Integer[] list = Board.numbersLeft(sudoku);
		int a = 0;
		int b = 0;
		
		//cube checks
		for(int i = a; i < a + 3; i++) {
			for(int j = b; j < b + 3; j++) {
				
			}
		}
		
		
		
		return null;
	}
	
	public static Integer[][] solveSudoku1(Integer[][] sudoku, int row, int col, boolean isSolved, boolean repeat){
			boolean backtrack = true;
			
			count++;
			//System.out.println("Start");
			Board.printPuzzle(sudoku);
			//System.out.println("Beg: row is " + row + ", col is " + col);
			//base case
			if(isSolved) {
				System.out.println("Solved");
				for(int i = 0; i < 9; i++) {
					for(int j = 0; j < 9; j++) {
						if(sudoku[i][j] < 0)
							sudoku[i][j] = Math.abs(sudoku[i][j]);
					}
				}
				return sudoku;
			}
			
			//recursive case
			else {
				
				///fixxxxxx
				/*while(sudoku[row][col] > 0 && !repeat) {
					if(col % 9 == 8)
						newRow = true;
					col = ((col + 1) % 9);
				}*/
				////////////
				
				while(sudoku[row][col] != 0 && !repeat) {
					if(col != 8)
						col++;
					else {
						col = 0;
						row++;
					}			
				}
				
				//System.out.println("in between row/col is " + row + ", " + col);
				int i = -1;
				//be careful when adding numbers. I might need to start back at zero
				//the issue is that it can't tell between the negative and positive values on check
				///maybe don't decrement i counter and just set cell to zero to reset if if statement becomes false
				while(sudoku[row][col] > -10 && i > -10 && (sudoku[row][col] + i > -10) ) {
					//System.out.println("i is " + i + " and the cell value is " + sudoku[row][col]);
					//System.out.println("inside loop");
					if(Board.columnCheck(col, Math.abs(sudoku[row][col]) + (-1* i), sudoku) && Board.rowCheck(row, Math.abs(sudoku[row][col]) + (-1 * i), sudoku) &&
						Board.cubeCheck(row, col, Math.abs(sudoku[row][col]) + (-1 * i), sudoku)){
							sudoku[row][col] += i;		
							backtrack = false;
							
							//System.out.println("[" + row + "," + col + "] is assigned " + sudoku[row][col]);
							break;
					}else {
						i--;
					}
				}
				
				if(backtrack == false) { //move to next cell
					//System.out.println("row is " + row + ", col is " + col);
					//find next value
					
					/*if(row == 8 && col == 8) {
						isSolved = true;
					}
					else {
						while(sudoku[row][col] != 0) {
							if(col != 8)
								col++;
							else {
								col = 0;
								row++;
							}			
						}
						repeat = true;
					}*/
					System.out.println("above row is " + row + " , column is " + col + ", and cell val is " + sudoku[row][col]);
					System.out.println(sudoku[row][col]);
					while(sudoku[row][col] != 0) {
						if(row == 8 && col == 8 && sudoku[row][col] != 0) {
							isSolved = true;
							break;
						}
						if(col != 8)
							col++;
						else {
							col = 0;
							row++;
						}			
						
						
					}
				
					System.out.println("row is " + row + " , column is " + col + ", and cell val is " + sudoku[row][col]);
					repeat = true;
					
					//recursive call
					return solveSudoku1(sudoku, row, col, isSolved, repeat);
				}else {
					//retry previous cell again
					//System.out.println("Backtrack");
					//System.out.println("inside cell is " + sudoku[row][col]);
					//System.out.println("inside row and col are [" + row + ", " + col + "]");
					
					///I think the issue is that the while loop is not being executed because 
					//the values are negative while the condition only looks for positive
					do{
						
						if(sudoku[row][col] < 0)
							sudoku[row][col] = 0;//maybe remove
						if(col != 0)
							col--;
						else {
							col = 8;
							row--;
						}			
					}while(sudoku[row][col] >= 0);
					
					//System.out.println("cell value is " + sudoku[row][col]);
					System.out.println("outside row and col are [" + row + ", " + col + "]");

					
					//recursive call
					return solveSudoku1(sudoku, row, col, isSolved, repeat);
				}

			}
		}

	
}
