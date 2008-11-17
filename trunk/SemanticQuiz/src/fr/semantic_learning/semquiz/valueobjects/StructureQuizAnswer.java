package fr.semantic_learning.semquiz.valueobjects;
/**
 * @author Emma
 *
 */
public class StructureQuizAnswer {
	private String id;
	private String idquiz;
	private String[] idQuestionAnswer;
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
	 * @return the idquiz
	 */
	public String getIdquiz() {
		return idquiz;
	}
	/**
	 * @param idquiz the idquiz to set
	 */
	public void setIdquiz(String idquiz) {
		this.idquiz = idquiz;
	}
	/**
	 * @return the idQuestionAnswer
	 */
	public String[] getIdQuestionAnswer() {
		return idQuestionAnswer;
	}
	/**
	 * @param idQuestionAnswer the idQuestionAnswer to set
	 */
	public void setIdQuestionAnswer(String[] idQuestionAnswer) {
		this.idQuestionAnswer = idQuestionAnswer;
	}
	
}