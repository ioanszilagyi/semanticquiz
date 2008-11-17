package fr.semantic_learning.semquiz.valueobjects
{
	[Bindable]
	[RemoteClass(alias="fr.semantic_learning.semquiz.valueobjects.StructureUnits")]
	
	public class StructureUnits
	{
		public var id:String;
		public var codUnit:String;
		
		public var title:String;
		public var unitType:String;  //unitatea poate fi de tip facultativ, obligatoriu sau optional
		public var idSection:String;  // array cu codul in care se incadreaza cursul (se trimite un array
							//cu stringuri cu valori intre 01-99 (de ex. pt info codul e 27))
		
		public var credits:String; //creditele asociate unitatii
		public var idUser:String; // referinta spre user-ul prof ce e responsabil de unitate
		public var learningType:String; //poate avea una din valorile: attendence, online, attendence and online
		public var idMention:String; // referinta spre RDF mentions
		public var idSpecialization:Array = new Array();// StructureSpecialization, bag cu referinta la specializari
		public var idParcours:Array = new Array();// StructureParcours, bag cu referinta la parcursuri
		public var idSemester:String; // care e semestrul in care se face unitatea
		public var idYear:String; // anul in functie de formatiune
		public var idCompetence:Array = new Array();//structureCompetence, care sunt competentele pe care trebuie sa le dobandeasca stud
		public var idPrerequists:Array = new Array();//structureCompetence, care sunt cerintele minime pt unitate (de fapt is tot competente)
		public var objectivs:String; // un text ce descrie obiectivele unitatii
		
		public function StructureUnits(title:String=null, codUnit:String=null, unitType:String=null, idUser:String=null, idSection:String= null, idMention:String=null, idSpecializations:Array=null, idParcours:Array=null,idPrerequists:Array=null, idCompetence:Array=null)
		{
			this.title = title;
			this.codUnit = codUnit;
			this.unitType = unitType;
			this.idUser = idUser;
			this.idSection = idSection;
			this.idMention = idMention;
			this.idSpecialization = idSpecializations;
			this.idParcours = idParcours;
			this.idPrerequists = idPrerequists;
			this.idParcours = idParcours;
		}

	}
}