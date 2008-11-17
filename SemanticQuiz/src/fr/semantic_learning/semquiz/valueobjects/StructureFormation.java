/**
 * 
 */
package fr.semantic_learning.semquiz.valueobjects;

/**
 * @author Emma
 *
 */
public class StructureFormation {
	private String id; //se genereaza automat de functia de creere RDF formatii
	
	private String title;
	private String num_ani; //numarul de ani pentru formatiunea respectiva;
	private String yearTitle; 
	/**
	 * @return the yearTitle
	 */
	public String getYearTitle() {
		return yearTitle;
	}
	/**
	 * @param yearTitle the yearTitle to set
	 */
	public void setYearTitle(String yearTitle) {
		this.yearTitle = yearTitle;
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
	 * @return the num_ani
	 */
	public String getNum_ani() {
		return num_ani;
	}
	/**
	 * @param num_ani the num_ani to set
	 */
	public void setNum_ani(String num_ani) {
		this.num_ani = num_ani;
	}
	
		
}
