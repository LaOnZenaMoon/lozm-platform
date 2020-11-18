package lozm.api.image;

import lombok.RequiredArgsConstructor;
import lozm.global.props.ImageProps;
import lozm.object.vo.image.ImageVo;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static lozm.util.ImageUtils.*;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageProps imageProps;


    public ImageVo getImage(ImageVo imageVo) {
        imageVo = checkImageFileExisted(imageVo);

        String imageFilePath = imageProps.getSourcePath(imageProps.getUploadPath())
                .resolve(imageProps.getDefaultUserImage())
                .toString();

        File resFile = imageVo.getImageFile().exists() ? imageVo.getImageFile() : new File(imageFilePath);
        imageVo.setImageFile(resFile);

        return imageVo;
    }

    public ImageVo uploadSingleImage(ImageVo imageVo) throws IOException {
        // 1. 기존 이미지 체크
        //TODO 이미지 테이블 조회
        imageVo = checkImageFileExisted(imageVo);
        // 2. 기존 이미지 존재 -> 삭제 처리
        // 3. 새로운 이미지 생성
        createImageFile(imageVo);
        // 4. 새로운 이미지 Resizing 처리
        setResizeImageFile(imageVo);
        // 5. 이미지 회전값 설정
        setRotateImageFile(imageVo);
        // 6. 새로운 이미지 등록
        // 7. 데이터 재설정

        return imageVo;
    }

    private ImageVo checkImageFileExisted(ImageVo imageVo) {
        imageProps.createFilesDirectories(imageProps.getSourcePath(imageProps.getUploadPath()));

        Path imageFilePath = imageProps.getSourcePath(imageProps.getUploadPath())
                .resolve(imageVo.getImageName())
                .normalize();

        imageVo.setImageFilePath(imageFilePath.toString());
        imageVo.setImageFile(new File(imageVo.getImageFilePath()));

        return imageVo;
    }

}
