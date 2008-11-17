package fr.semantic_learning.semquiz.valueobjects
{
	[Bindable]
	[RemoteClass(alias="fr.semantic_learning.semquiz.valueobjects.StructureInterfaceRights")]
	public class StructureInterfaceRights
	{
		public var id:String;
		public var idInterface:String;
		public var isAllowed:Boolean;	
		// description
		public var description:String;
			
		public function StructureInterfaceRights(id:String=null, idInterface:String=null, isAllowed:Boolean=false, description:String=null):void {
			this.id = id;
			this.idInterface = idInterface;
			this.isAllowed = isAllowed;
			this.description = description;
		}
	}
}