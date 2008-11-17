package fr.semantic_learning.semquiz
{
	import flash.events.*;
	import flash.net.*;
	
	import fr.semantic_learning.events.*;
	import fr.semantic_learning.semquiz.commands.AllCommands;
	import fr.semantic_learning.semquiz.utils.*;
	import fr.semantic_learning.semquiz.valueobjects.StructureQuestion;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.utils.UIDUtil;	
	
	public class UploadFiles
	{		
		public var resourcesToUpload:ArrayCollection;
		private var theAnswer:Array;
		private var addFileIndex:int;
		private var questionID:String;
		private var resourceType:String;
		private var answerID:String;
		public var sent:Boolean;		
		
		public var Question:StructureQuestion = new StructureQuestion();
		public var QuestionVariants:Array;
		public var VariantsTemp:Array = new Array();
		public var resourceRef:FileReference;
		
		public function UploadFiles()
		{		
			resourcesToUpload = new ArrayCollection();
			addFileIndex = -1;		
			questionID = UIDUtil.createUID();
			sent = false;
		}		
		
		public function writeResourcesToServer(addFileIndex:int):void {		
				
			var resourcesAnswer:FileReference = (resourcesToUpload.getItemAt(addFileIndex) as FileReference);
			if(resourcesAnswer != null)
			{			
			var resourceRef:FileReference = resourcesAnswer;			

			resourceRef.addEventListener(ProgressEvent.PROGRESS, fileRef_progress);
			resourceRef.addEventListener(Event.COMPLETE, fileRef_complete);          
			resourceRef.addEventListener(IOErrorEvent.IO_ERROR, fileRef_IOError);			
			var params:URLVariables = new URLVariables();			

			params.rootDir = "resources/";
			params.questionDir = "resources/"+questionID;
			params.resourceDir = "resources/"+questionID+"/";//+"dir";			

			var request:URLRequest = new URLRequest();
			
			request.url = "uploader.jsp"; 

			request.method = URLRequestMethod.GET;
			request.data = params;
			request.contentType = "multipart/form-data";
			resourceRef.upload(request, resourceRef.name);
			}
			else{
			Alert.show("null value");
			}
		}
	
		public function addResource(answerID_:String):void {
			resourceRef = new FileReference();
			answerID = answerID_;
            resourceRef.addEventListener(Event.SELECT, fileRef_select);            
            resourceRef.browse(getTypes());
            
            if(VariantsTemp[answerID]==null)
            {
            VariantsTemp[answerID] = new QuestionAnswerTemp();               
            } 		
		}
		
		private function fileRef_select(evt:Event):void {
			try {				
				var resourceType:String;    
			    var control_img:String;
				switch (resourceRef.type.toString().toLowerCase()){
					case ".jpg" :
					resourceType = "picture";
					break;
					case ".jpeg" :
					resourceType = "picture";
					break;
					case ".gif" :
					resourceType = "picture";
					break;
					case ".png" :
					resourceType = "picture";
					break;
					case ".avi" :
					resourceType = "video";
					break;
					case ".mpeg" :
					resourceType = "video";
					break;
					case ".mpg" :
					resourceType = "video";
					break;
					case ".flv" :
					resourceType = "video";
					break;
					case ".mp3" :
					resourceType = "sound";
					break;
					default:
					Alert.show("Unmapped Extension");
					}
					switch (resourceType) {
						case "video": VariantsTemp[answerID].video = "resources/"+questionID+"/"+resourceRef.name;
						VariantsTemp[answerID].video_ref = resourceRef;	
						control_img = "imgV" + answerID.toString();						
						break;
						case "sound": VariantsTemp[answerID].sound = "resources/"+questionID+"/"+resourceRef.name;
						VariantsTemp[answerID].sound_ref = resourceRef;
						control_img = "imgS" + answerID.toString();
						break;
						case "picture": VariantsTemp[answerID].picture = "resources/"+questionID+"/"+resourceRef.name;
						VariantsTemp[answerID].picture_ref = resourceRef;
						control_img = "imgP" + answerID.toString();
						break;
					}
				var event:EventCommandFlex = new EventCommandFlex(AllCommands.NEW_FILEUPTIP, control_img);
				CentralComm.app.dispatchEvent(event);
			//	Alert.show("File added! Name: "+resourceRef.name+" size (bytes): " + resourceRef.size);			
			//print();
			
			} catch (err:Error) {
				Alert.show("ERROR: zero-byte file" + err.message);
			}
		}		
		
		private function fileRef_progress(evt:ProgressEvent):void {
			//progressBar.visible = true;
		}
		
		private function print():void {
			for each( var obj:QuestionAnswerTemp in VariantsTemp)
			{
			Alert.show(obj.picture + "," +obj.sound  + "," + obj.video);
			}
		}
		
		public function gowrite():void {
			try{
			//Alert.show(addFileIndex.toString() + "/"+(resourcesToUpload.length - 1).toString());
			if(addFileIndex < resourcesToUpload.length - 1)
			{
        	addFileIndex++;          	
        	if((resourcesToUpload.getItemAt(addFileIndex) == null) && (addFileIndex < resourcesToUpload.length- 1) )
        	{
        	while(resourcesToUpload.getItemAt(addFileIndex) == null && (addFileIndex < resourcesToUpload.length- 1))
        	{
        		addFileIndex++;
        	//	Alert.show("adun:" + addFileIndex.toString());
        	}
        	//Alert.show("al1:" +addFileIndex.toString());
        	writeResourcesToServer(addFileIndex);
        	}
        	else if((resourcesToUpload.getItemAt(addFileIndex) != null) && (addFileIndex <= resourcesToUpload.length- 1) )
        	{        		
        		writeResourcesToServer(addFileIndex);
        	}
        	}	
   			if((addFileIndex == resourcesToUpload.length - 1) && (sent == false))
			{   				
   				var event:EventCommandFlex = new EventCommandFlex(AllCommands.UPLOAD_COMPLETE, "");
				CentralComm.app.dispatchEvent(event);	
				sent = true;
				//Alert.show("Gata UPL" + addFileIndex + "/"+ resourcesToUpload.length.toString() );			
   			}
   			
   			}		
			 catch (err:Error) {
				Alert.show("go write:" + err.message);
			}
			//progressBar.visible = false;
		}

		private function fileRef_complete(evt:Event):void {
        try{
        	gowrite();        	
			} catch (err:Error) {
				Alert.show("send compete err:" + err.message);
			}
			//progressBar.visible = false;
		}

		private function fileRef_IOError(evt:IOErrorEvent):void {
			//message.text = "(IOError) " + evt.text + " target: "+ evt.target +" currentTarget: "+evt.currentTarget;
			//progressBar.visible = false;
		}
				
		public function setFilesToUpload(filesToUpload:ArrayCollection):void
		{
			this.resourcesToUpload = resourcesToUpload;		
		}
		public function getFilesToUpload():ArrayCollection
		{
			return this.resourcesToUpload;		
		}
		
/* 		public function setQuestionPaths(questionPaths:StructureQuestionParam):void
		{
			this.questionPaths = questionPaths;		
		}
		public function getQuestionPaths():StructureQuestionParam
		{
			return this.questionPaths;		
		}	 */		
	
		public function getTypes():Array {
       			 var allTypes:Array = new Array();
       			 allTypes.push(new FileFilter("Images (*.jpg, *.jpeg, *.gif, *.png)", "*.jpg;*.jpeg;*.gif;*.png"));
       			 allTypes.push(new FileFilter("Video (*.avi;*.mpeg;*.mpg;*.flv)", "*.avi;*.mpeg;*.mpg;*.flv"));           		
       			 allTypes.push(new FileFilter("Audio (*.mp3)", "*.mp3"));
        		 return allTypes;
   		}			
	}
}