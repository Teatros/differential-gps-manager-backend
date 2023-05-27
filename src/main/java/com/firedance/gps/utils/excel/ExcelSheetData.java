package com.firedance.gps.utils.excel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author chong.sun
 */
public class ExcelSheetData implements Serializable {

    private String sheetName;

    private List<Map<String, String>> mapList;

    private Map<String, Integer> widthMap;

    private Map<String, Short> alignmentMap;

    private Map<String, Short> titleAlignmentMap;

    private Boolean mergeRangeLast;

    private Integer mergeRangeLastCol;

    private List<Map<String, String>> newTitleMap;

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<Map<String, String>> getMapList() {
        return mapList;
    }

    public void setMapList(List<Map<String, String>> mapList) {
        this.mapList = mapList;
    }

    public Map<String, Integer> getWidthMap() {
        return widthMap;
    }

    public void setWidthMap(Map<String, Integer> widthMap) {
        this.widthMap = widthMap;
    }

    public Map<String, Short> getAlignmentMap() {
        return alignmentMap;
    }

    public void setAlignmentMap(Map<String, Short> alignmentMap) {
        this.alignmentMap = alignmentMap;
    }

    public Map<String, Short> getTitleAlignmentMap() {
        return titleAlignmentMap;
    }

    public void setTitleAlignmentMap(Map<String, Short> titleAlignmentMap) {
        this.titleAlignmentMap = titleAlignmentMap;
    }

    public Boolean getMergeRangeLast() {
        return mergeRangeLast;
    }

    public void setMergeRangeLast(Boolean mergeRangeLast) {
        this.mergeRangeLast = mergeRangeLast;
    }

    public Integer getMergeRangeLastCol() {
        return mergeRangeLastCol;
    }

    public void setMergeRangeLastCol(Integer mergeRangeLastCol) {
        this.mergeRangeLastCol = mergeRangeLastCol;
    }

    public List<Map<String, String>> getNewTitleMap() {
        return newTitleMap;
    }

    public void setNewTitleMap(List<Map<String, String>> newTitleMap) {
        this.newTitleMap = newTitleMap;
    }
}
