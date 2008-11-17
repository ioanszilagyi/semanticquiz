/**
 * 
 */
package fr.semantic_learning.semquiz.valueobjects;

/**
 * @author radu
 *
 * This class is used to structure the question variants
 */
public class StructureQuestionAnswer {
	private String id;
	private String idQuestion;
	private Boolean isCorect;
	private String[] idVariantsAnswer; //bag
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
	 * @return the idQuestion
	 */
	public String getIdQuestion() {
		return idQuestion;
	}
	/**
	 * @param idQuestion the idQuestion to set
	 */
	public void setIdQuestion(String idQuestion) {
		this.idQuestion = idQuestion;
	}
	/**
	 * @return the isCorect
	 */
	public Boolean getIsCorect() {
		return isCorect;
	}
	/**
	 * @param isCorect the isCorect to set
	 */
	public void setIsCorect(Boolean isCorect) {
		this.isCorect = isCorect;
	}
	/**
	 * @return the idVariantsAnswer
	 */
	public String[] getIdVariantsAnswer() {
		return idVariantsAnswer;
	}
	/**
	 * @param idVariantsAnswer the idVariantsAnswer to set
	 */
	public void setIdVariantsAnswer(String[] idVariantsAnswer) {
		this.idVariantsAnswer = idVariantsAnswer;
	}
	

}
