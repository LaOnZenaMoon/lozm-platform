package lozm.dto.image;


import lombok.Getter;

public class ImageDto {

    @Getter
    static public class Request {
        private String imageName;
        private String imageSize;
        private String imageData;
        private String imageFileType;
        private int imageRotation;
    }

}
