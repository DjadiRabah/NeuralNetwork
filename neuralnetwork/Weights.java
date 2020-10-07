package neuralnetwork;
import java.math.BigDecimal;

import neuralnetwork.activationfunction.ActivationFunction;
import neuralnetwork.activationfunction.Sigmoid;
import neuralnetwork.math.Matrix;

public class Weights {
	protected Matrix weights;
	protected Matrix bias;
	protected Matrix inputs;
	protected Matrix sum;
	protected Matrix outputs;
	protected Matrix errors;
	protected ActivationFunction activationFunction;

	public Weights(int numberInputs, int numberOutputs) {
		this.weights = new Matrix(numberOutputs, numberInputs);
		this.weights.randomize(-0.1, 0.1);
		this.bias = new Matrix(numberOutputs, 1);
		this.bias.randomize(0.5, 1);
		this.activationFunction = new Sigmoid();
	}

	public Matrix predict(Matrix inputs) {
		this.inputs = inputs;
		this.outputs = this.activate(inputs);
		return this.outputs;
	}

	private Matrix activate(Matrix inputs) {
		Matrix sum = this.sum(inputs);
		BigDecimal[][] sumArray = sum.getMatrix();
		for (int i = 0; i < sumArray.length; i++) {
			sumArray[i][0] = activationFunction.activate(sumArray[i][0]);
		}

		return Matrix.fromArray(sumArray);
	}

	private Matrix dactivate(Matrix outputs) {
		Matrix doutputs = new Matrix(outputs);
		for (int i = 0; i < doutputs.getRow(); i++) {
			for (int j = 0; j < doutputs.getCol(); j++) {
				doutputs.setValue(i, j, activationFunction.dactivate(sum.getValue(i, j)));
			}
		}
		return doutputs;
	}

	/**
	 * AX + B
	 * 
	 * @param inputs
	 * @return
	 */
	private Matrix sum(Matrix inputs) {
		sum = Matrix.add(Matrix.multiply(weights, inputs), bias);
		return sum;
	}


	public Matrix getMatrix() {
		return this.weights;
	}
	
	public void setErrors(Matrix errors)
	{
		this.errors = Matrix.multiply(Matrix.transpose(weights), errors);
	}
	
	public Matrix getErrors()
	{
		return this.errors;
	}
	
	public void correctWeights(Matrix errors, double learningRate)
	{
		Matrix gradient = getGradient(inputs, errors, learningRate);
		Matrix deltaWeights = Matrix.multiply(gradient, Matrix.transpose(inputs));
		this.weights = correctWeights(this.weights, deltaWeights);
		this.bias = correctBias(this.bias, gradient);
	}
	
	private Matrix getGradient(Matrix inputs, Matrix errors, double learningRate)
	{
		Matrix gradient = Matrix.multiply(errors, learningRate);
		gradient = Matrix.multiply(gradient, dactivate(outputs));
		return gradient;
	}
	
	private Matrix correctWeights(Matrix weights, Matrix deltaWeights)
	{
		return Matrix.add(weights, deltaWeights);
	}
	
	private Matrix correctBias(Matrix bias, Matrix deltaBias)
	{
		return Matrix.add(bias, deltaBias);
	}
}
