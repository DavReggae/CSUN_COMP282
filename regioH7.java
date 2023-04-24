// CSUN Spring 22 COMP 282 - 2-3 Tree
// JAVA implementation of 2-3 Tree
// Author: G. Dastghaiby Fard
import java.io.*; 
import java.util.*; 

public class regioH7{  
    // root of 2-3 Tree (initially empty)	
    node root = null; 
   
   // use prt for System.out to save typing
    PrintStream prt = System.out;
	
    // class node
    private class node {
        int count;
        int data1, data2; //data1 and data2 can be any valid java type
        node link0, link1, link2, parent; // parent link is needed for root/node split
        // node constructor
        node(int x) {
            count = 1;
            data1 = x;
            link0 = link1 = link2 = parent = null;
        } // end node constructor
    } // end class node
 
    // Inorder traversal of 2-3 Tree
    private void inorder(){
        prt.printf("\n\tInorder Traversal of 2-3 tree:");
        inorder(root);
        prt.printf("\n");
    } // end inorder()

	// Inorder Traversal, 2-3 Tree starting from node t
    private void inorder(node t){
        if (t == null){
            return; // node t is null return
        }// end if
        // node t is not null
        inorder(t.link0); // inorder traverse of link0
        prt.printf("%3d, ", t.data1);
        inorder(t.link1); // inorder traverse of link1
        // node t has 2 data
        if (t.count == 2){
            prt.printf("%3d, ", t.data2);
            inorder(t.link2); // inorder traverse of link
        } // end if
    } // end inorder(node t)

    // INSERT x in 2-3 Tree with root t
    private void insert(int x){
        prt.printf("\n\t Insert %d ", x);
        if (root == null){ // insert x into empty 2-3 Tree
            root = new node(x);
            return;
        }// end if
        // 2-3 tree is not empty, first search for x from root
        node t = search(x, root);
        if (t == null){ // x is duplicate;
            prt.printf(" OOPS.. Duplicate...");
            return;
        } // endif
        // x is NOT duplicate insert it node t;
        insert(x, t);
    }

	// INSERT x into 2-3 Tree with root t from node t
    private void insert(int x, node t){
        node a = null;
        // insert x and a in node t
        for ( ; ; ){ // infinite loop
            if (t.count == 1){ // t has 1 data
                if (x < t.data1){ 
                    t.data2 = t.data1;
                    t.link2 = t.link1;
                    t.data1 = x;
                    t.link1 = a;
                }
                else { // x > data1
                    t.data2 = x;
                    t.link2 = a;
                }
                // increase count and update parent link of a
                t.count = 2;
                if (a != null)
                    a.parent = t; //very important
                }
                return;
             // end if (t.count == 1)
            //} // end for loop

            //3 | link0 | data1 | link1 | data2 | link2 | data3 | link3
            // Node SPLIT..... NOTE: THIS PART MAY EXECUTE MORE THAN ONCE!
                // * Write java code for this part *
                    // 3 cases should be considered for x:
                    // x < t.data1:
                    if(x < t.data1)
                        // middle element will be t.data1
                        t.link0 = x;
                        
                                            //t.data2 = a.data2;
                    // create a new node with t.data2, i.e., new_a = new node(t.data2);
                    a = new node(t.data2);
                    // x > t.data2
                    if(x > t.data2)
                        // middle element will be t.data2
                        a.link1 = t.parent.data1;
                        t.link0 = t.data2;
                        t = t.parent;

                                            // t.data2 = a.data1;
                                            //x = t.data2;
                    // create a new node with x, i.e., a = new node(x);
                    a = new node(x);
                    // data1 < x < t.data2
                    if(t.data1 < t.x < t.data2)
                        // middle element will be x
                        //t.data1 = x;
                    // create a new node with t.data2, i.e., a = new node(t.data2);
                    a = new node(t.data2);
                // SET X TO BE MIDDLE DATA

            // if node t is root perform ROOT SPLIT
            if (t == root){ // Remember x is middle data
                node newRoot = new node(x);
                newRoot.link0 = root;
                newRoot.link1 = a;
                root = newRoot;
                t.parent = root; //Very Important
                a.parent = root; //Very Important
            }        
            // end root split
            // Now insert X (middle data) and a (link of new created node) to parent node
            t = t.parent;
            return; 
        }// exit infinite loop
    } // end insert

    // *NEEDS TO BE SLIGHTLY MODIFIED*
	// SEARCH for x in the 2-3 Tree
	private int search(int x){
		// Search for in the BST 
		if (root == null){ //x not found 
			return 0;
		} // end if
		return search(x, root);    
	} // end search (int x)
   
	private node search(int x, node t){
        node parent = t; // save t
        while (t != null) { // tree is not empty;
            parent = t; // save t
            switch(t.count){
                // t has 2 children
                case 2: if (x == t.data1 || x == t.data2) // x is duplicate
                            return null;
                        if (x < t.data1)
                            t = t.link0;
                        else{
                            if (x > t.data2)
                                t = t.link2;
                            else
                                t = t.link1;
                            break;
                        }
                default:
                        if (x == t.data1) // node t has 1 data and x is duplicate
                            return null; 
                        if (x < t.data1) 
                            t = t.link0;
                        else 
                            t = t.link1;
            } // end switch(t.count)
        } // end while
        // x is not found, so x should be inserted in node parent
        return parent;
    }// end search

	// process method for BST insert and search 
	private void process(String fname){
		// local variables
		int j, n, k;
		String x, y; 

		prt.printf("\n\tHW7 program gets input file name from program argument, then reads:"+
		"\n\tinteger No. of elements to insert in BST "+
		"followed by elements to insert"+
		"\n\tNo. of elements to search followed by element to search"+
  		"\n\t\tTo compile: javac regioH7.java" +
		"\n\t\tTo execute: java  regioH7 inputfilename"); 	  
				  
		try{  
			// open input file
			Scanner inf = new Scanner(new File(fname)); 
						
			//read no. of elements to insert
			n = inf.nextInt();						
			prt.printf("\n\n\tInsert %d elements in the BST.", n);
			for(j = 1; j <= n; j++){
				x = inf.next();   // read x
				//y = inf.next();	  // read y
				insert(x); //insert x in the BST
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
					prt.printf("NOT Found");
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
			"\n\tTO Execute: java regioH7 inputfilename");
			return;
		} // end if

		// get input file name
		inf = args[0]; 
						
		System.out.printf("\n\tInput filename: %s", inf);
		
		// create an instance of BST class
		regioH7 t = new regioH7();

		// call process method
		t.process(inf); 

		//MAKE SURE TO WRITE YOUR NAME IN NEXT LINE		
		System.out.printf("\n\tAuthor: D. Regio Date: " +
		java.time.LocalDate.now()); 
   } // end main
} // end class BST