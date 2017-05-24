package wsj.structure.tree;

import java.util.Stack;
import java.util.concurrent.SynchronousQueue;

import javax.jws.WebParam.Mode;
import javax.naming.InitialContext;
import javax.swing.plaf.basic.BasicBorders.RolloverButtonBorder;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

public class BinaryTree {

	/**
	 * 数据节点.
	 * @author WSJ
	 *
	 * @param <T>
	 */
	public static class TreeNode<T> {
		
		private int index;
		
		private T data;
		private TreeNode<T> leftChild;
		private TreeNode<T> rightChild;
		
		public TreeNode(int index, T data) {
			this.index = index;
			this.data = data;
			leftChild = null;
			rightChild = null;
		}
		
		
	}
	
	
	private static TreeNode<String> root;
	
	/**
	 * 构造数
	 */
	public static void makeTree(){
		TreeNode<String> nodeA = new TreeNode<String>(0, "A");
		TreeNode<String> nodeB = new TreeNode<String>(0, "B");
		TreeNode<String> nodeC = new TreeNode<String>(0, "C");
		TreeNode<String> nodeD = new TreeNode<String>(0, "D");
		TreeNode<String> nodeE = new TreeNode<String>(0, "E");
		TreeNode<String> nodeF = new TreeNode<String>(0, "F");
		TreeNode<String> nodeG = new TreeNode<String>(0, "G");
		TreeNode<String> nodeH = new TreeNode<String>(0, "H");
		
		root = nodeA;
		
		root.leftChild = nodeB;
		root.rightChild = nodeC;
		
		nodeB.leftChild = nodeD;
		nodeB.rightChild = nodeE;
		
		nodeD.leftChild = nodeF;
		
		nodeC.leftChild = nodeG;
		nodeC.rightChild = nodeH;
		
	}
	
	/**
	 * 获取树深度/高度.
	 * @param root 根节点.
	 * @return
	 */
	public static int getTreeHeight(TreeNode<String> root) {
		
		if (root == null) {
			return 0;
		}else {
			int leftH = getTreeHeight(root.leftChild);
			int rightH = getTreeHeight(root.rightChild);
			
			return Math.max(leftH + 1, rightH + 1);
		}
	}
	/**
	 * 获取节点个数.
	 * @param root
	 * @return
	 */
	public static int getTreeSize(TreeNode<String> root) {
		if (root == null) {
			return 0;
		}else {
			// 当前节点不为空则节点总数增加 1.
			return getTreeSize(root.leftChild) + getTreeSize(root.rightChild) + 1;
		}
	}
	
	/**
	 * 先根遍历, 中 -> 左 -> 右; 迭代方法.
	 * @param root
	 */
	public static void preOrder(TreeNode<String> root) {
		if (root == null) {
			return ;
		}else {
			// 1. 根
			System.out.print(root.data);
			System.out.print(" ");
			// 2. 左
			preOrder(root.leftChild);
			// 3. 右
			preOrder(root.rightChild);
		}
	}
	
	/**
	 * 中根遍历 , 左 中 右 ; 迭代法
	 * @param root
	 */
	private static void midOrder(TreeNode<String> root) {
		if (root == null) {
			return;
		}else {
			// 1. 左
			midOrder(root.leftChild);
			// 2. 中
			System.out.print(root.data);
			System.out.print(" ");
			// 3. right
			midOrder(root.rightChild);
		}
	}
	/**
	 * 后根遍历, 左 右 中 ; 迭代
	 * @param root
	 */
	private static void afterOrder(TreeNode<String> root) {
		if (root == null) {
			return ;
		}else {
			// 1. 左
			afterOrder(root.leftChild);
			// 2. right
			afterOrder(root.rightChild);
			// 3. mid
			System.out.print(root.data);
			System.out.print(" ");
		}
	}
	
	
	/**
	 * 先根遍历, mid left right ; 非迭代法. 使用栈.
	 * @param root
	 */
	public static void preOrderNotIteration(TreeNode<String> root) {
		if (root == null) {
			return;
		}
		
		Stack<TreeNode<String>> stack = new Stack<>();
		stack.push(root);
		
		while(!stack.isEmpty()){
			// 1. mid
			TreeNode<String> midNode = stack.pop();
			System.out.print(midNode.data);
			System.out.print(" ");
			
			// 2. 右节点入栈, 由于栈是 先进后出 所以后将 左节点入栈,可以先出.
			if (midNode.rightChild != null) {
				stack.push(midNode.rightChild);
			}
			if (midNode.leftChild != null) {
				stack.push(midNode.leftChild);
			}
		}
	}
	// F D B E A G C H 
	// F D B E A G C H 
	/**
	 * 中序遍历, left mid right ; 非递归.
	 * @param root
	 */
	public static void midOrderNotIteration(TreeNode<String> root) {
		if (root == null) {
			return;
		}
		Stack<TreeNode<String>> stack = new Stack<>();
		TreeNode<String> node = root;
		
		while(node != null || !stack.isEmpty()){
			// 1. 如果左孩子不为空则将当前节点入栈.
			while(node.leftChild != null){
				stack.push(node);
				node = node.leftChild;
			}
			// 2. 测试node 的左孩子为空输出node
			System.out.print(node.data);
			System.out.print(" ");
			node = node.rightChild;
			// 3. 将node 的右孩子设置为当前元素.
			while(node == null){
				// 4. 右孩子为空.出栈根.
				if (stack.isEmpty()) {
					break;
				}
				node = stack.pop();
				System.out.print(node.data);
				System.out.print(" ");
				node = node.rightChild;	
			}
		}
	}
	
//	/**
//	 * 后续遍历, 非递归
//	 */
//	public static void afterOrderNotIteration(TreeNode<String> root) {
//		if (root == null) {
//			return;
//		}
//		
//		Stack<TreeNode<String>> stack = new Stack<>();
//		TreeNode<String> node = root;
//		
//		while(node != null || !stack.isEmpty()){
//			
//			while(node.leftChild != null){
//				stack.push(node);
//				node = node.leftChild;
//			}
//			
//			stack.push(node);
//			node = node.rightChild;
//			while(node == null){
//				// 出栈
//				node = stack.pop();
//				if (node == null) {
//					return;
//				}
//				System.out.println(node.data);
//				node = stack.pop();
//				if (node == null) {
//					return;
//				}
//				
//			
//			}
//			stack.push(node);
//			node = node.rightChild;
//		}
//	}
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		makeTree();
		System.out.println("树深度 : " + getTreeHeight(root));
		System.out.println("节点数 : " + getTreeSize(root));
		
		System.out.print("先序遍历 :　");
		preOrder(root);
		System.out.println();
		
		System.out.print("中序遍历 : ");
		midOrder(root);
		System.out.println();
		
		System.out.print("中序遍历 : ");
		afterOrder(root);
		System.out.println();
		
		System.out.print("先序遍历(非递归) : ");
		preOrderNotIteration(root);
		System.out.println();
		
		System.out.print("先序遍历(非递归) : ");
		midOrderNotIteration(root);
		System.out.println();
		
	}

	
	

	

}
