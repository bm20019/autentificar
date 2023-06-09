package com.mabmw.autentificar.dto;

import lombok.Data;
@Data
public class ResponseDto {
    private int codigo;
    private String mensaje;
    private Object respuesta;

    public ResponseDto() {
    }
}
