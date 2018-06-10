package ann;

import java.util.ArrayList;

import ann.math.*;

public class Brain {
	private ArrayList<InputNeuron> inputNeurons = new ArrayList<InputNeuron>();
	private ArrayList<HiddenNeuron> hiddenNeurons = new ArrayList<HiddenNeuron>();
	private ArrayList<OutputNeuron> outputNeurons = new ArrayList<OutputNeuron>();
	private ArrayList<Connection> connections = new ArrayList<Connection>();
	
	private int hiddenNeuronsPerLayer;
	
	
	public Brain(int inputNeurons, int hiddenNeuronsPerLayer, int outputNeurons, int hiddenLayers, double inputValue, IActivationFunction hiddenActivationFunction, IActivationFunction outputActivationFunction){
		this.hiddenNeuronsPerLayer = hiddenNeuronsPerLayer;
		
		// create Neurons
		for (int i = 0; i < hiddenLayers + 1; i++){
			if (i == 0){
				createNeurons(new InputNeuron(inputValue, new Linear(), 0), inputNeurons);
			}
			else{
				createNeurons(new HiddenNeuron(hiddenActivationFunction, i), hiddenNeuronsPerLayer);
				
				if (i == hiddenLayers){
					createNeurons(new OutputNeuron(outputActivationFunction, i + 1), outputNeurons);
				}
			}
		}
			
		// create Connections
		createConnections();
	}
	
	private void createNeurons(InputNeuron neuron, int amount){
		for (int i = 0; i < amount; i++){
			inputNeurons.add(new InputNeuron(neuron));
		}
	}
	
	private void createNeurons(HiddenNeuron neuron, int amount){
		for (int i = 0; i < amount; i++){
			hiddenNeurons.add(new HiddenNeuron(neuron));
		}
	}
	
	private void createNeurons(OutputNeuron neuron, int amount){
		for (int i = 0; i < amount; i++){
			outputNeurons.add(new OutputNeuron(neuron));
		}
	}
	
	private void createConnections(){
		for (InputNeuron in : inputNeurons){
			for (int i = 0; i < hiddenNeuronsPerLayer; i++){
				connections.add(new Connection(in, hiddenNeurons.get(i), RandomNumberGenerator.getWeight()));
			}
		}
		
		for (int i = 0; i < hiddenNeurons.size() - hiddenNeuronsPerLayer; i += hiddenNeuronsPerLayer){
			for (int j = 0; j < hiddenNeuronsPerLayer; j++){
				for (int k = 0; k < hiddenNeuronsPerLayer; k++){
					connections.add(new Connection(hiddenNeurons.get(i + j), hiddenNeurons.get(i + k + hiddenNeuronsPerLayer), RandomNumberGenerator.getWeight()));
				}
			}
		}
		
		for (int i = hiddenNeurons.size() - hiddenNeuronsPerLayer; i < hiddenNeurons.size(); i++){
			for (OutputNeuron out : outputNeurons){
				connections.add(new Connection(hiddenNeurons.get(i), out, RandomNumberGenerator.getWeight()));
			}
		}
	}

	// call to run input values through ann
	public void transmit(){
		for (Connection con : connections){
			con.transmitSignal();
		}
	}
	
	public void setValues(double[] values){
		if (values.length != inputNeurons.size()){
			System.out.println("Number of Values is not equal to input neurons");
		}
		else{
			for (int i = 0; i < inputNeurons.size(); i++){
				inputNeurons.get(i).setValue(values[i]);
			}
		}
	}

	public void printOutput(){
		for (OutputNeuron neuron : outputNeurons){
			neuron.display();
		}
	}
	
}