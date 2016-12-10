package fr.ecombio.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * Classe pour serialiser des donnes sur le paiement :
 * <ul>
 * <li>num de la carte</li>
 * <li>mois</li>
 * <li>annee</li>
 * <li>code de verification a trois chiffres</li>
 * </ul>
 * </p>
 */
@SuppressWarnings("serial")
public class ValidationPaiement implements Serializable{
	private String num;
	private String mois;
	private String annee;
	private String codeVerif;
	
	public ValidationPaiement() {
		super();
	}
	
	public ValidationPaiement(String num, String mois, String annee, String codeVerif) {
		super();
		this.num = num;
		this.mois = mois;
		this.annee = annee;
		this.codeVerif = codeVerif;
	}
	
	/**
	 * @return num de la carte
	 */
	public String getNum() {
		return num;
	}
	/**
	 * 
	 * @param num de la carte
	 */
	public void setNum(String num) {
		this.num = num.replace("\n", "");
	}
	/**
	 * 
	 * @return mois
	 */
	public String getMois() {
		return mois;
	}
	/**
	 * 
	 * @param mois
	 */
	public void setMois(String mois) {
		this.mois = mois.replace("\n", "");
	}
	/**
	 * 
	 * @return annee
	 */
	public String getAnnee() {
		return annee;
	}
	/**
	 * 
	 * @param annee
	 */
	public void setAnnee(String annee) {
		this.annee = annee.replace("\n", "");
	}
	/**
	 * 
	 * @return code verfication
	 */
	public String getCodeVerif() {
		return codeVerif;
	}
	/**
	 * 
	 * @param codeVerif code verfication
	 */
	public void setCodeVerif(String codeVerif) {
		this.codeVerif = codeVerif.replace("\n", "");
	}
	
	/**
	 * verification si les donnees ne sont par erronnees
	 * @return erreur si il y a, null sinon
	 */
	public String verify() {
		// TODO Auto-generated method stub
		if (!this.verifyDate()) {
			return "date :"+GestionErreur.PAIEMENT_DATE_PASSE;
		} 
		return null;
	}
	
	private boolean verifyDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dateExpi = "31-"+mois+"-"+annee;
		try {
			Date date = formatter.parse(dateExpi);
			Date today = new Date();
			if (date.getTime()-today.getTime() < 0) {
				return false;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean isInteger(String s) {
	    return isInteger(s,10);
	}

	public boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}
	
}
