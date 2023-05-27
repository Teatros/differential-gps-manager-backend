package com.firedance.gps.utils.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author minghu.he
 */
public class ExcelBuilder {

    private final List<ExcelProperty> properties = new ArrayList<>();

    private final Map<ExcelProperty, Field> fieldMap = new HashMap<>();

    private final XSSFWorkbook workbook = new XSSFWorkbook();

    private final XSSFSheet sheet;

    private int cursor = 0;

    public ExcelBuilder(Class<?> head, String sheetName) {
        parseProperties(head);
        sheet = workbook.createSheet(sheetName);
        createHeader();
    }

    private void parseProperties(Class<?> head) {
        Field[] fields = head.getDeclaredFields();
        for (Field field : fields) {
            ExcelProperty property = field.getAnnotation(ExcelProperty.class);
            if (property != null) {
                properties.add(property);
                field.setAccessible(true);
                fieldMap.put(property, field);
            }
        }
    }

    private void createHeader() {
        Row header = sheet.createRow(cursor++);
        header.setHeight((short)800);

        int i = 0;
        for (ExcelProperty property : properties) {
            Cell cell = header.createCell(i++);
            setContentStyle(cell);
            cell.setCellValue(property.value());
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public ExcelBuilder fill(List dataset) {
        Objects.requireNonNull(dataset);
        dataset.forEach(this::createRow);
        return this;
    }

    private void createRow(Object data) {
        Row row = sheet.createRow(cursor++);
        row.setHeight((short)600);

        int i = 0;
        for (ExcelProperty property : properties) {
            try {
                Cell cell = row.createCell(i++);
                sheet.setColumnWidth(i, 20 * 256);
                setContentStyle(cell);
                Field field = fieldMap.get(property);
                if (field == null) {
                    continue;
                }
                Object value = field.get(data);
                if (value == null) {
                    continue;
                }
                Class<?> fieldClass = value.getClass();
                if (Integer.class.isAssignableFrom(fieldClass)) {
                    cell.setCellValue(value.toString());
                } else if (Long.class.isAssignableFrom(fieldClass)) {
                    cell.setCellValue(value.toString());
                } else if (String.class.isAssignableFrom(fieldClass)) {
                    cell.setCellValue(value.toString());
                } else if (Date.class.isAssignableFrom(fieldClass)) {
                    CellStyle cellStyle = cell.getCellStyle();
                    XSSFDataFormat format = workbook.createDataFormat();
                    cellStyle.setDataFormat(format.getFormat(property.dateFormat()));
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue((Date)value);
                } else {
                    throw new IllegalArgumentException("Unsupported field class - " + value.getClass());
                }
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(
                    "Unexpected reflection exception - " + e.getClass().getName() + ": " + e.getMessage());
            }
        }
    }

    private void setContentStyle(Cell cell) {
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        XSSFFont font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setBold(false);
        font.setFontHeight(12);
        style.setFont(font);
        cell.setCellStyle(style);
    }

    public void export(OutputStream os) throws IOException {
        workbook.write(os);
        os.close();
    }
}
