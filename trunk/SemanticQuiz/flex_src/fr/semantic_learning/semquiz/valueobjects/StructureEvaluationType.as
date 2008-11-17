package fr.semantic_learning.semquiz.valueobjects
{
	[Bindable]
	[RemoteClass(alias="fr.semantic_learning.semquiz.valueobjects.StructureEvaluationType")]

	public class StructureEvaluationType
	{
		public var id:String;
		public var title:String;
		
		public function StructureEvaluationType(id:String=null, title:String=null)
		{
			this.id = id;
			this.title = title;			
		}

	}
}