package com.mabmw.autentificar.service;

import com.mabmw.autentificar.dto.ResponseDto;

public interface usuarioService {
    public ResponseDto getAllUser();

    ResponseDto getbyEmail(String email);
}
