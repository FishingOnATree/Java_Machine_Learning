package data;

import java.util.Arrays;

import Exceptions.InvalidMatrixFormatException;

public class Matrix {
	private int row;
	private int col;
	private double[][] values;
	
	public Matrix(int row, int col) throws InvalidMatrixFormatException {
		initFields(row, col);
	}

	public Matrix(int row) throws InvalidMatrixFormatException {
		this(row, 1);
	}
	
	public Matrix(double[][] matrix) throws InvalidMatrixFormatException {
		this(matrix.length, matrix[0].length);
		this.setValues(matrix);
	}
	
	private void initFields(int row, int col) throws InvalidMatrixFormatException {
		this.row = row;
		this.col = col;
		if (row <= 0 || col <= 0) {
			throw new InvalidMatrixFormatException(row, col);
		}
		values = new double[this.row][this.col];
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[\n");
		for (double[] r: values) {
			builder.append(Arrays.toString(r)).append("\n");			
		}
		builder.append("]\n");
		return builder.toString();
	}
	
	public void setValues(double[][] matrix) throws InvalidMatrixFormatException {
		this.copyValues(matrix);
	}
	
	private void copyValues(double[][] matrix) throws InvalidMatrixFormatException{
		if (values == null) {
			values = new double[matrix.length][matrix[0].length];
		}
		if (values.length != matrix.length || values[0].length != matrix[0].length) {
			throw new InvalidMatrixFormatException(values.length, values[0].length, matrix.length, matrix[0].length);
		}
		for (int i=0; i< matrix.length; i++) {
			System.arraycopy(matrix[i], 0, values[i], 0, matrix[i].length);
		}
	}

	public static void main(String[] args) {
		try {
			double[][] m = new double[][] {{1, 2, 3}, {2, 3, 4}, {3, 4, 5}};
			Matrix a = new Matrix(m);
			System.out.println(a.toString());
			m[0][0] = 0;
			System.out.println(a.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
