package com.github.nezuko2038.javasnippets.swing.jtable;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * JTableで1行非表示
 *
 * @author Nezu Mariko<nezuko2038@gmail.com>
 * @since 2017-02-23
 */
public class JTableで1行非表示 {

    static class SubWindow extends JDialog {
		/**
		 *
		 */
		private static final long serialVersionUID = 2399854693873086080L;
		private final DefaultTableModel model = new DefaultTableModel(10, 3) {
            /**
             *
             */
            private static final long serialVersionUID = -4887848553549583457L;

            @Override
            public Object getValueAt(final int row, final int column) {
                return String.format("%d,%d", row, column);
            };
        };
        private final JTable table = new JTable();

        public SubWindow() {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            final Container c = getContentPane();
            final JPanel p = new JPanel();
            p.setLayout(new BorderLayout());
            model.setColumnIdentifiers(new Object[] { "A", "B", "C" });
            table.setModel(model);

            final TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
            sorter.setRowFilter(new RowFilter<TableModel, Integer>() {

                @Override
                public boolean include(
                        final javax.swing.RowFilter.Entry<? extends TableModel, ? extends Integer> entry) {
                    return entry.getIdentifier() != 2; // ★ 2行目だけ見せない

                }
            });
            table.setRowSorter(sorter);
            p.add(new JScrollPane(table), BorderLayout.CENTER);
            c.add(p);
            pack();
        }
    }

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                final SubWindow w = new SubWindow();
                w.setVisible(true);
            }
        });
    }
}
