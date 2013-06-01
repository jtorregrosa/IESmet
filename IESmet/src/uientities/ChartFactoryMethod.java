package uientities;

import org.jfree.chart.ChartPanel;
import org.jfree.data.general.Dataset;

import enums.ChartType;

public interface ChartFactoryMethod {
	public ChartPanel createChart(String title, Dataset dataSet, ChartType type);
}
