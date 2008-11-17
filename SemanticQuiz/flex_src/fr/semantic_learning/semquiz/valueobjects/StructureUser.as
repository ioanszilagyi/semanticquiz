package fr.semantic_learning.semquiz.valueobjects
{
	[Bindable]
	[RemoteClass(alias="fr.semantic_learning.semquiz.valueobjects.StructureUser")]
	public class StructureUser
	{
		
		//public function StructureUser(){}
		
		public var id:String;
		public var isAuthenticated:Boolean=false;
		public var userName:String;
		public var password:String;
				
		public var familyName:String;
		public var givenName:String;
		
		public var email:String;
		public var title:String;
		public var role:String; //student, profesor, admin, secretar
		public var org:String; 
		public var url:String;
		public var language:String;
		
	//	public var idSubsections:Array = new Array();
	//	public var idSections:Array = new Array();
	//	public var idSpec: Array = new Array();
	//	public var idParcours: Array = new Array();
		
	//	public var rights:StructureUserRights;
		
		
		public function StructureUser(username:String=null, password:String=null)
		{
			this.userName = username;
			this.password = password;		
		}
		
		
	}
}

