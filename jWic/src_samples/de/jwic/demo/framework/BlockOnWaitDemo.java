/**
 * 
 */
package de.jwic.demo.framework;

import de.jwic.base.IControlContainer;
import de.jwic.demo.DemoModule;

/**
 * @author lippisch
 *
 */
public class BlockOnWaitDemo extends DemoModule {

	/**
	 * 
	 */
	public BlockOnWaitDemo() {
		setTitle("Block On Wait");
		setDescription("If an operation takes longer than 200 ms, the client is " +
				"blocked and no more input is accepted until the operation completed.");
		
		setGroup("Framework");
		
	}

	/* (non-Javadoc)
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {
		new BlockOnWait(container);

	}

}
