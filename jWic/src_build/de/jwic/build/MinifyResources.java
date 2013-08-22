/*
 * Copyright (c) 2009 Network Appliance, Inc.
 * All rights reserved.
 */

package de.jwic.build;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;

import com.yahoo.platform.yui.compressor.CssCompressor;
import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

/**
 * @author lippisch
 */
public class MinifyResources extends Task {

	private final static String PAGE_TAG_START = "#* PRODUCTION-MINIFY-START *#";
	private final static String PAGE_TAG_END = "#* PRODUCTION-MINIFY-END *#";
	
	private final static String BUILD_FILE_START_NAME = "jwic-all";
	
	private String versionTag;
	private String pageFile;
	
	private File src = null;
	
	private List<String> cssFiles = new ArrayList<String>();
	private List<String> jsFiles = new ArrayList<String>();
	
	/* (non-Javadoc)
	 * @see org.apache.tools.ant.Task#execute()
	 */
	@Override
	public void execute() throws BuildException {
		super.execute();
				
		try {
			updatePageFile();
			System.out.println("Generating minified CSS file..");
			generateMinified(BUILD_FILE_START_NAME + "-css_" + versionTag + ".css", cssFiles, true);
			System.out.println("Generating minified JS file..");
			generateMinified(BUILD_FILE_START_NAME + "-js_" + versionTag + ".js", jsFiles, false);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new BuildException("Something went wrong...", e);
		}
	}

	/**
	 * @param targetFile
	 * @param files
	 * @param isCss
	 * @throws IOException
	 */
	private void generateMinified(String targetFile, List<String> files, boolean isCss) throws IOException {
		
		File destDir = new File(pageFile).getParentFile();
		
		File destFile = new File(destDir, targetFile);
		if (destFile.exists()) {
			destFile.delete();
		}
		
		// create temp file to build "one" file for the minifier
		File tmpFile = File.createTempFile("all-res", isCss ? ".css" : ".js", destDir);
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(tmpFile)));
		// iterate over files and write it in.
		for (String file : files) {
			System.out.println("Compressing " + file);
			File daFile = new File(src, file.replace("$contextPath", ""));
			if (!daFile.exists()) {
				throw new RuntimeException("The file " + daFile.getAbsolutePath() + " can not be found.");
			}
			BufferedReader in = new BufferedReader(new FileReader(daFile));
			String line;
			out.println("/* content: " + file + " */");
			while ((line = in.readLine()) != null) {
				out.println(line);
			}
			in.close();
				
		}
		
		out.flush();
		out.close();
		
		// MINIFY THEM!!!!!
		Reader in = new FileReader(tmpFile);
		PrintWriter mfOut = new PrintWriter(new BufferedWriter(new FileWriter(destFile)));
		if (isCss) {
			CssCompressor comp = new CssCompressor(in);
			comp.compress(mfOut, 8000);
		} else {
			JavaScriptCompressor jsComp = new JavaScriptCompressor(in, new ErrorReporter() {
				
				@Override
				public void warning(String arg0, String arg1, int arg2, String arg3, int arg4) {
					System.out.println("warning: '" + arg0 + "' // '" + arg1 + "' // '" + arg2 + "' // '" + arg3 + "' // '" + arg4 + "'");
				}
				
				@Override
				public EvaluatorException runtimeError(String arg0, String arg1, int arg2, String arg3, int arg4) {
					System.out.println("runtimeError: " + arg0 + "' // '" + arg1 + "' // '" + arg2 + "' // '" + arg3 + "' // '" + arg4 + "'");
					return null;
				}
				
				@Override
				public void error(String arg0, String arg1, int arg2, String arg3, int arg4) {
					System.out.println("error: " + arg0 + "' // '" + arg1 + "' // '" + arg2 + "' // '" + arg3 + "' // '" + arg4 + "'");
				}
			});
			
			jsComp.compress(mfOut, 8000, false, true, true, true);
		}
		
		in.close();
		mfOut.close();
		
		tmpFile.delete();
	}

	/**
	 * @throws IOException
	 */
	private void updatePageFile() throws IOException {
		
		System.out.println("Updating " + pageFile + " (" + versionTag + ")");

		File src = new File(pageFile);
		if (!src.exists()) {
			System.out.println("Source file does not exist: " + pageFile);
			throw new RuntimeException("The file does not exist, please check what you are doing!");
		}
		
		File dest = File.createTempFile("pagefile", "page", src.getParentFile());
		
		// read files line by line and replace production part
		BufferedReader in = new BufferedReader(new FileReader(src));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(dest)));
		String line = null;
		boolean inside = false;
		
		System.out.println("Extracting CSS and JS files..");
		while ((line = in.readLine()) != null) {
			
			System.out.println(line);
			if (!inside) {
				if (line.indexOf(PAGE_TAG_START) != -1) {
					inside = true;
					
					// add the static minified line references
					out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\""+ BUILD_FILE_START_NAME + "-css_" + versionTag + ".css\">");
					out.println("<SCRIPT LANGUAGE=\"JavaScript\" SRC=\""+ BUILD_FILE_START_NAME + "-js_" + versionTag + ".js\"></SCRIPT>");
					
				} else {
					out.println(line);
				}
			} else {
				if (line.indexOf(PAGE_TAG_END) != -1) {
					inside = false;
				} else {
					detectResource(line, "<LINK", "HREF=\"", cssFiles);
					detectResource(line, "<SCRIPT", "SRC=\"", jsFiles);
				}
			}
		}
		
		in.close();
		out.flush();
		out.close();
		
		src.delete();
		dest.renameTo(src);
		
	}

	/**
	 * @param line
	 * @param linePrefix
	 * @param refAttr
	 * @param list
	 */
	private void detectResource(String line, String linePrefix, String refAttr, List<String> list) {

		String ucLine = line.toUpperCase();
		
		int idx = ucLine.indexOf(linePrefix);
		if (idx != -1) { // is a resource reference
			int idxHref = ucLine.indexOf(refAttr, idx);
			if (idxHref != -1) {
				int idxEnd = ucLine.indexOf("\"", idxHref + refAttr.length());
				if (idxEnd != -1) {
					String resFile = line.substring(idxHref + refAttr.length(), idxEnd);
					System.out.println("Detected resource: " + resFile);
					list.add(resFile);
				}
			}
		}

	}

	/**
	 * @return the versionTag
	 */
	public String getVersionTag() {
		return versionTag;
	}

	/**
	 * @param versionTag the versionTag to set
	 */
	public void setVersionTag(String versionTag) {
		this.versionTag = versionTag;
	}

	/**
	 * @return the pageFile
	 */
	public String getPageFile() {
		return pageFile;
	}

	/**
	 * @param pageFile the pageFile to set
	 */
	public void setPageFile(String pageFile) {
		this.pageFile = pageFile;
	}

	/**
	 * @return the src
	 */
	public File getSrc() {
		return src;
	}

	/**
	 * @param src the src to set
	 */
	public void setSrc(File src) {
		this.src = src;
	}
	
}
