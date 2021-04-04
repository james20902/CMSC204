package Assignments.Assignment5;

import java.util.ArrayList;

public class MorseCodeTree implements LinkedConverterTreeInterface<String>{

    TreeNode<String> root;

    public MorseCodeTree(){
        buildTree();
    }

    @Override
    public TreeNode<String> getRoot() {
        return root;
    }

    @Override
    public void setRoot(TreeNode<String> newNode) {
        root = newNode;
    }

    @Override
    public LinkedConverterTreeInterface<String> insert(String code, String result) {
        addNode(root, code, result);
        return this;
    }

    @Override
    public void addNode(TreeNode<String> root, String code, String letter) {
        if(code.length() == 1){
            if(code.charAt(0) == '.'){
                root.left = new TreeNode<>(letter);
            } else {
                root.right = new TreeNode<>(letter);
            }
        }
        addNode(code.charAt(0) == '.' ? root.left : root.right, code.substring(1), letter);
    }

    @Override
    public String fetch(String code) {
        System.out.println("try");
        return fetchNode(root, code);
    }

    @Override
    public String fetchNode(TreeNode<String> root, String code) {
        System.out.println("traverse");
        if(code.length() == 1){
            return code.charAt(0) == '.' ? root.left.getData() : root.right.getData();
        }
        return fetchNode(code.charAt(0) == '.' ? root.left : root.right, code.substring(1));
    }

    @Override
    public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public LinkedConverterTreeInterface update() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void buildTree() {
        insert(".", "e");
        insert("-", "t");

        insert("..", "i");
        insert(".-", "a");
        insert("-.", "n");
        insert("--", "m");

        insert("...", "s");
        insert("..-", "u");
        insert(".-.", "r");
        insert(".--", "w");
        insert("-..", "d");
        insert("-.-", "k");
        insert("--.", "g");
        insert("---", "o");

        insert("....", "h");
        insert("...-", "v");
        insert("..-.", "f");
        insert("", "");
        insert(".-..", "l");
        insert("", "");
        insert(".--.", "p");
        insert(".---", "j");
        insert("-...", "b");
        insert("-..-", "x");
        insert("-.-.", "c");
        insert("-.--", "y");
        insert("--..", "z");
        insert("--.-", "q");
        insert("", "");
        insert("", "");
    }


    @Override
    public ArrayList<String> toArrayList() {
        ArrayList<String> output = new ArrayList<>();
        LNRoutputTraversal(root, output);
        return output;
    }

    @Override
    public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
        if(root != null){
            LNRoutputTraversal(root.left, list);
            list.add(root.getData());
            LNRoutputTraversal(root.right, list);
        }
    }
}
