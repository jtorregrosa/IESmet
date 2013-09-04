package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import presenter.ActualChartsPresenter;
import model.IModel;

public class ActualChartsView implements IView {

    JPanel _mainPane;
	JFrame _frame;
	ChartPanel _temperatureChart;
	ChartPanel _pressureChart;
	ChartPanel _humidityChart;
	ChartPanel _wVelocityChart;
	ChartPanel _wDirectionChart;
	ChartPanel _rainGaugeChart;

	
	ActualChartsPresenter _presenter;

	public ActualChartsView(IModel model) {
                
		// Initializing members
                _mainPane = new JPanel();
		_frame = new JFrame("Mediciones en tiempo real");
		_temperatureChart = null;
		_pressureChart = null;
		_humidityChart = null;
		_wVelocityChart = null;
		_wDirectionChart = null;
		_rainGaugeChart = null;
		
		_presenter = new ActualChartsPresenter(model, this);

		// Creating all components
		init();
	}

        @Override
	public final void init() {
		// Creating Layout
		GridLayout layout = new GridLayout(2, 3);
                layout.setHgap(20);
                layout.setVgap(20);
                
		// Setting container panel
		_mainPane.setLayout(layout);
		_mainPane.setSize(1024, 768);
		_mainPane.add(_temperatureChart);
		_mainPane.add(_pressureChart);
		_mainPane.add(_humidityChart);
		_mainPane.add(_wVelocityChart);
		_mainPane.add(_wDirectionChart);
		_mainPane.add(_rainGaugeChart);
		_mainPane.setBackground(Color.white);

		// Setting, packing, and showing Frame
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                List<Image> iconList= new LinkedList<>();
        iconList.add(toolkit.getImage(getClass().getResource("/resources/icon16.png")));
        iconList.add(toolkit.getImage(getClass().getResource("/resources/icon32.png")));
        iconList.add(toolkit.getImage(getClass().getResource("/resources/icon64.png")));
        
        _frame.setIconImages(iconList);
        _frame.setMinimumSize(new Dimension(1024,600));
                _frame.setLayout(layout);
		_frame.setSize(1024, 600);
		_frame.setContentPane(_mainPane);
		_frame.setVisible(true);

		_frame.addWindowListener(new WindowListener() {
			@Override
			public void windowActivated(WindowEvent arg0) {
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				close();
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
			}
		});
	}

	@Override
	public void close() {
		_presenter.dispose();
	}

	public void setTemperatureChart(ChartPanel chart) {
		_temperatureChart = chart;
	}

	public void setPressureChart(ChartPanel chart) {
		_pressureChart = chart;
	}
	public void setHumidityChart(ChartPanel chart) {
		_humidityChart = chart;
	}

	public void setWVelocityChart(ChartPanel chart) {
		_wVelocityChart = chart;
	}

	public void setWDirectionChart(ChartPanel chart) {
		_wDirectionChart = chart;
	}

	public void setRainGaugeChart(ChartPanel chart) {
		_rainGaugeChart = chart;
	}
        
    @Override
        public void setEnabled(Boolean flag){
        _frame.setEnabled(flag);
    }

}
