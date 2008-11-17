package fr.semantic_learning.semquiz.valueobjects
{
	[Bindable]
	[RemoteClass(alias="fr.semantic_learning.semquiz.valueobjects.StructureDisciplineSection")]
	
	public class StructureDisciplineSection
	{
		public var id:String;
		public var title:String;
		public var cod:String;
		public var discipline_group:StructureDisciplineGroup;
		
		public function StructureDisciplineSection(cod:String=null, title:String=null, discipline_group:StructureDisciplineGroup=null, id:String=null)
		{
			this.cod = cod;
			this.title = title;
			this.discipline_group = discipline_group;
			this.id = id;
		}

	}
}