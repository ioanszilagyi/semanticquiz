package fr.semantic_learning.semquiz.valueobjects
{
	[Bindable]
	[RemoteClass(alias="fr.semantic_learning.semquiz.valueobjects.StructureQuestionVariants")]
	public class StructureQuestionVariants
	{
		public var id:String;
		public var text:String;
		public var validation:Boolean;
		public var sound:String;
		public var picture:String;
		public var video:String;
		
	}
}