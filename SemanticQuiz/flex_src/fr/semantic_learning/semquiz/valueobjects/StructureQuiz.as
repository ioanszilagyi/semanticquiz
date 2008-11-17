package fr.semantic_learning.semquiz.valueobjects
{
	[Bindable]
	[RemoteClass(alias="fr.semantic_learning.semquiz.valueobjects.StructureQuiz")]
	
	public class StructureQuiz
	{
		public var id:String;
		public var title:String;
		public var author:String;
		public var language:String;
		public var date:String;
		public var questions:Array = new Array();
		// this is used to recive
		public var questionsV:Array = new Array();

		public function StructureQuiz()
		{
		}

	}
}