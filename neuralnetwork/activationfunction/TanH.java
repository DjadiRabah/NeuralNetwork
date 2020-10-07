package neuralnetwork.activationfunction;

import java.math.BigDecimal;

public class TanH implements ActivationFunction {
	@Override
	public BigDecimal activate(BigDecimal x) {
		return new BigDecimal(Math.tanh(x.doubleValue()) + ""); 
	}

	@Override
	public BigDecimal dactivate(BigDecimal x) {
		BigDecimal tanH = activate(x);
		return new BigDecimal(1.0 + "").subtract(new BigDecimal(Math.pow(tanH.doubleValue(), 2)));
	}
}
