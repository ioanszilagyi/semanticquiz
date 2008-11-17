package fr.semantic_learning.semquiz.valueobjects
{
	
	[Bindable]
	[RemoteClass(alias="fr.semantic_learning.semquiz.valueobjects.StructureGroups")]
	public class StructureGroups
	{
		public var id:String;
		public var title:String;
		public var idMention:String;
		public var idSpec:String;
		public var idParcours:String;
		public var idYear:String;
		public var description:String;
	}
}