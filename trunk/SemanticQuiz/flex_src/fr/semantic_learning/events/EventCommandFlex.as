package fr.semantic_learning.events
{
	import flash.events.Event;
	

	public class EventCommandFlex extends Event
	{
		public var command:String;
		public var args:Object;
		
		public function EventCommandFlex(command:String, args:Object)
		{
			//Add a 2nd argument of true to make this event bubble
			super(command, false);
			this.command = command;
			this.args=args;
		}
		
		/* override public function clone():Event
		{
			return new EventCommandFlex(command, args);
		} */
		
	}
}