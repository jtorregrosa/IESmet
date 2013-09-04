package presenter;

/**
 * @author Jorge Torregrosa Lloret
 * @version 1.0
 * 
 * Main presenter of the application. It get the MainView and the MainModel and mediates between them.
 * It updates the View and get the user inputs. It also updates the Model.
 */

import view.IView;
import view.OptionsView;

import director.Director;
import model.IModel;
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
