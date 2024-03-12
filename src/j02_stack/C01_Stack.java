package j02_stack;

class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}



class LinkedStack<T> {
    private Node<T> top;
    private int size;

    public LinkedStack() {
        this.top = null;
        this.size = 0;
    }

    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new Error("Empty stack");
        }
        T poppedData = top.data;
        top = top.next;
        size--;
        return poppedData;
    }

    public T peek() {
        if (isEmpty()) {
            throw new Error("Empty stack");
        }
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }
}




class MyStack<T>{
    private T elements[];
    private int top;

    public MyStack(){
        elements = (T[]) new Object[1];
        top = -1;
    }
    public void push(T element){
        if(size() == elements.length){
            resize(elements.length * 2);
        }
        this.elements[++top] = element;
    }

    public T pop(){
        T result = peek();
        elements[top--] = null;
        if(size() == elements.length / 4){
            resize(elements.length / 2);
        }
        return result;
    }

    public T peek(){
        if(isEmpty()){
            throw new Error("Empty stack");
        }
        T result = elements[top];
        return result;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public int size(){
        return top + 1;
    }

    private void resize(int newSize){
        Object[] newElements = new Object[newSize];
        for(int i = 0; i < size(); i++){
            newElements[i] = elements[i];
        }
        elements = (T[]) newElements;
    }

}

public class C01_Stack {
    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack();
        System.out.println("isEmpty : " + stack.isEmpty());
        System.out.println("Push : " + 1);
        stack.push(1);
        System.out.println("Push : " + 2);
        stack.push(2);
        System.out.println("Push : " + 3);
        stack.push(3);
        System.out.println("isEmpty : " + stack.isEmpty());
        System.out.println("Size : " + stack.size());

        System.out.println("Pop : " + stack.pop());
        System.out.println("Pop : " + stack.pop());
        System.out.println("isEmpty : " + stack.isEmpty());
        System.out.println("Size : " + stack.size());

        System.out.println("Pop : " + stack.pop());
        System.out.println("isEmpty : " + stack.isEmpty());
        System.out.println("Size : " + stack.size());
    }

}
