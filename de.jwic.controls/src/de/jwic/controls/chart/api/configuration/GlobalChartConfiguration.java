package de.jwic.controls.chart.api.configuration;

import java.awt.Color;
import java.io.Serializable;

import de.jwic.base.IncludeJsOption;
import de.jwic.controls.chart.impl.util.DatenConverter;

/**
 * 
 * @author karolina
 *
 */
public class GlobalChartConfiguration implements Serializable {

	private boolean responsive = true;

	private boolean animation = true;

	private int animationSteps = 60;

	private String animationEasing = "easeOutQuart";

	private boolean showScale = true;

	private boolean scaleOverride = false;

	private Color scaleLineColor = new Color(0, 0, 0, 1);

	private int scaleLineWidth = 1;
	private boolean scaleShowLabels = true;
	private String scaleLabel = "<%=value%>";
	private boolean scaleIntegersOnly = true;
	private boolean scaleBeginAtZero = false;
	private String scaleFontFamily = "'Helvetica Neue'; 'Helvetica'; 'Arial'; sans-serif";
	private int scaleFontSize = 12;
	private String scaleFontStyle = "normal";
	private String scaleFontColor = "#666";
	private boolean maintainAspectRatio = true;
	private boolean showTooltips = true;
	private boolean customTooltips = false;
	private String[] tooltipEvents = { "mousemove", "touchstart", "touchmove" };
	private String tooltipFillColor = "rgba(0;0;0;0.8)";
	private String tooltipFontFamily = "'Helvetica Neue'; 'Helvetica'; 'Arial'; sans-serif";
	private int tooltipFontSize = 14;
	private String tooltipFontStyle = "normal";
	private String tooltipFontColor = "#fff";
	private String tooltipTitleFontFamily = "'Helvetica Neue'; 'Helvetica'; 'Arial'; sans-serif";
	private int tooltipTitleFontSize = 14;
	private String tooltipTitleFontStyle = "bold";
	private String tooltipTitleFontColor = "#fff";
	private int tooltipYPadding = 6;
	private int tooltipXPadding = 6;
	private int tooltipCaretSize = 8;
	private int tooltipCornerRadius = 6;
	private int tooltipXOffset = 10;
	private String tooltipTemplate = "<%if (label){%><%=label%>= <%}%><%= value %>";

	public boolean isResponsive() {
		return responsive;
	}

	public void setResponsive(boolean responsive) {
		this.responsive = responsive;
	}

	public boolean isAnimation() {
		return animation;
	}

	public void setAnimation(boolean animation) {
		this.animation = animation;
	}

	public int getAnimationSteps() {
		return animationSteps;
	}

	public void setAnimationSteps(int animationSteps) {
		this.animationSteps = animationSteps;
	}

	public String getAnimationEasing() {
		return animationEasing;
	}

	public void setAnimationEasing(String animationEasing) {
		this.animationEasing = animationEasing;
	}

	@IncludeJsOption(jsPropertyName = "showScale")
	public boolean isShowScale() {
		return showScale;
	}

	public void setShowScale(boolean showScale) {
		this.showScale = showScale;
	}

	@IncludeJsOption(jsPropertyName = "scaleOverride")
	public boolean isScaleOverride() {
		return scaleOverride;
	}

	public void setScaleOverride(boolean scaleOverride) {
		this.scaleOverride = scaleOverride;
	}

	@IncludeJsOption(jsPropertyName = "scaleLineColor")
	public String getScaleLineColor() {

		return DatenConverter.convertToJSColor(scaleLineColor);
	}

	public void setScaleLineColor(Color scaleLineColor) {
		this.scaleLineColor = scaleLineColor;
	}

	@IncludeJsOption
	public int getScaleLineWidth() {
		return scaleLineWidth;
	}

	public void setScaleLineWidth(int scaleLineWidth) {
		this.scaleLineWidth = scaleLineWidth;
	}

	@IncludeJsOption
	public boolean isScaleShowLabels() {
		return scaleShowLabels;
	}

	public void setScaleShowLabels(boolean scaleShowLabels) {
		this.scaleShowLabels = scaleShowLabels;
	}

	@IncludeJsOption
	public String getScaleLabel() {
		return scaleLabel;
	}

	public void setScaleLabel(String scaleLabel) {
		this.scaleLabel = scaleLabel;
	}

	@IncludeJsOption
	public boolean isScaleIntegersOnly() {
		return scaleIntegersOnly;
	}

	public void setScaleIntegersOnly(boolean scaleIntegersOnly) {
		this.scaleIntegersOnly = scaleIntegersOnly;
	}

	@IncludeJsOption
	public boolean isScaleBeginAtZero() {
		return scaleBeginAtZero;
	}

	public void setScaleBeginAtZero(boolean scaleBeginAtZero) {
		this.scaleBeginAtZero = scaleBeginAtZero;
	}

	@IncludeJsOption
	public String getScaleFontFamily() {
		return scaleFontFamily;
	}

	public void setScaleFontFamily(String scaleFontFamily) {
		this.scaleFontFamily = scaleFontFamily;
	}

	@IncludeJsOption
	public int getScaleFontSize() {
		return scaleFontSize;
	}

