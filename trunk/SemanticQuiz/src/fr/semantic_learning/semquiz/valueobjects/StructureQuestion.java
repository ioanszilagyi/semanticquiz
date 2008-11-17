/**
 * 
 */
package fr.semantic_learning.semquiz.valueobjects;

/**
 * @author radu
 *
 * This class is used to structure the parameters received from the interface
 */
public class StructureQuestion {
	private String id;
	private String subsection;
	private String type;
	private String evaluationTime;
	private String evaluationType;
	private String[] competence;
	/////////////////////
	private String units; // unitatea din care face parte intrebarea 
	////////////////////
	private String author;
	private String data;
	private String language;
	private String text;
	private String picture;
	private String video;
	private String sound;
	private StructureQuestionVariants[] variants;
	/**
	 * @return the subsection
	 */
	public String getSubsection() {
		return subsection;
	}
	/**
	 * @param subsection the subsection to set
	 */
	public void setSubsection(String subsection) {
		this.subsection = subsection;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the itemType to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the evaluationTime
	 */
	public String getEvaluationTime() {
		return evaluationTime;
	}
	/**
	 * @param evaluationTime the evaluationTime to set
	 */
	public void setEvaluationTime(String evaluationTime) {
		this.evaluationTime = evaluationTime;
	}
	/**
	 * @return the evaluationType
	 */
	public String getEvaluationType() {
		return evaluationType;
	}
	/**
	 * @param evaluationType the evaluationType to set
	 */
	public void setEvaluationType(String evaluationType) {
		this.evaluationType = evaluationType;
	}
	
	
	/**
	 * @return the competence
	 */
	public String[] getCompetence() {
		return competence;
	}
	/**
	 * @param competence the competence to set
	 */
	public void setCompetence(String[] competence) {
		this.competence = competence;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}
	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the picture
	 */
	public String getPicture() {
		return picture;
	}
	/**
	 * @param picture the picture to set
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}
	/**
	 * @return the video
	 */
	public String getVideo() {
		return video;
	}
	/**
	 * @param video the video to set
	 */
	public void setVideo(String video) {
		this.video = video;
	}
	/**
	 * @return the sound
	 */
	public String getSound() {
		return sound;
	}
	/**
	 * @param sound the sound to set
	 */
	public void setSound(String sound) {
		this.sound = sound;
	}
	/**
	 * @return the variants
	 */
	public StructureQuestionVariants[] getVariants() {
		return variants;
	}
	/**
	 * @param variants the variants to set
	 */
	public void setVariants(StructureQuestionVariants[] variants) {
		this.variants = variants;
	}
	/**
	 * @return the unit
	 */
	public String getUnits() {
		return units;
	}
	/**
	 * @param unit the unit to set
	 */
	public void setUnits(String units) {
		this.units = units;
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
	