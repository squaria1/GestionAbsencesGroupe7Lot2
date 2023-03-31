package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Administratif;
import model.Cours;
import model.Etudiant;
import model.Professeur;
import model.Profil;

public class ActionsGestionnaireDAO extends IdentificationBdd {
	
	public ActionsGestionnaireDAO() {
		super();
	}
	
	public int suppr(Profil profil, int typeUtilisateur) throws Exception {
		try (Connection con = DriverManager.getConnection(URL, LOGIN, PWD);) {
			PreparedStatement ps = null;

			int effectuee = 0;
			
			switch(typeUtilisateur) {
			case 0:
				ps = con.prepareStatement("DELETE FROM Lot2_Etudiant "
						+ "WHERE etu_nom = ? AND etu_prenom = ? AND etu_email = ?");
				break;
			case 1:
				ps = con.prepareStatement("DELETE FROM Lot2_Professeur "
						+ "WHERE prof_nom = ? AND prof_prenom = ? AND prof_email = ?");
				break;
			case 2:
				ps = con.prepareStatement("DELETE FROM Lot2_Administratif "
						+ "WHERE admin_nom = ? AND admin_prenom = ? AND admin_email = ?");
				break;
			}
			ps.setString(1, profil.getNom());
			ps.setString(2, profil.getPrenom());
			ps.setString(3, profil.getEmail());
			
			effectuee = ps.executeUpdate();
			
			return effectuee;
			}
		}
	
	public int supprCours(String nomCours) throws Exception {
		try (Connection con = DriverManager.getConnection(URL, LOGIN, PWD);) {
			int effectuee = 0;
			PreparedStatement ps = con.prepareStatement("DELETE FROM Lot2_Cours "
					+ "WHERE cours_nom = ?");;

			ps.setString(1, nomCours);
			
			effectuee = ps.executeUpdate();
			
			return effectuee;
			}
		}
	
