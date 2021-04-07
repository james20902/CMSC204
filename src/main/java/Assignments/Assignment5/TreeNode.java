package Assignments.Assignment5;

public class TreeNode <T>{

    private T data;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode(T data){
        this.data = data;
    }

    public TreeNode(TreeNode<T> node){
        this.data = node.data;
        this.left = node.left;
        this.right = node.right;
    }

    public T getData(){
        return data;
    }

}
