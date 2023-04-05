import java.io.Serializable;
public class AVL implements Serializable
{
	int key;
	int height;
	AVL left;
	AVL right;
	public AVL(int key, int height) {
		super();
		this.key = key;
		this.height = height;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public AVL getLeft() {
		return left;
	}
	public void setLeft(AVL left) {
		this.left = left;
	}
	public AVL getRight() {
		return right;
	}
	public void setRight(AVL right) {
		this.right = right;
	}	
}
