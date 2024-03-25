package j05_tree;

import java.util.LinkedList;
import java.util.Queue;

class AVLNode<Key extends Comparable<Key>, Value> {
    Key key;
    Value value;
    AVLNode<Key, Value> left;
    AVLNode<Key, Value> right;

    int height;

    public AVLNode(Key key, Value value){
        this.key = key;
        this.value = value;
        this.height = 1;
        this.left = this.right = null;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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

    public AVLNode<Key, Value> getLeft() {
        return left;
    }

    public void setLeft(AVLNode<Key, Value> left) {
        this.left = left;
    }

    public AVLNode<Key, Value> getRight() {
        return right;
    }

    public void setRight(AVLNode<Key, Value> right) {
        this.right = right;
    }




}

class AVLTree<Key extends Comparable<Key>, Value>{
    AVLNode<Key, Value> root;

    // 전위 순회
    public void preorder(){
        this.preorder(this.root);
    }

    private void preorder(AVLNode<Key, Value> n){
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

    private void inorder(AVLNode<Key, Value> n){
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

    private void postorder(AVLNode<Key, Value> n){
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

    private void levelorder(AVLNode<Key, Value> n){
        Queue queue = new LinkedList<AVLNode<Key, Value>>();
        queue.add(n);
        while(!queue.isEmpty()){
            AVLNode<Key, Value> current = (AVLNode<Key, Value>) queue.poll();
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

    private int getHeight(AVLNode<Key, Value> n){
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

    private int getSize(AVLNode<Key, Value> root){
        if(root == null){
            return 0;
        }
        else{
            return 1 + getSize(root.left) + getSize(root.right);
        }
    }

    public boolean isEqual(AVLTree<Key, Value> compare){
        return this.isEqual(this.root, compare.root);
    }

    private boolean isEqual(AVLNode<Key, Value> n, AVLNode<Key, Value> m){
        if(n == null || m == null){
            return n == m; //둘 다 null일 경우 True, 아니면 False(XOR)
        }
        if (n.key.compareTo(m.key) != 0){
            return false;
        }
        return isEqual(n.left, m.left) && isEqual(n.right, m.right);
    }

    public AVLNode search(Key k){
        return search(k, this.root);
    }

    private AVLNode search(Key k, AVLNode n){
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

    private AVLNode insert(Key k, Value v, AVLNode n){
        if(n == null){
            return new AVLNode(k, v);
        }
        int comp = n.getKey().compareTo(k);


        if(comp == 0){  // 같은 키값의 노드 -> Value만 갱신(Key 중복 허용 X)
            n.setValue(v);
            return n;
        }
        else if(comp > 0){  // 현재 노드보다 키값이 작은 경우
            n.setLeft(insert(k, v, n.getLeft()));
        }
        else{ // 현재 노드보다 키값이 큰 경우 -> 오른쪽 서브트리에 삽입
            n.setRight(insert(k, v, n.getRight()));
        }

        n.height = Math.max(height(n.left), height(n.right)) + 1;
        return balance(n);
    }


    public AVLNode getMin(){
        if(this.root == null){
            return null;
        }
        return getMin(this.root);
    }

    private AVLNode getMin(AVLNode n){
        if(n.getLeft() == null){
            return n; //Left 존재하지 않으면 최소값
        }
        return getMin(n.getLeft()); //최소값 나올때까지 left로 이동하며 재귀호출
    }

    public AVLNode getMax(){
        if(this.root == null){
            return null;
        }
        return getMax(this.root);
    }

    private AVLNode getMax(AVLNode n){
        if(n.getRight() == null){
            return n;   //Right가 존재하지 않으면 최대값
        }
        return getMax(n.getRight()); //최대값 나올때까지 right로 이동하며 재귀호출
    }

    public void deleteMin(){
        this.root = deleteMin(this.root);
    }

    private AVLNode deleteMin(AVLNode n){
        if(n.getLeft() == null){
            return n.getRight(); //Left 존재하지 않으면 최소값
        }
        n.setLeft(deleteMin(n.getLeft())); //최소값 나올 때 까지 Left로 이동
        n.height = Math.max(height(n.left), height(n.right)) + 1;
        return balance(n);
    }

    public void delete(Key k){
        this.root = delete(k, this.root);
    }

    private AVLNode delete(Key k, AVLNode n){
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
            else if(n.getLeft() == null){  //왼쪽 서브트리가 없는 경우 : 오른쪽 서브트리를 현재 노드의 위치로 연결
                return n.getRight();
            }
            else{
                AVLNode targetNode = n; // 삭제 대상 노드를 targetNode로 설정
                n = getMin(targetNode.getRight()); // 대체 : 삭제 대상의 오른쪽 서브트리의 최소값 노드를 삭제 위치로 이동
                n.setRight(deleteMin(targetNode.getRight()));  // 대체 노드의 right : 삭제 대상의 right 서브트리로 설정 + 이동된 노드는 제거
                n.setLeft(targetNode.getLeft());    // 대체 노드의 left : 삭제 대상의 left 서브트리로 설정
            }
        }

        n.height = Math.max(height(n.left), height(n.right)) + 1;
        return balance(n);
    }

    //왼쪽 서브트리와 오른쪽 서브트리의 높이 차이 반환
    private int bf(AVLNode n){
        return height(n.left) - height(n.right);
    }

    private int height(AVLNode n){
        if(n == null){
            return 0;
        }
        return n.getHeight();
    }


    private AVLNode balance(AVLNode n){
        //노드 N의 왼쪽 서브트리의 높이가 높음
        if(bf(n) > 1){
            //노드 N의 왼쪽 자식노드 -> 오른쪽 서브트리의 높이가 높음
            if(bf(n.left) < 0){
                n.left = rotateLeft(n.left); //LR유형(Left-Right Rotate)
            }
            n = rotateRight(n); //LL유형(Right Rotate) / LR유형(Left-Right Rotate)
        }
        //노드 N의 오른쪽 서브트리의 높이가 높음
        else if(bf(n) < -1){
            //노드 N의 오른쪽 자식노드 -> 왼쪽 서브트리의 높이가 높음
            if(bf(n.right) > 0){
                n.right = rotateRight(n.right);   //RL유형(Right-Left Rotate)
            }
            n = rotateLeft(n);  //RR유형(Left Rotate) / RL유형(Right-Left Rotate)
        }
        return n;
    }

    // 왼쪽으로 회전하는 함수
    private AVLNode<Key, Value> rotateLeft(AVLNode<Key, Value> n) {
        AVLNode<Key, Value> pivot = n.right;    // 오른쪽 자식노드 X : 노드 N의 위치로 변경
        n.right = pivot.left;   // 노드 X의 왼쪽 서브트리 : 노드 N의 오른쪽 서브트리로 위치 변경
        pivot.left = n;     // 노드 N : 노드 X의 왼쪽 자식노드로 변경

        // 높이 업데이트
        n.height = Math.max(height(n.left), height(n.right)) + 1;
        pivot.height = Math.max(height(pivot.left), height(pivot.right)) + 1;
        return pivot;
    }

    // 오른쪽으로 회전하는 함수
    private AVLNode<Key, Value> rotateRight(AVLNode<Key, Value> n) {
        AVLNode<Key, Value> pivot = n.left; // 왼쪽 자식노드 X : 노드 N의 위치로 변경
        n.left = pivot.right; // 노드 X의 오른쪽 서브트리 : 노드 N의 왼쪽 서브트리로 위치 변경
        pivot.right = n; // 노드 N : 노드 X의 오른쪽 자식노드로 변경

        // 높이 업데이트
        n.height = Math.max(height(n.left), height(n.right)) + 1;
        pivot.height = Math.max(height(pivot.left), height(pivot.right)) + 1;
        return pivot;
    }
}

public class C03_AVLTree {
    static AVLTree makeAVLTree(Integer lastKey){


        AVLTree<Integer, String> t = new AVLTree<>();
        t.insert(100, "Apple");
        t.insert(200, "Banana");
        t.insert(300, "Carrot");
        t.insert(400, "Dog");
        t.insert(500, "Elephant");
        t.insert(600, "Frog");
        t.insert(700, "Grape");
        t.insert(lastKey, "Lemon");
        return t;
    }
    public static void main(String[] args) {


        AVLTree t = makeAVLTree(800);

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


        AVLTree t2 = makeAVLTree(800);
        System.out.println("isEqual : " + t.isEqual(t2));

        AVLTree t3 = makeAVLTree(700);
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
