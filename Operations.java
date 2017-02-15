package MyCal;

import java.util.ArrayList;

public interface Operations {
	
	public Double addition(Double val1, Double val2);
	
	public Double subtraction(Double val1, Double val2);
	
	public Double multiplication(Double val1, Double val2);
	
	public Double division(Double val1, Double val2);
	
	public void orderOfOperations(ArrayList<Integer> op, ArrayList<Double> val);

	public void readInput(String input);
	

}
