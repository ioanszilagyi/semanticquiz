import fr.semantic_learning.events.EventCommandFlex;
import fr.semantic_learning.semquiz.CentralComm;
import fr.semantic_learning.semquiz.commands.AllCommands;
import fr.semantic_learning.semquiz.valueobjects.StructureMention;
import fr.semantic_learning.semquiz.valueobjects.StructureParcours;
import fr.semantic_learning.semquiz.valueobjects.StructureSpecialization;

import mx.core.Application;

private function init():void {
	CentralComm.app = Application.application as main;

	// Listen SELECT_USER
	this.addEventListener(AllCommands.SELECT_MENTION, setMentions);
	this.addEventListener(AllCommands.SELECT_SPECIALIZATION, setSpecialization);
	this.addEventListener(AllCommands.SELECT_PARCOURS, setParcours);
}

private function setMentions(event:EventCommandFlex):void {
	mention.dataProvider = event.args as Array;
}

private function setSpecialization(event:EventCommandFlex):void {
	specialization.dataProvider = event.args as Array;
}

private function setParcours(event:EventCommandFlex):void {
	parcours.dataProvider = event.args as Array;
}

private function getLabelParcours(item:StructureParcours):String {
	return item.title;
}

private function getLabelSpecialization(item:StructureSpecialization):String {
	return item.title;
}

private function getLabelMention(item:StructureMention):String {
	return item.title;
}