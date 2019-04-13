package com.github.nezuko2038.javasnippets.swing.focus;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * エンターでフォーカス移動。
 * 
 * @since 2017-02-10
 * @author Nezu Mariko<nezuko2038@gmail.com>
 */
public class フォーカス移動の順番を制御 {
    static class SampleWindow extends JDialog {

        /**
         * serialVersionUID
         */
        private static final long serialVersionUID = -6372119401773631924L;

        public SampleWindow() {
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            setPreferredSize(new Dimension(400, 300));

            // コンポーネントの追加
            final JPanel p1 = createSamplePanel("A", 5);
            add(p1);
            final JPanel p2 = createSamplePanel("B", 5);
            add(p2);
            final JPanel p3 = createSamplePanel("C", 3);
            add(p3);
            // 適当にレイアウトを配置
            final SpringLayout l = new SpringLayout();
            l.putConstraint(SpringLayout.NORTH, p1, 100, SpringLayout.NORTH, this);
            l.putConstraint(SpringLayout.NORTH, p2, 200, SpringLayout.NORTH, this);
            l.putConstraint(SpringLayout.WEST, p2, 200, SpringLayout.WEST, this);
            l.putConstraint(SpringLayout.NORTH, p3, 0, SpringLayout.NORTH, this);
            setLayout(l);

            pack();
        }
    }

    public static JPanel createSamplePanel(final String name, final int n) {
        final JPanel p = new JPanel();
        p.setBorder(new TitledBorder(new EtchedBorder(), "パネル" + name));
        for (int i = 0; i < n; i++) {
            final JTextField comp = new JTextField();
            comp.setText(String.valueOf(i + 1));
            p.add(comp);

        }
        return p;
    }

    public static void main(final String[] args) {

        final SampleWindow w = new SampleWindow();
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                w.setVisible(true);
            }
        });
    }
}
