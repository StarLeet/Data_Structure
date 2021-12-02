package _02_二叉树._02_BBST;

import _02_二叉树._01_BinarySearchTree.BinarySearchTree;

import java.util.Comparator;

/**
 * @ClassName BBST
 * @Description  平衡二叉树的公有代码,抽象实体
 * @Author StarLee
 * @Date 2021/11/28
 */
@SuppressWarnings("all")
public class BBST<E> extends BinarySearchTree<E> {
    public BBST() {
        this(null);
    }
    public BBST(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * 左旋
     */
    protected void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    /**
     * 右旋
     */
    protected void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    /**
     * 公共代码：不管是左旋、右旋，都要执行的
     * @param grand 失衡节点
     * @param parenet 失衡节点的tallerChild
     * @param child g和p需要交换的子树（本来是p的子树，后来会变成g的子树）
     */
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        // 让parent称为子树的根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) { // grand是左子树
            grand.parent.left = parent;
        } else if (grand.isRightChild()) { // grand是右子树
            grand.parent.right = parent;
        } else { // grand是root节点
            root = parent;
        }

        // 更新child的parent
        if (child != null) {
            child.parent = grand;
        }

        // 更新grand的parent
        grand.parent = parent;
    }

    /**
     * 统一处理的旋转代码(简洁版)
     */
    protected void rotate(
            Node<E> r, // 子树的根节点
            Node<E> b, Node<E> c,
            Node<E> d,
            Node<E> e, Node<E> f) {
        // 让d成为这棵子树的根节点
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }

        //b-c
        b.right = c;
        if (c != null) {
            c.parent = b;
        }

        // e-f
        f.left = e;
        if (e != null) {
            e.parent = f;
        }

        // b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
    }

    /**
     * 综合考虑较全的统一旋转
     */
    /*private void rotate(
			Node<E> r, // 子树的根节点
			Node<E> a, Node<E> b, Node<E> c,
			Node<E> d,
			Node<E> e, Node<E> f, Node<E> g) {
		// 让d成为这颗子树的根结点
		d.parent = r.parent;
		if(r.isLeftChild()){
			r.parent.left = d;
		}else if(r.isRightChild()){
			r.parent.right = d;
		}else{
			root = d;
		}
		// a-b-c
		b.left = a;
		if(a!=null){
			a.parent = b;
		}
		b.right = c;
		if(c!=null){
			c.parent = b;
		}

		// e-f-g
		f.left = e;
		if(e != null){
			e.parent = f;
		}
		f.right = g;
		if(g != null){
			g.parent = f;
		}

		// b-d-f
		d.left = b;
		d.right = f;
		b.parent = d;
		f.parent = d;
	}*/
}
