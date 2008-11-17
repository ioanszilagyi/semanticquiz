/**
 * 
 */
package fr.semantic_learning.semquiz.valueobjects;

/**
 * @author Emma
 *
 */
public class StructureProf {
	private String idUser;
	private String[] idSubsections;//bag
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
	 * @return the idSubsections
	 */
	public String[] getIdSubsections() {
		return idSubsections;
	}
	/**
	 * @param idSubsections the idSubsections to set
	 */
	public void setIdSubsections(String[] idSubsections) {
		this.idSubsections = idSubsections;
	}
	
	
}
