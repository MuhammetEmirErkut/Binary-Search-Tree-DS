package TreeProject;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class BST {
    private TreeNode root;

    public BST() {
        this.root = null;
    }

    // Yeni bir eleman eklemek için kullanılan metod
    public void insert(int x) {
        root = insertRecursive(root, x);
    }

    // Yeni bir eleman eklemek için kullanılan özel (recursive) metod
    private TreeNode insertRecursive(TreeNode node, int x) {
        if (node == null) {
            return new TreeNode(x);
        }

        if (x < node.data) {
            node.left = insertRecursive(node.left, x);
        } else if (x > node.data) {
            node.right = insertRecursive(node.right, x);
        }

        return node;
    }

    // Belirli bir elemanı silmek için kullanılan metod
    public void delete(int x) {
        root = deleteRecursive(root, x);
    }

    // Belirli bir elemanı silmek için kullanılan özel (recursive) metod
    private TreeNode deleteRecursive(TreeNode node, int x) {
        if (node == null) {
            return null;
        }

        if (x < node.data) {
            node.left = deleteRecursive(node.left, x);
        } else if (x > node.data) {
            node.right = deleteRecursive(node.right, x);
        } else {
            // Silinecek düğüm bulunduğunda
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            // İki çocuğu olan düğüm için: En küçük sağ alt ağacın değerini al
            node.data = minValue(node.right);

            // En küçük değere sahip düğümü sil
            node.right = deleteRecursive(node.right, node.data);
        }

        return node;
    }

    // Verilen bir düğüm altındaki en küçük değeri bulmak için kullanılan metod
    private int minValue(TreeNode node) {
        int minValue = node.data;
        while (node.left != null) {
            minValue = node.left.data;
            node = node.left;
        }
        return minValue;
    }

    // Ağacı görselleştirmek için kullanılan metod
    public void draw() {
        JFrame frame = new JFrame("BST Görselleştirme");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawTree(g, getWidth() / 2, 30, root, getWidth() / 4, 0);
            }
        };

        frame.getContentPane().add(panel);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Ağacı çizmek için kullanılan özel (recursive) metod
    private void drawTree(Graphics g, int x, int y, TreeNode node, int xOffset, int level) {
        if (node != null) {
            g.drawOval(x - 20, y - 20, 40, 40);
            g.drawString(Integer.toString(node.data), x - 5, y + 5);

            if (node.left != null) {
                int nextX = x - xOffset / (int) Math.pow(2, level + 2) - 40;
                int nextY = y + 60;
                g.drawLine(x, y, nextX, nextY);
                drawTree(g, nextX, nextY, node.left, xOffset / 2, level + 1);
            }

            if (node.right != null) {
                int nextX = x + xOffset / (int) Math.pow(2, level + 2) + 40;
                int nextY = y + 60;
                g.drawLine(x, y, nextX, nextY);
                drawTree(g, nextX, nextY, node.right, xOffset / 2, level + 1);
            }
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(25);
        bst.insert(19);
        bst.insert(80);
        bst.insert(59);
        bst.insert(62);

        bst.draw();
    }
}

// Ağaç düğümlerini temsil eden sınıf
class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
