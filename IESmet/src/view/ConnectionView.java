package view;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import director.Director;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedList;
import java.util.List;
import presenter.ConnectionPresenter;
import model.IModel;

public class ConnectionView extends JPanel implements IView {

    ConnectionPresenter _presenter;
    JFrame _frame;
    JLabel _connectionLabel;
    JComboBox<String> _portsCombobox;
    JButton _connectionButton;
    JButton _noConnectionButton;
    ImageIcon _background;
    boolean _closingFlag;

    public ConnectionView(IModel model) {
        // Initializing members
        _connectionLabel = new JLabel("Puerto de la estación: ");
        _portsCombobox = new JComboBox<>();
        _frame = new JFrame("Iniciar Conexión");
        _connectionButton = new JButton("Conectar");
        _noConnectionButton = new JButton("Modo sin conexión");
        _presenter = new ConnectionPresenter(model, this);
        _closingFlag = true;

        show();
    }

    public void show() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        _background = new ImageIcon(getClass().getResource("/resources/splash.png"));
        _connectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                _closingFlag = false;
                _presenter.Connect(_portsCombobox.getSelectedItem().toString(), true);
            }
        });
        _noConnectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                _closingFlag = false;
                _presenter.Connect(_portsCombobox.getSelectedItem().toString(), false);
            }
        });

        this.setLayout(new BorderLayout());

        JPanel connectionPane = new JPanel(new FlowLayout());
        JPanel buttonsPane = new JPanel(new FlowLayout());

        connectionPane.setBackground(new Color(255, 255, 255, 0));
        buttonsPane.setBackground(new Color(255, 255, 255, 90));

        _connectionLabel.setForeground(Color.WHITE);


        connectionPane.add(_connectionLabel);
        connectionPane.add(_portsCombobox);
        buttonsPane.add(_connectionButton);
        buttonsPane.add(_noConnectionButton);

        add(connectionPane, BorderLayout.NORTH);
        add(buttonsPane, BorderLayout.SOUTH);
        List<Image> iconList= new LinkedList<>();
        iconList.add(toolkit.getImage(getClass().getResource("/resources/icon16.png")));
        iconList.add(toolkit.getImage(getClass().getResource("/resources/icon32.png")));
        iconList.add(toolkit.getImage(getClass().getResource("/resources/icon64.png")));
        
        _frame.setIconImages(iconList);
        _frame.setResizable(false);
        _frame.setSize(400, 270);
        _frame.setLocationRelativeTo(null);
        _frame.add(this);
        _frame.setVisible(true);

        _frame.addWindowListener(new WindowListener() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

    }

    @Override
    public void close() {
        _frame.setVisible(false);
        if (_closingFlag) {
            System.exit(0);
        }
        Director.destroyView(this, _presenter);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(_background.getImage(), 0, 0, null);
    }

    public void setPortsCombobox(JComboBox<String> combo) {
        _portsCombobox = combo;
    }

    public JComboBox<String> getPortsComboBox() {
        return _portsCombobox;
    }

    public void noAvailablePorts() {
        _portsCombobox.addItem("Ningún puerto encontrado");
    }

    public JButton getConnectionButton() {
        return _connectionButton;
    }
}
