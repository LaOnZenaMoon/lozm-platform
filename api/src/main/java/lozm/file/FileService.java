package lozm.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lozm.exception.APIException;
import lozm.props.FileProps;
import lozm.vo.file.FileVo;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

    private final FileProps fileProps;


    public String saveFile(MultipartFile file, FileVo fileVo) throws Exception {
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName = fileVo.getName();
        String filePath = fileVo.getPath();

        try {
            if (fileName.contains("..")) {
                throw new APIException("FILE001001", "File name has a problem.");
            }

            Path sourcePath = fileProps.getSourcePath(filePath);
            fileProps.createFilesDirectories(sourcePath);
            Path targetPath = sourcePath.resolve(fileName).normalize();

            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (APIException apie) {
            apie.printStackTrace();
            throw new APIException("VVB000000", apie.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("VVB001000", e.getMessage());
        }
    }

    public Resource downloadFile(FileVo fileVo) throws Exception {
        String fileName = fileVo.getName();
        String filePath = fileVo.getPath();

        try {
            Path path = fileProps.getSourcePath(filePath).resolve(fileName)
                    .normalize();
            Resource resource = new UrlResource(path.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new APIException("FILE002001", "Resource has a problem.");
            }
        } catch (MalformedURLException mue) {
            throw new APIException("FILE002001", mue.getMessage());
        } catch (APIException apie) {
            throw new APIException("FILE002001", apie.getMessage());
        } catch (Exception e) {
            throw new APIException("FILE002001", e.getMessage());
        }
    }

    public MultipartFile convertFileToMultipartFile(FileVo fileVo) {
        String fileName = fileVo.getName();
        String filePath = fileVo.getPath();
        String contentType = fileVo.getContentType();

        try {
            Path path = fileProps.getSourcePath(filePath).resolve(fileName)
                    .normalize();
            File rtnFile = path.toFile();
            byte[] rtnFileContent = Files.readAllBytes(path);

            if (rtnFile.exists()) {
                return new MockMultipartFile(fileName, fileName, contentType, rtnFileContent);
            } else {
                throw new APIException("FILE002001", "File is already exists.");
            }
        } catch (APIException apie) {
            throw new APIException("FILE002001", apie.getMessage());
        } catch (Exception e) {
            throw new APIException("FILE002001", e.getMessage());
        }
    }

}
