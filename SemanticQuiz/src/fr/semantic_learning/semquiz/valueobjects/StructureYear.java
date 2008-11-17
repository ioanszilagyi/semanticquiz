/**
 * 
 */
package fr.semantic_learning.semquiz.valueobjects;

/**
 * @author Emma
 *
 */
public class StructureYear {
	private String id;
	private String title;
	private String idFormation;
	private String[] idSemesters;
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
	 * @return the idFormation
	 */
	public String getIdFormation() {
		return idFormation;
	}
	/**
	 * @param idFormation the idFormation to set
	 */
	public void setIdFormation(String idFormation) {
		this.idFormation = idFormation;
	}
	/**
	 * @return the idSemesters
	 */
	public String[] getIdSemesters() {
		return idSemesters;
	}
	/**
	 * @param idSemesters the idSemesters to set
	 */
	public void setIdSemesters(String[] idSemesters) {
		this.idSemesters = idSemesters;
	}
}
