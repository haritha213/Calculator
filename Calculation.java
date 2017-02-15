package MyCal;

import java.util.ArrayList;

public class Calculation implements Operations {

	@Override
	public void readInput(String input) {
		validateInput(input);
		ArrayList<Integer> operator = new ArrayList<>();
		ArrayList<Double> digits = new ArrayList<>();

		String temp = "";
		char character = 0;
		for (int i = 0; i < input.length(); i++) {
			character = input.charAt(i);
			if ((character == '(') || (character == ')'))
				continue;
			if (character != '+' && character != '-' && character != '*' && character != '/')
				temp = temp + character;
			else {
				digits.add(Double.parseDouble(temp));
				operator.add((int)character);
				temp = "";
			}
		}
		digits.add(Double.parseDouble(temp));
		orderOfOperations(operator, digits);
	}

	private void validateInput(String input) {

		if (input.isEmpty())
			throw new IllegalArgumentException("Enter valid input");

		input = input.trim(); 
		input = input.replace("\r\n", " ").replace("\n", " ");

		char[] charArr = input.toCharArray();
		char ch = input.charAt(0);

		if (!Character.isDigit(charArr[0]))
			throw new IllegalArgumentException("Cannot Start an Expression with " + charArr[0]);

		if (!Character.isDigit(ch) && ch != '*' && ch != '/' && ch != '+' && ch != '-')
			throw new IllegalArgumentException("Character Invalid: " + ch);
	}

	@Override
	public Double addition(Double val1, Double val2) {

		return val2 + val1;
	}

	@Override
	public Double subtraction(Double val1, Double val2) {
		return val2 - val1;
	}

	@Override
	public Double multiplication(Double val1, Double val2) {
		return val2 * val1;
	}

	@Override
	public Double division(Double val1, Double val2) {
		if (val2.equals(0))
			throw new IllegalArgumentException("Divided by Zero");
		return val2 / val1;
	}

	public void orderOfOperations(ArrayList<Integer> op, ArrayList<Double> val) {

		ArrayList<Integer> operatorTemp = new ArrayList<Integer>();
		ArrayList<Double> digitTemp = new ArrayList<Double>();

		char operators[] = {'/','*','+', '-'};
		for (int i = 0; i <= 3; i++)
		{
			boolean iterator = false;
			while (!op.isEmpty())
			{
				int optr = op.remove(op.size()-1);
				double v1 = val.remove(val.size()-1);
				double v2 = val.remove(val.size()-1);
				if (optr == operators[i])
				{
					if (i == 0)
					{
						Double div = division(v1, v2);
						digitTemp.add(div);
						iterator = true;
						break;
					}
					else if (i == 1)
					{
						Double mul = multiplication(v1, v2);
						digitTemp.add(mul);
						iterator = true;
						break;
					}
					else if (i == 2)
					{
						Double addition = addition(v1, v2);
						digitTemp.add(addition);
						iterator = true;
						break;
					}
					else if (i == 3)
					{
						Double subtract = subtraction(v1, v2);
						digitTemp.add(subtract);
						iterator = true;
						break;
					}
				}
				else
				{
					digitTemp.add(v1);
					val.add(v2);
					operatorTemp.add(optr);
				}                
			}        
			while (digitTemp.size() != 0)
				val.add(digitTemp.remove(digitTemp.size()-1));
			while (operatorTemp.size() != 0)
				op.add(operatorTemp.remove(operatorTemp.size()-1));
			if (iterator)
				i--;                            
		}    
		System.out.println("\nCalculated Result = "+val.get(val.size()-1));        
	}
}
