package j05_tree;

import com.sun.jdi.Value;

import java.util.LinkedList;
import java.util.Queue;

class BSTNode<Key extends Comparable<Key>, Value> {
    Key key;
    Value value;
    BSTNode<Key, Value> left;
    BSTNode<Key, Value> right;

    public BSTNode(Key key, Value value){
        this.key = key;
        this.value = value;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public BSTNode<Key, Value> getLeft() {
        return left;
    }

    public void setLeft(BSTNode<Key, Value> left) {
        this.left = left;
    }

    public BSTNode<Key, Value> getRight() {
        return right;
    }

    public void setRight(BSTNode<Key, Value> right) {
        this.right = right;
    }




}

class BinarySearchTree<Key extends Comparable<Key>, Value>{
    BSTNode<Key, Value> root;

    // 전위 순회
    public void preorder(){
        this.preorder(this.root);
    }

    private void preorder(BSTNode<Key, Value> n){
        if(n != null){
            System.out.print(n.key + " ");
            preorder(n.left);
            preorder(n.right);
        }
    }

    // 중위 순회

    public void inorder(){
        this.inorder(this.root);
    }

    private void inorder(BSTNode<Key, Value> n){
        if(n != null){
            inorder(n.left);
            System.out.print(n.key + " ");
            inorder(n.right);
        }
    }

    // 후위 순회
    public void postorder(){
        this.postorder(this.root);
    }

    private void postorder(BSTNode<Key, Value> n){
        if(n != null){
            postorder(n.left);
            postorder(n.right);
            System.out.print(n.key + " ");
        }
    }


    // 레벨 순회
    public void levelorder(){
        this.levelorder(this.root);
    }

