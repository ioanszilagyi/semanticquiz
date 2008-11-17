package fr.semantic_learning.events;

import flex.messaging.Destination;
import flex.messaging.messages.AsyncMessage;
import flex.messaging.services.MessageService;
import flex.messaging.util.UUIDUtils;

/**
 * @author radu
 *
 * This class will handle all the sending messages from Java to Flex.
 */
public class ComMsgToFlex {	
	
	private String clientID;
	private String command;
	private Object messageBody;

	public ComMsgToFlex() {
		command = null;
	}
	
	public ComMsgToFlex(String command, String clientID) {
		this.clientID = clientID;
		this.command = command;
		messageBody = null;
	}
	
	public ComMsgToFlex(String command, Object messageBody, String clientID) {
		this.clientID = clientID;
		this.command = command;
		this.messageBody = messageBody;
	}

	public void BuildAndSendMessage(Destination serviceAdapterDestunation) {
		if ((command != null) && (messageBody != null)) {
			
			AsyncMessage newMessage = new AsyncMessage();
			
			newMessage.setHeader("command", command);
			
			newMessage.setBody(messageBody);
			
			System.out.println("clinetID from ComMsgToJava.java: "+ clientID);
			
			newMessage.setClientId(clientID);
			//newMessage.setMessageId(UUIDUtils.createUUID(false));
			newMessage.setMessageId(clientID);
			newMessage.setTimestamp(System.currentTimeMillis());
			
			newMessage.setDestination("CentralCommAdapter");
			MessageService msgService = (MessageService) serviceAdapterDestunation.getService();
			msgService.pushMessageToClients(newMessage, true);
			
		} else {
			System.out.println("BuildAndSendMessage: (command != null) && (messageBody != null)");
		}
	}
	
	/**
	 * @return the command
	 */
	public String getCommand() {
		return command;
	}
	/**
	 * @param command the command to set
	 */
	public void setCommand(String command) {
		this.command = command;
	}
	/**
	 * @return the messageBody
	 */
	public Object getMessageBody() {
		return messageBody;
	}
	/**
	 * @param args the messageBody to set
	 */
	public void setMessageBody(Object messageBody) {
		this.messageBody = messageBody;
	}
}
