/**
 * 
 */
package fr.semantic_learning.semquiz.valueobjects;

/**
 * @author Emma
 *
 */
public class StructurePendingQuizzes {
	private String[] idQuiz;
	private String idUser;
//	private String ActivateDate;
//	private String ExpireDate;
	
	/**
	 * @return the idUser
	 */
	public String getIdUser() {
		return idUser;
	}
	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	/**
	 * @return the idQuiz
	 */
	public String[] getIdQuiz() {
		return idQuiz;
	}
	/**
	 * @param idQuiz the idQuiz to set
	 */
	public void setIdQuiz(String[] idQuiz) {
		this.idQuiz = idQuiz;
	}
	
	
}
