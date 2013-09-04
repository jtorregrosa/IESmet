package director;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import presenter.IPresenter;
import model.AboutUsModel;
import model.ActualChartsModel;
import model.ConnectionModel;
import model.EmulationPathsModel;
import model.MainModel;
import model.OptionsModel;
import view.AboutUsView;
import view.ActualChartsView;
import view.ConnectionView;
import view.EmulationPathsView;
import view.IView;
import view.MainView;
import view.OptionsView;

public class Director {

    public static enum Views{
         MAIN_VIEW,ACTUAL_CHARTS_VIEW,CONNECTION_VIEW,ABOUT_US_VIEW,OPTIONS_MAIN_VIEW,EMULATION_PATHS_VIEW
    }
      
        
	static MainView _main;
	static ActualChartsView _actualCharts;
	static ConnectionView _connection;
	static AboutUsView _aboutUs;
	static OptionsView _options;
        static EmulationPathsView _emulationPaths;
	
	public static void doInit() {
		try {
			// Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (    UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			System.exit(-2);
		}

		initConnection();
	}
	
	public static void doActualCharts() {
		initActualCharts();
	}

	public static void doMain() {
		initMain();
	}
	
	public static void doAboutUs() {
		initAboutUs();
	}
	
	public static void doOptions() {
		initOptions();
	}
        
        public static void doEmulationPaths(){
		initEmulationPaths();
	}
	
	static void initMain() {
		if (_main == null){
			_main = new MainView(new MainModel());
                }
	}
	
	static void initActualCharts() {
		if (_actualCharts == null){
			_actualCharts = new ActualChartsView(new ActualChartsModel());
                }
	}
	
	static void initConnection(){
		if (_connection == null){
			_connection = new ConnectionView(new ConnectionModel());
                }
	}
	
	static void initAboutUs(){
		if (_aboutUs == null){
			_aboutUs = new AboutUsView(new AboutUsModel());
                }
	}
	
	static void initOptions(){
		if (_options == null){
			_options = new OptionsView(new OptionsModel());
                }
	}
        
        static void initEmulationPaths(){
		if (_emulationPaths == null){
			_emulationPaths = new EmulationPathsView(new EmulationPathsModel());
                }
	}
        
        public static IView getView(Views view){
            IView v = null;
            switch(view){
                case MAIN_VIEW: v = _main;
                    break;
                case ACTUAL_CHARTS_VIEW: v = _actualCharts;
                    break;
                case CONNECTION_VIEW: v = _connection;
                    break;
                case ABOUT_US_VIEW: v = _aboutUs;
                    break;
                case OPTIONS_MAIN_VIEW: v = _options;
                    break;
                case EMULATION_PATHS_VIEW: v = _emulationPaths;
                    break;
            }
            
            return v;
        }
	
	public static void destroyView(IView view, IPresenter presenter){
                if(view.getClass().isInstance(_emulationPaths)){
                    _emulationPaths=null;
                }
                else if(view.getClass().isInstance(_main)){
               _main=null;
                }
                else if(view.getClass().isInstance(_options)){
               _options=null;
                }
                else if(view.getClass().isInstance(_actualCharts)){
               _actualCharts=null;
                }
                else if(view.getClass().isInstance(_aboutUs)){
               _aboutUs=null;
                }
                else if(view.getClass().isInstance(_connection)){
               _connection=null;
                }
                
		System.gc();
                
	}
	
}
