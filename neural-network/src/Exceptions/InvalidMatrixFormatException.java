package Exceptions;

import util.Messages;

public class InvalidMatrixFormatException extends Exception {
	public InvalidMatrixFormatException(int row, int col) {
		super(Messages.getMessage(String.format("The matrix dimension: (%d, %d) is invalid", row, col)));
	}
	
	public InvalidMatrixFormatException(int row1, int col1, int row2, int col2) {
		super(Messages.getMessage(String.format("The matrix dimensions don't match. (%d, %d) vs (%d, %d)", row1, col1, row2, col2)));
	}	
}
