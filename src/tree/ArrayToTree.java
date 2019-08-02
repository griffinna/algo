package tree;

public class ArrayToTree {

    static class Tree {
        static class Node {
            int data;
            Node left;
            Node right;

            Node(int data) {
                this.data = data;
            }
        }
        Node root;
        void makeTree(int[] arr) {
            root = makeTreeR(arr, 0, arr.length - 1);
        }

        Node makeTreeR(int[] arr, int str, int end) {
            if(str > end) return null;  // 끝나는 시점 명시
            int mid = (str + end) / 2;
            Node node = new Node(arr[mid]);
            node.left = makeTreeR(arr, str, mid - 1);
            node.right = makeTreeR(arr, mid + 1, end);
            return node;
        }

        void searchBTree(Node n, int find) {  // n : 시작노드 , find : 찾는 값
            if (find < n.data) {
                System.out.println("Data is smaller than " + n.data);
                searchBTree(n.left, find);
            } else if (find > n.data) {
                System.out.println("Data is bigger than " + n.data);
                searchBTree(n.right, find);
            } else {
                System.out.println("Found Data! " + find);
            }
        }

    }

    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        Tree t = new Tree();
        t.makeTree(arr);
        t.searchBTree(t.root, 2);
    }

}
