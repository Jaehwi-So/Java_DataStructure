package j04_linkedlist;

import java.util.LinkedList;
import java.util.List;

class CircularNode <E> {

    E data;
    CircularNode<E> next;

    public CircularNode(E data){
        this.data = data;
        this.next = null;
    }

}
class MyCircularLinkedList<E>{
    protected CircularNode<E> head;
    protected CircularNode<E> tail;
    private int size;   // 연결 리스트의 사이즈
    public MyCircularLinkedList(){
        this.head = null;
        this.tail = null;
        size = 0;
    }

    public void addFirst(E data){
        CircularNode<E> node = new CircularNode<>(data);
        if (isEmpty()) {
            head = node;
            tail = node;
            tail.next = head; // 환형 연결 리스트이므로 tail의 다음은 head
        }
        else {
            node.next = head;
            head = node;
            tail.next = head; // head가 새로 추가된 노드를 가리키도록 업데이트
        }
        size++;
    }

    public void addLast(E data){

        CircularNode node = new CircularNode(data);

        if (isEmpty()) {
            head = node;
            tail = node;
            tail.next = head; // 환형 연결 리스트이므로 tail의 다음은 head
        }
        else {
            tail.next = node;
            tail = node;
            tail.next = head; // tail이 새로 추가된 노드를 가리키도록 업데이트
        }
        size++;
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
            CircularNode newNode = new CircularNode(data);
            CircularNode before = this.getIndexOfNode(index - 1);
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
            CircularNode before = this.getIndexOfNode(this.size - 2);
            before.next = tail;
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
            CircularNode before = this.getIndexOfNode(index - 1);
            before.next = before.next.next;
            this.size--;
        }
    }

    public E get(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("인덱스가 범위를 벗어났습니다.");
        }
        CircularNode<E> node = this.getIndexOfNode(index);
        return node.data;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    private CircularNode getIndexOfNode(int index){

        CircularNode current = head;
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        return current;
    }

    @Override
    public String toString() {
        CircularNode current = head;
        StringBuilder sb = new StringBuilder();

        while (true) {
            if(current != head) {
                sb.append("->");
            }
            sb.append(current.data);
            if(current == tail){
                break;
            }
            current = current.next;
        }
        return sb.toString();
    }
}

public class C04_CircularLinkedList {
    public static void main(String[] args) {
        MyCircularLinkedList list = new MyCircularLinkedList<Integer>();
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

        LinkedList<String> linkedList = new LinkedList<>();

        linkedList.add("Banana");
        linkedList.add("Orange");
        linkedList.add("Kiwi");
        linkedList.add("Mango");
        linkedList.addFirst("Apple");
        linkedList.addLast("Grapes");
        //LinkedList: [Apple, Banana, Orange, Kiwi, Mango, Grapes]
        System.out.println("LinkedList: " + linkedList);


        linkedList.remove("Banana");
        linkedList.remove(3);

        //LinkedList: [Apple, Orange, Kiwi, Grapes]
        System.out.println("LinkedList: " + linkedList);

        linkedList.removeFirst();
        linkedList.removeLast();

        //LinkedList: [Orange, Kiwi]
        System.out.println("LinkedList: " + linkedList);

        String s = linkedList.get(1); // Kiwi
        s = linkedList.getFirst(); // Orange
        s = linkedList.getLast(); // Kiwi





    }
}
