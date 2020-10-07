package neuralnetwork;
import java.math.BigDecimal;

import neuralnetwork.math.Matrix;

public class Data 
{
	protected Matrix inputs;
	protected Matrix targets;
	public Data(double[] inputs, double[] targets) 
	{
		this.inputs = arrayToMatrix(inputs);
		this.targets = arrayToMatrix(targets);
	}
	
	protected Matrix arrayToMatrix(double[] array)
	{
		Matrix matrix = new Matrix(array.length, 1);
		for(int i = 0; i < array.length; i++)
		{
			matrix.setValue(i, 0, new BigDecimal(array[i]));
		}
		return matrix;
	}
	
	public Matrix getInputs()
	{
		return inputs;
	}
	
	public Matrix getTargets()
	{
		return targets;
	}
}
