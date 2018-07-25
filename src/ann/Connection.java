package ann;

public class Connection {
	public Neuron start;
	public Neuron end;
	public double weight;
	
	public Connection(Neuron start, Neuron end, double weight){
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
	
	public void transmitSignal(){
		if (!start.calculated){
			start.calcOutput();
		}
		end.input.add(start.getOutput() * weight);
	}
}
