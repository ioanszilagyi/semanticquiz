package fr.semantic_learning.semquiz.valueobjects
{
	[Bindable]
	[RemoteClass(alias="fr.semantic_learning.semquiz.valueobjects.StructureParcours")]
	
	public class StructureParcours
	{
		public var id:String;
		public var title:String;
		public var cod:String;
		public var idResp:String;
		public var idMention:String;
		public var idSpecialization:String;
		
		public function StructureParcours(cod:String=null, title:String=null, idResp:String=null, idMention:String=null, idSpecialization:String=null, id:String=null)
		{
			this.cod = cod;
			this.title = title;
			this.idResp = idResp;
			this.idMention = idMention;
			this.idSpecialization = idSpecialization;
			this.id = id;
		}

	}
}