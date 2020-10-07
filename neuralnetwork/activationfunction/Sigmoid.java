package neuralnetwork.activationfunction;

import java.math.BigDecimal;
import java.math.MathContext;

public class Sigmoid implements ActivationFunction {
	@Override
	public BigDecimal activate(BigDecimal x) {
		
		return new BigDecimal(1.0  + "").divide(new BigDecimal(1  + "").add(new BigDecimal(Math.pow(Math.E, -1 * x.doubleValue())  + "")),MathContext.DECIMAL128);
	}

	@Override
	public BigDecimal dactivate(BigDecimal x) {
		BigDecimal sigmoid = activate(x);
		return sigmoid.multiply(new BigDecimal(1  + "").subtract(sigmoid));
	}
}
