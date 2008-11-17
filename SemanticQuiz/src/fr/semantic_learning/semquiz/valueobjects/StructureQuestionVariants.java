/**
 * 
 */
package fr.semantic_learning.semquiz.valueobjects;

/**
 * @author Emma
 *
 */
public class StructureQuestionVariants {
	private String id;
	private String text;
	private Boolean validation;
	private String sound;
	private String picture;
	private String video;
	
	/**
	 * @return the test
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param test the test to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the validation
	 */
	public Boolean getValidation() {
		return validation;
	}
	/**
	 * @param validation the validation to set
	 */
	public void setValidation(Boolean validation) {
		this.validation = validation;
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
