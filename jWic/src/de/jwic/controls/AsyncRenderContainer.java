/*
 * de.jwic.controls.AsyncRenderContainer 
 */
package de.jwic.controls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONWriter;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.IResourceControl;
import de.jwic.base.JavaScriptSupport;
import de.jwic.base.RenderContext;
import de.jwic.web.ContentRenderer;

/**
 * This control is rendering the content asynchronously.
 * 
 * @author lippisch
 *
 */
@JavaScriptSupport
public class AsyncRenderContainer extends ControlContainer implements IResourceControl {

	private ControlContainer container;
	private LazyInitializationHandler lazyInitializationHandler = null;
	private boolean initialized = false;
	private long seqNum = 0;
	
	/**
	 * Constructor.
	 * @param parent
	 */
	public AsyncRenderContainer(IControlContainer parent) {
		super(parent);
		internalInit();
	}
	
	/**
	 * @param container
	 * @param name
	 */
	public AsyncRenderContainer(IControlContainer parent, String name) {
		super(parent, name);
		internalInit();
	}
	
	/**
	 * Initialize the control itself.
	 */
	private void internalInit() {
		this.container = new ControlContainer(this, "content");
	}

	/**
	 * @return the lazyInitializationHandler
	 */
	public LazyInitializationHandler getLazyInitializationHandler() {
		return lazyInitializationHandler;
	}

	/**
	 * @param lazyInitializationHandler the lazyInitializationHandler to set
	 */
	public void setLazyInitializationHandler(LazyInitializationHandler lazyInitializationHandler) {
		this.lazyInitializationHandler = lazyInitializationHandler;
	}

	/**
	 * Returns the container to be used for childs.  
	 */
	public IControlContainer getContainer() {
		return container;
	}
	
	
	/* (non-Javadoc)
	 * @see de.jwic.base.IResourceControl#attachResource(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void attachResource(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		// Initialize the content when the control is rendered the first time.
		if (!initialized && lazyInitializationHandler != null) {
			initialized = true;
			lazyInitializationHandler.initialize(getContainer());
		}
		
		res.setContentType("text/json; charset=UTF-8");
		PrintWriter pw;
		try {
			pw = res.getWriter();
		} catch (Exception e) {
			log.error("Error getting writer!");
			return;
		}
		JSONWriter jsonOut = new JSONWriter(pw);
		try {
			
			// render child control
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			PrintWriter buffer = new PrintWriter(out); 
			RenderContext context = new RenderContext(req, res, buffer);
			ContentRenderer cr = new ContentRenderer(container, context);
			try {
				cr.render();
			} catch (Throwable t) {
				log.error("Error rendering embedded container", t);
				pw.print("Error rendering control: " + t.toString());
			}
			buffer.flush();
			

			jsonOut.object()
			
				.key("seqNum")
				.value(req.getParameter("seqNum"))
				
				.key("controlId")
				.value(getControlID())
				
				.key("html")
				.value(out.toString());
				
				if (context.getScripts() != null) {
					if (context.getScripts() != null && context.getScripts().size() > 0) {
						jsonOut.key("scripts")
							.array();
						for (Map.Entry<String, String> entry : context.getScripts().entrySet()) {
							jsonOut.object();
							jsonOut.key("controlId");
							jsonOut.value(entry.getKey());
							jsonOut.key("script");
							jsonOut.value(entry.getValue());
							jsonOut.endObject();
						}
						jsonOut.endArray();	
					}
				}
			
			
			jsonOut.endObject();
			
		} catch (JSONException e) {
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
			log.error("Error generating JSON response", e);
		}
		pw.close();
	}
	
	/**
	 * @return the seqNum
	 */
	public long nextSeqNum() {
		seqNum++;
		return seqNum;
	}


	
}
