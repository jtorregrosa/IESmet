package view;

import common.Configuration;
import director.Director;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import model.IModel;
import presenter.EmulationPathsPresenter;
import xml.LogReader;

public class EmulationPathsView implements IView {

    EmulationPathsPresenter _presenter;
    JFrame _frame;
    JPanel _mainPane;
    JPanel _buttonsPane;
    JPanel _optionsPane;
    JPanel _formPane;
    JLabel _temperatureLabel;
    JTextField _temperatureField;
    JButton _temperatureButton;
    JLabel _pressureLabel;
    JTextField _pressureField;
    JButton _pressureButton;
    JLabel _humidityLabel;
    JTextField _humidityField;
    JButton _humidityButton;
    JLabel _windDirectionLabel;
    JTextField _windDirectionField;
    JButton _windDirectionButton;
    JLabel _windVelocityLabel;
    JTextField _windVelocityField;
    JButton _windVelocityButton;
    JLabel _rainGaugeLabel;
    JTextField _rainGaugeField;
    JButton _rainGaugeButton;
    JButton _acceptButton;
    JButton _cancelButton;
    JButton _autoButton;
    JButton _clearButton;
    final JFileChooser _temperatureFC = new JFileChooser();
    final JFileChooser _pressureFC = new JFileChooser();
    final JFileChooser _humidityFC = new JFileChooser();
    final JFileChooser _windVelocityFC = new JFileChooser();
    final JFileChooser _windDirectionFC = new JFileChooser();
    final JFileChooser _rainGaugeFC = new JFileChooser();
    final JFileChooser _autoFC = new JFileChooser();

    public EmulationPathsView(IModel model) {
        _frame = new JFrame("Configuración de directorios");
        _presenter = new EmulationPathsPresenter(model, this);

        _temperatureLabel = new JLabel();
        _temperatureField = new JTextField();
        _temperatureButton = new JButton();

        _pressureLabel = new JLabel();
        _pressureField = new JTextField();
        _pressureButton = new JButton();

        _humidityLabel = new JLabel();
        _humidityField = new JTextField();
        _humidityButton = new JButton();

        _windDirectionLabel = new JLabel();
        _windDirectionField = new JTextField();
        _windDirectionButton = new JButton();

        _windVelocityLabel = new JLabel();
        _windVelocityField = new JTextField();
        _windVelocityButton = new JButton();

        _rainGaugeLabel = new JLabel();
        _rainGaugeField = new JTextField();
        _rainGaugeButton = new JButton();

        _acceptButton = new JButton("Aceptar");
        _cancelButton = new JButton("Cancelar");

        _autoButton = new JButton("Autodetectar archivos");
        _clearButton = new JButton("Limpiar configuración");

        _mainPane = new JPanel();
        _formPane = new JPanel();
        _buttonsPane = new JPanel();
        _optionsPane = new JPanel();


        init();
    }

