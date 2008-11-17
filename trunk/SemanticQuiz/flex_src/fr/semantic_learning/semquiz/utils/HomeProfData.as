
package fr.semantic_learning.semquiz.utils
{
	import mx.collections.ArrayCollection;
	
	public class HomeProfData
	{
		public static var selected_subsection:String = null;
		public static var selected_competence:Array = new Array();
		public static var selected_unit:String = null;
		public static var response_time:String = null;
		public static var evaluation_type:String = null;
		public static var question_type:String = null;
		public static var language:String = null;
		//public static var Question:StructureQuestionParam = new StructureQuestionParam();
		
		public static var controls:ArrayCollection = new ArrayCollection();	
		public function HomeProfData()
		{
			
		}
		
		/* public static function GetDomain():String
		{
			return this.selected_domain;
		}
		
		public static function SetDomain(var dom:String):void
		{
			this.selected_domain = dom;
		} */

	}
}