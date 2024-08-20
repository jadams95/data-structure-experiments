package com.jadams.datastructureexperiments.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanObj {
        int frequency;
        char character;
        HuffmanObj left = null, right = null;

        public HuffmanObj(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
        }

        HuffmanObj(HuffmanObj left, HuffmanObj right) {
            this.frequency = left.frequency + right.frequency;
            this.left = left;
            this.right = right;
        }
     public static class HuffmanCoding {

        // Recursive function to print the huffman code through the tree traversal.
        // Here s is the huffman code generated.
        public static void printCode(HuffmanObj root, String s) {
            if (root.left == null && root.right == null && Character.isLetter(root.character)) {
                // c is the character in the node
                System.out.println(root.character + ":" + s);
                return;
            }
            // if we go to left then add "0" to the code.
            // if we go to the right add"1" to the code.

            // recursive calls for left and right sub-tree of the generated tree.
            printCode(root.left, s + "0");
            printCode(root.right, s + "1");
        }

        public static HuffmanObj buildHuffmanTree(String text) {
            // Base case: empty string
            if (text == null || text.length() == 0) {
                return null;
            }

            // Count frequency of appearance of each character
            // and store it in a map
            Map<Character, Integer> freq = new HashMap<>();
            for (char c : text.toCharArray()) {
                freq.put(c, freq.getOrDefault(c, 0) + 1);
            }

            // Create a priority queue to store live nodes of the Huffman tree;
            // higher frequency character will have lower priority
            PriorityQueue<HuffmanObj> pq = new PriorityQueue<>((l, r) -> l.frequency - r.frequency);

            // Create a leaf node for each character and add it to the priority queue.
            for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
                pq.add(new HuffmanObj(entry.getKey(), entry.getValue()));
            }

            // Iterate until there is more than one node in the queue
            while (pq.size() > 1) {
                // Remove the two nodes of the highest priority
                // (lowest frequency) from the queue
                HuffmanObj left = pq.poll();
                HuffmanObj right = pq.poll();

                // Create a new internal node with these two nodes as children
                // and with frequency equal to the sum of the two nodes' frequencies.
                // Add the new node to the priority queue.
                HuffmanObj node = new HuffmanObj(left, right);
                pq.add(node);
            }

            // The remaining node is the root of the Huffman tree.
            HuffmanObj root = pq.poll();
            return root;
        }
      }

    @Override
    public String toString() {
        return "HuffmanObj{" +
                "frequency=" + frequency +
                ", character=" + character +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
