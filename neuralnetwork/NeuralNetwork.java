package neuralnetwork;
import java.util.ArrayList;
import java.util.List;

import neuralnetwork.math.Matrix;

public class NeuralNetwork {
	protected int numberInputs;
	protected int numberOutputs;
	private List<Weights> weights;
	protected double learningRate;

	public NeuralNetwork(int numberInputs, int numberOutputs) {
		learningRate = 0.01;
		this.numberInputs = numberInputs;
		this.numberOutputs = numberOutputs;
		weights = new ArrayList<>();
		weights.add(new Weights(numberInputs, numberOutputs));
	}
	
	public NeuralNetwork(int[] numberNeuronsLayers) {
		if(numberNeuronsLayers.length < 2)
			System.err.println("Class NeuralNetwork method Constructor : Il faut au moins 2 layers");
		learningRate = 0.01;
		numberInputs = numberNeuronsLayers[0];
		numberOutputs = numberNeuronsLayers[numberNeuronsLayers.length-1];
		weights = new ArrayList<>();
		for(int i = 0; i < numberNeuronsLayers.length - 1; i++)
		{
			weights.add(new Weights(numberNeuronsLayers[i], numberNeuronsLayers[i+1]));
		}
	}

	public void display() {
		for (int i = 0; i < weights.size(); i++) {
			Weights current = weights.get(i);
			System.out.println(current.weights);
		}
	}

	public void addLayer(int numberNeurons) {
		weights.remove(weights.size() - 1);
		if (weights.size() == 0) {
			weights.add(new Weights(numberInputs, numberNeurons));
		} else {
			weights.add(new Weights(getNumberInputsFromPreviousLayer(), numberNeurons));
		}
		weights.add(new Weights(getNumberInputsFromPreviousLayer(), numberOutputs));
	}

	private int getNumberInputsFromPreviousLayer() {
		return weights.get(weights.size() - 1).getMatrix().getRow();
	}

	public Matrix predict(Matrix inputs) {
		if (inputs.getRow() == this.numberInputs) {
			Matrix outputs = weights.get(0).predict(inputs);
			for (int i = 1; i < weights.size(); i++) {
				outputs = weights.get(i).predict(outputs);
			}
			return outputs;
		}
		System.err.println(String.format("Nombre attendu d'inputs : %d", this.numberInputs));
		return null;
	}

	private Matrix getErrors(Matrix guess, Matrix targets) {
		Matrix errors = new Matrix(targets.getRow(), 1);
		for (int i = 0; i < errors.getRow(); i++) {
			errors.setValue(i, 0, targets.getValue(i, 0).subtract(guess.getValue(i, 0)));
		}

		return errors;
	}

	public void train(Matrix inputs, Matrix targets) {
		Matrix guess = this.predict(inputs);
		Matrix errors = getErrors(guess, targets);

		if (Math.abs(errors.getValue(0, 0).doubleValue()) > 0.1) {
			/*
			 * CALCUL ERRORS
			 */
			weights.get(weights.size() - 1).setErrors(errors);
			for (int i = this.weights.size() - 2; i >= 0; i--) {
				weights.get(i).setErrors(weights.get(i + 1).getErrors());
			}

			/*
			 * CORRECT WEIGHTS
			 */
			Matrix currentErrors = errors;
			this.weights.get(this.weights.size() - 1).correctWeights(errors, learningRate);
			for (int i = this.weights.size() - 1; i >= 0; i--) {
				this.weights.get(i).correctWeights(currentErrors, learningRate);
				currentErrors = weights.get(i).getErrors();
			}
		}
	}
}
