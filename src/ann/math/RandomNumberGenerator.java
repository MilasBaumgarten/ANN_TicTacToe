package ann.math;

import java.util.Random;

public class RandomNumberGenerator {
	public static double getWeight(){
		Random rand = new Random();
		return rand.nextDouble();
	}
}
