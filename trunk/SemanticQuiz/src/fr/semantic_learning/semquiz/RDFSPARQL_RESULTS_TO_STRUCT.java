package fr.semantic_learning.semquiz;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

import java.lang.String;
import java.util.Vector;



import fr.semantic_learning.semquiz.valueobjects.*;
//import fr.semantic_learning.semquiz.;

public class  RDFSPARQL_RESULTS_TO_STRUCT {
	//e dinamica in dimensiune . nu mai tre sa verific daca exista in vector elemente nule 
	//de fapt de amu inainte am sa o folosesc doar pe asta 
	private Vector <Object>solutie_vector=new Vector<Object>();
	
	//cap_tabel -- pun numele coloanelor din results,ca sa extrag valorile din tabel  mai tarziu
	private Vector <String>cap_tabel=new Vector<String>(); 
	public void ParseAsVectorOfStructureUnit(ResultSet results)
	{ //from resultset we extract information and format it as structure of a specified type an put it in "solutie" variable
		//just for return question for a specied domain
		String StructType="InterfaceRights";
		System.out.println("Convert results to Vector of Structure"+StructType+".... \n");
		
		QuerySolution s;
		int length=results.getResultVars().size();
		System.out.println("Cap tabel are dimensiunea :"+ length );
		int i=0;
			
		//put the name of variables in vector cap_tabel
		for (int j=0; j<length;j++ )
		{
			cap_tabel.add((String)results.getResultVars().get(j));
			System.out.println(j+" : "+(String)results.getResultVars().get(j) );
		}
		
		
		while(results.hasNext())//results.getRowNumber()!=0 )
		{
			StructureUnits temp = new StructureUnits();
			s=results.nextSolution(); 
			
			for (int j=0; j<length;j++ )
			{
				if (cap_tabel.get(j).equals("id")){temp.setId(s.getResource("id").toString());					}
				if (cap_tabel.get(j).equals("title")){temp.setTitle(s.getLiteral("title").toString());	}
				
			}
			
			solutie_vector.add(temp);
			System.out.println(i+": "+temp.getTitle());
		//	System.out.println("Atribuirea "+ i +" trecuta");
			i++;
		}
		System.out.println("Convert results to Vector of Structure"+StructType+"....Done. \n");
	} 		
	public void ParseAsVectorOfStructureQuiz(ResultSet results)
	{ //from resultset we extract information and format it as structure of a specified type an put it in "solutie" variable
		//just for return question for a specied domain
		String StructType="Quiz";
		System.out.println("Convert results to Vector of Structure"+StructType+".... \n");
		
		QuerySolution s;
		int length=results.getResultVars().size();
		System.out.println("Cap tabel are dimensiunea :"+ length );
		int i=0;
			
		//put the name of variables in vector cap_tabel
		for (int j=0; j<length;j++ )
		{
			cap_tabel.add((String)results.getResultVars().get(j));
			System.out.println(j+" : "+(String)results.getResultVars().get(j) );
		}
		
		
		while(results.hasNext())//results.getRowNumber()!=0 )
		{
			StructureQuiz temp = new StructureQuiz();
			s=results.nextSolution(); 
			
			for (int j=0; j<length;j++ )
			{
				if (cap_tabel.get(j).equals("id")){temp.setId(s.getResource("id").toString());					}
				if (cap_tabel.get(j).equals("title")){temp.setTitle(s.getLiteral("title").toString());	}
				if (cap_tabel.get(j).equals("id_quiz"))
				{
					temp.setId(s.getResource("id_quiz").toString());
					
					RDFSPARQL a=new RDFSPARQL();
					StructureQuiz struct=new StructureQuiz();
					Object[] w;
					try {
						w = a.returnAllQuiz(temp.getId());
						//aci tre facuta verificare daca este adus vreun quiz 
						//if(w.length>0)else da eroare "0"
						temp.setTitle( ((StructureQuiz)w[0]).getTitle());
						
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
			
			solutie_vector.add(temp);
			System.out.println(i+": "+temp.getTitle());
		//	System.out.println("Atribuirea "+ i +" trecuta");
			i++;
		}
		System.out.println("Convert results to Vector of Structure"+StructType+"....Done. \n");
	} 		
	public void ParseAsVectorOfStructureInterfaceRights(ResultSet results)
	{ //from resultset we extract information and format it as structure of a specified type an put it in "solutie" variable
		//just for return question for a specied domain
		String StructType="InterfaceRights";
		System.out.println("Convert results to Vector of Structure"+StructType+".... \n");
		
		QuerySolution s;
		int length=results.getResultVars().size();
		System.out.println("Cap tabel are dimensiunea :"+ length );
		int i=0;
			
		//put the name of variables in vector cap_tabel
		for (int j=0; j<length;j++ )
		{
			cap_tabel.add((String)results.getResultVars().get(j));
			System.out.println(j+" : "+(String)results.getResultVars().get(j) );
		}
		
		
		while(results.hasNext())//results.getRowNumber()!=0 )
		{
			StructureInterfaceRights temp = new StructureInterfaceRights();
			s=results.nextSolution(); 
			
			for (int j=0; j<length;j++ )
			{
				if (cap_tabel.get(j).equals("id")){temp.setId(s.getResource("id").toString());					}
				if (cap_tabel.get(j).equals("title")){temp.setIdInterface(s.getLiteral("title").toString());	}
				if (cap_tabel.get(j).equals("description")){temp.setDescription(s.getLiteral("description").toString());	}
			}
			
			solutie_vector.add(temp);
			System.out.println(i+": "+temp.getIdInterface());
		//	System.out.println("Atribuirea "+ i +" trecuta");
			i++;
		}
		System.out.println("Convert results to Vector of Structure"+StructType+"....Done. \n");
	} 		
	public void ParseAsVectorOfStructureYear(ResultSet results)
	{ //from resultset we extract information and format it as structure of a specified type an put it in "solutie" variable
		//just for return question for a specied domain
		String StructType="Years";
		System.out.println("Convert results to Vector of Structure"+StructType+".... \n");
		
		QuerySolution s;
		int length=results.getResultVars().size();
		System.out.println("Cap tabel are dimensiunea :"+ length );
		int i=0;
			
		//put the name of variables in vector cap_tabel
		for (int j=0; j<length;j++ )
		{
			cap_tabel.add((String)results.getResultVars().get(j));
			System.out.println(j+" : "+(String)results.getResultVars().get(j) );
		}
		
		
		while(results.hasNext())//results.getRowNumber()!=0 )
		{
			StructureYear temp = new StructureYear();
			String[] tempString=new String[2];
			s=results.nextSolution(); 
			
			for (int j=0; j<length;j++ )
			{
				if (cap_tabel.get(j).equals("id"))   { temp.setId(s.getResource("id").toString());	}
				if (cap_tabel.get(j).equals("title")){temp.setTitle(s.getLiteral("title").toString());	}
				if (cap_tabel.get(j).equals("id_semester"))
				{
					tempString[0]=s.getResource("id_semester").toString();
					s=results.nextSolution();
					tempString[1]=s.getResource("id_semester").toString();
					temp.setIdSemesters(tempString);
				}
			}
			
			solutie_vector.add(temp);
			System.out.println(i+": "+temp.getTitle());
		//	System.out.println("Atribuirea "+ i +" trecuta");
			i++;
		}
		System.out.println("Convert results to Vector of Structure"+StructType+"....Done. \n");
	} 			
	public void ParseAsVectorOfStructureEvaluationType(ResultSet results)
	{ //from resultset we extract information and format it as structure of a specified type an put it in "solutie" variable
		//just for return question for a specied domain
		String StructType="EvaluationType";
		System.out.println("Convert results to Vector of Structure"+StructType+".... \n");
		
		QuerySolution s;
		int length=results.getResultVars().size();
		System.out.println("Cap tabel are dimensiunea :"+ length );
		int i=0;
			
		//put the name of variables in vector cap_tabel
		for (int j=0; j<length;j++ )
		{
			cap_tabel.add((String)results.getResultVars().get(j));
			System.out.println(j+" : "+(String)results.getResultVars().get(j) );
		}
		
		
		while(results.hasNext())//results.getRowNumber()!=0 )
		{
			StructureEvaluationType temp = new StructureEvaluationType();
			s=results.nextSolution(); 
			
			for (int j=0; j<length;j++ )
			{
				if (cap_tabel.get(j).equals("id")){temp.setId(s.getResource("id").toString());					}
				if (cap_tabel.get(j).equals("title")){temp.setTitle(s.getLiteral("title").toString());			}
			}
			
			solutie_vector.add(temp);
			System.out.println(i+": "+temp.getTitle());
		//	System.out.println("Atribuirea "+ i +" trecuta");
			i++;
		}
		System.out.println("Convert results to Vector of Structure"+StructType+"....Done. \n");
	} 		
	public void ParseAsVectorOfStructureUserRights(ResultSet results) throws ClassNotFoundException
	{ //from resultset we extract information and format it as structure of a specified type an put it in "solutie" variable
		//just for return question for a specied domain
		String StructType="UserRights";
		System.out.println("Convert results to Vector of Structure"+StructType+".... \n");
		
		QuerySolution s;
		int length=results.getResultVars().size();
		System.out.println("Cap tabel are dimensiunea :"+ length );
		int i=0;
			
		//put the name of variables in vector cap_tabel
		for (int j=0; j<length;j++ )
		{
			cap_tabel.add((String)results.getResultVars().get(j));
			System.out.println(j+" : "+(String)results.getResultVars().get(j) );
		}
		
		Vector <String> idInterface =new Vector <String>();
		Vector <Boolean> isAllowed =new Vector <Boolean>();
		while(results.hasNext())//results.getRowNumber()!=0 )
		{
		s=results.nextSolution(); 
			
			for (int j=0; j<length;j++ )
			{
				if (cap_tabel.get(j).equals("idInterface")){
					idInterface.add(s.getResource("idInterface").toString());
				System.out.println("IDInterface : "+s.getResource("idInterface").toString());	
				}
				if (cap_tabel.get(j).equals("isAllowed")){isAllowed.add(s.getLiteral("isAllowed").getBoolean());			}
			}
		//solutie_vector.add(temp);
			System.out.println(i+": ");//+temp.getIdInterface());
		//	System.out.println("Atribuirea "+ i +" trecuta");
			i++;
		}
		Vector <StructureInterfaceRights> t =new Vector<StructureInterfaceRights>();
		RDFSPARQL a=new RDFSPARQL();
		for (int x=0;x<idInterface.size();x++)
		{ StructureInterfaceRights struct=new StructureInterfaceRights();
			System.out.println("IDInterface : "+idInterface.get(x));	
		  Object[]w=a.ChooseRightsAction(idInterface.get(x));
			struct.setDescription(((StructureInterfaceRights)w[0]).getDescription());
			struct.setIdInterface(((StructureInterfaceRights)w[0]).getIdInterface());
			struct.setIsAllowed(isAllowed.get(x));
			System.out.println(x+" : "+" Description : "+struct.getDescription());
			System.out.println(x+" : "+" Titlu : "+struct.getIdInterface());
			System.out.println(x+" : "+" isAllowed  : "+struct.getIsAllowed());
			solutie_vector.add(struct);	
		}
		
		System.out.println("Convert results to Vector of Structure"+StructType+"....Done. \n");
	} 	
	public void ParseAsVectorOfStructureSpecialization(ResultSet results)
	{ //from resultset we extract information and format it as structure of a specified type an put it in "solutie" variable
		//just for return question for a specied domain
		String StructType="Specialization";
		System.out.println("Convert results to Vector of Structure"+StructType+".... \n");
		
		QuerySolution s;
		int length=results.getResultVars().size();
		System.out.println("Cap tabel are dimensiunea :"+ length );
		int i=0;
			
		//put the name of variables in vector cap_tabel
		for (int j=0; j<length;j++ )
		{
			cap_tabel.add((String)results.getResultVars().get(j));
		}
		
		
		while(results.hasNext())//results.getRowNumber()!=0 )
		{
			StructureSpecialization temp = new StructureSpecialization();
			s=results.nextSolution(); 
			
			for (int j=0; j<length;j++ )
			{
				if (cap_tabel.get(j).equals("id")){temp.setId(s.getResource("id").toString());					}
				if (cap_tabel.get(j).equals("title")){temp.setTitle(s.getLiteral("title").toString());			}
			}
			
			solutie_vector.add(temp);
			System.out.println(i+": "+temp.getTitle());
		//	System.out.println("Atribuirea "+ i +" trecuta");
			i++;
		}
		System.out.println("Convert results to Vector of Structure"+StructType+"....Done. \n");
	} 
	public void ParseAsVectorOfStructureParcours(ResultSet results)
	{ //from resultset we extract information and format it as structure of a specified type an put it in "solutie" variable
		//just for return question for a specied domain
		String StructType="Parcours";
		System.out.println("Convert results to Vector of Structure"+StructType+".... \n");
		
		QuerySolution s;
		int length=results.getResultVars().size();
		System.out.println("Cap tabel are dimensiunea :"+ length );
		int i=0;
			
		//put the name of variables in vector cap_tabel
		for (int j=0; j<length;j++ )
		{
			cap_tabel.add((String)results.getResultVars().get(j));
		}
		
		
		while(results.hasNext())//results.getRowNumber()!=0 )
		{
			StructureParcours temp = new StructureParcours();
			s=results.nextSolution(); 
			
			for (int j=0; j<length;j++ )
			{
				if (cap_tabel.get(j).equals("id")){temp.setId(s.getResource("id").toString());					}
				if (cap_tabel.get(j).equals("title")){temp.setTitle(s.getLiteral("title").toString());			}
			}
			
			solutie_vector.add(temp);
			System.out.println(i+": "+temp.getTitle());
		//	System.out.println("Atribuirea "+ i +" trecuta");
			i++;
		}
		System.out.println("Convert results to Vector of Structure"+StructType+"....Done. \n");
	} 
	public void ParseAsVectorOfStructureFormation(ResultSet results)
	{ //from resultset we extract information and format it as structure of a specified type an put it in "solutie" variable
		//just for return question for a specied domain
		System.out.println("Convert results to Vector of StructureFormation.... \n");
		
		QuerySolution s;
		int length=results.getResultVars().size();
		System.out.println("Cap tabel are dimensiunea :"+ length );
		int i=0;
			
		//put the name of variables in vector cap_tabel
		for (int j=0; j<length;j++ )
		{
			cap_tabel.add((String)results.getResultVars().get(j));
		}
		
		
		while(results.hasNext())//results.getRowNumber()!=0 )
		{
			StructureFormation temp = new StructureFormation();
			s=results.nextSolution(); 
			
			for (int j=0; j<length;j++ )
			{
				if (cap_tabel.get(j).equals("id")){temp.setId(s.getResource("id").toString());					}
				if (cap_tabel.get(j).equals("title")){temp.setTitle(s.getLiteral("title").toString());			}
			}
			
			solutie_vector.add(temp);
			System.out.println(i+": "+temp.getTitle());
		//	System.out.println("Atribuirea "+ i +" trecuta");
			i++;
		}
		System.out.println("Convert results to Vector of StructureFormation....Done. \n");
	} 
	public void ParseAsVectorOfStructureCompetence(ResultSet results)
	{ //from resultset we extract information and format it as structure of a specified type an put it in "solutie" variable
		//just for return question for a specied domain
		String StructName="Competence";
		System.out.println("Convert results to Vector of Structure"+StructName+".... \n");
		
		QuerySolution s;
		int length=results.getResultVars().size();
		System.out.println("Cap tabel are dimensiunea :"+ length );
		int i=0;
			
		//put the name of variables in vector cap_tabel
		for (int j=0; j<length;j++ )
		{
			cap_tabel.add((String)results.getResultVars().get(j));
		}
		
		
		while(results.hasNext())//results.getRowNumber()!=0 )
		{
			StructureCompetence temp = new StructureCompetence();
			s=results.nextSolution(); 
			
			for (int j=0; j<length;j++ )
			{
				
				
				if (cap_tabel.get(j).equals("id")){temp.setId(s.getResource("id").toString());					}
				if (cap_tabel.get(j).equals("title")){temp.setTitle(s.getLiteral("title").toString());			}
								
			}
			
			solutie_vector.add(temp);
			System.out.println(i+": "+temp.getTitle());
		//	System.out.println("Atribuirea "+ i +" trecuta");
			i++;
		}
		System.out.println("Convert results to Vector of Structure"+StructName+"....Done. \n");
	} 
	public void ParseAsVectorOfStructureSemester(ResultSet results)
	{ //from resultset we extract information and format it as structure of a specified type an put it in "solutie" variable
		//just for return question for a specied domain
		String StructName="Semester";
		System.out.println("Convert results to Vector of Structure"+StructName+".... \n");
		
		QuerySolution s;
		int length=results.getResultVars().size();
		System.out.println("Cap tabel are dimensiunea :"+ length );
		int i=0;
			
		//put the name of variables in vector cap_tabel
		for (int j=0; j<length;j++ )
		{
			cap_tabel.add((String)results.getResultVars().get(j));
		}
		
		
		while(results.hasNext())//results.getRowNumber()!=0 )
		{
			StructureSemester temp = new StructureSemester();
			s=results.nextSolution(); 
			
			for (int j=0; j<length;j++ )
			{
				
				
				if (cap_tabel.get(j).equals("id")){temp.setId(s.getResource("id").toString());					}
				if (cap_tabel.get(j).equals("title")){temp.setTitle(s.getLiteral("title").toString());			}
								
			}
			
			solutie_vector.add(temp);
			System.out.println(i+": "+temp.getTitle());
		//	System.out.println("Atribuirea "+ i +" trecuta");
			i++;
		}
		System.out.println("Convert results to Vector of Structure"+StructName+"....Done. \n");
	} 	
	public void ParseAsVectorOfStructureMention(ResultSet results)
	{ //from resultset we extract information and format it as structure of a specified type an put it in "solutie" variable
		//just for return question for a specied domain
		String StructName="Mention";
		System.out.println("Convert results to Vector of Structure"+StructName+".... \n");
		
		QuerySolution s;
		int length=results.getResultVars().size();
		System.out.println("Cap tabel are dimensiunea :"+ length );
		int i=0;
			
		//put the name of variables in vector cap_tabel
		for (int j=0; j<length;j++ )
		{
			cap_tabel.add((String)results.getResultVars().get(j));
		}
		
		
		while(results.hasNext())//results.getRowNumber()!=0 )
		{
			StructureMention temp = new StructureMention();
			s=results.nextSolution(); 
			
			for (int j=0; j<length;j++ )
			{
				
				
				if (cap_tabel.get(j).equals("id")){temp.setId(s.getResource("id").toString());					}
				if (cap_tabel.get(j).equals("title")){temp.setTitle(s.getLiteral("title").toString());			}
								
			}
			
			solutie_vector.add(temp);
			System.out.println(i+": "+temp.getTitle());
		//	System.out.println("Atribuirea "+ i +" trecuta");
			i++;
		}
		System.out.println("Convert results to Vector of Structure"+StructName+"....Done. \n");
	} 
	public void ParseAsVectorOfStructureSubsection(ResultSet results)
	{ //from resultset we extract information and format it as structure of a specified type an put it in "solutie" variable
		//just for return question for a specied domain
		String StructName="Subsection";
		System.out.println("Convert results to Vector of Structure"+StructName+".... \n");
		
		QuerySolution s;
		int length=results.getResultVars().size();
		System.out.println("Cap tabel are dimensiunea :"+ length );
		int i=0;
			
		//put the name of variables in vector cap_tabel
		for (int j=0; j<length;j++ )
		{
			cap_tabel.add((String)results.getResultVars().get(j));
		}
		
		
		while(results.hasNext())//results.getRowNumber()!=0 )
		{
			StructureSubsection temp = new StructureSubsection();
			s=results.nextSolution(); 
			
			for (int j=0; j<length;j++ )
			{
				
				
				if (cap_tabel.get(j).equals("id")){temp.setId(s.getResource("id").toString());					}
				if (cap_tabel.get(j).equals("title")){temp.setTitle(s.getLiteral("title").toString());			}
								
			}
			
			solutie_vector.add(temp);
			System.out.println(i+": "+temp.getTitle());
		//	System.out.println("Atribuirea "+ i +" trecuta");
			i++;
		}
		System.out.println("Convert results to Vector of Structure"+StructName+"....Done. \n");
	} 	
	public void ParseAsVectorOfStructureSection(ResultSet results)
	{ //from resultset we extract information and format it as structure of a specified type an put it in "solutie" variable
		//just for return question for a specied domain
		String StructName="Section";
		System.out.println("Convert results to Vector of Structure"+StructName+".... \n");
		
		QuerySolution s;
		int length=results.getResultVars().size();
		System.out.println("Cap tabel are dimensiunea :"+ length );
		int i=0;
			
		//put the name of variables in vector cap_tabel
		for (int j=0; j<length;j++ )
		{
			cap_tabel.add((String)results.getResultVars().get(j));
		}
		
		
		while(results.hasNext())//results.getRowNumber()!=0 )
		{
			StructureDisciplineSection temp = new StructureDisciplineSection();
			s=results.nextSolution(); 
			
			for (int j=0; j<length;j++ )
			{
				if (cap_tabel.get(j).equals("id")){temp.setId(s.getResource("id").toString());					}
				if (cap_tabel.get(j).equals("title")){temp.setTitle(s.getLiteral("title").toString());			}
				if (cap_tabel.get(j).equals("code")){temp.setCod(s.getLiteral("code").toString());			}
			}
			
			solutie_vector.add(temp);
			System.out.println(i+": "+temp.getTitle());
		//	System.out.println("Atribuirea "+ i +" trecuta");
			i++;
		}
		System.out.println("Convert results to Vector of Structure"+StructName+"....Done. \n");
	} 		
	public void ParseAsVectorOfStructureVariants(ResultSet results)
	{ //from resultset we extract information and format it as structure of a specified type an put it in "solutie" variable
		//just for return question for a specied domain
		String StructName="Variants";
		System.out.println("Convert results to Vector of Structure"+StructName+".... \n");
		
		QuerySolution s;
		int length=results.getResultVars().size();
		System.out.println("Cap tabel are dimensiunea :"+ length );
		int i=0;
			
		//put the name of variables in vector cap_tabel
		for (int j=0; j<length;j++ )
		{
			cap_tabel.add((String)results.getResultVars().get(j));
		}
		
		
		while(results.hasNext())//results.getRowNumber()!=0 )
		{
			StructureQuestionVariants temp = new StructureQuestionVariants();
			s=results.nextSolution(); 
			
			for (int j=0; j<length;j++ )
			{
				if (cap_tabel.get(j).equals("text"))
				{
					temp.setText(s.getLiteral("text").toString());			
					System.out.println(i+": Text Variant : "+temp.getText());
				}
				if (cap_tabel.get(j).equals("picture"))
				{if (s.getLiteral("picture")!=null){
					temp.setPicture(s.getLiteral("picture").toString());
					System.out.println(i+": Picture Variant : "+temp.getPicture());
					}
				}
				if (cap_tabel.get(j).equals("valid"))
				{if (s.getLiteral("valid")!=null)
					temp.setValidation(s.getLiteral("valid").getBoolean());			
				}
				if (cap_tabel.get(j).equals("sound"))
				{if (s.getLiteral("sound")!=null){
					temp.setSound(s.getLiteral("sound").toString());		
					System.out.println(i+": Sound Variant : "+temp.getSound());
					}
				}				
				if (cap_tabel.get(j).equals("video"))
				{if (s.getLiteral("video")!=null){
					temp.setVideo(s.getLiteral("video").toString());			
					System.out.println(i+": Video Variant : "+temp.getVideo());
					}
				}
				
			}
			
			solutie_vector.add(temp);
		//	System.out.println(i+" : "+temp.getText());
		//	System.out.println("Atribuirea "+ i +" trecuta");
			i++;
		}
		System.out.println("Convert results to Vector of Structure"+StructName+"....Done. \n");
	} 			
	public void ParseAsVectorOfStructureQuestion(ResultSet results)
	{ //from resultset we extract information and format it as structure of a specified type an put it in solutie variable
		//just for return question for a specied domain
		System.out.println("Convert results to Vector of Objects....StructQuestion. \n");
		QuerySolution s;
		int length=results.getResultVars().size();
		int i=0;	
		System.out.println("Cap tabel are dimensiunea :"+ length );

		//put the name of variables in vector cap_tabel
		for (int j=0; j<length;j++ )
		{
			cap_tabel.add((String)results.getResultVars().get(j));
		}
		
		while(results.hasNext())//results.getRowNumber()!=0 )
		{
			StructureQuestion temp = new StructureQuestion();
			s=results.nextSolution(); 
			
			for (int j=0; j<length;j++ )
			{
				
				if (cap_tabel.get(j).equals("id")){temp.setId(s.getResource("id").toString());					}
				if (cap_tabel.get(j).equals("qtext"))
				{
					temp.setText(s.getLiteral("qtext").toString());			
					System.out.println(i+": Text Question : "+temp.getText());
				}
				if (cap_tabel.get(j).equals("picture"))
				{if (s.getLiteral("picture")!=null){
					temp.setPicture(s.getLiteral("picture").toString());	
					System.out.println(i+": Picture Question : "+temp.getPicture());
					}
				}
				if (cap_tabel.get(j).equals("video"))
				{if (s.getLiteral("video")!=null){
					temp.setVideo(s.getLiteral("video").toString());			
					System.out.println(i+": Video Question : "+temp.getVideo());
					}
				}
				if (cap_tabel.get(j).equals("sound"))
				{if (s.getLiteral("sound")!=null){
					temp.setSound(s.getLiteral("sound").toString());		
					System.out.println(i+": Sound Question : "+temp.getSound());
					}
				}				
				//pt fiecare varianta de raspuns tre adus din graf toate info 
						
				if (cap_tabel.get(j).equals("variant_id"))
				{Vector<StructureQuestionVariants> var=new Vector<StructureQuestionVariants>();
					int k=0;
					while(results.hasNext())
					{ 
						if (s.getResource("variant_id")!=null)
						{						
							RDFSPARQL a=new RDFSPARQL();
							
							try {
									System.out.println("ID_VARIANTA : "+s.getResource("variant_id").toString());
									System.out.println("Vreau sa aduc a : "+k+" -a  varianta de raspuns.");
									Object[] retFunc;
									retFunc=a.returnVariantById(s.getResource("variant_id").toString());
									if (retFunc.length>0){var.add((StructureQuestionVariants)retFunc[0]);}	
									System.out.println("Am Adus a : "+k+" -a  varianta de raspuns.");
								}
							catch (Exception e) 
								{
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						}	
						s=results.nextSolution();
						k++;
					}
					System.out.println("Am ieshit din while.");
					int ww=0;
					StructureQuestionVariants[] struct=new StructureQuestionVariants[var.size()];
					while(ww<var.size())
					{	
						struct[ww]=var.get(ww);
						ww++;
					}//while
					temp.setVariants(struct);
				}//if capttabel=variants
			
			}
			System.out.println("Am pus intrebarea : "+i+" in solutie_vector.");
			solutie_vector.add(temp);
			i++;
		}
		
	System.out.println("Solution parsed as vector of StructQuestion... Done.");	
	}
	public void ParseAsVectorOfStructureUser(ResultSet results)
	{ //from resultset we extract information and format it as structure of a specified type an put it in solutie variable
		//just for return userStruct
		System.out.println("Convert results to Vector of Objects....StructureUser \n");
		
		QuerySolution s;
		
	
		int i=0;
		int length=results.getResultVars().size();
		//put the name of variables in vector cap_tabel
		for (int j=0; j<length;j++ )
		{
			cap_tabel.add((String)results.getResultVars().get(j));
			System.out.println("cap_tabel:"+j+": "+cap_tabel.get(j));
		}
				
		
		while(results.hasNext())
		{	StructureUser temp = new StructureUser();
			s=results.nextSolution(); 
			for (int j=0; j<length;j++ )
			{
				
				if (cap_tabel.get(j)=="domains"){temp.setId(s.getResource("domains").toString());		}
				if (cap_tabel.get(j)=="email"){temp.setId(s.getResource("email").toString());			}
				if (cap_tabel.get(j).equals("full_name")){
					temp.setFamilyName((s.getLiteral("full_name").toString()));
					System.out.println(i+" : SetFamilyName :"+temp.getFamilyName().toString());
					}
				if (cap_tabel.get(j)=="familyName"){temp.setId(s.getResource("familyName").toString());	}
				if (cap_tabel.get(j)=="givenName"){temp.setId(s.getResource("givenName").toString());	}
				if (cap_tabel.get(j).equals("id")){
				
						temp.setId(s.getResource("id").toString());					
						System.out.println(i+" : SetId :"+temp.getId().toString());	
					}
				if (cap_tabel.get(j)=="language"){temp.setId(s.getResource("language").toString());		}
				if (cap_tabel.get(j)=="org"){temp.setId(s.getResource("org").toString());				}
				if (cap_tabel.get(j)=="password"){temp.setId(s.getResource("password").toString());		}
				if (cap_tabel.get(j)=="rights"){temp.setId(s.getResource("rights").toString());			}
				if (cap_tabel.get(j)=="role"){temp.setId(s.getResource("role").toString());				}
				if (cap_tabel.get(j)=="title"){temp.setId(s.getResource("title").toString());			}
				if (cap_tabel.get(j)=="url"){temp.setId(s.getResource("url").toString());				}
				if (cap_tabel.get(j).equals("user_name")){	
					if(s.getLiteral("user_name")!=null){
						temp.setUserName(s.getLiteral("user_name").toString());	
						System.out.println(i+" : SetUser :"+temp.getUserName().toString());
					}
				}
			}
			solutie_vector.add(temp);
			temp=(StructureUser)solutie_vector.get(i);
			System.out.println("ID:"+temp.getId());
		//	System.out.println(i+": User name : "+temp.getUserName());
		//	System.out.println("Atribuirea "+ i +" trecuta");
			i++;
		}
	System.out.println("Solution parsed as vector of StructureUser... Done.");	
	}
	public void ParseAsVectorOfStrings(ResultSet results,String tip)
		{
			System.out.println("Convert results to Vector. \n");
			QuerySolution s;
			String lista=(String) results.getResultVars().get(0);
			int i=0;
			while(results.hasNext())//results.getRowNumber()!=0 )
			{	
				s=results.nextSolution(); 
				//ResultSetFormatter.out(results);
				System.out.println("Solutia: " + i+" : ");
				if(tip.equals("l"))
				{
					solutie_vector.add(s.getLiteral(lista).toString());
				}else
				{
					solutie_vector.add(s.getResource(lista).toString());
				}
				System.out.println(solutie_vector.get(i)+"\n");
				i++;
			}
		}
	public String stripBeforeDiez(String string)
	{	//taie ce e inaintea semnului "#"
		//=>"domain1"
			
		String temp=new String();
		System.out.print("Split : "+string); 
		temp=string.split("#")[1];
		System.out.println("  ==>  "+temp);
		
		return temp;
	}
	public Object[] getSolution()
	{	//returnaza variabila privata de tip vector ce a fost procesata anterior solutie_vector
		System.out.println("Get solution.....Done.");
		return solutie_vector.toArray();
	}

}//end class;
