package com.firedance.gps.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author chong.sun
 */
public class ExcelUtil {
    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    private final static String SHEET_NAME_STR = ":|\\|/|\\?|\\*|\\[|]";

    private final static String XLSX = ".xlsx";

    private final static int EXCEL_CELL_MAX_LENGTH = 32700;

    private final static String OSS = "https://an-announcement.oss-cn-shanghai.aliyuncs.com/";

    public static List<Row> getAllRow(Sheet sheet) {
        if (null == sheet) {
            return Collections.emptyList();
        }
        List<Row> rows = new ArrayList<>(1024);
        for (Row row : sheet) {
            rows.add(row);
        }
        return rows;
    }


    /**
     * 初始化excel
     *
     * @param file
     * @return
     */
    public static Workbook initExcel(File file) throws IOException {
        String fileName = file.getName();
        Workbook wb = null;
        if (StringUtils.isBlank(fileName)) {
            return wb;
        }
        String ext = fileName.substring(fileName.lastIndexOf('.'));
        FileInputStream inputStream = new FileInputStream(file);
        if (".xls".equals(ext)) {
            wb = new HSSFWorkbook(inputStream);
        } else if (".xlsx".equals(ext)) {
            wb = new XSSFWorkbook(inputStream);
        }
        return wb;
    }

    /**
     * 获取excel的值
     *
     * @param cell
     * @return
     */
    public static Object getCellFormatValue(Cell cell) {
        Object cellValue = null;
        if (cell != null) {
            try {
                switch (cell.getCellTypeEnum()) {
                    case NUMERIC:
                        if (DateUtil.isCellInternalDateFormatted(cell)) {
                            Date date = cell.getDateCellValue();
                            cellValue = date;
                        } else {
                            BigDecimal value = new BigDecimal(cell.getNumericCellValue());
                            //默认有小数点的保存两个小数
                            if (StringUtils.isNotBlank(value.toString())
                                && value.toString().indexOf(".") != -1) {
                                cellValue = value.setScale(2, BigDecimal.ROUND_HALF_UP);
                            } else {
                                cellValue = value;
                            }
                        }
                        break;
                    case STRING:
                        cellValue = cell.getStringCellValue().toString();
                        break;
                    case FORMULA:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            cellValue = cell.getDateCellValue();
                        } else {
                            cellValue = String.valueOf(cell.getNumericCellValue());
                        }
                        break;
                    default:
                        cellValue = "";
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("excel获取列值失败！！" + e.getMessage());
            }
        } else {
            return null;
        }
        return cellValue;
    }

    /**
     * 获取excel的值
     *
     * @param cell
     * @return
     */
    public static Object getCellFormatValueWithNoFormat(Cell cell) {

        Object cellValue = "";
        if (cell != null) {
            try {
                switch (cell.getCellTypeEnum()) {
                    case NUMERIC:
                        if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                            Date date = cell.getDateCellValue();
                            cellValue = date;
                        } else {
                            cellValue = String.valueOf(cell.getNumericCellValue());
                        }
                        break;
                    case STRING:
                        cellValue = cell.getStringCellValue().toString();
                        break;
                    case FORMULA:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            cellValue = cell.getDateCellValue();
                        } else {
                            cellValue = String.valueOf(cell.getNumericCellValue());
                        }
                        break;
                    default:
                        cellValue = "";
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("excel获取列值失败！！" + e.getMessage());
            }
        }
        return cellValue;
    }
}

