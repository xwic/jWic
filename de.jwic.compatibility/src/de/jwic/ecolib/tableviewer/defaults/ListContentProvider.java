package de.jwic.ecolib.tableviewer.defaults;

import java.util.List;

/**
 * A simple content provider that provides the data from a List object.
 * Proxy for de.jwic.data.ListContentProvider
 * 
 * @author Florian Lippisch
 * @deprecated use de.jwic.data.ListContentProivider instead.
 */
public class ListContentProvider<A> extends de.jwic.data.ListContentProvider<A> {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * @param data
	 */
	public ListContentProvider(List<A> list) {
		super(list);
	}
}