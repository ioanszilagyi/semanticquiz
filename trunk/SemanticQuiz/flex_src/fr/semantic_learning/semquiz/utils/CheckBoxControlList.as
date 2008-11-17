package fr.semantic_learning.semquiz.utils
{
	import mx.controls.Alert;
	import mx.controls.CheckBox;
	import mx.controls.listClasses.BaseListData;
	import mx.controls.listClasses.ListBase;
	import mx.events.ListEvent;
	
	public class CheckBoxControlList extends CheckBox
	{
		override public function initialize():void
		{
			try {
				super.initialize();
				this.mouseEnabled = false;
			} catch(e:Error)	{				
			}
		}
		override public function set listData(value:BaseListData):void
		{	
			try {	
				super.listData = value;
				this.selected = (listData.owner as ListBase).isItemSelected(listData.uid);
				(value.owner as ListBase).addEventListener(ListEvent.CHANGE, listEventHandler);
			} catch(e:Error)	{			
			}
		}
		private function listEventHandler(event:ListEvent):void
		{
			try {
				this.selected = (listData.owner as ListBase).isItemSelected(listData.uid);
			} catch(e:Error)	{		
			}
		}
	}
}