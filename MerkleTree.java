import Includes.*;
import java.util.*;
import java.lang.Math;

public class MerkleTree{
	//check TreeNode.java for more details
	public TreeNode rootnode;
	public int numdocs;
	

	public String InsertDocument(String document){
		numdocs = 0;
		//Implement your code here
		if(rootnode == null){
			
			TreeNode node = new TreeNode();
			node.val = document;
			node.h = 0;
			node.balanceFactor = 0;
			node.isLeaf = true;
			node.maxleafval = document;
			node.minleafval = document;
			rootnode = node;
			numdocs++;
		
		

			return rootnode.val;
			
		}
		else if(rootnode.isLeaf == true){
			TreeNode node = new TreeNode();
			node.val = document;
			node.maxleafval = document;
			node.minleafval = document;
			node.isLeaf = true;
			node.h = 0;
			node.balanceFactor = 0;
			TreeNode node2 = new TreeNode();
			if(document.compareTo(rootnode.val) > 0){
				node2.left = rootnode;
				node2.right = node;
				node2.h = 1;
				node2.balanceFactor = 0;
				node2.minleafval = rootnode.minleafval;
				node2.maxleafval = node.maxleafval;
				node2.isLeaf = false;

			}
			else{
				node2.left = node;
				node2.right = rootnode;
				node2.h = 1;
				node2.balanceFactor = 0;
				node2.isLeaf = false;
				node2.minleafval = node.minleafval;
				node2.maxleafval = rootnode.maxleafval;

			}
			
			node.parent = node2;
			rootnode.parent = node2;
			rootnode = node2;
			rootnode.CRFupdate();
			numdocs++;
			
			
		
			
			return rootnode.val;
			
		}
		else{
			TreeNode curr= new TreeNode();
			curr = rootnode;
			while(curr.isLeaf == false){
				if(document.compareTo(curr.right.minleafval) < 0){
					curr = curr.left;

				}
				else{
					curr = curr.right;
				}


			}

			TreeNode node  = new TreeNode();
			node.val = document;
			node.h = 0;
			node.balanceFactor = 0;
			node.isLeaf = true;
			node.minleafval = document;
			node.maxleafval = document;
			TreeNode node1 = new TreeNode();
			node1.val = curr.val;
			node1.isLeaf = true;
			node1.maxleafval = node1.val;
			node1.minleafval = node1.val;
			node1.balanceFactor = 0;
			node1.h = 0;
			node1.parent = curr;
			node.parent = curr;
			
			if(node.val.compareTo(node1.val) > 0){
				curr.left = node1;
				curr.right = node;
				curr.minleafval = node1.val;
				curr.maxleafval = node.val;

			}
			else{
				curr.left = node;
				curr.right = node1;
				curr.maxleafval = node1.val;
				curr.minleafval = node.val;

			}
			curr.isLeaf = false;
			curr.CRFupdate();
			// System.out.println(curr.left.val);
			// System.out.println(curr.right.val);
			// System.out.println(curr.val);

			curr.h = 1;
			curr.balanceFactor = 0;
			curr.isLeaf = false;
			TreeNode temp1 = new TreeNode();
			TreeNode temp2 = new TreeNode();
			temp1 = curr;
			temp2 = curr;
			// if(document == "Hello!"){
			// System.out.println(rootnode.left.val);
			// System.out.println(rootnode.right.left.val);
			// System.out.println(rootnode.right.right.val);
			// System.out.println(rootnode.right.val);
			// System.out.println(rootnode.right.left.left.val);
			// System.out.println(rootnode.right.left.right.val);
			
			

			// }
		

			while(curr.parent != null){
				
				curr.parent.h = Math.max(curr.parent.left.h,curr.parent.right.h) + 1;
				curr.parent.balanceFactor = curr.parent.left.h - curr.parent.right.h;
			  
				
				curr.parent.minleafval = curr.parent.left.minleafval;
				curr.parent.maxleafval = curr.parent.right.maxleafval;
				
				curr = curr.parent;

			}
			

			// System.out.println("\n\n\n" + "checking balance factor" + "\n" + rootnode.balanceFactor);
			// System.out.println(rootnode.right.balanceFactor);
			// System.out.println(temp1.balanceFactor);
			// System.out.println(temp1.parent.balanceFactor);
			// System.out.println(temp1.left.val);
			// System.out.println(temp1.right.val);
			
			


			while(temp1.parent != null){
				
				// System.out.println("dfyugafbajfnjh");
				temp1.parent.CRFupdate();
				// System.out.println(temp1.parent.balanceFactor);
				if(temp1.parent.balanceFactor == -2){
				
					// System.out.println("refhbsrtn styn");
					// System.out.println(temp1.parent.right.balanceFactor);
					if(temp1.parent.right.balanceFactor == -1){
						temp1.parent.SingleLeftRotation();
						temp1.parent.rotationUpdate();
						temp1.parent.balanceUpdate();
					
						
					
						// System.out.println("kdksdkd");
						
						

					}
					else if(temp1.parent.right.balanceFactor == 1){
						temp1.parent.DoubleRightLeftRotation();
						// System.out.println(temp1.parent.left.val);
						// TreeNode a = new TreeNode();					
						temp1.parent.rotationUpdate();
						temp1.parent.balanceUpdate();
					
						
						
						

					}
				}

				if(temp1.parent.balanceFactor == 2){
					
					if(temp1.parent.left.balanceFactor == 1){
						temp1.parent.SingleRightRotation();					
						temp1.parent.rotationUpdate();
						temp1.parent.balanceUpdate();
						
						// System.out.println("chc");
						
						

					}
					else if(temp1.parent.left.balanceFactor == -1){
						temp1.parent.DoubleLeftRightRotation();						
						temp1.parent.rotationUpdate();
						temp1.parent.balanceUpdate();
					
						


					}
				
				}	
				temp1 = temp1.parent;
			}


		}
		// System.out.println("helo");
		// if(document == "Hello!"){
		// 	System.out.println(rootnode.val);
		// 	System.out.println(rootnode.left.val + " " + rootnode.right.val);
		// 	System.out.println( rootnode.left.right.val + " " + rootnode.right.left.val + " " + rootnode.right.right.val + "\n");

			
		
		// }
	
		
        numdocs++;
		return rootnode.val;
	}
	
	public String DeleteDocument(String document){
		//Implement your code here
		return "";
	}
}


