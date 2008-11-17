package fr.semantic_learning.semquiz.valueobjects
{
	[Bindable]
	[RemoteClass(alias="fr.semantic_learning.semquiz.valueobjects.StructureSpecialization")]
	
	public class StructureSpecialization
	{
		public var id:String;
		public var title:String;
		public var cod:String;
		public var idResp:String;
		public var idMention:String;
		
		public function StructureSpecialization( cod:String=null, title:String=null, idResp:String=null, idMention:String=null, id:String=null)
		{
			this.cod = cod;
			this.title = title;
			this.idResp = idResp;
			this.idMention = idMention;
			this.id = id;
		}

	}
}