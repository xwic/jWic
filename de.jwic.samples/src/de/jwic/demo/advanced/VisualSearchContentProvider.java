/**
 * 
 */
package de.jwic.demo.advanced;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.jwic.controls.combo.FilteredRange;
import de.jwic.data.ListContentProvider;
import de.jwic.data.Range;

/**
 * @author vedad
 *
 */
public class VisualSearchContentProvider extends ListContentProvider<VisualSearchObject> {

	private static final long serialVersionUID = 3786893239844385743L;

	protected FilteredRange fRange;

	/**
	 * @param list
	 */
	public VisualSearchContentProvider(List<VisualSearchObject> list) {
		super(list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.jwic.data.ListContentProvider#getContentIterator(de.jwic.data.Range)
	 */
	@Override
	public Iterator<VisualSearchObject> getContentIterator(Range range) {
		List<VisualSearchObject> tmp = data;
		if (range instanceof FilteredRange) {
			fRange = (FilteredRange) range;
			if (fRange.getFilter() != null) {
				String filter = fRange.getFilter().trim().toLowerCase();
				tmp = new ArrayList<VisualSearchObject>();
				for (VisualSearchObject po : data) {
					if (po.getValue().toLowerCase().contains(filter)) {
						tmp.add(po);
						if (fRange.getMax() > 0 && (fRange.getStart() + fRange.getMax() <= tmp.size())) {
							break;
						}
					}
				}
			}
		}
		if (range.getMax() != -1) {

			if (tmp.size() == 0) {
				return tmp.iterator();
			}
			int start = range.getStart();
			if (start >= tmp.size()) {
				start = tmp.size() - 1;
			}
			int end = start + range.getMax();
			if (end > tmp.size()) {
				end = tmp.size();
			}
			return tmp.subList(start, end).iterator();
		} else {
			return tmp.iterator();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.data.ListContentProvider#hasChildren(java.lang.Object)
	 */
	@Override
	public boolean hasChildren(VisualSearchObject object) {
		return object.getChildren().size() == 0 ? false : true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.data.ListContentProvider#getChildren(java.lang.Object)
	 */
	@Override
	public Iterator<VisualSearchObject> getChildren(VisualSearchObject object) {
		List<VisualSearchObject> list = object.getChildren();
		List<VisualSearchObject> tmp = new ArrayList<VisualSearchObject>();

		if (list != null) {
			if (fRange.getFilter() != null) {
				String filter = fRange.getFilter().trim().toLowerCase();
				for (VisualSearchObject po : list) {
					if (po.getValue().toLowerCase().contains(filter)) {
						tmp.add(po);
						if (fRange.getMax() > 0 && (fRange.getStart() + fRange.getMax() <= tmp.size())) {
							break;
						}
					}
				}
			}

			if (fRange.getMax() != -1) {

				if (tmp.size() == 0) {
					return tmp.iterator();
				}
				int start = fRange.getStart();
				if (start >= tmp.size()) {
					start = tmp.size() - 1;
				}
				int end = start + fRange.getMax();
				if (end > tmp.size()) {
					end = tmp.size();
				}
				return tmp.subList(start, end).iterator();
			} else {
				return tmp.iterator();
			}
		}
		return null;
	}

}
