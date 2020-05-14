package lozm.dto.file;


import lombok.Getter;
import lombok.Setter;

public class FileDto {

    @Getter
    @Setter
    static public class Response {
        private String fileName;
        private String fileType;
        private String fileDownloadUri;
        private long fileSize;
    }

}
