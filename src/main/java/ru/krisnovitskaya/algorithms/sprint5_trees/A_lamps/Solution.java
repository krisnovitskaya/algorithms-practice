package ru.krisnovitskaya.algorithms.sprint5_trees.A_lamps;

public class Solution {
    public static int treeSolution(Node head) {
        return maxValue(head, head.value);
    }

    public static int maxValue(Node node, int curmax){
        if(node == null){
            return curmax;
        }
        int maxLeft = maxValue(node.left, curmax);
        int maxRight = maxValue(node.right, curmax);
        return Math.max(Math.max(maxLeft, maxRight), node.value);
    }

    // <template>
    private static class Node {
        int value;  
        Node left;  
        Node right;  
    
        Node(int value) {  
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
    // <template>


    private static void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(2);
        node4.left = node3;
        int i = treeSolution(node4);
        System.out.println(i);
        assert treeSolution(node4) == 3;
    }
}