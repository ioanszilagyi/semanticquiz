 //TODO: comenzile pt RDF-urile noi
package fr.semantic_learning.semquiz;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import com.hp.hpl.jena.db.DBConnection;
import com.hp.hpl.jena.db.IDBConnection;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Bag;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ModelMaker;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DC;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.VCARD;

import fr.semantic_learning.events.*;
import fr.semantic_learning.semquiz.commands.AllCommands;
import fr.semantic_learning.semquiz.commands.RDFModels;
import fr.semantic_learning.semquiz.valueobjects.*;
import fr.semantic_learning.Utils;

/**
 * @author emma
 *
 * Comments your class here !
 */
public class RDFConstruction extends ReadWriteRDFDB implements EventCommandSeenListner {
	private EventCommandSeenListner subscribe;
	
	// When the constructor is called the event will be ready for use
	public RDFConstruction(EventCommandSeenListner ecsl) {
		// to send commands 
		subscribe = ecsl;
	}
	
	//a public enumeration of the existing commands
	private String NEW_QUESTION="NEW_QUESTION";
	private String NEW_USER="NEW_USER";
	private String NEW_COMPETENCE="NEW_COMPETENCE";
	private String NEW_QUIZ="NEW_QUIZ";
	private String Com;
	private String NEW_SUBSECTION="NEW_SUBSECTION";
	private String NEW_FORMATION="NEW_FORMATION";
	private String NEW_MENTION="NEW_MENTION";
	private String NEW_SPECIALIZATION="NEW_SPECIALIZATION";
	private String NEW_PARCOURS="NEW_PARCOURS";
	private String NEW_UNIT="NEW_UNIT";
	private String NEW_GROUP="NEW_GROUP";
	private String NEW_USER_RIGHTS="NEW_USER_RIGHTS";
	private String NEW_STUDENT_DETAILS="NEW_STUDENT_DETAILS";
	private String NEW_PROF_DETAILS="NEW_PROF_DETAILS";
	private String NEW_PENDING_Q = "NEW_PENDING_Q";
	
	private Vector <String>cap_tabel=new Vector<String>();
	
	
	private enum commands {NEW_QUESTION,
							NEW_USER,
							NEW_COMPETENCE,
							NEW_QUIZ,
							NEW_FORMATION,
							NEW_SUBSECTION,
							NEW_MENTION,
							NEW_SPECIALIZATION,
							NEW_PARCOURS,
							NEW_UNIT,
							NEW_GROUP,
							NEW_USER_RIGHTS,
							NEW_STUDENT_DETAILS,
							NEW_PROF_DETAILS,
							NEW_PENDING_Q};
	
	
	public void eventCommandSeen(EventCommandEvent ece) 
	{
		Com = ece.command;	
		commands Comanda = null;
		switch(Comanda.valueOf(Com))
		{
		case NEW_QUESTION:
			StructureQuestion x =((StructureQuestion)ece.args);
			String model = RDFModels.questionModel.getCommand();
			System.out.println(createQuestionRDF(x, ece.clientID,model));
			break;
		case NEW_USER:
			String mod=RDFModels.userModel.getCommand();
			StructureUser nu = ((StructureUser)ece.args);
			System.out.println(createUserRDF(nu, ece.clientID,mod));
			break;
		case NEW_SUBSECTION:
			StructureSubsection ns = ((StructureSubsection)ece.args);
			String modss = RDFModels.subsectionModel.getCommand();
			System.out.println(createSubsectionRDF(ns, ece.clientID,modss));
			break;
		case NEW_FORMATION:
			StructureFormation nf=((StructureFormation)ece.args);
			String mf = RDFModels.formationModel.getCommand();
			System.out.println(createFormationRDF(nf,ece.clientID, mf));
			break;
		case NEW_MENTION:
			StructureMention nm = ((StructureMention)ece.args);
			String mm = RDFModels.mentionModel.getCommand();
			System.out.println(createMentionRDF(nm,ece.clientID,mm));
			break;
		case NEW_SPECIALIZATION:
			StructureSpecialization nspec = ((StructureSpecialization)ece.args);
			String ms=RDFModels.specializationModel.getCommand();
			System.out.println(createSpecializationRDF(nspec,ece.clientID,ms));
			break;
		case NEW_PARCOURS:
			String mp = RDFModels.parcoursModel.getCommand();
			StructureParcours np = ((StructureParcours)ece.args);
			System.out.println(createParcoursRDF(np,ece.clientID,mp));
			break;
		case NEW_UNIT:
			String mu = RDFModels.unitsModel.getCommand();
			StructureUnits nunit = ((StructureUnits)ece.args);
			System.out.println(createUnitsRDF(nunit,ece.clientID,mu));
			break;
		case NEW_QUIZ:
			String mq = RDFModels.quizModel.getCommand();
			StructureQuiz pq = ((StructureQuiz)ece.args);
			System.out.println(createQuizRDF(pq,ece.clientID,mq));
			break;	
		case NEW_COMPETENCE:
			String mc = RDFModels.competenceModel.getCommand();
			StructureCompetence ncomp = ((StructureCompetence)ece.args);
			System.out.println(createCompetencesRDF(ncomp,ece.clientID,mc));
			break;		
		case NEW_GROUP:
			String mg = RDFModels.groupModel.getCommand();
			StructureGroups ngroup = ((StructureGroups)ece.args);
			System.out.println(createGroupRDF(ngroup,ece.clientID,mg));
			break;	
		case NEW_USER_RIGHTS:
			String mur = RDFModels.userRightsModel.getCommand();
			StructureUserRights nuserrights = ((StructureUserRights)ece.args);
			System.out.println(createUserRightsRDF(nuserrights,ece.clientID,mur));
			break;
		case NEW_STUDENT_DETAILS:
			String msd = RDFModels.studentModel.getCommand();
			StructureStudent nstudent = ((StructureStudent)ece.args);
			System.out.println(createStudentRDF(nstudent,ece.clientID, msd));
			break;	
		case NEW_PROF_DETAILS:
			String mpd = RDFModels.profModel.getCommand();
			StructureProf nprof = ((StructureProf)ece.args);
			System.out.println(createProfRDF(nprof,ece.clientID,mpd));
			break;
		case NEW_PENDING_Q:
			String mpq = RDFModels.pendingQuizzesModel.getCommand();
			StructurePendingQuizzes npq = ((StructurePendingQuizzes)ece.args);
			System.out.println(createPendingQuizzesRDF(npq,ece.clientID,mpq));
			break;	
		}
	}

