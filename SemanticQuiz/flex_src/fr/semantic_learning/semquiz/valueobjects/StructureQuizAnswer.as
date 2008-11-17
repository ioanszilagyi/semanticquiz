package fr.semantic_learning.semquiz.valueobjects
{

	
	[Bindable]
	[RemoteClass(alias="fr.semantic_learning.semquiz.valueobjects.StructureQuizAnswer")]
	
	public class StructureQuizAnswer
	{
		public var id:String;
		public var idquiz:String;
		public var qanswer:Array = new Array();
				
		public function StructureQuizAnswer()
		{
		}

	}
}