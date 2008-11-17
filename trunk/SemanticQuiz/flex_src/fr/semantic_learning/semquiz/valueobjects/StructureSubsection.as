package fr.semantic_learning.semquiz.valueobjects
{
	[Bindable]
	[RemoteClass(alias="fr.semantic_learning.semquiz.valueobjects.StructureSubsection")]
	
	public class StructureSubsection
	{
		public var id:String;
		public var title:String;
		public var partOf:String;
		
		public function StructureSubsection(title:String=null, partOf:String=null, id:String=null)
		{
			this.title = title;
			this.partOf = partOf;
			this.id = id;
		}

	}
}