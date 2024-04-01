package j08_hash01;

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

public class LinearProbingHashTable<K, V> {
    private Entry<K, V>[] table;
    private int capacity;
    private int size;

    public LinearProbingHashTable(int capacity) {
        this.capacity = capacity;
        this.table = new Entry[capacity];
        this.size = 0;
    }

    private int hash(K key) {
        // 절대값[Key의 해시코드(Integer)] % 배열의 크기 = 해시함수로 사용
        return Math.abs(key.hashCode()) % capacity;
    }

    public void insert(K key, V value) {
        if (size == capacity) {
            System.out.println("해시 테이블이 가득 찼습니다.");
            return;
        }

        int index = hash(key);
        while (table[index] != null) {
            index = (index + 1) % capacity; // 충돌 발생 시 인덱스를 1씩 증가시켜 삽입 위치를 찾음
        }

        table[index] = new Entry<>(key, value);
        size++;
    }

    public V search(K key) {
        int index = hash(key);
        while (table[index] != null) {
            // 키가 일치할 경우 해당 Value 반환
            if (table[index].getKey().equals(key)) {
                return table[index].getValue();
            }
            // 만약 키가 일치하지 않는다면 다음 인덱스를 조사
            index = (index + 1) % capacity;
        }
        return null; // 찾지 못한 경우
    }

    public void remove(K key) {
        int index = hash(key);
        while (table[index] != null) {
            // 키가 일치할 경우 해당 항목 제거
            if (table[index].getKey().equals(key)) {
                table[index] = null;
                size--;
                return;
            }
            // 만약 키가 일치하지 않는다면 다음 인덱스를 조사
            index = (index + 1) % capacity;
        }
    }

    public void print(){
        for(Entry e : table){
            System.out.print(e + " ");
        }
    }

    public static void main(String[] args) {
        LinearProbingHashTable<String, Integer> hashTable = new LinearProbingHashTable<>(11);
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