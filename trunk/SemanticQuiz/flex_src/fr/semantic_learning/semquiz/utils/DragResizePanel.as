package fr.semantic_learning.semquiz.utils
{
	import mx.controls.RichTextEditor;
	import mx.controls.Alert;
	import mx.containers.Panel;
	import mx.core.UIComponent;
	import mx.core.SpriteAsset;
	import mx.events.FlexEvent;
	import flash.events.MouseEvent;
	import flash.events.Event;
	
	public class DragResizePanel extends RichTextEditor
	{
		public function DragResizePanel()
		{
			try{
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, creationCompleteHandler);
			} catch (err:Error) {
				Alert.show("super" + err.message);
			}
		}
		
		private var myTitleBar:UIComponent;
					
		private function creationCompleteHandler(event:Event):void
		{
		try{	
		
			myTitleBar = titleBar;			
			// Add the resizing event handler.	
			addEventListener(MouseEvent.MOUSE_DOWN, resizeHandler);
			myTitleBar.addEventListener(MouseEvent.MOUSE_DOWN, tbMouseDownHandler);
			myTitleBar.addEventListener(MouseEvent.MOUSE_UP, tbMouseUpHandler);
			} catch (err:Error) {
				//Alert.show("creationCompleteHandler" + err.message);
			}
			
		}
		
		private var xOff:Number;
       	private var yOff:Number;
            
		private function tbMouseDownHandler(event:MouseEvent):void {
			try{
                xOff = event.currentTarget.mouseX;
                yOff = event.currentTarget.mouseY;
                parent.addEventListener(MouseEvent.MOUSE_MOVE, tbMouseMoveHandler);
                parent.setChildIndex(this,parent.numChildren-1); 
              } catch (err:Error) {
				//Alert.show("tbMouseDownHandler" + err.message);
			}  
            }
            
       private function tbMouseMoveHandler(event:MouseEvent):void {
			try{
			// Compensate for the mouse pointer's location in the title bar.
			
			var tempX:int = parent.mouseX - xOff;
			x = tempX;
			
			var tempY:int = parent.mouseY - yOff;
			y = tempY;	
			} catch (err:Error) {
				//Alert.show("tbMouseMoveHandler" + err.message);
			}		
        }
        
        private function tbMouseUpHandler(event:MouseEvent):void {
         try{

           parent.removeEventListener(MouseEvent.MOUSE_MOVE, tbMouseMoveHandler);    
        } catch (err:Error) {
				//Alert.show("tbMouseUpHandler" + err.message);
			}
        }
		
		
		

		protected var minShape:SpriteAsset;
		protected var restoreShape:SpriteAsset;

		override protected function createChildren():void
		{
			try{
		
				super.createChildren();
			
			// Create the SpriteAsset's for the min/restore icons and 
			// add the event handlers for them.
			minShape = new SpriteAsset();
			minShape.addEventListener(MouseEvent.MOUSE_DOWN, minPanelSizeHandler);
			titleBar.addChild(minShape);

			restoreShape = new SpriteAsset();
			restoreShape.addEventListener(MouseEvent.MOUSE_DOWN, restorePanelSizeHandler);
			titleBar.addChild(restoreShape);
			} catch (err:Error) {
				//Alert.show("createChildren" + err.message);
			}
		}
			
		override protected function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void
		{			
		try{
			super.updateDisplayList(unscaledWidth, unscaledHeight);
			
			// Create invisible rectangle to increase the hit area of the min icon.
			minShape.graphics.clear();
			minShape.graphics.lineStyle(0, 0, 0);
			minShape.graphics.beginFill(0xFFFFFF, 0.0);
			minShape.graphics.drawRect(unscaledWidth - 35, 12, 8, 8);

			// Draw min icon.
			minShape.graphics.lineStyle(2);
			minShape.graphics.beginFill(0xFFFFFF, 0.0);
			minShape.graphics.drawRect(unscaledWidth - 35, 18, 8, 2);
				
			// Draw restore icon.
			/* restoreShape.graphics.clear();
			restoreShape.graphics.lineStyle(2);
			restoreShape.graphics.beginFill(0xFFFFFF, 0.0);
			restoreShape.graphics.drawRect(unscaledWidth - 20, 12, 8, 8);
			restoreShape.graphics.moveTo(unscaledWidth - 20, 15);
			restoreShape.graphics.lineTo(unscaledWidth - 12, 15); */
			
			// Draw restore icon.
			restoreShape.graphics.clear();
			restoreShape.graphics.lineStyle(2);
			restoreShape.graphics.beginFill(0xFF0000, 0.0);
			restoreShape.graphics.drawRect(unscaledWidth - 20, 12, 8, 8);
			restoreShape.graphics.moveTo(unscaledWidth - 20, 15);
			restoreShape.graphics.lineTo(unscaledWidth - 12, 15);

			// Draw resize graphics if not minimzed.				
			graphics.clear()
			if (isMinimized == false)
			{
				graphics.lineStyle(2);
				graphics.moveTo(unscaledWidth - 6, unscaledHeight - 1)
				graphics.curveTo(unscaledWidth - 3, unscaledHeight - 3, unscaledWidth - 1, unscaledHeight - 6);						
				graphics.moveTo(unscaledWidth - 6, unscaledHeight - 4)
				graphics.curveTo(unscaledWidth - 5, unscaledHeight - 5, unscaledWidth - 4, unscaledHeight - 6);						
			}
			} catch (err:Error) {
				//Alert.show("updateDisplayList" + err.message);
			}
		}
					
		private var myRestoreHeight:int;
		private var isMinimized:Boolean = false; 
					
		// Minimize panel event handler.
		private function minPanelSizeHandler(event:Event):void
		{
			try{
		
			if (isMinimized != true)
			{
				myRestoreHeight = height;	
				height = titleBar.height;
				isMinimized = true;	
				// Don't allow resizing when in the minimized state.
				removeEventListener(MouseEvent.MOUSE_DOWN, resizeHandler);
			}
			} catch (err:Error) {
				//Alert.show("minPanelSizeHandler" + err.message);
			}				
		}
		
		// Restore panel event handler.
		private function restorePanelSizeHandler(event:Event):void
		{
			try{
		
			if (isMinimized == true)
			{
				height = myRestoreHeight;
				isMinimized = false;	
				// Allow resizing in restored state.				
				addEventListener(MouseEvent.MOUSE_DOWN, resizeHandler);
			}
			} catch (err:Error) {
				//Alert.show("restorePanelSizeHandler" + err.message);
			}
		}

		// Define static constant for event type.
		//public static const RESIZE_CLICK:String = "resizeClick";

		// Resize panel event handler.
		private var origWidth:int;
		private var origHeight:int;
		
		public  function resizeHandler(event:MouseEvent):void
		{
			try{
		
			// Determine if the mouse pointer is in the lower right 7x7 pixel
			// area of the panel. Initiate the resize if so.
			
			// Lower left corner of panel
			var lowerLeftX:Number = x + width; 
			var lowerLeftY:Number = y + height;
				
			// Upper left corner of 7x7 hit area
			var upperLeftX:Number = lowerLeftX-7;
			var upperLeftY:Number = lowerLeftY-7;
				
			// Mouse positionin Canvas
			var panelRelX:Number = event.localX + x;
			var panelRelY:Number = event.localY + y;

			// See if the mousedown is in the lower right 7x7 pixel area
			// of the panel.
			if (upperLeftX <= panelRelX && panelRelX <= lowerLeftX)
			{
				if (upperLeftY <= panelRelY && panelRelY <= lowerLeftY)
				{	
				
					
					event.stopPropagation();		
					
					origWidth = width;
					origHeight = height;
					xOff = parent.mouseX;
                	yOff = parent.mouseY;
					parent.addEventListener(MouseEvent.MOUSE_MOVE, resizePanel);
					parent.addEventListener(MouseEvent.MOUSE_UP, stopResizePanel);
					
				}
			}
			} catch (err:Error) {
				//Alert.show("resizeHandler" + err.message);
			}				
		}
		
		private function resizePanel(event:MouseEvent):void {
			try{	
			
			if ((origWidth + (parent.mouseX - xOff)) > 250){
				width = origWidth + (parent.mouseX - xOff);	
			}
			
			if ((origHeight + (parent.mouseY - yOff)) > titleBar.height){
				height = origHeight + (parent.mouseY - yOff);
			}
			} catch (err:Error) {
				//Alert.show("resizePanel" + err.message);
			}
					
		}
		
		private function stopResizePanel(event:MouseEvent):void {
			try{
			parent.removeEventListener(MouseEvent.MOUSE_MOVE, resizePanel);
			} catch (err:Error) {
				//Alert.show("stopResizePanel" + err.message);
			}
		}

	}
}