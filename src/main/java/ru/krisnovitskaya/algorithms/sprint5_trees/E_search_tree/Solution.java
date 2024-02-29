package ru.krisnovitskaya.algorithms.sprint5_trees.E_search_tree;

public class Solution {
    public static boolean treeSolution(Node head) {

        int line = head.value;
        return checkLeft(head.left, line) && checkRight(head.right, line);
    }

    private static boolean checkRight(Node rightNode, int parentValue) {
        if(rightNode == null) {return true;}
        if(rightNode.value <= parentValue) {return false;}
        boolean checkRightLeft = checkRightLeft(rightNode.left, rightNode.value, parentValue);
        boolean checkRightRight = checkRightRight(rightNode.right, rightNode.value, parentValue);
        return checkRightLeft && checkRightRight;
    }

    private static boolean checkRightRight(Node rightNode, int value, int line) {
        if(rightNode == null) {return true;}
        if(rightNode.value <= line || rightNode.value <= value) {return false;}
        boolean checkRightLeft = checkRightLeft(rightNode.left, rightNode.value, line);
        boolean checkRightRight = checkRightRight(rightNode.right, rightNode.value, line);
        return checkRightLeft && checkRightRight;
    }

    private static boolean checkRightLeft(Node rightNode, int value, int line) {
        if(rightNode == null) {return true;}
        if(rightNode.value <= line || rightNode.value >= value) {return false;}
        boolean checkRightLeft = checkRightLeft(rightNode.left, rightNode.value, line);
        boolean checkRightRight = checkRightRight(rightNode.right, rightNode.value, line);
        return checkRightLeft && checkRightRight;
    }

    private static boolean checkLeft(Node leftNode, int parentValue) {
        if(leftNode == null) {return true;}
        if(leftNode.value > parentValue) {return false;}
        boolean checkLeftLeft = checkLeftLeft(leftNode.left, leftNode.value, parentValue);
        boolean checkLeftRight = checkLeftRight(leftNode.right, leftNode.value, parentValue);
        return checkLeftLeft && checkLeftRight;
    }

    private static boolean checkLeftLeft(Node leftNode, int value, int line) {
        if(leftNode == null) {return true;}
        if(leftNode.value >= line || leftNode.value >= value) {return false;}
        boolean checkLeftLeft = checkLeftLeft(leftNode.left, leftNode.value, line);
        boolean checkLeftRight = checkLeftRight(leftNode.right, leftNode.value, line);
        return checkLeftLeft && checkLeftRight;
    }

    private static boolean checkLeftRight(Node rightNode, int value, int line) {
        if(rightNode == null) {return true;}
        if(rightNode.value <= value || rightNode.value >= line) {return false;}
        boolean checkLeftLeft = checkLeftLeft(rightNode.left, rightNode.value, line);
        boolean checkLeftRight = checkLeftRight(rightNode.right, rightNode.value, line);
        return checkLeftLeft && checkLeftRight;
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
        Node node1 = new Node(1, null, null);
        Node node2 = new Node(4, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(8, null, null);
        Node node5 = new Node(5, node3, node4);
        assert treeSolution(node5);
        System.out.println(treeSolution(node5));
        node2.value = 3;
        assert !treeSolution(node5);
        System.out.println(treeSolution(node5));

    }

//    public static void main(String[] args) {
//        test();
//    }
}