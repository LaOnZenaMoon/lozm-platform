package lozm.object.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum ApiResponseCode implements EnumType {
    OK("success", true),
    BAD_PARAMETER("요청 파라미터가 잘못되었습니다.", false),
    ERROR_RESPONSE("오류 응답 입니다.", false),
    BAD_REQUEST("잘못된 요청 입니다.", false),
    NOT_FOUND("리소스를 찾지 못했습니다.", false),
    UNAUTHORIZED("인증에 실패하였습니다.", false),
    FORBIDDEN("금지된 접근입니다.", false),
    SERVER_ERROR("서버 에러입니다.", false);

    private final String message;
    private final boolean success;

    public String getId() {
        return name();
    }

    public String getText() {
        return message;
    }
}
