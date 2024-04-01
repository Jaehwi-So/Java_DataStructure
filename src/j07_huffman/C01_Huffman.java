package j07_huffman;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class HuffmanNode {
    char data;
    int frequency;
    HuffmanNode left, right;

    public HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
        left = right = null;
    }
}

class HuffmanCoding {

    // 1. 빈도수 계산
    public Map<Character, Integer> calculateFrequency(String input) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : input.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        return frequencyMap;
    }

    // 2. 허프만 트리 생성
    public HuffmanNode buildHuffmanTree(Map<Character, Integer> frequencyMap) {
        // 우선순위 큐(최소 힙)
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>((a, b) -> a.frequency - b.frequency);

        // 우선순위 큐에 문자와 빈도를 노드 형태로 삽입
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            pq.offer(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

       // 우선순위 큐에 하나의 루트 노드만 남을 때 까지 반복
        while (pq.size() > 1) {
            // 큐에서 두 개의 빈도가 작은 노드를 꺼냄
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();

            // 두 개의 노드를 한 개의 부모 노드를 통해서 묶음 + 부모 노드의 Frequency는 자식 노드의 합으로 설정
            HuffmanNode parent = new HuffmanNode('$', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;

            // 해당 부모 노드를 다시 큐로 집어넣음.
            pq.offer(parent);
        }
        return pq.poll();
    }

    // 3. 허프만 부호화
    public void generateHuffmanCodes(HuffmanNode root, String code, Map<Character, String> huffmanCodes) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.data, code);
        }
        generateHuffmanCodes(root.left, code + "0", huffmanCodes);
        generateHuffmanCodes(root.right, code + "1", huffmanCodes);
    }

    // 4. 문자열 압축
    public String compress(String input) {

        // 1. 빈도수 계산
        Map<Character, Integer> frequencyMap = calculateFrequency(input);
        System.out.println("1. 빈도수 계산");
        System.out.println(frequencyMap);

        // 2. 허프만 트리 생성
        HuffmanNode root = buildHuffmanTree(frequencyMap);
        System.out.println("2. 허프만 트리 생성");


        // 3. 허프만 부호화
        Map<Character, String> huffmanCodes = new HashMap<>();
        generateHuffmanCodes(root, "", huffmanCodes);
        System.out.println("3. 허프만 부호화");
        System.out.println(huffmanCodes);

        // 4. 문자열 압축
        StringBuilder compressedString = new StringBuilder();
        System.out.println("4. 문자열 압축");
        for (char c : input.toCharArray()) {
            compressedString.append(huffmanCodes.get(c));
        }
        return compressedString.toString();
    }
}

public class C01_Huffman{
    public static void main(String[] args) {
        HuffmanCoding hf = new HuffmanCoding();
        String input = "AAAAAAAAAAAAAAABBBBBBBCCCCCCDDDDDDEEEEE";
        String compressedString = hf.compress(input);
        System.out.println("압축 전: " + input);
        System.out.println("압축 후: " + compressedString);
    }
}