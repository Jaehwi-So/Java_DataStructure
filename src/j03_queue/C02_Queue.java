package j03_queue;


import java.util.Queue;

class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}


class LinkedQueue<T> {
    private Node<T> front;
    private Node<T> rear;
    private int size;

    public LinkedQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            front = newNode;
        } else {
            rear.next = newNode;
        }
        rear = newNode;
        size++;
    }

    public T dequeue() {
        T dequeuedData = peek();
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return dequeuedData;
    }

    public T peek() {
        if (isEmpty()) {
            throw new Error("Empty Queue");
        }
        return front.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}


class MyQueue<T>{
    private T elements[];
    private int front;
    private int rear;
    private int size;

    public MyQueue(){
        elements = (T[]) new Object[1];
        front = 0;
        rear = -1;
        size = 0;
    }
    public void enqueue(T element){
        if(size() == elements.length){
            resize(elements.length * 2);
        }
        this.elements[++rear] = element;
        size++;
    }

    public T dequeue(){
        T result = peek();
        elements[front++] = null;
        size--;
        if(size() == elements.length / 4){
            resize(elements.length / 2);
        }
        return result;
    }

    public T peek(){
        if(isEmpty()){
            throw new Error("Empty queue");
        }
        T result = elements[front];
        return result;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public int size(){
        return size;
    }

    private void resize(int newSize){
        Object[] newElements = new Object[newSize];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[(front + i)];
        }
        elements = (T[]) newElements;
        front = 0;
        rear = size - 1;
    }
}


class CircularQueue<T>{
    private T elements[];
    private int front;
    private int rear;
    private int size;

    public void printIndex(){
        System.out.println("Front : " + front + " Rear : " + rear);
    }

    public CircularQueue(){
        elements = (T[]) new Object[1];
        front = 0;
        rear = 0;
        size = 0;
    }
    public void enqueue(T element){
        if(size() == elements.length){
            resize(elements.length * 2);
        }
        rear = (rear + 1) % elements.length;
        this.elements[rear] = element;
        size++;
    }

    public T dequeue(){
        T result = peek();
        elements[front] = null;
        front = (front + 1) % elements.length;
        size--;
        if(size() == elements.length / 4){
            resize(elements.length / 2);
        }
        return result;
    }

    public T peek(){
        if(isEmpty()){
            throw new Error("Empty queue");
        }
        T result = elements[front];
        return result;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public int size(){
        return size;
    }

    private void resize(int newSize){
        Object[] newElements = new Object[newSize];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[(front + i) % elements.length];
        }
        elements = (T[]) newElements;
        front = 0;
        rear = size - 1;
    }
}


public class C02_Queue {
    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue();
        System.out.println("Enqueue : " + 1);
        queue.enqueue(1);
        queue.printIndex();
        System.out.println("Enqueue : " + 2);
        queue.enqueue(2);
        queue.printIndex();
        System.out.println("Enqueue : " + 3);
        queue.enqueue(3);
        queue.printIndex();
        System.out.println("Enqueue : " + 4);
        queue.enqueue(4);
        queue.printIndex();


        System.out.println("Dequeue : " + queue.dequeue());
        queue.printIndex();
        System.out.println("Dequeue : " + queue.dequeue());
        queue.printIndex();
        System.out.println("Enqueue : " + 1);
        queue.enqueue(1);
        queue.printIndex();
        System.out.println("Enqueue : " + 2);
        queue.enqueue(2);
        queue.printIndex();


        System.out.println("Dequeue : " + queue.dequeue());
        queue.printIndex();
        System.out.println("Dequeue : " + queue.dequeue());
        queue.printIndex();
        System.out.println("Dequeue : " + queue.dequeue());
        queue.printIndex();
        System.out.println("Dequeue : " + queue.dequeue());
        queue.printIndex();
        System.out.println("Enqueue : " + 1);
        queue.enqueue(1);
        queue.printIndex();

        Queue queue
    }

}
