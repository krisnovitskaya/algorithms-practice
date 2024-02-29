package ru.krisnovitskaya.algorithms.sprint5_trees.D_tree_tween;

public class Solution {
    public static boolean treeSolution(Node head1, Node head2) {
        StringBuilder sbLeft = printLMR(head1, new StringBuilder());
        StringBuilder sbRight = printLMR(head2, new StringBuilder());
        return sbLeft.toString().equals(sbRight.toString());
    }

    private static StringBuilder printLMR(Node vertex, StringBuilder sb) {
        if (vertex.left != null) {
            printLMR(vertex.left, sb);
        }
        sb.append(vertex.value);
        if (vertex.right != null) {
            printLMR(vertex.right, sb);
        }
        return sb;
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

        Node(int value, Node left, Node right) {  
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
    // <template>
    
    private static void test() {
        Node node1 = new Node(1,  null,  null);
        Node node2 = new Node(2,  null,  null);
        Node node3 = new Node(3,  node1,  node2);
        Node node4 = new Node(1,  null,  null);
        Node node5 = new Node(2,  null,  null);
        Node node6 = new Node(3,  node4,  node5);
        assert treeSolution(node3, node6);
    }
}