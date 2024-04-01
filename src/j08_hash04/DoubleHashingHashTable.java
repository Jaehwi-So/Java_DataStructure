package j08_hash04;



// 이중 해싱 : 충돌이 발생했을 때는 첫 번째 해시 함수로 계산한 인덱스에 두 번째 해시 함수의 결과를 더해가면서 다음 빈 슬롯을 찾는다.
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

public class DoubleHashingHashTable<K, V> {
    private Entry<K, V>[] table;
    private int capacity;
    private int size;

    public DoubleHashingHashTable(int capacity) {
        this.capacity = capacity;
        this.table = new Entry[capacity];
        this.size = 0;
    }

    private int hash(K key) {
        // 절대값[Key의 해시코드(Integer)] % 배열의 크기 = 해시함수로 사용
        return Math.abs(key.hashCode()) % capacity;
    }

    private int stepHash(K key) {
        // 두 번째 해시 함수를 정의. 해시 결과가 0이 되면 안 되므로 0보다 큰 수 중에서 선택
        int hash = Math.abs(key.hashCode()) % (capacity - 1);
        return hash != 0 ? hash : 1;
    }

    public void insert(K key, V value) {
        if (size == capacity) {
            System.out.println("해시 테이블이 가득 찼습니다.");
            return;
        }

        int index = hash(key);
        int step = stepHash(key); //  두 번째 해시 함수의 결과
        while (table[index] != null) {
            index = (index + step) % capacity; // 충돌 발생 시 인덱스를 step씩 증가시켜 삽입 위치를 찾음
        }

        table[index] = new Entry<>(key, value);
        size++;
    }

    public V search(K key) {
        int index = hash(key);
        int step = stepHash(key); //  두 번째 해시 함수의 결과

        while (table[index] != null) {
            // 키가 일치할 경우 해당 Value 반환
            if (table[index].getKey().equals(key)) {
                return table[index].getValue();
            }
            // 만약 키가 일치하지 않는다면 인덱스를 step만큼 증가시킨 후 조사
            index = (index + step) % capacity;
        }
        return null; // 찾지 못한 경우
    }

    public void remove(K key) {
        int index = hash(key);
        int step = stepHash(key);

        while (table[index] != null) {
            // 키가 일치할 경우 해당 항목 제거
            if (table[index].getKey().equals(key)) {
                table[index] = null;
                size--;
                return;
            }
            // 만약 키가 일치하지 않는다면 인덱스를 step만큼 증가시킨 후 조사
            index = (index + step) % capacity;
        }
    }

    public void print(){
        for(Entry e : table){
            System.out.print(e + " ");
        }
    }

    public static void main(String[] args) {
        DoubleHashingHashTable<String, Integer> hashTable = new DoubleHashingHashTable<>(11);
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