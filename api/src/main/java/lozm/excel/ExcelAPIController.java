package lozm.excel;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping(value = "/api/excel")
@RestController
@RequiredArgsConstructor
public class ExcelAPIController {

    private final ExcelService excelService;


    @GetMapping(value = "/download", produces = "application/vnd.ms-excel")
    public String downloadExcel() {
        return "excelView";
    }

}
