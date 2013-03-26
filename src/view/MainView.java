package view;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import presenter.MainPresenter;

import model.IModel;


public class MainView extends JPanel implements IView{

    SystemTray _tray;
    JPopupMenu _menu;
    JMenu _subMenu;
    TrayIcon _trayIcon;
	Rectangle _menuDimensions;
    
    MainPresenter _presenter;

    public MainView(IModel model) {
    	if (!SystemTray.isSupported()) {
            //TODO ERROR DIALOG
            return;
        }
    	
    	// Initializing members
    	 _tray = SystemTray.getSystemTray();
    	 _trayIcon = null; // Initialization before
    	 _menu = new JPopupMenu("Tray");
         _subMenu = new JMenu("Gráficas");
    	
        _presenter = new MainPresenter(model, this);
        
        show();
    }

    public void show() {
        // Creating toolkit to create icons
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        
    	// SystemTray menu items 
    	JMenuItem options = new JMenuItem("Opciones", new ImageIcon(getClass().getResource("/resources/preferences-desktop.png")));
    	JMenuItem help = new JMenuItem("Ayuda", new ImageIcon(getClass().getResource("/resources/help-browser.png")));
    	JMenuItem about = new JMenuItem("Acerca de...", new ImageIcon(getClass().getResource("/resources/system-users.png")));
    	JMenuItem close = new JMenuItem("Salir", new ImageIcon(getClass().getResource("/resources/emblem-unreadable.png")));
    	
    	JMenuItem sub_actual = new JMenuItem("Actual", new ImageIcon(getClass().getResource("/resources/utilities-system-monitor.png")));
    	JMenuItem sub_24 = new JMenuItem("Diaria", new ImageIcon(getClass().getResource("/resources/utilities-system-monitor.png")));
    	JMenuItem sub_month = new JMenuItem("Mensual", new ImageIcon(getClass().getResource("/resources/utilities-system-monitor.png")));
    	JMenuItem sub_year = new JMenuItem("Anual", new ImageIcon(getClass().getResource("/resources/utilities-system-monitor.png")));
    	
    	
    	// Adding menu items functionality
    	sub_actual.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                _presenter.launchActualCharts();
              }
    	});
    	about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                _presenter.launchAboutUs();
              }
    	});
    	options.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                _presenter.launchOptions();
              }
    	});
        close.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            System.exit(0);
          }
        });
        
        
        // Hack to simulate a JPopumMenu in SysIcon
        _menu.addPopupMenuListener(new PopupMenuListener(){
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						_menuDimensions=_menu.getBounds();
					}
				});
			}
			@Override
			public void popupMenuCanceled(PopupMenuEvent arg0) {}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {}
        });
        
        _menu.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {
                if (!_menuDimensions.contains(e.getPoint())) {
                	_menu.setVisible(false);
                }
			}
			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent e) {}
        });
        
        // Composing menu
        _subMenu.add(sub_actual);
        _subMenu.add(sub_24);
        _subMenu.add(sub_month);
        _subMenu.add(sub_year);
        
        _menu.add(_subMenu);
        _menu.addSeparator();
        _menu.add(options);
        _menu.add(help);
        _menu.add(about);
        _menu.addSeparator();
        _menu.add(close);
        
        
        // Setting TrayIcon
        
        Image image = toolkit.getImage(getClass().getResource("/resources/tray.png"));
        _trayIcon=new TrayIcon(image,"IESmet",null);
        _trayIcon.setImage(image);
        _trayIcon.setToolTip("IESmet");
        _trayIcon.setImageAutoSize(true);
        _trayIcon.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON3 || e.getButton()==MouseEvent.BUTTON1) {
                	_menu.setLocation(e.getX(), e.getY());
                	_menu.setInvoker(_menu);
                	_menu.setVisible(true);
                }
            }
        });

        try {
			_tray.add(_trayIcon);
		} catch (AWTException e1) {
			// TODO Excepcion tray
			e1.printStackTrace();
		}
    }
	@Override
	public void close() {		
	}
}
