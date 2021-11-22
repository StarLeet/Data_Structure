package _0_Tools.TreePrinter;

/**
 * @Description 要想使用此工具,必须实现这个接口
 */
public interface BinaryTreeInfo {
	/**
	 * who is the root node
	 */
	Object root();
	/**
	 * how to get the left child of the node
	 */
	Object left(Object node);
	/**
	 * how to get the right child of the node
	 */
	Object right(Object node);
	/**
	 * how to print the node
	 */
	Object string(Object node);
}
