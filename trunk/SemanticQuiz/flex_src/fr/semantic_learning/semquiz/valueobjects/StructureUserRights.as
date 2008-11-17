package fr.semantic_learning.semquiz.valueobjects
{
	[Bindable]
	[RemoteClass(alias="fr.semantic_learning.semquiz.valueobjects.StructureUserRights")]
	public class StructureUserRights
	{
		public var idUser:String;
		public var rights:Array = new Array(); //obiecte de tipul StructureInterfaceRights
	}
}