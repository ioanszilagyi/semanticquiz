package fr.semantic_learning.semquiz.valueobjects
{
	import mx.validators.StringValidator;
	
	[Bindable]
	[RemoteClass(alias="fr.semantic_learning.semquiz.valueobjects.StructureStudent")]
	public class StructureStudent
	{
		public var idUser:String;
		public var idGroup:Array = new Array();
		
		public var familyName:String;
		public var givenName:String;
		
		public var idMention:String;
		public var idSpec:String;
		public var idParcours:String;
		public var idYear:String;
	//	public var idPendingQuizzes:Array = new Array();
	//	public var idAnsweredQuizzes:Array = new Array();
	}
}