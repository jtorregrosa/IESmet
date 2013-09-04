package view;

import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;

import presenter.AboutUsPresenter;
import presenter.ActualChartsPresenter;
import model.IModel;

public class AboutUsView implements IView {

    JPanel _mainPane;
	JFrame _frame;
	JLabel _title;
	JLabel _message;
	
	AboutUsPresenter _presenter;

	public AboutUsView(IModel model) {
		// Initializing members
            _mainPane = new JPanel();
		_frame = new JFrame();
		_title=new JLabel();
		_message = new JLabel();
		
		_presenter = new AboutUsPresenter(model, this);

		// Creating all components
		init();
	}

        @Override
	public final void init() {

		// Setting, packing, and showing Frame
		_frame.setSize(400, 300);
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
		
		// Adding labels
		_mainPane.add(_title);
		_mainPane.add(_message);
	}

	@Override
	public void close() {
		_presenter.dispose();
	}
	
	public void setTitle(String title){
		_title.setText(title);
	}
	
	public void setMessage(String message){
		_message.setText(message);
	}
        
    @Override
        public void setEnabled(Boolean flag){
        _frame.setEnabled(flag);
    }

}
