package director;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import presenter.IPresenter;
import model.AboutUsModel;
import model.ActualChartsModel;
import model.ConnectionModel;
import model.MainModel;
import model.OptionsModel;
import view.AboutUsView;
import view.ActualChartsView;
import view.ConnectionView;
import view.IView;
import view.MainView;
import view.OptionsView;

public class Director {


	
	static MainView _main;
	static ActualChartsView _actualCharts;
	static ConnectionView _connection;
	static AboutUsView _aboutUs;
	static OptionsView _options;
	
	public static void doInit() {
		try {
			// Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (    UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO handle exception
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
	
	static void initMain() {
		if (_main == null)
			_main = new MainView(new MainModel());
	}
	
	static void initActualCharts() {
		if (_actualCharts == null)
			_actualCharts = new ActualChartsView(new ActualChartsModel());
	}
	
	static void initConnection(){
		if (_connection == null)
			_connection = new ConnectionView(new ConnectionModel());
	}
	
	static void initAboutUs(){
		if (_aboutUs == null)
			_aboutUs = new AboutUsView(new AboutUsModel());
	}
	
	static void initOptions(){
		if (_options == null)
			_options = new OptionsView(new OptionsModel());
	}
	
	public static void destroyView(IView view, IPresenter presenter){
		Class vclass = view.getClass();
		
		if(vclass==ActualChartsView.class)
			_actualCharts=null;
		else if (vclass==ConnectionView.class)
			_connection=null;
		
		view = null;
		presenter = null;
		System.gc();
	}
	
}
