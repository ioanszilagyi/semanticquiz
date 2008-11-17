package fr.semantic_learning.semquiz.valueobjects;

public class StructureMention {
	private String id; // se pune automat din fctia de creere RDF mentiuni
	//private String cod; //codul mentiunii.. se introduce din interfata de creere mentiuni
	private String title; //codul mentiunii.. se introduce din interfata de creere mentiuni
	private String idResp; //responsabilul pt mentiunea respectiva (se primeste un id user)
	private String idFormation;
	
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
	 * @return the idResp
	 */
	public String getIdResp() {
		return idResp;
	}
	/**
	 * @param idResp the idResp to set
	 */
	public void setIdResp(String idResp) {
		this.idResp = idResp;
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
