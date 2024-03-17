package j04_linkedlist;

class DoubleNode<E> {
    E data;
    DoubleNode<E> next;
    DoubleNode<E> prev;

    public DoubleNode(E data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

class MyDoubleLinkedList<E> {
    private DoubleNode<E> head;
    private DoubleNode<E> tail;
    private int size;

    public MyDoubleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void addFirst(E data) {
        DoubleNode node = new DoubleNode(data);
        if (isEmpty()) {
            this.head = node;
            this.tail = node;
        }
        else {
            node.next = head;
            this.head.prev = node;
            this.head = node;
        }
        this.size++;
    }

    public void addLast(E data) {
        DoubleNode<E> node = new DoubleNode<>(data);

        if (isEmpty()) {
            this.head = node;
            this.tail = node;f
        }
        else {
            this.tail.next = node;
            node.prev = tail;
            this.tail = node;
        }

        size++;
    }

    public void add(int index, E data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("인덱스가 범위를 벗어났습니다.");
        }
        if (index == 0) {
            this.addFirst(data);
        }
        else if (index == size) {
            this.addLast(data);
        }
        else {
            DoubleNode<E> newNode = new DoubleNode<>(data);
            DoubleNode<E> before = getNodeAtIndex(index - 1);
            newNode.next = before.next;
            before.next.prev = newNode;
            before.next = newNode;
            newNode.prev = before;
            this.size++;
        }
    }

    public void deleteFirst() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("리스트가 비어있습니다.");
        }
        if (this.size == 1) {
            this.head = null;
            this.tail = null;
        }
        else {
            this.head = head.next;
            this.head.prev = null;
        }
        this.size--;
    }

    public void deleteLast() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("리스트가 비어있습니다.");
        }
        if (size == 1) {
            this.head = null;
            this.tail = null;
        }
        else {
            this.tail = this.tail.prev;
            this.tail.next = null;
        }
        this.size--;
    }

    public void delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("인덱스가 범위를 벗어났습니다.");
        }
        if (index == 0) {
            this.deleteFirst();
        }
        else if (index == size - 1) {
            this.deleteLast();
        }
        else {
            DoubleNode current = getNodeAtIndex(index);
            current.prev.next = current.next;
            current.next.prev = current.prev;
            this.size--;
        }
    }

    public E get(int index) {
        DoubleNode<E> node = this.getNodeAtIndex(index);
        return node.data;
    }

    private DoubleNode<E> getNodeAtIndex(int index) {

        // 리스트의 절반 이상에 해당하는 인덱스일 경우 뒤에서부터 탐색
        if (index > size / 2) {
            DoubleNode<E> current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
            return current;
        } else { // 리스트의 절반 이하에 해당하는 인덱스일 경우 앞에서부터 탐색
            DoubleNode<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size(){
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DoubleNode<E> current = head;
        while (current != null) {
            if (current != head) {
                sb.append("-");
            }
            sb.append(current.data);
            current = current.next;
        }
        return sb.toString();
    }
}

public class C03_DoubleLinkedList {
    public static void main(String[] args) {
        MyDoubleLinkedList list = new MyDoubleLinkedList();
        list.add(0, 6);
        list.add(1, 7);
        System.out.println(list); // 6-7

        list.addFirst(4);
        list.addFirst(3);
        list.addFirst(2);
        System.out.println(list); // 2-3-4-6-7

        list.addLast(8);
        list.addLast(9);
        list.add(0, 1);
        list.add(4, 5);
        System.out.println(list); // 1-2-3-4-5-6-7-8-9

        list.deleteFirst();
        list.deleteLast();
        System.out.println(list); // 2-3-4-5-6-7-8

        list.delete(1);
        System.out.println(list); // 2-4-5-6-7-8

        System.out.println(list.get(1)); // 4
    }

}
