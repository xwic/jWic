/*******************************************************************************
 * Copyright 2015 xWic group (http://www.xwic.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *  
 *******************************************************************************/
package de.jwic.demo.advanced;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.lazytooltip.ILazyTooltip;
import de.jwic.controls.lazytooltip.ILazyTooltipProvider;
import de.jwic.controls.lazytooltip.LazyTooltipControl;
import de.jwic.controls.lazytooltip.LazyTooltipControl.TooltipPosition;

public class LazyTooltipDemo extends ControlContainer implements Serializable{
	private final List<BlueBox> blueBoxes = new ArrayList<BlueBox>();
	/**
	 * @param container
	 * @param name
	 */
	public LazyTooltipDemo(IControlContainer container, String name) {
		super(container, name);
	}

	/**
	 * @param container
	 */
	public LazyTooltipDemo(IControlContainer container) {
		this(container,null);

		final LazyTooltipControl lazyTooltipControl = new LazyTooltipControl(this,"lazyTooltip");
		
		for (int i = 0; i <= 10; i++) {
			BlueBox blueBox = new BlueBox(this, "number"+i);
			blueBox.setNumber(i+1);
			blueBox.setMessage("message"+(i+1));
			if(i < 8){
				blueBox.setTooltipProvider("demoProvider");
			}else{
				blueBox.setTooltipProvider("demoProvider2");	
			}
			blueBoxes.add(blueBox);
			
		}
		
		lazyTooltipControl.setPosition(TooltipPosition.ABOVE);
		
		lazyTooltipControl.addLazyTooltipProvider("demoProvider", new ILazyTooltipProvider() {

			@Override
			public ILazyTooltip getTooltip(String requestParams) {
				if(requestParams.equals("message1")){
					return new DemoTooltip("Nonsense!");
				}
				if(requestParams.equals("message2")){
					return new DemoTooltip("Oh my word!");
				}
				if(requestParams.equals("message3")){
					return new DemoTooltip("Did I ever tell you about the time...");
				}
				if(requestParams.equals("message4")){
					return new DemoTooltip("Would you like a jelly baby?");
				}
				if(requestParams.equals("message5")){
					return new DemoTooltip("Must dash!");
				}
				if(requestParams.equals("message6")){
					return new DemoTooltip("Mmm I wonder??? Aha!");
				}
				if(requestParams.equals("message7")){
					return new DemoTooltip("Absence makes the nose grow longer!");
				}
				if(requestParams.equals("message8")){
					return new DemoTooltip("Who am I? Who... Am... I?!");
				}

				return new DemoTooltip("Hmm? i don't know this one");
			}
		});
		
		final LazyTooltipControl lazyTooltipControl2 = new LazyTooltipControl(this,"lazyTooltipControl2");
		
		lazyTooltipControl2.setPosition(TooltipPosition.OVER);
		
		lazyTooltipControl2.addLazyTooltipProvider("demoProvider2", new ILazyTooltipProvider() {
			
			@Override
			public ILazyTooltip getTooltip(String requestParams) {
				if(requestParams.equals("message9")){
					return new DemoTooltip("Fantastic!");
				}
				if(requestParams.equals("message10")){
					return new DemoTooltip("Allons-y");
				}
				
				if(requestParams.equals("message11")){
					return new DemoTooltip("Geronimo!");
				}
				return new DemoTooltip("Hmm? i don't know this one either");
			}
		});
	}
	
	/**
	 * @author bogdan
	 *
	 */
	private static final class DemoTooltip implements ILazyTooltip{
		private final String message;
		
		public DemoTooltip(String message) {
			this.message = message;
		}
		
		@Override
		public String getJSLabelProviderClass() {
			return "DemoTooltipProvider";
		}

		@Override
		public JSONObject getData() throws JSONException {
			return new JSONObject().put("message", message);
		}
		
	}
}
