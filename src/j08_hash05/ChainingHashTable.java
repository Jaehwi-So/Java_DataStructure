package j08_hash05;

import java.util.LinkedList;

class Entry<K, V> {
    private K key;
    private V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "[" + this.key + " : " + this.value + "]";
    }
}

public class ChainingHashTable<K, V> {

    // 체이닝을 위해서 Linked List의 배열을 선언
    private LinkedList<Entry<K, V>>[] table;
    private int capacity;
    private int size;

    public ChainingHashTable(int capacity) {
        this.capacity = capacity;
        this.table = new LinkedList[capacity];
        this.size = 0;
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void insert(K key, V value) {
        int index = hash(key);
        LinkedList<Entry<K, V>> list = table[index];
        // 해당 해시값에 대응하는 배열의 인덱스에 위치한 LinkedList를 탐색한다.
        for (Entry<K, V> entry : list) {
            // 키가 존재한다면 Value만 바꾼다.
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        // Linked List에 <Key, Value>를 추가한다.
        list.add(new Entry<>(key, value));
        size++;
    }

    public V search(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> list = table[index];
        // 해당 해시값에 대응하는 배열의 인덱스에 위치한 LinkedList를 탐색한다.
        for (Entry<K, V> entry : list) {
            // 키가 존재한다면 LinkedList에서 해당 아이템을 반환한다.
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void remove(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> list = table[index];
        // 해당 해시값에 대응하는 배열의 인덱스에 위치한 LinkedList를 탐색한다.
        for (Entry<K, V> entry : list) {
            // 키가 존재한다면 LinkedList에서 해당 아이템을 지운다.
            if (entry.getKey().equals(key)) {
                list.remove(entry);
                size--;
                return;
            }
        }
    }

    public void print(){
        for (int i = 0; i < capacity; i++) {
            System.out.print("Index " + i + ": ");
            LinkedList<Entry<K, V>> list = table[i];
            for (Entry<K, V> entry : list) {
                System.out.print(entry + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ChainingHashTable<String, Integer> hashTable = new ChainingHashTable<>(11);
        hashTable.insert("One", 1);
        hashTable.insert("Two", 2);
        hashTable.insert("Three", 3);
        hashTable.insert("Four", 4);
        hashTable.insert("Five", 5);
        hashTable.insert("Six", 6);
        hashTable.insert("Seven", 7);
        hashTable.insert("Eight", 8);
        hashTable.insert("Nine", 9);

        System.out.println("검색 결과:");
        System.out.println("키 Two 값: " + hashTable.search("Two")); // 2
        System.out.println("키 Ten 값: " + hashTable.search("Ten")); // null

        hashTable.remove("Three");
        System.out.println("Three 키 삭제 후 검색 결과:");
        System.out.println("키 Three 값: " + hashTable.search("Three")); // null

        System.out.println("전체 해시 테이블 값");
        hashTable.print();
    }
}