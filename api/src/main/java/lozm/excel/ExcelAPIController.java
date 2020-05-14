package lozm.excel;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/excel")
@RequiredArgsConstructor
public class ExcelAPIController {

    private final ExcelService excelService;




}
