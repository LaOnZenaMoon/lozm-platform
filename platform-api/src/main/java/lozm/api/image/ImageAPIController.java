package lozm.api.image;

import lombok.RequiredArgsConstructor;
import lozm.object.dto.ApiResponseCode;
import lozm.object.dto.ApiResponseDto;
import lozm.object.dto.image.ImageDto;
import lozm.object.vo.image.ImageVo;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin("*")
@RestController @RequestMapping(value = "/api/image")
@RequiredArgsConstructor
public class ImageAPIController {

    private static Map<String, byte[]> imageCash;
    private final ImageService imageService;


    @GetMapping("/{fileName:.+}")
    public void getImage(@PathVariable String fileName, HttpServletResponse response) {
        try {
            if (imageCash == null) {
                imageCash = new HashMap<String, byte[]>();
            }

            byte[] imageFileToByte = null;
            if(imageCash.containsKey(fileName)) {
                imageFileToByte = imageCash.get(fileName);
            } else {
                ImageVo imageVo = ImageVo.builder()
                        .imageName(fileName)
                        .build();

                imageVo = StringUtils.isEmpty(fileName) ? null : imageService.getImage(imageVo);

                imageFileToByte = Files.readAllBytes(imageVo.getImageFile().toPath());
                imageCash.put(fileName, imageFileToByte);
            }

            InputStream imageFileStream = new ByteArrayInputStream(imageFileToByte);
            //response.setContentType("png");
            StreamUtils.copy(imageFileStream, response.getOutputStream());
            imageFileStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/upload")
    public ApiResponseDto uploadSingleImage(@RequestBody ImageDto.Request reqDto) throws IOException {
        ImageVo imageVo = ImageVo.builder()
                .imageName(reqDto.getImageName())
                .imageSize(reqDto.getImageSize())
                .imageData(reqDto.getImageData())
                .imageFileType(reqDto.getImageFileType())
                .imageRotation(reqDto.getImageRotation())
                .build();

        imageService.uploadSingleImage(imageVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

}
