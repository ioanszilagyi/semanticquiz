package fr.semantic_learning.semquiz.valueobjects
{
	[Bindable]
	[RemoteClass(alias="fr.semantic_learning.semquiz.valueobjects.StructureDiscliplineDomain")]
	
	public class StructureDisciplineDomain
	{
		public var id:String;
		public var cod:String;
		public var title:String;
		
		public function StructureDisciplineDomain(cod:String=null, title:String=null, id:String=null)
		{
			this.cod = cod;
			this.title = title;
			this.id = id;
		}
		

	}
}