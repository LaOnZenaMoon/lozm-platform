package lozm.image;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lozm.dto.APIResponseDto;
import lozm.dto.image.ImageDto;
import lozm.vo.image.ImageVo;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/api/image")
@RequiredArgsConstructor
public class ImageAPIController {

    private static Map<String, byte[]> imageCash;

    private final ImageService imageService;


    public void getImage(HttpServletResponse response, @ModelAttribute ImageDto.Request reqDto) {
        //TODO 리팩토링 필요
        //imageVo 가 null 일 때 처리
        if (imageCash == null) {
            imageCash = new HashMap<String, byte[]>();
        }

        try {
            ImageVo imageVo = ImageVo.builder()
                    .imageName(reqDto.getImageName())
                    .imageSize(reqDto.getImageSize())
                    .imageData(reqDto.getImageData())
                    .imageFileType(reqDto.getImageFileType())
                    .imageRotation(reqDto.getImageRotation())
                    .build();

            imageVo = StringUtils.isEmpty(reqDto.getImageName()) ? null : imageService.getImage(imageVo);

            byte[] imageFileToByte = Files.readAllBytes(imageVo.getImageFile().toPath());
            imageCash.put(reqDto.getImageName(), imageFileToByte);

            InputStream imageFileStream = new ByteArrayInputStream(imageFileToByte);
//            response.setContentType("png");
            StreamUtils.copy(imageFileStream, response.getOutputStream());
            imageFileStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/upload")
    public APIResponseDto uploadSingleImage(@RequestBody ImageDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            ImageVo imageVo = ImageVo.builder()
                    .imageName(reqDto.getImageName())
                    .imageSize(reqDto.getImageSize())
                    .imageData(reqDto.getImageData())
                    .imageFileType(reqDto.getImageFileType())
                    .imageRotation(reqDto.getImageRotation())
                    .build();

            imageService.uploadSingleImage(imageVo);

            resDto.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

}
