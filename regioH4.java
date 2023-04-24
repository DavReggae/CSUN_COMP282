import java.util.*;
import java.io.*;
// CSUN COMP 282 Spring 23, regioH4.java
// Program to merge 2 sorted files in one sorted file.
// End of each inputfile is 2147483647(Integer.MAX_VALUE). 
// Author: D. Regio
public class regioH4 {

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

    private void merge2way(String fn1, String fn2, String fn3) {  

        try{
        // open input files F1 and F2 
        Scanner F1 = new Scanner(new File(fn1)); 
        Scanner F2 = new Scanner(new File(fn2)); 
        
        // open output file F3 
        PrintWriter F3 = new PrintWriter(new File(fn3)); 
         
        //read first input from each file 
        int x = F1.nextInt();  
        int y = F2.nextInt(); 
        int max = Integer.MAX_VALUE; 
         
        while (true){ 
            if (x == max && y == max)  
                break; // exit while loop 
            if ( x <= y) {     
                F3.printf("%3d ", x);  // write x in output file F3
                x = F1.nextInt();      // read x from input file F1 
            }  // end if 
            else { 
                F3.printf("%3d ", y);  // write y in output file F3
                y = F2.nextInt();      // read y from input file F2 
            }  // end else 
        } // end while 
        
        //append F3 unprocessed data in F1
        /**
        while(!F1.hasNext())
        {
            F3.printf("#3d ", x); //write x in output file F3
            x = F1.nextInt();
        }  //endwhile 2

        //append F3 unprocessed data in F2
        while(!F2.hasNext())
        {
            F3.printf("%3d ", y);
            y = F2.nextInt();
        } //endwhile 3
        */
         
        //Close all I/O files.  
        F1.close(); 
        F2.close(); 
        F3.close(); 
        }catch (Exception e){prt.printf("\n\tRead Error! %s", e);}
    } // end merge2way(fn1, fn2, fn3)  

    public static void main(String[] args) throws Exception{
        int cnt = args.length; // get no. of atguments
        String fn1, fn2, fn3;

        if (cnt < 3){
            System.out.printf("\n\tOOOPS Invalid No. of aguments!"+
            "\n\tTO Execute: java xxxxxH4 f1 f2 f3");
            return;
        } // end if

        // get input file name
        fn1 = args[0]; 
        fn2 = args[1]; 
        fn3 = args[2]; 

        regioH4 srt = new regioH4();

        //Print input files
        srt.prtfile(fn1);
        srt.prtfile(fn2);

        //Call merge2way
        srt.merge2way(fn1, fn2, fn3);

        //Print output file
        srt.prtfile(fn3);

        //Replace ????????? with your name in next line
        System.out.printf("\n\n\tAuthor: David Regio Date: %s\n", 
        java.time.LocalDate.now());
    } // end main
} //end regioH4