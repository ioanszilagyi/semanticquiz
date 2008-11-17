package fr.semantic_learning.semquiz
{
	import fr.semantic_learning.events.ComMsgToJava;
	import fr.semantic_learning.events.EventCommandFlex;
	import fr.semantic_learning.semquiz.commands.AllCommands;
	import fr.semantic_learning.semquiz.valueobjects.StructureUser;
	
	import mx.controls.Alert;
	import mx.core.Application;
	import mx.messaging.Consumer;
	import mx.messaging.Producer;
	import mx.messaging.events.MessageEvent;
	import mx.messaging.events.MessageFaultEvent;

	public class CentralComm
	{
		//Get a reference to the Application object here
		public static var app:main = Application.application as main;
		public static var currentUser:StructureUser;
		private var producer:Producer;
		private var consumer:Consumer;
		
		public static var rightsUserProf:Boolean; 
		public static var rightsUserAdmin:Boolean;
		public static var rightsUserStudent:Boolean; 
		public static var rightsUserSecretar:Boolean;
			
		public function CentralComm()
		{
			init();
			
			//Add event listeners for events here
			app.addEventListener(AllCommands.LOGIN_REQUEST, sendMessage);
			app.addEventListener(AllCommands.LOGIN_RIGHTS, sendMessage);

			//new
			app.addEventListener(AllCommands.NEW_USER, sendMessage);
			app.addEventListener(AllCommands.NEW_USER_RIGHTS, sendMessage);
			app.addEventListener(AllCommands.NEW_DOMAIN, sendMessage);
			app.addEventListener(AllCommands.NEW_COMPETENCE, sendMessage);
			app.addEventListener(AllCommands.NEW_QUESTION, sendMessage);
			app.addEventListener(AllCommands.NEW_SUBSECTION, sendMessage);
			app.addEventListener(AllCommands.NEW_FORMATION, sendMessage);
			app.addEventListener(AllCommands.NEW_MENTION, sendMessage);
			app.addEventListener(AllCommands.NEW_QUIZ, sendMessage);
			app.addEventListener(AllCommands.NEW_SPECIALIZATION, sendMessage);
			app.addEventListener(AllCommands.NEW_PARCOURS, sendMessage);
			app.addEventListener(AllCommands.NEW_FILEUPTIP, sendMessageToFlex);
			app.addEventListener(AllCommands.NEW_UNIT, sendMessage);
			app.addEventListener(AllCommands.NEW_STUDENT_DETAILS, sendMessage);
			app.addEventListener(AllCommands.NEW_PENDING_Q, sendMessage);
			
			//select
			app.addEventListener(AllCommands.SELECT_USER, sendMessage);
			app.addEventListener(AllCommands.SELECT_SECTION, sendMessage);
			app.addEventListener(AllCommands.SELECT_FORMATION, sendMessage);
			app.addEventListener(AllCommands.SELECT_MENTION, sendMessage);
			app.addEventListener(AllCommands.SELECT_SPECIALIZATION, sendMessage);
			app.addEventListener(AllCommands.SELECT_SUBSECTION, sendMessage);
			app.addEventListener(AllCommands.SELECT_PARCOURS, sendMessage);
			app.addEventListener(AllCommands.SELECT_COMPETENCE, sendMessage);
			app.addEventListener(AllCommands.SELECT_RIGHTS, sendMessage);
			app.addEventListener(AllCommands.SELECT_EVALUATIONTYPE, sendMessage);
			app.addEventListener(AllCommands.SELECT_ALL_RIGHTS, sendMessage);
			app.addEventListener(AllCommands.SELECT_SEMESTER, sendMessage);
			app.addEventListener(AllCommands.SELECT_YEAR, sendMessage);
			app.addEventListener(AllCommands.SELECT_PENDING_QUIZZES, sendMessage);
			app.addEventListener(AllCommands.SELECT_QUESTIONS, sendMessage);
			app.addEventListener(AllCommands.SELECT_QUIZ_QUESTIONS, sendMessage);
			app.addEventListener(AllCommands.SELECT_UNIT, sendMessage);
			app.addEventListener(AllCommands.SELECT_ALL_QUIZ, sendMessage);			
			
			//get
			app.addEventListener(AllCommands.GET_DOMAINS, sendMessage);
			app.addEventListener(AllCommands.SUBSECTION_QUESTIONS, sendMessage);

			// This events are lisened from Create Question interfaces
			app.addEventListener(AllCommands.UPLOAD_COMPLETE, sendMessageToFlex);

			// This events are lisened from Student interfaces
			app.addEventListener(AllCommands.STUDENT_START_QUIZ, sendMessageToFlex);
			app.addEventListener(AllCommands.STUDENT_NEXT_QUESTION, sendMessageToFlex);
		}

		public function init():void{
			producer = new Producer();
			consumer = new Consumer();
								
			producer.destination ="CentralCommAdapter";
			consumer.destination ="CentralCommAdapter";
			
			producer.addEventListener(MessageFaultEvent.FAULT, serviceFaultHandler);
			
			consumer.addEventListener(MessageEvent.MESSAGE, allMessageHandler);
			consumer.addEventListener(MessageFaultEvent.FAULT, serviceFaultHandler);
			
			consumer.subscribe();
			
			// 
			currentUser = new StructureUser();
		}
		
		//trimite mesajul dinou spre flex (interfata activa)
		private function sendMessageToFlex(event:EventCommandFlex):void {			 
			var event:EventCommandFlex = new EventCommandFlex(event.command, event.args)
			app.appView.selectedChild.dispatchEvent(event);
		}
		
		//trimite mesajul spre server
		private function sendMessage(event:EventCommandFlex):void
		{	
			var message:ComMsgToJava = new ComMsgToJava(event.command, event.args);		
			message.BuildAndSendMessage(producer);
		}
		
		//primeste toate mesajele de pe server
		private function allMessageHandler(events:MessageEvent):void
		{
			// interesant ca vine pe messageId
			//Alert.show("Command Recieved from server: " + events.message.headers.command);
			
			if (events.message.messageId == app.clientID) {
				var event:EventCommandFlex = new EventCommandFlex(events.message.headers.command, events.message.body);
				
				if ((events.message.headers.command == AllCommands.LOGIN_RESPONSE) || (events.message.headers.command == AllCommands.LOGIN_RIGHTS)) {
					app.login.dispatchEvent(event);					
				} else {					
					app.appView.selectedChild.dispatchEvent(event);
				}
			} else {
				//Alert.show("Command Recieved from server but clientID not recognized!");
			}
		}
				
		private function serviceFaultHandler(events:MessageFaultEvent):void
		{
			Alert.show(events.faultString, events.faultCode);
		}
		
		public static function setAllRightsToFalse():void {
			// for Admin
			CentralComm.rightsUserAdmin = false;
				
			// for Prof
			CentralComm.rightsUserProf = false;
				
			// for Secretar 
			CentralComm.rightsUserSecretar = false;

			// for Strudent
			CentralComm.rightsUserStudent = false;
		}
	}
}