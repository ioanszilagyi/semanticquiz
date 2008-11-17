import fr.semantic_learning.events.EventCommandFlex;
import fr.semantic_learning.semquiz.CentralComm;
import fr.semantic_learning.semquiz.commands.AllCommands;
import fr.semantic_learning.semquiz.valueobjects.StructureQuestion;
import fr.semantic_learning.semquiz.valueobjects.StructureQuestionVariants;
import fr.semantic_learning.semquiz.valueobjects.StructureQuiz;

import mx.controls.Alert;
import mx.core.Application;

private var allQuestionIds:Array = new Array(); 
		
private function init():void {
	CentralComm.app = Application.application as main;		
	this.addEventListener(AllCommands.STUDENT_START_QUIZ, manageEvent);
	//createSomeTemporaryQuizes();									
	this.addEventListener(AllCommands.SELECT_PENDING_QUIZZES, setPendingQuizzes);
	this.addEventListener(AllCommands.SELECT_QUIZ_QUESTIONS, setQuestions);
	this.addEventListener(AllCommands.SELECT_QUESTIONS, setRunQuestion);
}	

public function manageEvent(event:EventCommandFlex):void {
	Alert.show("in sd here");
}

// go to StudentRunningQuiz inteface 
private function RunSelected():void {
	if (pendingQuizList.selectedItems.length > 0) {
		var quizID:StructureQuiz = new StructureQuiz();
		quizID.id = pendingQuizList.selectedItem.id;

		// send 
		var	event:EventCommandFlex = new EventCommandFlex(AllCommands.SELECT_QUIZ_QUESTIONS, quizID.id);
		CentralComm.app.dispatchEvent(event);
	} else {
		Alert.show("Please select the quiz!");
	}
    /*********************************/
	/* MOVE THIS TO RETURN_QUESTIONS */ 
	// the selected Quiz to run
/*	var theQuiz:StructureQuiz = GetQuiz(pendingQuizList.selectedItem.toString());
				
	CentralComm.app.homeBtnBar.selectedIndex = 3;				
				
	// send event to start the quiz
	CentralComm.app.dispatchEvent(new EventCommandFlex(AllCommands.STUDENT_START_QUIZ, theQuiz));
	*/
}

private function setQuizLabel(item:StructureQuiz):String {
	return item.title;
}

private function setPendingQuizzes(event:EventCommandFlex):void {
	pendingQuizList.dataProvider = event.args as Array;
}
			
private function setQuestions(event:EventCommandFlex):void {
	try {
		// set all running question
		allQuestionIds = event.args as Array;
		//allQuestionIds = new Array("qs")
	
		// send to   
		CentralComm.app.dispatchEvent(new EventCommandFlex(AllCommands.SELECT_QUESTIONS, allQuestionIds[0]));
	} catch (e:Error) {
		Alert.show(e.message);
	}
}

private function setRunQuestion(event:EventCommandFlex):void {
	try {
		// do this just once
		//Alert.show(event.args.toString());		
		//Alert.show(((event.args[0] as StructureQuestion).variants[0] as StructureQuestionVariants).text.toString());
		//Alert.show((event.args[0] as StructureQuestion).text.toString());
		
		var nT:StructureQuestion = new StructureQuestion();
		nT = event.args[0] as StructureQuestion;
		
		//var nE:Array = new Array(allQuestionIds, GetQuiz(""));
		var nE:Array = new Array(allQuestionIds, nT);

		CentralComm.app.homeBtnBar.selectedIndex = 3;				
				
		// send event to start the quiz
		CentralComm.app.dispatchEvent(new EventCommandFlex(AllCommands.STUDENT_START_QUIZ, nE));
		
 	} catch (e:Error) {
 		Alert.show(e.message);
	 }
}
			
