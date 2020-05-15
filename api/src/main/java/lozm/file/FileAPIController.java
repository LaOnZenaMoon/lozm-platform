package lozm.file;

import lombok.RequiredArgsConstructor;
import lozm.dto.APIResponseDto;
import lozm.dto.file.FileDto;
import lozm.props.FileProps;
import lozm.vo.file.FileVo;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RequestMapping(value = "/api/file")
@RestController
@RequiredArgsConstructor
public class FileAPIController {

    private final FileService fileService;
    private final FileProps fileProps;


    @PostMapping(value = "/upload/single")
    public APIResponseDto uploadSingleFile(@RequestParam("file") MultipartFile file) {
        APIResponseDto resDto = new APIResponseDto<>();
        FileDto.Response dto = new FileDto.Response();

        try {
            FileVo fileVo = FileVo.builder()
                    .name(file.getOriginalFilename())
                    .path(fileProps.getUploadPath())
                    .build();

            String fileName = fileService.saveFile(file, fileVo);
            dto.setFileName(fileName);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/file/download/")
                .path(fileName)
                .toUriString();
            dto.setFileDownloadUri(fileDownloadUri);
            dto.setFileType(file.getContentType());
            dto.setFileSize(file.getSize());

            resDto.setSuccess(true);
            resDto.setData(dto);
        } catch (Exception e) {
            e.printStackTrace();
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @PostMapping(value = "/upload/multi")
    public List<APIResponseDto> uploadMultipleFile(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
            .stream()
            .map(file -> uploadSingleFile(file))
            .collect(Collectors.toList());
    }

    @GetMapping(value = "/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = null;
        String contentType = null;

        try {
            FileVo fileVo = FileVo.builder()
                    .name(fileName)
                    .path(fileProps.getUploadPath())
                    .build();

            resource = fileService.downloadFile(fileVo);

            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            if(contentType == null) contentType = "application/octet-stream";
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
            .body(resource);
    }

}
