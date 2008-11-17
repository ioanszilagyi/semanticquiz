package fr.semantic_learning.semquiz.valueobjects
{
	
	[Bindable]
	[RemoteClass(alias="fr.semantic_learning.semquiz.valueobjects.StructureProf")]
	public class StructureProf
	{
		public var idUser:String;
		public var idSubsections:Array = new Array();
		
		public function StructureProf()
		{
		
		}

	}
}