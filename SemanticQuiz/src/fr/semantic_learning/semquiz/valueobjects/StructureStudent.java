/**
 * 
 */
package fr.semantic_learning.semquiz.valueobjects;

/**
 * @author Emma
 *
 */
public class StructureStudent {
	private String idUser;
	private String[] idGroup;
	private String familyName;
	private String givenName;
	private String idMention;
	private String idSpec;
	private String idParcours;
	private String idYear;
	//private String[] idPendingQuizzes; //bag cu quizuri ce trebuie facute
	//private String[] idAnsweredQuizzes; //bag cu quizuri la care a raspuns deja
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
	 * @return the idGroup
	 */
	public String[] getIdGroup() {
		return idGroup;
	}
	/**
	 * @param idGroup the idGroup to set
	 */
	public void setIdGroup(String[] idGroup) {
		this.idGroup = idGroup;
	}
	/**
	 * @return the familyName
	 */
	public String getFamilyName() {
		return familyName;
	}
	/**
	 * @param familyName the familyName to set
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	/**
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}
	/**
	 * @param givenName the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
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
	 * @return the idSpec
	 */
	public String getIdSpec() {
		return idSpec;
	}
	/**
	 * @param idSpec the idSpec to set
	 */
	public void setIdSpec(String idSpec) {
		this.idSpec = idSpec;
	}
	/**
	 * @return the idParcours
	 */
	public String getIdParcours() {
		return idParcours;
	}
	/**
	 * @param idParcours the idParcours to set
	 */
	public void setIdParcours(String idParcours) {
		this.idParcours = idParcours;
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
