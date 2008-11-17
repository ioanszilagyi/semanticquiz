package fr.semantic_learning.semquiz.valueobjects
{
	[Bindable]
	[RemoteClass(alias="fr.semantic_learning.semquiz.valueobjects.StructureQuestionAnswer")]
	public class StructureQuestionAnswer
	{		
		public var id:String;
		public var idQuestion:String;
		public var isCorect:Boolean;
		public var idVariantsAnswer:Array = new Array(); //array de id+isCorrectAnswered din StructureeQuestionVariants
		
	}
}