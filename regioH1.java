import java.io.*;
import java.util.*;
import java.util.Random;

public class regioH1
{
    //use prt for System.out to save typing
	PrintStream prt = System.out;
		
	//generate(n, a, b, k) method
	//prints n random integers from a to b formatted k numbers per line
	private void generate(int n, int a, int b, int k){
		//declare variables
		int i, cnt = 0;

        //instance of class Random
        Random rand = new Random();

		prt.printf("\n\tThis method prints %d random integers from %d thru %d formatted %d numbers per line.\n\t", n, a, b, k); 

		if (a >= b){ // a should be < b
			prt.printf("Sorry, a should be < b.");
			return;
		} // end if

		//for loop to print n random integers from a...b
		for (i = 1; i <= n ; i++){
            int x = rand.nextInt((b-a) + 1) + a;
			// formatted print
			prt.printf("%5d", x);
			cnt ++;
			if (cnt % k == 0)
				prt.printf("\n\t");
		} // end for
	} // end generate

    // main program gets input from program argument
    public static void main(String args[]) throws Exception
    {
        // declare variables
        int n, a, b, k, cnt;

        //create an instance of a class
        regioH1 g = new regioH1();
        
        //get no. of arguments              
        cnt = args.length;

        if (cnt < 4) {
            System.out.printf("\n\n\tOOPS Invalid No. of aguments! EXIT.\n");
            return;
        }
                          
        // get values of n, a, b and k from program argument
        n = Integer.parseInt(args[0]);
        a = Integer.parseInt(args[1]);
        b = Integer.parseInt(args[2]);                            
        k = Integer.parseInt(args[3]);                             

        //It’s a good practice to print input in program output!

        // formatted print
        System.out.printf("\n\tINPUT: n = %4d, a = %4d, b = %4d, k = %4d", n, a, b, k);

        // Call generate Method             
        g.generate(n, a, b, k);                   

        //Replaced G. Dastghaibyfard with 'D. Regio' in next line                   
        System.out.printf("\n\n\tAuthor: D. Regio Date: %s\n",
        java.time.LocalDate.now());

    } 
}// end main 