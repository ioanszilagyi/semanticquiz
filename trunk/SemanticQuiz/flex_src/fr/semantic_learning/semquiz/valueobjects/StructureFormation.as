package fr.semantic_learning.semquiz.valueobjects
{

	[Bindable]
	[RemoteClass(alias="fr.semantic_learning.semquiz.valueobjects.StructureFormation")]
	
	public class StructureFormation
	{
		public var id:String;
		public var title:String;
		public var num_ani:String;
		public var yearTitle:String;
		
		public function StructureFormation(title:String=null, num_ani:String=null, yearTitle:String=null, id:String=null)
		{
		
			this.title = title;
			this.num_ani = num_ani;
			this.id = id;
			this.yearTitle = yearTitle;
		}

	}
}