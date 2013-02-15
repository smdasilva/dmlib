package vue;

import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;


import javax.swing.JButton;

public class Frame_metadata extends JFrame{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	JButton btnNewButton = new JButton("Valider");
	JButton btnNewButton_3 = new JButton("Annuler");
	JButton btnNewButton_2 = new JButton("Valider");
	JButton btnNewButton_1 = new JButton("Annuler");

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame_metadata frame = new Frame_metadata();
					frame.setVisible(true);
					frame.setSize(1200, 400);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public Frame_metadata() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 400, 714, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		layout.putConstraint(SpringLayout.NORTH, tabbedPane, 10,
				SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, tabbedPane, 32,
				SpringLayout.WEST, contentPane);
		contentPane.add(tabbedPane);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setForeground(Color.BLACK);
		tabbedPane.addTab("Fichier MP3", null, layeredPane, null);
		tabbedPane.setForegroundAt(0, Color.BLUE);
		GridBagLayout gbl_layeredPane = new GridBagLayout();
		gbl_layeredPane.columnWidths = new int[] { 33, 169, 48, 169, 43, 169, 0 };
		gbl_layeredPane.rowHeights = new int[] { 19, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_layeredPane.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gbl_layeredPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, Double.MIN_VALUE };
		layeredPane.setLayout(gbl_layeredPane);

		JLabel lblNewLabel_2 = new JLabel("[Général]");
		lblNewLabel_2.setForeground(Color.BLUE);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		layeredPane.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JLabel lblTitre = new JLabel("Titre : ");
		lblTitre.setToolTipText("");
		layeredPane.setLayer(lblTitre, 0);
		GridBagConstraints gbc_lblTitre = new GridBagConstraints();
		gbc_lblTitre.anchor = GridBagConstraints.WEST;
		gbc_lblTitre.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitre.gridx = 0;
		gbc_lblTitre.gridy = 2;
		layeredPane.add(lblTitre, gbc_lblTitre);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.anchor = GridBagConstraints.NORTH;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		layeredPane.add(textField, gbc_textField);
		textField.setColumns(15);

		JLabel lblNewLabel = new JLabel("Artiste : ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 3;
		layeredPane.add(lblNewLabel, gbc_lblNewLabel);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.anchor = GridBagConstraints.NORTH;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 3;
		layeredPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(15);

		JLabel lblAlbum = new JLabel("Album : ");
		GridBagConstraints gbc_lblAlbum = new GridBagConstraints();
		gbc_lblAlbum.anchor = GridBagConstraints.WEST;
		gbc_lblAlbum.insets = new Insets(0, 0, 5, 5);
		gbc_lblAlbum.gridx = 0;
		gbc_lblAlbum.gridy = 4;
		layeredPane.add(lblAlbum, gbc_lblAlbum);

		textField_2 = new JTextField();
		textField_2.setColumns(15);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.anchor = GridBagConstraints.NORTH;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 4;
		layeredPane.add(textField_2, gbc_textField_2);

		JLabel lblAnne = new JLabel("Année : ");
		GridBagConstraints gbc_lblAnne = new GridBagConstraints();
		gbc_lblAnne.anchor = GridBagConstraints.WEST;
		gbc_lblAnne.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnne.gridx = 0;
		gbc_lblAnne.gridy = 5;
		layeredPane.add(lblAnne, gbc_lblAnne);

		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.anchor = GridBagConstraints.NORTH;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 5;
		layeredPane.add(textField_3, gbc_textField_3);
		textField_3.setColumns(15);

		JLabel lblDre = new JLabel("Durée : ");
		lblDre.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblDre = new GridBagConstraints();
		gbc_lblDre.anchor = GridBagConstraints.WEST;
		gbc_lblDre.insets = new Insets(0, 0, 5, 5);
		gbc_lblDre.gridx = 0;
		gbc_lblDre.gridy = 6;
		layeredPane.add(lblDre, gbc_lblDre);

		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 6;
		layeredPane.add(textField_4, gbc_textField_4);
		textField_4.setColumns(15);

		JLabel lblNewLabel_1 = new JLabel("Commentaire : ");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 7;
		layeredPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridheight = 2;
		gbc_textArea.gridwidth = 3;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 7;
		layeredPane.add(textArea, gbc_textArea);

		JLabel lblNewLabel_3 = new JLabel("[AUDIO]");
		lblNewLabel_3.setForeground(Color.BLUE);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 9;
		layeredPane.add(lblNewLabel_3, gbc_lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Codec : ");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 10;
		layeredPane.add(lblNewLabel_4, gbc_lblNewLabel_4);

		textField_5 = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 10;
		layeredPane.add(textField_5, gbc_textField_5);
		textField_5.setColumns(15);

		JLabel lblNewLabel_5 = new JLabel("Canaux : ");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 11;
		layeredPane.add(lblNewLabel_5, gbc_lblNewLabel_5);

		textField_6 = new JTextField();
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 5);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 1;
		gbc_textField_6.gridy = 11;
		layeredPane.add(textField_6, gbc_textField_6);
		textField_6.setColumns(15);

		JLabel lblNewLabel_6 = new JLabel("Echantillonnage : ");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 12;
		layeredPane.add(lblNewLabel_6, gbc_lblNewLabel_6);

		textField_7 = new JTextField();
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 5, 5);
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 1;
		gbc_textField_7.gridy = 12;
		layeredPane.add(textField_7, gbc_textField_7);
		textField_7.setColumns(15);

