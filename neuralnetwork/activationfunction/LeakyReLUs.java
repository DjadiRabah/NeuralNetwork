package neuralnetwork.activationfunction;

import java.math.BigDecimal;

public class LeakyReLUs implements ActivationFunction
{

	@Override
	public BigDecimal activate(BigDecimal x) {
		if(x.compareTo(new BigDecimal(0 + "")) == 1)
			return x;
		return x.multiply(new BigDecimal(0.01 + ""));
	}

	@Override
	public BigDecimal dactivate(BigDecimal x) {
		if (x.compareTo(new BigDecimal(0 + "")) == 1)
			return new BigDecimal(1 + "");
		
		return new BigDecimal(0 + "");
	}
	

}
