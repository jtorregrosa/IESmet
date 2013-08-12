package view;

import common.Configuration;
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
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import presenter.ConnectionPresenter;
import model.IModel;

public class ConnectionView extends JPanel implements IView {

    ConnectionPresenter _presenter;
    JFrame _frame;
    JLabel _connectionLabel;
    JComboBox<String> _portsCombobox;
    JComboBox<String> _modesCombobox;
    JButton _acceptButton;
    JButton _exitButton;
    boolean _closingFlag;
    JPanel _cardsPane;
    JPanel _card0;
    JPanel _card1;
    JPanel _card2;
    JPanel _card3;
    ImageIcon _imageMode0;
    ImageIcon _imageMode1;
    ImageIcon _imageMode2;
    ImageIcon _imageMode3;
    JLabel _animation;
    final static String CARD0 = "Elige un modo";
    final static String CARD1 = "Modo con conexión";
    final static String CARD2 = "Modo sin conexión";
    final static String CARD3 = "Modo emulado";
    final static int TEXT_DEFAULT_SIZE = 11;
    final static int TEXTAREA_DEFAULT_WIDTH = 200;
    final static int TEXTAREA_DEFAULT_HEIGHT = 59;

    public ConnectionView(IModel model) {
        // Initializing members
        _connectionLabel = new JLabel("Puerto de la estación: ");
        _portsCombobox = new JComboBox<>();
        _modesCombobox = new JComboBox<>();
        _frame = new JFrame("Iniciar Conexión");
        _acceptButton = new JButton("Aceptar");
        _exitButton = new JButton("Salir");
        _presenter = new ConnectionPresenter(model, this);
        _closingFlag = true;
        _card0 = new JPanel();
        _card1 = new JPanel();
        _card2 = new JPanel();
        _card3 = new JPanel();


        show();
    }

