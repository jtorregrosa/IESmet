package uientities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Paint;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CompassPlot;
import org.jfree.chart.plot.DialShape;
import org.jfree.chart.plot.MeterInterval;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.chart.plot.ThermometerPlot;
import org.jfree.data.Range;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.ValueDataset;
import org.jfree.ui.RectangleInsets;

import enums.ChartType;

public class ChartFactory implements ChartFactoryMethod {

	@Override
	public ChartPanel createChart(String title, Dataset dataset, ChartType type) {
		ChartPanel cp = null;
		switch(type){
			case TEMPERATURE: 		cp = createTempChart(title,(ValueDataset)dataset);
									break;
			case PRESSURE: 			cp = createPressureChart(title,(ValueDataset)dataset);
									break;		
			case HUMIDITY:			cp = createHumidityChart(title,(ValueDataset)dataset);
									break;
			case WIND_VELOCITY:		cp = createWVelocityChart(title,(ValueDataset)dataset);
									break;
			case WIND_DIRECTION:	cp = createWDirectionChart(title,(ValueDataset)dataset);
									break;
			case RAIN_GAUGE:		cp = createRainGaugeChart(title,(ValueDataset)dataset);
									break;
		}
		return cp;
	}

	private ChartPanel createTempChart(String title, ValueDataset dataset){
		ThermometerPlot plot = new ThermometerPlot(dataset);
        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, false);               

        plot.setBulbRadius(40);
        plot.setSubrangeInfo(ThermometerPlot.NORMAL, 10.0, 25.0, -30.0, 60.0);
        plot.setSubrangeInfo(ThermometerPlot.WARNING, 25.0, 60.0, -30.0, 60.0);
        plot.setSubrangeInfo(ThermometerPlot.CRITICAL, -30.0, 10.0, -30.0, 60.0);
        
        plot.setSubrangePaint(ThermometerPlot.CRITICAL, new GradientPaint(0, 0, Color.CYAN, 0, 350, Color.BLUE));
        plot.setSubrangePaint(ThermometerPlot.NORMAL, new GradientPaint(0, 0, Color.GREEN, 0, 350, Color.BLUE));
        plot.setSubrangePaint(ThermometerPlot.WARNING, new GradientPaint(0, 0, Color.RED, 0, 350, Color.GREEN));
        
        plot.setBackgroundAlpha(0);
        plot.setOutlineVisible(false);
        
        plot.setInsets(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setPadding(new RectangleInsets(10D, 10D, 10D, 10D));
        plot.setThermometerStroke(new BasicStroke(2.0f));
        plot.setThermometerPaint(Color.lightGray);
        plot.setUnits(ThermometerPlot.UNITS_CELCIUS);
        plot.setRange(-30, 60);
        plot.setUnits(1);
        plot.setGap(4);
        return new ChartPanel(chart);       
	}
	
