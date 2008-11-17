package fr.semantic_learning.semquiz.utils
{
	import mx.controls.List;
	import mx.controls.*;
	import mx.controls.listClasses.IListItemRenderer;
	import mx.core.mx_internal;
	import mx.events.*;
	import mx.collections.ListCollectionView;
	import flash.events.MouseEvent;

	
	public class DragSelectList extends List
	{
		private var dragSelecting:Boolean = false;
		private var dragStartItem:IListItemRenderer;
		private var dragStartIndex:int;
		private var lastDragIndex:int;
		
		override protected function selectItem(item:IListItemRenderer, shiftKey:Boolean, ctrlKey:Boolean, transition:Boolean=true):Boolean
		{
			if(dragSelecting)
			{
				return super.selectItem(item, shiftKey, ctrlKey, transition);
			}
			else
			{
				if(shiftKey)
				{
					return super.selectItem(item, true, false, transition);
				}
				else
				{
					return super.selectItem(item, false, true, transition);
				}
			}
		}
		override protected function mouseDownHandler(event:MouseEvent):void
		{
			dragStartItem = mouseEventToItemRenderer(event);
			var alreadySelected:Boolean = dragStartItem && isItemSelected(dragStartItem.data);
			super.mouseDownHandler(event);
			if(dragStartItem && !alreadySelected)
			{
				dragStartIndex = itemRendererToIndex(dragStartItem);
				lastDragIndex = dragStartIndex;			
				dragSelecting = true;
			}
			else
			{
				dragSelecting = false;
			}
		}
		override protected function mouseUpHandler(event:MouseEvent):void
		{
			super.mouseUpHandler(event);
			//resetDragScrolling();
			dragSelecting = false;
		}
		override protected function mouseMoveHandler(event:MouseEvent):void
		{
			if(dragSelecting)
			{
				var oldDragEnabled:Boolean = dragEnabled;
				dragEnabled = false;
				super.mouseMoveHandler(event);
				dragEnabled = oldDragEnabled;
			}
			else
			{
				super.mouseMoveHandler(event);
			}
			if(!dragSelecting || !dragStartItem) return;
			var item:IListItemRenderer = mouseEventToItemRenderer(event);
			if(item && item != dragStartItem)
			{
				var index:int = itemRendererToIndex(item);
				var i:int;
				if(index < dragStartIndex)
				{
					for(i=index; i<=dragStartIndex; i++)
					{
						selectItemAt(i);
					}
					if(index > lastDragIndex)
					{
						for(i=lastDragIndex; i<index; i++)
						{
							deselectItemAt(i);
						}
					}
				}
				else
				{
					for(i=dragStartIndex + 1; i<=index; i++)
					{
						selectItemAt(i);
					}
					if(index < lastDragIndex)
					{
						for(i=index+1; i<=lastDragIndex; i++)
						{
							deselectItemAt(i);
						}
					}
				}
				lastDragIndex = index;
			}
			else if(item && item == dragStartItem)
			{
				if(lastDragIndex != itemRendererToIndex(item))
				{
					deselectItemAt(lastDragIndex);
				}
			}
			dragScroll();
		}
		private function selectItemAt(index:int):void 
		{
			var curItem:IListItemRenderer = indexToItemRenderer(index);
			if(curItem != null)
			{
				if(!isItemSelected(curItem.data))
				{
					super.selectItem(curItem, false, true, false);
				}
			}
		}
		private function deselectItemAt(index:int):void
		{
			var curItem:IListItemRenderer = indexToItemRenderer(index);
			if(curItem != null)
			{
				if(isItemSelected(curItem.data))
				{
					super.selectItem(curItem, false, true, false);
				}
			}
			else
			{
				selectedData[itemToUID(dataProvider[index])] = undefined;
			}
		}
	}

}