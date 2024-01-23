package ru.krisnovitskaya.algorithms.sprint2.B;

// <template>
class Node<V> {  
    public V value;  
    public Node<V> next;  
 
    public Node(V value, Node<V> next) {  
        this.value = value;  
        this.next = next;  
    }  
}
// <template>

public class Solution {
    public static void solution(Node<String> head) {
        // Your code
        // ヽ(´▽`)/
        Node<String> curNode = head;
        while (curNode != null){
            System.out.println(curNode.value);
            curNode = curNode.next;
        }
    }

    private static void test() {
        Node<String> node3 = new Node<>("node3", null);
        Node<String> node2 = new Node<>("node2", node3);
        Node<String> node1 = new Node<>("node1", node2);
        Node<String> node0 = new Node<>("node0", node1);
        solution(node0);
        /*
        Output is:
        node0
        node1
        node2
        node3
        */
    }
}