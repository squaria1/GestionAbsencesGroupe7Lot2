package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import dao.ConnexionUtilisateurDAO;
import java.awt.Color;
import javax.swing.JPasswordField;

/**
 * Classe interface utilisateur de la connexion au compte utilisateur
 * 
 * @author Loic OUASSA, Mael PAROT
 * @version 1.0
 */
public class ConnexionIHM extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frmConnexion;
	private JTextField textField;
	private JLabel lblNewLabel_3;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnexionIHM window = new ConnexionIHM();
					window.frmConnexion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ConnexionIHM() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		/**
		 * Creation de la JFrame
		 */
		frmConnexion = new JFrame();
		frmConnexion.setTitle("Connexion");
		frmConnexion.setVisible(true);
		frmConnexion.setBounds(100, 100, 1045, 741);
		frmConnexion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConnexion.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		frmConnexion.setExtendedState(JFrame.MAXIMIZED_BOTH);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 158, 60));
		frmConnexion.getContentPane().add(panel);
		FlowLayout fl_panel = new FlowLayout(FlowLayout.CENTER, 5, 5);
		panel.setLayout(fl_panel);
		
		JLabel lblNewLabel = new JLabel("Connexion");
		lblNewLabel.setForeground(new Color(45, 62, 78));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 45));
		panel.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		frmConnexion.getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(224, 158, 60));
		panel_2.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Email : ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1.setForeground(new Color(45, 62, 78));
		panel_1.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_1.add(textField);
		textField.setColumns(20);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(224, 158, 60));
		panel_2.add(panel_3);
		
		JLabel lblNewLabel_2 = new JLabel("Mot de passe : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_2.setForeground(new Color(45, 62, 78));
		panel_3.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setColumns(20);
		panel_3.add(passwordField);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(224, 158, 60));
		panel_2.add(panel_4);
		
		JButton btnProfil = new JButton("Connexion");
		btnProfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Verification de textField non vierge 
				 * et des donnees entrees au format correct
				 */
				if (textField.getText().length() > 0 && passwordField.getPassword().length > 0) {
					connexion(textField.getText(), String.valueOf(passwordField.getPassword()));
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Vous devez entrer un email et un mot de passe.", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnProfil.setFont(new Font("Tahoma", Font.PLAIN, 28));
		panel_4.add(btnProfil);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(224, 158, 60));
		panel_2.add(panel_5);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_5.add(lblNewLabel_3);
	}
	
	
	/**
	 * Methode de connexion utilisateur quelconque en fonction 
	 * de l'email et du mot de passe entre par l'utilisateur
	 * @param email
	 * 			email entre par l'utilisateur
	 * @param pwd
	 * 			mot de passe entre par l'utilisateur
	 */
	public void connexion(String email, String pwd)
	{
		/**
		 * Instanciation du DAO de connexion et du tableau des resultats
		 */
		ConnexionUtilisateurDAO connDAO = new ConnexionUtilisateurDAO();
		int[] res = {-1,-1};
		try {
			res = connDAO.checkConnexion(email, pwd);

			/**
			 * Enregistrement de l'id SQL du compte desormais connecte et son type de compte
			 */
			IdEtTypeCompte.id = res[1];
			IdEtTypeCompte.typeCompte = res[0];
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * Ouvre une nouveau menu principal et ferme 
		 * la fenetre de connexion en fonction du type de compte
		 */
		switch(IdEtTypeCompte.typeCompte) {
		case -1:
			lblNewLabel_3.setText("Mauvais mot de passe ou email !");
			break;
		case 0:
			lblNewLabel_3.setText("Connecte en tant qu'etudiant !");
			frmConnexion.dispose();
			new EtudiantIHM();
			break;
		case 1:
			lblNewLabel_3.setText("Connecte en tant que professeur !");
			frmConnexion.dispose();
			new EnseignantIHM();
			break;
		case 2:
			lblNewLabel_3.setText("Connecte en tant qu'administratif !");
			frmConnexion.dispose();
			new AdministratifIHM();
			break;
		case 3:
			lblNewLabel_3.setText("Connecte en tant que gestionnaire !");
			frmConnexion.dispose();
			new GestionnaireIHM();
			break;
		}
	}

}


