package ann;

import ann.math.Linear;

public class InputNeuron extends Neuron {

	public InputNeuron(double value, int layer) {
		super(new Linear(), layer);
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