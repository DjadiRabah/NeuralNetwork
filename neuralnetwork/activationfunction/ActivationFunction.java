package neuralnetwork.activationfunction;

import java.math.BigDecimal;

public interface ActivationFunction 
{
	public BigDecimal activate(BigDecimal x);
	public BigDecimal dactivate(BigDecimal x);
}