/* This will be used to get the quiz structure */
private function GetQuiz(quizId:String):StructureQuestion {
	var theVariant1:StructureQuestionVariants = new StructureQuestionVariants();
	theVariant1.picture = "";
	theVariant1.sound = "";
	theVariant1.video = "resources/How_to_Make_a_Cheap_Multitouch_Pad.flv";
	theVariant1.text = '<b>This</b> is first <i>MC</i> <font size="14" color="#FF0000">variant</font>';
	theVariant1.validation = false;
				
	var theVariant2:StructureQuestionVariants = new StructureQuestionVariants();
	theVariant2.picture = "resources/Winter.jpg";
	theVariant2.sound = "";
	theVariant2.video = "resources/Crazy_japanese_train_loaders.flv";
	theVariant2.text = '<b>This</b> is second <i>MC</i> <font size="14" color="#0000FF">variant</font>';
	theVariant2.validation = true;
			
	var theVariant3:StructureQuestionVariants = new StructureQuestionVariants();
	theVariant3.picture = "";
	theVariant3.sound = "";
	theVariant3.video = "resources/Enya_Only_time.flv";
	theVariant3.text = '<b>This</b> is 3 <i>MC</i> <font size="14" color="#FFFF0">variant</font>';
	theVariant3.validation = false;
				
	var theVariant4:StructureQuestionVariants = new StructureQuestionVariants();
	theVariant4.picture = "";
	theVariant4.sound = "";
	theVariant4.video = "resources/The_Sun_at_19.5nm.flv";
	theVariant4.text = '<b>This</b> is 4 <i>MC</i> <font size="14" color="#FF7477">variant</font>';
	theVariant4.validation = true;
		
				
	var theQuestion1:StructureQuestion = new StructureQuestion();
	theQuestion1.author = "Author X";
	//theQuestion1.competence = "Competenta"; // un array ??
	theQuestion1.data = "20.04.2008";
	theQuestion1.evaluationTime = "32";
	theQuestion1.evaluationType = "examen";
	theQuestion1.id = "Quiz1";
	theQuestion1.language = "en";
	theQuestion1.picture = "";
	theQuestion1.sound = "resources/Above All.mp3";
	theQuestion1.subsection = "Math";
	theQuestion1.text = '<b>This</b> is the text of the <i>MC</i> <font size="46" color="#00FF00">question</font>';
	theQuestion1.type = "Multiple Choice";
	theQuestion1.units = "";
	theQuestion1.variants = new Array(theVariant1, theVariant2, theVariant3, theVariant4);
	theQuestion1.video = "resources/16628_259_20070709_212753_128.flv";
			
	// -----------------------------
				
	var theVariant2_1:StructureQuestionVariants = new StructureQuestionVariants();
	theVariant2_1.picture = "";
	theVariant2_1.sound = "";
	theVariant2_1.video = "resources/How_to_Make_a_Cheap_Multitouch_Pad.flv";
	theVariant2_1.text = '<b>This</b> is first <i>MC</i> <font size="14" color="#FF0000">variant</font>';
	theVariant2_1.validation = true;
			
	var theVariant2_2:StructureQuestionVariants = new StructureQuestionVariants();
	theVariant2_2.picture = "";
	theVariant2_2.sound = "resources/Song Of Love.mp3";
	theVariant2_2.video = "resources/Crazy_japanese_train_loaders.flv";
	theVariant2_2.text = '<b>This</b> is second <i>MC</i> <font size="14" color="#0000FF">variant</font>';
	theVariant2_2.validation = true;
			
	var theQuestion2:StructureQuestion = new StructureQuestion();
	theQuestion2.author = "Author X";
	//theQuestion2.competence = "Competenta"; // un array ??
	theQuestion2.data = "20.04.2008";
	theQuestion2.evaluationTime = "32";
	theQuestion2.evaluationType = "examen";
	theQuestion2.id = "Quiz1";
	theQuestion2.language = "en";
	theQuestion2.picture = "resources/Sunset.jpg";
	theQuestion2.sound = "";
	theQuestion2.subsection = "Math";
	theQuestion2.text = '<font size="26" color="#002050"><b>This</b> is the text of the <i>Second MC</i> question</font>';
	theQuestion2.type = "Multiple Choice";
	theQuestion2.units = "";
	theQuestion2.variants = new Array(theVariant2_1, theVariant2_2);
	theQuestion2.video = "";
				
	var theQuiz:StructureQuestion = new StructureQuestion();
		
	/*theQuiz.author = "Author X";
	theQuiz.date = "21.04.2008";
	theQuiz.id = "QSDQKSDKAZMKQSDNCMKQSDOO";
	theQuiz.language = "en";
	theQuiz.questions = new Array(theQuestion1, theQuestion2);
	theQuiz.title = "Question Title";
		*/		
	return theQuestion1;
}		
			
/* This will be remover when event from java will be recieved */
private function createSomeTemporaryQuizes():void {
	var arrQuizes:Array = new Array('Quiz1', 'Quiz2', 'Quiz3', 'Quiz4', 'Quiz5');				
		
	pendingQuizList.dataProvider = arrQuizes;
}