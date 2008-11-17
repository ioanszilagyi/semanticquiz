/**
 * 
 */
package fr.semantic_learning.semquiz.valueobjects;

/**
 * @author Emma
 *
 */
public class StructureInterfaceRights {
	private String id;
	private String idInterface;
	private Boolean isAllowed;
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the idInterface
	 */
	public String getIdInterface() {
		return idInterface;
	}
	/**
	 * @param idInterface the idInterface to set
	 */
	public void setIdInterface(String idInterface) {
		this.idInterface = idInterface;
	}
	/**
	 * @return the isAllowed
	 */
	public Boolean getIsAllowed() {
		return isAllowed;
	}
	/**
	 * @param isAllowed the isAllowed to set
	 */
	public void setIsAllowed(Boolean isAllowed) {
		this.isAllowed = isAllowed;
	}
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
	
}
