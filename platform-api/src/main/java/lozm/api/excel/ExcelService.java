package lozm.api.excel;

import lombok.RequiredArgsConstructor;
import lozm.global.exception.ServiceException;
import lozm.object.dto.orders.GetOrdersDto;
import lozm.entity.delivery.Delivery;
import lozm.api.orders.OrdersService;
import lozm.repository.delivery.DeliveryRepository;
import lozm.object.vo.delivery.DeliveryVo;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExcelService {

    private final OrdersService ordersService;
    private final DeliveryRepository deliveryRepository;


    @Transactional
    public Sheet getSampleList(Workbook workbook) {
        //Get the list
        List<GetOrdersDto> ordersList = ordersService.getOrdersList();

        if(ordersList.size() == 0) throw new ServiceException("EXCEL_DOWNLOAD_NO_LIST", "There is no result.");

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
    }

    @Transactional
    public void uploadExcel(MultipartFile file) {
        List<Delivery> deliveryList = new ArrayList<>();

        try {
            //Read the file using Workbook
            OPCPackage opcPackage = OPCPackage.open(file.getInputStream());
            XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);

            //Insert the entity to DB
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i < sheet.getLastRowNum(); i++) {
                XSSFRow row = sheet.getRow(i);
                DeliveryVo vo = DeliveryVo.builder()
                        .country(row.getCell(4).toString())
                        .zipCode(row.getCell(5).toString())
                        .city(row.getCell(6).toString())
                        .street(row.getCell(7).toString())
                        .etc(row.getCell(8).toString())
                        .build();

                Delivery delivery = new Delivery();
                delivery.insertDelivery(vo);

                System.out.println("delivery = " + delivery);
                deliveryList.add(delivery);
            }

            deliveryRepository.saveAll(deliveryList);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
