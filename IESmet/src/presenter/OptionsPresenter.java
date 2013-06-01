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
import view.AboutUsView;
import view.ActualChartsView;
import view.IView;
import view.MainView;
import view.OptionsView;

import comm.SerialData;
import comm.SerialDirector;
import director.Director;
import enums.ChartType;

import model.AboutUsModel;
import model.ActualChartsModel;
import model.IModel;
import model.MainModel;
import model.OptionsModel;

public class OptionsPresenter implements IPresenter{

	private OptionsView _view;
	private OptionsModel _model;
	

	public OptionsPresenter(IModel model, IView view){

		// Instantiate view and model
		_view=(OptionsView) view;
		_model=(OptionsModel) model;
		
	}
	
    public void dispose(){
		Director.destroyView(_view,this);
    }
}
