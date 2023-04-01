package ihm;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import dao.ActionsGestionnaireDAO;
import model.Administratif;
import model.Cours;
import model.Groupe;
import model.PlanningGroupe;
import model.Profil;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.GridLayout;

public class GestionPlanningIHM {

	private JFrame frmCoursNonTraites;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblNewLabel_3_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionPlanningIHM window = new GestionPlanningIHM();
					window.frmCoursNonTraites.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public GestionPlanningIHM() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() {
		frmCoursNonTraites = new JFrame();
		frmCoursNonTraites.setVisible(true);
		frmCoursNonTraites.setTitle("Cours non traites");
		frmCoursNonTraites.setBounds(100, 100, 1409, 751);
		frmCoursNonTraites.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCoursNonTraites.getContentPane().setLayout(new BoxLayout(frmCoursNonTraites.getContentPane(), BoxLayout.Y_AXIS));
		
		ActionsGestionnaireDAO actionGest = new ActionsGestionnaireDAO();
		ArrayList<Cours> listeCours = null;
		try {
			listeCours = actionGest.listeCours();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frmCoursNonTraites.getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {182, 10, 0};
		gbl_panel.rowHeights = new int[] {20, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(false);
		table.setFont(new Font("Times New Roman", Font.BOLD, 18));
		table.setForeground(Color.DARK_GRAY);
		table.setToolTipText("");
		table.setDragEnabled(true);
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{Boolean.FALSE, "INTITULE", "NOM PROFESSEUR", "NBHEURESAMPHI", "NBHEURESAMPHI", "NBHEURESAMPHI", "NBHEURESAMPHI", "GROUPE"},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			
			boolean[] isCellEditable = new boolean[]{
                    true, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return isCellEditable[columnIndex];
            }
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(0).setMinWidth(5);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		
		for(int i = 0; i<listeCours.size(); i++) {
			ArrayList<Groupe> listeGroupe = null;
			try {
				listeGroupe = actionGest.getListeGroupeCours(i+1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String format = "";
			for(int j = 0; j < listeGroupe.size(); j++) {
				if(j != listeGroupe.size() - 1)
					format = format + listeGroupe.get(j).getGrpNum() + ", ";
				else
					format = format + listeGroupe.get(j).getGrpNum() + " ";
			}
			((DefaultTableModel) table.getModel()).addRow(new Object[]{
					Boolean.FALSE, listeCours.get(i).getNom(), listeCours.get(i).getEnseignantNom(),
					listeCours.get(i).getNbHeuresAmphi(),listeCours.get(i).getNbHeuresTD(),
					listeCours.get(i).getNbHeuresTP(), listeCours.get(i).getNbHeuresExamen(), format});
		}
		ListSelectionModel tableSelectionModel = table.getSelectionModel();
		tableSelectionModel.setSelectionInterval(0, 0);
		table.setSelectionModel(tableSelectionModel);
		table.repaint();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridheight = 2;
		gbc_table.gridwidth = 2;
		gbc_table.insets = new Insets(0, 0, 0, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 0;
		panel.add(table, gbc_table);
		
		JPanel panel_2 = new JPanel();
		frmCoursNonTraites.getContentPane().add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		
		JLabel lblEntrezLeGroupe = new JLabel("Insertion cours dans le planning :  ");
		lblEntrezLeGroupe.setForeground(Color.BLUE);
		lblEntrezLeGroupe.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblEntrezLeGroupe.setAlignmentX(0.5f);
		panel_4.add(lblEntrezLeGroupe);
		
		JLabel lblNewLabel = new JLabel("Numero groupe : ");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_4.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setColumns(10);
		panel_4.add(textField);
		
		JLabel lblNewLabel_1 = new JLabel("Date (YYYY-MM-DD) : ");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_4.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_1.setColumns(10);
		panel_4.add(textField_1);
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		
		JLabel lblNewLabel_2 = new JLabel("Heure debut du cours : ");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_3.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_2.setColumns(10);
		panel_3.add(textField_2);
		
		JLabel lblNewLabel__3 = new JLabel("Heure fin du cours : ");
		lblNewLabel__3.setForeground(Color.BLACK);
		lblNewLabel__3.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_3.add(lblNewLabel__3);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_3.setColumns(10);
		panel_3.add(textField_3);
		
		JPanel panel_1 = new JPanel();
		frmCoursNonTraites.getContentPane().add(panel_1);
		
		JButton btnNewButton_1 = new JButton("Inserer cours");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ligneNum = -1;
				for(int i = 0; i < table.getRowCount(); i++) {
					Boolean CaseCochee = Boolean.valueOf(table.getValueAt(i, 0).toString());
					if(CaseCochee)
						ligneNum = i;
				}
				if(ligneNum != -1)
					ajouterCoursPlanning(new PlanningGroupe(Integer.valueOf(textField.getText()),
							ligneNum, textField_1.getText(), Float.valueOf(textField_2.getText()),
							Float.valueOf(textField_3.getText())));
				else {
					JOptionPane.showMessageDialog(new JFrame(), "Vous n'avez pas coche d'absence.", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_1.add(btnNewButton_1);
		
		lblNewLabel_3_2 = new JLabel("");
		lblNewLabel_3_2.setForeground(Color.RED);
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_3_2);
		
	}
	
	public void ajouterCoursPlanning(PlanningGroupe planningGroupe) {
		ActionsGestionnaireDAO actionGest = new ActionsGestionnaireDAO();
		try {
			int effectuee = actionGest.ajouterCoursPlanning(planningGroupe);
			if (effectuee == 1)
				lblNewLabel_3_2.setText("Cours insere dans le planning de groupe !");
			else
				lblNewLabel_3_2.setText("Erreur ce groupe n'existe pas !");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}