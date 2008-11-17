<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="org.apache.commons.fileupload.FileUploadException"%>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@ page import="org.apache.commons.fileupload.FileItem"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.io.File"%>

<?xml version="1.0"?>
<%
	try {
		System.out.println("\n\n***** ProcessFileUpload request********\n\n");
		System.out.println("Content Type:\t" + request.getContentType());

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		// this directory is the absoute path to root of the saving resources
		String a_rootDir = request.getParameter("rootDir");		
		// this directory is the absoute path to question unique ID
		String a_questionDir = request.getParameter("questionDir");
		// this directory is the absoute path to question resources directory (ex. main, answers...)
		String a_resourceDir = request.getParameter("resourceDir");
		
		String r_rootDir = pageContext.getServletContext().getRealPath(a_rootDir);
		System.out.println("r_rootDir:" + r_rootDir);
		
		// Testing the uploade dir
		File dir = new File(r_rootDir);
		if (!dir.exists()) {
			dir.mkdir();
		}
		
		String r_questionDir = pageContext.getServletContext().getRealPath(a_questionDir);
		System.out.println("r_questionDir:" + r_questionDir);
		
		// Testing the uploade dir
		dir = new File(r_questionDir);
		if (!dir.exists()) {
			dir.mkdir();
		}

		String r_resourceDir = pageContext.getServletContext().getRealPath(a_resourceDir);
		System.out.println("r_resourceDir:" + r_resourceDir);
		
		// Testing the uploade dir
		dir = new File(r_resourceDir);
		if (!dir.exists()) {
			dir.mkdir();
		}
		
		// If file size exceeds this value, a FileUploadException is thrown
		//upload.setSizeMax(1000000);

		//Get a list of items from the request
		//Items can be either form fields or files
		List fileItems = upload.parseRequest(request);
		Iterator itr = fileItems.iterator();
		
		System.out.println("List fileItems = upload.parseRequest(request);");
		
		// The fileItems List contains FileItem instances, each of
		// which can be either a form field or a file.  Determine which
		// calling FileItem.isFormField(); if it returns false, then it's
		// a file.  Requests generated by Flash Player's FileReference can
		// only contain a single file, so we only write 1 file to disk each
		// time this file is called	     
		while (itr.hasNext()) {

			FileItem fi = (FileItem) itr.next();

			if (!fi.isFormField()) {
				System.out.println("FIELD NAME:\t" + fi.getFieldName());
				System.out.println("NAME:\t\t" + fi.getName());
				System.out.println("SIZE:\t\t" + fi.getSize());

				File fNew = new File(r_resourceDir, fi.getFieldName());
				System.out.println("PATH:\t\t" + fNew.getAbsolutePath());
				fi.write(fNew);
				break;
			}
		}
	} catch (FileUploadException e) {
		System.out.println("Error " + e.toString());
	}
%>