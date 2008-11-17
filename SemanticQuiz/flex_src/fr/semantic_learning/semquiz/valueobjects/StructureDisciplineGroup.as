package fr.semantic_learning.semquiz.valueobjects
{
	
	[Bindable]
	[RemoteClass(alias="fr.semantic_learning.semquiz.valueobjects.StructureDisciplineGroup")]
	
	public class StructureDisciplineGroup
	
	{
		public var id:String;
		public var title:String;
		public var discipline_domain:StructureDisciplineDomain;
	
		
		public function StructureDisciplineGroup(title:String=null, discipline_domain:StructureDisciplineDomain=null, id:String=null)
		{
			this.title = title;
			this.discipline_domain = discipline_domain;
			this.id = id;
		}

	}
}