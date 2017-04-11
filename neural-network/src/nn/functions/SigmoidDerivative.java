package nn.functions;

public class SigmoidDerivative extends SigmoidActivation {
	@Override
	public double apply(double input) {
		double y = super.apply(input);
		return y * (1 -y);
	}

}