	public String createVariantRDF(StructureQuestionVariants pv,String clientID,String mod)
	{
		
		try
		{	
				StringWriter Mout = new StringWriter();
				
				// Create database connection
		
				String className = "com.mysql.jdbc.Driver"; // path of driver class
				Class.forName(className); // Load the Driver

				IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER, Utils.DB_PASSWD,Utils.DB);
		
				ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
			
				// create an empty model
				Model model = maker.createModel(mod);
			
				//Model model = ModelFactory.createDefaultModel();
				String namespaceSQuiz = "http://194.57.85.190/namespaces/export/squiz#";
				String base = "http://194.57.85.190/qvariants.rdf#";
				
				model.setNsPrefix("squiz", namespaceSQuiz);
				model.setNsPrefix("base", "http://194.57.85.190/qvariants.rdf");
			
				//String resource = base+Utils.GetUniqueID(pv.getId());
				Resource Variant = model.createResource(pv.getId());
				Variant.addProperty(RDF.type, model.createResource("http://194.57.85.190/namespaces/export/squiz#Variant"));
					
				if (pv.getText()!=null)
				{
					Variant.addProperty(model.createProperty(model.expandPrefix("squiz:text")), pv.getText());
				}
				
				if (pv.getValidation()!=null)
				{
					Variant.addProperty(model.createProperty(model.expandPrefix("squiz:valid")), pv.getValidation().toString());
				}
				if (pv.getPicture()!=null)
				{
					Variant.addProperty(model.createProperty(model.expandPrefix("squiz:picture")), pv.getPicture());
				}
				if (pv.getSound()!=null)
				{
					Variant.addProperty(model.createProperty(model.expandPrefix("squiz:sound")), pv.getSound());
				}
				if (pv.getVideo()!=null)
				{
					Variant.addProperty(model.createProperty(model.expandPrefix("squiz:video")), pv.getVideo());
				}
			
				model.write(Mout, "RDF/XML-ABBREV");
				return Mout.toString();
				//subscribe.eventCommandSeen(new EventCommandEvent(this, "RDF Saved in DB", pv, clientID));
				//return "RDFConstruction! RDF Saved in DB";
			} 
		catch (Exception e) 
		{
				System.out.println("There was a problem writing the RDF to the DB");
				return e.toString();
		}	
		
	}
	
	public String createQuestionRDF(			
						StructureQuestion pq,
						String clientID,
						String mod
					)
		
	
	{	
		System.out.println("A intrat in createQuestionRDF");
		System.out.println("Author:"+pq.getAuthor());
		
		String[] comp = pq.getCompetence();
		for(int h =0; h<pq.getCompetence().length; h++)
			System.out.println("Competente:"+comp[h]);
		
		System.out.println("Data:"+pq.getData());
		System.out.println("Subsection: "+pq.getSubsection());
		System.out.println("Evaluationtime:"+pq.getEvaluationTime());
		System.out.println("Type:"+pq.getType());
		System.out.println("Language:"+pq.getLanguage());
		System.out.println("Picture:"+pq.getPicture());
		System.out.println("Sound:"+pq.getSound());
		System.out.println("Text: "+pq.getText());
		System.out.println("Video:"+pq.getVideo());
		System.out.println("EvaluationType:"+pq.getEvaluationType());
		System.out.println("Units:"+pq.getUnits());
		
	/*	StructureQuestionVariants[] var = pq.getVariants();
		for(int k=0;k<var.length;k++)
		{
			System.out.println("Variants id "+k+": "+var[k].getId());
			System.out.println("Variants picture "+k+": "+var[k].getPicture());
			System.out.println("Variants sound "+k+": "+var[k].getSound());
			System.out.println("Variants text "+k+": "+var[k].getText());
			System.out.println("Variants video "+k+": "+var[k].getVideo());
			System.out.println("Variants validare "+k+": "+var[k].getValidation().toString());
			
			
		}
		*/
		
		
		//pt competente
		
		
		String[] c = pq.getCompetence();
		int nr_competence = 0;
		if (c!=null)
		{
			nr_competence = c.length;
		}
		
			
		
		try
		{	
				StringWriter Mout = new StringWriter();
			
				// Create database connection
				
				String className = "com.mysql.jdbc.Driver"; // path of driver class
				Class.forName(className); // Load the Driver

				IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER, Utils.DB_PASSWD,Utils.DB);
				
			    ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
				
			// parte de RDF variante
			
			StructureQuestionVariants[] t;
			t=pq.getVariants();
			
			int nr_variante = 0;
			
			if (t!=null)
			{
				nr_variante= t.length ;
			}
			String[] iduri_variante = new String[nr_variante];
			
			if (t!=null)
			{
				for (int i=0; i<nr_variante;i++)
				{
					String varianta="http://194.57.85.190/qvariants.rdf#"+Utils.GetUniqueID(t[i].getText());
					t[i].setId(varianta);

					iduri_variante[i]=varianta;
					System.out.println("var"+i+":"+iduri_variante[i]);
					String model = RDFModels.variantModel.getCommand();
					System.out.println(createVariantRDF(t[i],clientID,model));
				}
				
				
			}		
			
			//parte de RDF intrebare
				// create an empty model
				Model model = maker.createModel(mod);

			//		Model model = ModelFactory.createDefaultModel();
				String namespaceSQuiz = "http://194.57.85.190/namespaces/export/squiz#";

				// we set the prefix for name-space here
				model.setNsPrefix("squiz", namespaceSQuiz);
				model.setNsPrefix("DCTerms", "http://purl.org/dc/terms/");

				// create head resource question
				String resource = "http://194.57.85.190/questions.rdf#"+Utils.GetUniqueID(pq.getAuthor());
				//System.out.println(resource);
				Resource SQuiz = model.createResource(resource);

				// adding "fixed" properties
				SQuiz.addProperty(RDF.type, model.createResource("http://194.57.85.190/namespaces/export/squiz#Question"));
				
				
				Resource subsect = model.createResource(pq.getSubsection());
				SQuiz.addProperty(model.createProperty(model.expandPrefix("squiz:hasSubsection")), subsect);
				
				SQuiz.addProperty(model.createProperty(model.expandPrefix("squiz:questionType")), pq.getType());
				
				
				SQuiz.addProperty(model.createProperty(model.expandPrefix("squiz:evaluationTime")), pq.getEvaluationTime());
				
				Resource evalType = model.createResource(pq.getEvaluationType());
				SQuiz.addProperty(model.createProperty(model.expandPrefix("squiz:evaluationType")), evalType);
				
				
				
				if (c!=null)
				{
					Bag CompetencesBag = model.createBag();
					SQuiz.addProperty(model.createProperty(model.expandPrefix("squiz:hasCompetences")), CompetencesBag );
					for (int j = 0; j<nr_competence; j++){
						Resource CompLi = model.createResource(c[j]);
						CompetencesBag.add(CompLi);						
					}
					
				}
				
			
				Resource unitati = model.createResource(pq.getUnits());
				SQuiz.addProperty(model.createProperty(model.expandPrefix("squiz:hasUnits")), unitati );
					
				Resource autor= model.createResource(pq.getAuthor());
				SQuiz.addProperty(model.createProperty(model.expandPrefix("squiz:hasAuthor")), autor );
				
				if (pq.getLanguage()!=null)
					SQuiz.addProperty(DC.language, pq.getLanguage());
				else
					SQuiz.addProperty(DC.language, "en");
				
				
				//SQuiz.addProperty(DC.creator, pq.getAuthor()); - nu merge proprietatea pt cazul nostru
				// current date is added
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				java.util.Date date = new java.util.Date();
				String datetime = dateFormat.format(date);
				SQuiz.addProperty(DCTerms.dateSubmitted, datetime);


				// Configuration resources...
				Resource Configuration = model.createResource();
				SQuiz.addProperty(model.createProperty(model.expandPrefix("squiz:hasConfiguration")), Configuration);
				
				// varinat - Bag resource... 
				
				Bag VarinatBag = model.createBag();	
				Configuration.addProperty(model.createProperty(model.expandPrefix("squiz:variant")), VarinatBag);
				
				for (int i=0;i<iduri_variante.length;i++)
				{
					Resource VariantLi = model.createResource(iduri_variante[i]);		
					VarinatBag.add(VariantLi);
				}
				
				if (pq.getText()!=null)
				{
					Configuration.addProperty(model.createProperty(model.expandPrefix("squiz:qtext")), pq.getText());
				}
				if (pq.getPicture()!=null) 
				{
					Configuration.addProperty(model.createProperty(model.expandPrefix("squiz:picture")), pq.getPicture());
				}
				if (pq.getVideo()!=null)
				{
					Configuration.addProperty(model.createProperty(model.expandPrefix("squiz:video")), pq.getVideo());
				}
				if (pq.getSound()!=null)
				{
					Configuration.addProperty(model.createProperty(model.expandPrefix("squiz:sound")), pq.getSound());
				} 
			
				model.write(Mout, "RDF/XML-ABBREV");
				System.out.println(Mout.toString());
				//subscribe.eventCommandSeen(new EventCommandEvent(this, "RDF Saved in DB", pq, clientID));
				subscribe.eventCommandSeen(new EventCommandEvent(this,AllCommands.IS_SAVED.getCommand(),true,clientID));
				
				return "RDFConstruction! RDF Saved in DB";
				
				//model.write(Mout, "RDF/XML-ABBREV");
				//return Mout.toString();

				
	
			} catch (Exception e) {
				
				System.out.println("There was a problem writing the RDF to the DB");
				subscribe.eventCommandSeen(new EventCommandEvent(this,AllCommands.IS_SAVED.getCommand(),false,clientID));
				return e.toString();
				
				}	
				
	}
	
	public  String createUserRDF(			
			StructureUser pu, String clientID,String mod
		)
	{
		
		StringWriter Mout = new StringWriter();		
		try{	
				
			String className = "com.mysql.jdbc.Driver"; // path of driver class
			Class.forName(className); // Load the Driver
			
		
        // create an empty model
			
			IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER, Utils.DB_PASSWD,Utils.DB);
		    ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
			
			// create an empty model
			Model model = maker.createModel(mod);
			
			
		//Model model = ModelFactory.createDefaultModel();
		
        // create the resource
        //   and add the properties cascading style

        String namespaceSQuiz = "http://194.57.85.190/namespaces/export/squiz#";
		String base = "http://194.57.85.190/user.rdf#"; // pu.getGivenName() + pu.getFamilyName();
		// we set the prefix for name-space here
		
		
		String fullName     = pu.getGivenName() + " " + pu.getFamilyName();
			
		model.setNsPrefix("squiz", namespaceSQuiz);
		model.setNsPrefix("dcterms","http://purl.org/dc/terms/");
		model.setNsPrefix("base", "http://194.57.85.190/user.rdf");
		
        Resource User  = model.createResource(base+Utils.GetUniqueID(pu.getGivenName() + pu.getFamilyName()))
             .addProperty(VCARD.FN, fullName)
             .addProperty(VCARD.N, 
                      model.createResource()
                           .addProperty(VCARD.Given, pu.getGivenName())
                           .addProperty(VCARD.Family, pu.getFamilyName()))
             .addProperty(VCARD.EMAIL, pu.getEmail())
             .addProperty(VCARD.TITLE, pu.getTitle())
             .addProperty(VCARD.ROLE, pu.getRole())
             .addProperty(VCARD.ORG, model.createResource()
                      		   .addProperty(VCARD.Orgname, pu.getOrg()))
                      ;
		User.addProperty(RDF.type, model.createResource(namespaceSQuiz+"User"));
        User.addProperty(model.createProperty(model.expandPrefix("squiz:user_name")), pu.getUserName());
        User.addProperty(model.createProperty(model.expandPrefix("squiz:password")), pu.getPassword());
        User.addProperty(model.createProperty(model.expandPrefix("squiz:url")), pu.getUrl());
        User.addProperty(DC.language, "en");
        
		model.write(Mout, "RDF/XML-ABBREV");		
		System.out.println( Mout.toString());
		subscribe.eventCommandSeen(new EventCommandEvent(this, AllCommands.IS_SAVED.getCommand(),true, clientID));
		
		return "RDFConstruction! RDF Saved in DB";

	} catch (Exception e) {
		
		System.out.println("There was a problem writing the RDF to the DB");
		subscribe.eventCommandSeen(new EventCommandEvent(this,AllCommands.IS_SAVED.getCommand(),false,clientID));
		return e.toString();
		
		}
        
	}
	
	public String createCompetencesRDF(StructureCompetence pc, String clientID, String mod)
	{
		StringWriter Mout = new StringWriter();
		
		try{	
			
			String className = "com.mysql.jdbc.Driver"; // path of driver class
			Class.forName(className); // Load the Driver
			
			IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER, Utils.DB_PASSWD,Utils.DB);
		    ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
			
			// create an empty model
			Model model = maker.createModel(mod);
		//Model model = ModelFactory.createDefaultModel();
		
		String namespaceSQuiz = "http://194.57.85.190/namespaces/export/squiz#";
		String base = "http://194.57.85.190/competence.rdf#";
		// we set the prefix for name-space here
		
		
		model.setNsPrefix("squiz", namespaceSQuiz);
		model.setNsPrefix("dcterms","http://purl.org/dc/terms/");
		model.setNsPrefix("base", "http://194.57.85.190/competence.rdf");
		
		
		// create head resource question
		Resource Competence = model.createResource(base+Utils.GetUniqueID(pc.getTitle())); 
		Competence.addProperty(RDF.type, model.createResource("http://194.57.85.190/namespaces/export/squiz#Competence"));
		// adding "fixed" properties
		Competence.addProperty(model.createProperty(model.expandPrefix("squiz:title")),pc.getTitle());
		model.write(Mout, "RDF/XML-ABBREV");
	
		System.out.println(Mout.toString());
		subscribe.eventCommandSeen(new EventCommandEvent(this, AllCommands.IS_SAVED.getCommand(), true, clientID));		
		return "RDFConstruction! RDF Saved in DB";

	} catch (Exception e) {
		
		System.out.println("There was a problem writing the RDF to the DB");
		subscribe.eventCommandSeen(new EventCommandEvent(this, AllCommands.IS_SAVED.getCommand(), false, clientID));
		return e.toString();
		
		}	
	}

	public String createQuizRDF( StructureQuiz pq, String clientID, String mod)
	{
		
		//System.out.println(pq.getDate());
		/*System.out.println(pq.getAuthor());
		System.out.println(pq.getLanguage());
		System.out.println(pq.getTitle());
		
		for(int i=0;i<pq.getQuestions().length;i++)
		{
			System.out.println(pq.getQuestions()[i]);
		}
		
		*/
		StringWriter Mout = new StringWriter();
		
		
		
		// Create database connection
		try{
		
		
		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver

		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER, Utils.DB_PASSWD,Utils.DB);
		
	    ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		
		Model model = maker.createModel(mod);
		
		//Model model = ModelFactory.createDefaultModel();
		
		String namespaceSQuiz = "http://194.57.85.190/namespaces/export/squiz#";
		String base = "http://194.57.85.190/quiz.rdf#";
			// we set the prefix for name-space here
			
				
		model.setNsPrefix("squiz", namespaceSQuiz);
		model.setNsPrefix("dcterms","http://purl.org/dc/terms/");
		model.setNsPrefix("base", "http://194.57.85.190/quiz.rdf");
		
		Resource Quiz = model.createResource(base+Utils.GetUniqueID(pq.getTitle()));
		Quiz.addProperty(RDF.type, model.createResource("http://194.57.85.190/namespaces/export/squiz#Quiz"));
	
		Resource autor = model.createResource(pq.getAuthor());
		Quiz.addProperty(model.createProperty(model.expandPrefix("squiz:hasAuthor")),autor);
		
		
		Quiz.addProperty(DC.title, pq.getTitle());
		if(pq.getLanguage()!=null)
			Quiz.addProperty(DC.language, pq.getLanguage());
		else 
			Quiz.addProperty(DC.language, "en");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		String datetime = dateFormat.format(date);
		Quiz.addProperty(DCTerms.dateSubmitted, datetime);
		
		
		//Resource Question =  model.createResource();
		//Quiz.addProperty(model.createProperty(model.expandPrefix("squiz:questions")), Question);
		Bag questionBag = model.createBag();
		Quiz.addProperty(model.createProperty(model.expandPrefix("squiz:hasQuestions")), questionBag);
		
		String[] t = pq.getQuestions();
		
		int nr_intr = t.length;
		for (int i = 0; i< nr_intr; i++) {
			// varinat - Bag - li resources...
			Resource questionsLi = model.createResource(t[i]);		
			questionBag.add(questionsLi);
		//	questionsLi.addProperty(RDF.type,model.createResource(t[i])); 
		}
		
		model.write(Mout, "RDF/XML-ABBREV");
		System.out.println(Mout.toString());
		subscribe.eventCommandSeen(new EventCommandEvent(this, AllCommands.IS_SAVED.getCommand(), true, clientID));		
		return "RDFConstruction! RDF Saved in DB";

	} catch (Exception e) {
		
		System.out.println("There was a problem writing the RDF to the DB");
		subscribe.eventCommandSeen(new EventCommandEvent(this, AllCommands.IS_SAVED.getCommand(), false, clientID));	
		return e.toString();
		
		}	
	}
	
	public String createSubsectionRDF(StructureSubsection pd, String clientID, String mod)
	{
		StringWriter Mout = new StringWriter();
		
		try{	
			
		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver
		
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER, Utils.DB_PASSWD,Utils.DB);
	    ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		
		// create an empty model
		Model model = maker.createModel(mod);
		//Model model = ModelFactory.createDefaultModel();
		
		String namespaceSQuiz = "http://194.57.85.190/namespaces/export/squiz#";
		String base = "http://194.57.85.190/subsections.rdf#";
		// we set the prefix for name-space here
		
		
		model.setNsPrefix("squiz", namespaceSQuiz);
		model.setNsPrefix("dcterms","http://purl.org/dc/terms/");
		model.setNsPrefix("base", "http://194.57.85.190/subsections.rdf");
			
		// create head resource question
		Resource Domain = model.createResource(base+Utils.GetUniqueID(pd.getTitle())); 
		Domain.addProperty(RDF.type, model.createResource("http://194.57.85.190/namespaces/export/squiz#Subsection"));
		// adding "fixed" properties
		Domain.addProperty(model.createProperty(model.expandPrefix("squiz:title")),pd.getTitle());
		
		
		Resource Part = model.createResource(pd.getPartOf());
		Domain.addProperty(DCTerms.isPartOf,Part);
	
		
		model.write(Mout, "RDF/XML");
		System.out.println(Mout.toString());
		subscribe.eventCommandSeen(new EventCommandEvent(this, AllCommands.IS_SAVED.getCommand(), true, clientID));	
			
		return "RDFConstruction! RDF Saved in DB";

	} catch (Exception e) {
		
		System.out.println("There was a problem writing the RDF to the DB");
		subscribe.eventCommandSeen(new EventCommandEvent(this, AllCommands.IS_SAVED.getCommand(), false, clientID));
		return e.toString();
		
		}	
		
		
	}
	
	public static String createDisciplineDomainRDF(StructureDisciplineDomain dd)
	{
		StringWriter Mout = new StringWriter();
		String ddmodel = RDFModels.disciplineDomainModel.getCommand();
		try{	
			
			String className = "com.mysql.jdbc.Driver"; // path of driver class
			Class.forName(className); // Load the Driver
		//	String DB_URL = "jdbc:mysql://194.57.85.190:3306/questionsRDF"; // URL of database
		//	String DB_USER = "leonardo"; // database user id
		//	String DB_PASSWD = "leonardo"; // database password
		//	String DB = "MySQL"; // database type
		

        // create an empty model

			//IDBConnection conn = new DBConnection(DB_URL, DB_USER, DB_PASSWD, DB);
			IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER, Utils.DB_PASSWD, Utils.DB);
			
		    ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
			
			// create an empty model
			Model model = maker.createModel(ddmodel);
		
		
		//Model model = ModelFactory.createDefaultModel();
		
		String namespaceSQuiz = "http://194.57.85.190/namespaces/export/squiz#";
		String base = "http://194.57.85.190/disciplinedomains.rdf#";
		
		model.setNsPrefix("squiz", namespaceSQuiz);
		model.setNsPrefix("dcterms","http://purl.org/dc/terms/");
		model.setNsPrefix("base", "http://194.57.85.190/disciplinedomains.rdf");
		
		Resource DisciplineDomain = model.createResource(base+Utils.GetUniqueID(dd.getTitle()));
		DisciplineDomain.addProperty(RDF.type, model.createResource("http://194.57.85.190/namespaces/export/squiz#DisciplineDomain"));
		DisciplineDomain.addProperty(model.createProperty(model.expandPrefix("squiz:title")),dd.getTitle());
		DisciplineDomain.addProperty(model.createProperty(model.expandPrefix("squiz:code")),dd.getCod());

		
		model.write(Mout, "RDF/XML-ABBREV");
		return Mout.toString();
		
		} catch (Exception e) {
		
			System.out.println("There was a problem writing the RDF to the DB");
			return e.toString();
			
			}
	}
	
	public static String createDisciplineGroupRDF(StructureDisciplineGroup dg, String parte)
	{
		StringWriter Mout = new StringWriter();
		String dgmodel= RDFModels.disciplineGroupModel.getCommand();
		try{	
			
			String className = "com.mysql.jdbc.Driver"; // path of driver class
			Class.forName(className); // Load the Driver
		//	String DB_URL = "jdbc:mysql://194.57.85.190:3306/questionsRDF"; // URL of database
		//	String DB_USER = "leonardo"; // database user id
		//	String DB_PASSWD = "leonardo"; // database password
		//	String DB = "MySQL"; // database type
		

        // create an empty model

			//IDBConnection conn = new DBConnection(DB_URL, DB_USER, DB_PASSWD, DB);
			IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER, Utils.DB_PASSWD, Utils.DB);
		    ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
			
			// create an empty model
			Model model = maker.createModel(dgmodel);
		//Model model = ModelFactory.createDefaultModel();
		
		
		String namespaceSQuiz = "http://194.57.85.190/namespaces/export/squiz#";
		String base = "http://194.57.85.190/disciplinegroup.rdf#";
		
		model.setNsPrefix("squiz", namespaceSQuiz);
		model.setNsPrefix("dcterms","http://purl.org/dc/terms/");
		model.setNsPrefix("base", "http://194.57.85.190/disciplinegroup.rdf");
		
		Resource DisciplineGroup = model.createResource(base+Utils.GetUniqueID(dg.getTitle()));
		DisciplineGroup.addProperty(RDF.type, model.createResource("http://194.57.85.190/namespaces/export/squiz#DisciplineGroup"));
		DisciplineGroup.addProperty(model.createProperty(model.expandPrefix("squiz:title")),dg.getTitle());
		
		Resource Part = model.createResource(parte);
		DisciplineGroup.addProperty(DCTerms.isPartOf,Part);

		
		model.write(Mout, "RDF/XML-ABBREV");
		return Mout.toString();
		} catch (Exception e) {
			
			System.out.println("There was a problem writing the RDF to the DB");
			return e.toString();
			
			}
	}
	
	public static String createDisciplineSectionRDF(StructureDisciplineSection ds, String Parte)
	{
		StringWriter Mout = new StringWriter();
		String dsmodel = RDFModels.sectionModel.getCommand();
		try{	
			
			String className = "com.mysql.jdbc.Driver"; // path of driver class
			Class.forName(className); // Load the Driver
			//String DB_URL = "jdbc:mysql://194.57.85.190:3306/questionsRDF"; // URL of database
			//String DB_USER = "leonardo"; // database user id
			//String DB_PASSWD = "leonardo"; // database password
			//String DB = "MySQL"; // database type
		

        // create an empty model

			//IDBConnection conn = new DBConnection(DB_URL, DB_USER, DB_PASSWD, DB);
			IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER, Utils.DB_PASSWD,Utils.DB);
			
		    ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
			
			// create an empty model
			Model model = maker.createModel(dsmodel);
		
		//Model model = ModelFactory.createDefaultModel();
		
		String namespaceSQuiz = "http://194.57.85.190/namespaces/export/squiz#";
		String base = "http://194.57.85.190/sections.rdf#";
		
		model.setNsPrefix("squiz", namespaceSQuiz);
		model.setNsPrefix("dcterms","http://purl.org/dc/terms/");
		model.setNsPrefix("base", "http://194.57.85.190/sections.rdf");
		
		Resource DisciplineSection = model.createResource(base+Utils.GetUniqueID(ds.getTitle()));
		DisciplineSection.addProperty(RDF.type, model.createResource("http://194.57.85.190/namespaces/export/squiz#Section"));
		DisciplineSection.addProperty(model.createProperty(model.expandPrefix("squiz:title")),ds.getTitle());
		DisciplineSection.addProperty(model.createProperty(model.expandPrefix("squiz:code")), ds.getCod());
		
		Resource Part = model.createResource(Parte);
		DisciplineSection.addProperty(DCTerms.isPartOf,Part);

		
		model.write(Mout, "RDF/XML-ABBREV");
		return Mout.toString();
		} catch (Exception e) {
				
				System.out.println("There was a problem writing the RDF to the DB");
				return e.toString();
	
		}
	}
	
	public String createFormationRDF(StructureFormation sf, String clientID, String mod)
	{
		StringWriter Mout = new StringWriter();
		
		try{
			
			String className = "com.mysql.jdbc.Driver"; // path of driver class
			Class.forName(className); // Load the Driver
			
			IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER, Utils.DB_PASSWD,Utils.DB);
		    ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
			
			// create an empty model
			Model model = maker.createModel(mod);
		
		String ShortTitle = sf.getYearTitle();
		
		//Model model = ModelFactory.createDefaultModel();
		
		String namespaceSQuiz = "http://194.57.85.190/namespaces/export/squiz#";
		String base = "http://194.57.85.190/formations.rdf#";
		
		model.setNsPrefix("squiz", namespaceSQuiz);
		model.setNsPrefix("dcterms","http://purl.org/dc/terms/");
		model.setNsPrefix("base", "http://194.57.85.190/formations.rdf");
		
		String id_formatiune = base+Utils.GetUniqueID(sf.getTitle());
		Resource Formation = model.createResource(id_formatiune);
		Formation.addProperty(RDF.type, model.createResource("http://194.57.85.190/namespaces/export/squiz#Formation"));
		Formation.addProperty(model.createProperty(model.expandPrefix("squiz:title")),sf.getTitle());
		Formation.addProperty(model.createProperty(model.expandPrefix("squiz:nrofYears")), sf.getNum_ani());
				
		model.write(Mout, "RDF/XML-ABBREV");
		System.out.println(Mout.toString());
		
		int num_sem = Integer.parseInt(sf.getNum_ani())*2;
		
		ModelMaker maker2 = ModelFactory.createModelRDBMaker(conn);
		Model model2 = maker2.openModel("semesterModel");

		// Create a new query
		String queryString = 
				  " PREFIX name:<http://194.57.85.190/namespaces/export/squiz#>"
				+ " PREFIX type:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ " PREFIX dc:<http://purl.org/dc/elements/1.1/> "
				+ " SELECT DISTINCT ?id ?title "
				+ " WHERE {"
				+ " ?id type:type ?Semester  ." 
				+ " ?id dc:title ?title  ."
				+ "      }";

		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model2);
		ResultSet results = qe.execSelect();
		
	//ResultSetFormatter.out(System.out, results, query);
		
		QuerySolution s;
		int length=results.getResultVars().size();
		
		int i=0;
			
		//put the name of variables in vector cap_tabel
		for (int j=0; j<length;j++ )
		{
			cap_tabel.add((String)results.getResultVars().get(j));
		}
		
		StructureSemester temp = new StructureSemester();
		String[] semestrele_mele = new String[num_sem];
		while(results.hasNext())//results.getRowNumber()!=0 )
		{
			
			s=results.nextSolution(); 
			
			for (int j=0; j<length;j++ )
			{
				
				if (cap_tabel.get(j).equals("id")){temp.setId(s.getResource("id").toString());					}
				if (cap_tabel.get(j).equals("title")){temp.setTitle(s.getLiteral("title").toString());			}
								
			}
			
			
			//System.out.println(i+": "+temp.getTitle());
			if(i<num_sem)
				semestrele_mele[i] = temp.getId();
		
			i++;
		}
		
		
		qe.close();
		
		String[] titluri_ani = new String[Integer.parseInt(sf.getNum_ani())];
		for (int m=0;m<Integer.parseInt(sf.getNum_ani());m++ )
		{
			titluri_ani[m] = ShortTitle+(m+1);
		}
		int a=0; //pt nr de semestre
		for (int n= 0; n<Integer.parseInt(sf.getNum_ani());n++)
		{
			String[] sem = new String[2];
			sem[0]=semestrele_mele[a];
			sem[1]=semestrele_mele[a+1];
			System.out.println(createYearRDF(titluri_ani[n],sem,id_formatiune));
			a=a+2;
			
		}
		//
		
		
		
		
		
	//	subscribe.eventCommandSeen(new EventCommandEvent(this, AllCommands.IS_SAVED.getCommand(), true, clientID));	
		return "RDFConstruction! RDF Saved in DB";

		} catch (Exception e) {
		
		System.out.println("There was a problem writing the RDF to the DB");
		subscribe.eventCommandSeen(new EventCommandEvent(this, AllCommands.IS_SAVED.getCommand(), false, clientID));
		return e.toString();
		
		}	
	}
	
	public String createMentionRDF(StructureMention sm, String clientID, String mod)
	{
		StringWriter Mout = new StringWriter();
		
		try{	
			
			String className = "com.mysql.jdbc.Driver"; // path of driver class
			Class.forName(className); // Load the Driver
			
			IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER, Utils.DB_PASSWD,Utils.DB);
		    ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
			
			// create an empty model
			Model model = maker.createModel(mod);
		//Model model = ModelFactory.createDefaultModel();
		
		String namespaceSQuiz = "http://194.57.85.190/namespaces/export/squiz#";
		String base = "http://194.57.85.190/mentions.rdf#";
		
		model.setNsPrefix("squiz", namespaceSQuiz);
		model.setNsPrefix("dcterms","http://purl.org/dc/terms/");
		model.setNsPrefix("base", "http://194.57.85.190/mentions.rdf");
		
		Resource Mention = model.createResource(base+Utils.GetUniqueID(sm.getTitle()));
		Mention.addProperty(RDF.type, model.createResource("http://194.57.85.190/namespaces/export/squiz#Mention"));
		Mention.addProperty(model.createProperty(model.expandPrefix("squiz:title")),sm.getTitle());
		//Mention.addProperty(model.createProperty(model.expandPrefix("squiz:code")), sm.getCod());
		
		Resource Part = model.createResource(sm.getIdFormation());
		Mention.addProperty(DCTerms.isPartOf,Part);
		
		Resource Resp = model.createResource(sm.getIdResp());
		Mention.addProperty(model.createProperty(model.expandPrefix("squiz:hasResponsable")),Resp);

		model.write(Mout, "RDF/XML-ABBREV");
		System.out.println(Mout.toString());
		subscribe.eventCommandSeen(new EventCommandEvent(this, AllCommands.IS_SAVED.getCommand(), true, clientID));		
		return "RDFConstruction! RDF Saved in DB";

	} catch (Exception e) {
		
		System.out.println("There was a problem writing the RDF to the DB");
		subscribe.eventCommandSeen(new EventCommandEvent(this, AllCommands.IS_SAVED.getCommand(), false, clientID));
		return e.toString();
		
		}	
		
	}
	
	public String createParcoursRDF(StructureParcours sp, String clientID, String mod)
	{
		
		System.out.println(sp.getIdMention());
		System.out.println(sp.getIdResp());
		System.out.println(sp.getTitle());
		System.out.println(sp.getCod());
		System.out.println(sp.getIdSpecialization());
		
		
		
		StringWriter Mout = new StringWriter();
		try{	
			
			String className = "com.mysql.jdbc.Driver"; // path of driver class
			Class.forName(className); // Load the Driver
			
			IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER, Utils.DB_PASSWD,Utils.DB);
		    ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
			
			// create an empty model
			Model model = maker.createModel(mod);
		
		//Model model = ModelFactory.createDefaultModel();
		
		String namespaceSQuiz = "http://194.57.85.190/namespaces/export/squiz#";
		String base = "http://194.57.85.190/parcours.rdf#";
		
		model.setNsPrefix("squiz", namespaceSQuiz);
		model.setNsPrefix("dcterms","http://purl.org/dc/terms/");
		model.setNsPrefix("base", "http://194.57.85.190/parcours.rdf");
		String idparcours=base+Utils.GetUniqueID(sp.getTitle());
		System.out.println(idparcours);
		
		Resource Parcours = model.createResource(idparcours);
		Parcours.addProperty(RDF.type, model.createResource("http://194.57.85.190/namespaces/export/squiz#Parcours"));
		Parcours.addProperty(model.createProperty(model.expandPrefix("squiz:title")),sp.getTitle());
		Parcours.addProperty(model.createProperty(model.expandPrefix("squiz:code")), sp.getCod());
		
		Resource MentionPart = model.createResource(sp.getIdMention());
		Parcours.addProperty(DCTerms.isPartOf,MentionPart);
		
		//TODO: pe flex: in functie de mentiunea aleasa sa apara doar specializarile din mentiunea respectiva
		//TODO: punere mesaj implicit din flex dupa care sa verific daca am sau nu specializare
		
		
		//System.out.println(sp.getIdSpecialization());
		if (!sp.getIdSpecialization().equals("null"))
		{
			Resource SpecPart = model.createResource(sp.getIdSpecialization());
			Parcours.addProperty(DCTerms.isPartOf,SpecPart);
		}
			
		
		Resource Resp = model.createResource(sp.getIdResp());
		Parcours.addProperty(model.createProperty(model.expandPrefix("squiz:hasResponsable")), Resp);

		model.write(Mout, "RDF/XML-ABBREV");
		System.out.println(Mout.toString());
	//	subscribe.eventCommandSeen(new EventCommandEvent(this, "RDF Saved in DB", sp, clientID));	
		//subscribe.eventCommandSeen(new EventCommandEvent(this, AllCommands.IS_SAVED.getCommand(), true, clientID));
		return "RDFConstruction! RDF Saved in DB";

	} catch (Exception e) {
		
		System.out.println("There was a problem writing the RDF to the DB");
		subscribe.eventCommandSeen(new EventCommandEvent(this, AllCommands.IS_SAVED.getCommand(), false, clientID));
		return e.toString();
		
		}	
		
	}
	
	public String createSpecializationRDF(StructureSpecialization ss, String clientID, String mod)
	{
		StringWriter Mout = new StringWriter();
		try{	
			
			String className = "com.mysql.jdbc.Driver"; // path of driver class
			Class.forName(className); // Load the Driver
			
			IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER, Utils.DB_PASSWD,Utils.DB);
		    ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
			
			// create an empty model
			Model model = maker.createModel(mod);
		//Model model = ModelFactory.createDefaultModel();
		
		String namespaceSQuiz = "http://194.57.85.190/namespaces/export/squiz#";
		String base = "http://194.57.85.190/specializations.rdf#";
		
		model.setNsPrefix("squiz", namespaceSQuiz);
		model.setNsPrefix("dcterms","http://purl.org/dc/terms/");
		model.setNsPrefix("base", "http://194.57.85.190/specializations.rdf");
		
		Resource Specialization = model.createResource(base+Utils.GetUniqueID(ss.getTitle()));
		Specialization.addProperty(RDF.type, model.createResource("http://194.57.85.190/namespaces/export/squiz#Specialization"));
		Specialization.addProperty(model.createProperty(model.expandPrefix("squiz:title")),ss.getTitle());
		Specialization.addProperty(model.createProperty(model.expandPrefix("squiz:code")), ss.getCod());
		
		
		Resource MentionPart = model.createResource(ss.getIdMention());
		Specialization.addProperty(DCTerms.isPartOf,MentionPart);
		
		Resource Resp = model.createResource(ss.getIdResp());
		Specialization.addProperty(model.createProperty(model.expandPrefix("squiz:hasResponsable")), Resp);

		//TODO: de verificat daca se trimite ok mesajul de salvare in BD in flex
		model.write(Mout, "RDF/XML-ABBREV");
		System.out.println(Mout.toString());
		subscribe.eventCommandSeen(new EventCommandEvent(this, AllCommands.IS_SAVED.getCommand(), true, clientID));
		//subscribe.eventCommandSeen(new EventCommandEvent(this, "RDF Saved in DB", ss, clientID));		
		return "RDFConstruction! RDF Saved in DB";

	} catch (Exception e) {
		
		System.out.println("There was a problem writing the RDF to the DB");
		subscribe.eventCommandSeen(new EventCommandEvent(this, AllCommands.IS_SAVED.getCommand(), false, clientID));
		return e.toString();
		
		}	
		
		
	}
	
	public String createUnitsRDF(StructureUnits su, String clientID, String mod)
	{
		
		//------------unitsModel-------------------//
		
	/*	System.out.println(su.getCodUnit());
		System.out.println(su.getCredits());
		System.out.println(su.getIdMention());
		System.out.println(su.getIdSection());
		System.out.println(su.getIdSemester());
		System.out.println(su.getIdUser());
		System.out.println(su.getIdYear());
		System.out.println(su.getLearningType());
		System.out.println(su.getObjectivs());
		System.out.println(su.getTitle());
		System.out.println(su.getUnitType());
		
		String[] s=su.getIdCompetence();
		for(int i=0;i<su.getIdCompetence().length;i++ )
			System.out.println("cOMPETENTE:"+s[i]);
		
		
		String[] s2=su.getIdParcurs();
		if (s2!=null)
		{
			for(int i=0;i<su.getIdParcurs().length;i++ )
				System.out.println("PARCURS"+s2[i]);
		}
		
		String[] s3=su.getIdPrerequists();
		for(int i=0;i<su.getIdPrerequists().length;i++ )
			System.out.println("PREREQUIST:"+s3[i]);
		
		String[] s4=su.getIdSpecialization();
		for(int i=0;i<su.getIdSpecialization().length;i++ )
			System.out.println("sPEC:"+s4[i]);
		
		*/
		
		
		StringWriter Mout = new StringWriter();
		
		try{	
			
			String className = "com.mysql.jdbc.Driver"; // path of driver class
			Class.forName(className); // Load the Driver
			
			IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER, Utils.DB_PASSWD,Utils.DB);
		    ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
			
			// create an empty model
			Model model = maker.createModel(mod);
		
		
	//	Model model = ModelFactory.createDefaultModel();
		
		String namespaceSQuiz = "http://194.57.85.190/namespaces/export/squiz#";
		String base = "http://194.57.85.190/units.rdf#";
		
		model.setNsPrefix("squiz", namespaceSQuiz);
		model.setNsPrefix("dcterms","http://purl.org/dc/terms/");
		model.setNsPrefix("base", "http://194.57.85.190/units.rdf");
		
		Resource Unit = model.createResource(base+Utils.GetUniqueID(su.getTitle()));
		Unit.addProperty(RDF.type, model.createResource("http://194.57.85.190/namespaces/export/squiz#Unit"));
		Unit.addProperty(model.createProperty(model.expandPrefix("squiz:title")),su.getTitle());
		Unit.addProperty(model.createProperty(model.expandPrefix("squiz:code")), su.getCodUnit());
		Unit.addProperty(model.createProperty(model.expandPrefix("squiz:unitType")), su.getUnitType());
		Unit.addProperty(model.createProperty(model.expandPrefix("squiz:credits")), su.getCredits());
		Unit.addProperty(model.createProperty(model.expandPrefix("squiz:learningType")), su.getLearningType());
		
		
		Resource sem = model.createResource(su.getIdSemester());
		Unit.addProperty(model.createProperty(model.expandPrefix("squiz:semester")), sem);
		
		Resource anul = model.createResource(su.getIdYear());
		Unit.addProperty(model.createProperty(model.expandPrefix("squiz:studiedinYear")), anul);
		
		Unit.addProperty(model.createProperty(model.expandPrefix("squiz:objective")), su.getObjectivs());
		
		
		Resource MentionPart = model.createResource(su.getIdMention());
		Unit.addProperty(DCTerms.isPartOf,MentionPart);
		
		
		String[] spec = su.getIdSpecialization();
		if (spec!=null)
		{
			int nr_spec = spec.length;
			Bag SpecBag = model.createBag();
			Unit.addProperty(model.createProperty(model.expandPrefix("squiz:specializations")), SpecBag );
			for (int j = 0; j<nr_spec; j++){
				Resource SpecLi = model.createResource(spec[j]);
				SpecBag.add(SpecLi);
				//SpecLi.addProperty(model.createProperty(model.expandPrefix("squiz:hasSpecialization")), spec[j]);
				
			}
			
		}
		
		String[] parc = su.getIdParcurs();
		if (parc!=null)
		{
			int nr_parc = spec.length;
			Bag ParcBag = model.createBag();
			Unit.addProperty(model.createProperty(model.expandPrefix("squiz:parcours")), ParcBag );
			for (int j = 0; j<nr_parc; j++){
				Resource ParcLi = model.createResource(parc[j]);
				ParcBag.add(ParcLi);
				//ParcLi.addProperty(model.createProperty(model.expandPrefix("squiz:hasSpecialization")), parc[j]);
				
			}
			
		}
	
		Resource RespPart = model.createResource(su.getIdUser());
		Unit.addProperty(model.createProperty(model.expandPrefix("squiz:hasResponsable")), RespPart);

		model.write(Mout, "RDF/XML-ABBREV");
		System.out.println(Mout.toString());
		subscribe.eventCommandSeen(new EventCommandEvent(this, AllCommands.IS_SAVED.getCommand(), true, clientID));	
			
		return "RDFConstruction! RDF Saved in DB";

	} catch (Exception e) {
		
		System.out.println("There was a problem writing the RDF to the DB");
		subscribe.eventCommandSeen(new EventCommandEvent(this, AllCommands.IS_SAVED.getCommand(), false, clientID));
		return e.toString();
		
		}	
	}
	
	public String createGroupRDF(StructureGroups sg, String clientID,String mod)
	{
		//-------- groupModel ----------------
		
		StringWriter Mout = new StringWriter();
		Model model = ModelFactory.createDefaultModel();
		
		String namespaceSQuiz = "http://194.57.85.190/namespaces/export/squiz#";
		String base = "http://194.57.85.190/groups.rdf#";
		
		model.setNsPrefix("squiz", namespaceSQuiz);
		model.setNsPrefix("base", "http://194.57.85.190/groups.rdf");
		
		Resource Group = model.createResource(base+Utils.GetUniqueID(sg.getTitle()));
		Group.addProperty(RDF.type, model.createResource("http://194.57.85.190/namespaces/export/squiz#Group"));
		Group.addProperty(DC.title,sg.getTitle());
		
		if(sg.getIdMention()!=null)
			Group.addProperty(DCTerms.isPartOf, sg.getIdMention());
		
		if(sg.getIdParcours()!=null)
			Group.addProperty(DCTerms.isPartOf, sg.getIdParcours());
		
		if(sg.getIdSpec()!=null)
			Group.addProperty(DCTerms.isPartOf, sg.getIdSpec());
		
		Group.addProperty(DCTerms.isPartOf,sg.getIdYear());
		Group.addProperty(DC.description, sg.getDescription());
		
		model.write(Mout, "RDF/XML-ABBREV");
		return Mout.toString();
	}
	
	public String createPendingQuizzesRDF(StructurePendingQuizzes spq, String clientID, String mod)
	{
		// ------------ pendingQuizzesModel -------------
		
	/*	System.out.println("userul:"+spq.getIdUser());
		
		String[] idquiz= spq.getIdQuiz();
		for (int i=0;i<spq.getIdQuiz().length;i++)
			System.out.println("quiz"+i+":"+idquiz[i]);
		*/
		
		StringWriter Mout = new StringWriter();
		
		try{	
			
			String className = "com.mysql.jdbc.Driver"; // path of driver class
			Class.forName(className); // Load the Driver
			
			IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER, Utils.DB_PASSWD,Utils.DB);
		    ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
			
			// create an empty model
			Model model = maker.createModel(mod);
		
		
		//Model model = ModelFactory.createDefaultModel();
		
		String namespaceSQuiz = "http://194.57.85.190/namespaces/export/squiz#";
		String base = "http://194.57.85.190/pendingQuizzes.rdf#";
		
		model.setNsPrefix("squiz", namespaceSQuiz);
		model.setNsPrefix("base", "http://194.57.85.190/pendingQuizzes.rdf");
		
		Resource PendingQuizzes = model.createResource(spq.getIdUser());
		PendingQuizzes.addProperty(RDF.type, model.createResource("http://194.57.85.190/namespaces/export/squiz#PendingQuizzes"));
		
		String[] pq = spq.getIdQuiz();
		
		if (pq!=null)
		{
			Bag pendingqBag = model.createBag();
			PendingQuizzes.addProperty(model.createProperty(model.expandPrefix("squiz:hasQuizzes")), pendingqBag);
			int nr_intr = pq.length;
			for (int i = 0; i< nr_intr; i++) {
				// varinat - Bag - li resources...
				Resource pendingqLi = model.createResource(pq[i]);		
				pendingqBag.add(pendingqLi); 
			}
		}
		
	//	PendingQuizzes.addProperty(model.createProperty(model.expandPrefix("squiz:hasUser")),spq.getIdUser());
//		PendingQuizzes.addProperty(model.createProperty(model.expandPrefix("squiz:activateDate")),spq.getActivateDate());
//		PendingQuizzes.addProperty(model.createProperty(model.expandPrefix("squiz:expireDate")),spq.getExpireDate());
		
		model.write(Mout, "RDF/XML-ABBREV");
		model.write(Mout, "RDF/XML");
		System.out.println(Mout.toString());
		subscribe.eventCommandSeen(new EventCommandEvent(this, AllCommands.IS_SAVED.getCommand(), true, clientID));	
			
		return "RDFConstruction! RDF Saved in DB";

	} catch (Exception e) {
		
		System.out.println("There was a problem writing the RDF to the DB");
		subscribe.eventCommandSeen(new EventCommandEvent(this, AllCommands.IS_SAVED.getCommand(), false, clientID));
		return e.toString();
		
		}	

	}
	
	public String createVariantsAnswerRDF(StructureVariantsAnswer sva, String clientID, String mod)
	{
		
		// ---- variantsAnswerModel ------------
		
		StringWriter Mout = new StringWriter();
		Model model = ModelFactory.createDefaultModel();
		
		String namespaceSQuiz = "http://194.57.85.190/namespaces/export/squiz#";
		String base = "http://194.57.85.190/variantsAnswer.rdf#";
		
		model.setNsPrefix("squiz", namespaceSQuiz);
		model.setNsPrefix("base", "http://194.57.85.190/variantsAnswer.rdf");
		
		Resource VariantsAnswer = model.createResource(base+Utils.GetUniqueID(sva.getIdVariant()));
		VariantsAnswer.addProperty(RDF.type, model.createResource("http://194.57.85.190/namespaces/export/squiz#VariantsAnswer"));
		VariantsAnswer.addProperty(model.createProperty(model.expandPrefix("squiz:variants")),sva.getIdVariant());
		VariantsAnswer.addProperty(model.createProperty(model.expandPrefix("squiz:isCorect")),sva.getIsCorect().toString());
		
		
		model.write(Mout, "RDF/XML-ABBREV");
		return Mout.toString();
	}
	
	public String createQuizAnswerRDF(StructureQuizAnswer sqv, String clientID, String mod)
	{
		//---------quizAnswerModel ----------
		StringWriter Mout = new StringWriter();
		Model model = ModelFactory.createDefaultModel();
		
		String namespaceSQuiz = "http://194.57.85.190/namespaces/export/squiz#";
		String base = "http://194.57.85.190/quizAnswer.rdf#";
		
		model.setNsPrefix("squiz", namespaceSQuiz);
		model.setNsPrefix("base", "http://194.57.85.190/quizAnswer.rdf");
		
		Resource QuizAnswer = model.createResource(base+Utils.GetUniqueID(sqv.getIdquiz()));
		QuizAnswer.addProperty(RDF.type, model.createResource("http://194.57.85.190/namespaces/export/squiz#QuizAnswer"));
		QuizAnswer.addProperty(model.createProperty(model.expandPrefix("squiz:quiz")),sqv.getIdquiz());
		
		String[] qa = sqv.getIdQuestionAnswer();
		Bag questionBag = model.createBag();
		QuizAnswer.addProperty(model.createProperty(model.expandPrefix("squiz:hasQuestions")), questionBag);
		
			int nr_intr = qa.length;
			for (int i = 0; i< nr_intr; i++) {
				// varinat - Bag - li resources...
				Resource questionsLi = model.createResource(qa[i]);		
				questionBag.add(questionsLi);
			//	questionsLi.addProperty(RDF.type,model.createResource(t[i])); 
			}
		
		
		
		model.write(Mout, "RDF/XML-ABBREV");
		return Mout.toString();
	}
	
	public String createQuestionAnswerRDF(StructureQuestionAnswer sqa, String clientID, String mod)
	{
		
		// ------------questionAnswerModel
		StringWriter Mout = new StringWriter();
		Model model = ModelFactory.createDefaultModel();
		
		String namespaceSQuiz = "http://194.57.85.190/namespaces/export/squiz#";
		String base = "http://194.57.85.190/questionAnswer.rdf#";
		
		model.setNsPrefix("squiz", namespaceSQuiz);
		model.setNsPrefix("base", "http://194.57.85.190/questionAnswer.rdf");
		
		Resource QuestionAnswer = model.createResource(base+Utils.GetUniqueID(sqa.getIdQuestion()));
		QuestionAnswer.addProperty(RDF.type, model.createResource("http://194.57.85.190/namespaces/export/squiz#QuestionAnswer"));
		QuestionAnswer.addProperty(model.createProperty(model.expandPrefix("squiz:Question")),sqa.getIdQuestion());
		QuestionAnswer.addProperty(model.createProperty(model.expandPrefix("squiz:isCorect")),sqa.getIsCorect().toString());
		
		String[] va = sqa.getIdVariantsAnswer();
		Bag variantsBag = model.createBag();
		QuestionAnswer.addProperty(model.createProperty(model.expandPrefix("squiz:hasVariants")), variantsBag);
		if (va!=null)
		{
			int nr_intr = va.length;
			for (int i = 0; i< nr_intr; i++) {
				// varinat - Bag - li resources...
				Resource questionsLi = model.createResource(va[i]);		
				variantsBag.add(questionsLi); 
			}
		}
		
		
		model.write(Mout, "RDF/XML-ABBREV");
		return Mout.toString();
	}
	
	public String createUserRightsRDF(StructureUserRights sur, String clientID, String mod)
	{
		StringWriter Mout = new StringWriter();
		
		try{	
			
			String className = "com.mysql.jdbc.Driver"; // path of driver class
			Class.forName(className); // Load the Driver
			
			IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER, Utils.DB_PASSWD,Utils.DB);
		    ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
			
			// create an empty model
			Model model = maker.createModel(mod);
		
		//Model model = ModelFactory.createDefaultModel();
		
		String namespaceSQuiz = "http://194.57.85.190/namespaces/export/squiz#";
		String base = "http://194.57.85.190/userRights.rdf#";
		
		model.setNsPrefix("squiz", namespaceSQuiz);
		model.setNsPrefix("base", "http://194.57.85.190/userRights.rdf");
		
		Resource UserRights = model.createResource(sur.getIdUser());
		UserRights.addProperty(RDF.type, model.createResource("http://194.57.85.190/namespaces/export/squiz#UserRights"));
		
		StructureInterfaceRights[] sir = sur.getRights();
		if (sir!=null)
		{
			int nr_sir=sir.length;
			Bag interfaceBag = model.createBag();
			UserRights.addProperty(model.createProperty(model.expandPrefix("squiz:hasRight")), interfaceBag);
			for(int i=0;i<nr_sir;i++)
			{
				Resource interfaceLi = model.createResource();		
				interfaceBag.add(interfaceLi);
				interfaceLi.addProperty(model.createProperty(model.expandPrefix("squiz:hasInterface")), model.createResource(sir[i].getId()));
				interfaceLi.addProperty(model.createProperty(model.expandPrefix("squiz:isAllowed")), sir[i].getIsAllowed().toString());
			}
		}
		
		model.write(Mout, "RDF/XML-ABBREV");
		System.out.println(Mout.toString());
		subscribe.eventCommandSeen(new EventCommandEvent(this, AllCommands.IS_SAVED.getCommand(), true, clientID));	
			
		return "RDFConstruction! RDF Saved in DB";

	} catch (Exception e) {
		
		System.out.println("There was a problem writing the RDF to the DB");
		subscribe.eventCommandSeen(new EventCommandEvent(this, AllCommands.IS_SAVED.getCommand(), false, clientID));
		return e.toString();
		
		}
	}
	
	public String createStudentRDF(StructureStudent ss, String clientID, String mod)
	{
		
		// ---------studentModel
		StringWriter Mout = new StringWriter();
		Model model = ModelFactory.createDefaultModel();
		
		String namespaceSQuiz = "http://194.57.85.190/namespaces/export/squiz#";
		String base = "http://194.57.85.190/student.rdf#";
		
		model.setNsPrefix("squiz", namespaceSQuiz);
		model.setNsPrefix("base", "http://194.57.85.190/student.rdf");
		
		Resource Students = model.createResource(ss.getIdUser());
		Students.addProperty(RDF.type, model.createResource("http://194.57.85.190/namespaces/export/squiz#Student"));
		
		String[] grupe = ss.getIdGroup();
		
		if(grupe!=null)
		{
			int nr_grupe = grupe.length;
			Resource GroupsBag = model.createBag();
			Students.addProperty(DCTerms.isPartOf, GroupsBag);
			for(int i=0;i<nr_grupe;i++)
			{
				Resource groupLi = model.createResource(grupe[i]);		
				GroupsBag.addProperty(DCTerms.isPartOf, groupLi);
			}
				
		}
		//Students.addProperty(DCTerms.isPartOf,ss.getIdGroup());
		
		
		Students.addProperty(model.createProperty(model.expandPrefix("squiz:hasSpecialization")),ss.getIdSpec());
		Students.addProperty(model.createProperty(model.expandPrefix("squiz:hasParcours")),ss.getIdParcours());
		Students.addProperty(DCTerms.isPartOf,ss.getIdYear());
		
		/*	String[] pq = ss.getIdPendingQuizzes();
		
		if (pq!=null)
		{
			Bag pendingqBag = model.createBag();
			Students.addProperty(model.createProperty(model.expandPrefix("squiz:hasPendingQuizzes")), pendingqBag);
			int nr_intr = pq.length;
			for (int i = 0; i< nr_intr; i++) {
				// varinat - Bag - li resources...
				Resource pendingqLi = model.createResource(pq[i]);		
				pendingqBag.add(pendingqLi); 
			}
		}
		
		String[] aq = ss.getIdAnsweredQuizzes();
		
		if (aq!=null)
		{
			Bag answeredqBag = model.createBag();
			Students.addProperty(model.createProperty(model.expandPrefix("squiz:hasAnsweredQuizzes")), answeredqBag);
			int nr_intr = aq.length;
			for (int i = 0; i< nr_intr; i++) {
				// varinat - Bag - li resources...
				Resource answeredqLi = model.createResource(aq[i]);		
				answeredqBag.add(answeredqLi); 
			}
		}
		*/
		model.write(Mout, "RDF/XML-ABBREV");
		return Mout.toString();
	}
	
	public String createProfRDF(StructureProf sp, String clientID, String mod)
	{
		
		//--------- profModel
		StringWriter Mout = new StringWriter();
		Model model = ModelFactory.createDefaultModel();
		
		String namespaceSQuiz = "http://194.57.85.190/namespaces/export/squiz#";
		String base = "http://194.57.85.190/prof.rdf#";
		
		model.setNsPrefix("squiz", namespaceSQuiz);
		model.setNsPrefix("base", "http://194.57.85.190/prof.rdf");
		
		Resource Profs = model.createResource(sp.getIdUser());
		Profs.addProperty(RDF.type, model.createResource("http://194.57.85.190/namespaces/export/squiz#Professor"));
		
		
		//Profs.addProperty(model.createProperty(model.expandPrefix("squiz:hasSubsection")),sp.getIdSubsections());
		
				
		String[] pq = sp.getIdSubsections();
		
		if (pq!=null)
		{
			Bag subsectBag = model.createBag();
			Profs.addProperty(model.createProperty(model.expandPrefix("squiz:hasSubsection")), subsectBag);
			int nr_intr = pq.length;
			for (int i = 0; i< nr_intr; i++) {
				// varinat - Bag - li resources...
				Resource subsectLi = model.createResource(pq[i]);		
				subsectBag.add(subsectLi); 
			}
		}
		
		model.write(Mout, "RDF/XML-ABBREV");
		return Mout.toString();
	}
	
	public static String createEvaluationTypeRDF(String title)
	{
		
		//--------- evalTypeModel
		StringWriter Mout = new StringWriter();
		try{	
			
			String className = "com.mysql.jdbc.Driver"; // path of driver class
			Class.forName(className); // Load the Driver
			
			IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER, Utils.DB_PASSWD,Utils.DB);
		    ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
			
			// create an empty model
		    String mod = RDFModels.evalTypeModel.getCommand();
			Model model = maker.createModel(mod);
		
		
		//Model model = ModelFactory.createDefaultModel();
		
		String namespaceSQuiz = "http://194.57.85.190/namespaces/export/squiz#";
		String base = "http://194.57.85.190/evaluationType.rdf#";
		
		model.setNsPrefix("squiz", namespaceSQuiz);
		model.setNsPrefix("base", "http://194.57.85.190/evaluationType.rdf");
		
		Resource evalType = model.createResource(base+Utils.GetUniqueID(title));
		evalType.addProperty(RDF.type, model.createResource("http://194.57.85.190/namespaces/export/squiz#evaluationType"));
			
		evalType.addProperty(DC.title,title);
			
		model.write(Mout, "RDF/XML-ABBREV");
		//System.out.println(Mout.toString());
		
		//subscribe.eventCommandSeen(new EventCommandEvent(this, "RDF Saved in DB", ss, clientID));		
		return Mout.toString();

	} catch (Exception e) {
		
		System.out.println("There was a problem writing the RDF to the DB");
		
		return e.toString();
		
		}	
	}
	
	public static String createYearRDF(String titlu, String[] semestre, String formatiune)
	{
		
		//--------- yearModel
		StringWriter Mout = new StringWriter();
		try{	
			
			String className = "com.mysql.jdbc.Driver"; // path of driver class
			Class.forName(className); // Load the Driver
			
			IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER, Utils.DB_PASSWD,Utils.DB);
		    ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
			
			// create an empty model
		   String mod = RDFModels.yearModel.getCommand();
			Model model = maker.createModel(mod);
		
		
		//Model model = ModelFactory.createDefaultModel();
		
		String namespaceSQuiz = "http://194.57.85.190/namespaces/export/squiz#";
		String base = "http://194.57.85.190/year.rdf#";
		
		model.setNsPrefix("squiz", namespaceSQuiz);
		model.setNsPrefix("base", "http://194.57.85.190/year.rdf");
		model.setNsPrefix("dcterms","http://purl.org/dc/terms/");
		
		Resource Year = model.createResource(base+Utils.GetUniqueID(titlu));
		Year.addProperty(RDF.type, model.createResource("http://194.57.85.190/namespaces/export/squiz#Year"));
			
		Year.addProperty(DC.title,titlu);
		Year.addProperty(DCTerms.isPartOf, model.createResource(formatiune));
		
		String[] sem = semestre;
		
		if (sem!=null)
		{
			Bag subsectBag = model.createBag();
			Year.addProperty(model.createProperty(model.expandPrefix("squiz:hasSemesters")), subsectBag);
			int nr_intr = sem.length;
			for (int i = 0; i< nr_intr; i++) {
				// varinat - Bag - li resources...
				Resource subsectLi = model.createResource(sem[i]);		
				subsectBag.add(subsectLi); 
			}
		}
		
		
			
		model.write(Mout, "RDF/XML-ABBREV");
		//System.out.println(Mout.toString());
		
		//subscribe.eventCommandSeen(new EventCommandEvent(this, "RDF Saved in DB", ss, clientID));		
		return Mout.toString();

	} catch (Exception e) {
		
		System.out.println("There was a problem writing the RDF to the DB");
		
		return e.toString();
		
		}	
	}
	
	public static String createSemesterRDF(String title)
	{
		
		//--------- semesterModel
		StringWriter Mout = new StringWriter();
		try{	
			
			String className = "com.mysql.jdbc.Driver"; // path of driver class
			Class.forName(className); // Load the Driver
			
			IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER, Utils.DB_PASSWD,Utils.DB);
		    ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
			
			// create an empty model
		    String mod = RDFModels.semesterModel.getCommand();
			Model model = maker.createModel(mod);
		
		
		//Model model = ModelFactory.createDefaultModel();
		
		String namespaceSQuiz = "http://194.57.85.190/namespaces/export/squiz#";
		String base = "http://194.57.85.190/semesters.rdf#";
		
		model.setNsPrefix("squiz", namespaceSQuiz);
		model.setNsPrefix("base", "http://194.57.85.190/semesters.rdf");
		
		Resource evalType = model.createResource(base+Utils.GetUniqueID(title));
		evalType.addProperty(RDF.type, model.createResource("http://194.57.85.190/namespaces/export/squiz#Semester"));
			
		evalType.addProperty(DC.title,title);
			
		model.write(Mout, "RDF/XML-ABBREV");
		//System.out.println(Mout.toString());
		
		//subscribe.eventCommandSeen(new EventCommandEvent(this, "RDF Saved in DB", ss, clientID));		
		return Mout.toString();

	} catch (Exception e) {
		
		System.out.println("There was a problem writing the RDF to the DB");
		
		return e.toString();
		
		}	
	}
	
	public static String createInterfacesRDF(String title,String description)
	{
		
		//--------- interfacesModel
		StringWriter Mout = new StringWriter();
		try{	
			
			String className = "com.mysql.jdbc.Driver"; // path of driver class
			Class.forName(className); // Load the Driver
			
			IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER, Utils.DB_PASSWD,Utils.DB);
		    ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
			
			// create an empty model
		    String mod = RDFModels.interfacesModel.getCommand();
			Model model = maker.createModel(mod);
		
		
		//Model model = ModelFactory.createDefaultModel();
		
		String namespaceSQuiz = "http://194.57.85.190/namespaces/export/squiz#";
		String base = "http://194.57.85.190/interfaces.rdf#";
		
		model.setNsPrefix("squiz", namespaceSQuiz);
		model.setNsPrefix("base", "http://194.57.85.190/interfaces.rdf");
		
		Resource Interfaces = model.createResource(base+Utils.GetUniqueID(title));
		Interfaces.addProperty(RDF.type, model.createResource("http://194.57.85.190/namespaces/export/squiz#Interface"));
			
		Interfaces.addProperty(DC.title,title);
		Interfaces.addProperty(DC.description, description);
		
			
		model.write(Mout, "RDF/XML-ABBREV");
		//System.out.println(Mout.toString());
		
		//subscribe.eventCommandSeen(new EventCommandEvent(this, "RDF Saved in DB", ss, clientID));		
		return Mout.toString();

	} catch (Exception e) {
		
		System.out.println("There was a problem writing the RDF to the DB");
		
		return e.toString();
		
		}	
	}
}