	public void setScaleFontSize(int scaleFontSize) {
		this.scaleFontSize = scaleFontSize;
	}

	@IncludeJsOption
	public String getScaleFontStyle() {
		return scaleFontStyle;
	}

	public void setScaleFontStyle(String scaleFontStyle) {
		this.scaleFontStyle = scaleFontStyle;
	}

	@IncludeJsOption
	public String getScaleFontColor() {
		return scaleFontColor;
	}

	public void setScaleFontColor(String scaleFontColor) {
		this.scaleFontColor = scaleFontColor;
	}

	@IncludeJsOption
	public boolean isMaintainAspectRatio() {
		return maintainAspectRatio;
	}

	public void setMaintainAspectRatio(boolean maintainAspectRatio) {
		this.maintainAspectRatio = maintainAspectRatio;
	}

	@IncludeJsOption
	public boolean isShowTooltips() {
		return showTooltips;
	}

	public void setShowTooltips(boolean showTooltips) {
		this.showTooltips = showTooltips;
	}

	@IncludeJsOption
	public boolean isCustomTooltips() {
		return customTooltips;
	}

	public void setCustomTooltips(boolean customTooltips) {
		this.customTooltips = customTooltips;
	}

	@IncludeJsOption
	public String[] getTooltipEvents() {
		return tooltipEvents;
	}

	public void setTooltipEvents(String[] tooltipEvents) {
		this.tooltipEvents = tooltipEvents;
	}

	@IncludeJsOption
	public String getTooltipFillColor() {
		return tooltipFillColor;
	}

	public void setTooltipFillColor(String tooltipFillColor) {
		this.tooltipFillColor = tooltipFillColor;
	}

	@IncludeJsOption
	public String getTooltipFontFamily() {
		return tooltipFontFamily;
	}

	public void setTooltipFontFamily(String tooltipFontFamily) {
		this.tooltipFontFamily = tooltipFontFamily;
	}

	@IncludeJsOption
	public int getTooltipFontSize() {
		return tooltipFontSize;
	}

	public void setTooltipFontSize(int tooltipFontSize) {
		this.tooltipFontSize = tooltipFontSize;
	}

	@IncludeJsOption
	public String getTooltipFontStyle() {
		return tooltipFontStyle;
	}

	public void setTooltipFontStyle(String tooltipFontStyle) {
		this.tooltipFontStyle = tooltipFontStyle;
	}

	@IncludeJsOption
	public String getTooltipFontColor() {
		return tooltipFontColor;
	}

	public void setTooltipFontColor(String tooltipFontColor) {
		this.tooltipFontColor = tooltipFontColor;
	}

	@IncludeJsOption
	public String getTooltipTitleFontFamily() {
		return tooltipTitleFontFamily;
	}

	public void setTooltipTitleFontFamily(String tooltipTitleFontFamily) {
		this.tooltipTitleFontFamily = tooltipTitleFontFamily;
	}

	@IncludeJsOption
	public int getTooltipTitleFontSize() {
		return tooltipTitleFontSize;
	}

	public void setTooltipTitleFontSize(int tooltipTitleFontSize) {
		this.tooltipTitleFontSize = tooltipTitleFontSize;
	}

	@IncludeJsOption
	public String getTooltipTitleFontStyle() {
		return tooltipTitleFontStyle;
	}

	public void setTooltipTitleFontStyle(String tooltipTitleFontStyle) {
		this.tooltipTitleFontStyle = tooltipTitleFontStyle;
	}

	@IncludeJsOption
	public String getTooltipTitleFontColor() {
		return tooltipTitleFontColor;
	}

	public void setTooltipTitleFontColor(String tooltipTitleFontColor) {
		this.tooltipTitleFontColor = tooltipTitleFontColor;
	}

	@IncludeJsOption
	public int getTooltipYPadding() {
		return tooltipYPadding;
	}

	public void setTooltipYPadding(int tooltipYPadding) {
		this.tooltipYPadding = tooltipYPadding;
	}

	@IncludeJsOption
	public int getTooltipXPadding() {
		return tooltipXPadding;
	}

	public void setTooltipXPadding(int tooltipXPadding) {
		this.tooltipXPadding = tooltipXPadding;
	}

	@IncludeJsOption
	public int getTooltipCaretSize() {
		return tooltipCaretSize;
	}

	public void setTooltipCaretSize(int tooltipCaretSize) {
		this.tooltipCaretSize = tooltipCaretSize;
	}

	@IncludeJsOption
	public int getTooltipCornerRadius() {
		return tooltipCornerRadius;
	}

	public void setTooltipCornerRadius(int tooltipCornerRadius) {
		this.tooltipCornerRadius = tooltipCornerRadius;
	}

	@IncludeJsOption
	public int getTooltipXOffset() {
		return tooltipXOffset;
	}

	public void setTooltipXOffset(int tooltipXOffset) {
		this.tooltipXOffset = tooltipXOffset;
	}

	@IncludeJsOption
	public String getTooltipTemplate() {
		return tooltipTemplate;
	}

	public void setTooltipTemplate(String tooltipTemplate) {
		this.tooltipTemplate = tooltipTemplate;
	}

}
