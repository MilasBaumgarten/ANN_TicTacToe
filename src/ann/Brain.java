package ann;

import java.util.ArrayList;
import java.util.Arrays;

import ann.math.*;
import tictactoe.ANNPlayer;
import tictactoe.Board;

public class Brain {
	private ArrayList<InputNeuron> inputNeurons = new ArrayList<InputNeuron>();
	private ArrayList<HiddenNeuron> hiddenNeurons = new ArrayList<HiddenNeuron>();
	private ArrayList<OutputNeuron> outputNeurons = new ArrayList<OutputNeuron>();
	private ArrayList<Connection> connections = new ArrayList<Connection>();
	
	public int hiddenNeuronAmount;
	public int inputNeuronAmount;
	public int outputNeuronAmount;
	public int hiddenLayers;
	public IActivationFunction hiddenActivationFunction;
	public IActivationFunction outputActivationFunction;
	
	
	
	public Brain(int inputNeuronAmount, int hiddenNeuronAmount, int outputNeuronAmount, int hiddenLayers, IActivationFunction hiddenActivationFunction, IActivationFunction outputActivationFunction){
		this.hiddenNeuronAmount = hiddenNeuronAmount;
		this.inputNeuronAmount = inputNeuronAmount;
		this.outputNeuronAmount = outputNeuronAmount;
		this.hiddenLayers = hiddenLayers;
		this.hiddenActivationFunction = hiddenActivationFunction;
		this.outputActivationFunction = outputActivationFunction;
		
		
		// create Neurons
		for (int i = 0; i < hiddenLayers + 1; i++){
			if (i == 0){
				createNeurons(new InputNeuron(0, 0), inputNeuronAmount);
			}
			else{
				createNeurons(new HiddenNeuron(hiddenActivationFunction, i), hiddenNeuronAmount);
				
				if (i == hiddenLayers){
					createNeurons(new OutputNeuron(outputActivationFunction, i + 1), outputNeuronAmount);
				}
			}
		}
			
		// create Connections
		createConnections();
	}
	
	public Brain(ANNPlayer player){
		this(player.getBrain().inputNeuronAmount, player.getBrain().hiddenNeuronAmount, player.getBrain().outputNeuronAmount, player.getBrain().hiddenLayers, player.getBrain().hiddenActivationFunction, player.getBrain().outputActivationFunction);
		setWeights(player.getBrain().connections);
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
			for (int i = 0; i < hiddenNeuronAmount; i++){
				connections.add(new Connection(in, hiddenNeurons.get(i), RandomNumberGenerator.getWeight()));
			}
		}
		
		for (int i = 0; i < hiddenNeurons.size() - hiddenNeuronAmount; i += hiddenNeuronAmount){
			for (int j = 0; j < hiddenNeuronAmount; j++){
				for (int k = 0; k < hiddenNeuronAmount; k++){
					connections.add(new Connection(hiddenNeurons.get(i + j), hiddenNeurons.get(i + k + hiddenNeuronAmount), RandomNumberGenerator.getWeight()));
				}
			}
		}
		
		for (int i = hiddenNeurons.size() - hiddenNeuronAmount; i < hiddenNeurons.size(); i++){
			for (OutputNeuron out : outputNeurons){
				connections.add(new Connection(hiddenNeurons.get(i), out, RandomNumberGenerator.getWeight()));
			}
		}
	}
	
	/**
	 * copy weights to connections
	 * 
	 * @param connections = ArrayList from which weights will be copied
	 */
	private void setWeights(ArrayList<Connection> connections){
		for (int i = 0; i < this.connections.size(); i++){
			this.connections.get(i).weight = connections.get(i).weight;
		}
	}

	// call to run input values through ann
	public void transmit(){
		for (Neuron neuron : hiddenNeurons){
			neuron.calculated = false;
		}
		
		for (Neuron neuron : outputNeurons){
			neuron.calculated = false;
		}
		
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
	
	public double[] getOutput(Board board){
		double[] output = new double[outputNeurons.size()];
		
		for (int i = 0; i < outputNeurons.size(); i ++){
			output[i] = outputNeurons.get(i).getOutput();
		}
		
		// clean output
		for (int x = 0; x < board.getSizeX(); x++){
			for (int y = 0; y < board.getSizeY(); y ++){
				if (board.get(x, y) != 0){
					output[x + board.getSizeX() * y] = 0;
				}
			}
		}
		//System.out.println(Arrays.toString(output));

		return output;
	}

	public void printOutput(){
		for (OutputNeuron neuron : outputNeurons){
			neuron.display();
		}
	}
	
	public ArrayList<Connection> getConnections(){
		return connections;
	}
	
}