    private void levelorder(BSTNode<Key, Value> n){
        Queue queue = new LinkedList<BSTNode<Key, Value>>();
        queue.add(n);
        while(!queue.isEmpty()){
            BSTNode<Key, Value> current = (BSTNode<Key, Value>) queue.poll();
            System.out.print(current.key + " ");
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

    private int getHeight(BSTNode<Key, Value> n){
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

    private int getSize(BSTNode<Key, Value> root){
        if(root == null){
            return 0;
        }
        else{
            return 1 + getSize(root.left) + getSize(root.right);
        }
    }

    public boolean isEqual(BinarySearchTree<Key, Value> compare){
        return this.isEqual(this.root, compare.root);
    }

    private boolean isEqual(BSTNode<Key, Value> n, BSTNode<Key, Value> m){
        if(n == null || m == null){
            return n == m; //둘 다 null일 경우 True, 아니면 False(XOR)
        }
        if (n.key.compareTo(m.key) != 0){
            return false;
        }
        return isEqual(n.left, m.left) && isEqual(n.right, m.right);
    }

    public BSTNode search(Key k){
        return search(k, this.root);
    }

    private BSTNode search(Key k, BSTNode n){
        if(n == null){
            return null;
        }

        int comp = n.getKey().compareTo(k);
        if(comp == 0){
            return n;
        }
        else if(comp > 0){  // 현재 노드보다 키값이 작은 경우
            return search(k, n.getLeft());
        }
        else{
            return search(k, n.getRight());
        }
    }

    public void insert(Key k, Value v){
        this.root = insert(k, v, root);
    }

    private BSTNode insert(Key k, Value v, BSTNode n){
        if(n == null){
            return new BSTNode(k, v);
        }
        int comp = n.getKey().compareTo(k);

        // insert 재귀는 두 가지 케이스를 반환함.
        // case1. 서브트리 탐색 끝에 Null일 경우에는 새로운 노드 반환
        // case2. 서브트리 탐색 시 노드가 존재할 경우 기존 노드 반환

        if(comp == 0){  // 같은 키값의 노드 -> Value만 갱신(Key 중복 허용 X)
            n.setValue(v);
        }
        else if(comp > 0){  // 현재 노드보다 키값이 작은 경우 -> case1) 왼쪽에 삽입 or case2) 기존 left 노드를 setLeft(변화 없음)
            n.setLeft(insert(k, v, n.getLeft()));
        }
        else{ // 현재 노드보다 키값이 큰 경우 -> 오른쪽 서브트리에 삽입 -> case1) 오른쪽에 삽입 or case2) 기존 right 노드를 setRight(변화 없음)
            n.setRight(insert(k, v, n.getRight()));
        }
        return n;
    }


    public BSTNode getMin(){
        if(this.root == null){
            return null;
        }
        return getMin(this.root);
    }

    private BSTNode getMin(BSTNode n){
        if(n.getLeft() == null){
            return n; //Left 존재하지 않으면 최소값
        }
        return getMin(n.getLeft()); //최소값 나올때까지 left로 이동하며 재귀호출
    }

    public BSTNode getMax(){
        if(this.root == null){
            return null;
        }
        return getMax(this.root);
    }

    private BSTNode getMax(BSTNode n){
        if(n.getRight() == null){
            return n;   //Right가 존재하지 않으면 최대값
        }
        return getMax(n.getRight()); //최대값 나올때까지 right로 이동하며 재귀호출
    }

    public void deleteMin(){
        if(this.root == null){
            return;
        }
        this.root = deleteMin(this.root);
    }

    private BSTNode deleteMin(BSTNode n){
        if(n.getLeft() == null){
            return n.getRight(); //Left 존재하지 않으면 최소값
        }
        n.setLeft(deleteMin(n.getLeft())); //최소값 나올 때 까지 Left로 이동
        return n;
    }

    public void delete(Key k){
        delete(k, this.root);
    }

    private BSTNode delete(Key k, BSTNode n){
        if(n == null){
            return null;
        }
        int comp = n.getKey().compareTo(k);

        if(comp > 0){  // 현재 노드보다 키값이 작은 경우 -> 왼쪽 서브트리로 이동하며 delete() 재귀호출
            n.setLeft(delete(k, n.getLeft()));
        }
        else if(comp < 0){ // 현재 노드보다 키값이 큰 경우 -> 오른쪽 서브트리로 이동하며 delete() 재귀호출
            n.setRight(delete(k, n.getRight()));
        }
        else{  // 삭제할 노드 발견
            if(n.getRight() == null){   //오른쪽 서브트리가 없는 경우 : 왼쪽 서브트리를 현재 노드의 위치로 연결
                return n.getLeft();
            }
            if(n.getLeft() == null){  //왼쪽 서브트리가 없는 경우 : 오른쪽 서브트리를 현재 노드의 위치로 연결
                return n.getRight();
            }
            BSTNode targetNode = n; // 삭제 대상 노드를 targetNode로 설정
            n = getMin(targetNode.getRight()); // 대체 : 삭제 대상의 오른쪽 서브트리의 최소값 노드를 삭제 위치로 이동
            n.setRight(deleteMin(targetNode.getRight()));  // 대체 노드의 right : 삭제 대상의 right 서브트리로 설정 + 이동된 노드는 제거
            n.setLeft(targetNode.getLeft());    // 대체 노드의 left : 삭제 대상의 left 서브트리로 설정
        }
        return n;
    }
}

public class C02_BinarySearchTree {
    static BinarySearchTree makeBinarySearchTree(Integer lastKey){


        BinarySearchTree<Integer, String> t = new BinarySearchTree<>();
        t.insert(600, "Frog");
        t.insert(400, "Dog");
        t.insert(300, "Carrot");
        t.insert(100, "Apple");
        t.insert(200, "Banana");
        t.insert(700, "Grape");
        t.insert(500, "Elephant");
        t.insert(lastKey, "Lemon");
        return t;
    }
    public static void main(String[] args) {


        BinarySearchTree t = makeBinarySearchTree(800);

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


        BinarySearchTree t2 = makeBinarySearchTree(800);
        System.out.println("isEqual : " + t.isEqual(t2));

        BinarySearchTree t3 = makeBinarySearchTree(700);
        System.out.println("isEqual : " + t.isEqual(t3));

        System.out.println("Search 300 : " + t.search(300).getValue());
        System.out.println("Search 900 : " + t.search(900));
        System.out.println("Search getMin : " + t.getMin().getKey());
        System.out.println("Search getMax : " + t.getMax().getKey());

        System.out.println("Delete 400");
        t.delete(400);
        System.out.print("Inorder : ");
        t.inorder();
        System.out.println();

        System.out.print("Inorder : ");
        t.inorder();
        System.out.println();

        System.out.println("Delete min");
        t.deleteMin();
        System.out.print("Inorder : ");
        t.inorder();
        System.out.println();


    }
}
