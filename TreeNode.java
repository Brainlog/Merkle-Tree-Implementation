import Includes.*;
import java.lang.Math;
public class TreeNode{
	public TreeNode parent;
	public TreeNode left;
	public TreeNode right;
	public String val;
	public boolean isLeaf;
	public int numberLeaves;
	public String maxleafval;
	public String minleafval;
	public int balanceFactor;
	public int h;
		
	public TreeNode SingleLeftRotation(){
		//Implement your code here
		TreeNode c = new TreeNode();
		c = this.right.right;
		TreeNode b = new TreeNode();
		b = this.right.left;
		TreeNode a = new TreeNode();
		a = this.left;
		this.right = c;
		TreeNode p = new TreeNode();
		p.right = b;
		p.left = a;
		this.left = p;
		p.parent = this;
		a.parent = p;
		b.parent = p;
		c.parent = this;
		p.CRFupdate();
		this.CRFupdate();
		
		
		
		return this;
	}
	
	public TreeNode SingleRightRotation(){
		//Implement your code here
		TreeNode a = new TreeNode();
		TreeNode b = new TreeNode();
		TreeNode c = new TreeNode();
	    c = this.left.left;
		b = this.left.right;
		a = this.right;
		c.parent = this;
		TreeNode p =new TreeNode();
		p.right = a;
		p.left = b;
		this.left = c;
		this.right = p;
		a.parent = p;
		b.parent = p;
		p.parent = this;
		p.CRFupdate();
		this.CRFupdate();
		return this;
	}
	
	public TreeNode DoubleLeftRightRotation(){
		//Implement your code here
		TreeNode a = new TreeNode();
		TreeNode b = new TreeNode();
		TreeNode c = new TreeNode();
		TreeNode d = new TreeNode();
		TreeNode p1 = new TreeNode();
		TreeNode p2 = new TreeNode();
		a = this.left.left;
		b = this.left.right.left;
		c = this.left.right.right;
		d = this.right;
		p1.left = a;
		p1.right = b;
		a.parent = p1;
		b.parent = p1;
		p2.left = c;
		p2.right = d;
		c.parent = p2;
		d.parent = p2;
		p1.parent = this;
		p2.parent = this;
		this.left = p1;
		this.right = p2;
		p1.CRFupdate();
		p2.CRFupdate();
		this.CRFupdate();

		return this;
	}
	
	public TreeNode DoubleRightLeftRotation(){
		//Implement your code here
		TreeNode a = new TreeNode();
		a= this.left;
	
	
		TreeNode b = new TreeNode();
		b = this.right.left.left;
		TreeNode c= new TreeNode();
		c = this.right.left.right;
		TreeNode d = new TreeNode();
		d = this.right.right;
		TreeNode p1 = new TreeNode();
		TreeNode p2 = new TreeNode();
		p1.left = a;
		p1.right = b;
		a.parent = p1;
		b.parent = p1;
		p1.parent = this;
		
		p2.left = c;
		p2.right = d;
		c.parent = p2;
		d.parent = p2;
		p2.parent = this;
		this.left = p1;
		this.right = p2;
		p1.CRFupdate();
		p2.CRFupdate();
		this.CRFupdate();
		

		return this;
	}

	public String CRFupdate(){
		CRF obj = new CRF(64);
		if(this.isLeaf == true){
			return null;
		}
		this.val = obj.Fn(this.left.val + "#" + this.right.val);

		return null;

	}

	public void balanceUpdate(){
		TreeNode temp = new TreeNode();
		temp = this;
		while(temp.parent != null){
			temp.parent.h = Math.max(temp.parent.left.h,temp.parent.right.h) +1;
			temp.parent.balanceFactor = temp.parent.left.h - temp.parent.right.h;
			temp.minleafval = temp.left.minleafval;
			temp.maxleafval = temp.right.maxleafval;
			temp = temp.parent;
		}
		return;

	}

	public void rotationUpdate(){
		this.left.h = Math.max(this.left.left.h,this.left.right.h) + 1;
		this.right.h = Math.max(this.right.left.h,this.right.right.h) + 1;
		this.h = Math.max(this.left.h,this.right.h) + 1;
		this.left.balanceFactor = this.left.left.h - this.left.right.h;
		this.right.balanceFactor = this.right.left.h - this.right.right.h;
		this.balanceFactor = this.left.h - this.right.h;
		this.left.minleafval = this.left.left.minleafval; 
		this.left.maxleafval = this.left.right.maxleafval;
		this.right.maxleafval = this.right.right.maxleafval;
		this.right.minleafval = this.right.left.minleafval;
		this.minleafval = this.left.minleafval;
		this.maxleafval = this.right.maxleafval;
		return;
	}
}

