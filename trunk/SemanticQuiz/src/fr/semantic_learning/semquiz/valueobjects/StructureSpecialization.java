package fr.semantic_learning.semquiz.valueobjects;

public class StructureSpecialization {
	private String id; //automat se pune din fctia de creere RDF specializari 
	private String title;
	private String cod;
	private String idResp;
	private String idMention;
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
	 * @return the idMention
	 */
	public String getIdMention() {
		return idMention;
	}
	/**
	 * @param idMention the idMention to set
	 */
	public void setIdMention(String idMention) {
		this.idMention = idMention;
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
