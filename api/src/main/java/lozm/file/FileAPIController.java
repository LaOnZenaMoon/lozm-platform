package lozm.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lozm.dto.APIResponseDto;
import lozm.props.FileProps;
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

@Slf4j
@RequestMapping(value = "/api/file")
@RestController
@RequiredArgsConstructor
public class FileAPIController {

    private final FileService fileService;
    private final FileProps fileProps;


    @PostMapping(value = "/uploadSingleFile")
    public APIResponseDto uploadSingleFile(@RequestParam("file") MultipartFile file) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {


            String fileName = fileService.saveFile(file, fileProps.getUploadPath(), file.getOriginalFilename());
            resDto.setFileName(fileName);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
            resDto.setFileDownloadUri(fileDownloadUri);
            resDto.setFileType(file.getContentType());
            resDto.setFileSize(file.getSize());

            resDto.setEvType("S");
        } catch (Exception e) {
            log.debug(e.getMessage());
            resDto.setEvType("E");
            resDto.setEvMsg(e.getMessage());
        }

        return resDto;
    }

    @PostMapping(value = "/uploadMultipleFile")
    public List<FileDto.Response> uploadMultipleFile(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
            .stream()
            .map(file -> uploadSingleFile(file))
            .collect(Collectors.toList());
    }

    @GetMapping(value = "/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = null;
        String contentType = null;

        try {
            resource = fileService.downloadFile(fileProps.getUploadPath(), fileName);

            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            if(contentType == null) contentType = "application/octet-stream";
        } catch (IOException ex) {
            log.debug("Could not determine file type.");
            log.debug(ex.getMessage());
        } catch (Exception e) {
            log.debug(e.getMessage());
        }

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
            .body(resource);
    }

}
