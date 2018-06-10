import ann.Brain;
import ann.math.*;

public class Controller {
	public static void main(String[] args){
		
		Brain b = new Brain(9,3,9,3, 0, new Sigmoid(), new Sigmoid());
		//Brain b = new Brain(2,3,2,2, 0.5, new Sigmoid(), new Sigmoid());
		
		b.setValues(new double[] {0,1,0,
					 			  0,0,0,
					 			  0,1,1});
		
		b.transmit();
		b.printOutput();
			
	}
}