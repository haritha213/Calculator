package MyCal;

import java.util.Scanner;

public class CalcMain {

	public static void main(String[] args) {
		Operations oper = new Calculation();
		Scanner sc = new Scanner(System.in); 
        System.out.println("Enter expression\n");
        String input = sc.next();
        
        oper.readInput(input);
	}
}
