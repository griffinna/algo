package algo.tree;

public class TreeStruct {

    static class Node {
        int data;
        Node left;
        Node right;
    }

    static class Tree {
        Node root;

        Node getRoot() {
            return root;
        }

        void setRoot(Node root) {
            this.root = root;
        }

        Node makeNode(Node left, int data, Node right) {
            Node node = new Node();
            node.data = data;
            node.left = left;
            node.right = right;
            return node;
        }

        void inorder(Node node) {    // left > root > right
            if (node != null) {
                inorder(node.left);
                System.out.print(node.data + " ");
                inorder(node.right);
            }
        }

        void preorder(Node node) {  // root > left > right
            if (node != null) {
                System.out.print(node.data + " ");
                preorder(node.left);
                preorder(node.right);
            }
        }

        void postorder(Node node) { // left > right > root
            if (node != null) {
                postorder(node.left);
                postorder(node.right);
                System.out.print(node.data + " ");
            }
        }
    }

    public static void main(String[] args) {

        Tree tree  = new Tree();
        Node n4 = tree.makeNode(null, 4, null);
        Node n5 = tree.makeNode(null, 5, null);
        Node n2 = tree.makeNode(n4, 2, n5);
        Node n3 = tree.makeNode(null, 3, null);
        Node n1 = tree.makeNode(n2, 1, n3);

        tree.setRoot(n1);

        System.out.print("Inorder : ");
        tree.inorder(tree.getRoot());
        System.out.println();
        System.out.print("Preorder : ");
        tree.preorder(tree.getRoot());
        System.out.println();
        System.out.print("Postorder : ");
        tree.postorder(tree.getRoot());

    }

}
