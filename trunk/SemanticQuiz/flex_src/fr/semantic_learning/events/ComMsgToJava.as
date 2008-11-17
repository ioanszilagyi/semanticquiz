package fr.semantic_learning.events
{
	
import fr.semantic_learning.semquiz.CentralComm;

import mx.messaging.*;
import mx.messaging.messages.*;

/**
 * @author radu
 * 
 * This class will handle all the sending messages from Flex to Java.
 */	
	public class ComMsgToJava
	{
		private var command:String;
		private var messageBody:Object;
	
		public function ComMsgToJava(command:String, messageBody:Object):void
		{
			this.command = command;
			this.messageBody = messageBody;
		}	
	
		public function BuildAndSendMessage(producer:Producer):void {
			if (command != null) {
				var msg:AsyncMessage = new AsyncMessage();
		
				msg.headers = new Object();
				msg.headers.command = command;
				msg.clientId = CentralComm.app.clientID;
				
				msg.body = messageBody;
				
				// send the event to server								
				producer.send(msg);
			} else {
				
				// throw exception 
			}
		}
	
		public function setCommand(command:String):void
		{
			this.command = command;		
		}
		public function getCommand():String
		{
			return this.command;		
		}
		
		public function setMessageBody(messageBody:Object):void
		{
			this.messageBody = messageBody;		
		}
		public function getMessageBody():Object
		{
			return this.messageBody;		
		}
	}
}