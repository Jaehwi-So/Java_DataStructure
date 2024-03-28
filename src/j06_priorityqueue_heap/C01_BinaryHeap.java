package j06_priorityqueue_heap;


class Entry <Key extends Comparable<Key>, Value>{
    private Key key;
    private Value value;
    public Entry(Key key, Value value){
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
}

class BinaryHeap<Key extends Comparable<Key>, Value>{
    private Entry[] elements;
    private int size;
    public BinaryHeap(Entry[] ele, int size){
        this.elements = ele;
        this.size = size;
    }

    public int size(){
        return size;
    }

    // 키 비교
    private boolean greater(int i, int j){
        return elements[i].getKey().compareTo(elements[j].getKey()) > 0;
    }


    //두 키의 위치 변경
    private void swap(int i, int j){
        Entry tmp = elements[i];
        elements[i] = elements[j];
        elements[j] = tmp;
    }


    // 힙 생성 (초기에 임의의 순서로 키가 저장되어 있는 배열의 항목을 최소힙으로 만듬)
    public void createHeap(){
        // Leaf 노드의 부모 노드부터 -> 부모 노드까지 downheap 진행
        // [size / 2 ~ size]의 노드들은 이파리노드이므로 비교할 노드가 없으므로 size/2부터 수행
        for(int i = size / 2; i > 0; i--){
            downheap(i);
        }
    }


    private void downheap(int i){
        while(2 * i <= size){   // 자식 노드가 존재한다면
            int k = 2 * i;  // 자식 노드의 인덱스
            //size보다 작으면 자식 노드가 존재하는 것. 두 개의 자식 노드를 비교하여 작은 자식 노드를 인덱스로 취함
            if(k < size && greater(k, k+1)){
                k++;
            }
            if(!greater(i, k)){ //최소힙을 유지하는 과정에서 자식 노드(k)가 부모 노드(i)보다 크다면 루프 중단
                break;
            }
            swap(i, k); // 교환이 이루어짐
            i = k;  // 자식 노드를 부모 노드로 다시 설정하여 진행
        }
    }

    public void enqueue(Key newKey, Value newValue){
        Entry temp = new Entry(newKey, newValue);
        elements[++size] = temp;
        upheap(size);   //삽입된 원소의 인덱스를 upheap으로 조정
    }

    private void upheap(int i){
        while(i > 1 && greater(i/2, i)){
           swap(i/2, i);
           i = i/2;
        }
    }

    public Entry dequeue(){
        Entry min = elements[1];
        swap(1, size);    //가장 마지막 노드와 루트 노드 교환
        elements[size] = null; //삭제하였으므로 루트와 교환한 인덱스를 null로 설정
        size--; //사이즈도 줄임
        downheap(1);
        return min;

    }

    public void print(){
        for(Entry e : elements){
            if(e == null){
                continue;
            }
            System.out.print(e.getKey() + " ");
        }
        System.out.println();
    }


}
public class C01_BinaryHeap {
    public static void main(String[] args) {
        Entry<Integer, String>[] elements = new Entry[16];   //0번째 인덱스는 사용안함
        elements[1] = new Entry(60, "Grape");
        elements[2] = new Entry(30, "Carrot");
        elements[3] = new Entry(20, "Banana");
        elements[4] = new Entry(70, "Lemon");
        elements[5] = new Entry(80, "Monkey");
        elements[6] = new Entry(10, "Apple");
        elements[7] = new Entry(40, "Dog");
        elements[8] = new Entry(50, "Frog");

        BinaryHeap heap = new BinaryHeap(elements, 8);
        System.out.println("Initialize");
        heap.print();

        heap.createHeap();
        System.out.println("Create Heap");
        heap.print();

        System.out.println("Dequeue : " + heap.dequeue().getKey());
        heap.print();

        System.out.println("Dequeue : " + heap.dequeue().getKey());
        heap.print();

        System.out.println("Enqueue : 25");
        heap.enqueue(25, "Bus");
        heap.print();

        System.out.println("Enqueue : 90");
        heap.enqueue(90, "Nurse");
        heap.print();

        System.out.println("Dequeue : " + heap.dequeue().getKey());
        heap.print();
    }
}
