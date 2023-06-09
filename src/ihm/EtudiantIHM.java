package ihm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Classe interface utilisateur du menu d'un compte etudiant
 * 
 * @author Loic OUASSA, Mael PAROT
 * @version 1.0
 */
public class EtudiantIHM extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frmMenuPrincipalEtu;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EtudiantIHM window = new EtudiantIHM();
					window.frmMenuPrincipalEtu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public EtudiantIHM() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		/**
		 * Creation de la JFrame
		 */
		frmMenuPrincipalEtu = new JFrame();
		frmMenuPrincipalEtu.setVisible(true);
		frmMenuPrincipalEtu.setTitle("Menu Principal - Etudiant");
		frmMenuPrincipalEtu.setBounds(100, 100, 1045, 741);
		frmMenuPrincipalEtu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenuPrincipalEtu.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		frmMenuPrincipalEtu.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(0, 128, 255));
		frmMenuPrincipalEtu.getContentPane().add(panel_5);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 158, 60));
		panel_5.add(panel);
		FlowLayout fl_panel = new FlowLayout(FlowLayout.CENTER, 5, 5);
		panel.setLayout(fl_panel);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(224, 158, 60));
		panel.add(panel_10);
		FlowLayout flowLayout = (FlowLayout) panel_10.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);

		/**
		 * Bouton retour pour la navigation du logiciel
		 */
		JButton btnNewButtonRetour = new JButton("Retour");
		btnNewButtonRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ConnexionIHM();
				frmMenuPrincipalEtu.dispose();
			}
		});
		btnNewButtonRetour.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel_10.add(btnNewButtonRetour);
		
		JLabel lblNewLabel = new JLabel("Menu - Etudiant");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 45));
		panel.add(lblNewLabel);
		
		JPanel panel_6 = new JPanel();
        panel_6.setBackground(new Color(224, 158, 60));
        panel_5.add(panel_6);
		FlowLayout fl_panel1 = new FlowLayout(FlowLayout.CENTER, 5, 5);
		panel.setLayout(fl_panel1);
		
		ImageIcon imageIcon = new ImageIcon("img/esig.png");
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 30));
		JLabel imageLabel = new JLabel();
        imageLabel.setIcon(imageIcon);
        panel_6.add(imageLabel);
        

        ImageIcon imageIcon1 = new ImageIcon("img/etudiant.png");
        Image image = imageIcon1.getImage().getScaledInstance(-1, 150, Image.SCALE_DEFAULT); // Adjust the height (200) as desired
        ImageIcon scaledIcon = new ImageIcon(image);
		JLabel imageLabel1 = new JLabel();
        imageLabel1.setIcon(scaledIcon);
        panel_6.add(imageLabel1);
		
		JPanel panel_2 = new JPanel();
		frmMenuPrincipalEtu.getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(224, 158, 60));
		panel_2.add(panel_1);
		
		JButton btnNewButton_2 = new JButton("Planning de groupe");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					/**
					 * Ouvre une nouvelle fenetre et ferme l'ancienne
					 */
					new PlanningGroupeIHM();
					frmMenuPrincipalEtu.dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setForeground(new Color(0, 0, 0));
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_1.add(btnNewButton_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(224, 158, 60));
		panel_2.add(panel_3);
		
		JButton btnNewButton_1 = new JButton("Absences");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					/**
					 * Ouvre une nouvelle fenetre et ferme l'ancienne
					 */
					new ListeAbsencesEtuIHM();
					frmMenuPrincipalEtu.dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_3.add(btnNewButton_1);
		

		JButton btnNewButton = new JButton("Declarer une absence physique");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/**
				 * Ouvre une nouvelle fenetre et ferme l'ancienne
				 */
				new DeposerJustificatifAbsPhysiqueIHM();
				frmMenuPrincipalEtu.dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnNewButton.setForeground(Color.BLACK);
		panel_1.add(btnNewButton);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(224, 158, 60));
		panel_2.add(panel_4);
		
		JButton btnGroupe = new JButton("Notes et notifications de rattrapage");
		btnGroupe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					/**
					 * Ouvre une nouvelle fenetre et ferme l'ancienne
					 */
					new NotesEtRatrapagesEtuIHM();
					frmMenuPrincipalEtu.dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnGroupe.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_4.add(btnGroupe);
	}

}
