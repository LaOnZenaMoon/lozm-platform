package lozm.vo.file;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class FileVo {

    private String name;
    private String path;
    private String contentType;

}
