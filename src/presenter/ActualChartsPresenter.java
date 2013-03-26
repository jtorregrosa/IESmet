package presenter;

/**
 * @author Jorge Torregrosa Lloret
 * @version 1.0
 * 
 * Main presenter of the application. It get the MainView and the MainModel and mediates between them.
 * It updates the View and get the user inputs. It also updates the Model.
 */

import java.util.Observable;
import java.util.Observer;

import org.jfree.chart.ChartPanel;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;

import uientities.ChartFactory;
import view.ActualChartsView;
import view.IView;
import view.MainView;

import comm.SerialData;
import comm.SerialDirector;
import director.Director;
import enums.ChartType;

import model.ActualChartsModel;
import model.IModel;
import model.MainModel;

public class ActualChartsPresenter implements Observer, IPresenter{

	private ActualChartsView _view;
	private ActualChartsModel _model;
	
	private ChartPanel _temperatureChart;
	private DefaultValueDataset _temperature;
	
	private ChartPanel _pressureChart;
	private DefaultValueDataset _pressure;
	
	private ChartPanel _humidityChart;
	private DefaultValueDataset _humidity;
	
	private ChartPanel _wVelocityChart;
	private DefaultValueDataset _wVelocity;
	
	private ChartPanel _wDirectionChart;
	private DefaultValueDataset _wDirection;
	
	private ChartPanel _rainGaugeChart;
	private DefaultValueDataset _rainGauge;

	public ActualChartsPresenter(IModel model, IView view){

		startValueCollector();
		
		// Setting initial data
		_temperature=new DefaultValueDataset(0.0);
		_pressure=new DefaultValueDataset(0.0);
		_humidity=new DefaultValueDataset(0.0);
		_wVelocity=new DefaultValueDataset(0.0);
		_wDirection=new DefaultValueDataset(0.0);
		_rainGauge=new DefaultValueDataset(0.0);
		
		// Creating charts
		ChartFactory factory=new ChartFactory();
		_temperatureChart= factory.createChart("Temperatura Actual", _temperature,ChartType.TEMPERATURE);
		_pressureChart= factory.createChart("Presión Actual", _pressure,ChartType.PRESSURE);
		_humidityChart= factory.createChart("Humedad Actual", _humidity,ChartType.HUMIDITY);
		_wVelocityChart= factory.createChart("Velocidad del Viento Actual", _wVelocity,ChartType.WIND_VELOCITY);
		_wDirectionChart= factory.createChart("Dirección del Viento Actual", _wDirection,ChartType.WIND_DIRECTION);
		_rainGaugeChart= factory.createChart("Pluviometría Actual", _rainGauge,ChartType.RAIN_GAUGE);
		
		// Instantiate view and model
		_view=(ActualChartsView) view;
		_model=(ActualChartsModel) model;
		
		// Update View
		_view.setTemperatureChart(_temperatureChart);
		_view.setPressureChart(_pressureChart);
		_view.setHumidityChart(_humidityChart);
		_view.setWVelocityChart(_wVelocityChart);
		_view.setWDirectionChart(_wDirectionChart);
		_view.setRainGaugeChart(_rainGaugeChart);
	}
	
    public void startValueCollector(){
		//Adding presenter as serialdata observer
		SerialData.getInstance().addObserver(this);
    }
    
    public void stopValueCollector(){
		//Remove presenter as serialdata observer
		SerialData.getInstance().deleteObserver(this);
    }
	
    public void dispose(){
		Director.destroyView(_view,this);
		stopValueCollector();
    }
	@Override
	public void update(Observable arg0, Object arg1) {	
		_temperature.setValue(((SerialData) arg0).getTemperature());
		_pressure.setValue(((SerialData) arg0).getPressure());
		_humidity.setValue(((SerialData) arg0).getHumidity());
		_wVelocity.setValue(((SerialData) arg0).getWindVelocity());
		_wDirection.setValue(((SerialData) arg0).getWindDirection());
		_rainGauge.setValue(((SerialData) arg0).getRainGauge());
	}	
}
