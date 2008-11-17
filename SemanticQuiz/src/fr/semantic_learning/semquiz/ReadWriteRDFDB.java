package fr.semantic_learning.semquiz;

import com.hp.hpl.jena.db.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.*;
import java.io.*;

/**
 * @author ciprian
 *
 * Comments your class here !
 */
public class ReadWriteRDFDB {
	public static String WriteRDF(Model mod)	{//, String modelname
		String dbAnswer="";
		try {
			String className = "com.mysql.jdbc.Driver"; // path of driver class
			Class.forName(className); // Load the Driver
			String DB_URL = "jdbc:mysql://194.57.85.190:3306/questionsRDF"; // URL of database
			String DB_USER = "leonardo"; // database user id
			String DB_PASSWD = "leonardo"; // database password
			String DB = "MySQL"; // database type

/*			String className = "com.mysql.jdbc.Driver"; // path of driver class
			Class.forName(className); // Load the Driver
			String DB_URL = "jdbc:mysql://localhost:3306/questionsRDF"; // URL of database
			String DB_USER = "root"; // database user id
			String DB_PASSWD = "root"; // database password
			String DB = "MySQL"; // database type
*/			
			// Create database connection
			IDBConnection conn = new DBConnection(DB_URL, DB_USER, DB_PASSWD, DB);
			
		    ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
			// create an empty model
			Model model = maker.createDefaultModel();
			model = mod;
			//model.commit();
			conn.close();
			dbAnswer = "OK";
		} 
		catch (Exception e) {		
			System.out.println(e.getMessage());
			dbAnswer = e.getMessage();
		}
		return 	dbAnswer;
		//return "No";
	}	

}
