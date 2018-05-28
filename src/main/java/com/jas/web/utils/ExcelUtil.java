package com.jas.web.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by yangli on 16/3/31.
 */
public class ExcelUtil {
    /**
     * 获取cell的值
     * @param cell
     * @return
     */
    public static String getStringVal(Cell cell){
        if(cell==null){
            return "";
        }
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue()?"true":"false";
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                cell.setCellType(Cell.CELL_TYPE_STRING);
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            default:
                 return "";
        }
    }
    /**
     * 为了兼容03 和 07 两个版本的excel
     * @param is 输入的文件流
     * @param fileName 文件名
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbook(FileInputStream is,String fileName) throws IOException{
        Workbook workbook = null;
        if (fileName.endsWith(".xls")) {
            workbook = new HSSFWorkbook(is);
        } else if (fileName.endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(is);
        }
        return workbook;
    }

    public static void exportExcel(String[] titile, String[] table, List list, String filename, HttpServletResponse response) throws Exception {
        response.reset();//清空输出流

        response.setHeader("Content-disposition", "attachment;filename=" + new String(filename.getBytes("UTF-8"), "ISO8859-1")+".xlsx");
        response.setContentType("application/msexcel");

        XSSFWorkbook xwb = new XSSFWorkbook();
        XSSFSheet xSheet = xwb.createSheet("sheet1");
        XSSFRow xRow = xSheet.createRow(0);
        for (int i = 0; i < titile.length; i++) {
            xRow.createCell(i).setCellValue(titile[i]);
        }

        for (int i = 1; i <= list.size(); i++) {
            XSSFRow xRow1 = xSheet.createRow(i);
            for (int j = 0; j < table.length; j++) {
                if ("xh".equals(table[j])) {
                    xRow1.createCell(j).setCellValue(i);
                } else {
                    xRow1.createCell(j).setCellValue(getFieldValueByName(table[j],list.get(i-1))==null?"":getFieldValueByName(table[j],list.get(i-1)).toString());
                }
            }
        }
        OutputStream out=response.getOutputStream();
        xwb.write(out);
        out.close();
    }


    private  static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = null;
            value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
