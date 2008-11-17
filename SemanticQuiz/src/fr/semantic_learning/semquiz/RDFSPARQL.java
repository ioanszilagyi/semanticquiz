package fr.semantic_learning.semquiz;

import com.hp.hpl.jena.db.DBConnection;
import com.hp.hpl.jena.db.IDBConnection;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ModelMaker;

import fr.semantic_learning.events.EventCommandEvent;
import fr.semantic_learning.events.EventCommandSeenListner;
import fr.semantic_learning.semquiz.commands.AllCommands;
import fr.semantic_learning.semquiz.commands.RDFModels;
import fr.semantic_learning.semquiz.valueobjects.*;
import fr.semantic_learning.Utils;

public class RDFSPARQL implements EventCommandSeenListner {
	private EventCommandSeenListner subscribe;

	public RDFSPARQL() {
		// When the constructor is called the event will be ready for use
	}

	public RDFSPARQL(EventCommandSeenListner ecsl) {
		// to send commands
		subscribe = ecsl;
	}

	public void eventCommandSeen(EventCommandEvent ece) {
		// in loc de toate if-urile ar trebui sa folosesc un case mult mai
		// elegant TODO
		if (ece.command != "") {
			// returneaza toate unitatile
			if (AllCommands.toCommands(ece.command) == AllCommands.SELECT_UNIT) {
				System.out.println("Event: " + ece.command
						+ "; Recived in RDFSPARQL from: "
						+ ece.getSource().toString());
				try {
					subscribe.eventCommandSeen(new EventCommandEvent(this,
							AllCommands.SELECT_UNIT.getCommand(),
							ChooseUnitAction(), ece.clientID));
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (AllCommands.toCommands(ece.command) == AllCommands.SELECT_SUBSECTION) {
				System.out.println("Event: " + ece.command
						+ "; Recived in RDFSPARQL from: "
						+ ece.getSource().toString());
				try {
					subscribe.eventCommandSeen(new EventCommandEvent(this,
							AllCommands.SELECT_SUBSECTION.getCommand(),
							returnSubsections(), ece.clientID));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (AllCommands.toCommands(ece.command) == AllCommands.SELECT_QUESTIONS) {
				System.out.println("Event: " + ece.command
						+ "; Recived in RDFSPARQL from: "
						+ ece.getSource().toString());
				try {
					subscribe
							.eventCommandSeen(new EventCommandEvent(this,
									AllCommands.SELECT_QUESTIONS.getCommand(),
									returnQuestionByID((String) ece.args),
									ece.clientID));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (AllCommands.toCommands(ece.command) == AllCommands.SELECT_QUIZ_QUESTIONS) {
				System.out.println("Event: " + ece.command
						+ "; Recived in RDFSPARQL from: "
						+ ece.getSource().toString());
				try {
					subscribe.eventCommandSeen(new EventCommandEvent(this,
							AllCommands.SELECT_QUIZ_QUESTIONS.getCommand(),
							returnQuizQuestions((String) ece.args),
							ece.clientID));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (AllCommands.toCommands(ece.command) == AllCommands.SELECT_ALL_QUIZ) {
				System.out.println("Event: " + ece.command
						+ "; Recived in RDFSPARQL from: "
						+ ece.getSource().toString());
				try {
					subscribe.eventCommandSeen(new EventCommandEvent(this,
							AllCommands.SELECT_ALL_QUIZ.getCommand(),
							returnAllQuiz((String) ece.args), ece.clientID));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (AllCommands.toCommands(ece.command) == AllCommands.SELECT_SEMESTER) {
				System.out.println("Event: " + ece.command
						+ "; Recived in RDFSPARQL from: "
						+ ece.getSource().toString());
				try {
					subscribe.eventCommandSeen(new EventCommandEvent(this,
							AllCommands.SELECT_SEMESTER.getCommand(),
							returnSemester((String) ece.args), ece.clientID));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (AllCommands.toCommands(ece.command) == AllCommands.SELECT_YEAR) {
				System.out.println("Event: " + ece.command
						+ "; Recived in RDFSPARQL from: "
						+ ece.getSource().toString());
				try {
					subscribe.eventCommandSeen(new EventCommandEvent(this,
							AllCommands.SELECT_YEAR.getCommand(),
							ChooseYearAction((StructureYear) ece.args),
							ece.clientID));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (AllCommands.toCommands(ece.command) == AllCommands.LOGIN_RIGHTS) {
				System.out.println("Event: " + ece.command
						+ "; Recived in RDFSPARQL from: "
						+ ece.getSource().toString());
				try {
					subscribe.eventCommandSeen(new EventCommandEvent(this,
							AllCommands.LOGIN_RIGHTS.getCommand(),
							returnUserRights((String) ece.args), ece.clientID));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// returneaza EvaluationType= types of evaluation
			if (AllCommands.toCommands(ece.command) == AllCommands.SELECT_EVALUATIONTYPE) {
				System.out.println("Event: " + ece.command
						+ "; Recived in RDFSPARQL from: "
						+ ece.getSource().toString());
				try {
					subscribe.eventCommandSeen(new EventCommandEvent(this,
							AllCommands.SELECT_EVALUATIONTYPE.getCommand(),
							returnEvaluationType(), ece.clientID));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// returneaza InterfaceRights = all interface rights //root acces
			if (AllCommands.toCommands(ece.command) == AllCommands.SELECT_ALL_RIGHTS) {
				System.out.println("Event: " + ece.command
						+ "; Recived in RDFSPARQL from: "
						+ ece.getSource().toString());
				try {
					subscribe.eventCommandSeen(new EventCommandEvent(this,
							AllCommands.SELECT_ALL_RIGHTS.getCommand(),
							ChooseRightsAction(null), ece.clientID));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// returneaza UserRIghts By id
			if (AllCommands.toCommands(ece.command) == AllCommands.SELECT_RIGHTS) {
				System.out.println("Event: " + ece.command
						+ "; Recived in RDFSPARQL from: "
						+ ece.getSource().toString());
				try {
					subscribe.eventCommandSeen(new EventCommandEvent(this,
							AllCommands.SELECT_RIGHTS.getCommand(),
							returnUserRights((String) ece.args), ece.clientID));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// returneaza SPECIALIZATION
			if (AllCommands.toCommands(ece.command) == AllCommands.SELECT_SPECIALIZATION) {
				System.out.println("Event: " + ece.command
						+ "; Recived in RDFSPARQL from: "
						+ ece.getSource().toString());
				try {
					subscribe
							.eventCommandSeen(new EventCommandEvent(
									this,
									AllCommands.SELECT_SPECIALIZATION
											.getCommand(),
									ChooseSpecializationAction((StructureSpecialization) ece.args),
									ece.clientID));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// return Competence
			if (AllCommands.toCommands(ece.command) == AllCommands.SELECT_COMPETENCE) {
				System.out.println("Event: " + ece.command
						+ "; Recived in RDFSPARQL from: "
						+ ece.getSource().toString());
				try {
					subscribe
							.eventCommandSeen(new EventCommandEvent(
									this,
									AllCommands.SELECT_COMPETENCE.getCommand(),
									ChooseCompetenceAction((StructureCompetence) ece.args),
									ece.clientID));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// returneaza formatiunile
			if (AllCommands.toCommands(ece.command) == AllCommands.SELECT_FORMATION) {
				System.out.println("Event: " + ece.command
						+ "; Recived in RDFSPARQL from: "
						+ ece.getSource().toString());
				try {
					subscribe
							.eventCommandSeen(new EventCommandEvent(
									this,
									AllCommands.SELECT_FORMATION.getCommand(),
									ChooseFormationAction((StructureFormation) ece.args),
									ece.clientID));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// returneaza pending quises per user //1 user at a time
			if (AllCommands.toCommands(ece.command) == AllCommands.SELECT_PENDING_QUIZZES) {
				System.out.println("Event: " + ece.command
						+ "; Recived in RDFSPARQL from: "
						+ ece.getSource().toString());
				try {
					subscribe.eventCommandSeen(new EventCommandEvent(this,
							AllCommands.SELECT_PENDING_QUIZZES.getCommand(),
							returnPendingQuizzes((String) ece.args),
							ece.clientID));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// returneaza mentiunile
			if (AllCommands.toCommands(ece.command) == AllCommands.SELECT_MENTION) {
				System.out.println("Event: " + ece.command
						+ "; Recived in RDFSPARQL from: "
						+ ece.getSource().toString());
				try {
					subscribe.eventCommandSeen(new EventCommandEvent(this,
							AllCommands.SELECT_MENTION.getCommand(),
							ChooseMentionAction((StructureMention) ece.args),
							ece.clientID));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (AllCommands.toCommands(ece.command) == AllCommands.SELECT_SECTION) {
				System.out.println("Event: " + ece.command
						+ "; Recived in RDFSPARQL from: "
						+ ece.getSource().toString());
				try {
					subscribe
							.eventCommandSeen(new EventCommandEvent(
									this,
									AllCommands.SELECT_SECTION.getCommand(),
									ChooseSectionAction((StructureDisciplineSection) ece.args),
									ece.clientID));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (AllCommands.toCommands(ece.command) == AllCommands.SELECT_PARCOURS) {
				System.out.println("Event: " + ece.command
						+ "; Recived in RDFSPARQL from: "
						+ ece.getSource().toString());
				try {
					subscribe.eventCommandSeen(new EventCommandEvent(this,
							AllCommands.SELECT_PARCOURS.getCommand(),
							ChooseParcoursAction((StructureParcours) ece.args),
							ece.clientID));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (AllCommands.toCommands(ece.command) == AllCommands.LOGIN_REQUEST) {
				// if useru exista si parola e buna trimit structura populata ,
				// in Flex
				System.out.println("Event: " + ece.command
						+ "; Recived in RDFSPARQL from: "
						+ ece.getSource().toString());
				try {
					subscribe.eventCommandSeen(new EventCommandEvent(this,
							AllCommands.LOGIN_RESPONSE.getCommand(),
							returnUserByID((StructureUser) ece.args),
							ece.clientID));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (AllCommands.toCommands(ece.command) == AllCommands.SELECT_USER) {
				// if realtime returneaza in timp ce omu baga in text box useru
				// un fel de cautare de useri rapida
				System.out.println("Event: " + ece.command
						+ "; Recived in RDFSPARQL from: "
						+ ece.getSource().toString());
				try {
					subscribe.eventCommandSeen(new EventCommandEvent(this,
							AllCommands.SELECT_USER.getCommand(),
							ChooseUserAction((StructureUser) ece.args),
							ece.clientID));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (AllCommands.toCommands(ece.command) == AllCommands.SUBSECTION_QUESTIONS) {
				// returneaza intrebarile dintr-un anumit domeniu
				// return. id question+ text question
				System.out.println("Event: " + ece.command
						+ "; Recived in RDFSPARQL from: "
						+ ece.getSource().toString());
				try {
					subscribe.eventCommandSeen(new EventCommandEvent(this,
							AllCommands.SUBSECTION_QUESTIONS.getCommand(),
							returnQuestionsBySubsection((String) ece.args),
							ece.clientID));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (AllCommands.toCommands(ece.command) == AllCommands.EXIST_USER) {
				// if useru exista trimit mesaj ,la nelu in flex, ca nu se poate
				// face altu cu id-u' asta
				System.out.println("Event: " + ece.command
						+ "; Recived in RDFSPARQL from: "
						+ ece.getSource().toString());
				try {
					if (ExistUserByID(((StructureUser) ece.args).getUserName())) {
						subscribe.eventCommandSeen(new EventCommandEvent(this,
								AllCommands.EXIST_USER.getCommand(), null,
								ece.clientID));
						System.out
								.println("Sent bad news to nelu:Exista deja .");
					} else {
						System.out.println("Sent to Emma :Write it down .");
						subscribe.eventCommandSeen(new EventCommandEvent(this,
								AllCommands.EXIST_USER.getCommand(), ece.args,
								ece.clientID));

					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} else {
			subscribe
					.eventCommandSeen(new EventCommandEvent(
							this,
							"Error.",
							"U should specify a valid Command \n	 Specify a"
									+ "valid Function Name.\n This isn't : \""
									+ ece.command
									+ "\".\n"
									+ "the valid function names are:\n"
									+ "-returnDomains() - No parameter needed\n"
									+ "-returnQuestionByID(parameter) - parameter:QuestionID\n"
									+ "-returnQuestionsByDomain(parameter) - parameter:DomainID\n"
									+ "-ExistDomain(parameter) - parameter:DomainID\n",
							ece.clientID));
		}
	}

	public Object[] ChooseUnitAction() throws ClassNotFoundException {// toate
																		// mesajele
																		// Select_Competence
																		// vin
																		// aci
		// checking the proprietes of the "struct" argument the function decide
		// what subroutine to lunch
		// constructing the queryString
		System.out.println("ChooseUnits...");
		String queryString = " ";
		String prefix = " ";
		String select = " SELECT DISTINCT ";
		String where = "  WHERE {";
		String filter = " ";
		String orderby = " ";
		String limit = " ";
		String end = " }";

		prefix += "PREFIX name:<http://194.57.85.190/namespaces/export/squiz#> "
				+ " PREFIX dcterms:<http://purl.org/dc/terms/>"
				+ " PREFIX type:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>";

		select += " ?id  ?title ";

		where += "" + " ?id  type:type name:Unit . "
				+ " ?id name:title ?title . ";

		/*
		 * if (mention.getIdFormation()!=null ) { filter+= " FILTER
		 * (?id_formation=<" + mention.getIdFormation() + ">) ."; } if
		 * (mention.getTitle()!=null) { filter+= " FILTER regex(?title,\"" +
		 * mention.getTitle() + "\",\"i\") ."; }
		 */

		queryString = prefix + select + where + filter + orderby + limit + end;
		System.out.println("ChooseUnits...Done");
		return returnUnit(queryString);

	}

	public Object[] ChooseParcoursAction(StructureParcours struct)
			throws ClassNotFoundException {// toate mesajele Select_Competence
											// vin aci
		// checking the proprietes of the "struct" argument the function decide
		// what subroutine to lunch
		// constructing the queryString
		String queryString = " ";
		String prefix = " ";
		String select = " SELECT ";
		String where = "  WHERE {";
		String filter = " ";
		String orderby = " ";
		String limit = " ";
		String end = " }";
		prefix += " PREFIX name:<http://194.57.85.190/namespaces/export/squiz#> "
				+ " PREFIX type:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ " PREFIX dcterms:<http://purl.org/dc/terms/>";
		select += " ?id  ?title ?code ?id_mention ?id_specialization  ";

		where += "" + " ?id  type:type name:Parcours . "
				+ " ?id name:title ?title . "
				// + " ?id dcterms:isPartOf ?id_mention . "
				// + " OPTIONAL {?id dcterms:isPartOf ?id_specialization }. "
				+ " ?id name:code ?code  .";
		/*
		 * if (struct.getIdSpecialization()!=null) {System.out.println("Filtrare
		 * pe Specializare : "+struct.getIdSpecialization()); filter+= " FILTER " +
		 * "(?id_specialization=<" + struct.getIdSpecialization() + ">) .";
		 * }else{if (struct.getIdMention()!=null) {System.out.println("Filtrare
		 * pe Mentiune : "+struct.getIdMention()); filter+= " FILTER " +
		 * "(?id_mention=<" + struct.getIdMention() + ">) ."; }
		 *  } if (struct.getTitle()!=null) { filter+= " FILTER regex(?title,\"" +
		 * struct.getTitle() + "\",\"i\") ."; }
		 */
		queryString = prefix + select + where + filter + orderby + limit + end;

		return returnParcours(queryString);

	}

	public Object[] ChooseSectionAction(StructureDisciplineSection struct)
			throws ClassNotFoundException {// toate mesajele Select_Competence
											// vin aci
		// checking the proprietes of the "struct" argument the function decide
		// what subroutine to lunch
		// constructing the queryString
		String queryString = " ";
		String prefix = " ";
		String select = " SELECT ";
		String where = "  WHERE {";
		String filter = " ";
		String orderby = " ";
		String limit = " ";
		String end = " }";
		prefix += " PREFIX name:<http://194.57.85.190/namespaces/export/squiz#> "
				+ " PREFIX type:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>";

		select += " ?id  ?title ?code ";

		where += "" + " ?id type:type name:Section . "
				+ " ?id name:title ?title . " + " ?id name:code ?code  .";

		/*
		 * if (!mention.getTitle().equals("")) { filter+= " FILTER " + "(?id=\"" +
		 * mention.getTitle() + "\") .";
		 */

		if (struct.getTitle() != null) {
			filter += " FILTER regex(?title,\"" + struct.getTitle()
					+ "\",\"i\") .";
		}

		queryString = prefix + select + where + filter + orderby + limit + end;

		return returnSection(queryString);

	}

	public Object[] ChooseCompetenceAction(StructureCompetence struct)
			throws ClassNotFoundException {// toate mesajele Select_Competence
											// vin aci
		// checking the proprietes of the "struct" argument the function decide
		// what subroutine to lunch
		// constructing the queryString
		String queryString = " ";
		String prefix = " ";
		String select = " SELECT ";
		String where = "  WHERE {";
		String filter = " ";
		String orderby = " ";
		String limit = " ";
		String end = " }";
		prefix += " PREFIX name:<http://194.57.85.190/namespaces/export/squiz#> "
				+ " PREFIX type:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>";

		select += " ?id  ?title ";

		where += "" + " ?id  type:type name:Competence . "
				+ " ?id name:title ?title . ";

		/*
		 * if (!mention.getTitle().equals("")) { filter+= " FILTER " + "(?id=\"" +
		 * mention.getTitle() + "\") .";
		 */

		if (struct.getTitle() != null) {
			filter += " FILTER regex(?title,\"" + struct.getTitle()
					+ "\",\"i\") .";
		}

		queryString = prefix + select + where + filter + orderby + limit + end;

		return returnCompetence(queryString);

	}

	public Object[] ChooseYearAction(StructureYear struct)
			throws ClassNotFoundException {// toate mesajele Select_Competence
											// vin aci
		// checking the proprietes of the "struct" argument the function decide
		// what subroutine to lunch
		// constructing the queryString
		System.out.println("ChooseYears...");
		String queryString = " ";
		String prefix = " ";
		String select = " SELECT DISTINCT ";
		String where = "  WHERE {";
		String filter = " ";
		String orderby = " ";
		String limit = " ";
		String end = " }";
		prefix += " PREFIX name:<http://194.57.85.190/namespaces/export/squiz#> "
				+ " PREFIX type:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ " PREFIX dcterms:<http://purl.org/dc/terms/> "
				+ " PREFIX dc:<http://purl.org/dc/elements/1.1/> ";

		select += " ?id  ?title ?id_semester ?id_formation ";

		where += "" + " ?id type:type name:Year . " + " ?id dc:title ?title . "
				+ " ?id name:hasSemesters ?_semester. "
				+ " ?_semester ?type ?id_semester. "
				+ " ?id dcterms:isPartOf ?id_formation . " + "";

		filter += " FILTER (?id_semester!=<http://www.w3.org/1999/02/22-rdf-syntax-ns#Bag>) . ";
		if (struct.getIdFormation() != null) {
			System.out.println("FILTER by : " + struct.getIdFormation());
			filter += " FILTER (?id_formation=<" + struct.getIdFormation()
					+ ">) . ";
		}

		queryString = prefix + select + where + filter + orderby + limit + end;
		System.out.println("ChooseYears...Done");
		return returnYear(queryString);

	}

	public Object[] ChooseRightsAction(String id_interface)
			throws ClassNotFoundException {// toate mesajele Select_Competence
											// vin aci
		// checking the proprietes of the "struct" argument the function decide
		// what subroutine to lunch
		// constructing the queryString
		System.out.println("ChooseRightsAction...");
		String queryString = " ";
		String prefix = " ";
		String select = " SELECT ";
		String where = "  WHERE {";
		String filter = " ";
		String orderby = " ";
		String limit = " ";
		String end = " }";
		prefix += " PREFIX name:<http://194.57.85.190/namespaces/export/squiz#> "
				+ " PREFIX type:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ " PREFIX dc:<http://purl.org/dc/elements/1.1/> ";

		select += " ?id  ?title ?description ";

		where += "" + " ?id type:type name:Interface . "
				+ " ?id dc:title ?title . "
				+ " ?id dc:description ?description. ";

		if (id_interface != null) {
			filter += " FILTER (?id=<" + id_interface + ">) .";
		}

		/*
		 * if (struct.getTitle()!=null) { filter+= " FILTER regex(?title,\"" +
		 * struct.getTitle() + "\",\"i\") ."; }
		 */

		queryString = prefix + select + where + filter + orderby + limit + end;
		System.out.println("ChooseRightsAction...Done");
		return returnRights(queryString);

	}

	public Object[] ChooseMentionAction(StructureMention mention)
			throws ClassNotFoundException {// toate mesajele SelectQuiz vin aci
		// checking the proprietes of the "struct" argument the function decide
		// what subroutine to lunch
		// constructing the queryString
		String queryString = " ";
		String prefix = " ";
		String select = " SELECT ";
		String where = "  WHERE {";
		String filter = " ";
		String orderby = " ";
		String limit = " ";
		String end = " }";
		prefix += "PREFIX name:<http://194.57.85.190/namespaces/export/squiz#> "
				+ " PREFIX dcterms:<http://purl.org/dc/terms/>"
				+ " PREFIX type:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>";

		select += " ?id  ?title ";

		where += "" + " ?id  type:type name:Mention . "
				+ " ?id  dcterms:isPartOf ?id_formation ."
				+ " ?id name:title ?title . ";

		if (mention.getIdFormation() != null) {
			filter += " FILTER (?id_formation=<" + mention.getIdFormation()
					+ ">) .";
		}
		if (mention.getTitle() != null) {
			filter += " FILTER regex(?title,\"" + mention.getTitle()
					+ "\",\"i\") .";
		}

		queryString = prefix + select + where + filter + orderby + limit + end;

		return returnMention(queryString);
	}

	public Object[] ChooseQuizAction(String id_user)
			throws ClassNotFoundException { // TODO CHOOZE QUIZ ACTION astept sa
											// se creeze RDF student
		// toate mesajele SelectQuiz vin aci
		// checking the proprietes of the "struct" argument the function decide
		// what subroutine to lunch
		// constructing the queryString
		String queryString = " ";
		String prefix = " ";
		String select = " SELECT ";
		String where = "  WHERE {";
		String filter = " ";
		String orderby = " ";
		String limit = " ";
		String end = " }";
		prefix += "PREFIX name:<http://194.57.85.190/namespaces/export/squiz#> "
				+ " PREFIX domain:<http://194.57.85.190/domains.rdf#>"
				+ " PREFIX vcard:<http://www.w3.org/2001/vcard-rdf/3.0#>";

		select += " ?id ?full_name ";

		where += "" + " ?id vcard:FN ?full_name . "
				+ " ?id vcard:ROLE ?role . ";

		if (id_user != null) {
			filter += " FILTER " + "(?id=<" + id_user + ">) .";
		}
		/*
		 * if (!user.getFamilyName().equals("")) { filter+= " FILTER
		 * regex(?full_name,\"" + user.getFamilyName() + "\",\"i\") ."; }
		 */
		queryString = prefix + select + where + filter + orderby + limit + end;

		return returnUser(queryString);

	}

	public Object[] ChooseUserAction(StructureUser user)
			throws ClassNotFoundException {// tote mesajele SelectUser vin aci
		// checking the proprietes of the "struct" argument the function decide
		// what subroutine to lunch
		// constructing the queryString
		String queryString = " ";
		String prefix = " ";
		String select = " SELECT ";
		String where = "  WHERE {";
		String filter = " ";
		String orderby = " ";
		String limit = " ";
		String end = " }";
		prefix += " PREFIX name:<http://194.57.85.190/namespaces/export/squiz#> "
				+ " PREFIX domain:<http://194.57.85.190/domains.rdf#>"
				+ " PREFIX vcard:<http://www.w3.org/2001/vcard-rdf/3.0#>";

		select += " ?id ?full_name ?user_name ";

		where += "" + " ?id vcard:FN ?full_name . "
				+ " ?id name:user_name ?user_name  . "
				+ " ?id vcard:ROLE ?role . ";

		if (user.getId() != null) {
			filter += " FILTER " + "(?id=\"" + user.getId() + "\") .";
		}
		if (user.getRole() != null) {
			filter += " FILTER " + "(?role=\"" + user.getRole() + "\") .";
		}
		if (user.getFamilyName() != null) {
			filter += " FILTER regex(?full_name,\"" + user.getFamilyName()
					+ "\",\"i\") .";
		}

		queryString = prefix + select + where + filter + orderby + limit + end;

		return returnUser(queryString);

	}

	public Object[] ChooseFormationAction(StructureFormation var)
			throws ClassNotFoundException {// tote mesajele SelectFormation vin
											// aci
		// checking the proprietes of the "struct" argument the function decide
		// what subroutine to lunch
		// and how to construct the queryString
		String queryString = " ";
		String prefix = " ";
		String select = " SELECT ";
		String where = "  WHERE {";
		String filter = " ";
		String orderby = " ";
		String limit = " ";
		String end = " }";
		prefix += " PREFIX type:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ " PREFIX base:<http://194.57.85.190/namespaces/export/squiz#>";

		select += " ?id ?title ";

		where += "" + " ?id type:type base:Formation  ."
				+ " ?id base:title ?title  .";

		if (!var.getTitle().equals("")) {
			filter += " FILTER regex(?title,\"" + var.getTitle()
					+ "\",\"i\") .";
		}

		queryString = prefix + select + where + filter + orderby + limit + end;

		return returnFormations(queryString);

	}

	public Object[] ChooseSpecializationAction(StructureSpecialization var)
			throws ClassNotFoundException {// toate mesajele
											// SelectSpecialization vin aci
		// checking the proprietes of the "struct" argument the function decide
		// what subroutine to lunch
		// and how to construct the queryString
		String queryString = " ";
		String prefix = " ";
		String select = " SELECT ";
		String where = "  WHERE {";
		String filter = " ";
		String orderby = " ";
		String limit = " ";
		String end = " }";
		prefix += " PREFIX type:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ " PREFIX base:<http://194.57.85.190/namespaces/export/squiz#>"
				+ " PREFIX dcterms:<http://purl.org/dc/terms/>";

		select += " ?id ?title ";

		where += "" + " ?id type:type base:Specialization  ."
				+ " ?id dcterms:isPartOf ?id_mention  ."
				+ " ?id base:title ?title  .";
		if (var.getIdMention() != null) {
			filter += " FILTER " + "(?id_mention=<" + var.getIdMention()
					+ ">) .";
		}
		if (!var.getTitle().equals("")) {
			filter += " FILTER regex(?title,\"" + var.getTitle()
					+ "\",\"i\") .";
		}

		queryString = prefix + select + where + filter + orderby + limit + end;

		return returnSpecialization(queryString);

	}

	public Object[] changeUserPassword() throws ClassNotFoundException {// functie
																		// ce
																		// returneaza
																		// toate
																		// domeniile
																		// distincte
																		// " sub
																		// forma
																		// de
																		// structuri
																		// de
																		// date
		// le putem folosi in dropdownurile pt alegere domeniu la creare
		// question
		// Open database
		String StructType = "change UserpASS";
		System.out.println("Exec Return" + StructType + " ...");
		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel(RDFModels.userModel.getCommand());
		// System.out.println("execut query-u get sections...'");
		String queryString = " PREFIX name:<http://194.57.85.190/namespaces/export/squiz#> "
				+ " PREFIX domain:<http://194.57.85.190/domains.rdf#>"
				+ " PREFIX vcard:<http://www.w3.org/2001/vcard-rdf/3.0#>"
				+ " DELETE DATA "
				+ "  {"
				+ " ?id name:user_name \"ioan\" }. "
				+ " INSERT DATA " + "  {" + " ?id name:user_name \"ilie\" }. "

				+ " ";
		// Create a new query
		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		// ResultSetFormatter.out(results);

		RDFSPARQL_RESULTS_TO_STRUCT a = new RDFSPARQL_RESULTS_TO_STRUCT();
		a.ParseAsVectorOfStructureUnit(results);
		qe.close();
		System.out.println("Exec Return" + StructType + " ...DONE.");

		return a.getSolution();

	}

	public Object[] returnQuiz(String queryString)
			throws ClassNotFoundException {// functie ce returneaza quizurile
											// distincte " sub forma de
											// structuri de date
		// le putem folosi in dropdownurile pt alegere domeniu la creare
		// question
		// Open database
		String StructType = "Quiz";
		System.out.println("Exec Return" + StructType + " ...");
		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel(RDFModels.quizModel.getCommand());
		// System.out.println("execut query-u get sections...'");

		// Create a new query
		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		// ResultSetFormatter.out(results);

		RDFSPARQL_RESULTS_TO_STRUCT a = new RDFSPARQL_RESULTS_TO_STRUCT();
		a.ParseAsVectorOfStructureQuiz(results);
		qe.close();
		System.out.println("Exec Return" + StructType + " ...DONE.");

		return a.getSolution();
	}

	public Object[] returnUnit(String queryString)
			throws ClassNotFoundException {// functie ce returneaza toate
											// domeniile distincte " sub forma
											// de structuri de date
		// le putem folosi in dropdownurile pt alegere domeniu la creare
		// question
		// Open database
		String StructType = "Unit";
		System.out.println("Exec Return" + StructType + " ...");
		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel(RDFModels.unitsModel.getCommand());
		// System.out.println("execut query-u get sections...'");

		// Create a new query
		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		// ResultSetFormatter.out(results);

		RDFSPARQL_RESULTS_TO_STRUCT a = new RDFSPARQL_RESULTS_TO_STRUCT();
		a.ParseAsVectorOfStructureUnit(results);
		qe.close();
		System.out.println("Exec Return" + StructType + " ...DONE.");

		return a.getSolution();

	}

	public Object[] returnYear(String queryString)
			throws ClassNotFoundException {// functie ce returneaza toate
											// domeniile distincte " sub forma
											// de structuri de date
		// le putem folosi in dropdownurile pt alegere domeniu la creare
		// question
		// Open database
		String StructType = "Years";
		System.out.println("queryString= " + queryString);
		System.out.println("Exec Return" + StructType + " ...");
		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel(RDFModels.yearModel.getCommand());
		// System.out.println("execut query-u get sections...'");

		// Create a new query
		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		// ResultSetFormatter.out(results);

		RDFSPARQL_RESULTS_TO_STRUCT a = new RDFSPARQL_RESULTS_TO_STRUCT();
		a.ParseAsVectorOfStructureYear(results);
		qe.close();
		System.out.println("Exec Return" + StructType + " ...DONE.");

		return a.getSolution();
	}

	public Object[] returnParcours(String queryString)
			throws ClassNotFoundException {// functie ce returneaza toate
											// domeniile distincte " sub forma
											// de structuri de date
		// le putem folosi in dropdownurile pt alegere domeniu la creare
		// question
		// Open database
		String StructType = "Parcours";
		System.out.println("queryString = " + queryString);
		System.out.println("Exec Return" + StructType + " ...");
		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel("parcoursModel");
		// System.out.println("execut query-u get sections...'");

		// Create a new query
		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		// ResultSetFormatter.out(results);

		RDFSPARQL_RESULTS_TO_STRUCT a = new RDFSPARQL_RESULTS_TO_STRUCT();
		a.ParseAsVectorOfStructureParcours(results);
		qe.close();
		System.out.println("Exec Return" + StructType + " ...DONE.");

		return a.getSolution();

	}

	public Object[] returnSection(String queryString)
			throws ClassNotFoundException {// functie ce returneaza toate
											// domeniile distincte " sub forma
											// de structuri de date
		// le putem folosi in dropdownurile pt alegere domeniu la creare
		// question
		// Open database
		String StructType = "Section";
		System.out.println("Exec Return" + StructType + " ...");
		System.out.println("queryString = " + queryString);
		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel("sectionModel");
		System.out.println("execut query-u get sections...'");

		// Create a new query
		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		// ResultSetFormatter.out(results);

		RDFSPARQL_RESULTS_TO_STRUCT a = new RDFSPARQL_RESULTS_TO_STRUCT();
		a.ParseAsVectorOfStructureSection(results);
		qe.close();
		System.out.println("Exec Return" + StructType + " ...DONE.");

		return a.getSolution();

	}

	public Object[] returnQuestionsBySubsection(String id)
			throws ClassNotFoundException {

		// get back here still work 2 to
		// format the questions for a
		// specific domain (all the question
		// in the specific Subsection)
		// info returnet : the resource
		// ex:http://www.w3.org/java.util.Random@15e538e.zip
		// the value : "cand te-ai nascut?"
		// OBS: subsection IS NOT CaseSensitive
		// Open database
		System.out.println("ReturnQuestionsInSubsection........");
		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel("questionModel");
		// Create a new query
		String queryString = "";
		System.out.println("Return QUESTIONS 4 the SubSection : " + id);
		if (id != null) {
			queryString = "PREFIX name:<http://194.57.85.190/namespaces/export/squiz#>"
					+ "PREFIX domain:<http://194.57.85.190/domains.rdf#>"
					+ "SELECT DISTINCT ?id ?qtext "
					+ "WHERE {"
					+ " ?id name:hasSubsection ?id_subsection . "
					+ " ?id name:hasConfiguration ?_configuration . "
					+ " ?_configuration name:qtext ?qtext . "

					+ " FILTER (?id_subsection=<" + id + ">)" + "  }";
			com.hp.hpl.jena.query.Query query = QueryFactory
					.create(queryString);
			// Execute the query and obtain results
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			ResultSet results = qe.execSelect();
			// ResultSetFormatter.out(System.out, results, query);
			RDFSPARQL_RESULTS_TO_STRUCT a = new RDFSPARQL_RESULTS_TO_STRUCT();
			a.ParseAsVectorOfStructureQuestion(results);
			qe.close();
			// bag = ResultSetFormatter.asXMLString(results);

			return a.getSolution();
		} else {
			System.out.println("Subsection is null!");
		}
		System.out.println("ReturnQuestionsInSubsection........DONE.");
		return null;
	}

	public Object[] returnAllQuiz(String id_quiz) throws ClassNotFoundException {

		// get all quizes
		// Open database
		System.out.println("ReturnAllQuiz........");
		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel(RDFModels.quizModel.getCommand());
		// Create a new query
		String queryString = "";

		queryString = "PREFIX name:<http://194.57.85.190/namespaces/export/squiz#>"
				+ "PREFIX domain:<http://194.57.85.190/domains.rdf#>"
				+ "PREFIX dc:<http://purl.org/dc/elements/1.1/>"
				+ "SELECT DISTINCT ?id ?title "
				+ "WHERE {"
				+ " ?id dc:title ?title . ";
		if (!id_quiz.equals("")) {
			queryString += " FILTER (?id=<" + id_quiz + ">) ";
		}

		queryString += "  }";
		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		// ResultSetFormatter.out(System.out, results, query);
		RDFSPARQL_RESULTS_TO_STRUCT a = new RDFSPARQL_RESULTS_TO_STRUCT();
		a.ParseAsVectorOfStructureQuiz(results);
		qe.close();

		System.out.println("ReturnALLQuiz....DONE.");

		return a.getSolution();

	}

	public Object[] returnQuizQuestions(String id_quiz)
			throws ClassNotFoundException {

		// get all question 4 the id_quizes
		// Open database
		System.out.println("ReturnQuizQuestionsId ........");
		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel(RDFModels.quizModel.getCommand());
		// Create a new query
		String queryString = "";

		queryString = " PREFIX name:<http://194.57.85.190/namespaces/export/squiz#> "
				+ " PREFIX dc:<http://purl.org/dc/elements/1.1/> "
				+ " PREFIX type:<http://www.w3.org/1999/02/22-rdf-syntax-ns> "
				+ " SELECT DISTINCT ?id_questions "
				+ " WHERE { "
				+ " ?id name:hasQuestions ?_questions . "
				+ " ?_questions ?link ?id_questions . ";

		queryString += " FILTER (?id_questions!=<http://www.w3.org/1999/02/22-rdf-syntax-ns#Bag>) ";

		if (!id_quiz.equals("")) {
			queryString += " FILTER (?id=<" + id_quiz + ">) ";
		}

		queryString += "  }";
		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		// ResultSetFormatter.out(System.out, results, query);
		RDFSPARQL_RESULTS_TO_STRUCT a = new RDFSPARQL_RESULTS_TO_STRUCT();
		a.ParseAsVectorOfStrings(results, "r");
		qe.close();

		System.out.println("ReturnQuizQuestionsId....DONE.");

		return a.getSolution();

	}

	public Object[] returnSemester(String id) throws ClassNotFoundException {// functie
																				// ce
																				// returneaza
																				// toate
																				// subsectiunile
																				// distincte
																				// "
																				// sub
																				// forma
																				// de
																				// structuri
																				// de
																				// date
		// le putem folosi in dropdownurile pt alegere Subsectiune la creare
		// question
		// Open database
		String StructType = "Semester";
		System.out.println("Exec Return" + StructType + " ...");

		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel("semesterModel");

		// Create a new query
		String queryString = " PREFIX name:<http://194.57.85.190/namespaces/export/squiz#>"
				+ " PREFIX type:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ " PREFIX dc:<http://purl.org/dc/elements/1.1/> "
				+ " SELECT DISTINCT ?id ?title "
				+ " WHERE {"
				+ " ?id type:type ?Semester  ." + " ?id dc:title ?title  .";

		if (!id.equals("")) {
			queryString += " FILTER (?id=<" + id + ">) .";

		}

		queryString += "      }";

		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();

		// ResultSetFormatter.out(System.out, results, query);

		RDFSPARQL_RESULTS_TO_STRUCT a = new RDFSPARQL_RESULTS_TO_STRUCT();
		a.ParseAsVectorOfStructureSemester(results);
		qe.close();

		System.out.println("Exec Return" + StructType + " ...");

		return a.getSolution();

	}

	public Object[] returnPendingQuizzes(String id)
			throws ClassNotFoundException {// functie ce returneaza toate
											// subsectiunile distincte " sub
											// forma de structuri de date
		// le putem folosi in dropdownurile pt alegere Subsectiune la creare
		// question
		// Open database
		String StructType = "PendingQuizzes";
		System.out.println("Exec Return" + StructType + " ...");

		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel(RDFModels.pendingQuizzesModel
				.getCommand());

		// Create a new query
		String queryString = " PREFIX name:<http://194.57.85.190/namespaces/export/squiz#>"
				+ " PREFIX type:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ " PREFIX dc:<http://purl.org/dc/elements/1.1/> "
				+ " SELECT DISTINCT ?id_quiz "
				+ " WHERE {"
				+ " ?id name:hasQuizzes ?_quiz  ."
				+ " ?_quiz ?link ?id_quiz  .";
		queryString += " FILTER (?id_quiz!=<http://www.w3.org/1999/02/22-rdf-syntax-ns#Bag>) .";

		if (!id.equals("")) {
			queryString += " FILTER (?id=<" + id + ">) .";

		}

		queryString += "      }";

		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();

		// ResultSetFormatter.out(System.out, results, query);

		RDFSPARQL_RESULTS_TO_STRUCT a = new RDFSPARQL_RESULTS_TO_STRUCT();
		a.ParseAsVectorOfStructureQuiz(results);
		qe.close();

		System.out.println("Exec Return" + StructType + " ...");

		return a.getSolution();

	}

	public Object[] returnSubsections() throws ClassNotFoundException {// functie
																		// ce
																		// returneaza
																		// toate
																		// subsectiunile
																		// distincte
																		// " sub
																		// forma
																		// de
																		// structuri
																		// de
																		// date
		// le putem folosi in dropdownurile pt alegere Subsectiune la creare
		// question
		// Open database
		String StructType = "Subsection";
		System.out.println("Exec Return" + StructType + " ...");

		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel("subsectionModel");

		// Create a new query
		String queryString = " PREFIX name:<http://194.57.85.190/namespaces/export/squiz#>"
				+ " PREFIX type:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ " SELECT DISTINCT ?id ?title "
				+ " WHERE {"
				+ " ?id type:type ?subsection  ."
				+ " ?id name:title ?title  ."
				+ "      }";

		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();

		// ResultSetFormatter.out(System.out, results, query);

		RDFSPARQL_RESULTS_TO_STRUCT a = new RDFSPARQL_RESULTS_TO_STRUCT();
		a.ParseAsVectorOfStructureSubsection(results);
		qe.close();

		System.out.println("Exec Return" + StructType + " ...");

		return a.getSolution();

	}

	public Object[] returnUserStartingWith(String start_leters)
			throws ClassNotFoundException {// functie ce returneaza toate
											// domeniile distincte "
		// le putem folosi in dropdownurile pt alegere domeniu la creare
		// question
		// Open database
		System.out.println("am intrat");
		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel("userModel");
		System.out.println("execut queryuuuuuuuuuu'...................");

		// Create a new query
		String queryString = "PREFIX name:<http://194.57.85.190/namespaces/export/squiz#>"
				+ "SELECT DISTINCT ?user_name "
				+ "WHERE {"
				+ " ?user_id name:user_name ?user_name . "
				+ " FILTER regex(?user_name,\"^"
				+ start_leters
				+ "\",\"i\")"
				+ "  }" + " LIMIT 10";

		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		// ResultSetFormatter.out(results);
		RDFSPARQL_RESULTS_TO_STRUCT a = new RDFSPARQL_RESULTS_TO_STRUCT();
		a.ParseAsVectorOfStrings(results, "l"); // l-pt literal altceva 4
												// resource
		qe.close();
		Object[] b = a.getSolution();

		for (int i = 0; i < b.length; i++) {
			System.out.println(i + " : " + b[i]);
		}
		return b; // totzi useri care incep cu Start_leter
	}

	public Object[] returnEvaluationType() throws ClassNotFoundException {// functie
																			// ce
																			// returneaza
																			// reigts
																			// for
																			// user
																			// with
																			// Id
		// Open database
		String StructType = "EvaluationType";
		System.out.println("Exec Return" + StructType + " ...");
		// System.out.println("Filtrare after id_user: "+id);
		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel(RDFModels.evalTypeModel.getCommand());

		// Create a new query
		String queryString = ""
				+ "PREFIX name:<http://194.57.85.190/namespaces/export/squiz#>"
				+ " PREFIX type:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ " PREFIX dc:<http://purl.org/dc/elements/1.1/>"
				+ "SELECT DISTINCT  ?id ?title " + "WHERE {"
				+ " ?id type:type name:evaluationType . "
				+ " ?id dc:title ?title . " + "  }" + " LIMIT 10";

		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		// ResultSetFormatter.out(results);
		RDFSPARQL_RESULTS_TO_STRUCT a = new RDFSPARQL_RESULTS_TO_STRUCT();
		a.ParseAsVectorOfStructureEvaluationType(results);
		qe.close();

		System.out.println("Exec Return" + StructType + " ...Done.");
		return a.getSolution(); // totzi useri care incep cu Start_leter
	}

	public Object[] returnRights(String queryString)
			throws ClassNotFoundException {// functie ce returneaza reigts for
											// user with Id
		// Open database
		String StructType = "StructureInterfaceRights";
		System.out.println("Exec Return" + StructType + " ...");
		// System.out.println("Filtrare after id_user: "+id);
		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel(RDFModels.interfacesModel.getCommand());

		// Create a new query
		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		// ResultSetFormatter.out(results);
		RDFSPARQL_RESULTS_TO_STRUCT a = new RDFSPARQL_RESULTS_TO_STRUCT();
		a.ParseAsVectorOfStructureInterfaceRights(results);
		qe.close();

		System.out.println("Exec Return" + StructType + " ...Done.");
		return a.getSolution(); // totzi useri care incep cu Start_leter
	}

	public Object[] returnUserRights(String id) throws ClassNotFoundException {// functie
																				// ce
																				// returneaza
																				// reigts
																				// for
																				// user
																				// with
																				// Id
		// Open database
		System.out.println("Exec returnUserRigths....");
		System.out.println("Filtrare after id_user: " + id);
		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel("userRightsModel");

		// Create a new query
		String queryString = ""
				+ "PREFIX name:<http://194.57.85.190/namespaces/export/squiz#>"
				+ " PREFIX type:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "SELECT DISTINCT  ?id ?idInterface ?isAllowed " + "WHERE {"
				+ " ?id type:type name:UserRights . "
				+ " ?id name:hasRight ?_interface . "
				+ " ?_interface ?all_rights ?__interface . "
				+ " ?__interface name:hasInterface ?idInterface . "
				+ " ?__interface name:isAllowed ?isAllowed . "
				+ " FILTER (?id=<" + id + ">)" + "  }";
		// +" LIMIT 10";

		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		// ResultSetFormatter.out(results);
		RDFSPARQL_RESULTS_TO_STRUCT a = new RDFSPARQL_RESULTS_TO_STRUCT();
		a.ParseAsVectorOfStructureUserRights(results);
		qe.close();

		System.out.println("Exec returnUserRigths....Done.");
		return a.getSolution(); // totzi useri care incep cu Start_leter
	}

	public Object[] returnMention(String queryString)
			throws ClassNotFoundException {// functie ce returneaza toate
											// Mentiunile distincte " sub forma
											// de structuri de date
		// Open database
		String StructType = "Mention";
		System.out.println("Exec Return" + StructType + " ...");
		System.out.println("queryString = " + queryString);
		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel("mentionModel");
		System.out.println("execut query-ul return" + StructType + ".....");

		// Create a new query

		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		// ResultSetFormatter.out(results);

		RDFSPARQL_RESULTS_TO_STRUCT a = new RDFSPARQL_RESULTS_TO_STRUCT();
		a.ParseAsVectorOfStructureMention(results);
		qe.close();

		System.out.println("Exec return" + StructType + " ...Done.");
		return a.getSolution();
	}

	public Object[] returnCompetence(String queryString)
			throws ClassNotFoundException {// functie ce returneaza toate
											// Mentiunile distincte " sub forma
											// de structuri de date
		// Open database
		String StructType = "Competence";
		System.out.println("Exec Return" + StructType + " ...");
		System.out.println("queryString = " + queryString);
		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel("competenceModel");
		System.out.println("execut query-ul return" + StructType + ".....");

		// Create a new query

		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		// ResultSetFormatter.out(results);

		RDFSPARQL_RESULTS_TO_STRUCT a = new RDFSPARQL_RESULTS_TO_STRUCT();
		a.ParseAsVectorOfStructureCompetence(results);
		qe.close();

		System.out.println("Exec return" + StructType + " ...Done.");
		return a.getSolution();
	}

	public Object[] returnFormations(String queryString)
			throws ClassNotFoundException {// functie ce returneaza toate
											// formatiunile distincte " sub
											// forma de structuri de date
		// Open database
		System.out.println("Exec ReturnFormation...");
		System.out.println("queryString = " + queryString);
		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel("formationModel");
		System.out.println("execut query-ul returnFormations.....");

		// Create a new query

		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		// ResultSetFormatter.out(results);

		RDFSPARQL_RESULTS_TO_STRUCT a = new RDFSPARQL_RESULTS_TO_STRUCT();
		a.ParseAsVectorOfStructureFormation(results);
		qe.close();

		System.out.println("Exec returnFormations ...Done.");
		return a.getSolution();
	}

	public Object[] returnUser(String queryString)
			throws ClassNotFoundException {// get user

		// Open database

		System.out.println("am intrat in returnUser....\nqueryString="
				+ queryString);
		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel("userModel");
		System.out.println("Exec query-u'....................................");

		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		// ResultSetFormatter.out(results);

		RDFSPARQL_RESULTS_TO_STRUCT a = new RDFSPARQL_RESULTS_TO_STRUCT();
		a.ParseAsVectorOfStructureUser(results);
		qe.close();

		System.out.println("Am iesit din returnUser and name. Done.");

		return a.getSolution(); // totzi useri role=prof and care au in nume
								// literele "xyz"
	}

	public Object[] returnSpecialization(String queryString)
			throws ClassNotFoundException {// functie ce returneaza toate
											// formatiunile distincte " sub
											// forma de structuri de date
		// Open database
		String StructType = "Specialization";
		System.out.println("Exec Return" + StructType + "...");
		System.out.println("queryString = " + queryString);
		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel("specializationModel");
		System.out.println("execut query-ul returnFormations.....");

		// Create a new query

		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		// ResultSetFormatter.out(results);

		RDFSPARQL_RESULTS_TO_STRUCT a = new RDFSPARQL_RESULTS_TO_STRUCT();
		a.ParseAsVectorOfStructureSpecialization(results);
		qe.close();

		System.out.println("Exec return" + StructType + " ...Done.");
		return a.getSolution();
	}

	public Object[] returnQuestionByID(String question_id)
			throws ClassNotFoundException {// returns as StructQuestion the
											// question
		// for the run process
		// Open database

		String StructType = "QuestionByID";
		System.out.println("Exec Return" + StructType + "...");

		String className = "com.mysql.jdbc.Driver"; // path of driver class
	
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel(RDFModels.questionModel.getCommand());
		// Create a new query
		System.out.println(question_id);
		String queryString = "";
		queryString = " PREFIX name:<http://194.57.85.190/namespaces/export/squiz#> "
				+ " PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ " SELECT DISTINCT ?qtext ?picture ?video ?variant_id "
				+ " WHERE { "
				+ " ?id  name:hasConfiguration ?link1  . "
				+ " OPTIONAL {?link1 name:qtext ?qtext }. "
				+ " OPTIONAL {?link1 name:picture ?picture }. "
				+ " OPTIONAL {?link1 name:video  ?video }. "
				+ " OPTIONAL {?link1 name:variant  ?variant_bag }. "
				+ " OPTIONAL {?variant_bag ?link2  ?variant_id }. "
				+ " FILTER (?id=<"
				+ question_id
				+ "> ). "
				+ " FILTER (?variant_id!=<http://www.w3.org/1999/02/22-rdf-syntax-ns#Bag> ) "
				+ "  }";
		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();

	// ResultSetFormatter.out(System.out, results, query);

		RDFSPARQL_RESULTS_TO_STRUCT a = new RDFSPARQL_RESULTS_TO_STRUCT();
		a.ParseAsVectorOfStructureQuestion(results);
		qe.close();

		System.out.println("Exec return" + StructType + " ...Done.");
		return a.getSolution();

	}

	public Object[] returnVariantById(String variant_id)
			throws ClassNotFoundException {// returns as StructQuestion the
		RDFSPARQL_RESULTS_TO_STRUCT a = new RDFSPARQL_RESULTS_TO_STRUCT();
		try {										// question
	// for the run process
	// Open database

		String StructType = "VariantByID";
		System.out.println("Exec Return" + StructType + "...");

		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel(RDFModels.variantModel.getCommand());
		// Create a new query
		System.out.println("VARIANTid : "+variant_id);
		String queryString = "";
		queryString = " PREFIX name:<http://194.57.85.190/namespaces/export/squiz#> "
				+ " PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ " SELECT DISTINCT ?id ?text ?picture ?video ?sound ?valid "
				+ " WHERE { "
				+ "  ?id name:text ?text . "
				+ " OPTIONAL {?id name:picture ?picture }. "
				+ " OPTIONAL {?id name:video  ?video }. "
				+ " OPTIONAL {?id name:sound  ?sound }. "
				+ " OPTIONAL {?id name:valid  ?valid }. "
				+ " FILTER (?id=<"
				+ variant_id
				+ "> ) "
				+ "  }";
		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();

 //ResultSetFormatter.out(System.out, results, query);

		
		a.ParseAsVectorOfStructureVariants(results);
		qe.close();

		System.out.println("Exec return" + StructType + " ...Done.");
		
	}catch (Exception e) {
		// TODO: handle exception
		System.out.println(e.getStackTrace());
	}
		return a.getSolution();

	}

	public String returnQuestionsByCompetence(String name_domain) // /work in
			// progress no
			// competence in
			// rdf
			throws ClassNotFoundException {
		// TODO aci functia asta nu e facuta deloc e varza ce in auntru
		// format the questions for a
		// specific Competence (all the question
		// that have the specific Competence)
		// info returned : the resource
		// ex:http://www.w3.org/java.util.Random@15e538e.zip
		// the value : "cand te-ai nascut?"
		// OBS: parameter nAME_DOMAIN IS CaseSensitive
		// Open database
		String className = "com.mysql.jdbc.Driver"; // path of driver class
		String bag = "";
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel("test");
		// Create a new query
		String queryString = "";
		// System.out.println(name_domain);
		if (name_domain != null) {
			queryString = "PREFIX name:<http://194.57.85.190/namezispaces/export/squiz#>"
					+ "PREFIX domain:<http://194.57.85.190/domains.rdf#>"
					+ "SELECT DISTINCT ?resurse ?value  "
					+ "WHERE {"
					+ " ?resurse name:hasDomain ?domain . "
					+ " FILTER (?domain=\"http://194.57.85.190/domains.rdf#"
					+ name_domain
					+ "\")"
					+ " ?resurse name:hasConfiguration ?obj  ."
					+ "  ?obj name:qtext ?value . " + "      }"; // +
			com.hp.hpl.jena.query.Query query = QueryFactory
					.create(queryString);
			// Execute the query and obtain results
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			ResultSet results = qe.execSelect();

			bag = ResultSetFormatter.asXMLString(results);
		} else {
			System.out.println("Domeniul e null!");
		}
		return bag;
	}

	public boolean ExistQuestionByID(String question_ID)
			throws ClassNotFoundException {
		// functie ce returneaza "true" if questionu cu ID-u dat exista si "
		// "false" daca nu exista
		// dedic aceasta functie lui Raducu' pt verificare unicitate ID question
		// Open database
		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel("test");

		// Create a new query
		String queryString = "PREFIX name:<http://194.57.85.190/namespaces/export/squiz#>"
				+ "PREFIX domain:<http://194.57.85.190/domains.rdf#>"
				+ "ASK  { "
				+ " <"
				+ question_ID
				+ "> name:hasConfiguration ?obj  ." + "      }";
		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		boolean result = qe.execAsk();
		System.out.println(question_ID + " : Exista o intrebare cu acest ID: "
				+ result);
		qe.close();
		return result;
	}

	public boolean ExistUserByID(String user_id) throws ClassNotFoundException {
		// functie ce returneaza "true" if useru' cu ID-u dat exista si "
		// "false" daca nu exista
		// dedic aceasta functie lui Nelutzu' pt verificare unicitate ID user
		// deocamdata nu exista in baza de date nici un user ..waiting 4
		// interfaca input users
		// Open database
		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel("userModel");

		// Create a new query
		String queryString = " PREFIX name:<http://194.57.85.190/namespaces/export/squiz#>"
				+ " PREFIX domain:<http://194.57.85.190/domains.rdf#>"
				// ?user_id name:user_name ?user_name
				+ " ASK  { "
				+ " ?user_id name:user_name ?user  ."
				+ "FILTER (?user=\"" + user_id + "\")" + "      }";
		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		boolean result = qe.execAsk();
		System.out.println(queryString);
		System.out.println("Exist user: " + user_id + " :" + result);
		qe.close();
		return result;
	}

	public boolean ExistDomain(String domain) throws ClassNotFoundException {
		// functie ce returneaza "true" if domeniul "domain" exista si "
		// "false" daca nu
		// dedic aceasta functie lu' ala de va face partea de administrator
		// (creere domeniu)
		// deocamdata nu exista baza de date pe aprte de domenii ..waiting 4
		// interface create_domain
		// Open database
		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel("test");

		// Create a new query
		String queryString = "PREFIX name:<http://194.57.85.190/namespaces/export/squiz#> "
				+ "PREFIX domain:<http://194.57.85.190/domains.rdf#> "
				+ "ASK  { "
				+ " ?subject name:hasDomain ?domain ."
				+ " FILTER (?domain=\"http://194.57.85.190/domains.rdf#"
				+ domain + "\")"
				// name:hasCode io am presupus aici ca va fi statementu' legat
				// de
				// numele user-ului
				// probabil va fi altceva
				+ "}";
		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		boolean result = qe.execAsk();
		System.out.println("Domeniul " + domain + " exista: " + result);
		System.out.println(queryString);
		qe.close();
		return result;
	}

	public StructureUser returnUserByID(StructureUser user)
			throws ClassNotFoundException {
		// returns as struct user info ByID
		// info returned : the user struct
		// LOGIN_RESPONSE
		// Open database
		// dedic aceasta functie d'lui Nelu sa'i fie de folos la autentificare
		// user
		String className = "com.mysql.jdbc.Driver"; // path of driver class
		Class.forName(className); // Load the Driver

		// Create database connection
		IDBConnection conn = new DBConnection(Utils.DB_URL, Utils.DB_USER,
				Utils.DB_PASSWD, Utils.DB);
		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model model = maker.openModel("userModel");
		// Create a new query
		String queryString = "";
		System.out.println("Asta e useru ce'l verific  " + user.getUserName());
		queryString = " PREFIX name:<http://194.57.85.190/namespaces/export/squiz#> "
				+ " PREFIX domain:<http://194.57.85.190/domains.rdf#>"
				+ " PREFIX vcard:<http://www.w3.org/2001/vcard-rdf/3.0#>"
				+ " SELECT DISTINCT ?id ?user_name ?pass ?role ?f_name ?g_name "
				+ " WHERE {"
				+ " ?id name:user_name ?user_name . "
				+ " ?id name:password ?pass . "
				+ " ?id vcard:ROLE ?role . "
				+ " ?id vcard:N ?_name ."
				+ " ?_name   vcard:Family ?f_name ."
				+ " ?_name   vcard:Given ?g_name ."
				+ " FILTER (?user_name=\""
				+ user.getUserName() + "\")" + " }";
		// System.out.println(queryString);
		Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		// ResultSetFormatter.out(results);
		// System.out.println("in if user"+results.getRowNumber());

		if (results.hasNext())// results.getRowNumber()!=0 )
		{
			System.out.println("Asta exista, se afla, se gaseste.");

			QuerySolution s = results.nextSolution();
			System.out.println(s.getLiteral("pass"));
			if (user.getPassword().equals(s.getLiteral("pass").toString())) {
				user.setIsAuthenticated(true);
				user.setGivenName(s.getLiteral("g_name").toString());
				user.setFamilyName(s.getLiteral("f_name").toString());
				user.setRole(s.getLiteral("role").toString());
				user.setId(s.getResource("id").toString());
				user.setPassword("");

			} else {
				System.out
						.println("Asta Exista but, nu o bagat parola  buna. Asta umbla cu fofarlica . Nu te incurca cu el.");
			}
		} else {
			System.out.println("Asta nu exista moshule. Nu te incurca cu el.");

		}
		qe.close();
		return user;
	}

}// end clasa
