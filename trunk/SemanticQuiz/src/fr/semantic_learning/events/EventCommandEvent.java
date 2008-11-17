package fr.semantic_learning.events;

import java.util.EventObject;

public class EventCommandEvent extends EventObject {
	public String command;
	public Object args;
	public String clientID;

	public EventCommandEvent(Object source, String command, Object args, String clientID) {
		super(source);
		this.clientID = clientID;
		this.command = command;
		this.args = args;
	}
}
