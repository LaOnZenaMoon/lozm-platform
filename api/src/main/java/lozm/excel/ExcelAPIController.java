package lozm.excel;

import lombok.RequiredArgsConstructor;
import lozm.dto.APIResponseDto;
import lozm.file.FileAPIController;
import lozm.file.FileService;
import lozm.props.FileProps;
import lozm.vo.file.FileVo;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@RequestMapping(value = "/api/excel")
@RestController
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
    public APIResponseDto uploadExcel(@RequestParam("file") MultipartFile file) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            FileVo fileVo = FileVo.builder()
                    .name(file.getOriginalFilename())
                    .path(fileProps.getUploadPath())
                    .build();
            String fileName = fileService.saveFile(file, fileVo);

            excelService.uploadExcel(file);

            resDto.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

}
