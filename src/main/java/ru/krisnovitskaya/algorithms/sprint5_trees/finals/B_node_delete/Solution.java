package ru.krisnovitskaya.algorithms.sprint5_trees.finals.B_node_delete;
// https://contest.yandex.ru/contest/24810/run-report/108362810/

/* -- ПРИНЦИП РАБОТЫ --
Алгоритм работы можно разбить на несколько этапов:
1) найти в цикле удаляемый элемент
2) найти в цикле элемент на замену
3) провести ряд проверок и перелинковать ноды
-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Алгоритм работает в среднем за O(H), где H - высота дерева.
Два цикла: поиск элемента + поиск замены = O(H), так как пройдя часть высоты дерева, при поиске ноды замены
 в большинстве случаев опускаемся до конца

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ
O(1) + O(N) -> O(N)
В реализации не использована рекурсия, перебор идет в цикле для экономии памяти.
Память локальных переменных можно представить как O(1).
O(N) пространственная сложность, где N - размер дерева.
 */

/*
B. Удали узел
 */
// <template>
class Node {
    private int value;
    private Node left;
    private Node right;

    Node(Node left, Node right, int value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
// <template>


public class Solution {
    public static Node remove(Node root, int key) {
        if (root == null) {
            return null;
        }
        return checkRootAndRemove(root, key);
    }

    private static Node checkRootAndRemove(Node root, int key) {
        if (root.getValue() == key) {
            return nodeReplace(null, root);
        }
        findAndRemove(null, root, key);
        return root;
    }

    private static void findAndRemove(Node parent, Node current, int key) {
        Node curParent = parent;
        Node cur = current;
        while (cur != null) {
            if (cur.getValue() == key) {
                nodeReplace(curParent, cur);
                break;
            } else if (cur.getValue() > key) {
                curParent = cur;
                cur = cur.getLeft();
            } else { //cur.getValue() < key
                curParent = cur;
                cur = cur.getRight();
            }
        }
    }

    private static Node nodeReplace(Node parent, Node current) {
        if (isLeaf(current)) {
            return removeLeaf(parent, current);
        }
        if (hasOneThread(current)) {
            return replaceNodeWithOneThread(parent, current);
        }
        return replaceNodeWithTwoThreads(parent, current);
    }

    private static Node replaceNodeWithTwoThreads(Node parent, Node current) {
        //twoThread
        //ищем самую правую вершину в левой ветви
        Node parentCurrentMaxRight = current;
        Node currentMaxRight = current.getLeft();
        while (currentMaxRight.getRight() != null) {
            parentCurrentMaxRight = currentMaxRight;
            currentMaxRight = currentMaxRight.getRight();
        }

        boolean isMaxFirstLeft = parentCurrentMaxRight == current;

        if (isMaxFirstLeft) {
            return relinkRemovingOnFirstLeft(parent, current, currentMaxRight);
        }

        return relinkRemovingOnReplacing(parent, current, parentCurrentMaxRight, currentMaxRight);
    }

    private static Node relinkRemovingOnReplacing(Node parent, Node removing, Node parentReplacing, Node replacing) {
        if (isLeaf(replacing)) {
            parentReplacing.setRight(null);
        } else {//есть левый потомок
            parentReplacing.setRight(replacing.getLeft());
        }

        if (parent == null) {
            replacing.setRight(removing.getRight());
            replacing.setLeft(removing.getLeft());
            return replacing;
        } else if (parent.getValue() > removing.getValue()) {
            parent.setLeft(replacing);
            replacing.setRight(removing.getRight());
            replacing.setLeft(removing.getLeft());
            return parent.getLeft();
        } else {
            parent.setRight(replacing);
            replacing.setRight(removing.getRight());
            replacing.setLeft(removing.getLeft());
            return parent.getRight();
        }
    }

    private static Node relinkRemovingOnFirstLeft(Node parent, Node removing, Node replacing) {
        if (parent == null) {
            replacing.setRight(removing.getRight());
            return replacing;
        } else if (parent.getValue() > removing.getValue()) {
            parent.setLeft(replacing);
            replacing.setRight(removing.getRight());
            return parent.getLeft();
        } else {
            parent.setRight(replacing);
            replacing.setRight(removing.getRight());
            return parent.getRight();
        }
    }

    private static Node replaceNodeWithOneThread(Node parent, Node current) {
        if (current.getLeft() == null) { //у удаляемой ноды только правая ветвь
            if (parent == null) {
                return current.getRight();
            } else if (parent.getValue() > current.getValue()) {
                parent.setLeft(current.getRight());
                return parent.getLeft();
            } else {
                parent.setRight(current.getRight());
                return parent.getRight();
            }
        } else { //у удаляемой ноды только левая ветвь
            if (parent == null) {
                return current.getLeft();
            } else if (parent.getValue() > current.getValue()) {
                parent.setLeft(current.getLeft());
                return parent.getLeft();
            } else {
                parent.setRight(current.getLeft());
                return parent.getRight();
            }
        }
    }

    private static Node removeLeaf(Node parent, Node current) {
        if (parent == null) {
            return null;
        } else if (parent.getValue() > current.getValue()) {
            parent.setLeft(null);
            return parent.getLeft();
        } else {
            parent.setRight(null);
            return parent.getRight();
        }
    }

    private static boolean isLeaf(Node node) {
        return node.getRight() == null && node.getLeft() == null;
    }

    private static boolean hasOneThread(Node node) {
        return node.getRight() == null ^ node.getLeft() == null;
    }
}