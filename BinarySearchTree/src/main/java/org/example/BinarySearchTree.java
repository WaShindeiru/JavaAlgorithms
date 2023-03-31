package org.example;

import javax.naming.BinaryRefAddr;
import java.util.*;

public class BinarySearchTree <T extends Comparable<T>> implements Iterable<T>{

    private int nodeCount = 0;
    private Node root = null;

    class Node {
        private Node left;
        private Node right;
        private Node parent;
        private T data;

        public Node(T data, Node left, Node right, Node parent){
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

//        public Node getLeft() {
//            return left;
//        }
//
//        public Node getRight() {
//            return right;
//        }
//
//        public Node getParent() {
//            return parent;
//        }
//
//        public T getData() {
//            return data;
//        }
    }

    public BinarySearchTree() {
        this.nodeCount = 0;
        this.root = null;
    }

    public BinarySearchTree(T data) {
        this.root = new Node(data, null, null, null);
        this.nodeCount = 1;
    }

    public Node Minimum() {
        Node temp = root;
        while(temp.left != null){
            temp = temp.left;
        }
        return temp;
    }

    public Node Maximum() {
        Node temp = root;
        while(temp.right != null){
            temp = temp.right;
        }
        return temp;
    }

    public void insert(T data){
        insertPrivate(new Node(data, null, null, null));
        nodeCount++;
    }

    private void insertPrivate(Node z){
        Node under = root;
        Node above = null;

        while(under != null){
            above = under;
            if(z.data.compareTo(under.data) < 0){
                under = under.left;
            }
            else{
                under = under.right;
            }
        }

        z.parent = above;
        if(above == null){
            root = z;
        }
        else if(z.data.compareTo(above.data) < 0){
            above.left = z;
        }
        else {
            above.right = z;
        }
    }

    public int size(){
        return this.nodeCount;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    @Override
    public String toString() {
        return showWalk(root);
    }

    public String showWalk(Node x){
        StringBuilder temp = new StringBuilder();
        InorderTreeWalk(x, temp);
        return temp.toString();
    }

    private void InorderTreeWalk(Node x, StringBuilder output){
        if(x != null){
            InorderTreeWalk(x.left, output);
            output.append(x.data).append("\n");
            InorderTreeWalk(x.right, output);

        }
    }


    @Override
    public Iterator<T> iterator() {

        final Queue<Node> buffer = new LinkedList<>();
        buffer.add(root);
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return (root != null && !buffer.isEmpty());
            }

            @Override
            public T next() {
                Node data = buffer.poll();
                if (data.left != null)
                    buffer.add(data.left);
                if (data.right != null)
                    buffer.add(data.right);

                return data.data;
            }
        };
    }
}

class example{
    public static void main(String[] args) {
        BinarySearchTree<Integer> aha = new BinarySearchTree<>();
//        Scanner scanner = new Scanner(System.in);
//        String buffer;
//        buffer = scanner.nextLine().replace("\n", "");
//        int number = Integer.parseInt(buffer);
//        while (number != -1) {
//            aha.insert(number);
//            System.out.println(aha);
//            number = Integer.parseInt(scanner.nextLine().replace("\n", ""));
//        }
        aha.insert(21);
        aha.insert(6);
        aha.insert(18);
        aha.insert(20);
        aha.insert(30);
        aha.insert(50);
        var iter = aha.iterator();
        while(iter.hasNext())
            System.out.println(iter.next());
    }
}
