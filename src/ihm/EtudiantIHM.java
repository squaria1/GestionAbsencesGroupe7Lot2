package ihm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		frmMenuPrincipalEtu = new JFrame();
		frmMenuPrincipalEtu.setVisible(true);
		frmMenuPrincipalEtu.setTitle("Menu Principal - Etudiant");
		frmMenuPrincipalEtu.setBounds(100, 100, 1045, 741);
		frmMenuPrincipalEtu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenuPrincipalEtu.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		frmMenuPrincipalEtu.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 255));
		frmMenuPrincipalEtu.getContentPane().add(panel);
		FlowLayout fl_panel = new FlowLayout(FlowLayout.CENTER, 5, 5);
		panel.setLayout(fl_panel);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(0, 128, 255));
		panel.add(panel_10);
		FlowLayout flowLayout = (FlowLayout) panel_10.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
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
		
		JPanel panel_2 = new JPanel();
		frmMenuPrincipalEtu.getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 255));
		panel_2.add(panel_1);
		
		JButton btnNewButton_2 = new JButton("Planning de groupe");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
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
		panel_3.setBackground(new Color(0, 128, 255));
		panel_2.add(panel_3);
		
		JButton btnNewButton_1 = new JButton("Absences");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ListeAbsencesEtuIHM();
					frmMenuPrincipalEtu.dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_3.add(btnNewButton_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(0, 128, 255));
		panel_2.add(panel_4);
		
		JButton btnGroupe = new JButton("Notes et notifications de rattrapage");
		btnGroupe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
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
