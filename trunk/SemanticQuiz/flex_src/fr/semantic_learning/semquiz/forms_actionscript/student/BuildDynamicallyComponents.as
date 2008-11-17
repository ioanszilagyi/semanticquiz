package fr.semantic_learning.semquiz.forms_actionscript.student
{
	import flash.events.MouseEvent;
	
	import fr.semantic_learning.events.EventCommandFlex;
	import fr.semantic_learning.semquiz.CentralComm;
	import fr.semantic_learning.semquiz.commands.AllCommands;
	import fr.semantic_learning.semquiz.valueobjects.StructureQuestion;
	import fr.semantic_learning.semquiz.valueobjects.StructureQuestionVariants;
	
	import mx.containers.ControlBar;
	import mx.containers.HBox;
	import mx.containers.Panel;
	import mx.containers.VBox;
	import mx.controls.Alert;
	import mx.controls.Button;
	import mx.controls.CheckBox;
	import mx.controls.Image;
	import mx.controls.Label;
	import mx.controls.Text;
	import mx.core.UIComponent;
	
	public final class BuildDynamicallyComponents
	{		
		private var idNextQuestion:String;
		private var theVQuestion:VBox;
		private var theQuestion:StructureQuestion;
		private var rQuiz:UIComponent;
		private var showAnswers:Boolean;

		public function BuildDynamicallyComponents(rQuiz:UIComponent, theQuestion:StructureQuestion, showAnswers:Boolean, idNextQuestion:String) {
			this.rQuiz = rQuiz;
			this.theQuestion = theQuestion;
			this.showAnswers = showAnswers;
			this.idNextQuestion = idNextQuestion;			
			//Alert.show(idNextQuestion);
			
			BuildSpecificQuestion();
		}

		private function BuildSpecificQuestion():void {
			try {
				//Alert.show(theQuestion.type.toString());
				//switch (theQuestion.type) {
					//case "Multiple Choice":
						
						theVQuestion = CreateVBox(100, 100, "top", "left");
					
						CreateQuestionTitle(theVQuestion, 100, "middle");
											
						CreateLabel(theVQuestion, '<font size="16" color="#FFFFF"><b>Choose your answers:</b></font>');
						
						for (var i:Number=0; i<theQuestion.variants.length; i++) {											
							CreateQuestionAnswerVarinat(theVQuestion, 100, "middle", "<b>"+(i+1).toString()+".</b>", (theQuestion.variants[i] as StructureQuestionVariants));  
						} 
						
						rQuiz.addChild(theVQuestion);						
						
						//Alert.show("rQuiz: "+theVQuestion.toString());
						theVQuestion.visible = true;
						
						if (!showAnswers) {					
							var nextBtn:Button = CreateButton(theVQuestion, "Next Question");
							nextBtn.addEventListener(MouseEvent.CLICK, nextQuestion);
						}
					//break;
				//}
			} catch (e:Error) {
				Alert.show(e.message);
			}
		}
		
		private function nextQuestion(e:MouseEvent):void {	
			if (idNextQuestion != null) {	
				// send event to start the quiz
				var event:EventCommandFlex = new EventCommandFlex(AllCommands.SELECT_QUESTIONS, idNextQuestion);
				CentralComm.app.dispatchEvent(event);
			}
		}  

		private function CreateQuestionTitle(parent:VBox, width:Number, verticalAlign:String):void {
			try {
				//Alert.show(theQuestion.text);
			var newHBox:HBox = CreateHBox(width, verticalAlign);
			var newHTMLText:Text = CreateHTMLText(theQuestion.text);
			
			newHBox.addChild(newHTMLText);
			
			if (theQuestion.picture != null) { 
				var newImageButton_Pic:BuildImageButtons = new BuildImageButtons("isPicture", theQuestion.picture, theVQuestion, rQuiz, showAnswers);				
				newImageButton_Pic.addListenerAndSetSource();
				
				newHBox.addChild(newImageButton_Pic);
			} 
			
			if (theQuestion.video != null) {  
				var newImageButton_Mov:BuildImageButtons = new BuildImageButtons("isVideo", theQuestion.video, theVQuestion, rQuiz, showAnswers);				
				newImageButton_Mov.addListenerAndSetSource();
				
				newHBox.addChild(newImageButton_Mov);
			}
			
			if (theQuestion.sound != null) {
				var newImageButton_Sou:BuildImageButtons = new BuildImageButtons("isSound", theQuestion.sound, theVQuestion, rQuiz, showAnswers);				
				newImageButton_Sou.addListenerAndSetSource();
				
				newHBox.addChild(newImageButton_Sou);
			}
	
			parent.addChild(newHBox);	
			} catch (e:Error) {
				Alert.show(e.message);
			}	
		} 
		
		public function CreateQuestionAnswerVarinat(parent:VBox, width:Number, verticalAlign:String, textHTMLNumber:String, theVariant:StructureQuestionVariants):void {
			try {
				//Alert.show(theVariant.text);
			var newHBox:HBox = CreateHBox(width, verticalAlign);
			var newHTMLTextNumber:Text = CreateHTMLText(textHTMLNumber);
			var newHTMLText:Text = CreateHTMLText(theVariant.text);
			var newCheckBox:CheckBox = CreateCheckBox(textHTMLNumber, showAnswers, theVariant);
			
			newHBox.addChild(newHTMLTextNumber);
			newHBox.addChild(newHTMLText);
			newHBox.addChild(newCheckBox);
			
			if (theVariant.picture != null) { 
				var newImageButton_Pic:BuildImageButtons = new BuildImageButtons("isPicture", theVariant.picture, theVQuestion, rQuiz, showAnswers);				
				newImageButton_Pic.addListenerAndSetSource();
						
				newHBox.addChild(newImageButton_Pic);
			} 
			
			if (theVariant.video != null) {  
				var newImageButton_Mov:BuildImageButtons = new BuildImageButtons("isVideo", theVariant.video, theVQuestion, rQuiz, showAnswers);				
				newImageButton_Mov.addListenerAndSetSource();
				
				newHBox.addChild(newImageButton_Mov);
			}
			
			if (theVariant.sound != null) {
				var newImageButton_Sou:BuildImageButtons = new BuildImageButtons("isSound", theVariant.sound, theVQuestion, rQuiz, showAnswers);				
				newImageButton_Sou.addListenerAndSetSource();
				
				newHBox.addChild(newImageButton_Sou);
			}

			parent.addChild(newHBox);
			//Alert.show("dez");
			} catch (e:Error) {
				Alert.show(e.message);
			}		
		}
		
		/**
		 * This function will create a Label dinamically
		 * 
		 * @param parent
		 * @param text
		 * 
		 */
		public function CreateLabel(parent:VBox, text:String):void {
			var newLabel:Label = new Label();
			newLabel.htmlText = text;
			 	
			parent.addChild(newLabel);
		}
		
		public static function CreatePanel(parent:UIComponent, visible:Boolean):Panel {
			var newPanel:Panel = new Panel();
			newPanel.visible = visible; 
			
			parent.addChild(newPanel);
			
			return newPanel;
		}
		
		public static function CreateControlBar(parent:UIComponent):ControlBar {
			var newControlBar:ControlBar = new ControlBar();
			
			parent.addChild(newControlBar);
			
			return newControlBar;
		}
		
		public static function CreateUIComponent(parent:UIComponent):UIComponent {
			var newUIComponent:UIComponent = new UIComponent();
			
			parent.addChild(newUIComponent);
			
			return newUIComponent;
		}
		
		/**
		 * This function will create a CheckBox dinamically and will return it
		 *  
		 * @param idNumber
		 * @return 
		 * 
		 */		
		public function CreateCheckBox(idNumber:String, showAnswer:Boolean, theVariant:StructureQuestionVariants):CheckBox {
			var newCheckBox:CheckBox = new CheckBox();
			newCheckBox.id = "ch_"+idNumber;			
			if (showAnswer) {
				newCheckBox.selected = theVariant.validation; 
				newCheckBox.enabled = false;
			}

			return newCheckBox;
		}
		
		/**
		 * This function will create a HBox dinamically and will return it
		 * 
		 * @param width
		 * @param verticalAlign
		 * @return HBox
		 * 
		 */		
		public function CreateHBox(width:Number, verticalAlign:String):HBox {
			var newHBox:HBox = new HBox();
			newHBox.percentWidth = width;
			newHBox.setStyle("verticalAlign", verticalAlign);
			
			return newHBox;			
		}
		
		 /**
		 * This function will create a VBox dinamically and will return it
		 * 
		 * @param width
		 * @param verticalAlign
		 * @return VBox
		 * 
		 */		
		public static function CreateVBox(width:Number, height:Number, vAlign:String, hAlign:String):VBox {
			var newVBox:VBox = new VBox();
			newVBox.percentWidth = width;
			newVBox.percentHeight = height;
			newVBox.setStyle("verticalAlign", vAlign);
			newVBox.setStyle("horizontalAlign", hAlign);
			
			return newVBox;			
		}
		
		/**
		 * This function will create a Text dinamically and will return it
		 * 
		 * @param color
		 * @param textHTML
		 * @return Text
		 * 
		 */		
		public function CreateHTMLText(textHTML:String):Text {
			var newHTMLText:Text = new Text();
			try {
			
			newHTMLText.htmlText = textHTML;
			
			return newHTMLText;
			} catch (e:Error) {
				Alert.show(e.message);
			}
			return newHTMLText;
		}
		
		public static function CreateButton(parent:UIComponent, label:String):Button {
			var newButton:Button = new Button();
			newButton.label = label; 
			
			parent.addChild(newButton);
			
			return newButton;
		}
		
		public static function CreateImage(parent:UIComponent):Image {
			var newImage:Image = new Image();
			
			parent.addChild(newImage);
			
			return newImage;
		}
	}
}