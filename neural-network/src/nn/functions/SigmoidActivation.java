package nn.functions;

public class SigmoidActivation implements MathFunction {
	@Override
	public double apply(double input) {
		return 1 / (1 + Math.exp(-input));
	}
}
