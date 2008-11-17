import fr.semantic_learning.semquiz.valueobjects.StructureGroups;
import fr.semantic_learning.semquiz.valueobjects.StructureStudent;

import mx.controls.Alert;

private function init():void {
	// TODO: Remove this on release
	
	
	// groups
	var gTemp1:StructureGroups = new StructureGroups();
	gTemp1.id = "SQDSQDFKQDSKFLFLSDFLSD";
	gTemp1.title = "M1";

	var gTemp2:StructureGroups = new StructureGroups();
	gTemp2.id = "ZEOJDSSQLDJFZEFNKKSDQFK";
	gTemp2.title = "M2";

	var gTemp3:StructureGroups = new StructureGroups();
	gTemp3.id = "EFSJDLDSQFZEIRJFSKDQFKMLSD";
	gTemp3.title = "L3";
	
	var gTemp:Array = new Array(gTemp1, gTemp2, gTemp3);
	
	groups.dataProvider = gTemp;

	// students	
	var sTemp1:StructureStudent = new StructureStudent();
	sTemp1.idUser = "ZORFSDLFSDFOEZRFSD";
	//sTemp1.idGroup = "none";
	sTemp1.familyName = "Rus";
	sTemp1.givenName = "Ilie";
	
	var sTemp2:StructureStudent = new StructureStudent();
	sTemp2.idUser = "QSDFLJZQIOERFKNSQKSQD";
	//sTemp2.idGroup = "none";
	sTemp2.familyName = "Yo";
	sTemp2.givenName = "Li";
	
	var sTemp3:StructureStudent = new StructureStudent();
	sTemp3.idUser = "OEFDSLKFQSKFKFKDSD";
	//sTemp3.idGroup = "none";
	sTemp3.familyName = "Kauff";
	sTemp3.givenName = "Funny";
	
	var sTemp4:StructureStudent = new StructureStudent();
	sTemp4.idUser = "QDFLSDLFSDMFKEORKVVNDSFK";
	//sTemp4.idGroup = "SQDSQDFKQDSKFLFLSDFLSD";
	sTemp4.familyName = "Crisan";
	sTemp4.givenName = "Radu";
	
	var sTemp:Array = new Array(sTemp1, sTemp2, sTemp3, sTemp4);
	
	students.dataProvider = sTemp;
}

private function setGroupsLabel(item:StructureGroups):String {
	return item.title;
}

private function setStatusLabel(item:StructureStudent):String {
	return item.familyName + " " + item.givenName;
} 

private function setStudentsLabel(item:StructureStudent):String {
	return item.familyName + " " + item.givenName;
} 

private function getStudentsFromGroup(item:StructureGroups):void {
	// TODO: Get Students from selected group 
}