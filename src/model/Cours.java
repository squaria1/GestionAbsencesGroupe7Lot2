package model;

/**
 * Classe modele java d'un cours
 * @author Loic OUASSA, Mael PAROT
 * @version 1.0
 */
public class Cours {
	/**
	 * 
	 */
	private String nom;
	/**
	 * 
	 */
	private int enseignantId;
	
	/**
	 * 
	 */
	private float nbHeuresAmphi;
	
	/**
	 * 
	 */
	private float nbHeuresTD;
	
	/**
	 * 
	 */
	private float nbHeuresTP;
	
	/**
	 * 
	 */
	private float nbHeuresExamen;
	
	/**
	 * @param nom
	 * @param enseignant
	 * @param nbHeuresAmphi
	 * @param nbHeuresTD
	 * @param nbHeuresTP
	 * @param nbHeuresExamen
	 */
	public Cours(String nom, int enseignantId,
			float nbHeuresAmphi, float nbHeuresTD, float nbHeuresTP, float nbHeuresExamen) {
		this.nom = nom;
		this.enseignantId = enseignantId;
		this.nbHeuresAmphi = nbHeuresAmphi;
		this.nbHeuresTD = nbHeuresTD;
		this.nbHeuresTP = nbHeuresTP;
		this.nbHeuresExamen = nbHeuresExamen;
	}

	/**
	 * @return
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return
	 */
	public int getEnseignantId() {
		return enseignantId;
	}

	/**
	 * @param enseignant
	 */
	public void setEnseignantId(int enseignantId) {
		this.enseignantId = enseignantId;
	}
	
	/**
	 * @return
	 */
	public float getNbHeuresAmphi() {
		return nbHeuresAmphi;
	}

	/**
	 * @param nbHeuresAmphi
	 */
	public void setNbHeuresAmphi(float nbHeuresAmphi) {
		this.nbHeuresAmphi = nbHeuresAmphi;
	}

	/**
	 * @return
	 */
	public float getNbHeuresTD() {
		return nbHeuresTD;
	}

	/**
	 * @param nbHeuresTD
	 */
	public void setNbHeuresTD(float nbHeuresTD) {
		this.nbHeuresTD = nbHeuresTD;
	}

	/**
	 * @return
	 */
	public float getNbHeuresTP() {
		return nbHeuresTP;
	}

	/**
	 * @param nbHeuresTP
	 */
	public void setNbHeuresTP(float nbHeuresTP) {
		this.nbHeuresTP = nbHeuresTP;
	}

	/**
	 * @return
	 */
	public float getNbHeuresExamen() {
		return nbHeuresExamen;
	}

	/**
	 * @param nbHeuresExamen
	 */
	public void setNbHeuresExamen(float nbHeuresExamen) {
		this.nbHeuresExamen = nbHeuresExamen;
	}
	
	/*
	public void modifierCours(String nom,int enseignantId, 
			int nbHeuresAmphi,int nbHeuresTD,int nbHeuresTP,int nbHeuresExamen) {
		setNom(nom);
		setEnseignantId(enseignantId);
		setNbHeuresAmphi(nbHeuresAmphi);
		setNbHeuresTD(nbHeuresTD);
		setNbHeuresTP(nbHeuresTP);
		setNbHeuresExamen(nbHeuresExamen);
		
	}
	public void display() {
		System.out.println("Le cours de "+nom+ " donné par Mr/Mme "+enseignantId+" a pour nombre d'heures en amphi"+nbHeuresAmphi+" heures,pour nombre d'heures de td "+nbHeuresTD+" heures, pour nombre d'heures de tp"+nbHeuresTP+" et a pour nombre d'heures d'examen "+nbHeuresExamen+"heures.");
	}*/
}
