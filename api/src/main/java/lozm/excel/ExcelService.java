package lozm.excel;

import lombok.RequiredArgsConstructor;
import lombok.val;
import lozm.dto.orders.GetOrdersDto;
import lozm.exception.APIException;
import lozm.orders.OrdersService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelService {

    private final OrdersService ordersService;


    public Sheet getSampleList(Workbook workbook) throws Exception {
        //Get the list
        List<GetOrdersDto> ordersList = ordersService.getOrdersList();

        if(ordersList.size() == 0) throw new APIException("EXCEL_DOWNLOAD_NO_LIST", "There is no result.");

        //Make the workbook
        Workbook wb =  workbook;
        Sheet sheet = wb.createSheet();

        //Set the header
        Row row0 = sheet.createRow(0);
        Field[] declaredFields = GetOrdersDto.class.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Cell cell = row0.createCell(i);
            cell.setCellValue(declaredFields[i].getName());
        }

        //Set the body
        //TODO 엔티티 일반화해서 처리할 방법 고려
        for (int i = 0; i < ordersList.size(); i++) {
            Row row = sheet.createRow(i + 1);
            GetOrdersDto item = ordersList.get(i);
            row.createCell(0).setCellValue(item.getOrdersId());
            row.createCell(1).setCellValue(item.getOrderDt());
            row.createCell(2).setCellValue(String.valueOf(item.getOrdersStatus()));
            row.createCell(3).setCellValue(item.getDeliveryId());
            row.createCell(4).setCellValue(item.getDeliveryCountry());
            row.createCell(5).setCellValue(item.getDeliveryZipCode());
            row.createCell(6).setCellValue(item.getDeliveryCity());
            row.createCell(7).setCellValue(item.getDeliveryStreet());
            row.createCell(8).setCellValue(item.getDeliveryEtc());
            row.createCell(9).setCellValue(item.getUserId());
            row.createCell(10).setCellValue(item.getUserName());
            row.createCell(11).setCellValue(item.getIdentifier());
            row.createCell(12).setCellValue(String.valueOf(item.getUserType()));
        }

        return sheet;
//        for (int i = 0; i < ordersList.size(); i++) {
//            Row row = sheet.createRow(i + 1);
//            GetOrdersDto item = ordersList.get(i);
//            Field[] itemDeclaredFields = item.getClass().getDeclaredFields();
//            for (int j = 0; j < itemDeclaredFields.length; j++) {
//                Cell cell = row.createCell(j);
//                System.out.println("cell = " + cell);
////                String name = String.valueOf(itemDeclaredFields[j]);
////                Field field = item.getClass().getField(name);
////                cell.setCellValue(field1);
//            }
//        }
    }

}
