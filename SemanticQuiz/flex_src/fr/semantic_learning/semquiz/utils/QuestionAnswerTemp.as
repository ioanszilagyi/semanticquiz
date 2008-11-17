package fr.semantic_learning.semquiz.utils
{
import mx.collections.*;
import flash.net.*;
	
	public class QuestionAnswerTemp extends Array  
	{		
		public var key:String = "not";
		public var text:String;
		public var validation:String;
		
		public var sound:String;
		public var picture:String;
		public var video:String;	
		public var sound_ref:FileReference = null;
		public var picture_ref:FileReference = null;
		public var video_ref:FileReference = null;
	}
}