package ru.krisnovitskaya.algorithms.sprint5_trees.B_balanced_tree;

public class Solution {
    public static boolean treeSolution(Node head) {
        if (head == null) {
            return true;
        }
        Result countLeft = count(head.left);
        Result countRight = count(head.right);
        if(!(countLeft.isOK() && countRight.isOK())){
            return false;
        }
        return (Math.abs(countLeft.getCount() - countRight.getCount()) <=1);
    }


    private static Result count(Node node) {
        if (node == null) {
            return new Result(0, true);
        }
        Result countLeft = count(node.left);
        Result countRight = count(node.right);
        return new Result(Math.max(countLeft.getCount(), countRight.getCount()) + 1, Math.abs(countLeft.getCount() - countRight.getCount()) <=1);
    }
    private static class Result{
        int count;
        boolean isOK;

        public Result(int count, boolean isOK) {
            this.count = count;
            this.isOK = isOK;
        }

        public int getCount() {
            return count;
        }

        public boolean isOK() {
            return isOK;
        }
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


        Node n2 = new Node(2);
        Node n1 = new Node(1);
        Node n0 = new Node(0);
        n0.right = n1;
        n1.right = n2;
//        0 0 None 1
//        1 1 None 2
//        2 2 None None
        System.out.println(treeSolution(n0));
    }

    public static void main(String[] args) {
        test();
    }
}