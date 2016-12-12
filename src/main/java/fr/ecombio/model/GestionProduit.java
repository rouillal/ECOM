package fr.ecombio.model;

public class GestionProduit {
	private Long id;
	private GestionCategorie categorie;
	private String name;
	private String variete;
	private String unite;
	private int quantite;
	private int stock;
	private float prix;
	private String filename;
	private String provenance;
	/**
	 * @return the dateCueillette
	 */
	public String getDateCueillette() {
		return dateCueillette;
	}
	/**
	 * @param dateCueillette the dateCueillette to set
	 */
	public void setDateCueillette(String dateCueillette) {
		this.dateCueillette = dateCueillette;
	}
	private String dateCueillette;
	private int dureeConservation;
	private int calories;
	private int glucides;
	private int fibres;
	private int proteines;
	private int quotite;
	private int prixTotal;
	private String url;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the categorie
	 */
	public GestionCategorie getCategorie() {
		return categorie;
	}
	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(GestionCategorie categorie) {
		this.categorie = categorie;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the variete
	 */
	public String getVariete() {
		return variete;
	}
	/**
	 * @param variete the variete to set
	 */
	public void setVariete(String variete) {
		this.variete = variete;
	}
	/**
	 * @return the unite
	 */
	public String getUnite() {
		return unite;
	}
	/**
	 * @param unite the unite to set
	 */
	public void setUnite(String unite) {
		this.unite = unite;
	}
	/**
	 * @return the quantite
	 */
	public int getQuantite() {
		return quantite;
	}
	/**
	 * @param quantite the quantite to set
	 */
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}
	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}
	/**
	 * @return the prix
	 */
	public float getPrix() {
		return prix;
	}
	/**
	 * @param prix the prix to set
	 */
	public void setPrix(float prix) {
		this.prix = prix;
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * @return the provenance
	 */
	public String getProvenance() {
		return provenance;
	}
	/**
	 * @param provenance the provenance to set
	 */
	public void setProvenance(String provenance) {
		this.provenance = provenance;
	}
	/**
	 * @return the dureeConservation
	 */
	public int getDureeConservation() {
		return dureeConservation;
	}
	/**
	 * @param dureeConservation the dureeConservation to set
	 */
	public void setDureeConservation(int dureeConservation) {
		this.dureeConservation = dureeConservation;
	}
	/**
	 * @return the calories
	 */
	public int getCalories() {
		return calories;
	}
	/**
	 * @param calories the calories to set
	 */
	public void setCalories(int calories) {
		this.calories = calories;
	}
	/**
	 * @return the glucides
	 */
	public int getGlucides() {
		return glucides;
	}
	/**
	 * @param glucides the glucides to set
	 */
	public void setGlucides(int glucides) {
		this.glucides = glucides;
	}
	/**
	 * @return the fibres
	 */
	public int getFibres() {
		return fibres;
	}
	/**
	 * @param fibres the fibres to set
	 */
	public void setFibres(int fibres) {
		this.fibres = fibres;
	}
	/**
	 * @return the proteines
	 */
	public int getProteines() {
		return proteines;
	}
	/**
	 * @param proteines the proteines to set
	 */
	public void setProteines(int proteines) {
		this.proteines = proteines;
	}
	/**
	 * @return the quotite
	 */
	public int getQuotite() {
		return quotite;
	}
	/**
	 * @param quotite the quotite to set
	 */
	public void setQuotite(int quotite) {
		this.quotite = quotite;
	}
	/**
	 * @return the prixTotal
	 */
	public int getPrixTotal() {
		return prixTotal;
	}
	/**
	 * @param prixTotal the prixTotal to set
	 */
	public void setPrixTotal(int prixTotal) {
		this.prixTotal = prixTotal;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
