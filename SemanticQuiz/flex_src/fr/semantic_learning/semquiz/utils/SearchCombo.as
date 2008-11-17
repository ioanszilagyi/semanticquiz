package fr.semantic_learning.semquiz.utils
{
  import mx.controls.ComboBox;
  import flash.events.Event;
  import mx.collections.ArrayCollection;
  import flash.events.FocusEvent;

	public class SearchCombo extends ComboBox
	{
    	private var _trackText:String = "";
   		private var _caseSensitiveSearching : Boolean = true;
 
    	public function SearchCombo()
    	{
    		super();      
    	}
    
    	public function set caseSensitiveSearching (bool : Boolean) : void 
    	{
    		_caseSensitiveSearching = bool;      
    	}
    
    	public function get caseSensitiveSearching () : Boolean
    	{
    		return _caseSensitiveSearching;      
    	}
    
    	override protected function textInput_changeHandler(event:Event):void 
    	{
    		_trackText = this.textInput.text;
        
        	this.open();

        	var label : String = null;
        	var matchingIdx : int = 0;
        	var foundMatch : Boolean = false;
        	var searchString : String = this.textInput.text;
        
        	if ( caseSensitiveSearching == false )
        	{
        		searchString = searchString.toLowerCase();
        	}
	    	for each ( var item : Object in this.dataProvider ) 
        	{
        		label = this.itemToLabel( item );
          		if ( this.caseSensitiveSearching == false )
          		{
            		label = label.toLowerCase();
          		}
				if ( label.substr( 0, searchString.length ) == searchString )
				{
          			this.dropdown.selectedIndex = matchingIdx;
          			this.dropdown.scrollToIndex( matchingIdx );
          			foundMatch = true
          			break;
        		}
        		matchingIdx++;
    		}
    		if ( foundMatch == false ) 
    		{
      			this.dropdown.selectedIndex = -1;
    		}
  		}
    
    	override public function close(trigger:Event=null):void
    	{ 
      		super.close(trigger);
      		if (this.text == "")
      		{
        		this.selectedIndex = 0;
      		}
		}	
	}
}