package fr.semantic_learning.semquiz.valueobjects
{
	[Bindable]
	[RemoteClass(alias="fr.semantic_learning.semquiz.valueobjects.StructureYear")]
	public class StructureYear
	{
		public var id:String;
		public var title:String;
		public var idFormation:String;
		public var idSemesters:Array = new Array(); //id-urile din RDF-ul de semestre (static)
		
	}
}