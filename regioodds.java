import java.io.*;
import java.util.*;
 // CSUN COMP 282  Spring 23 oddsSc.java
 //  Gets 3 integers m, n and k using Scanner utility
 //  prints odd numbers from m thru n formatted k
 //  odd numbers per line (assume n > m > 0 and k > 0).     
 //  Compiles and executes at the command prompt
 //  Author: D. Regio
  public class regioodds{
	
	//use prt for System.out to save typing
	PrintStream prt = System.out;
		
	//oddsSc(m, n, k) method
	//prints odd numbers from m to n formatted k numbers per line
	private void odds(int m, int n, int k){
		//declare variables
		int i, cnt = 0;

		prt.printf("\n\tOdd numbers from %3d thru %3d formatted %2d numbers per line.\n\t", m, n, k);

		if (m >= n){ // m should be < n
			prt.printf("Sorry, m should be < n.");
			return;
		} // end if
		
		if (m % 2 == 0) //check if m is not odd?
			m++;  //Make it odd
		// end if

		//for loop to print Odd numbers from m...n
		for (i = m; i <= n ; i+=2){
			// formatted print
			prt.printf("%5d", i);
			cnt ++;
			if (cnt % k == 0)
				prt.printf("\n\t");
		} // end for
	} // end odds

	// main program gets input using Scanner Utility
	public static void main(String args[]) throws Exception{
		// program Description
		System.out.printf("\n\tThis program gets 3 integers m, n, k from KB, next calls odds(m, n, k)\n\tto print odd numbers from m thru n, formatted k odd numbers per line\n\t"+
		"Use followings to compile and execute at the command prompt:"+
		"\n\t\tTo Compile: javac  oddsSc.java"+
		"\n\t\tTo Execute: java   oddsSc");
	
		// declare variables
		int m, n, k;

		//create an instance of a class
		regioodds g = new regioodds();
		
		try{
			// open inf file to read input from KB
			Scanner inf = new Scanner(System.in);
			
			System.out.printf("\n\n\tPlease enter values of m, n and k.\n\tAssume n > m > 0 and k > 0: ");

			//read m, n, k
			m = inf.nextInt(); 					
			n = inf.nextInt(); 					
			k = inf.nextInt();
			
			// close inf file
			inf.close(); 
			
//It’s a good practice to print input in program output!
			// formatted print
			System.out.printf("\n\tINPUT: m = %3d, n = %4d, k = %4d", m, n, k);
			
			// Call oddsSc Method		
			g.odds(m, n, k); 
		}catch (Exception e){System.out.printf("\n\tRead Error! %s",e);}
		
//Please replace G. Dastghaibyfard with your name in line 83		
		System.out.printf("\n\n\tAuthor: D. Regio Date: %s\n",
		java.time.LocalDate.now()); 
	} // end main
}// end class oddsSc