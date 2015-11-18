package com.mbellagamba.csvsync;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URISyntaxException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

public class MainJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField dirTextField;
	private JTextField inFileTextField;
	private JTextField outFileTextField;
	private JSpinner setDimensionSpinner;
	private JSpinner headerSpinner;
	private JComboBox<String> inSplitComboBox;
	private JComboBox<String> outSplitSpinner;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainJFrame frame = new MainJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainJFrame() {
		setTitle("Sincronizzatore CSV");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConfigurazioneDelFile = new JLabel("Configurazione del file di input");
		lblConfigurazioneDelFile.setBounds(12, 0, 228, 15);
		contentPane.add(lblConfigurazioneDelFile);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 27, 426, 147);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPercorsoCartella = new JLabel("Percorso cartella");
		lblPercorsoCartella.setBounds(12, 12, 150, 15);
		panel.add(lblPercorsoCartella);
		
		JLabel lblNomeFile = new JLabel("Nome file");
		lblNomeFile.setBounds(12, 39, 150, 15);
		panel.add(lblNomeFile);
		
		JLabel lblDimensioneGruppo = new JLabel("Dimensione gruppo");
		lblDimensioneGruppo.setBounds(12, 66, 150, 15);
		panel.add(lblDimensioneGruppo);
		
		JLabel lblLineeDiIntestazione = new JLabel("Linee di intestazione");
		lblLineeDiIntestazione.setBounds(12, 93, 150, 15);
		panel.add(lblLineeDiIntestazione);
		
		JLabel lblSeparatore = new JLabel("Separatore");
		lblSeparatore.setBounds(12, 120, 150, 15);
		panel.add(lblSeparatore);
		
		dirTextField = new JTextField();
		dirTextField.setBounds(180, 10, 234, 19);
		panel.add(dirTextField);
		dirTextField.setColumns(10);
		
		inFileTextField = new JTextField();
		inFileTextField.setBounds(180, 37, 234, 19);
		panel.add(inFileTextField);
		inFileTextField.setColumns(10);
		
		setDimensionSpinner = new JSpinner();
		setDimensionSpinner.setModel(new SpinnerNumberModel(new Integer(2), null, null, new Integer(1)));
		setDimensionSpinner.setBounds(180, 64, 28, 20);
		panel.add(setDimensionSpinner);
		
		headerSpinner = new JSpinner();
		headerSpinner.setBounds(180, 91, 28, 20);
		panel.add(headerSpinner);
		
		inSplitComboBox = new JComboBox<String>();
		inSplitComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {",", ";"}));
		inSplitComboBox.setSelectedIndex(0);
		inSplitComboBox.setBounds(176, 115, 32, 24);
		panel.add(inSplitComboBox);
		
		JLabel lblConfigurazioneFileDi = new JLabel("Configurazione file di output");
		lblConfigurazioneFileDi.setBounds(12, 189, 203, 15);
		contentPane.add(lblConfigurazioneFileDi);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 216, 426, 67);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNomeFile_1 = new JLabel("Nome file");
		lblNomeFile_1.setBounds(12, 12, 70, 15);
		panel_1.add(lblNomeFile_1);
		
		JLabel lblSeparatore_1 = new JLabel("Separatore");
		lblSeparatore_1.setBounds(12, 39, 93, 15);
		panel_1.add(lblSeparatore_1);
		
		outFileTextField = new JTextField();
		outFileTextField.setToolTipText("Test");
		outFileTextField.setBounds(181, 10, 114, 19);
		panel_1.add(outFileTextField);
		outFileTextField.setColumns(10);
		
		outSplitSpinner = new JComboBox<String>();
		outSplitSpinner.setModel(new DefaultComboBoxModel<String>(new String[] {",", ";"}));
		outSplitSpinner.setBounds(181, 34, 32, 24);
		panel_1.add(outSplitSpinner);
		
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(308, 295, 117, 25);
		contentPane.add(btnStart);
		btnStart.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String dir; 
				if(dirTextField.getText().equals("")) {
					try {
						dir = new File(MainJFrame.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath())
								.getParentFile().getPath();
					} catch (URISyntaxException e1) {
						dir = "";
					}
				} else {
					dir = dirTextField.getText();
				}
				if(!dir.endsWith("/") && !dir.endsWith("\\")) {
					dir +="/";
				}

				CsvSyncConf conf = new CsvSyncConf((int) setDimensionSpinner.getValue(), 
						(int) headerSpinner.getValue(), 
						dir + inFileTextField.getText(),
						dir + outFileTextField.getText(),
						(String) inSplitComboBox.getSelectedItem(),
						(String) outSplitSpinner.getSelectedItem());
				CsvSync sync = new CsvSync(conf, new Reporter() {

					@Override
					public void reportStatus(String status) {
						lblStatus.setText(status);
						
					}

					@Override
					public void reportException(Exception e) {
						JOptionPane.showMessageDialog(null,
							    e.getLocalizedMessage() + dirTextField.getText(),
							    "Errore", JOptionPane.ERROR_MESSAGE);
						
					}
					
				});
				sync.run();
			}
			
		});
		
		lblStatus = new JLabel("");
		lblStatus.setBounds(22, 300, 268, 30);
		contentPane.add(lblStatus);
	}
}