		JLabel lblNewLabel_7 = new JLabel("Débit : ");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 13;
		layeredPane.add(lblNewLabel_7, gbc_lblNewLabel_7);

		textField_8 = new JTextField();
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.insets = new Insets(0, 0, 5, 5);
		gbc_textField_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_8.gridx = 1;
		gbc_textField_8.gridy = 13;
		layeredPane.add(textField_8, gbc_textField_8);
		textField_8.setColumns(15);

		JButton btnNewButton_2 = new JButton("Valider");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 15;
		layeredPane.add(btnNewButton_2, gbc_btnNewButton_2);

		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 3;
		gbc_btnNewButton_3.gridy = 15;
		layeredPane.add(btnNewButton_3, gbc_btnNewButton_3);

		JLayeredPane layeredPane_2 = new JLayeredPane();
		layeredPane_2.setForeground(Color.BLACK);
		tabbedPane.addTab("Fichier image", null, layeredPane_2, null);
		tabbedPane.setForegroundAt(1, Color.BLUE);
		GridBagLayout gbl_layeredPane_2 = new GridBagLayout();
		gbl_layeredPane_2.columnWidths = new int[] { 33, 169, 48, 169, 43, 169,
				0 };
		gbl_layeredPane_2.rowHeights = new int[] { 19, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_layeredPane_2.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		gbl_layeredPane_2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		layeredPane_2.setLayout(gbl_layeredPane_2);

		JLabel lblConstructeur = new JLabel("Constructeur : ");
		lblConstructeur.setToolTipText("");
		GridBagConstraints gbc_lblConstructeur = new GridBagConstraints();
		gbc_lblConstructeur.anchor = GridBagConstraints.WEST;
		gbc_lblConstructeur.insets = new Insets(0, 0, 5, 5);
		gbc_lblConstructeur.gridx = 0;
		gbc_lblConstructeur.gridy = 1;
		layeredPane_2.add(lblConstructeur, gbc_lblConstructeur);

		textField_9 = new JTextField();
		textField_9.setColumns(15);
		GridBagConstraints gbc_textField_9 = new GridBagConstraints();
		gbc_textField_9.gridwidth = 2;
		gbc_textField_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_9.anchor = GridBagConstraints.NORTH;
		gbc_textField_9.insets = new Insets(0, 0, 5, 5);
		gbc_textField_9.gridx = 1;
		gbc_textField_9.gridy = 1;
		layeredPane_2.add(textField_9, gbc_textField_9);

		JLabel lblModle = new JLabel("Modéle : ");
		lblModle.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblModle = new GridBagConstraints();
		gbc_lblModle.anchor = GridBagConstraints.WEST;
		gbc_lblModle.insets = new Insets(0, 0, 5, 5);
		gbc_lblModle.gridx = 0;
		gbc_lblModle.gridy = 2;
		layeredPane_2.add(lblModle, gbc_lblModle);

		textField_10 = new JTextField();
		textField_10.setColumns(15);
		GridBagConstraints gbc_textField_10 = new GridBagConstraints();
		gbc_textField_10.gridwidth = 2;
		gbc_textField_10.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_10.anchor = GridBagConstraints.NORTH;
		gbc_textField_10.insets = new Insets(0, 0, 5, 5);
		gbc_textField_10.gridx = 1;
		gbc_textField_10.gridy = 2;
		layeredPane_2.add(textField_10, gbc_textField_10);

		JLabel lblOrientation = new JLabel("Orientation : ");
		GridBagConstraints gbc_lblOrientation = new GridBagConstraints();
		gbc_lblOrientation.anchor = GridBagConstraints.WEST;
		gbc_lblOrientation.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrientation.gridx = 0;
		gbc_lblOrientation.gridy = 3;
		layeredPane_2.add(lblOrientation, gbc_lblOrientation);

		textField_11 = new JTextField();
		textField_11.setColumns(15);
		GridBagConstraints gbc_textField_11 = new GridBagConstraints();
		gbc_textField_11.gridwidth = 2;
		gbc_textField_11.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_11.anchor = GridBagConstraints.NORTH;
		gbc_textField_11.insets = new Insets(0, 0, 5, 5);
		gbc_textField_11.gridx = 1;
		gbc_textField_11.gridy = 3;
		layeredPane_2.add(textField_11, gbc_textField_11);

		JLabel lblRsolutionX = new JLabel("Résolution X : ");
		GridBagConstraints gbc_lblRsolutionX = new GridBagConstraints();
		gbc_lblRsolutionX.anchor = GridBagConstraints.WEST;
		gbc_lblRsolutionX.insets = new Insets(0, 0, 5, 5);
		gbc_lblRsolutionX.gridx = 0;
		gbc_lblRsolutionX.gridy = 4;
		layeredPane_2.add(lblRsolutionX, gbc_lblRsolutionX);

		textField_12 = new JTextField();
		textField_12.setColumns(10);
		GridBagConstraints gbc_textField_12 = new GridBagConstraints();
		gbc_textField_12.gridwidth = 2;
		gbc_textField_12.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_12.anchor = GridBagConstraints.NORTH;
		gbc_textField_12.insets = new Insets(0, 0, 5, 5);
		gbc_textField_12.gridx = 1;
		gbc_textField_12.gridy = 4;
		layeredPane_2.add(textField_12, gbc_textField_12);

		JLabel lblRsolutionY = new JLabel("Résolution Y : ");
		lblRsolutionY.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblRsolutionY = new GridBagConstraints();
		gbc_lblRsolutionY.anchor = GridBagConstraints.WEST;
		gbc_lblRsolutionY.insets = new Insets(0, 0, 5, 5);
		gbc_lblRsolutionY.gridx = 0;
		gbc_lblRsolutionY.gridy = 5;
		layeredPane_2.add(lblRsolutionY, gbc_lblRsolutionY);

		textField_13 = new JTextField();
		textField_13.setColumns(10);
		GridBagConstraints gbc_textField_13 = new GridBagConstraints();
		gbc_textField_13.gridwidth = 2;
		gbc_textField_13.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_13.insets = new Insets(0, 0, 5, 5);
		gbc_textField_13.gridx = 1;
		gbc_textField_13.gridy = 5;
		layeredPane_2.add(textField_13, gbc_textField_13);

		JLabel lblUnitDeRsolution = new JLabel("Unité de résolution : ");
		GridBagConstraints gbc_lblUnitDeRsolution = new GridBagConstraints();
		gbc_lblUnitDeRsolution.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblUnitDeRsolution.insets = new Insets(0, 0, 5, 5);
		gbc_lblUnitDeRsolution.gridx = 0;
		gbc_lblUnitDeRsolution.gridy = 6;
		layeredPane_2.add(lblUnitDeRsolution, gbc_lblUnitDeRsolution);

		textField_14 = new JTextField();
		textField_14.setColumns(10);
		GridBagConstraints gbc_textField_14 = new GridBagConstraints();
		gbc_textField_14.gridwidth = 2;
		gbc_textField_14.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_14.insets = new Insets(0, 0, 5, 5);
		gbc_textField_14.gridx = 1;
		gbc_textField_14.gridy = 6;
		layeredPane_2.add(textField_14, gbc_textField_14);

		JLabel lblLogiciel = new JLabel("Logiciel : ");
		GridBagConstraints gbc_lblLogiciel = new GridBagConstraints();
		gbc_lblLogiciel.anchor = GridBagConstraints.WEST;
		gbc_lblLogiciel.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogiciel.gridx = 0;
		gbc_lblLogiciel.gridy = 7;
		layeredPane_2.add(lblLogiciel, gbc_lblLogiciel);

		textField_15 = new JTextField();
		textField_15.setColumns(10);
		GridBagConstraints gbc_textField_15 = new GridBagConstraints();
		gbc_textField_15.gridwidth = 2;
		gbc_textField_15.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_15.insets = new Insets(0, 0, 5, 5);
		gbc_textField_15.gridx = 1;
		gbc_textField_15.gridy = 7;
		layeredPane_2.add(textField_15, gbc_textField_15);

		JLabel lblDateHeure = new JLabel("Date & Heure : ");
		GridBagConstraints gbc_lblDateHeure = new GridBagConstraints();
		gbc_lblDateHeure.anchor = GridBagConstraints.WEST;
		gbc_lblDateHeure.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateHeure.gridx = 0;
		gbc_lblDateHeure.gridy = 8;
		layeredPane_2.add(lblDateHeure, gbc_lblDateHeure);

		textField_16 = new JTextField();
		textField_16.setColumns(10);
		GridBagConstraints gbc_textField_16 = new GridBagConstraints();
		gbc_textField_16.gridwidth = 2;
		gbc_textField_16.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_16.insets = new Insets(0, 0, 5, 5);
		gbc_textField_16.gridx = 1;
		gbc_textField_16.gridy = 8;
		layeredPane_2.add(textField_16, gbc_textField_16);

		JLabel lblCompression = new JLabel("Compression : ");
		GridBagConstraints gbc_lblCompression = new GridBagConstraints();
		gbc_lblCompression.anchor = GridBagConstraints.WEST;
		gbc_lblCompression.insets = new Insets(0, 0, 5, 5);
		gbc_lblCompression.gridx = 0;
		gbc_lblCompression.gridy = 9;
		layeredPane_2.add(lblCompression, gbc_lblCompression);

		textField_17 = new JTextField();
		textField_17.setColumns(10);
		GridBagConstraints gbc_textField_17 = new GridBagConstraints();
		gbc_textField_17.gridwidth = 2;
		gbc_textField_17.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_17.insets = new Insets(0, 0, 5, 5);
		gbc_textField_17.gridx = 1;
		gbc_textField_17.gridy = 9;
		layeredPane_2.add(textField_17, gbc_textField_17);

		JLabel lblVersionExif = new JLabel("Version Exif : ");
		GridBagConstraints gbc_lblVersionExif = new GridBagConstraints();
		gbc_lblVersionExif.anchor = GridBagConstraints.WEST;
		gbc_lblVersionExif.insets = new Insets(0, 0, 5, 5);
		gbc_lblVersionExif.gridx = 0;
		gbc_lblVersionExif.gridy = 10;
		layeredPane_2.add(lblVersionExif, gbc_lblVersionExif);

		textField_18 = new JTextField();
		GridBagConstraints gbc_textField_18 = new GridBagConstraints();
		gbc_textField_18.gridwidth = 2;
		gbc_textField_18.insets = new Insets(0, 0, 5, 5);
		gbc_textField_18.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_18.gridx = 1;
		gbc_textField_18.gridy = 10;
		layeredPane_2.add(textField_18, gbc_textField_18);
		textField_18.setColumns(1);

		JLabel lblNewLabel_8 = new JLabel("Flash : ");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 11;
		layeredPane_2.add(lblNewLabel_8, gbc_lblNewLabel_8);

		textField_19 = new JTextField();
		GridBagConstraints gbc_textField_19 = new GridBagConstraints();
		gbc_textField_19.gridwidth = 2;
		gbc_textField_19.insets = new Insets(0, 0, 5, 5);
		gbc_textField_19.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_19.gridx = 1;
		gbc_textField_19.gridy = 11;
		layeredPane_2.add(textField_19, gbc_textField_19);
		textField_19.setColumns(10);

		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 13;
		layeredPane_2.add(btnNewButton, gbc_btnNewButton);

		JButton btnNewButton_1 = new JButton("Annuler");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 3;
		gbc_btnNewButton_1.gridy = 13;
		layeredPane_2.add(btnNewButton_1, gbc_btnNewButton_1);
		// onglet MP3
		JTabbedPane onglet1 = new JTabbedPane();
		JPanel p1 = new JPanel();
		p1.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		onglet1.addTab("Fichier MP3", p1);
		
		Listeneur listen = new Listeneur();
		btnNewButton.addActionListener(listen);
		btnNewButton_2.addActionListener(listen);
	}
}




		//btnNewButton.addActionListener(this);
		/*
		btnNewButton_3.addActionListener(this);
		btnNewButton.addActionListener(this);
		btnNewButton_2.addActionListener(this);
*/
	

	/*@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnNewButton_1 || e.getSource() == btnNewButton_3) {
			//this.setVisible(false);
			this.dispose();
		}
		else{
			if(e.getSource() == btnNewButton || e.getSource() == btnNewButton_2){
				this.dispose();			
			}
		}
	}
}*/
