package data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import Exceptions.InvalidMatrixFormatException;
import nn.functions.MathFunction;
import nn.functions.SigmoidActivation;

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
		values = matrix;
	}
	
	public Matrix transpose()  throws InvalidMatrixFormatException {
		double[][] tValues = new double[col][row];
		for (int i=0; i<row; i++) {
			for (int j=0; j<col; j++) {
				tValues[j][i] = values[i][j];
			}
		}
		return new Matrix(tValues);
	}
	
	private void initFields(int row, int col) throws InvalidMatrixFormatException {
		this.row = row;
		this.col = col;
		if (row <= 0 || col <= 0) {
			throw new InvalidMatrixFormatException(row, col);
		}
		values = new double[this.row][this.col];
	}
		
	public void apply(MathFunction function) {
		for (int i=0; i<values.length; i++) {
			double[] rowData = values[i];
			for (int j=0; j<rowData.length; j++) {
				values[i][j] = function.apply(values[i][j]);
			}
		}
	}
	
	public Matrix randomRowBatch(int batchSize) throws InvalidMatrixFormatException {
		Set<Integer> rowIdxSet = new HashSet<Integer>();
		int size = batchSize <= row ? batchSize : row;
		while (rowIdxSet.size() < size) {
			rowIdxSet.add((int)(Math.random()*row));
		}
		double[][] tValues = new double[size][col];
		Iterator<Integer> it = rowIdxSet.iterator();
		int idx = 0;
		while (it.hasNext()) {
			int sampleIdx = it.next(); 
			tValues[idx] = new double[col];
			System.arraycopy(values[sampleIdx], 0, tValues[idx], 0, col);
			idx++;
		}
		return new Matrix(tValues);
	}
	
	
	public void copyValues(double[][] matrix) throws InvalidMatrixFormatException {
		this.copy(matrix);
	}
	
	private void copy(double[][] matrix) throws InvalidMatrixFormatException{
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("[%d ,%d] \n", row, col));
		builder.append("[\n");
		for (double[] r: values) {
			builder.append(Arrays.toString(r)).append("\n");			
		}
		builder.append("]\n");
		return builder.toString();
	}
	
	public static void main(String[] args) {
		try {
			double[][] m = new double[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}, {13, 14, 15}};
			Matrix a = new Matrix(m);
			System.out.println(a.toString());
			System.out.println(a.transpose().toString());
			System.out.println(a.randomRowBatch(3).toString());
			a.apply(new SigmoidActivation());
			System.out.println(a.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
