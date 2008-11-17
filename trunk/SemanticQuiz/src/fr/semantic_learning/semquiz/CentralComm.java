package fr.semantic_learning.semquiz;

import flex.messaging.messages.Message;
import flex.messaging.services.ServiceAdapter;

import fr.semantic_learning.events.*;
import fr.semantic_learning.semquiz.commands.AllCommands;
import fr.semantic_learning.semquiz.valueobjects.*;

/**
 * @author radu
 * 
 * This class will handle the events received from the interface and from other
 * classes in java
 */
public class CentralComm extends ServiceAdapter
		implements
			EventCommandSeenListner {
	// this class variable will reference "RDF Construction" module
	public RDFConstruction rdfConstruction;
	// this class variable will reference "RDF SPARQL" module
	public RDFSPARQL rdfSPARQL;

	// used to send commands to modules
	private EventCommandSeenListner subscribeToRDFConstruction;
	private EventCommandSeenListner subscribeToRDFSPARQL;

	public CentralComm() {
		// instantiate all classes and start listen the commands
		rdfConstruction = new RDFConstruction(this);
		rdfSPARQL = new RDFSPARQL(this);

		// listen commands from modules (link)
		subscribeToRDFConstruction = rdfConstruction;
		subscribeToRDFSPARQL = rdfSPARQL;
	}

	/* (non-Javadoc)
	 * @see flex.messaging.services.ServiceAdapter#invoke(flex.messaging.messages.Message)
	 * 
	 * Here events from Flex will be called, parsed and dispersed further in Java. 
	 */
	@Override
	public Object invoke(Message message) {		
		System.out.println("Message from Flex: ");
		System.out.println(" clientID: "+message.getClientId().toString());
		System.out.println(" Command: "+message.getHeader("command"));
			
		switch (AllCommands.toCommands((String)message.getHeader("command"))) {
			case NEW_QUESTION:
				// "New Question" this message is received from the "Create Question" Flex interface.
				// 				
				// call the events with args to RDFConstruction
				subscribeToRDFConstruction.eventCommandSeen(new EventCommandEvent(this,	message.getHeader("command").toString(), message.getBody(), message.getClientId().toString()));
			break;
			case LOGIN_REQUEST:

				System.out.println(" Username: " + ((StructureUser)message.getBody()).getUserName());
				System.out.println(" Password: " + ((StructureUser)message.getBody()).getPassword());
				
				subscribeToRDFSPARQL.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(), message.getBody(), message.getClientId().toString()));
				break;
			case NEW_USER:
				// call the event with args to RDFSPARQL
				//subscribeToRDFConstruction.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(), message.getBody(), message.getClientId().toString()));
				subscribeToRDFSPARQL.eventCommandSeen(new EventCommandEvent(this, AllCommands.EXIST_USER.getCommand(), message.getBody(), message.getClientId().toString()));
				break;
			case NEW_USER_RIGHTS:
				// call the event with args to RDFSPARQL
				//subscribeToRDFConstruction.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(), message.getBody(), message.getClientId().toString()));
				subscribeToRDFConstruction.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(), message.getBody(), message.getClientId().toString()));
				break;				
			case NEW_QUIZ:
				// call the event with args to RDFSPARQL
				subscribeToRDFConstruction.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(), message.getBody(), message.getClientId().toString()));
				break;
			case NEW_COMPETENCE:
				// call the event with args to RDFSPARQL
				subscribeToRDFConstruction.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(), message.getBody(), message.getClientId().toString()));
				break;
			case NEW_SUBSECTION:
				// call the event with args to RDFSPARQL
				subscribeToRDFConstruction.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(), message.getBody(), message.getClientId().toString()));
				break;
			case NEW_FORMATION:
				// call the event with args to RDFSPARQL
				subscribeToRDFConstruction.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(), message.getBody(), message.getClientId().toString()));
				break;
			case NEW_MENTION:
				// call the event with args to RDFSPARQL
				subscribeToRDFConstruction.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(), message.getBody(), message.getClientId().toString()));
				break;
			case NEW_SPECIALIZATION:
				// call the event with args to RDFSPARQL
				subscribeToRDFConstruction.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(), message.getBody(), message.getClientId().toString()));
				break;
			case NEW_PARCOURS:
				// call the event with args to RDFSPARQL
				subscribeToRDFConstruction.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(), message.getBody(), message.getClientId().toString()));
				break;
			case NEW_UNIT:
				// call the event with args to RDFConstruction
				subscribeToRDFConstruction.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(), message.getBody(), message.getClientId().toString()));
				break;
			case NEW_PENDING_Q:
				// call the event with args to RDFConstruction
				subscribeToRDFConstruction.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(), message.getBody(), message.getClientId().toString()));
				break;	
			case SELECT_USER:
				// call the event with args to RDFSPARQL
				subscribeToRDFSPARQL.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(), message.getBody(), message.getClientId().toString()));
				break;	
			case SELECT_SECTION:
				subscribeToRDFSPARQL.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(),message.getBody(),message.getClientId().toString()));
				break;
			case SELECT_RIGHTS:
				subscribeToRDFSPARQL.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(),message.getBody(),message.getClientId().toString()));
				break;
			case LOGIN_RIGHTS:
				subscribeToRDFSPARQL.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(),message.getBody(),message.getClientId().toString()));
				break;	
			case SELECT_ALL_RIGHTS:
				subscribeToRDFSPARQL.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(),message.getBody(),message.getClientId().toString()));
				break;				
			case SELECT_SUBSECTION:
				subscribeToRDFSPARQL.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(),message.getBody(),message.getClientId().toString()));
				break;
			case SELECT_YEAR:
				subscribeToRDFSPARQL.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(),message.getBody(),message.getClientId().toString()));
				break;	
			case SELECT_FORMATION:
				subscribeToRDFSPARQL.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(),message.getBody(),message.getClientId().toString()));
				break;
			case SELECT_MENTION:
				subscribeToRDFSPARQL.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(),message.getBody(),message.getClientId().toString()));
				break;
			case SELECT_COMPETENCE:
				subscribeToRDFSPARQL.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(),message.getBody(),message.getClientId().toString()));
				break;	
			case SELECT_SEMESTER:
				subscribeToRDFSPARQL.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(),message.getBody(),message.getClientId().toString()));
				break;		
			case SELECT_SPECIALIZATION:
				subscribeToRDFSPARQL.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(),message.getBody(),message.getClientId().toString()));
				break;
			case SELECT_PARCOURS:
				subscribeToRDFSPARQL.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(),message.getBody(),message.getClientId().toString()));
				break;
			case SELECT_EVALUATIONTYPE:
				subscribeToRDFSPARQL.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(),message.getBody(),message.getClientId().toString()));
				break;
			case SELECT_PENDING_QUIZZES:
				// call the event with args to RDFSPARQL
				subscribeToRDFSPARQL.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(), message.getBody(), message.getClientId().toString()));
				break;
			case SELECT_QUESTIONS:
				// call the event with args to RDFSPARQL
				subscribeToRDFSPARQL.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(), message.getBody(), message.getClientId().toString()));
				break;	
			case SELECT_QUIZ_QUESTIONS:
				// call the event with args to RDFSPARQL
				subscribeToRDFSPARQL.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(), message.getBody(), message.getClientId().toString()));
				break;					
			case SUBSECTION_QUESTIONS:
				// call the event with args to RDFSPARQL
				subscribeToRDFSPARQL.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(), message.getBody(), message.getClientId().toString()));
				break;
			case GET_SECTIONS:
				// call the event with args to RDFSPARQL
				subscribeToRDFSPARQL.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(), message.getBody(), message.getClientId().toString()));
				break;
			case SELECT_UNIT:
				// call the event with args to RDFSPARQL
				subscribeToRDFSPARQL.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(), message.getBody(), message.getClientId().toString()));
				break;
			case SELECT_ALL_QUIZ:
				// call the event with args to RDFSPARQL
				subscribeToRDFSPARQL.eventCommandSeen(new EventCommandEvent(this, message.getHeader("command").toString(), message.getBody(), message.getClientId().toString()));
				break;
			case UNKNOW_COMMAND:
				System.out.println(" Unknow Command Received: "+message.getHeader("command").toString());
				break;
			default:
				System.out.println(" Unknow Command Received: "+message.getHeader("command").toString());
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.semantic_learning.semquiz.EventCommandSeenListner#eventCommandSeen(fr.semantic_learning.semquiz.EventCommandEvent)
	 * 
	 * This method will handle the events seen between Java modules.
	 */
	public void eventCommandSeen(EventCommandEvent ece) {
		// handle the events from RDFConstruction module			
		if (ece.getSource().equals(rdfConstruction)) {
			if (ece.command == AllCommands.IS_SAVED.getCommand()) {
				if(ece.args.toString().equals(false))
				{
					System.out.println("CentralComm! RDF Not Saved in DB");
				}
				else
				{
					System.out.println("CentralComm! RDF Saved in DB");
				}
				
				ComMsgToFlex message = new ComMsgToFlex(AllCommands.IS_SAVED.getCommand(), ece.args, ece.clientID);								
				message.BuildAndSendMessage(getDestination());
			}
		} else
		// handle the events from RDFSPARQL module
		if (ece.getSource().equals(rdfSPARQL)) {		
			if (ece.command == AllCommands.SELECT_SUBSECTION.getCommand()) {
				System.out.println("Event: " + ece.command + "; Recived in CentralComm from: " + ece.getSource().toString());

				ComMsgToFlex message = new ComMsgToFlex(AllCommands.SELECT_SUBSECTION.getCommand(), ece.args, ece.clientID);								
				message.BuildAndSendMessage(getDestination());
			}
			if (ece.command == AllCommands.SELECT_QUIZ_QUESTIONS.getCommand()) {
				System.out.println("Event: " + ece.command + "; Recived in CentralComm from: " + ece.getSource().toString());

				ComMsgToFlex message = new ComMsgToFlex(AllCommands.SELECT_QUIZ_QUESTIONS.getCommand(), ece.args, ece.clientID);								
				message.BuildAndSendMessage(getDestination());
			}
			if (ece.command == AllCommands.SELECT_UNIT.getCommand()) {
				System.out.println("Event: " + ece.command + "; Recived in CentralComm from: " + ece.getSource().toString());

				ComMsgToFlex message = new ComMsgToFlex(AllCommands.SELECT_UNIT.getCommand(), ece.args, ece.clientID);								
				message.BuildAndSendMessage(getDestination());
			}
			if (ece.command == AllCommands.SELECT_EVALUATIONTYPE.getCommand()) {
				System.out.println("Event: " + ece.command + "; Recived in CentralComm from: " + ece.getSource().toString());

				ComMsgToFlex message = new ComMsgToFlex(AllCommands.SELECT_EVALUATIONTYPE.getCommand(), ece.args, ece.clientID);								
				message.BuildAndSendMessage(getDestination());
			}
			if (ece.command == AllCommands.LOGIN_RESPONSE.getCommand()) {
				System.out.println("Event: " + ece.command + "; Recived in CentralComm from: " + ece.getSource().toString());

				ComMsgToFlex message = new ComMsgToFlex(AllCommands.LOGIN_RESPONSE.getCommand(), ece.args, ece.clientID);								
				message.BuildAndSendMessage(getDestination());
				
			}
			if (ece.command == AllCommands.SELECT_ALL_QUIZ.getCommand()) {
				System.out.println("Event: " + ece.command + "; Recived in CentralComm from: " + ece.getSource().toString());

				ComMsgToFlex message = new ComMsgToFlex(AllCommands.SELECT_ALL_QUIZ.getCommand(), ece.args, ece.clientID);								
				message.BuildAndSendMessage(getDestination());
				
			}
		
			if (ece.command == AllCommands.LOGIN_RIGHTS.getCommand()) {
				System.out.println("Event: " + ece.command + "; Recived in CentralComm from: " + ece.getSource().toString());

				ComMsgToFlex message = new ComMsgToFlex(AllCommands.LOGIN_RIGHTS.getCommand(), ece.args, ece.clientID);								
				message.BuildAndSendMessage(getDestination());
				
			}

			if (ece.command == AllCommands.NEW_USER.getCommand()) {
				System.out.println("Event: " + ece.command + "; Recived in CentralComm from: " + ece.getSource().toString());

				ComMsgToFlex message = new ComMsgToFlex(AllCommands.LOGIN_RESPONSE.getCommand(), ece.args, ece.clientID);								
				message.BuildAndSendMessage(getDestination());
				
			}
			
			//get USER rights by id
			if (ece.command == AllCommands.SELECT_RIGHTS.getCommand()) {
				System.out.println("Event: " + ece.command + "; Recived in CentralComm from: " + ece.getSource().toString());

				ComMsgToFlex message = new ComMsgToFlex(AllCommands.SELECT_RIGHTS.getCommand(), ece.args, ece.clientID);								
				message.BuildAndSendMessage(getDestination());
				
			}
			//REALTIME SEARCH USER
			if (ece.command == AllCommands.SELECT_USER.getCommand()) {
				System.out.println("Event: " + ece.command + "; Recived in CentralComm from: " + ece.getSource().toString());

				ComMsgToFlex message = new ComMsgToFlex(AllCommands.SELECT_USER.getCommand(), ece.args, ece.clientID);								
				message.BuildAndSendMessage(getDestination());
				
			}
			//REALTIME SEARCH USER
			if (ece.command == AllCommands.SELECT_YEAR.getCommand()) {
				System.out.println("Event: " + ece.command + "; Recived in CentralComm from: " + ece.getSource().toString());

				ComMsgToFlex message = new ComMsgToFlex(AllCommands.SELECT_YEAR.getCommand(), ece.args, ece.clientID);								
				message.BuildAndSendMessage(getDestination());
				
			}
			//Interface_All_RIGHTS 
			if (ece.command == AllCommands.SELECT_ALL_RIGHTS.getCommand()) {
				System.out.println("Event: " + ece.command + "; Recived in CentralComm from: " + ece.getSource().toString());

				ComMsgToFlex message = new ComMsgToFlex(AllCommands.SELECT_ALL_RIGHTS.getCommand(), ece.args, ece.clientID);								
				message.BuildAndSendMessage(getDestination());
				
			}
			//return questions from a specific domain
			if (ece.command == AllCommands.SUBSECTION_QUESTIONS.getCommand()) {
				System.out.println("Event: " + ece.command + "; Recived in CentralComm from: " + ece.getSource().toString());

				ComMsgToFlex message = new ComMsgToFlex(AllCommands.SUBSECTION_QUESTIONS.getCommand(), ece.args, ece.clientID);								
				message.BuildAndSendMessage(getDestination());
				
			}

			if (ece.command == AllCommands.SELECT_SECTION.getCommand()) {
				System.out.println("Event: " + ece.command + "; Recived in CentralComm from: " + ece.getSource().toString());

				ComMsgToFlex message = new ComMsgToFlex(AllCommands.SELECT_SECTION.getCommand(), ece.args, ece.clientID);								
				message.BuildAndSendMessage(getDestination());
				
			}
			if (ece.command == AllCommands.SELECT_SEMESTER.getCommand()) {
				System.out.println("Event: " + ece.command + "; Recived in CentralComm from: " + ece.getSource().toString());

				ComMsgToFlex message = new ComMsgToFlex(AllCommands.SELECT_SEMESTER.getCommand(), ece.args, ece.clientID);								
				message.BuildAndSendMessage(getDestination());
				
			}
			if (ece.command == AllCommands.SELECT_FORMATION.getCommand()) {
				System.out.println("Event: " + ece.command + "; Recived in CentralComm from: " + ece.getSource().toString());

				ComMsgToFlex message = new ComMsgToFlex(AllCommands.SELECT_FORMATION.getCommand(), ece.args, ece.clientID);								
				message.BuildAndSendMessage(getDestination());
				
			}

			if (ece.command == AllCommands.SELECT_SPECIALIZATION.getCommand()) {
				System.out.println("Event: " + ece.command + "; Recived in CentralComm from: " + ece.getSource().toString());

				ComMsgToFlex message = new ComMsgToFlex(AllCommands.SELECT_SPECIALIZATION.getCommand(), ece.args, ece.clientID);								
				message.BuildAndSendMessage(getDestination());
				
			}
			
			if (ece.command == AllCommands.SELECT_QUESTIONS.getCommand()) {
				System.out.println("Event: " + ece.command + "; Recived in CentralComm from: " + ece.getSource().toString());

				ComMsgToFlex message = new ComMsgToFlex(AllCommands.SELECT_QUESTIONS.getCommand(), ece.args, ece.clientID);								
				message.BuildAndSendMessage(getDestination());
				
			}
			
			if (ece.command == AllCommands.SELECT_PARCOURS.getCommand()) {
				System.out.println("Event: " + ece.command + "; Recived in CentralComm from: " + ece.getSource().toString());

				ComMsgToFlex message = new ComMsgToFlex(AllCommands.SELECT_PARCOURS.getCommand(), ece.args, ece.clientID);								
				message.BuildAndSendMessage(getDestination());
				
			}


			if (ece.command == AllCommands.RETURN_SECTIONS.getCommand()) {
				System.out.println("Event: " + ece.command + "; Recived in CentralComm from: " + ece.getSource().toString());

				ComMsgToFlex message = new ComMsgToFlex(AllCommands.RETURN_SECTIONS.getCommand(), ece.args, ece.clientID);								
				message.BuildAndSendMessage(getDestination());
				
			}
			
			if (ece.command == AllCommands.SELECT_MENTION.getCommand()) {
				System.out.println("Event: " + ece.command + "; Recived in CentralComm from: " + ece.getSource().toString());

				ComMsgToFlex message = new ComMsgToFlex(AllCommands.SELECT_MENTION.getCommand(), ece.args, ece.clientID);								
				message.BuildAndSendMessage(getDestination());
			}
			
			if (ece.command == AllCommands.SELECT_COMPETENCE.getCommand()) {
				System.out.println("Event: " + ece.command + "; Recived in CentralComm from: " + ece.getSource().toString());

				ComMsgToFlex message = new ComMsgToFlex(AllCommands.SELECT_COMPETENCE.getCommand(), ece.args, ece.clientID);								
				message.BuildAndSendMessage(getDestination());
			}
			if (ece.command == AllCommands.SELECT_PENDING_QUIZZES.getCommand()) {
				System.out.println("Event: " + ece.command + "; Recived in CentralComm from: " + ece.getSource().toString());

				ComMsgToFlex message = new ComMsgToFlex(AllCommands.SELECT_PENDING_QUIZZES.getCommand(), ece.args, ece.clientID);								
				message.BuildAndSendMessage(getDestination());
			}

			if (ece.command == AllCommands.EXIST_USER.getCommand()) {
				System.out.println("Event: " + ece.command + "; Recived in CentralComm from: " + ece.getSource().toString());
				
				if(ece.args == null)
				{
					ComMsgToFlex message = new ComMsgToFlex(AllCommands.NEW_USER.getCommand(), false, ece.clientID);								
					message.BuildAndSendMessage(getDestination());
				}else {
					
					subscribeToRDFConstruction.eventCommandSeen(new EventCommandEvent(this,	AllCommands.NEW_USER.getCommand(), ece.args, ece.clientID));
				
				}
			}
			

		}
	}
}