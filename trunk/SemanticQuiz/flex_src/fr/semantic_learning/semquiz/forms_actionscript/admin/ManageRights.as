import fr.semantic_learning.events.EventCommandFlex;
import fr.semantic_learning.semquiz.CentralComm;
import fr.semantic_learning.semquiz.commands.AllCommands;
import fr.semantic_learning.semquiz.valueobjects.StructureInterfaceRights;
import fr.semantic_learning.semquiz.valueobjects.StructureUser;
import fr.semantic_learning.semquiz.valueobjects.StructureUserRights;

import mx.controls.Alert;
import mx.core.Application;

private var all_rights:Array; 

private function init():void {
	CentralComm.app = Application.application as main;

	// Listen SELECT_USER
	this.addEventListener(AllCommands.SELECT_USER, fillUsers);
	this.addEventListener(AllCommands.SELECT_RIGHTS, setRightsForUser);
	this.addEventListener(AllCommands.SELECT_ALL_RIGHTS, setAllRights);
}

private function fillUsers(event:EventCommandFlex):void {	
	users.dataProvider = event.args as Array;
	
	/*var iDesc:Array = new Array();	
	for each (var item:String in FormsInterfaceRights.FIR_ALL_DESC) {
		iDesc.push(item);
	}
	
	rights.dataProvider = iDesc; */
	status.dataProvider = new Array();
}

private function setAllRights(event:EventCommandFlex):void {
	rights.dataProvider = event.args as Array;
}

private function sendRightsForUser():void {
	try {	
		// TODO: dataProvider null stuff
		if (users.selectedItems.length > 0) {
			if (currentrights.dataProvider.length == 0) {
				if (status.dataProvider.length > 0) {
					var sUR:StructureUserRights = new StructureUserRights();
		
					for each (var item:StructureInterfaceRights in status.dataProvider) 
					{						
						var sIR:StructureInterfaceRights = new StructureInterfaceRights();
						sIR.id = item.id;						
						sIR.isAllowed = true;
					
						sUR.rights.push(sIR);
					}
				
					sUR.idUser = users.selectedItem.id; 
					
					var event:EventCommandFlex = new EventCommandFlex(AllCommands.NEW_USER_RIGHTS, sUR);
					CentralComm.app.dispatchEvent(event);
				} else {
					Alert.show("There is no right to set!");	
				}
			} else {
				Alert.show("In this version you can't add rights to the user!");
			}
		} else {
			Alert.show("Select the user first!");
		}
	} catch (e:Error) {
		Alert.show(e.message);
	}
}

private function setRightsForUser(event:EventCommandFlex):void {	
	if ((event.args as Array).length > 0) {
		var rFU:Array = new Array();
		
		for each (var item:StructureInterfaceRights in event.args) {
			if (item.isAllowed) {
				rFU.push(item.description);				
			}
		}
		currentrights.dataProvider = rFU;
	} else {
		currentrights.dataProvider = new Array();		
	}
}

private function getRightsForUser():void {
	var event:EventCommandFlex = new EventCommandFlex(AllCommands.SELECT_RIGHTS, users.selectedItem.id);
	CentralComm.app.dispatchEvent(event);
}

private function setUsersLabel(item:StructureUser):String {	
	return item.familyName + " (" + item.userName + ")";
}

private function setRightsLabel(item:StructureInterfaceRights):String {	
	return item.description;
}
