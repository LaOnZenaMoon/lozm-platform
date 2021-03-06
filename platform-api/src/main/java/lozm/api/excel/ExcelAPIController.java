package lozm.api.excel;

import lombok.RequiredArgsConstructor;
import lozm.object.dto.ApiResponseCode;
import lozm.object.dto.ApiResponseDto;
import lozm.api.file.FileAPIController;
import lozm.api.file.FileService;
import lozm.global.props.FileProps;
import lozm.object.vo.file.FileVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@CrossOrigin("*")
@RestController @RequestMapping(value = "/api/excel")
@RequiredArgsConstructor
public class ExcelAPIController {

    private final ExcelService excelService;
    private final FileService fileService;
    private final FileProps fileProps;


    @GetMapping(value = "/download", produces = "application/vnd.ms-excel")
    public ModelAndView downloadExcel() {
        return new ModelAndView("excelView");
    }

    @PostMapping(value = "/upload")
    public ApiResponseDto uploadExcel(@RequestParam("file") MultipartFile file) {
        FileVo fileVo = FileVo.builder()
                .name(file.getOriginalFilename())
                .path(fileProps.getUploadPath())
                .build();
        String fileName = fileService.saveFile(file, fileVo);

        excelService.uploadExcel(file);

        return ApiResponseDto.createException(ApiResponseCode.OK, fileName);
    }

}
