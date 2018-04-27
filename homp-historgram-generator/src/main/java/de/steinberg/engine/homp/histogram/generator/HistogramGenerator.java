package de.steinberg.engine.homp.histogram.generator;

import com.panayotis.gnuplot.dataset.Point;
import com.panayotis.gnuplot.dataset.PointDataSet;
import de.steinberg.engine.homp.histogram.parser.xml.entities.Document;

public class HistogramGenerator {

    public Histogram generateHistogram (Document document) {
        Histogram histogram = new Histogram();

        PointDataSet<Float> dataSet = new PointDataSet<>();

        for (Document.Bin bin : document.histogram.bins) {
            Point<Float> point = new Point<>(bin.value, Float.valueOf(bin.elementValue));
            dataSet.add(point);
        }
        histogram.plot.addPlot(dataSet);
        return histogram;
    }

}
