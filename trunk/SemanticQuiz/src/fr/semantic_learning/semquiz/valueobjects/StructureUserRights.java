/**
 * 
 */
package fr.semantic_learning.semquiz.valueobjects;

/**
 * @author Emma
 *
 */
public class StructureUserRights {
	private String idUser;
	private StructureInterfaceRights[] rights;
	
	/**
	 * @return the rights
	 */
	public StructureInterfaceRights[] getRights() {
		return rights;
	}
	/**
	 * @param rights the rights to set
	 */
	public void setRights(StructureInterfaceRights[] rights) {
		this.rights = rights;
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
	
}
