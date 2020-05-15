package lozm.excel;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component("excelView")
public class ExcelView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"sample.xlsx\"");
        CellStyle numberCellStyle = workbook.createCellStyle();
        DataFormat numberDataFormat = workbook.createDataFormat();
        numberCellStyle.setDataFormat(numberDataFormat.getFormat("#,##0"));
        Sheet sheet = workbook.createSheet("sample_sheet");
        Row row0 = sheet.createRow(0);
        Cell cell0 = row0.createCell(0);
        cell0.setCellValue("날짜");
        Cell cell1 = row0.createCell(1);
        cell1.setCellValue("이름");
        Cell cell2 = row0.createCell(2);
        cell2.setCellValue("연봉");
        Row row1 = sheet.createRow(1);
        cell0 = row1.createCell(0);
        cell0.setCellValue("2019-01-01");
        cell1 = row1.createCell(1);
        cell1.setCellValue("홍길동");
        cell2 = row1.createCell(2);
        cell2.setCellType(CellType.NUMERIC);
        cell2.setCellValue(1000000);
        cell2.setCellStyle(numberCellStyle);
    }

}
