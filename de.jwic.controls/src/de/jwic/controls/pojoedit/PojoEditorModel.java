/**
 * 
 */
package de.jwic.controls.pojoedit;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.jwic.base.IControl;
import de.jwic.controls.pojoedit.handler.BooleanFieldHandler;
import de.jwic.controls.pojoedit.handler.EnumFieldHandler;
import de.jwic.controls.pojoedit.handler.NumberFieldHandler;
import de.jwic.controls.pojoedit.handler.TextFieldHandler;

/**
 * The backend model for the PojoEditor. Initialize with the Class of objects you want
 * to edit. Then use loadPojo(..) and savePojo(..) to load and save data.
 * 
 * The object classes you want to edit can use annotations (PojoEditable) to specify
 * additional behavior.
 * 
 * @author lippisch
 */
public class PojoEditorModel implements Serializable{

	/** List of properties to always skip */
	private final static Set<String> SKIP_PROPERTIES = new HashSet<String>();
	static {
		SKIP_PROPERTIES.add("class");
	}

	private final static Log log = LogFactory.getLog(PojoEditorModel.class);
	
	private Class<?> pojoClass;
	private List<PojoField> fields = new ArrayList<PojoField>();
	
	private List<IFieldHandler<? extends IControl>> availableHandler = new ArrayList<IFieldHandler<? extends IControl>>();
	
	
	/**
	 * Construct a new model for the type specified.
	 * @throws PojoEditorException 
	 */
	public PojoEditorModel(Class<?> pojoClass) throws PojoEditorException {
		this(pojoClass, null);
	}
	
	/**
	 * Construct a new model for the type specified, including the additional handlers specified.
	 * @throws PojoEditorException 
	 */
	public PojoEditorModel(Class<?> pojoClass, IFieldHandler<? extends IControl> customHandler[]) throws PojoEditorException {
		this.pojoClass = pojoClass;
		
		if (pojoClass == null) {
			throw new IllegalArgumentException("pojoClass must not be null");
		}
		
		if (customHandler != null) {
			Collections.addAll(availableHandler, customHandler);
		}
		// now add all the "standard handlers"
		availableHandler.add(new BooleanFieldHandler());
		availableHandler.add(new TextFieldHandler());
		availableHandler.add(new EnumFieldHandler());
		availableHandler.add(new NumberFieldHandler());
		
		evaluatePojoClass();
		
	}
	
	/**
	 * Build the field details from the pojo.
	 * @param pojo2
	 * @throws PojoEditorException 
	 */
	private void evaluatePojoClass() throws PojoEditorException {
		
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(pojoClass);
			for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
				// only add fields that have at least a read method and are not in the list of properties to skip.
				if (pd.getReadMethod() != null && !SKIP_PROPERTIES.contains(pd.getName())) { 
					PojoEditable annotation = pd.getReadMethod().getAnnotation(PojoEditable.class);

					PojoField field = new PojoField(pd, annotation);
					
					// select proper handler
					IFieldHandler<? extends IControl> fh = null;
					for (IFieldHandler<? extends IControl> handler : availableHandler) {
						if (handler.accepts(field)) {
							fh = handler;
							break;
						}
					}
					
					
					if (fh != null) {
						field.setFieldHandler(fh);
						fields.add(field);
					} else {
						log.warn("No FieldHandler found for property " + field.getName() + ". The property will not be displayed.");
					}
					
				}
			}
			
			Collections.sort(fields);
			
		} catch (Exception e) {
			throw new PojoEditorException("Error creating model from Pojo", e);
		}
		
	}

	/**
	 * @return the fields
	 */
	public List<PojoField> getFields() {
		return fields;
	}
	
	/**
	 * Load values into the form from the pojo.
	 * @param pojo
	 * @throws PojoEditorException 
	 */
	@SuppressWarnings("unchecked")
	public void loadPojo(Object pojo) throws PojoEditorException {
		
		if (pojo == null) {
			throw new IllegalArgumentException("The pojo to load from must not be null");
		}
		try {
			for (PojoField field : fields) {
				PropertyDescriptor pd = field.getPropertyDescriptor();
				Method readMethod = pd.getReadMethod();
				Object value = readMethod.invoke(pojo);
				IFieldHandler<IControl> fh = (IFieldHandler<IControl>)field.getFieldHandler();
				fh.loadValue(field, field.getControl(), value);
			}
		} catch (Exception e) {
			throw new PojoEditorException("Error loading values", e);
		}
	}
	
	/**
	 * Load values into the form from the pojo.
	 * @param pojo
	 * @throws PojoEditorException 
	 */
	@SuppressWarnings("unchecked")
	public void savePojo(Object pojo) throws PojoEditorException {
		
		if (pojo == null) {
			throw new IllegalArgumentException("The pojo to be saved to must not be null");
		}
		try {
			for (PojoField field : fields) {
				PropertyDescriptor pd = field.getPropertyDescriptor();
				Method writeMethod = pd.getWriteMethod();
				IFieldHandler<IControl> fh = (IFieldHandler<IControl>)field.getFieldHandler();
				Object value = fh.readValue(field, field.getControl());
				writeMethod.invoke(pojo, value);
			}
		} catch (Exception e) {
			throw new PojoEditorException("Error saving values", e);
		}
	}

}
