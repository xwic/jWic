/**
 * 
 */
package de.jwic.demo.wizard;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * A wizard works best when sharing a model amongst the pages. 

 * @author lippisch
 */
public class WizardGeneratorModel implements Serializable {

	private final static String TPL_WIZARD_JAVA = "wizTpl_wizard_java.vtl";
	private final static String TPL_WIZARD_PAGE_JAVA = "wizTpl_wizard_page_java.vtl";
	private final static String TPL_WIZARD_PAGE_VTL = "wizTpl_wizard_page_vtl.vtl";
	private final static String TPL_WIZARD_MODEL = "wizTpl_wizard_model_java.vtl";
	
	
	private PropertyChangeSupport chgSupport;
	
	private String title = "Untitled";
	private int wizardHeight = 400;
	private int wizardWidth = 500;
	private String packageName = "de.jwic.demo.mywizard";
	private String wizardClass = "";
	private String modelClass = "";
	
	private List<WizardPageConfig> pages = new ArrayList<WizardPageConfig>();

	private List<GeneratedFile> generatedFiles = new ArrayList<GeneratedFile>();

	/**
	 * Construct a new model.
	 */
	public WizardGeneratorModel() {
		
		chgSupport = new PropertyChangeSupport(this);
		
	}

	
	/**
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		chgSupport.addPropertyChangeListener(listener);
	}

	/**
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		chgSupport.removePropertyChangeListener(listener);
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		String old = this.title;
		this.title = title;
		chgSupport.firePropertyChange("title", old, title);
	}


	/**
	 * @return the wizardHeight
	 */
	public int getWizardHeight() {
		return wizardHeight;
	}


	/**
	 * @param wizardHeight the wizardHeight to set
	 */
	public void setWizardHeight(int wizardHeight) {
		int old = this.wizardHeight;
		this.wizardHeight = wizardHeight;
		chgSupport.firePropertyChange("wizardHeight", old, wizardHeight);
	}


	/**
	 * @return the wizardWidth
	 */
	public int getWizardWidth() {
		return wizardWidth;
	}


	/**
	 * @param wizardWidth the wizardWidth to set
	 */
	public void setWizardWidth(int wizardWidth) {
		int old = this.wizardWidth;
		this.wizardWidth = wizardWidth;
		chgSupport.firePropertyChange("wizardWidth", old, wizardWidth);
	}


	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}


	/**
	 * @param packageName the packageName to set
	 */
	public void setPackageName(String packageName) {
		String old = this.packageName;
		this.packageName = packageName;
		chgSupport.firePropertyChange("packageName", old, packageName);
	}


	/**
	 * @return the wizardClass
	 */
	public String getWizardClass() {
		return wizardClass;
	}


	/**
	 * @param wizardClass the wizardClass to set
	 */
	public void setWizardClass(String wizardClass) {
		String old = this.wizardClass;
		this.wizardClass = wizardClass;
		chgSupport.firePropertyChange("wizardClass", old, wizardClass);
	}


	/**
	 * @return the modelClass
	 */
	public String getModelClass() {
		return modelClass;
	}


	/**
	 * @param modelClass the modelClass to set
	 */
	public void setModelClass(String modelClass) {
		String old = this.modelClass;
		this.modelClass = modelClass;
		chgSupport.firePropertyChange("wizardClass", old, modelClass);
	}


	/**
	 * 
	 */
	public void addPage(WizardPageConfig pageConfig) {
		pages.add(pageConfig);
		chgSupport.firePropertyChange("pages", null, wizardHeight);
	}


	/**
	 * @return the pages
	 */
	public List<WizardPageConfig> getPages() {
		return pages;
	}


	/**
	 * Generate the files for the wizard based upon the model configuration. 
	 * 
	 */
	public void generateFiles() {

		VelocityEngine ve = new VelocityEngine();
		VelocityContext ctx = new VelocityContext();
		
		ctx.put("model", this);

		generatedFiles.clear();
		
		generateFile(ve, ctx, wizardClass + ".java", TPL_WIZARD_JAVA);
		generateFile(ve, ctx, modelClass + ".java", TPL_WIZARD_MODEL);
		
		for (WizardPageConfig page : pages) {
			ctx.put("page", page);
			generateFile(ve, ctx, page.getClassName() + ".java", TPL_WIZARD_PAGE_JAVA);
			generateFile(ve, ctx, page.getClassName() + ".vtl", TPL_WIZARD_PAGE_VTL);
		}
		
		Collections.sort(generatedFiles);
				
	}


	/**
	 * @param ve
	 * @param ctx
	 * @param string
	 * @param tplWizardJava
	 */
	private void generateFile(VelocityEngine ve, VelocityContext ctx, String filename, String tplName) {
		
		GeneratedFile file = new GeneratedFile();
		file.setFilename(filename);
		
		ByteArrayOutputStream ba = new ByteArrayOutputStream();
		PrintWriter out = new PrintWriter(ba);
		
		String tplFile = "/" + getClass().getPackage().getName().replace('.', '/') + "/" + tplName;
		
		InputStream in = getClass().getResourceAsStream(tplFile);
		if (in == null) {
			throw new IllegalStateException("Cannot find template " + tplFile + " in classpath!");
		}
		try {
			ve.evaluate(ctx, out, "WizardGenerator", new InputStreamReader(in));
			out.flush();
			file.setContent(ba.toString());
			generatedFiles.add(file);
			
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}


	/**
	 * @return the generatedFiles
	 */
	public List<GeneratedFile> getGeneratedFiles() {
		return generatedFiles;
	}


	/**
	 * @param pageConfigs
	 */
	public void setPages(List<WizardPageConfig> pageConfigs) {
		this.pages = pageConfigs;
	}
	
}
