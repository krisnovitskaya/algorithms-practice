package ru.krisnovitskaya.algorithms.sprint2.C;

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

/*
C. Нелюбимое дело

 Список дел представлен в виде односвязного списка.
  Напишите функцию solution, которая принимает на вход голову списка и номер удаляемого дела
   и возвращает голову обновлённого списка.
 */
public class Solution {
    public static Node<String> solution(Node<String> head, int idx) {
        // Your code
        // ヽ(´▽`)/
        return deleteById(head, idx);
    }

    private static Node<String> deleteById(Node<String> head, int deletingIndex){
        if(deletingIndex == 0){
            Node<String> newHead = head.next;
            head.next = null;
            return newHead;
        }

        Node<String> previous = getNodeById(head, deletingIndex - 1);
        Node<String> deleting = previous.next;
        Node<String> next = deleting.next;

        previous.next = next;
        deleting = null;
        return head;
    }

    private static Node<String> getNodeById(Node<String> head, int index){
        Node<String> searching = head;
        for (int i = 0; i < index; i++) {
            searching = searching.next;
        }
        return searching;
    }

    private static void test() {
        Node<String> node3 = new Node<>("node3", null);
        Node<String> node2 = new Node<>("node2", node3);
        Node<String> node1 = new Node<>("node1", node2);
        Node<String> node0 = new Node<>("node0", node1);
        Node<String> newHead = solution(node0, 1);
        assert newHead == node0;
        assert newHead.next == node2;
        assert newHead.next.next == node3;
        assert newHead.next.next.next == null;
        // result is : node0 -> node2 -> node3
    }

    private static void test2() {
        Node<String> node6 = new Node<>("node6", null);
        Node<String> node5 = new Node<>("node5", node6);
        Node<String> node4 = new Node<>("node4", node5);
        Node<String> node3 = new Node<>("node3", node4);
        Node<String> node2 = new Node<>("node2", node3);
        Node<String> node1 = new Node<>("node1", node2);
        Node<String> node0 = new Node<>("node0", node1);
        Node<String> newHead = solution(node0, 0);


        Node<String> curNode = newHead;
        while (curNode != null){
            System.out.println(curNode.value);
            curNode = curNode.next;
        }
    }

    public static void main(String[] args) {
        test2();
    }
}