// ActionScript file
import fr.semantic_learning.events.EventCommandFlex;
import fr.semantic_learning.semquiz.commands.AllCommands;
import fr.semantic_learning.semquiz.forms_actionscript.student.BuildDynamicallyComponents;
import fr.semantic_learning.semquiz.valueobjects.StructureQuestion;

import mx.controls.Alert;

private var theQuestionIds:Array;
private var currentDisplayedQuestion:int;

private function init():void {
	this.addEventListener(AllCommands.STUDENT_START_QUIZ, manageEvent);
	//this.addEventListener(AllCommands.STUDENT_NEXT_QUESTION, manageEvent);
	this.addEventListener(AllCommands.SELECT_QUESTIONS, manageEvent);
}

public function manageEvent(event:EventCommandFlex):void {
	try {
		// TODO: here will be check the type of the question and will be desplayed 
		if (event.command == AllCommands.STUDENT_START_QUIZ) {	
			theQuestionIds = new Array();
			theQuestionIds = event.args[0] as Array;
		
			//Alert.show(theQuestionIds.toString());
		
			var theQuestions:StructureQuestion = new StructureQuestion();
			theQuestions = event.args[1] as StructureQuestion;
			currentDisplayedQuestion = 1;
		
			//Alert.show(theQuestionIds[1].toString());
			

			if (theQuestionIds.length >= currentDisplayedQuestion) {
				//Alert.show("id: "+ theQuestionIds[currentDisplayedQuestion]);
				var theQuestion:BuildDynamicallyComponents = new BuildDynamicallyComponents(this, theQuestions, false, theQuestionIds[currentDisplayedQuestion]);
			} else {
				//Alert.show("last one: currentDisplayedQuestion: "+ currentDisplayedQuestion);
				theQuestion = new BuildDynamicallyComponents(this, theQuestions, false, null);
			}
		} else 
		if (event.command == AllCommands.SELECT_QUESTIONS) {	
			// TODO: Save the responses...
			// remove all unsed stuff
			this.removeAllChildren();
			
			currentDisplayedQuestion++;
			theQuestions = event.args[0];
			
			if (theQuestionIds.length >= currentDisplayedQuestion) {
				theQuestion = new BuildDynamicallyComponents(this, theQuestions, false, theQuestionIds[currentDisplayedQuestion]);
			} else {
				theQuestion = new BuildDynamicallyComponents(this, theQuestions, false, null);
			}
		}
	} catch (e:Error) {
		Alert.show(e.message);
	} 
}
	

