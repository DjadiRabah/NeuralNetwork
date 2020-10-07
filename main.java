import java.util.ArrayList;
import java.util.List;

import neuralnetwork.Data;
import neuralnetwork.NeuralNetwork;

public class main {

	public main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) 
	{
		NeuralNetwork andNetwork = new NeuralNetwork(2, 1);
		
		List<Data> and = new ArrayList<>(); 
		and.add(new Data(new double[]{0, 0}, new double[]{0}));
		and.add(new Data(new double[]{0, 1}, new double[]{0}));
		and.add(new Data(new double[]{1, 0}, new double[]{0}));
		and.add(new Data(new double[]{1, 1}, new double[]{1}));
		
		for(int i = 0; i < 10000; i++)
		{
			for(Data current : and)
			{
				andNetwork.train(current.getInputs(), current.getTargets());
			}
		}
		
		System.out.println("AND");
		for(Data current : and)
		{
			String str = current.getInputs() + "" + andNetwork.predict(current.getInputs());
			System.out.println(str);
		}
		
		NeuralNetwork orNetwork = new NeuralNetwork(2, 1);
		
		List<Data> or = new ArrayList<>(); 
		or.add(new Data(new double[]{0, 0}, new double[]{0}));
		or.add(new Data(new double[]{0, 1}, new double[]{1}));
		or.add(new Data(new double[]{1, 0}, new double[]{1}));
		or.add(new Data(new double[]{1, 1}, new double[]{1}));
		
		for(int i = 0; i < 10000; i++)
		{
			for(Data current : or)
			{
				orNetwork.train(current.getInputs(), current.getTargets());
			}
		}
		
		System.out.println("OR");
		for(Data current : or)
		{
			String str = current.getInputs() + "" + orNetwork.predict(current.getInputs());
			System.out.println(str);
		}
	}

}
