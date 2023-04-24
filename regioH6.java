// CSUN Spring 22 COMP 282 - BST
// JAVA implementation of Binary Search Tree (BST)  
// Author: G. Dastghaiby Fard
import java.io.*; 
import java.util.*; 

public class regioH6{  
    //root is a pointer to root of the BST	
    node root = null; 
   
   // use prt for System.out to save typing
    PrintStream prt = System.out;
	
    // class node
    private class node {
		String wrd1, wrd2;
		node ll, rl;  //left and right link
		// node  constructor
		node(String w1, String w2) {
			wrd1 = w1; //key (data = x)
			wrd2 = w2; //value
			rl = ll = null;
		}  //end node constructor
    }  // end class node

	//inorder traversal of BST
	private void inorder(){
		if (root == null) return; //BST is empty
		prt.printf("\n\tInorder Traversal:");
		inorder(root);
	}// end inorder()

	//inorder traversal from node t
	private void inorder(node t){
		if (t == null) return; //BST is empty
		inorder (t.ll); //inorder left subtree
		//print content of root of subtree
		prt.printf ("(%s, %s) ", t.wrd1, t.wrd2); 
		inorder (t.rl); //inorder right subtree
	} //end inorder(t)

    // FOR INSERTION METHODS
    // you must insert both the word and its corresponding meaning. */
	//insert x in the BST
	private void insert(String w1, String w2){
		if (root == null){
			//Allocate space for root
			root = new node(w1, w2);
			return;
		}// end if 
		insert(w1, w2, root);
	} 	// end insert(w1, w2)

	//insert x in the BST from node t
	private void insert(String w1, String w2, node t){
		node tmp, parent = t; 
		// Allocate space and store x
		tmp = new node(w1, w2);
		// find parent node for x	  
		while (t != null) { 
			parent = t;   // save t in parent
			if (w1.compareTo(t.wrd1) <= 0) //if (w1.compareTo(t.wrd1) <= 0)
				t = t.ll;
			else	 
				t = t.rl;	
		} // end while
		
		//x should be inserted either to left or right of node parent 
		if (w1.compareTo(parent.wrd1) <= 0) 
			parent.ll = tmp;
		else 
			parent.rl = tmp;
		
		// end if
	}// end insert(x, t)

    //FOR SEARCH METHODS
    //you should get a word and return its corresponding meaning, if it exists.
	// search for w1 in the BST 
	private int search(String w1){
		// Search for in the BST 
		if (root == null){ //x not found 
			return 0;
		} // end if
		return search(w1, root);    
	} // end search (int x)
   
	// search for x in the BST from node t 
	// if found return 1
	private int search(String w1, node t){
		//complete the rest   
		while (t != null) { 
			if (w1.compareTo(t.wrd1) == 0) { //x is found 
				prt.printf("Found: %s", t.wrd2);
				return 1;
			} // end if
			if (w1.compareTo(t.wrd1) <= 0) 
				t = t.ll;
			else	 
				t = t.rl;
		} // end while
		return 0;  // x NOT found
	} // end search (x, t)

	// process method for BST insert and search 
	private void process(String fname){
		// local variables
		int j, n, k;
		String x, y; 

		prt.printf("\n\tHW6 program gets input file name from program argument, then reads:"+
		"\n\tinteger No. of elements to insert in BST "+
		"followed by elements to insert"+
		"\n\tNo. of elements to search followed by element to search"+
  		"\n\t\tTo compile: javac regioH6.java" +
		"\n\t\tTo execute: java  regioH6 inputfilename"); 	  
				  
		try{  
			// open input file
			Scanner inf = new Scanner(new File(fname)); 
						
			//read no. of elements to insert
			n = inf.nextInt();						
			prt.printf("\n\n\tInsert %d elements in the BST.", n);
			for(j = 1; j <= n; j++){
				x = inf.next();   // read x
				y = inf.next();	  // read y
				insert(x, y); //insert x in the BST
			} // end for
			//print inorder traversal
			inorder();
					
			//read no. of elements to search in BST
			n = inf.nextInt(); 
			prt.printf("\n\tSearch for %d elements in the BST: ", n);
			prt.printf("\t");
			for(j = 1; j <= n; j++){
				x = inf.next(); // read x to search
				prt.printf("\n\t\tSearch for %s in BST -> ", x);
				k = search(x);  // Search for x
				if (k == 0)
					prt.printf("NOT F ound");
				// end if
			}// end for	

			// close input file 		
			inf.close();   
		}catch (Exception e){prt.printf("\n\tRead Error! %s", e);}
		
	} // end process(fname)
  
  //  main method
	public static void main(String args[]) throws Exception{
		// declare variables
		int cnt = args.length; // get no. of atguments
		String inf;
	  
		if (cnt < 1){
		    System.out.printf("\n\tOOOPS Invalid No. of arguments!"+
			"\n\tTO Execute: java regioH6 inputfilename");
			return;
		} // end if

		// get input file name
		inf = args[0]; 
						
		System.out.printf("\n\tInput filename: %s", inf);
		
		// create an instance of BST class
		regioH6 t = new regioH6();

		// call process method
		t.process(inf); 

		//MAKE SURE TO WRITE YOUR NAME IN NEXT LINE		
		System.out.printf("\n\tAuthor: D. Regio Date: " +
		java.time.LocalDate.now()); 
   } // end main
} // end class BST