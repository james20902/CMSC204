package Assignments.Assignment5;

/**
 * Data structure class similar to a doubly linked list, designed to containerize data and
 * support tree based operations
 * @param <T> Datatype to store
 * @author James Pham
 */
public class TreeNode <T>{

    private T data;
    public TreeNode<T> left;
    public TreeNode<T> right;

    /**
     * New node only containing data
     * @param data value to store
     */
    public TreeNode(T data){
        this.data = data;
    }

    /**
     * Deep copy constructor, copying data and pointers to other leaves in the tree
     * @param node ndoe to copy
     */
    public TreeNode(TreeNode<T> node){
        this.data = node.data;
        this.left = node.left;
        this.right = node.right;
    }

    /**
     * Public access method for data access
     * @return data held by pointer
     */
    public T getData(){
        return data;
    }

}
