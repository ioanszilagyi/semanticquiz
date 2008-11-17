/**
 * 
 */
package fr.semantic_learning.semquiz.valueobjects;

/**
 * @author Emma
 *
 */
public class StructureDisciplineGroup {
	private String id; // generat automat de fctia de creere automat de domeniu discipline
	private String title;
	private StructureDisciplineDomain discipline_domain;
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
	 * @return the discipline_domain
	 */
	public StructureDisciplineDomain getDiscipline_domain() {
		return discipline_domain;
	}
	/**
	 * @param discipline_domain the discipline_domain to set
	 */
	public void setDiscipline_domain(StructureDisciplineDomain discipline_domain) {
		this.discipline_domain = discipline_domain;
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
