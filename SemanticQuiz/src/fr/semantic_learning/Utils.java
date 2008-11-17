/**
 * Utility class
 */
package fr.semantic_learning;

import java.util.UUID;
import java.security.MessageDigest;

/**
 * @author radu
 * 
 */
public class Utils {

	/**
	 * This database connection settings 
	 */
	public static String DB_URL = "jdbc:mysql://194.57.85.190:3306/slcms"; //database URL
	public static String DB_USER = "slcms_user"; // database user id
	public static String DB_PASSWD = "slcms_1y0t2"; // database password
	public static String DB = "MySQL"; // database type
	
	/**
	 * @author cipri
	 * @param text
	 * @return
	 * 
	 * This function will return a unique numeral ID startig from a text seed 
	 */
	public static String GetUniqueID(String text)
	{
		try {
			UUID uid = UUID.randomUUID();
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			
			byte[] uidresult = sha.digest((text.trim() + uid.toString()).getBytes());
			
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < uidresult.length; i++) {
				hexString.append(Integer.toHexString(0xFF & uidresult[i]));
			}
			
			return hexString.toString();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
}
