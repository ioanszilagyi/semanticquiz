package fr.semantic_learning.semquiz.valueobjects
{
	import mx.collections.ArrayCollection;
	
	[Bindable]
	[RemoteClass(alias="fr.semantic_learning.semquiz.valueobjects.StructureQuestion")]
	public class StructureQuestion
	{
		public function StructureQuestion()
		{
			
		}
		
		//public var domain:String;
		public var id:String;
		public var subsection:String;
		public var type:String;
		public var evaluationTime:String;
		public var evaluationType:String;
		public var competence:Array;		
		public var author:String;
		public var data:String;
		public var language:String;
		public var text:String;
		public var picture:String;
		public var video:String;
		public var sound:String;
		public var variants:Array;
		public var units:String;

		
	
	/*	//setters and getter for domain
		public function setDomain(domain:String):void
		{
			this.domain= domain;		
		}
		public function getDomain():String
		{
			return this.domain;		
		}
		
		
		//setters and getter for type
		public function setType(type:String):void
		{
			this.type= type;		
		}
		public function getType():String
		{
			return this.type;		
		}
		
		//setters and getter for evaluationTime
		public function setEvaluationTime(evaluationTime:String):void
		{
			this.evaluationTime= evaluationTime;		
		}
		public function getEvaluationTime():String
		{
			return this.evaluationTime;		
		}
		
		//setters and getter for competence
		public function setCompetence(competence:String):void
		{
			this.competence= competence;		
		}
		public function getCompetence():String
		{
			return this.competence;		
		}
		
		//setters and getter for author
		public function setAuthor(author:String):void
		{
			this.author= author;		
		}
		public function getAuthor():String
		{
			return this.author;		
		}
		
		//setters and getter for data
		public function setData(data:String):void
		{
			this.data= data;		
		}
		public function getData():String
		{
			return this.data;		
		}
		
		//setters and getter for language
		public function setLanguage(language:String):void
		{
			this.language= language;		
		}
		public function getLanguage():String
		{
			return this.language;		
		}
		
		//setters and getter for text
		public function setText(text:String):void
		{
			this.text= text;		
		}
		public function getText():String
		{
			return this.text;		
		}
		
		//setters and getter for picture
		public function setPicture(picture:String):void
		{
			this.picture= picture;		
		}
		public function getPicture():String
		{
			return this.picture;		
		}
		
		//setters and getter for video
		public function setVideo(video:String):void
		{
			this.video= video;		
		}
		public function getVideo():String
		{
			return this.video;		
		}
		
		//setters and getter for author
		public function setSound(sound:String):void
		{
			this.sound= sound;		
		}
		public function getSound():String
		{
			return this.sound;		
		}
		
		//setters and getter for variants
		public function setVariants(variant:StructureQuestionVariant):void
		{
			this.variants.push(variant);		
		}
				
		public function getVariants(i:Number):StructureQuestionVariant
		{
			return this.variants[i];
		}
		
		public function getVariantsNumber():int
		{
			return this.variants.length;		
		}
		
	*/	
	}
}