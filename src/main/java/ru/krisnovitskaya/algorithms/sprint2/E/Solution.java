package ru.krisnovitskaya.algorithms.sprint2.E;

// <template>
class Node<V> {  
    public V value;  
    public Node<V> next;  
    public Node<V> prev;  
 
    public Node(V value, Node<V> next, Node<V> prev) {  
        this.value = value;  
        this.next = next;  
        this.prev = prev;  
    }  
}
// <template>


/*
E. Всё наоборот
Вася решил запутать маму —– делать дела в обратном порядке. Список его дел теперь хранится в двусвязном списке. Напишите функцию, которая вернёт список в обратном порядке.
 */
public class Solution {
    public static Node<String> solution(Node<String> head) {
        // Your code
        // ヽ(´▽`)/
        return reverse(head);
    }

    private static Node<String> reverse(Node<String> head) {
        if(head.next == null){
            return head;
        }

        Node<String> cur = head;
        Node<String> curPrev = null;
        while (cur != null){
            curPrev = cur.prev;
            cur.prev = cur.next;
            cur.next = curPrev;
            cur = cur.prev;
        }
        return curPrev.prev;
    }

    private static void test() {
        Node<String> node3 = new Node<>("node3", null, null);
        Node<String> node2 = new Node<>("node2", node3, null);
        Node<String> node1 = new Node<>("node1", node2, null);
        Node<String> node0 = new Node<>("node0", node1, null);
        node1.prev = node0;
        node2.prev = node1;
        node3.prev = node2;

        print(node0);
        System.out.println("_____");
        Node<String> newNode = solution(node0);
        print(newNode);

        /* result is :*/
        assert newNode == node3;
        assert node3.next == node2;
        assert node2.next == node1;
        assert node2.prev == node3;
        assert node1.next == node0;
        assert node1.prev == node2;
        assert node0.prev == node1;
    }

    private static void print(Node<String> head){
        Node<String> curNode = head;
        while (curNode != null){
            System.out.println(curNode.value);
            curNode = curNode.next;
        }
    }

    public static void main(String[] args) {
        test();
    }
}