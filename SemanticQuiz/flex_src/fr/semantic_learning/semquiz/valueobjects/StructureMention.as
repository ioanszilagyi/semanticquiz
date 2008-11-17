package fr.semantic_learning.semquiz.valueobjects
{
	[Bindable]
	[RemoteClass(alias="fr.semantic_learning.semquiz.valueobjects.StructureMention")]
	
	public class StructureMention
	{
		
		public var id:String;
		//public var cod:String; //codul mentiunii.. se introduce din interfata de creere mentiuni
		public var title:String; //codul mentiunii.. se introduce din interfata de creere mentiuni
		public var idResp:String; //responsabilul pt mentiunea respectiva (se primeste un id user)
		public var idFormation:String;
		
		public function StructureMention( title:String=null, idResp:String=null, idFormation:String=null, id:String=null)
		{
			//this.cod = cod;
			this.title = title;
			this.idResp = idResp;
			this.idFormation = idFormation;
			this.id = id;
		}

	}
}