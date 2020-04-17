public class BST<S> {
    class Node {
        private Comparable data;
        private Node leftChild;
        private Node rightChild;

        Node(Comparable item){
            data = item;
        }
    }

    Node root;

    boolean find (Comparable item) {
        return recursiveFind(root, item);
    }

    void insert(Comparable item) {
        root = recursiveInsert(root, item);
    }

    void print() {
        recursivePrint(root);
    }

    void delete(Comparable item) {
        root = recursiveDelete(root, item);
    }

    /* ------------ Helper functions ------------ */

    private boolean recursiveFind (Node node, Comparable item) {
        if (node == null) { // if root is empty return false
            return false;
        } else if (item.compareTo(node.data) == 0) { // if root is the item we are looking for
            return true;
        } else if (item.compareTo(node.data) < 0) { // if it is smaller we shift to the left
            return recursiveFind(node.leftChild, item);
        } else { // if it is bigger we shift to the right
            return recursiveFind(node.rightChild, item);
        }
    }


    private Node recursiveInsert(Node node, Comparable item) {
        if (node == null) { // if root is empty we initialize it
            return new Node(item);
        } else if (item.compareTo(node.data) < 0) { // if it is smaller we shift to the right
            node.leftChild = recursiveInsert(node.leftChild, item);
            return node;
        } else { // if it is bigger we shift to the right
            node.rightChild = recursiveInsert(node.rightChild, item);
            return node;
        }
    }

    private void recursivePrint(Node node) {
        if (node != null) { // printing left chils, then root and then right childs
            recursivePrint(node.leftChild);
            System.out.println(node.data);
            recursivePrint(node.rightChild);
        }
    }

    private Node recursiveDelete(Node node, Comparable item) {
        if (node == null) { // if there is nothing to delete
            return null;
        }

        if (node.data.compareTo(item) == 0) { // if we are to delete the node
            if (node.leftChild == null) {
                return node.rightChild;
            } else if (node.rightChild == null) {
                return node.leftChild;
            } else {
                if (node.rightChild.leftChild == null) {
                    node.data = node.rightChild.data;
                    node.rightChild = node.rightChild.rightChild;
                } else {
                    node.data = removeSmallest(node.rightChild);
                }
                return node;
            }
        } else if (item.compareTo(node.data) < 0) { // shift to the left recursively until item to be deleted is found
            node.leftChild = recursiveDelete(node.leftChild, item);
            return node;
        } else { // shift to the right recursively until item to be deleted is found
            node.rightChild= recursiveDelete(node.rightChild, item);
            return node;
        }
    }


    Comparable removeSmallest(Node node) { // recursively searches for the smallest value in BST
        if(node.leftChild.leftChild == null) {
            Comparable smallest = node.leftChild.data;
            node.leftChild = node.leftChild.rightChild;
            return smallest;
        } else {
            return removeSmallest(node.leftChild);
        }
    }
}

