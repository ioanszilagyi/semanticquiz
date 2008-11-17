/**
 * 
 */
package fr.semantic_learning.semquiz.valueobjects;

/**
 * @author Emma
 *
 */
public class StructureVariantsAnswer {
	private String id;
	private String idVariant;
	private Boolean isCorect;
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
	 * @return the idVariant
	 */
	public String getIdVariant() {
		return idVariant;
	}
	/**
	 * @param idVariant the idVariant to set
	 */
	public void setIdVariant(String idVariant) {
		this.idVariant = idVariant;
	}
	/**
	 * @return the isCorrect
	 */
	public Boolean getIsCorect() {
		return isCorect;
	}
	/**
	 * @param isCorrect the isCorrect to set
	 */
	public void setIsCorect(Boolean isCorect) {
		this.isCorect = isCorect;
	}
	
}
