package view;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import director.Director;

import presenter.ConnectionPresenter;
import model.IModel;


public class ConnectionView extends JPanel implements IView{
    
    ConnectionPresenter _presenter;
    JFrame _frame;
    JLabel _connectionLabel;
    JComboBox<String> _portsCombobox;
    JButton _connectionButton;
    JButton _noConnectionButton;
    ImageIcon _background;
    
    

    public ConnectionView(IModel model) {
    	// Initializing members
    	_connectionLabel= new JLabel("Puerto de la Estación");
    	_portsCombobox = new JComboBox<String>();
    	_frame = new JFrame("Iniciar Conexi�n");
    	_connectionButton= new JButton("Conectar");
    	_noConnectionButton= new JButton("Modo sin conexión");
        _presenter = new ConnectionPresenter(model, this);
        
        show();
    }

    public void show() {
    	Toolkit toolkit = Toolkit.getDefaultToolkit();
    	_background = new ImageIcon(getClass().getResource("/resources/splash.png"));
		_connectionButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				_presenter.Connect(_portsCombobox.getSelectedItem().toString(), true);	
			}
		});
		_noConnectionButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				_presenter.Connect(_portsCombobox.getSelectedItem().toString(), false);
			}
		});
		
    	add(_connectionLabel);
    	add(_portsCombobox);
    	add(_connectionButton);
    	add(_noConnectionButton);
    	
    	_frame.setIconImage(toolkit.getImage(getClass().getResource("/resources/tray.png")));
    	_frame.setResizable(false);
    	_frame.setSize(400, 250);
    	_frame.setLocationRelativeTo(null);
    	_frame.add(this);
    	_frame.setVisible(true);
    }
	@Override
	public void close() {
		_frame.setVisible(false);
		Director.destroyView(this, _presenter);
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(_background.getImage(), 0, 0, null);
	}
	
	public void setPortsCombobox(JComboBox<String> combo){
		_portsCombobox = combo;
	}
	
	public JComboBox<String> getPortsComboBox(){
		return _portsCombobox;
	}

	public void noAvailablePorts() {
		_portsCombobox.addItem("Ningún puerto encontrado");	
	}
	public JButton getConnectionButton(){
		return _connectionButton;
	}
}
