package com.github.nezuko2038.javasnippets.swing.jtree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 * JTree内のノードを非活性表示するサンプルコード.
 *
 * @author Nezu Mariko<nezuko2038@gmail.com>
 * @since 2017-02-10
 */
public class JTreeで非活性 {
    private static MutableTreeNode createNode(final String string) {
        final DefaultMutableTreeNode node = new DefaultMutableTreeNode(string);
        for (int i = 0; i < 3; i++) {
            node.add(new DefaultMutableTreeNode(String.format("%s%d", string, i)));
        }
        return node;
    }

    private static DefaultTreeModel createTreeModel() {
        final DefaultMutableTreeNode root = new DefaultMutableTreeNode("ROOT");
        root.add(createNode("A"));
        root.add(createNode("B"));
        root.add(createNode("C"));
        final DefaultTreeModel model = new DefaultTreeModel(root);

        return model;
    }

    /**
     * メイン
     *
     * @param args 引数
     */
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                final JFrame f = new JFrame("JTreeで非活性");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                final TestTree tree = new TestTree();
                // テストデータ追加
                final DefaultTreeModel model = createTreeModel();
                tree.setModel(model);

                // 非活性を設定
                final TreeNode root = (TreeNode) model.getRoot();
                final TreeNode n = root.getChildAt(1);
                final TreePath path = TestTree.getPath(n);
                tree.setEnabled(path, false);

                f.getContentPane().add(tree);
                f.pack();
                f.setVisible(true);
            }
        });
    }

    private JTreeで非活性() {
    }
}

class TestTree extends JPanel {

    class CustomCellRenderer extends DefaultTreeCellRenderer {

        private static final long serialVersionUID = -1522048428456708227L;

        @Override
        public Component getTreeCellRendererComponent(final JTree tree, final Object value,
                final boolean sel, final boolean expanded, final boolean leaf, final int row,
                final boolean hasFocus) {
            final DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            final TreePath path = getPath(node);
            boolean enable = true;
            // 非活性リストにあるものはenableをfalseにする
            if (disable.contains(path)) {
                enable = false;
            }
            final Component c = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
                    row, hasFocus);
            // ★enableのものはグレー表示
            if (!enable) {
                c.setForeground(Color.gray);
            }

            return c;
        }

    }

    class CustomSelectionModel extends DefaultTreeSelectionModel {

        private static final long serialVersionUID = 7373646511570479799L;

        @Override
        public void setSelectionPath(final TreePath path) {

            if (disable.contains(path)) {
                return;
            }
            super.setSelectionPath(path);
        }

        @Override
        public void setSelectionPaths(final TreePath[] pPaths) {
            // カーソルキーによる選択はこちらを通る
            // 無視リストにあるものへカーソル移動した場合は無視
            final List<TreePath> l = new LinkedList<>(Arrays.asList(pPaths));
            l.removeIf(new Predicate<TreePath>() {

                @Override
                public boolean test(final TreePath t) {
                    return disable.contains(t);

                }
            });
            if (!l.isEmpty()) {
                super.setSelectionPaths(l.toArray(new TreePath[l.size()]));
            }
        }

    }

    /**
     *
     */
    private static final long serialVersionUID = 7019213129526723010L;

    /**
     * TreeNodeをTreePathに変換する.
     *
     * @param treeNode ノード
     * @return TreePathオブジェクト
     */
    static TreePath getPath(final TreeNode treeNode) {
        final List<Object> nodes = new ArrayList<>();
        TreeNode n = treeNode;
        if (n != null) {
            nodes.add(n);
            n = n.getParent();
            while (n != null) {
                nodes.add(0, n);
                n = n.getParent();
            }

        }

        return nodes.isEmpty() ? null : new TreePath(nodes.toArray());
    }

    private final JTree jtree = new JTree() {
        /**
         *
         */
        private static final long serialVersionUID = -7050681124564310296L;

        /*
         * (non-Javadoc)
         * @see javax.swing.JTree#expandPath(javax.swing.tree.TreePath)
         */
        @Override
        public void expandPath(final TreePath path) {
            // ★非活性の場合はフォルダを開かない
            final boolean a = disable.contains(path);
            if (!a) {
                super.expandPath(path);
            }
        };
    };

    /**
     * 非活性リスト。
     */
    private final List<TreePath> disable = new ArrayList<>();

    TestTree() {

        setLayout(new BorderLayout());
        add(jtree, BorderLayout.CENTER);

        jtree.setCellRenderer(new CustomCellRenderer());
        jtree.setSelectionModel(new CustomSelectionModel());

    }

    /**
     * ノードの非活性を設定する。
     *
     * @param path パス
     * @param b 非活性かどうか
     */
    public void setEnabled(final TreePath path, final boolean b) {
        if (b) {
            disable.remove(path);
        } else {
            disable.add(path);
        }
    }

    /**
     * モデルを設定する。
     *
     * @param model モデル
     */
    public void setModel(final DefaultTreeModel model) {
        jtree.setModel(model);
    }
}
