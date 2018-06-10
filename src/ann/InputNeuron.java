package ann;

import ann.math.IActivationFunction;

public class InputNeuron extends Neuron {

	public InputNeuron(double value, IActivationFunction activFunc, int layer) {
		super(activFunc, layer);
		super.output = super.outputBeforeActivation = value;
		super.calculated = true;
	}
	
	public InputNeuron(InputNeuron neuron) {
		super(neuron.activationFunction, neuron.layer);
	}
	
	public void setValue(double x){
		super.output = super.outputBeforeActivation = x;
		super.calculated = true;
	}
}