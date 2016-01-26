package de.jwic.demo.chart;

import java.util.Iterator;
import java.util.List;

import de.jwic.data.ListContentProvider;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 13.11.2015
 */
public class TableElementContentProvider extends
		ListContentProvider<TableElement> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -444635214920301084L;

	public TableElementContentProvider(List<TableElement> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}

	/**
	 * adds the DemoTask to the input list
	 * 
	 * @param task
	 */
	public void addElement(TableElement tableElement) {
		data.add(tableElement);
	}

	/**
	 * removes the DemoTask from the input list
	 * 
	 * @param task
	 */
	public void removeElement(TableElement tableElement) {
		data.remove(tableElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.data.ListContentProvider#getObjectFromKey(java.lang.String)
	 */
	@Override
	public TableElement getObjectFromKey(String key) {

		for (Iterator<TableElement> it = data.iterator(); it.hasNext();) {
			TableElement task = it.next();
			if (task.getTitle() == key) {
				return task;
			}
		}

		return null;
	}

}
