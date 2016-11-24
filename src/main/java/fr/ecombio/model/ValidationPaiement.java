package fr.ecombio.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.core.Variant;

public class ValidationPaiement {
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
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num.replace("\n", "");
	}
	public String getMois() {
		return mois;
	}
	public void setMois(String mois) {
		this.mois = mois.replace("\n", "");
	}
	public String getAnnee() {
		return annee;
	}
	public void setAnnee(String annee) {
		this.annee = annee.replace("\n", "");
	}
	public String getCodeVerif() {
		return codeVerif;
	}
	public void setCodeVerif(String codeVerif) {
		this.codeVerif = codeVerif.replace("\n", "");
	}
	
	public String verify() {
		// TODO Auto-generated method stub
		if (num.length() != 16) {
			return "num<"+num+">"+GestionErreur.PAIEMENT_NUM_LENGTH;
			
		} else if(!this.isInteger(num)) {
			return "num<"+num+">"+GestionErreur.PAIEMENT_NUM_CHAR;
			
		} else if (!this.verifyDate()) {
			return "date :"+GestionErreur.PAIEMENT_DATE_PASSE;
			
		} else if (codeVerif.length() != 3) {
			return "code<"+codeVerif+">"+GestionErreur.PAIEMENT_CODE_LENGTH;
			
		} else if(!this.isInteger(codeVerif)) {
			return "code<"+codeVerif+">"+GestionErreur.PAIEMENT_CODE_CHAR;
			
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