    @Override
    public final void init() {
        _mainPane.setLayout(new BorderLayout());
        BoxLayout bl = new BoxLayout(_formPane, BoxLayout.Y_AXIS);
        _formPane.setLayout(bl);
        TitledBorder title;
        title = BorderFactory.createTitledBorder("Sensores");
        _formPane.setBorder(title);
        _mainPane.add(_formPane, BorderLayout.CENTER);
        _buttonsPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

        _optionsPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

        _temperatureLabel.setText("Temperatura:");
        _pressureLabel.setText("Presión:");
        _humidityLabel.setText("Humedad:");
        _windDirectionLabel.setText("Dirección del Viento:");
        _windVelocityLabel.setText("Velocidad del Viento:");
        _rainGaugeLabel.setText("Pluviometría:");

        _temperatureField.setPreferredSize(new Dimension(200, _temperatureField.getPreferredSize().height));
        _pressureField.setPreferredSize(new Dimension(200, _temperatureField.getPreferredSize().height));
        _humidityField.setPreferredSize(new Dimension(200, _temperatureField.getPreferredSize().height));
        _windDirectionField.setPreferredSize(new Dimension(200, _temperatureField.getPreferredSize().height));
        _windVelocityField.setPreferredSize(new Dimension(200, _temperatureField.getPreferredSize().height));
        _rainGaugeField.setPreferredSize(new Dimension(200, _temperatureField.getPreferredSize().height));

        _temperatureLabel.setPreferredSize(new Dimension(100, _temperatureLabel.getPreferredSize().height));
        _pressureLabel.setPreferredSize(new Dimension(100, _temperatureLabel.getPreferredSize().height));
        _humidityLabel.setPreferredSize(new Dimension(100, _temperatureLabel.getPreferredSize().height));
        _windDirectionLabel.setPreferredSize(new Dimension(100, _temperatureLabel.getPreferredSize().height));
        _windVelocityLabel.setPreferredSize(new Dimension(100, _temperatureLabel.getPreferredSize().height));
        _rainGaugeLabel.setPreferredSize(new Dimension(100, _temperatureLabel.getPreferredSize().height));


        _temperatureButton.setText("...");
        _pressureButton.setText("...");
        _humidityButton.setText("...");
        _windDirectionButton.setText("...");
        _windVelocityButton.setText("...");
        _rainGaugeButton.setText("...");

        JPanel temperaturePane = new JPanel();
        temperaturePane.setLayout(new BoxLayout(temperaturePane, BoxLayout.X_AXIS));
        temperaturePane.setMaximumSize(new Dimension(350, 30));

        JPanel pressurePane = new JPanel();
        pressurePane.setLayout(new BoxLayout(pressurePane, BoxLayout.X_AXIS));
        pressurePane.setMaximumSize(new Dimension(350, 30));

        JPanel humidityPane = new JPanel();
        humidityPane.setLayout(new BoxLayout(humidityPane, BoxLayout.X_AXIS));
        humidityPane.setMaximumSize(new Dimension(350, 30));

        JPanel windDirectionPane = new JPanel();
        windDirectionPane.setLayout(new BoxLayout(windDirectionPane, BoxLayout.X_AXIS));
        windDirectionPane.setMaximumSize(new Dimension(350, 30));

        JPanel windVelocityPane = new JPanel();
        windVelocityPane.setLayout(new BoxLayout(windVelocityPane, BoxLayout.X_AXIS));
        windVelocityPane.setMaximumSize(new Dimension(350, 30));

        JPanel rainGaugePane = new JPanel();
        rainGaugePane.setLayout(new BoxLayout(rainGaugePane, BoxLayout.X_AXIS));
        rainGaugePane.setMaximumSize(new Dimension(350, 30));

        temperaturePane.add(_temperatureLabel);
        temperaturePane.add(_temperatureField);
        temperaturePane.add(_temperatureButton);

        pressurePane.add(_pressureLabel);
        pressurePane.add(_pressureField);
        pressurePane.add(_pressureButton);

        humidityPane.add(_humidityLabel);
        humidityPane.add(_humidityField);
        humidityPane.add(_humidityButton);

        windDirectionPane.add(_windDirectionLabel);
        windDirectionPane.add(_windDirectionField);
        windDirectionPane.add(_windDirectionButton);

        windVelocityPane.add(_windVelocityLabel);
        windVelocityPane.add(_windVelocityField);
        windVelocityPane.add(_windVelocityButton);

        rainGaugePane.add(_rainGaugeLabel);
        rainGaugePane.add(_rainGaugeField);
        rainGaugePane.add(_rainGaugeButton);

        _formPane.add(Box.createVerticalStrut(5));
        _formPane.add(temperaturePane);
        _formPane.add(Box.createVerticalStrut(5));
        _formPane.add(pressurePane);
        _formPane.add(Box.createVerticalStrut(5));
        _formPane.add(humidityPane);
        _formPane.add(Box.createVerticalStrut(5));
        _formPane.add(windDirectionPane);
        _formPane.add(Box.createVerticalStrut(5));
        _formPane.add(windVelocityPane);
        _formPane.add(Box.createVerticalStrut(5));
        _formPane.add(rainGaugePane);
        _formPane.add(Box.createVerticalStrut(15));

        _optionsPane.add(_autoButton);
        _optionsPane.add(_clearButton);

        _formPane.add(_optionsPane);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        List<Image> iconList = new LinkedList<>();
        iconList.add(toolkit.getImage(getClass().getResource("/resources/icon16.png")));
        iconList.add(toolkit.getImage(getClass().getResource("/resources/icon32.png")));
        iconList.add(toolkit.getImage(getClass().getResource("/resources/icon64.png")));

        //_buttonsPane.set
        _buttonsPane.add(_acceptButton);
        _buttonsPane.add(_cancelButton);
        _mainPane.add(_buttonsPane, BorderLayout.SOUTH);

        _frame.setIconImages(iconList);
        _frame.setResizable(false);
        _frame.setSize(400, 350);
        _frame.setLocationRelativeTo(null);
        _frame.add(_mainPane);
        _frame.setVisible(true);

        _frame.addWindowListener(new WindowListener() {
            @Override
            public void windowClosing(WindowEvent e) {
                close();
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

        _temperatureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = _temperatureFC.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String path = _temperatureFC.getSelectedFile().getAbsolutePath();
                    _temperatureField.setText(path);
                }
            }
        });

