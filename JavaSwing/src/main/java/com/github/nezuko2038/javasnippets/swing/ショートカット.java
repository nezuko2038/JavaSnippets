package com.github.nezuko2038.javasnippets.swing;

import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

/**
 * ショートカットでのアクション発生時にフォーカスをボタンにする。
 *
 * @author Nezu Mariko<nezuko2038@gmail.com>
 * @since 2017-04-15
 */
public class ショートカット {
    static class SamplePanel extends JPanel {
        /**
         *
         */
        private static final long serialVersionUID = -3452899227975209806L;
        private final JButton b = new JButton("COMMAND");

        private final JTextField f = new JTextField(10);
        private final AbstractAction action = new AbstractAction("A") {

            /**
             *
             */
            private static final long serialVersionUID = 7863303877394668161L;

            @Override
            public void actionPerformed(final ActionEvent e) {
                System.out.println("ACTION ");
            }

        };

        public SamplePanel() {
            f.addFocusListener(new FocusAdapter() {

                /*
                 * (non-Javadoc)
                 * @see java.awt.event.FocusAdapter#focusLost(java.awt.event.
                 * FocusEvent)
                 */
                @Override
                public void focusLost(final FocusEvent e) {
                    System.out.println("focus lost");

                }
            });

            b.setAction(action);

            final AbstractAction wrapper = new AbstractAction() {

                /**
                 *
                 */
                private static final long serialVersionUID = -529748454890649656L;

                @Override
                public void actionPerformed(final ActionEvent e) {
                    action.actionPerformed(e);
                    b.requestFocus();
                }
            };
            final KeyStroke keyStroke = KeyStroke.getKeyStroke("shift X");
            final Object actionMapKey = "A";
            b.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, actionMapKey);
            b.getInputMap(JButton.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(keyStroke, actionMapKey);
            b.getActionMap().put(actionMapKey, wrapper);

            // 追加
            add(f);
            add(b);

        }

    }

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                final JFrame f = new JFrame();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.getContentPane().add(new SamplePanel());
                f.pack();
                f.setVisible(true);
            }
        });
    }
}