	private ChartPanel createPressureChart(String title, ValueDataset dataset){
		MeterPlot plot = new MeterPlot(dataset);
        plot.addInterval(new MeterInterval("Normal", new Range(1010.0, 1015.0), 
                Color.lightGray, new BasicStroke(2.0f), 
                new Color(0, 255, 0, 64)));
        plot.addInterval(new MeterInterval("Altas presiones", new Range(1015.0, 1100.0), 
                Color.lightGray, new BasicStroke(2.0f), new Color(255, 255, 0, 64)));
        plot.addInterval(new MeterInterval("Bajas presiones", new Range(850.0, 1010.0), 
                Color.lightGray, new BasicStroke(2.0f), 
                new Color(255, 0, 0, 128)));
        
        plot.setUnits("mBar");
        plot.setRange(new Range(850, 1100));
        plot.setNeedlePaint(Color.darkGray);
        plot.setDialBackgroundPaint(Color.white);
        plot.setDialOutlinePaint(Color.gray);
        plot.setDialShape(DialShape.PIE);
        plot.setMeterAngle(260);
        plot.setTickLabelsVisible(true);
        plot.setTickLabelFont(new Font("Dialog", Font.BOLD, 10));
        plot.setTickLabelPaint(Color.darkGray);
        plot.setTickSize(5.0);
        plot.setTickPaint(Color.lightGray);
        
        plot.setValuePaint(Color.black);
        plot.setValueFont(new Font("Dialog", Font.BOLD, 14));
        
        JFreeChart chart = new JFreeChart(title, 
                JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        return new ChartPanel(chart);     
	}
	
	private ChartPanel createHumidityChart(String title, ValueDataset dataset){
		
		MeterPlot plot = new MeterPlot(dataset);
		plot.setRange(new Range(0, 100));
        plot.addInterval(new MeterInterval("Normal", new Range(0.0, 35.0), 
                Color.lightGray, new BasicStroke(2.0f), 
                new Color(0, 255, 0, 64)));
        plot.addInterval(new MeterInterval("Warning", new Range(35.0, 50.0), 
                Color.lightGray, new BasicStroke(2.0f), new Color(255, 255, 0, 64)));
        plot.addInterval(new MeterInterval("Critical", new Range(50.0, 60.0), 
                Color.lightGray, new BasicStroke(2.0f), 
                new Color(255, 0, 0, 128)));
        plot.setNeedlePaint(Color.darkGray);
        plot.setDialBackgroundPaint(Color.white);
        plot.setDialOutlinePaint(Color.gray);
        plot.setDialShape(DialShape.CHORD);
        plot.setMeterAngle(260);
        plot.setTickLabelsVisible(true);
        plot.setTickLabelFont(new Font("Dialog", Font.BOLD, 10));
        plot.setTickLabelPaint(Color.darkGray);
        plot.setTickSize(5.0);
        plot.setTickPaint(Color.lightGray);
        
        plot.setValuePaint(Color.black);
        plot.setValueFont(new Font("Dialog", Font.BOLD, 14));
        
        JFreeChart chart = new JFreeChart(title, 
                JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        return new ChartPanel(chart);     
	}
	
	private ChartPanel createWVelocityChart(String title, ValueDataset dataset){
		MeterPlot plot = new MeterPlot(dataset);
		plot.setRange(new Range(0, 200));
        plot.addInterval(new MeterInterval("Normal", new Range(0.0, 35.0), 
                Color.lightGray, new BasicStroke(2.0f), 
                new Color(0, 255, 0, 64)));
        plot.addInterval(new MeterInterval("Warning", new Range(35.0, 50.0), 
                Color.lightGray, new BasicStroke(2.0f), new Color(255, 255, 0, 64)));
        plot.addInterval(new MeterInterval("Critical", new Range(50.0, 60.0), 
                Color.lightGray, new BasicStroke(2.0f), 
                new Color(255, 0, 0, 128)));
        plot.setNeedlePaint(Color.darkGray);
        plot.setDialBackgroundPaint(Color.white);
        plot.setDialOutlinePaint(Color.gray);
        plot.setDialShape(DialShape.CHORD);
        plot.setMeterAngle(260);
        plot.setTickLabelsVisible(true);
        plot.setTickLabelFont(new Font("Dialog", Font.BOLD, 10));
        plot.setTickLabelPaint(Color.darkGray);
        plot.setTickSize(5.0);
        plot.setTickPaint(Color.lightGray);
        
        plot.setValuePaint(Color.black);
        plot.setValueFont(new Font("Dialog", Font.BOLD, 14));
        
        JFreeChart chart = new JFreeChart(title, 
                JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        return new ChartPanel(chart);     
	}
	
	private ChartPanel createWDirectionChart(String title, ValueDataset dataset){
		CompassPlot plot = new CompassPlot(dataset);

        JFreeChart chart = new JFreeChart(title, 
                JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        return new ChartPanel(chart);     
	}
	
	private ChartPanel createRainGaugeChart(String title, ValueDataset dataset){
		MeterPlot plot = new MeterPlot(dataset);
        plot.addInterval(new MeterInterval("Normal", new Range(0.0, 35.0), 
                Color.lightGray, new BasicStroke(2.0f), 
                new Color(0, 255, 0, 64)));
        plot.addInterval(new MeterInterval("Warning", new Range(35.0, 50.0), 
                Color.lightGray, new BasicStroke(2.0f), new Color(255, 255, 0, 64)));
        plot.addInterval(new MeterInterval("Critical", new Range(50.0, 60.0), 
                Color.lightGray, new BasicStroke(2.0f), 
                new Color(255, 0, 0, 128)));
        plot.setNeedlePaint(Color.darkGray);
        plot.setDialBackgroundPaint(Color.white);
        plot.setDialOutlinePaint(Color.gray);
        plot.setDialShape(DialShape.CHORD);
        plot.setMeterAngle(260);
        plot.setTickLabelsVisible(true);
        plot.setTickLabelFont(new Font("Dialog", Font.BOLD, 10));
        plot.setTickLabelPaint(Color.darkGray);
        plot.setTickSize(5.0);
        plot.setTickPaint(Color.lightGray);
        plot.setValuePaint(Color.black);
        plot.setValueFont(new Font("Dialog", Font.BOLD, 14));
        
        JFreeChart chart = new JFreeChart(title, 
                JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        return new ChartPanel(chart);     
	}
}