	public Etudiant getEtudiant(Profil profil) throws Exception {
		
		try (Connection con = DriverManager.getConnection(URL, LOGIN, PWD);) {
			Etudiant etudiant = null;
			
			PreparedStatement ps = con.prepareStatement("SELECT etu_nom, etu_prenom, etu_email, grp_numero, fil_nom FROM Lot2_Etudiant "
						+ "JOIN Lot2_Filiere ON etu_fil_id = fil_id "
						+ "JOIN Lot2_Groupe ON etu_grp_id = grp_id "
						+ "WHERE etu_nom = ? AND etu_prenom = ? AND etu_email = ?");
			ps.setString(1, profil.getNom());
			ps.setString(2, profil.getPrenom());
			ps.setString(3, profil.getEmail());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				etudiant = new Etudiant(profil.getNom(), profil.getPrenom(), profil.getEmail(),
						rs.getString("fil_nom"), rs.getInt("grp_numero"));
			}
		return etudiant;
		}
	}
	
	public Professeur getProfesseur(Profil profil) throws Exception {
		
		try (Connection con = DriverManager.getConnection(URL, LOGIN, PWD);) {
			Professeur professeur = null;
			
			PreparedStatement ps = con.prepareStatement("SELECT prof_nom, prof_prenom, prof_email, prof_numTelephone "
					+ "FROM Lot2_Professeur "
					+ "WHERE prof_nom = ? AND prof_prenom = ? AND prof_email = ?");
			ps.setString(1, profil.getNom());
			ps.setString(2, profil.getPrenom());
			ps.setString(3, profil.getEmail());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				professeur = new Professeur(profil.getNom(), profil.getPrenom(), profil.getEmail(),
						rs.getString("prof_numTelephone"));
			}
		return professeur;
		}
	}
	
	public Administratif getAdministratif(Profil profil) throws Exception {
		
		try (Connection con = DriverManager.getConnection(URL, LOGIN, PWD);) {
			Administratif administratif = null;
			
			PreparedStatement ps = con.prepareStatement("SELECT admin_nom, admin_prenom, admin_email "
					+ "FROM Lot2_Administratif "
					+ "WHERE admin_nom = ? AND admin_prenom = ? AND admin_email = ?");
			ps.setString(1, profil.getNom());
			ps.setString(2, profil.getPrenom());
			ps.setString(3, profil.getEmail());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				administratif = new Administratif(profil.getNom(), profil.getPrenom(), profil.getEmail());
			}
		return administratif;
		}
	}
	
	public int modEtudiant(Profil profil, Etudiant etudiant, String pwd) throws Exception {
		try (Connection con = DriverManager.getConnection(URL, LOGIN, PWD);) {
			int effectuee = 0;
			int filiere = 1;
			
			if(etudiant.getFiliere().equals("Apprentissage"))
				filiere = 2;
			
			PreparedStatement ps = con.prepareStatement("UPDATE Lot2_Etudiant "
					+ "SET etu_nom = NVL(?, etu_nom), etu_prenom = NVL(?, etu_prenom), etu_pwd = NVL(?, etu_pwd), "
					+ "etu_email = NVL(?, etu_email), etu_fil_id = NVL(NULLIF(?,0), etu_fil_id), etu_grp_id = NVL(NULLIF(?,0), etu_grp_id)"
					+ "WHERE etu_nom = ? AND etu_prenom = ? AND etu_email = ?");

		
			ps.setString(1, etudiant.getNom());
			ps.setString(2, etudiant.getPrenom());
			ps.setString(3, pwd);
			ps.setString(4, etudiant.getEmail());
			ps.setInt(5, filiere);
			ps.setInt(6, etudiant.getGroupe());
			ps.setString(7, profil.getNom());
			ps.setString(8, profil.getPrenom());
			ps.setString(9, profil.getEmail());
			
			effectuee = ps.executeUpdate();
			
			return effectuee;
			}
		}
	
	public int modProfesseur(Profil profil, Professeur professeur, String pwd) throws Exception {
		try (Connection con = DriverManager.getConnection(URL, LOGIN, PWD);) {
			int effectuee = 0;
			
			PreparedStatement ps = con.prepareStatement("UPDATE Lot2_Professeur "
					+ "SET prof_nom = NVL(?, prof_nom), prof_prenom = NVL(?, prof_prenom), prof_pwd = NVL(?, prof_pwd), "
					+ "prof_email = NVL(?, prof_email), prof_numTelephone = NVL(?, prof_numTelephone) "
					+ "WHERE prof_nom = ? AND prof_prenom = ? AND prof_email = ?");

			
			ps.setString(1, professeur.getNom());
			ps.setString(2, professeur.getPrenom());
			ps.setString(3, pwd);
			ps.setString(4, professeur.getEmail());
			ps.setString(5, professeur.getNumTelephone());
			ps.setString(6, profil.getNom());
			ps.setString(7, profil.getPrenom());
			ps.setString(8, profil.getEmail());
			
			effectuee = ps.executeUpdate();
			
			return effectuee;
			}
		}
	
	public int modAdministratif(Profil profil, Administratif administratif, String pwd) throws Exception {
		try (Connection con = DriverManager.getConnection(URL, LOGIN, PWD);) {
			int effectuee = 0;
			
			PreparedStatement ps = con.prepareStatement("UPDATE Lot2_Administratif "
					+ "SET admin_nom = NVL(?, admin_nom), admin_prenom = NVL(?, admin_prenom), "
					+ "admin_pwd = NVL(?, admin_pwd), admin_email = NVL(?, admin_email) "
					+ "WHERE admin_nom = ? AND admin_prenom = ? AND admin_email = ?");

			
			ps.setString(1, administratif.getNom());
			ps.setString(2, administratif.getPrenom());
			ps.setString(3, pwd);
			ps.setString(4, administratif.getEmail());
			ps.setString(5, profil.getNom());
			ps.setString(6, profil.getPrenom());
			ps.setString(7, profil.getEmail());
			
			effectuee = ps.executeUpdate();
			
			return effectuee;
			}
		}
	
	public int modCours(Cours cours, String nomCours) throws Exception {
		try (Connection con = DriverManager.getConnection(URL, LOGIN, PWD);) {
			int effectuee = 0;
			PreparedStatement ps = con.prepareStatement("UPDATE Lot2_Cours "
					+ "SET cours_nom = NVL(?, cours_nom), cours_prof_id = NVL(NULLIF(?,0), cours_prof_id), "
					+ "cours_NbHeuresAmphi = NVL(NULLIF(?,0.0), cours_NbHeuresAmphi), cours_NbHeuresTD = NVL(NULLIF(?,0.0), cours_NbHeuresTD), "
					+ "cours_NbHeuresTP = NVL(NULLIF(?,0.0), cours_NbHeuresTP), cours_NbHeuresExamen = NVL(NULLIF(?,0.0), cours_NbHeuresExamen) "
					+ "WHERE cours_nom = ?");

			System.out.println("+" + cours.getNom() + "+");
			System.out.println("+" + cours.getEnseignantId() + "+");
			System.out.println("+" + cours.getNbHeuresAmphi() + "+");
			System.out.println("+" + cours.getNbHeuresTD() + "+");
			System.out.println("+" + cours.getNbHeuresTP() + "+");
			System.out.println("+" + cours.getNbHeuresExamen() + "+");
			System.out.println("+" + nomCours + "+");
			ps.setString(1, cours.getNom());
			ps.setInt(2, cours.getEnseignantId());
			ps.setFloat(3, cours.getNbHeuresAmphi());
			ps.setFloat(4, cours.getNbHeuresTD());
			ps.setFloat(5, cours.getNbHeuresTP());
			ps.setFloat(6, cours.getNbHeuresExamen());
			ps.setString(7, nomCours);
			
			effectuee = ps.executeUpdate();
			
			return effectuee;
			}
		}
	
	public int getLastIdTable(int table) throws Exception {
		try (Connection con = DriverManager.getConnection(URL, LOGIN, PWD);) {
			PreparedStatement ps = null;
			int lastIdTable = 0;
			
			switch(table) {
			case 0:
				ps = con.prepareStatement("SELECT etu_id FROM Lot2_Etudiant");
				break;
			case 1:
				ps = con.prepareStatement("SELECT prof_id FROM Lot2_Professeur");
				break;
			case 2:
				ps = con.prepareStatement("SELECT admin_id FROM Lot2_Administratif");
				break;
			case 3:
				ps = con.prepareStatement("SELECT cours_id FROM Lot2_Cours");
				break;
			}
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				lastIdTable++;
			}
			
			return lastIdTable;
		}
		
	}
	
	public int creerEtudiant(Etudiant etudiant, String pwd) throws Exception {
		try (Connection con = DriverManager.getConnection(URL, LOGIN, PWD);) {
			PreparedStatement ps = null;

			int effectuee = 0;
			int filiere = 1;
			
			if(etudiant.getFiliere().equals("Apprentissage"))
				filiere = 2;
			
			ps = con.prepareStatement("INSERT INTO Lot2_Etudiant "
					+ "VALUES (?,?,?,?,?,?,?)");

			ps.setInt(1, getLastIdTable(0)+1);
			ps.setString(2, etudiant.getNom());
			ps.setString(3, etudiant.getPrenom());
			ps.setString(4, pwd);
			ps.setString(5, etudiant.getEmail());
			ps.setInt(6, filiere);
			ps.setInt(7, etudiant.getGroupe());
			
			effectuee = ps.executeUpdate();
			
			return effectuee;
			}
		}
	
	public int creerProfesseur(Professeur professeur, String pwd) throws Exception {
		try (Connection con = DriverManager.getConnection(URL, LOGIN, PWD);) {
			PreparedStatement ps = null;

			int effectuee = 0;
			
			ps = con.prepareStatement("INSERT INTO Lot2_Professeur "
					+ "VALUES (?,?,?,?,?,?)");

			ps.setInt(1, getLastIdTable(1)+1);
			ps.setString(2, professeur.getNom());
			ps.setString(3, professeur.getPrenom());
			ps.setString(4, pwd);
			ps.setString(5, professeur.getEmail());
			ps.setString(6, professeur.getNumTelephone());
			
			effectuee = ps.executeUpdate();
			
			return effectuee;
			}
		}
	
	public int creerAdministratif(Administratif administratif, String pwd) throws Exception {
		try (Connection con = DriverManager.getConnection(URL, LOGIN, PWD);) {
			PreparedStatement ps = null;

			int effectuee = 0;
			
			ps = con.prepareStatement("INSERT INTO Lot2_Administratif "
					+ "VALUES (?,?,?,?,?)");

			ps.setInt(1, getLastIdTable(2)+1);
			ps.setString(2, administratif.getNom());
			ps.setString(3, administratif.getPrenom());
			ps.setString(4, pwd);
			ps.setString(5, administratif.getEmail());
			
			effectuee = ps.executeUpdate();
			
			return effectuee;
			}
		}
	
	public int creerCours(Cours cours) throws Exception {
		try (Connection con = DriverManager.getConnection(URL, LOGIN, PWD);) {
			PreparedStatement ps = null;

			int effectuee = 0;
			
			ps = con.prepareStatement("INSERT INTO Lot2_Cours "
					+ "VALUES (?,?,?,?,?,?,?)");
			
			ps.setInt(1, getLastIdTable(3)+1);
			ps.setString(2, cours.getNom());
			ps.setInt(3, cours.getEnseignantId());
			ps.setFloat(4, cours.getNbHeuresAmphi());
			ps.setFloat(5, cours.getNbHeuresTD());
			ps.setFloat(6, cours.getNbHeuresTP());
			ps.setFloat(7, cours.getNbHeuresExamen());
			
			effectuee = ps.executeUpdate();
			
			return effectuee;
			}
		}
	
}
