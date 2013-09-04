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
import presenter.OptionsPresenter;
import model.IModel;

public class OptionsView implements IView {

	JFrame _frame;
	JPanel _mainPane;
	OptionsPresenter _presenter;

	public OptionsView(IModel model) {
		// Initializing members
		_frame = new JFrame();
		_mainPane = new JPanel();
		_presenter = new OptionsPresenter(model, this);

		// Creating all components
		init();
	}

        @Override
	public final void init() {

		// Setting, packing, and showing Frame
		_frame.setSize(400, 600);
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
		
	}

	@Override
	public void close() {
		_presenter.dispose();
	}
        
        @Override
        public void setEnabled(Boolean flag){
        _frame.setEnabled(flag);
    }
}
