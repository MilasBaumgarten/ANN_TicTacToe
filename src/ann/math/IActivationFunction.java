package ann.math;

public interface IActivationFunction {
	public double calc(double x);
	
	public enum ActivationFunctionENUM{
		STEP, LINEAR, SIGMOID
	}

}
