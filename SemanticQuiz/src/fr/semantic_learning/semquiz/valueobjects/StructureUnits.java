package fr.semantic_learning.semquiz.valueobjects;

public class StructureUnits {
	private String id; //autogen
	private String codUnit;
	
	private String title;
	private String unitType; //unitatea poate fi de tip facultativ, obligatoriu sau optional
	private String idSection; // id-ul CNU-ului din care face parte unitatea
	private String credits; //creditele asociate unitatii
	private String idUser; // referinta spre user-ul prof ce e responsabil de unitate
	private String learningType; //poate avea una din valorile: attendence, online, attendence and online
	private String idMention; // referinta spre RDF mentions 
	private String[] idSpecialization; // bag cu referinta la specializari
	private String[] idParcurs; // bag cu referinta la parcursuri
	private String idSemester; // care e semestrul in care se face unitatea
	private String idYear; //anul in fctie de formatiune
	private String[] idCompetence; //care sunt competentele pe care trebuie sa le dobandeasca stud
	private String[] idPrerequists; // care sunt cerintele minime pt unitate (de fapt tot competente)
	private String objectivs; // un text ce descrie obiectivele unitatii
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the codUnit
	 */
	public String getCodUnit() {
		return codUnit;
	}
	/**
	 * @param codUnit the codUnit to set
	 */
	public void setCodUnit(String codUnit) {
		this.codUnit = codUnit;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the unitType
	 */
	public String getUnitType() {
		return unitType;
	}
	/**
	 * @param unitType the unitType to set
	 */
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	/**
	 * @return the idSection
	 */
	public String getIdSection() {
		return idSection;
	}
	/**
	 * @param idSection the idSection to set
	 */
	public void setIdSection(String idSection) {
		this.idSection = idSection;
	}
	/**
	 * @return the credits
	 */
	public String getCredits() {
		return credits;
	}
	/**
	 * @param credits the credits to set
	 */
	public void setCredits(String credits) {
		this.credits = credits;
	}
	/**
	 * @return the idUser
	 */
	public String getIdUser() {
		return idUser;
	}
	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	/**
	 * @return the learningType
	 */
	public String getLearningType() {
		return learningType;
	}
	/**
	 * @param learningType the learningType to set
	 */
	public void setLearningType(String learningType) {
		this.learningType = learningType;
	}
	/**
	 * @return the idMention
	 */
	public String getIdMention() {
		return idMention;
	}
	/**
	 * @param idMention the idMention to set
	 */
	public void setIdMention(String idMention) {
		this.idMention = idMention;
	}
	/**
	 * @return the idSpecialization
	 */
	public String[] getIdSpecialization() {
		return idSpecialization;
	}
	/**
	 * @param idSpecialization the idSpecialization to set
	 */
	public void setIdSpecialization(String[] idSpecialization) {
		this.idSpecialization = idSpecialization;
	}
	/**
	 * @return the isParcurs
	 */
	public String[] getIdParcurs() {
		return idParcurs;
	}
	/**
	 * @param isParcurs the isParcurs to set
	 */
	public void setIdParcurs(String[] idParcurs) {
		this.idParcurs = idParcurs;
	}
	
	/**
	 * @return the idSemester
	 */
	public String getIdSemester() {
		return idSemester;
	}
	/**
	 * @param idSemester the idSemester to set
	 */
	public void setIdSemester(String idSemester) {
		this.idSemester = idSemester;
	}
	/**
	 * @return the idCompetence
	 */
	public String[] getIdCompetence() {
		return idCompetence;
	}
	/**
	 * @param idCompetence the idCompetence to set
	 */
	public void setIdCompetence(String[] idCompetence) {
		this.idCompetence = idCompetence;
	}
	/**
	 * @return the idPrerequists
	 */
	public String[] getIdPrerequists() {
		return idPrerequists;
	}
	/**
	 * @param idPrerequists the idPrerequists to set
	 */
	public void setIdPrerequists(String[] idPrerequists) {
		this.idPrerequists = idPrerequists;
	}
	/**
	 * @return the objectivs
	 */
	public String getObjectivs() {
		return objectivs;
	}
	/**
	 * @param objectivs the objectivs to set
	 */
	public void setObjectivs(String objectivs) {
		this.objectivs = objectivs;
	}
	/**
	 * @return the idYear
	 */
	public String getIdYear() {
		return idYear;
	}
	/**
	 * @param idYear the idYear to set
	 */
	public void setIdYear(String idYear) {
		this.idYear = idYear;
	}
	
}
