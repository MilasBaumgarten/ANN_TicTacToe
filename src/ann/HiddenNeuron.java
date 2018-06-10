package ann;

import ann.math.IActivationFunction;

public class HiddenNeuron extends Neuron{

	public HiddenNeuron(IActivationFunction activFunc, int layer) {
		super(activFunc, layer);
	}
	
	public HiddenNeuron(HiddenNeuron neuron) {
		super(neuron.activationFunction, neuron.layer);
	}
}
