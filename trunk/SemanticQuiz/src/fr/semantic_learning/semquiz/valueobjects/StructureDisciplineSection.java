package fr.semantic_learning.semquiz.valueobjects;
/**
 * @author Emma
 *
 */
public class StructureDisciplineSection {
	private String id; // generat automat de fctia de creere automat de domeniu discipline
	private String title;
	private String cod;
	private StructureDisciplineGroup discipline_group;
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
	 * @return the cod
	 */
	public String getCod() {
		return cod;
	}
	/**
	 * @param cod the cod to set
	 */
	public void setCod(String cod) {
		this.cod = cod;
	}
	/**
	 * @return the discipline_group
	 */
	public StructureDisciplineGroup getDiscipline_group() {
		return discipline_group;
	}
	/**
	 * @param discipline_group the discipline_group to set
	 */
	public void setDiscipline_group(StructureDisciplineGroup discipline_group) {
		this.discipline_group = discipline_group;
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