        _pressureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = _pressureFC.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String path = _pressureFC.getSelectedFile().getAbsolutePath();
                    _pressureField.setText(path);
                }
            }
        });

        _humidityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = _humidityFC.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String path = _humidityFC.getSelectedFile().getAbsolutePath();
                    _humidityField.setText(path);
                }
            }
        });

        _windDirectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = _windDirectionFC.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String path = _windDirectionFC.getSelectedFile().getAbsolutePath();
                    _windDirectionField.setText(path);
                }
            }
        });

        _windVelocityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = _windVelocityFC.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String path = _windVelocityFC.getSelectedFile().getAbsolutePath();
                    _windVelocityField.setText(path);
                }
            }
        });

        _rainGaugeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = _rainGaugeFC.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String path = _rainGaugeFC.getSelectedFile().getAbsolutePath();
                    _rainGaugeField.setText(path);
                }
            }
        });

        _clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _temperatureField.setText("");
                _humidityField.setText("");
                _pressureField.setText("");
                _windVelocityField.setText("");
                _windDirectionField.setText("");
                _rainGaugeField.setText("");
            }
        });

        _autoFC.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        _autoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = _autoFC.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String path = _autoFC.getSelectedFile().getAbsolutePath();
                    // LANZAR COMPROBACION
                    LogReader lr = new LogReader();
                    Map<Integer, String> files = lr.autoDetectFiles(path);

                    _temperatureField.setText(files.get(1) == null? "" : files.get(1));
                    _pressureField.setText(files.get(2) == null? "" : files.get(2));
                    _humidityField.setText(files.get(3) == null? "" : files.get(3));
                    _windVelocityField.setText(files.get(4) == null? "" : files.get(4));
                    _windDirectionField.setText(files.get(5) == null? "" : files.get(5));
                    _rainGaugeField.setText(files.get(6) == null? "" : files.get(6));
                }
            }
        });
        
        _acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Configuration.getInstance().setEmulationPath(Configuration.Sensors.TEMPERATURE, _temperatureField.getText());
                Configuration.getInstance().setEmulationPath(Configuration.Sensors.PRESSURE, _pressureField.getText());
                Configuration.getInstance().setEmulationPath(Configuration.Sensors.HUMIDITY, _humidityField.getText());
                Configuration.getInstance().setEmulationPath(Configuration.Sensors.WIND_DIRECTION, _windDirectionField.getText());
                Configuration.getInstance().setEmulationPath(Configuration.Sensors.WIND_VELOCITY, _windVelocityField.getText());
                Configuration.getInstance().setEmulationPath(Configuration.Sensors.RAIN_GAUGE, _rainGaugeField.getText());
                
                close();
            }
        });
        
        _cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });

    }

    @Override
    public void close() {
        Director.getView(Director.Views.CONNECTION_VIEW).setEnabled(true);
        _frame.setVisible(false);
        Director.destroyView(this, _presenter);

    }
    
    @Override
    public void setEnabled(Boolean flag){
        _frame.setEnabled(flag);
    }
}
