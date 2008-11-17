package fr.semantic_learning.semquiz.valueobjects;
/**
 * @author Emma
 *
 */
public class StructureSubsection {
	private String id; // generat automat de fctia de creere automat de domeniu discipline
	private String title;
	private String partOf;
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
	 * @return the partOf
	 */
	public String getPartOf() {
		return partOf;
	}
	/**
	 * @param partOf the partOf to set
	 */
	public void setPartOf(String partOf) {
		this.partOf = partOf;
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
