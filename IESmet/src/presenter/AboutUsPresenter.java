package presenter;

/**
 * @author Jorge Torregrosa Lloret
 * @version 1.0
 * 
 * Main presenter of the application. It get the MainView and the MainModel and mediates between them.
 * It updates the View and get the user inputs. It also updates the Model.
 */

import view.AboutUsView;
import view.IView;
import director.Director;

import model.AboutUsModel;
import model.IModel;

public class AboutUsPresenter implements IPresenter{

	private AboutUsView _view;
	private AboutUsModel _model;
	

	public AboutUsPresenter(IModel model, IView view){

		// Instantiate view and model
		_view=(AboutUsView) view;
		_model=(AboutUsModel) model;
		
		// Update View
		_view.setTitle(_model.getTitle());
		_view.setMessage(_model.getMessage());
	}
	
    public void dispose(){
		Director.destroyView(_view,this);
    }
}
