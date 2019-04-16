package com.github.nezuko2038.javasnippets.jfreechart;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.PaintScale;
import org.jfree.chart.renderer.xy.AbstractXYItemRenderer;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRendererState;
import org.jfree.data.Range;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleAnchor;

/**
 *
 * @author Nezu Mariko<nezuko2038@gmail.com>
 * @since 2017-02-10
 */
public class VariableXYBlockRenderer extends AbstractXYItemRenderer
        implements XYItemRenderer, Cloneable, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8817277534640548573L;

    private TreeMap<Double, Double> heights = new TreeMap<>();

    private TreeMap<Double, Double> widths = new TreeMap<>();

    private final XYBlockRenderer renderer = new XYBlockRenderer();

    public VariableXYBlockRenderer() {
        renderer.setBlockAnchor(RectangleAnchor.BOTTOM_LEFT);
    }

    /**
     * Draws the block representing the specified item.
     *
     * @param g2 the graphics device.
     * @param state the state.
     * @param dataArea the data area.
     * @param info the plot rendering info.
     * @param plot the plot.
     * @param domainAxis the x-axis.
     * @param rangeAxis the y-axis.
     * @param dataset the dataset.
     * @param series the series index.
     * @param item the item index.
     * @param crosshairState the crosshair state.
     * @param pass the pass index.
     */
    @Override
    public void drawItem(final Graphics2D g2, final XYItemRendererState state,
            final Rectangle2D dataArea, final PlotRenderingInfo info, final XYPlot plot,
            final ValueAxis domainAxis, final ValueAxis rangeAxis, final XYDataset dataset,
            final int series, final int item, final CrosshairState crosshairState, final int pass) {
        final double x = dataset.getXValue(series, item);
        final double xWidth = getBlockWidth(x, domainAxis.getRange());
        final double y = dataset.getYValue(series, item);
        final double yHeight = getBlockHeight(y, rangeAxis.getRange());

        renderer.setBlockWidth(xWidth);
        renderer.setBlockHeight(yHeight);
        System.out.println(String.format("%d(%f,%f) (%f,%f)", item, x, y, xWidth, yHeight));
        renderer.drawItem(g2, state, dataArea, info, plot, domainAxis, rangeAxis, dataset, series,
                item, crosshairState, pass);

    }

    /**
     * Returns the lower and upper bounds (range) of the x-values in the
     * specified dataset.
     *
     * @param dataset the dataset (<code>null</code> permitted).
     * @return The range (<code>null</code> if the dataset is <code>null</code>
     *         or empty).
     * @see #findRangeBounds(XYDataset)
     */
    @Override
    public Range findDomainBounds(final XYDataset dataset) {
        return renderer.findDomainBounds(dataset);
    }

    /**
     * Returns the range of values the renderer requires to display all the
     * items from the specified dataset.
     *
     * @param dataset the dataset (<code>null</code> permitted).
     * @return The range (<code>null</code> if the dataset is <code>null</code>
     *         or empty).
     * @see #findDomainBounds(XYDataset)
     */
    @Override
    public Range findRangeBounds(final XYDataset dataset) {
        return renderer.findRangeBounds(dataset);
    }

    /**
     * Returns the block height, in data/axis units.
     *
     * @param y
     * @param range
     * @return The block height.
     * @see #setBlockHeight(double)
     */
    public double getBlockHeight(final double y, final Range range) {
        final Entry<Double, Double> e = heights.floorEntry(y);
        final double height;
        if (e == null) {
            height = range.getUpperBound() - y;
        } else {
            if (e.getValue() > 0) {

                height = e.getValue();
            } else {
                height = range.getUpperBound() - y;
            }
        }
        return height;
    }

    /**
     * Returns the block width, in data/axis units.
     *
     * @param x
     * @param range
     * @return The block width.
     * @see #setBlockWidth(double)
     */
    public double getBlockWidth(final double x, final Range range) {
        final Entry<Double, Double> e = widths.floorEntry(x);
        final double height;
        if (e == null) {
            height = range.getUpperBound() - x;
        } else {
            if (e.getValue() > 0) {

                height = e.getValue();
            } else {
                height = range.getUpperBound() - x;
            }
        }
        return height;
    }

    public void setBlockHeight(final double[] y) {
        heights = new TreeMap<>();
        for (int i = 0; i < y.length - 1; i++) {
            heights.put(y[i], y[i + 1] - y[i]);
        }
        heights.put(y[y.length - 1], -1.0);
        fireChangeEvent();
    }

    public void setBlockWidth(final double[] xx) {

        widths = new TreeMap<>();
        for (int i = 0; i < xx.length - 1; i++) {
            widths.put(xx[i], xx[i + 1] - xx[i]);
        }
        widths.put(xx[xx.length - 1], -1.0);
        fireChangeEvent();
    }

    /**
     * Sets the paint scale used by the renderer and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param scale the scale (<code>null</code> not permitted).
     * @see #getPaintScale()
     * @since 1.0.4
     */
    public void setPaintScale(final PaintScale scale) {
        renderer.setPaintScale(scale);

    }

}
