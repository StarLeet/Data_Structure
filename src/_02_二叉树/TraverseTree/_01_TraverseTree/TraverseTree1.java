package _02_二叉树.TraverseTree._01_TraverseTree;

import _02_二叉树.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @ClassName TraverseTree1
 * @Description 归纳  非递归遍历二叉树代码
 * @Author StarLee
 * @Date 2021/11/20
 */
@SuppressWarnings("all")
public class TraverseTree1<E> {
    private final BinaryTree<E> myTree;

    public TraverseTree1(BinaryTree<E> mytree) {
        this.myTree = mytree;
    }

    public void preOrderTraversal(){
        preOrderTraversal((BinaryTree.Node<E>)myTree.root());
    }
    /**
     *  @MethodName preOrderTraversal
     *  @Description  迭代实现前序遍历
     *  @Param [node]
     *  @return void
     *
     *  使用一个栈，存储二叉链表中的结点。
     *  思路为：从二叉树的根结点开始，沿左子树一直走到末端（左孩子为空）为止，
     *  在遍历过程中，依次把所遇结点入栈，当左子树为空时，从栈中退出栈顶结点，并将指针指向该结点的右孩子。
     *  如此重复，直到栈为空或指针为空时止
     */
    public void preOrderTraversal(BinaryTree.Node<E> node){
        if (node == null) return;
        Stack<BinaryTree.Node<E>> stack = new Stack<>(); //用来存储根节点
        while(!stack.empty() || node != null){  //栈不为空说明节点没遍历完
            while(node != null){  //遍历 根和左子树  直到最左
                System.out.print(node.element + " ");
                stack.add(node);
                node = node.left;
            }
            node = stack.pop().right;  //切换到右子树进行遍历
        }
    }

    public void inorderTraversal(){
        inorderTraversal((BinaryTree.Node<E>)myTree.root());
    }
    /**
     *  @MethodName inorderTraversal
     *  @Description  迭代实现中序遍历
     *  @Param [node]
     *  @return void
     */
    public void inorderTraversal(BinaryTree.Node<E> node){
        if (node == null) return;
        Stack<BinaryTree.Node<E>> stack = new Stack<>(); //用来存储根节点
        while (!stack.empty() || node != null){
            while(node != null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            System.out.print(node.element + " ");
            node = node.right;
        }
    }

    public void postorderTraversal(){
        postorderTraversal((BinaryTree.Node<E>)myTree.root());
    }
    /**
     *  @MethodName postorderTraversal
     *  @Description  迭代实现后序遍历
     *  @Param [node]
     *  @return void
     *
     *  在后序遍历中，当搜索指针指向某一个结点(根)时，不能马上进行访问，
     *  而先要遍历左子树，所以此结点先要入栈保存，当遍历完它的左子树后，再次回到该结点，
     *  仍旧不能访问它，而是要继续遍历它的右子树，遍历完右子树之后才能访问它。
     *  为了统计一个节点的被遍历次数，引入一个同步的标记栈，节点入栈时，标记栈同步入一个标记0
     *  遍历完左子树回到此节点后，修改同步标记为1，当其遍历完右子树回来之后，发现同步标记为1，节点访问并出栈
     */
    public void postorderTraversal(BinaryTree.Node<E> node){
        if (node == null) return;
        Stack<BinaryTree.Node<E>> stack = new Stack<>(); //用来存储根节点
        Stack<Integer> flagStack = new Stack<>(); //后序遍历中记录节点访问次数
        while(!stack.empty() || node != null){
            while(node != null){
                stack.push(node);
                flagStack.push(0);
                node = node.left;
            } //到此说明,遍历到当前节点的最左节点
            if (flagStack.peek() == 0){   //说明左子树遍历完成,回到根
                node = stack.peek();   //根还不能访问,所以只是取出引用
                flagStack.set(flagStack.size() - 1, 1);  //代表开始访问右子树
                node = node.right;
            }else{  // flagStack.peek() == 1 说明右子树访问完成,此时node一定为null
                System.out.print(stack.pop().element + " ");  //此时弹出根
                flagStack.pop();  //根的标记一起弹出
            }
        }
    }

    /**
    *  @MethodName levelOrderTraversal
    *  @Description  队列实现层序遍历(非递归)
    *  @Param []
    *  @return void
    */
    public void levelOrderTraversal(){
        if(myTree.root() == null) return;
        Queue<BinaryTree.Node<E>> queue = new LinkedList<>();
        queue.add((BinaryTree.Node<E>)myTree.root());  //先让根加入队列

        while(!queue.isEmpty()){ //队列为空说明遍历完成
            BinaryTree.Node<E> node = queue.poll();  //父节点出队的同时,让两个孩子入队
            System.out.print(node.element + " ");   //打印出队的父节点
            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }
    }
}