    @Override
    public final void show() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        _acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                _closingFlag = false;
                switch ((String) _modesCombobox.getSelectedItem()) {
                    case CARD1:
                        Configuration.getInstance().setAppMode(Configuration.AppMode.CONNECTED);
                        break;
                    case CARD2:
                        Configuration.getInstance().setAppMode(Configuration.AppMode.NO_CONNECTED);
                        break;
                    case CARD3:
                        Configuration.getInstance().setAppMode(Configuration.AppMode.EMULATED);
                        break;
                    default:
                        Configuration.getInstance().setAppMode(Configuration.AppMode.NO_CONNECTED);
                }
                _presenter.Connect(_portsCombobox.getSelectedItem().toString());
            }
        });
        _exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                _frame.setVisible(false);
                if (_closingFlag) {
                    System.exit(0);
                }
            }
        });


        _modesCombobox.addItem(CARD0);
        _modesCombobox.addItem(CARD1);
        _modesCombobox.addItem(CARD2);
        _modesCombobox.addItem(CARD3);


        this.setLayout(new BorderLayout());
        this.setBackground(new Color(37, 84, 160));

        JPanel connectionPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel buttonsPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JPanel detailsPane = new JPanel();
        _cardsPane = new JPanel(new CardLayout(10, 10));

        _cardsPane.setBackground(new Color(37, 84, 160));
        _card0.setBackground(new Color(37, 84, 160));
        _card1.setBackground(new Color(37, 84, 160));
        _card2.setBackground(new Color(37, 84, 160));
        _card3.setBackground(new Color(37, 84, 160));

        _card0.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(39, 58, 108)));
        _card1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(39, 58, 108)));
        _card2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(39, 58, 108)));
        _card3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(39, 58, 108)));

        detailsPane.setLayout(new BoxLayout(detailsPane, BoxLayout.PAGE_AXIS));
        detailsPane.setBackground(new Color(37, 84, 160));
        detailsPane.setBorder(BorderFactory.createMatteBorder(20, 10, 10, 10, new Color(37, 84, 160)));
        connectionPane.setBackground(new Color(37, 84, 160));

        buttonsPane.setBackground(new Color(137, 150, 191));

        _connectionLabel.setForeground(Color.WHITE);


      
        _imageMode0 = new ImageIcon(getClass().getResource("/resources/splash.gif"));
        _imageMode1 = new ImageIcon(getClass().getResource("/resources/mode1.gif"));
        _imageMode2 = new ImageIcon(getClass().getResource("/resources/mode2.gif"));
        _imageMode3 = new ImageIcon(getClass().getResource("/resources/mode3.gif"));

        _animation = new JLabel(_imageMode0);
        connectionPane.add(_animation);

        buttonsPane.add(_acceptButton);
        buttonsPane.add(_exitButton);
        detailsPane.add(_modesCombobox);
        detailsPane.add(_cardsPane);
        _cardsPane.add(_card0, CARD0);
        _cardsPane.add(_card1, CARD1);
        _cardsPane.add(_card2, CARD2);
        _cardsPane.add(_card3, CARD3);

        _acceptButton.setEnabled(false);
        add(connectionPane, BorderLayout.CENTER);
        add(buttonsPane, BorderLayout.SOUTH);
        add(detailsPane, BorderLayout.EAST);


        List<Image> iconList = new LinkedList<>();
        iconList.add(toolkit.getImage(getClass().getResource("/resources/icon16.png")));
        iconList.add(toolkit.getImage(getClass().getResource("/resources/icon32.png")));
        iconList.add(toolkit.getImage(getClass().getResource("/resources/icon64.png")));

        _modesCombobox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                CardLayout cl = (CardLayout) (_cardsPane.getLayout());
                cl.show(_cardsPane, (String) e.getItem());
                _acceptButton.setEnabled(true);
                switch ((String) e.getItem()) {
                    case CARD1:
                        _animation.setIcon(_imageMode1);
                        break;
                    case CARD2:
                        _animation.setIcon(_imageMode2);
                        break;
                    case CARD3:
                        _animation.setIcon(_imageMode3);
                        break;
                    default:
                        _animation.setIcon(_imageMode0);
                        _acceptButton.setEnabled(false);
                }
            }
        });

        _card0.setLayout(new BoxLayout(_card0, BoxLayout.PAGE_AXIS));
        JTextPane t0 = new JTextPane();
        t0.setText("Elija un modo de funcionamiento de IESmet entre los propuestos. Cada uno le permitira interactuar con la aplicación de formas diferentes.");
        t0.setBackground(new Color(86, 112, 180));
        t0.setForeground(Color.WHITE);

        t0.setPreferredSize(new Dimension(TEXTAREA_DEFAULT_WIDTH, TEXTAREA_DEFAULT_HEIGHT));
        SimpleAttributeSet aSet = new SimpleAttributeSet();
        StyleConstants.setAlignment(aSet, StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setFontSize(aSet, TEXT_DEFAULT_SIZE);
        t0.setCharacterAttributes(aSet, true);
        _card0.add(t0);

        _card1.setLayout(new BoxLayout(_card1, BoxLayout.PAGE_AXIS));
        JTextPane t1 = new JTextPane();
        t1.setText("En este modo la aplicación se conectará directamente con la central meteorológica. Ud. podrá leer los datos climáticos directamente desde su posición.");
        t1.setPreferredSize(new Dimension(TEXTAREA_DEFAULT_WIDTH, TEXTAREA_DEFAULT_HEIGHT));
        t1.setBackground(new Color(86, 112, 180));
        t1.setForeground(Color.WHITE);
        _card1.add(t1);
        t1.setCharacterAttributes(aSet, true);
        
        _card1.add(_connectionLabel);
        _card1.add(_portsCombobox);

        _card2.setLayout(new BoxLayout(_card2, BoxLayout.PAGE_AXIS));
        JTextPane t2 = new JTextPane();
        t2.setText("En este modo la aplicación no se conectará con la central meteorológica. Esto le permitirá visualizar las gráficas del historial de datos");
        t2.setPreferredSize(new Dimension(TEXTAREA_DEFAULT_WIDTH, TEXTAREA_DEFAULT_HEIGHT));
        t2.setBackground(new Color(86, 112, 180));
        t2.setForeground(Color.WHITE);
        _card2.add(t2);
        t2.setCharacterAttributes(aSet, true);

        _card3.setLayout(new BoxLayout(_card3, BoxLayout.PAGE_AXIS));
        JTextPane t3 = new JTextPane();
        t3.setText("Este es un modo especial en el que podrá emular una central meteorológica desde varios archivos de texto plano (uno por sensor).");
        t3.setPreferredSize(new Dimension(TEXTAREA_DEFAULT_WIDTH, TEXTAREA_DEFAULT_HEIGHT));
        t3.setBackground(new Color(86, 112, 180));
        t3.setForeground(Color.WHITE);
        _card3.add(t3);
        t3.setCharacterAttributes(aSet, true);

        _frame.setIconImages(iconList);
        _frame.setResizable(true);
        _frame.setSize(650, 350);
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

    public void setPortsCombobox(JComboBox<String> combo) {
        _portsCombobox = combo;
    }

    public void setModesCombobox(JComboBox<String> combo) {
        _modesCombobox = combo;
    }

    public JComboBox<String> getPortsComboBox() {
        return _portsCombobox;
    }

    public JComboBox<String> getModesComboBox() {
        return _modesCombobox;
    }

    public void noAvailablePorts() {
        _portsCombobox.addItem("Ningún puerto encontrado");
        _acceptButton.setEnabled(false);
    }

    public JButton getConnectionButton() {
        return _acceptButton;
    }
}
