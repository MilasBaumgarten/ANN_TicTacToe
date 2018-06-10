package ann;

import ann.math.IActivationFunction;

public class OutputNeuron extends Neuron{

	public OutputNeuron(IActivationFunction activFunc, int layer) {
		super(activFunc, layer);
	}
	
	public OutputNeuron(OutputNeuron neuron) {
		super(neuron.activationFunction, neuron.layer);
	}
	
	public void display(){
		calcOutput();
		
		System.out.println(outputBeforeActivation);
		System.out.println(output + "\n");
	}
}
