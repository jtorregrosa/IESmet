package presenter;

/**
 * @author Jorge Torregrosa Lloret
 * @version 1.0
 * 
 * Main presenter of the application. It get the MainView and the MainModel and mediates between them.
 * It updates the View and get the user inputs. It also updates the Model.
 */

import gnu.io.CommPortIdentifier;


import javax.swing.JComboBox;

import view.ConnectionView;
import view.IView;

import comm.SerialDirector;
import comm.SerialDispatcher;
import comm.SerialScheduler;
import director.Director;

import model.ConnectionModel;
import model.IModel;

public class ConnectionPresenter implements IPresenter{

	ConnectionView _view;
	ConnectionModel _model;

	public ConnectionPresenter(IModel model, IView view){
		// Instantiate view and model
		this._view=(ConnectionView) view;
		this._model=(ConnectionModel) model;
		
		//Recollect data
		JComboBox<String> c = _view.getPortsComboBox();
		SerialDirector d = SerialDirector.getInstance();
		for(CommPortIdentifier h : d.getAvailablePorts().values()){
			c.addItem(h.getName());
		}
		
		if(c.getItemCount()==0){
			_view.noAvailablePorts();
			_view.getConnectionButton().setEnabled(false);
		}
		_view.setPortsCombobox(c);
		
	}
	
	public void Connect(String port, boolean doConnection) {
		if(doConnection){
			SerialDirector.getInstance().connect(port);
                        SerialScheduler.getInstance().startTasks();
                        SerialDispatcher.getInstance().startTasks();
		}

		Director.doMain();
		_view.close();
	}
	
	public void launchActualCharts(){
		Director.doActualCharts();
	}
	
}
