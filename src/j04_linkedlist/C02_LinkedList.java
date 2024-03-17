package j04_linkedlist;


/*
단순 연결 리스트 : 동적 메모리 할당을 이용해 리스트를 구현하는 가장 간단한 형태의 자료구조
노드는 값과 다음 노드의 참조값을 가지며 다음 순서의 노드를 가리키도록 만들어 노드들을 한 줄로 연결시킨다.
연결 리스트에서는 탐색 시 첫 노드부터 원하는 노드를 찾을 때 까지 순차 탐색을 하게 된다.
 */


class SimpleNode <E> {

    E data;
    SimpleNode<E> next;

    public SimpleNode(E data){
        this.data = data;
        this.next = null;
    }

}
class MySimpleLinkedList<E>{
    protected SimpleNode<E> head; // 연결 리스트의 첫 번째 노드를 가리킨다.
    private int size;   // 연결 리스트의 사이즈
    public MySimpleLinkedList(){
        this.head = null;
        size = 0;
    }

    public void addFirst(E data){
        SimpleNode node = new SimpleNode(data);
        node.next = this.head;
        this.head = node;
        this.size++;
    }

    public void addLast(E data){

        SimpleNode node = new SimpleNode(data);

        if(this.isEmpty()){
            this.head = node;
        }
        else{
            SimpleNode current = this.getIndexOfNode(this.size - 1);
            current.next = node;
        }

        this.size++;
    }

    // 특정 인덱스에 요소 추가
    public void add(int index, E data) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("인덱스가 범위를 벗어났습니다.");
        }
        if (index == 0) {
            this.addFirst(data);
        }
        else if (index == size){
            this.addLast(data);
        }
        else{
            SimpleNode newNode = new SimpleNode(data);
            SimpleNode before = this.getIndexOfNode(index - 1);
            //인덱스 이전의 노드 -> 새로운 노드 -> 인덱스 이전의 노드의 다음 노드
            newNode.next = before.next;
            before.next = newNode;
            this.size++;
        }
    }

    public void deleteFirst(){
        if(this.isEmpty()){
            throw new IndexOutOfBoundsException("리스트가 비어있습니다.");
        }
        this.head = this.head.next;
        this.size--;
    }

    public void deleteLast(){
        if(this.isEmpty()){
            throw new IndexOutOfBoundsException("리스트가 비어있습니다.");
        }
        if(size == 1){
            this.head = null;
        }
        else{
            SimpleNode before = this.getIndexOfNode(this.size - 2);
            before.next = null;
        }
        this.size--;

    }

    // 특정 인덱스에 요소 삭제
    public void delete(int index) {
        if(index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException("인덱스 범위를 벗어났습니다");
        }

        if (index == 0) {
            this.deleteFirst();
        }
        else if (index == size - 1) {
            this.deleteLast();
        }
        else{
            SimpleNode before = this.getIndexOfNode(index - 1);
            before.next = before.next.next;
            this.size--;
        }
    }

    public E get(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("인덱스가 범위를 벗어났습니다.");
        }
        SimpleNode<E> node = this.getIndexOfNode(index);
        return node.data;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    private SimpleNode getIndexOfNode(int index){

        SimpleNode current = head;
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        return current;
    }

    @Override
    public String toString() {
        SimpleNode current = head;
        StringBuilder sb = new StringBuilder();

        while (current != null) {
            if(current != head) {
                sb.append("->");
            }
            sb.append(current.data);
            current = current.next;
        }
        return sb.toString();
    }
}
public class C02_LinkedList {
    public static void main(String[] args) {
        MySimpleLinkedList list = new MySimpleLinkedList();
        list.add(0, 6);
        list.add(1, 7);
        System.out.println(list); // 6->7

        list.addFirst(4);
        list.addFirst(3);
        list.addFirst(2);
        System.out.println(list); // 2->3->4->6->7

        list.addLast(8);
        list.addLast(9);
        list.add(0, 1);
        list.add(4, 5);
        System.out.println(list); // 1->2->3->4->5->6->7->8->9

        list.deleteFirst();
        list.deleteLast();
        System.out.println(list); // 2->3->4->5->6->7->8

        list.delete(1);
        System.out.println(list); // 2->4->5->6->7->8

        System.out.println(list.get(1)); // 4

    }


}


//리스트의 구현 : 1차원 배열, 연결 리스트, 이중 연결 리스트, 환형 연결 리스트