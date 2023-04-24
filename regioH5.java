import java.util.*;
import java.io.*;
// CSUN COMP 282 Spring 23, regioH5.java
// Program to merge k sorted files in one sorted file.
// End of each inputfile is 2147483647(Integer.MAX_VALUE). 
// Author: D. Regio
public class regioH5 {

    //use prt for System.out to save typing
    PrintStream prt = System.out;

    // print file contents
    private void prtfile(String fn){

        //declare variables
        int i = 0, x;
        prt.printf("\n%s:", fn);
        try{
            Scanner inf = new Scanner(new File(fn)); 
            while (inf.hasNext()) {
                //read an input from fn
                i++;
                x = inf.nextInt();
                prt.printf("%4d", x);
                if(i % 20 == 0) 
                prt.printf("\n%s:", fn);
            } // endwhile
            prt.printf("(count=%d)", i);
            // close file
            inf.close();
        } catch (IOException e){
        prt.printf("\nOoops! Read Exception: %s", e);}
    } // end prtfile

    private void mergekway(int k) 
    {  // merge k sorted files F1, F2, ..., Fk into Fk + 1

        try{

            int j;
            // create array of scanner
            Scanner[] inf = new Scanner[k + 1];
        
            // open k sorted files
            for(j = 1; j <= k; j++){
                inf[j] = new Scanner(new File("F" + j + ".txt"));
            } //end for

            // open output file
            PrintWriter opf = new PrintWriter(new File("F" + j + ".txt"));
        
            // allocate array space for min heap(arr[][])
            int[][] arr = new int[k + 1][2]; //Note: minheap starts from position 1
            // Each minheap element has 2 parts one
            // for next input and one for file no.

            // fill initial minheap from input files
            for(j = 1; j <= k; j++){
                //read next input from inf[j]
                arr[j][0] = inf[j].nextInt();
                arr[j][1] = j; // store file number
            } // end for
            
            // heapsize is current no. of elements in minheap
            int heapsize = k; //initial heapsize

            //min heapdn from j downto 1
            for(j = heapsize / 2; j > 0; j--){
                minheapdn(arr, j, heapsize); //min heapdn from j thru heapsize
            } // end for

            while(heapsize > 0){
                // write arr[1] to the output file
                opf.printf("%5d ", arr[1][0]);

                // Store file number of min data in j
                j = arr[1][1];

                // if end of inf[j] move arr[heapsize]
                // to root and reduce heapsize by 1
                if(!inf[j].hasNext()){
                    arr[1] = arr[heapsize];
                    heapsize --;
                }
                else{ // still more data in inf[j]
                    
                    arr[1][0] = inf[j].nextInt(); // read next input from inf[j]
                    arr[1][1] = j; // store file no. Arr[1][1]
                } // end if

                //minheap down from 1 thru heapsize
                minheapdn(arr, 1, heapsize);
            } // end of while loop

            //Close all input files
            for(j = 1; j <= k; j++ ){
                inf[j].close();
            }// end for

            //close output file
            opf.close();

            //print final sorted file
            prtfile ("F" + j + ".txt");
        } catch (Exception e){prt.printf("\n\tRead Error! %s", e);}
    } // end k-way merge Algorithm 

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

    public static void main(String[] args) throws Exception{ //FIX MAIN METHOD TO ADJUST FOR -> mergekway method
        int cnt = args.length; // get no. of atguments
        int k;

        if (cnt < 1){
            System.out.printf("\n\tOOOPS Invalid No. of aguments!"+
            "\n\tTO Execute: java regioH5 k");
            return;
        } // end if

        // get input file name
        //fn1 = args[0]; 
        //fn2 = args[1]; 
        //fn3 = args[2]; 

        regioH5 srt = new regioH5();

        //Print input files
        srt.prtfile(k);

        //Call merge2way
        srt.mergekway(k);

        //Print output file
        srt.prtfile(k);

        //Replace ????????? with your name in next line
        System.out.printf("\n\n\tAuthor: David Regio Date: %s\n", 
        java.time.LocalDate.now());
    } // end main
} //end regioH5