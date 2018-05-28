package com.jas.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jas.web.model.ExcelModel;
import com.jas.web.service.IExcelFileService;
import com.jas.web.utils.ExcelUtil;
import com.jas.web.utils.HttpUtil;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class ExcelFileServiceImpl implements IExcelFileService{
    public List<ExcelModel> getDataByExcel(Workbook workbook) {
        List<ExcelModel> excelModels = new LinkedList<ExcelModel>();
        //读取第一个sheet页
        Sheet sheet = workbook.getSheetAt(0);
        //多取第一行和最后一行
        int lastRowNum = sheet.getLastRowNum();
        //行号和列号
        int rowNum;
        for (rowNum = 1; rowNum <= lastRowNum; rowNum++) {
            int colNum = 0;
            Row row = sheet.getRow(rowNum);
            //获取每个cell的值
            ExcelModel excelModel = new ExcelModel();
            excelModel.setCusomerId(ExcelUtil.getStringVal(row.getCell(colNum++)));
            excelModel.setComment(ExcelUtil.getStringVal(row.getCell(colNum++)));
            excelModel.setCreatedOnUtc(ExcelUtil.getStringVal(row.getCell(colNum++)));
            String ip = ExcelUtil.getStringVal(row.getCell(colNum++));
            String jsonData = HttpUtil.get("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
            if (jsonData != null) {
                JSONObject jsonObject = JSONObject.parseObject(jsonData);
                JSONObject data = jsonObject.getJSONObject("data");
                excelModel.setProvinceName(data.getString("region"));
                excelModel.setCityName(data.getString("city"));
            }
            excelModel.setIPAddress(ip);
            excelModel.setCompanyCode(ExcelUtil.getStringVal(row.getCell(colNum++)));
            excelModels.add(excelModel);
        }
        return excelModels;
    }

    public Workbook getWorkbookByData(List<ExcelModel> dataList) {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("ActivityLog");
        int colNum = 0, rowNum = 0;
        Row row = sheet.createRow(colNum++);
        String[] barNames = {"CusomerId","Comment","CreatedOnUtc","IPAddress","CompanyCode","provinceName","cityName"};
        for(String barName : barNames){
            Cell cell = row.createCell(rowNum++);
            HSSFCellStyle cellStyle = (HSSFCellStyle )workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            cellStyle.setFont(font);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(barName);
        }
        for(ExcelModel excelExportDO : dataList){
            rowNum=0;
            row = sheet.createRow(colNum++);
            row.createCell(rowNum++).setCellValue(excelExportDO.getCusomerId());
            row.createCell(rowNum++).setCellValue(excelExportDO.getComment());
            row.createCell(rowNum++).setCellValue(excelExportDO.getCreatedOnUtc());
            row.createCell(rowNum++).setCellValue(excelExportDO.getIPAddress());
            row.createCell(rowNum++).setCellValue(excelExportDO.getCompanyCode());
            row.createCell(rowNum++).setCellValue(excelExportDO.getProvinceName());
            row.createCell(rowNum++).setCellValue(excelExportDO.getCityName());
        }
        return workbook;
    }
}
