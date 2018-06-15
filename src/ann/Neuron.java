package ann;

import java.util.ArrayList;

import ann.math.*;

public class Neuron {
	public ArrayList<Double> input = new ArrayList<Double>();
	protected double outputBeforeActivation;
	protected double output;
	private double bias = 1.0;
	protected IActivationFunction activationFunction;
	protected int layer;
	
	public boolean calculated = false;
	
	public Neuron(IActivationFunction activFunc, int layer){
		this.layer = layer;
		activationFunction = activFunc;
	}
	
	protected void calcOutput(){
		calculated = true;
		outputBeforeActivation = 0.0;
		
		for (Double in : input) {
			outputBeforeActivation += in;
		}
		
		output = activationFunction.calc(outputBeforeActivation);
	}
	
	public double getOutput(){
		return output;
	}
	
	public double getOutputBeforeActivation(){
		return outputBeforeActivation;
	}
}