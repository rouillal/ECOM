package fr.ecombio.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;

import com.itextpdf.text.DocumentException;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import fr.ecombio.data.PanierRepository;

import javax.activation.*;

/**
 * <p>
 * Gestion de l'envoie d'un mail de confiramtion d'achat
 * en y associant une facture
 * </p>
 *
 * @see PanierRepository
 * @see CreateFacture
 */
public class SendEmail {

	/**
	 * repository qui gere les requetes en base
	 * @see PanierRepository
	 */
	PanierRepository PanierRepository;

	/**
	 * cstor.
	 * @param PanierRepository repository qui gere les requete en base
	 */
	public SendEmail(PanierRepository PanierRepository) {
		this.PanierRepository = PanierRepository;
	}

	/**
	 * envoie un mail de confirmation
	 * @param infos informations de la commande
	 */
	public synchronized void send(ValidationCommande infos) {

		// Recipient's email ID needs to be mentioned.
		String to = "biotobealive@gmail.com";
		if (infos.getCommandInfo().getMail() != null) {
			to = infos.getCommandInfo().getMail() ;
		}

		// Sender's email ID needs to be mentioned
		final String from = "biotobealive@gmail.com";
		// Sender's email ID needs to be mentioned
		final String fromPsw = "ecom1612";

		// Assuming you are sending email from localhost
		String host = "localhost";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, fromPsw);
			}
		});

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("Confirmation de votre commande");
			String montant = "test montant";
			String prenom = "Prenom";
			String nom ="Nom"; 
			String date = "date";
			String heure = "heure";
			String livraison = "e";
			String adresse = "adresse";
			String cp = "cp";
			String ville = "ville";
			String produits = "liste de produits";
			Panier panier = null;
			if (infos != null) {
				panier = PanierRepository.findById(infos.getIdPanier());
				produits = panier.toStringHTML();
				montant = Double.toString(panier.getTotal());
				prenom = infos.getCommandInfo().getPrenom();
				nom =infos.getCommandInfo().getNom(); 
				date = infos.getCommandInfo().getDate();
				heure = this.getHeure(infos.getCommandInfo().getHeure());
				livraison = infos.getCommandInfo().getLivDom();
				adresse = infos.getCommandInfo().getAdresse();
				cp = infos.getCommandInfo().getCp();
				ville = infos.getCommandInfo().getVille();
			}

			// Send the actual HTML message, as big as you like
			//message.setContent("<h1>This is actual message</h1>", "text/html");

			// Create the message part 

		    MimeBodyPart messageBodyPart = new MimeBodyPart();
		    messageBodyPart.setContent( this.getText(prenom, nom, montant, date, heure, livraison, adresse, cp, ville, produits), "text/html; charset=utf-8" );

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			if (infos != null) {
				// Part two is attachment
				messageBodyPart = new MimeBodyPart();
				File MonPdf = new File((new CreateFacture()).getFile(infos,panier));
				DataSource source = new FileDataSource(MonPdf);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(MonPdf.getName());
				multipart.addBodyPart(messageBodyPart);
			}

			// Send the complete message parts
			message.setContent(multipart, "text/html");

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
			try {
				System.out.println(message.getContent());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}catch (Exception mex) {
			mex.printStackTrace();
		} 
	}

	private String getHeure(String heure) {
		if (heure.equals("0")) {
			return "8:00 - 9:00h";
		} else if (heure.equals("1")) {
			return "9:00 - 10:00h";
		} else if (heure.equals("2")) {
			return "10:00 - 11:00h";
		} else if (heure.equals("3")) {
			return "11:00 - 12:00h";
		} else if (heure.equals("4")) {
			return "12:00 - 13:00h";
		} else if (heure.equals("5")) {
			return "13:00 - 14:00h";
		} else if (heure.equals("6")) {
			return "14:00 - 15:00h";
		} else if (heure.equals("7")) {
			return "15:00 - 16:00h";
		} else if (heure.equals("8")) {
			return "16:00 - 17:00h";
		} else if (heure.equals("9")) {
			return "17:00 - 18:00h";
		} else if (heure.equals("10")) {
			return "18:00 - 19:00h";
		} else {
			return "19:00 - 20:00h";
		}
	}

	private String getText(String prenom, String nom, String montant, String date, String heure, String livraison, String adresse, String cp, String ville, String produits) {
		String liv = "";
		if (livraison.equals("e")) {
			liv = "<span>venez retirer votre commande à : Bio To Be Alive<br></span>";
		} else {
			liv = "<span>votre commande vous sera livrée à:<br></span>";
		}

		return String.format("%s %s,<br><br> \nVotre commande d'un montant de %s euros a bien été prise en compte et nous vous en remercions.<br><br>\n<span style=\"font-weight:bold;\">Le %s entre %s</span>\n%s<br>%s <br>%s %s<br><br>%s\nL\'équipe Bio To Be Alive.", prenom, nom, montant, date, heure, liv, adresse, cp, ville,produits);	
	}
}