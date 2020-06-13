package lozm.object.vo.file;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FileVo {

    private String name;
    private String path;
    private String contentType;

}
