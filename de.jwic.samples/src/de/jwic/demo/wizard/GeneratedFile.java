/**
 * 
 */
package de.jwic.demo.wizard;


/**
 * Contains the filename and content of the generated file.
 * 
 * @author lippisch
 */
public class GeneratedFile implements Comparable<GeneratedFile> {

	private String filename;
	private String content;
	
	
	/**
	 * 
	 */
	public GeneratedFile() {
		super();
	}
	
	/**
	 * @param filename
	 * @param content
	 */
	public GeneratedFile(String filename, String content) {
		super();
		this.filename = filename;
		this.content = content;
	}
	
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(GeneratedFile o) {
		if (this.filename == null && o.filename == null) {
			return 0;
		}
		if (this.filename == null && o.filename != null) {
			return -1;
		}
		return this.filename.compareTo(o.filename);
	}	
	
}
