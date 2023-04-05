import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
public class AVLImplementation 
{
	public static int height(AVL root)
	{
		if(root==null)
			return -1;
	    return root.height;
	}
	public static AVL newNode(int key)
	{
		AVL node=new AVL(key,0);
		node.left=null;
		node.right=null;
		return node;
	}
	public static AVL rightRotate(AVL x)
	{
		AVL y=x.left;
		AVL temp1=y.right;
		y.setRight(x);
		x.setLeft(temp1);
		int h1=Integer.max(height(y.right),height(y.left))+1;
		int h2=Integer.max(height(x.right),height(x.left))+1;
		x.setHeight(h2);
		y.setHeight(h1);
		return y;
	}
	public static AVL leftRotate(AVL x)
	{
		AVL y=x.right;
		AVL temp2=y.left;
		y.setLeft(x);
		x.setRight(temp2);
		int h1=Integer.max(height(y.right),height(y.left))+1;
		int h2=Integer.max(height(x.right),height(x.left))+1;
		x.setHeight(h2);
		y.setHeight(h1);
		return y;
	}
	public static int getBalance(AVL root)
	{
		if(root==null)
			return 0;
		return(height(root.left)-height(root.right));
	}
	public static AVL insertNode(AVL tree,int key)
	{
		if(tree==null)
			return newNode(key);
		else if(key<tree.key)
			tree.setLeft(insertNode(tree.left,key));
		else if(key>tree.key)
			tree.setRight(insertNode(tree.right,key));
		else 
			return tree;
		int height=Integer.max(height(tree.left),height(tree.right))+1;
		tree.setHeight(height);
		int heightDiff=getBalance(tree);
		if(heightDiff > 1 && key < tree.left.key) //
			return rightRotate(tree);
		else if(heightDiff>1 && key > tree.left.key) //
		{
		   tree.setLeft(leftRotate(tree.left));
		   return rightRotate(tree);
		}
		else if(heightDiff < -1 && key < tree.right.key) //
		{
			tree.setRight(rightRotate(tree));
			return leftRotate(tree);
		}
		else if(heightDiff < -1 && key > tree.right.key) //
			return leftRotate(tree);
		return tree;
	}
	public static AVL minValueNode(AVL node)
	{
		AVL temp=node;
		while(temp.left!=null)
			temp=temp.left;
		return temp;
	}
	public static AVL deleteNode(AVL root,int key)
	{
		if(root == null)
			return root;
		if(key < root.key)
			root.setLeft(deleteNode(root.left,key));
		else if(key > root.key)
			root.setRight(deleteNode(root.right,key));
		else
		{
			if(root.left==null || root.right==null)
			{
				AVL temp=(root.left!=null)?root.left:root.right;
				if(temp==null)
				{
					temp=root;
					root=null;
				}
				else
					root=temp;
			}
			else
			{
				AVL temp=minValueNode(root.right);
				root.setKey(temp.key);
				root.setRight(deleteNode(root.right,temp.key));
			}
		}
		if(root==null)
			return root;
		int height=Integer.max(height(root.left),height(root.right))+1;
		root.setHeight(height);
		int heightDiff=getBalance(root);
		if(heightDiff > 1 && getBalance(root.left)>=0)
			return rightRotate(root);
		else if(heightDiff > 1 && getBalance(root.left)<0)
		{
			root.setLeft(leftRotate(root.left));
			return rightRotate(root);
		}
		else if(heightDiff<-1 && getBalance(root.right)<=0)
			return leftRotate(root);
		else if(heightDiff<-1 && getBalance(root.right)>0)
		{
			root.setRight(rightRotate(root.right));
			return leftRotate(root);
		}
		return root;		
	}
	public static void printLevelOrder(AVL root)
	{
		int height=height(root);
		for(int i=0;i<=height;i++)
		{
			printLevel(root,i);
			System.out.println();
		}
	}
	public static void printLevel(AVL root,int level)
	{
		if(root==null)
			return;
		if(level==0)
			System.out.print(root.key+" ");
		else
		{
			printLevel(root.left,level-1);
			printLevel(root.right,level-1);
		}
	}
	public static boolean search(AVL root,int key)
	{
		if(root==null)
			return false;
		if(key==root.key)
			return true;
		if(key > root.key)
			return search(root.right,key);
		else
			return search(root.left,key);
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException 
	{
//		Scanner sc=new Scanner(System.in);
//		AVL TREE=null;
//		while(true)
//		{
//			System.out.println("1)Insert Data");
//			System.out.println("2)Search for a data");
//			System.out.println("3)Delete a Data");
//			System.out.println("4)Print Tree Structure");
//			System.out.println("5)Exit");
//			System.out.println();
//			System.out.println("Enter your choice : ");
//			int choice=sc.nextInt();
//			switch(choice)
//			{
//				case 1:
//					System.out.print("Enter the number of data to be inserted : ");
//					int count=sc.nextInt();
//					for(int i=0;i<count;i++)
//						TREE=insertNode(TREE,sc.nextInt());
//					System.out.println("Values Inserted\n");
//					break;
//				case 2:
//					System.out.println("Enter the value to be searched : ");
//					if(search(TREE,sc.nextInt()))
//						System.out.println("The data is found in tree structre \n");
//					else
//						System.out.println("The data is not found in tree structrure\n");
//					break;
//				case 3:
//					System.out.println("Enter the value to be deleted : ");
//					TREE=deleteNode(TREE,sc.nextInt());
//					System.out.println("The entered data was deleted from the tree strudcture\n");
//					break;
//				case 4:
//					printLevelOrder(TREE);
//					break;
//				case 5:
//					sc.close();
//					System.out.println("Exitting...");
//					break;
//				default:
//					System.out.println("Invalid Choice, Please enter a valid choice\n");
////			}
		AVL Tree=null;
//		FileOutputStream fileOutput=new FileOutputStream("C:\\\\Users\\\\admin\\\\eclipse-workspace\\\\DSAProject\\\\TreeFile");
//		ObjectOutputStream objectOutput=new ObjectOutputStream(fileOutput);
//		objectOutput.writeObject(Tree);
//		objectOutput.close();
		FileInputStream fileInput=new FileInputStream("C:\\Users\\admin\\eclipse-workspace\\DSAProject\\TreeFile");
		ObjectInputStream objectInput=new ObjectInputStream(fileInput);
		Tree=(AVL)objectInput.readObject();
		printLevelOrder(Tree);
		objectInput.close();
		}
	}
