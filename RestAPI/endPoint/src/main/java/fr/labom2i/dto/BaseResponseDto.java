package fr.labom2i.dto;

public class BaseResponseDto {

    private String message;

    private Object data;

    public BaseResponseDto(String message, Object data) {
        this.message = message;
        this.data = data;
    }


    public BaseResponseDto(String message) {
        this.message = message;
    }
}
