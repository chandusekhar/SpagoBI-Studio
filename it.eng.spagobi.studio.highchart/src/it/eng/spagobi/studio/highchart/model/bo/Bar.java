/**
SpagoBI - The Business Intelligence Free Platform

Copyright (C) 2005-2008 Engineering Ingegneria Informatica S.p.A.

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

 **/
package it.eng.spagobi.studio.highchart.model.bo;



public class Bar implements InterfaceType{

	
	private String borderColor;
	private Float borderRadius;
	private Integer borderWidth;
	private Boolean colorByPoint;
	private Float groupPadding;
	private Integer minPointLength;
	private Float pointPadding;
	private Integer pointWidth;
	

	private Boolean allowPointSelect;
	private Boolean animation;
	private String color;
	private String cursor; // combo
	private String dashStyle; // combo
	private DataLabels dataLabels;
	private Boolean enableMouseTracking;
	private Integer lineWidth;
	private String marker;
	private Integer pointStart;
	private Integer pointInterval;
	private Boolean selected;
	private Boolean shadow;
	private Boolean showCheckbox;
	private Boolean showInLegend;
	private String stacking; // combo
	private Boolean stickyTracking ;
	private Boolean visible;
	private Integer zIndex;
	

	
	public String getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}
	public Float getBorderRadius() {
		return borderRadius;
	}
	public void setBorderRadius(Float borderRadius) {
		this.borderRadius = borderRadius;
	}
	public Integer getBorderWidth() {
		return borderWidth;
	}
	public void setBorderWidth(Integer borderWidth) {
		this.borderWidth = borderWidth;
	}
	public Boolean getColorByPoint() {
		return colorByPoint;
	}
	public void setColorByPoint(Boolean colorByPoint) {
		this.colorByPoint = colorByPoint;
	}
	public Float getGroupPadding() {
		return groupPadding;
	}
	public void setGroupPadding(Float groupPadding) {
		this.groupPadding = groupPadding;
	}
	public Integer getMinPointLength() {
		return minPointLength;
	}
	public void setMinPointLength(Integer minPointLength) {
		this.minPointLength = minPointLength;
	}
	public Float getPointPadding() {
		return pointPadding;
	}
	public void setPointPadding(Float pointPadding) {
		this.pointPadding = pointPadding;
	}
	public Integer getPointWidth() {
		return pointWidth;
	}
	public void setPointWidth(Integer pointWidth) {
		this.pointWidth = pointWidth;
	}
	public Boolean isAllowPointSelect() {
		return allowPointSelect;
	}
	public void setAllowPointSelect(Boolean allowPointSelect) {
		this.allowPointSelect = allowPointSelect;
	}
	public Boolean isAnimation() {
		return animation;
	}
	public void setAnimation(Boolean animation) {
		this.animation = animation;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getCursor() {
		return cursor;
	}
	public void setCursor(String cursor) {
		this.cursor = cursor;
	}
	public String getDashStyle() {
		return dashStyle;
	}
	public void setDashStyle(String dashStyle) {
		this.dashStyle = dashStyle;
	}
	public DataLabels getDataLabels() {
		if(dataLabels == null) dataLabels = new DataLabels();
		return dataLabels;
	}
	public void setDataLabels(DataLabels dataLabels) {
		this.dataLabels = dataLabels;
	}
	public Boolean isEnableMouseTracking() {
		return enableMouseTracking;
	}
	public void setEnableMouseTracking(Boolean enableMouseTracking) {
		this.enableMouseTracking = enableMouseTracking;
	}
	public Integer getLineWidth() {
		return lineWidth;
	}
	public void setLineWidth(Integer lineWidth) {
		this.lineWidth = lineWidth;
	}
	public String getMarker() {
		return marker;
	}
	public void setMarker(String marker) {
		this.marker = marker;
	}
	public Integer getPointStart() {
		return pointStart;
	}
	public void setPointStart(Integer pointStart) {
		this.pointStart = pointStart;
	}
	public Integer getPointInterval() {
		return pointInterval;
	}
	public void setPointInterval(Integer pointInterval) {
		this.pointInterval = pointInterval;
	}
	public Boolean isSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	public Boolean isShadow() {
		return shadow;
	}
	public void setShadow(Boolean shadow) {
		this.shadow = shadow;
	}
	public Boolean isShowCheckbox() {
		return showCheckbox;
	}
	public void setShowCheckbox(Boolean showCheckbox) {
		this.showCheckbox = showCheckbox;
	}
	public Boolean isShowInLegend() {
		return showInLegend;
	}
	public void setShowInLegend(Boolean showInLegend) {
		this.showInLegend = showInLegend;
	}
	public String getStacking() {
		return stacking;
	}
	public void setStacking(String stacking) {
		this.stacking = stacking;
	}
	public Boolean isStickyTracking() {
		return stickyTracking;
	}
	public void setStickyTracking(Boolean stickyTracking) {
		this.stickyTracking = stickyTracking;
	}
	public Boolean isVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	public Integer getzIndex() {
		return zIndex;
	}
	public void setzIndex(Integer zIndex) {
		this.zIndex = zIndex;
	}


}
