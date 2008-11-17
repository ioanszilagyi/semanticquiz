package fr.semantic_learning.semquiz.commands
{


	public final class AllCommands
	{	
		// Display Interface Forms Commands
		public static const LOGIN_SCREEN:Number = 1;	
		public static const HOME_ADMIN:Number = 2;
		public static const HOME_SECRETAR:Number = 3;
		public static const HOME_PROF:Number = 4;
		public static const HOME_STUDENT:Number = 5;
		public static const CREATE_QUESTION:Number = 6;
		public static const CREATE_MULTIPLECH:Number = 7;
		public static const CREATE_SINGLECH:Number = 8;
		public static const CREATE_QUIZ:Number = 9;
	
		// Events Commands
		public static const LOGIN_RESPONSE:String = "LOGIN_RESPONSE";
		public static const LOGIN_REQUEST:String = "LOGIN_REQUEST";	
		public static const LOGIN_RIGHTS:String = "LOGIN_RIGHTS";

		//NEW.....		
		public static const NEW_SUBSECTION:String = "NEW_SUBSECTION";
		public static const NEW_FORMATION:String = "NEW_FORMATION";
		public static const NEW_USER:String = "NEW_USER";
		public static const NEW_USER_RIGHTS:String = "NEW_USER_RIGHTS";
		public static const NEW_MENTION:String = "NEW_MENTION";
		public static const NEW_DOMAIN:String = "NEW_DOMAIN";
		public static const NEW_SPECIALIZATION:String = "NEW_SPECIALIZATION";
		public static const NEW_PARCOURS:String = "NEW_PARCOURS";
		public static const NEW_COMPETENCE:String = "NEW_COMPETENCE";
		public static const NEW_UNIT:String = "NEW_UNIT";
		public static const NEW_QUESTION:String = "NEW_QUESTION";
		public static const NEW_QUIZ:String = "NEW_QUIZ";
		public static const NEW_FILEUPTIP:String = "NEW_FILEUPTIP";
		public static const NEW_STUDENT_DETAILS:String = "NEW_STUDENT_DETAILS";
		public static const NEW_PENDING_Q:String = "NEW_PENDING_Q";
	
		//GET and RETURN, SELECT.....
		public static const SELECT_RIGHTS:String = "SELECT_RIGHTS";
		public static const SELECT_ALL_RIGHTS:String = "SELECT_ALL_RIGHTS";
		
		public static const SELECT_PENDING_QUIZZES:String = "SELECT_PENDING_QUIZZES";
		public static const SELECT_QUIZ_QUESTIONS:String = "SELECT_QUIZ_QUESTIONS";
		public static const SELECT_QUESTIONS:String = "SELECT_QUESTIONS";
		public static const SELECT_ALL_QUIZ:String = "SELECT_ALL_QUIZ";
		
		public static const GET_SECTIONS:String = "GET_SECTIONS";
		public static const RETURN_SECTIONS:String = "RETURN_SECTIONS";
		public static const SELECT_SECTION:String = "SELECT_SECTION";
		
		public static const GET_SUBSECTIONS:String = "GET_SUBSECTIONS";
		public static const RETURN_SUBSECTIONS:String = "RETURN_SUBSECTIONS";
		public static const SELECT_SUBSECTION:String = "SELECT_SUBSECTION";
		
		
		public static const GET_FORMATIONS:String = "GET_FORMATIONS";
		public static const RETURN_FORMATIONS:String = "RETURN_FORMATIONS";
		public static const SELECT_FORMATION:String = "SELECT_FORMATION";
		
		public static const GET_USER:String = "GET_USER";
		public static const RETURN_USER:String = "RETURN_USER";
		public static const SELECT_USER:String = "SELECT_USER";
		
		public static const SELECT_RESPONSABLE:String = "SELECT_RESPONSABLE";

		public static const GET_MENTIONS:String = "GET_MENTIONS";
		public static const RETURN_MENTIONS:String = "RETURN_MENTIONS";
		public static const SELECT_MENTION:String = "SELECT_MENTION";			
		
		public static const GET_DOMAINS:String = "GET_DOMAINS";
		public static const RETURN_DOMAINS:String = "RETURN_DOMAINS";
		public static const SELECT_DOMAIN:String = "SELECT_DOMAIN";
		
		public static const GET_SPECIALIZATIONS:String = "GET_SPECIALIZATIONS";
		public static const RETURN_SPECIALIZATIONS:String = "RETURN_SPECIALIZATIONRS";
		public static const SELECT_SPECIALIZATION:String = "SELECT_SPECIALIZATION";			
		
		public static const GET_PARCOURS:String = "GET_PARCOURS";
		public static const RETURN_PARCOURS:String = "RETURN_PARCOURS";
		public static const SELECT_PARCOURS:String = "SELECT_PARCOURS";			
				
		public static const GET_COMPETENCES:String = "GET_COMPETENCES";
		public static const RETURN_COMPETENCES:String = "RETURN_COMPETENCES";
		public static const SELECT_COMPETENCE:String = "SELECT_COMPETENCE";
				
		public static const GET_UNITS:String = "GET_UNITS";
		public static const RETURN_UNITS:String = "RETURN_UNITS";
		public static const SELECT_UNIT:String = "SELECT_UNIT";	
		
		public static const SELECT_EVALUATIONTYPE:String = "SELECT_EVALUATIONTYPE";
		public static const SELECT_SEMESTER:String = "SELECT_SEMESTER";
		public static const SELECT_YEAR:String = "SELECT_YEAR";	
		

		
		//INFO FLEX to FLEX		
	    public static const UPLOAD_COMPLETE:String = "UPLOAD_COMPLETE";
	    public static const STUDENT_START_QUIZ:String = "STUDENT_START_QUIZ";
	    public static const STUDENT_NEXT_QUESTION:String = "STUDENT_NEXT_QUESTION";
		
		//INFO from java
		public static const RDF_SAVED_IN_BD:String = "RDF_SAVED_IN_BD";	
		public static const SUBSECTION_QUESTIONS:String = "SUBSECTION_QUESTIONS";

	}
}