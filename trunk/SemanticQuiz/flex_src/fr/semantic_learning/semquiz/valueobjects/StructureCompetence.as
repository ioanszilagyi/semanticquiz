package fr.semantic_learning.semquiz.valueobjects
{
	[Bindable]
	[RemoteClass(alias="fr.semantic_learning.semquiz.valueobjects.StructureCompetence")]
	public class StructureCompetence
	{
		public var id:String;
		public var title:String;
		
		
		public function StructureCompetence(title:String=null, id:String=null)
		{
			this.title = title;
			this.id = id;
		}
		

	}
}