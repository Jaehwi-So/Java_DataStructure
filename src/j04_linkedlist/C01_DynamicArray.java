package j04_linkedlist;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

class MyDynamicArray <E> {
    private E elements[];
    private int size;
    public MyDynamicArray(){
        elements = (E[]) new Object[1];
        size = 0;
    }

    public E get(int k){
        if(size == 0){
            throw new NoSuchElementException();
        }
        return elements[k];
    }

    public void add(E element){
        if(size == elements.length){
            resize(2 * elements.length);
        }
        elements[size++] = element;
    }

    public void add(int k, E element){
        if(size == elements.length){
            resize(2 * elements.length);
        }

        // K번째 이후 원소는 한 칸 씩 뒤로 이동
        for(int i = size - 1; i >= k; i--){
            elements[i+1] = elements[i];
        }
        elements[k] = element;
        size++;
    }

    public E remove(int k){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        else{
            E item = elements[k];

            // 삭제된 인덱스 기준 뒤의 아이템들을 한 칸씩 앞으로 이동
            for(int i = k; i < size - 1; i++){
                elements[i] = elements[i + 1];
            }
            elements[size - 1] = null;
            size--;

            if(size > 0 && size == elements.length / 4){
                resize(elements.length / 2);
            }
            return item;
        }
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private void resize(int newSize){
        // 기존의 배열은 가비지 컬렉션에 의해서 처리된다.
//        Object[] newList = new Object[newSize];
//        for(int i = 0; i < size; i++){
//            newList[i] = elements[i];
//        }
//        elements = (E[]) newList;

        Object[] newList = Arrays.copyOf(elements, newSize);
        elements = (E[]) newList;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null) {
                sb.append(elements[i]);
                if (i < elements.length - 1 && elements[i + 1] != null) {
                    sb.append(", ");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
public class C01_DynamicArray {
    // 배열 : 미리 정해진 크기의 메모리 공간을 할당 받은 뒤 사용한다.
    // 동적 배열 : 배열에 Overflow가 발생하면 배열 크기를 2배로 확장한다, 배열의 3/4가 비어 있다면 배열 크기를 1/2로 축소한다

    public static void main(String[] args) {
        MyDynamicArray list = new MyDynamicArray<Integer>();
        list.add(1);
        list.add(2);
        list.add(4);
        System.out.println(list);
        System.out.println(list.get(1));
        list.add(2, 3);
        System.out.println(list);
        list.remove(0);
        System.out.println(list);
    }
}
