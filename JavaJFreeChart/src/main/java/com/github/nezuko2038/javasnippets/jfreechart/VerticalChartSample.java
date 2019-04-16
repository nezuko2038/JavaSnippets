package com.github.nezuko2038.javasnippets.jfreechart;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.GrayPaintScale;
import org.jfree.data.xy.DefaultXYZDataset;

/**
 *
 * @author Nezu Mariko<nezuko2038@gmail.com>
 * @since 2017-02-10
 */
public class VerticalChartSample extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = -7045617181190898462L;

    public static void main(final String[] args) {
        final JFrame f = new JFrame("");
        f.getContentPane().add(new VerticalChartSample());
        f.pack();
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                f.setVisible(true);

            }
        });

    }

    public VerticalChartSample() {
        final DefaultXYZDataset dataset = new DefaultXYZDataset();

        final double[][] data = new double[10][10];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = 0.1 * j + 0.2 * i;
            }
        }
        final double[] xx = new double[] { 0, 2 };
        final double[] yy = new double[] { 0, 3, 4 };
        dataset.addSeries("A", createDataArray(xx, yy, data));

        final XYPlot plot = new XYPlot();
        plot.setDomainAxis(new NumberAxis("A"));
        plot.getDomainAxis().setRange(0, 10);
        plot.setRangeAxis(new NumberAxis("B"));
        plot.getRangeAxis().setRange(0, 5);
        plot.getRangeAxis().setInverted(true);
        plot.setDomainAxisLocation(AxisLocation.TOP_OR_LEFT);
        final JFreeChart chart = new JFreeChart(plot);

        final VariableXYBlockRenderer r = new VariableXYBlockRenderer();
        r.setBlockHeight(yy);
        r.setBlockWidth(xx);
        r.setPaintScale(new GrayPaintScale());
        chart.getXYPlot().setRenderer(0, r);
        chart.getXYPlot().setDataset(0, dataset);
        final ChartPanel p = new ChartPanel(chart);
        add(p);
    }

    private double[][] createDataArray(final double[] x, final double[] y, final double[][] data) {
        final double[][] array = new double[3][x.length * y.length];
        int c = 0;
        for (int i = 0; i < y.length; i++) {
            for (int j = 0; j < x.length; j++) {
                array[0][c] = x[j];
                array[1][c] = y[i];
                array[2][c] = data[i][j];
                c++;
            }
        }
        return array;
    }
}
