package j05_tree;

import java.util.LinkedList;
import java.util.Queue;

class Node<T extends Comparable<T>> {
    T item;
    Node<T> left;
    Node<T> right;

    public Node(T item, Node<T> left, Node<T> right){
        this.item = item;
        this.left = left;
        this.right = right;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }


}

class BinaryTree<T extends Comparable<T>>{
    Node<T> root;

    public void setRoot(Node<T> n){
        this.root = n;
    }

    public Node<T> getRoot(){
        return this.root;
    }

    // 전위 순회

    public void preorder(){
        this.preorder(this.root);
    }

    private void preorder(Node<T> n){
        if(n != null){
            System.out.print(n.item + " ");
            preorder(n.left);
            preorder(n.right);
        }
    }

    // 중위 순회

    public void inorder(){
        this.inorder(this.root);
    }

    private void inorder(Node<T> n){
        if(n != null){
            inorder(n.left);
            System.out.print(n.item + " ");
            inorder(n.right);
        }
    }

    // 후위 순회
    public void postorder(){
        this.postorder(this.root);
    }

    private void postorder(Node<T> n){
        if(n != null){
            postorder(n.left);
            postorder(n.right);
            System.out.print(n.item + " ");
        }
    }


    // 레벨 순회
    public void levelorder(){
        this.levelorder(this.root);
    }

    private void levelorder(Node<T> n){
        Queue queue = new LinkedList<Node<T>>();
        queue.add(n);
        while(!queue.isEmpty()){
            Node<T> current = (Node<T>) queue.poll();
            System.out.print(current.item + " ");
            if(current.left != null){
                queue.add(current.left);
            }
            if(current.right != null){
                queue.add(current.right);
            }
        }
    }


    public int getHeight(){
        return this.getHeight(this.root);
    }

    private int getHeight(Node<T> n){
        if(n == null){
            return 0;
        }
        else{
            return 1 + Math.max(getHeight(n.left), getHeight(n.right));
        }
    }

    public int getSize(){
        return this.getSize(this.root);
    }

    private int getSize(Node<T> root){
        if(root == null){
            return 0;
        }
        else{
            return 1 + getSize(root.left) + getSize(root.right);
        }
    }

    public boolean isEqual(BinaryTree<T> compare){
        return this.isEqual(this.root, compare.root);
    }

    private boolean isEqual(Node<T> n, Node<T> m){
        if(n == null || m == null){
            return n == m; //둘 다 null일 경우 True, 아니면 False(XOR)
        }
        if (n.item.compareTo(m.item) != 0){
            return false;
        }
        return isEqual(n.left, m.left) && isEqual(n.right, m.right);
    }
}
public class C01_BinaryTree {

    static BinaryTree makeBinaryTree(Integer lastKey){
        Node<Integer> n1 = new Node(100, null, null);
        Node<Integer> n2 = new Node(200, null, null);
        Node<Integer> n3 = new Node(300, null, null);
        Node<Integer> n4 = new Node(400, null, null);
        Node<Integer> n5 = new Node(500, null, null);
        Node<Integer> n6 = new Node(600, null, null);
        Node<Integer> n7 = new Node(700, null, null);
        Node<Integer> n8 = new Node(lastKey, null, null);

        n1.setLeft(n2);
        n1.setRight(n3);
        n2.setLeft(n4);
        n2.setRight(n5);
        n3.setLeft(n6);
        n3.setRight(n7);
        n4.setLeft(n8);

        BinaryTree<Integer> t = new BinaryTree<>();
        t.setRoot(n1);
        return t;
    }
    public static void main(String[] args) {


        BinaryTree t = makeBinaryTree(800);

        System.out.print("Preorder : ");
        t.preorder();
        System.out.println();

        System.out.print("Inorder : ");
        t.inorder();
        System.out.println();

        System.out.print("Postorder : ");
        t.postorder();
        System.out.println();

        System.out.print("Levelorder : ");
        t.levelorder();
        System.out.println();

        System.out.println("Height : " + t.getHeight());
        System.out.println("Size : " + t.getSize());


        BinaryTree t2 = makeBinaryTree(800);
        System.out.println("isEqual : " + t.isEqual(t2));

        BinaryTree t3 = makeBinaryTree(700);
        System.out.println("isEqual : " + t.isEqual(t3));


    }
}



