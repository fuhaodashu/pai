//package com.pai.app.web.core.framework.web.servlet;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.URLDecoder;
//
//import javax.servlet.Servlet;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import com.pai.app.web.core.constants.WebConstants;
//import com.pai.base.core.util.ServletContextHelper;
//
//@SuppressWarnings("serial")
//public class CoreMediaServlet extends HttpServlet implements Servlet {
//	protected final transient Log logger = LogFactory
//			.getLog(CoreMediaServlet.class);
//
//	/*
//	 * (non-Java-doc)
//	 * 
//	 * @see javax.servlet.http.HttpServlet#HttpServlet()
//	 */
//	public CoreMediaServlet() {
//		super();
//	}
//
//	/*
//	 * (non-Java-doc)
//	 * 
//	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest arg0,
//	 * HttpServletResponse arg1)
//	 */
//	@Override
//	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1)
//			throws ServletException, IOException {
//		doPost(arg0, arg1);
//		// TODO Auto-generated method stub
//	}
//
//	/*
//	 * (non-Java-doc)
//	 * 
//	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest arg0,
//	 * HttpServletResponse arg1)
//	 */
//	@Override
//	protected void doPost(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		File file;
//		FileInputStream in = null;
//		String path = ServletContextHelper.getServletContext().getRealPath("/");
//		String reqPath = URLDecoder.decode(request.getRequestURI(),
//				WebConstants.DEFAULT_ENCODING);
//		if (!path.endsWith("/")) {
//			path += "/";
//		}
//		String filePath = path
//				+ reqPath.substring(reqPath.indexOf(request.getContextPath())
//						+ request.getContextPath().length());
//		try {
//			file = new File(filePath);
//			if (!file.isFile()) { // Must be a directory or something else
//				return;
//			}
//			in = new FileInputStream(file);
//		} catch (FileNotFoundException e) {
//			return;
//		} catch (SecurityException se) { // Be unavailable permanently
//			throw (new ServletException("Servlet lacks appropriate privileges."));
//		}
//
//		String mimeType = getServletContext().getMimeType(filePath);
//
//		// response.setContentType("text/html");
//		response.setContentType(mimeType);
//		OutputStream out = response.getOutputStream();
//		byte[] buf = new byte[1024];
//		int count = 0;
//		while ((count = in.read(buf)) >= 0) {
//			out.write(buf, 0, count);
//		}
//		in.close();
//		out.close();
//		// TODO Auto-generated method stub
//	}
//}