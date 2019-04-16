package com.github.nezuko2038.javasnippets.jfreechart;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisState;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DefaultXYItemRenderer;
import org.jfree.data.xy.DefaultXYDataset;

/**
 *
 * @author Nezu Mariko<nezuko2038@gmail.com>
 * @since 2017-02-10
 */
public class ゼロ値に軸を表示 {
    static class Chart extends JPanel {
		/**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = 6627992156459973888L;
		private final XYPlot plot = new XYPlot();
        private final JFreeChart chart = new JFreeChart(plot);
        private final ChartPanel chartPanel = new ChartPanel(chart);

        public Chart() {
            // 凡例出さない
            chart.setSubtitles(new ArrayList<>());
            // 横軸
            final NumberAxis xAxis = new NumberAxis() {
				/**
				 *
				 */
				private static final long serialVersionUID = -8269749065934731972L;

				@Override
                public org.jfree.chart.axis.AxisState draw(final java.awt.Graphics2D g2,
                        double cursor, final java.awt.geom.Rectangle2D plotArea,
                        final java.awt.geom.Rectangle2D dataArea,
                        final org.jfree.ui.RectangleEdge edge,
                        final org.jfree.chart.plot.PlotRenderingInfo plotState) {
                    // ★縦軸でデータ値0になるような位置を求める
                    cursor = plot.getRangeAxis().valueToJava2D(0, dataArea,
                            plot.getRangeAxisEdge());
                    // ↓単純にグラフ中央に軸を描画したい場合
                    // cursor = dataArea.getCenterY();
                    final AxisState s = super.draw(g2, cursor, plotArea, dataArea, edge, plotState);
                    return s;
                };
            };
            // ★角度軸 360度表記
            final NumberFormat format = new NumberFormat() {
				/**
				 * serialVersionUID
				 */
				private static final long serialVersionUID = -5169795302146276023L;
				private final DecimalFormat df = new DecimalFormat();

                @Override
                public StringBuffer format(double number, final StringBuffer toAppendTo,
                        final FieldPosition pos) {
                    number = (number + 360) % 360;

                    return df.format(number, toAppendTo, pos);
                }

                @Override
                public StringBuffer format(long number, final StringBuffer toAppendTo,
                        final FieldPosition pos) {
                    number = (number + 360) % 360;
                    return df.format(number, toAppendTo, pos);
                }

                @Override
                public Number parse(final String source, final ParsePosition parsePosition) {
                    return df.parse(source, parsePosition);
                }
            };

            xAxis.setLabel("AAA");
            xAxis.setAutoRange(true);
            xAxis.setAutoRangeStickyZero(true);
            xAxis.setAxisLineVisible(true);
            xAxis.setNumberFormatOverride(format);// 表示方法を指定

            // 縦軸
            final NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("BBB");
            yAxis.setRange(-10, +20);

            plot.setDomainAxis(xAxis);
            plot.setRangeAxis(yAxis);

            final DefaultXYDataset dataset = new DefaultXYDataset();
            final double[][] data = { { -80, 0, 1, 80 }, { 1, 2, 3, 4 } };
            dataset.addSeries("CCC", data);
            plot.setDataset(0, dataset);

            // レンダラ
            plot.setRenderer(new DefaultXYItemRenderer());

            chartPanel.setMouseZoomable(false);
            add(chartPanel);
        }
    }

    public static void main(final String[] args) {
        final JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Chart panel = new Chart();
        f.getContentPane().add(panel);

        f.pack();

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                f.setVisible(true);

            }

        });
    }
}
