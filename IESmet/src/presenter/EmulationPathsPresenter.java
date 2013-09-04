/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import model.EmulationPathsModel;
import model.IModel;
import view.EmulationPathsView;
import view.IView;

/**
 *
 * @author Jorge
 */
public class EmulationPathsPresenter implements IPresenter{
    EmulationPathsView _view;
    EmulationPathsModel _model;
    
    public EmulationPathsPresenter(IModel model, IView view){
	this._view=(EmulationPathsView) view;
	this._model=(EmulationPathsModel) model;
                
    }
}
