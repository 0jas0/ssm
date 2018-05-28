package com.jas.web.service;

import com.jas.web.model.ExcelModel;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface IExcelFileService {
    List<ExcelModel> getDataByExcel(Workbook workbook);

    Workbook getWorkbookByData(List<ExcelModel> dataList);
}
