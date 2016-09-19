/**
 * 
 */
package de.jwic.controls.combo;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONWriter;

import de.jwic.base.IControlContainer;
import de.jwic.data.DataLabel;

/**
 * @author vedad
 *
 */
public class VisualSearchCombo<A> extends Combo<A> {

	private static final long serialVersionUID = -4006546505828258260L;

	/**
	 * @param container
	 * @param name
	 */
	public VisualSearchCombo(IControlContainer container, String name) {
		super(container, name);
		setTemplateName(VisualSearchCombo.class.getName());
		setCssClass("visual_search");
		setWidth(100);
		comboBehavior.setShowOpenBoxIcon(false);
		comboBehavior.setMinSearchKeyLength(3);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.controls.combo.Combo#handleJSONResponse(javax.servlet.http.
	 * HttpServletRequest, org.json.JSONWriter)
	 */
	@Override
	public void handleJSONResponse(HttpServletRequest req, JSONWriter out) throws JSONException {
		FilteredRange range = new FilteredRange();
		range.setFilter(req.getParameter("filter"));
		range.setMax(comboBehavior.getMaxFetchRows());

		String action = req.getParameter("action");
		String selectedParent = req.getParameter("selectedParent");
		if (action == null) {
			action = "load";
		}

		out.object();
		out.key("controlId").value(getControlID());

		out.key("data");
		out.array();

		if ("load".equals(action)) {
			if (contentProvider != null) {
				for (Iterator<A> it = contentProvider.getContentIterator(range); it.hasNext();) {
					A obj = it.next();
					DataLabel label;
					if (selectedParent == null) {
						out.object();
						out.key("key").value(contentProvider.getUniqueKey(obj));
						if (baseLabelProvider != null) {
							label = baseLabelProvider.getBaseLabel(obj);
							out.key("title").value(label.text);
						}
						if (contentProvider.hasChildren(obj)) {
							out.key("children").value(Boolean.TRUE);
						}
						if (comboBehavior.isTransferFullObject() && objectSerializer != null) {
							out.key("object");
							objectSerializer.serialize(obj, out);
						}
						out.endObject();
					} else {
						comboBehavior.setMaxFetchRows(20);
						if (baseLabelProvider != null) {
							label = baseLabelProvider.getBaseLabel(obj);
							if (selectedParent.equals(label.text)) {
								
								for (Iterator<A> iterator = contentProvider.getChildren(obj); iterator.hasNext();) {
									A childObj = iterator.next();
									out.object();
									out.key("key").value(contentProvider.getUniqueKey(childObj));
									DataLabel childLabel = baseLabelProvider.getBaseLabel(childObj);
									out.key("title").value(childLabel.text);

									if (comboBehavior.isTransferFullObject() && objectSerializer != null) {
										out.key("object");
										objectSerializer.serialize(childObj, out);
									}
									out.endObject();
								}
							}
						}
					}

				}
			}
		}

		out.endArray();
		out.endObject();
	}
}
