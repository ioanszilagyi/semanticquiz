package fr.semantic_learning.semquiz.commands;

public enum RDFModels {
	UNKNOWN_MODEL("UNKNOWN_MODEL"),
	variantModel("variantModel"),
	questionModel("questionModel"),
	userModel("userModel"),
	competenceModel("competenceModel"),
	quizModel("quizModel"),
	subsectionModel("subsectionModel"),
	disciplineDomainModel("disciplineDomainModel"),
	disciplineGroupModel("disciplineGroupModel"),
	sectionModel("sectionModel"),
	formationModel("formationModel"),
	mentionModel("mentionModel"),
	parcoursModel("parcoursModel"),
	specializationModel("specializationModel"),
	unitsModel("unitsModel"),
	groupModel("groupModel"),
	pendingQuizzesModel("pendingQuizzesModel"),
	variantsAnswerModel("variantsAnswerModel"),
	quizAnswerModel("quizAnswerModel"),
	questionAnswerModel("questionAnswerModel"),
	userRightsModel("userRightsModel"),
	studentModel("studentModel"),
	profModel("profModel"),
	evalTypeModel("evalTypeModel"),
	yearModel("yearModel"),
	semesterModel("semesterModel"),
	interfacesModel("interfacesModel");
	

	
	private final String command;
	
	RDFModels(String command){
		this.command = command;
	}

    public static RDFModels toCommands(String command)
    {
        try {
            return valueOf(command);
        } 
        catch (Exception ex) {
            return UNKNOWN_MODEL;
        }
    }  
	
	/**
	 * @return the command
	 */
	public String getCommand() {
		return command;
	}	
	
	
}
