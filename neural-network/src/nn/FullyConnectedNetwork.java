package nn;

import java.util.Random;

import Exceptions.InvalidMatrixFormatException;
import data.Matrix;

public class FullyConnectedNetwork {
	private int nInputNode;
	private int nOutputNode;
	private double learningRate;
	private int[] nHiddenNode;
	private Matrix[] weights;
	private Random r = new Random();
	private static final double WEIGHT_INIT_BOUNDRY = 0.1;
	

	public FullyConnectedNetwork(int numInput, int numOutput, int[] numHidden, double lr) throws InvalidMatrixFormatException {
		this.nInputNode = numInput;
		this.nOutputNode = numOutput;
		this.learningRate = lr;
		nHiddenNode = numHidden;
		this.initializeWeights();
	}
	
	private void initializeWeights() throws InvalidMatrixFormatException {
		weights = new Matrix[nHiddenNode.length+1];
		// level 0 is by input_featues x hidden_level
		double[][] weight0 = new double[nInputNode][nHiddenNode[0]];
		this.generaeGaussianValues(weight0);
		weights[0] = new Matrix(weight0);
		// hidden-layer to hidden-layer weights
		for (int i=1; i<nHiddenNode.length; i++) {
			double[][] weight = new double[nHiddenNode[i-1]][nHiddenNode[i]];
			this.generaeGaussianValues(weight);
			weights[i] = new Matrix(weight);
		}
		//hidden-to-output
		double[][] weightFinal = new double[nHiddenNode[nHiddenNode.length-1]][nOutputNode];
		this.generaeGaussianValues(weightFinal);
		weights[weights.length-1] = new Matrix(weightFinal);
		for (Matrix m: weights) {
			System.out.println(m.toString());
		}
	}
	
	private void generaeGaussianValues(double[][] weight) {
		for (int i=0; i<weight.length; i++) {
			for (int j=0; j<weight[0].length; j++) {
				weight[i][j] = r.nextGaussian() * WEIGHT_INIT_BOUNDRY;
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			FullyConnectedNetwork fn = new FullyConnectedNetwork(10, 2, new int[]{8, 4}, 0.001);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
