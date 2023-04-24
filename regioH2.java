// CSUN COMP 282 Spring 23, Hwk-2  regioH2.java
// External Sorting - using Normal Sort
// Author: D. Regio

import java.util.*;
import java.io.*;

public class regioH2
{
	
    //use prt for System.out to save typing	
	PrintStream prt = System.out;	
	
	// print file contents
	private void prtfile(String fn)
    {
		//declare variables
		int i = 0, x;
		prt.printf("\n\t%s:", fn);
    
	  try
      {
	    Scanner inf = new Scanner(new File(fn)); 
	    while (inf.hasNext()) 
        {
          //read an input from inf
		  x = inf.nextInt();
		  prt.printf("%4d ", x);
		  i++;
		  if(i % 16 == 0) 
			  prt.printf("\n\t%s:", fn);
	    } // endwhile

		prt.printf("(count=%d)", i);
		// close file
		inf.close();
      } catch (Exception e){prt.printf("\n\tRead Error! %s", e);}
	} // end prtfile

	// print file contents
	private void prtfiles(String fn, int n)
    {
		int i;
		prtfile(fn); // print input file
		String fname ;
		for (i = 1; i <= n; i++)
        {
			fname = "F" + i + ".txt";
			prtfile(fname);
		}
	} // end prtfiles	

	// SortNormal, with arrays of size M
	private int SortNormal(String fn, int M)
    { //regular sort
	  //Complete this Method
        // F: is input file and M: is largest array size that can be used: 
        // open input file

		// numbering the temporary sorted files 
        int i = 1;
		
		try{
        Scanner inf = new Scanner(new File(fn));

        // Allocate space for array of size M+1 
        int arr[] = new int [M+1];   // allocate array space, arr[0] is not used 
		int j = arr[M];

		//[...]
        while (inf.hasNext()) 
        { // while more input 
            // read  from input file 
            for (j = 1 ; j <= M && inf.hasNext() ; j++)
            {
                //read next integer input 
                arr[j] = inf.nextInt();   //arr[0] is not used 
            } // end for 
        
            int k = j - 1;  // k is no. of elements in arr[] 
            heapsort (arr, k);    // apply heapsort algorithm 

            // create ith temporary sorted output file name  
            String fname = "F" + i +".txt"; 

            // open ith temporary sorted output file 
            PrintWriter opf = new PrintWriter(new File(fname));

            //write arr[] to ith temporary sorted output file formatted 10 numbers per line 
            for (j = 1 ; j <= k; j++)
            { 
                opf.printf("%5d", arr[j]);   // print formatted 
                if (j %10 == 0) 
                    opf.printf("\n"); 
                // endif 
            }// end for 
			
			opf.printf(" " + Integer.MAX_VALUE);
            // close ith temporary sorted output file 
            opf.close();  

            // start next temporary sorted output file 
            // increment no. of sorted file
            i++;     
        } // end while 

        // close input file
        inf.close(); 
		} catch (Exception e){prt.printf("\n\tRead Error! %s", e);}
		
        // no. of created sorted files
        return i - 1;    
	} // end SortNormal

	private void swap(int arr[], int i, int j)
    {
		int tmp = arr[i];arr[i] = arr[j];arr[j] = tmp;
	} // end swap

	private void heapsort(int arr[], int n)
    {
		int m;
		// convert to maxheap
		for(m= n/2; m >= 1; m--) 
			maxheapdn(arr, m, n);	
		//end for
		
		for(m= n; m > 1; m--)
        {
			swap(arr, 1, m); 
			maxheapdn(arr, 1, m-1);
		} //end for
	} // end heapsort
	
	// maxheapdn(arr[], int m, int n)
	private void maxheapdn(int arr[], int m, int n)
    {
		//Complete this Method
        int j, tmp = arr[m]; 
        while (m * 2 <= n)
        {  // m has at left child  

            // index of left child of m
            j = 2 * m;  
            // check if j has a sibling 
            if (j + 1 <= n  &&  arr[j+1] < arr[j]) // for maxheap if arr[j+1] < arr[j] 
                j++; 
            // endif 
            if (tmp > arr[j])
            {  // for maxheap if tmp > arr[j] 
                arr[m] = arr[j]; 
                m = j;  // convert to max heap arr[j] thru arr[n], }  
            } // endif 
            else 
                break; 
        } // end while 
        arr[m] = tmp;
	} // end maxheapdn(int a[], int m, int n)
	
   // Normal sort process method using arrays size M
   public void process(String fn, int M)
   { 
		int n;
		prt.print("\n\tExternal Sorting Java program that:\n\t"+
		"gets array size and inputfilename from \n\t"+
		"program argument and creates one or more sorted files." +
		"\n\t\tTo compile: javac xxxxxH2.java" +
		"\n\t\tTo execute: java  xxxxxH2 M inputfilename"+
		"\n\t\t   Example: java  xxxxxH2 5 inputfilename");
		
		// Call SortNormal
		n = SortNormal(fn, M);

		//print input and output files
		prtfiles(fn, n);	    
   } //end process method
				
	public static void main(String[] args) throws Exception
    {
		//get no. of arguments
		int M, cnt = args.length; 
		String fn;
		if (cnt < 2)
        {
		    System.out.printf("\n\tOOOPS Invalid No. of arguments!"+
			"\n\tTO Execute: java xxxxxH2 5 input.txt");
			return;
		} // end if	
			
		// get largest array size
		M = Integer.parseInt(args[0]);
		
		// get input file name
		fn = args[1]; 
			
		System.out.printf("\n\tInput:%s, array size=%d", fn, M);
			
		// create an instance of xxxxxH2 class
		regioH2 srt = new regioH2();
		
		// call process method
		srt.process(fn, M); 
			
		//Replace ????????? with your name in next line
		System.out.printf("\n\n\tAuthor: David Regio Date: %s\n",
		java.time.LocalDate.now());
	}	// end main
} // end xxxxxH2