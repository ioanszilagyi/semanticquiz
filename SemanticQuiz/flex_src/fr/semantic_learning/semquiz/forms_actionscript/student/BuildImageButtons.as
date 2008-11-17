package fr.semantic_learning.semquiz.forms_actionscript.student
{
	import flash.display.Bitmap;
	import flash.display.BitmapData;
	import flash.display.Loader;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.geom.Matrix;
	import flash.media.Sound;
	import flash.media.SoundChannel;
	import flash.media.Video;
	import flash.net.NetConnection;
	import flash.net.NetStream;
	import flash.net.URLRequest;
	
	import fr.semantic_learning.semquiz.CentralComm;
	
	import mx.containers.ControlBar;
	import mx.containers.Panel;
	import mx.containers.TitleWindow;
	import mx.containers.VBox;
	import mx.controls.Alert;
	import mx.controls.Button;
	import mx.controls.Image;
	import mx.core.UIComponent;
	
	public class BuildImageButtons extends Image
	{	
		// for sound
		private var sound:Sound;
		private var sc:SoundChannel;
		private var isPlaying:Boolean;
		private var atPosition:Number;
		
		// for video
		private var video:Video;
		private var nc:NetConnection;
		private var ns:NetStream;
		
		private var theType:String;
		private var theResource:String;
		private var theVQuestion:VBox;
		private var theMultiMediaVBox:VBox;
		private var rQuiz:UIComponent;
		
		// direct useful components
		private var mul_Panel:Panel;
		private var mul_VideoUICom:UIComponent;
		private var mul_VideoPlayPause:Button;
		private var mul_VideoRewind:Button;
		private var mul_VideoStopAndExitVideo:Button;
		
		// 
		private var mul_SoundPlayPause:Button;
		private var mul_SoundRewind:Button;
		private var mul_SoundStopAndExitVideo:Button;
		
		//
		private var mul_Picture:Image;
		private var mul_PictureStopAndExitVideo:Button;
		
		private var original:BitmapData;
	
		private static const MAX_WIDTH:uint = 600;
		private static var MAX_HEIGHT:uint = 600;		
	
		private var showAnswers:Boolean;
		
		public function BuildImageButtons(theType:String, theResource:String, theVQuestion:VBox, rQuiz:UIComponent, showAnswers:Boolean) {
			try {
				super();
			
				this.theType = theType;				
				this.theResource = theResource;
				this.theVQuestion = theVQuestion;
				this.rQuiz = rQuiz;
				this.showAnswers = showAnswers;	
			
				theMultiMediaVBox = CreatePanelSupport();
				rQuiz.addChild(theMultiMediaVBox);
			} catch (e:Error) {
				Alert.show(e.message);
			}
		}

		public function addListenerAndSetSource():void {
			switch (theType) {
				case "isPicture":
					this.source = "img/image.png";
					this.addEventListener(MouseEvent.CLICK, showPicture); 
				break;
				case "isVideo":
					this.source = "img/movie.png";
					this.addEventListener(MouseEvent.CLICK, playVideo);
				break;
				case "isSound":
					this.source = "img/music.png";
					this.addEventListener(MouseEvent.CLICK, playSound);
				break;
			} 
		}

		private function pauseVideo(e:MouseEvent):void {
			ns.togglePause();
		}
		
		private function rewindVideo(e:MouseEvent):void {
			ns.seek(0); 
			ns.pause();
		}
		
		public function stopAndExitVideo(e:MouseEvent):void {
			try {	
				ns.pause();
				video = null;
				nc = null;
				ns = null;
			
				mul_Panel.visible = false;
			
				if (showAnswers) {
					(rQuiz as TitleWindow).showCloseButton = true;
					(rQuiz as TitleWindow).enabled = true;
				} else {
					CentralComm.app.homeBtnBar.visible = true;
					CentralComm.app.appSignOutBtn.visible = true;
				}
			
				theVQuestion.visible = true;
			} catch (e:Error){
				Alert.show(e.message);
			}				
		}
				
		private function playVideo(e:MouseEvent):void {
			try {
				if (!mul_Panel.visible) {				
					var nsClient:Object = {};
					nsClient.onMetaData = ns_onMetaData;
					nsClient.onCuePoint = ns_onCuePoint;
					nc = new NetConnection();
					nc.connect(null);	
					ns = new NetStream(nc);
					ns.play(theResource); // play this video
					ns.client = nsClient;
					video = new Video();
					video.attachNetStream(ns);				
					mul_VideoUICom.addChild(video);
				} else {
					mul_Panel.visible = false;
				}
			} catch (e:Error){
				Alert.show(e.message);
			}
		}
		
		private function ns_onMetaData(item:Object):void {
			try {
				trace("meta");
				var meta:Object = item;
				
				// Resize Video object to same size as meta data.
				video.width = item.width;
				video.height = item.height;

				// Resize UIComponent to same size as Video object.
				mul_VideoUICom.width = video.width;
				mul_VideoUICom.height = video.height;

				mul_Panel.title = "framerate: " + item.framerate;
			
				mul_Panel.visible = true;
			
				if (showAnswers) {
					(rQuiz as TitleWindow).showCloseButton = false;
					(rQuiz as TitleWindow).enabled = true;
				} else {
					CentralComm.app.homeBtnBar.visible = false;
					CentralComm.app.appSignOutBtn.visible = false;
				}
			
				theVQuestion.visible = false;
			} catch (e:Error){
				Alert.show(e.message);
			}					
		}

		private function ns_onCuePoint(item:Object):void {
			trace("cue");
		}
		
		private function playSound(e:MouseEvent):void {
			try {
				sound = new Sound();
				sound.load(new URLRequest(theResource));	
				sc = sound.play();
				sc.soundTransform.volume = 1;
			
				isPlaying = true;
			
				mul_Panel.visible = true;
		
				if (showAnswers) {
					(rQuiz as TitleWindow).showCloseButton = false;
					(rQuiz as TitleWindow).enabled = true;
				} else {
					CentralComm.app.homeBtnBar.visible = false;
					CentralComm.app.appSignOutBtn.visible = false;
				}
			
				theVQuestion.visible = false;
			} catch (e:Error){
				Alert.show(e.message);
			}			
		}
		
		private function pauseSound(e:MouseEvent):void {
			if (isPlaying) {				
				sc.stop();
				
				atPosition = sc.position;
				isPlaying = false;
			} else {
				sc = sound.play(atPosition);
				
				isPlaying = true;
			} 			
		}
		
		private function rewindSound(e:MouseEvent):void {
			sc.stop();
			
			isPlaying = false;
			atPosition = 0;
		}
		
		public function stopAndExitSound(e:MouseEvent):void {
			try {
				sc.stop();
				sound = null;
				sc = null;
			
				mul_Panel.visible = false;
			
				if (showAnswers) {
					(rQuiz as TitleWindow).showCloseButton = true;
					(rQuiz as TitleWindow).enabled = true;
				} else {
					CentralComm.app.homeBtnBar.visible = true;
					CentralComm.app.appSignOutBtn.visible = true;
				}
			
				theVQuestion.visible = true;
			} catch (e:Error) {
	 			Alert.show(e.message);		
 			}	
		}
		
		private function showPicture(e:MouseEvent):void {
			try {
				loadImage(theResource);	
			} catch (e:Error) {
	 			Alert.show(e.message);		
 			}		
		}
		
		private function loadImage(url:String):void
		{
			try {
				//Alert.show(url);
	 			var request:URLRequest = new URLRequest(url);
 
				var imageLoader:Loader = new Loader();
 				imageLoader.contentLoaderInfo.addEventListener(Event.COMPLETE, image_completeHandler);
 			
	 			// add other listeners here
 				imageLoader.load(request)
 			} catch (e:Error) {
	 			Alert.show(e.message);		
 			}
		}
			
		private function image_completeHandler(event:Event):void {
			try {
	 			var bmd:BitmapData = Bitmap(event.currentTarget.content).bitmapData;
 				
 				var originalWidth:Number = bmd.width;
 				var originalHeight:Number = bmd.height;
 				var newWidth:Number = originalWidth;
	 			var newHeight:Number = originalHeight;
 			
 				var m:Matrix = new Matrix();
 			
 				var scaleX:Number = 1;
	 			var scaleY:Number = 1;
 
 				if (originalWidth > MAX_WIDTH || originalHeight > MAX_HEIGHT) {
  					var sx:Number =  MAX_WIDTH / originalWidth;
	  				var sy:Number = MAX_HEIGHT / originalHeight;
  					var scale:Number = Math.min(sx, sy);
  					newWidth = originalWidth * scale;
	  				newHeight = originalHeight * scale;	
  				}
 			
 				m.scale(scale, scale);
	 			original = new BitmapData( newWidth , newHeight); 
 				
 				original.draw(bmd, m);	
 			
 				mul_Picture.graphics.clear();
 				mul_Picture.graphics.beginBitmapFill(original);                                
    	        mul_Picture.graphics.drawRect(0,0, original.width, original.height); 
	 			
 				mul_Picture.width = original.width;
				mul_Picture.height = original.height;
 			
 				mul_Panel.visible = true;
			
				if (showAnswers) {
					(rQuiz as TitleWindow).showCloseButton = false;
					(rQuiz as TitleWindow).enabled = true;
				} else {
					CentralComm.app.homeBtnBar.visible = false;
					CentralComm.app.appSignOutBtn.visible = false;
				}
			
				theVQuestion.visible = false;	
			} catch (e:Error){
				Alert.show(e.message);
			}
		}
		
		public function stopAndExitPicture(e:MouseEvent):void {
			try {
				mul_Panel.visible = false;
			
				if (showAnswers) {
					(rQuiz as TitleWindow).showCloseButton = true;
					(rQuiz as TitleWindow).enabled = true;
				} else {
					CentralComm.app.homeBtnBar.visible = true;
					CentralComm.app.appSignOutBtn.visible = true;
				}
			
				theVQuestion.visible = true;
			} catch (e:Error){
				Alert.show(e.message);
			}
		}
		
		private function CreatePanelSupport():VBox {
			var newVBox:VBox = BuildDynamicallyComponents.CreateVBox(100, 100, "middle", "center");
			try {
				//Alert.show("CreatePanelSupport");
				mul_Panel = BuildDynamicallyComponents.CreatePanel(newVBox, false);
			
				switch (theType) {
					case "isPicture":
						mul_Picture = BuildDynamicallyComponents.CreateImage(mul_Panel);
					
						mul_PictureStopAndExitVideo = BuildDynamicallyComponents.CreateButton(mul_Panel, "Exit");
					
						mul_PictureStopAndExitVideo.addEventListener(MouseEvent.CLICK, stopAndExitPicture);
					break;
					case "isVideo":					
						mul_VideoUICom = BuildDynamicallyComponents.CreateUIComponent(mul_Panel);
						var newCB:ControlBar = BuildDynamicallyComponents.CreateControlBar(mul_Panel);
			
			 			mul_VideoPlayPause = BuildDynamicallyComponents.CreateButton(newCB, "Play/Pause");
						mul_VideoRewind = BuildDynamicallyComponents.CreateButton(newCB, "Rewind");
						mul_VideoStopAndExitVideo = BuildDynamicallyComponents.CreateButton(newCB, "Exit");		
			
						mul_VideoPlayPause.addEventListener(MouseEvent.CLICK, pauseVideo);
						mul_VideoRewind.addEventListener(MouseEvent.CLICK, rewindVideo);
						mul_VideoStopAndExitVideo.addEventListener(MouseEvent.CLICK, stopAndExitVideo);
					break;
					case "isSound":					
						mul_SoundPlayPause = BuildDynamicallyComponents.CreateButton(mul_Panel, "Play/Pause");
						mul_SoundRewind = BuildDynamicallyComponents.CreateButton(mul_Panel, "Rewind");
						mul_SoundStopAndExitVideo = BuildDynamicallyComponents.CreateButton(mul_Panel, "Exit");
					
						mul_SoundPlayPause.addEventListener(MouseEvent.CLICK, pauseSound);
						mul_SoundRewind.addEventListener(MouseEvent.CLICK, rewindSound);
						mul_SoundStopAndExitVideo.addEventListener(MouseEvent.CLICK, stopAndExitSound);
					break;
				} 
			} catch (e:Error){
				Alert.show(e.message);
			}
			return newVBox;
		}
	}
}