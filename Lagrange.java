/*
 * Lagrange.java
 *
 * Computer Science E-22
 */

import java.util.*;

/**
 * An application that reads positive integers from the user and
 * breaks them into sums of at most four positive perfect squares.
 */
public class Lagrange {
    private int number;      // the number we are trying to break up
    private int values[];
    /* Put any additional fields below. */
    

    /*
     * largestSquare - a private helper method that returns the 
     * largest perfect square less than or equal to the specified 
     * positive integer n.
     */
    private static int largestSquare(int n) {
        int sqrt = (int)Math.sqrt(n);
        return sqrt * sqrt;
    }

    /**
     * constructor
     */
    public Lagrange(int num) {
        number = num;
        values = new int[4];
    }

    /**
     * printSolution - print the solution to the problem
     */
    public void printSolution() {
        System.out.print(number + " = ");
        for(int i = 3; i >= 0; i--){
        	System.out.print(values[i]);
        	if (i ==0 || values[i-1] == 0)break;
        	System.out.print(" + ");
        }    
    }
    
    /**
     * findSum - the key recursive-backtracking method.
     * We call it to break the specified number (num) into a sum 
     * of at most maxTerms perfect squares.  
     * Returns true if the solution has been found and false otherwise.
     * 
     * NOTE: If you choose to modify the signature of this method,
     * you must also change the code in the main method that
     * uses the method.
     */
    public boolean findSum(int num, int maxTerms) {
    	
        	if ((num > 0 && maxTerms == 0) || (num < 4 && maxTerms < num))return false;	//Backtrack
        	
        	if (num == 0 && maxTerms >= 0) return true;		//Base case for recursion
        	
        	values[maxTerms-1] = largestSquare(num);
        	int tempTerms = maxTerms -1;
        	int diff = (num - values[maxTerms-1]);
        	
        	while (!findSum(diff, tempTerms)){
        		if (values[maxTerms-1] == 1) return false;				//Exhausted all available options
        		values[maxTerms-1] = largestSquare(values[maxTerms-1]-1);
        		diff = (num - values[maxTerms-1]);
        		}
        		
        	return true;
    }

    

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a positive integer (-1 to quit): ");
            int n = console.nextInt();
            console.nextLine();
            
            if (n == -1) {
                System.out.println("Goodbye!");
                return;
            } else if (n <= 0)
                continue;
            
         //   System.out.println(largestSquare(n));
            Lagrange problem = new Lagrange(n);
            
            if (problem.findSum(n, 4)) {
                problem.printSolution();
            } else {
                System.out.println("could not find a sum for " + n);
                System.out.println();
             }
            System.out.println();
        }
    }
}

