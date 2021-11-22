package _02_二叉树.TraverseTree._01_TraverseTree;

import _02_二叉树.BinaryTree;

/**
 * @ClassName TraverseTree
 * @Description 归纳  递归遍历二叉树代码
 * @Author StarLee
 * @Date 2021/11/19
 */
@SuppressWarnings("all")
public class TraverseTree<E> {
    private final BinaryTree<E> myTree;  //BinaryTree类型说明只要是二叉树都可以遍历
    public TraverseTree(BinaryTree<E> myTree) {
        this.myTree = myTree;
    }

    // 前序遍历
	public void preOrderTraversal(){
		preOrderTraversal((BinaryTree.Node<E>)myTree.root());  //遍历整棵树
	}

	public void preOrderTraversal(BinaryTree.Node<E> node){  //可以指定某个子树遍历
		if(node == null) return;
		System.out.print(node.element + " ");  //先遍历父亲节点
		preOrderTraversal(node.left);		   //再遍历左子树
		preOrderTraversal(node.right);		   //最后遍历右子树
	}

	// 中序遍历
	public void inorderTraversal(){
		inorderTraversal((BinaryTree.Node<E>)myTree.root());  //遍历整棵树
	}
	public void inorderTraversal(BinaryTree.Node<E> node){    //可以指定遍历某棵子树
		if(node == null) return;
		inorderTraversal(node.left);   		   //先遍历左子树
		System.out.print(node.element + " ");   //再遍历父亲节点
		inorderTraversal(node.right);			//最后遍历右子树
	}
	// 后序遍历
	public void postorderTraversal(){
		postorderTraversal((BinaryTree.Node<E>)myTree.root());  //遍历整棵树
	}
	public void postorderTraversal(BinaryTree.Node<E> node){  //可以指定遍历某棵子树
		if(node == null) return;
		postorderTraversal(node.left);		 //先遍历左子树
		postorderTraversal(node.right);		 //最后遍历右子树
		System.out.print(node.element + " ");  //再遍历父亲节点
	}

}
