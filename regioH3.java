// CSUN COMP 282 Spring 23, Hwk-3  regioH3.java
// External Sorting - using Replacement Selection Sort
// Author: D. Regio

import java.util.*;
import java.io.*;

public class regioH3
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
		String fname;
		for (i = 1; i <= n; i++)
        {
			fname = "F" + i + ".txt";
			prtfile(fname);
		}
	} // end prtfiles	

	// SortRepSel, with arrays of size M
	private int SortRepSel (String fn, int M) {
        // assume integer inputs  
        int i = 1;   // output file no.
 
        try{
            // open input file 
            Scanner inf = new Scanner(new File(fn)); // open input file 
             
            // Allocate space for arr[] (minheap), NOTE: min heap index starts from 1 
            int arr[] = new int[M + 1]; // allocate array space 
            int j;
            
                // fill initial minheap from infile 
                for (j = 1;  j <=  M && inf.hasNext();  j++) 
                { 
                    // read next input from infile 
                    arr [j]  = inf.nextInt();  
                }   // endfor         
            
                int heapsize = --j;  //  no. of elements in minheap 
                
                //heapsort(arr, heapsize);
        
                //outer while
                while (heapsize > 0) 
                { // while more data exist 
                    // create ith temporary sorted output file name  
                    String fname = "F" + i + ".txt";

                    // open ith  temporary sorted output file 
                    PrintWriter opf = new PrintWriter(new File(fname)); 
         
                    //convert to minheap using heapdn 
                    for (int m = heapsize/2; m  > 0; m--){
                        minheapdn (arr, m, heapsize); 
                    } //end for 

                    // Initialize last written 
                    int lastwritten = Integer.MIN_VALUE;  // i.e. -∞ 
                    int inheap = 0;  // unprocessed elements 
         
                    // write arr[1] to ith  temporary sorted output file,  and read next data, 
                    // if end of file, shift arr[] element to left and reduce heapsize by 1 
                    // else read next input  
           
                    while (heapsize > 0) { // inner while 
                        // Check if root can be written to current output file 
                        if (arr [1] <  lastwritten) {   //root cannot be written to current file 
                            swap (arr, 1, heapsize); 
                            //tmp=arr[1];arr[1]=arr[heapsize];arr[heapsize]= tmp;  
                            heapsize--;  // reduce heap size by 1 
                            //increase unprocessed elements in heap 
                            inheap++;  
                        } // endif  
                        else { //end outer else 
                            // write min to ith  temporary sorted output file,  
                            opf.printf("%5d ", arr[1]); 
                            lastwritten = arr[1]; 

                            // if end of file shift elements to left in minheap 
                            if (!inf.hasNext()){ //  
                                for (j = 1; j < heapsize + inheap; j++){
                                    arr[j] = arr[j + 1]; 
                                }// end for 
                                heapsize--;  // reduce heap size by 1 
                            } // end if 
                            else { // inner else 
                                // read next input from infile 
                                arr [1]  = inf.nextInt();  
                            } // end inner else 
                        }  //end outer else 
            
                        //heapdn from root 
                        minheapdn(arr, 1, heapsize);   
                    } // end inner while 

                    opf.printf(" " + Integer.MAX_VALUE);
                    // close ith  temporary sorted output file 
                    opf.close();   // since no more can be added 
         
                    if (inheap == 0){ 
                        break;  // all data are processed 
                    }
                    heapsize = inheap;
                    i++;  // start another output file 
                } // end outer while 
            
            inf.close();  // close input file 
        } catch (Exception e){prt.printf("\n\tRead Error! %s", e);}
        
        return i;  // return No. of sorted files created 
    }   // end SortRepSel 

	private void swap(int arr[], int i, int j)
    {
		int tmp = arr[i];arr[i] = arr[j];arr[j] = tmp;
	} // end swap

    /**
	private void heapsort(int arr[], int n)
    {
		int m;
		// convert to maxheap
		for(m= n/2; m >= 1; m--) 
			minheapdn(arr, m, n);	
		//end for
		
		for(m= n; m > 1; m--)
        {
			swap(arr, 1, m); 
			minheapdn(arr, 1, m-1);
		} //end for
	} // end heapsort
    */
	
	// minheapdn(arr[], int m, int n)
	private void minheapdn(int arr[], int m, int n)
    {
        int j, tmp = arr[m]; 
        while (m * 2 <= n)
        {  // m has a left child  

            // index of left child of m
            j = 2 * m;  
            // check if j has a sibling 
            if (j + 1 <= n  &&  arr[j+1] < arr[j]){ // for maxheap if arr[j+1] > arr[j] 
                j++; 
            } // endif 
            if (tmp > arr[j])
            {  // for maxheap if tmp < arr[j] 
                arr[m] = arr[j]; 
                m = j;  // convert to max heap arr[j] thru arr[n],  
            } // endif 
            else 
                break; 
        } // end while 
        arr[m] = tmp;
	} // end minheapdn(int arr[], int m, int n)
	
   // Replication Selection Sort process method using array size M
   public void process(String fn, int M)
   { 
		int n;
		prt.print("\n\tExternal Sorting Java program that\n\t"+
		"gets array size and inputfilename from \n\t"+
		"program argument and creates one or more sorted files." +
		"\n\t\tTo compile: javac xxxxxH3.java" +
		"\n\t\tTo execute: java  xxxxxH3 M inputfilename"+
		"\n\t\t   Example: java  xxxxxH3 5 inputfilename");
		
		// Call SortRepSel
		n = SortRepSel(fn, M);

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
			"\n\tTO Execute: java xxxxxH3 5 input.txt");
			return;
		} // end if	
			
		// get largest array size
		M = Integer.parseInt(args[0]);
		
		// get input file name
		fn = args[1]; 
			
		System.out.printf("\n\tInput:%s, array size=%d", fn, M);
			
		// create an instance of xxxxxH3 class
		regioH3 srt = new regioH3();
		
		// call process method
		srt.process(fn, M); 
			
		//Replace ????????? with your name in next line
		System.out.printf("\n\n\tAuthor: David Regio Date: %s\n",
		java.time.LocalDate.now());
	} // end main
} // end regioH3