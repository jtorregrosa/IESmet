package presenter;

/**
 * @author Jorge Torregrosa Lloret
 * @version 1.0
 * 
 * Main presenter of the application. It get the MainView and the MainModel and mediates between them.
 * It updates the View and get the user inputs. It also updates the Model.
 */


import view.IView;
import view.MainView;

import director.Director;

import model.IModel;
import model.MainModel;

public class MainPresenter{

	MainView view;
	MainModel model;

	public MainPresenter(IModel model, IView view){
		// Instantiate view and model
		this.view=(MainView) view;
		this.model=(MainModel) model;
	}
	
	public void launchActualCharts(){
		Director.doActualCharts();
	}
	
	public void launchAboutUs(){
		Director.doAboutUs();
	}
	
	public void launchOptions(){
		Director.doOptions();
	}
}
