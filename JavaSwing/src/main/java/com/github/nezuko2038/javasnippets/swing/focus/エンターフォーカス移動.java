package com.github.nezuko2038.javasnippets.swing.focus;

import java.awt.AWTKeyStroke;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * エンターでフォーカス移動。
 * 
 * @since 2017-02-10
 * @author Nezu Mariko<nezuko2038@gmail.com>
 */
public class エンターフォーカス移動 {
    static class SampleWindow extends JDialog {

        /**
         * serialVersionUID
         */
        private static final long serialVersionUID = -6372119401773631924L;

        public SampleWindow() {
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            setPreferredSize(new Dimension(400, 300));
            setLayout(new FlowLayout(FlowLayout.LEADING));
            final JPanel p1 = createSamplePanel(5);

            add(p1);
            final JPanel p2 = createSamplePanel(5);
            add(p2);
            final JPanel p3 = createSamplePanel(3);
            add(p3);
            // ★エンターで移動
            final Set<AWTKeyStroke> forwardKeys = new HashSet<>(
                    this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
            forwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));

            setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, forwardKeys);
            pack();
        }
    }

    public static JPanel createSamplePanel(final int n) {
        final JPanel p = new JPanel();
        p.setBorder(new TitledBorder(new EtchedBorder(), "パネル"));
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
