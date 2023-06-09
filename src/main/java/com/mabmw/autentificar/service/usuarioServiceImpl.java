package com.mabmw.autentificar.service;

import com.mabmw.autentificar.dto.ResponseDto;
import com.mabmw.autentificar.entity.UsuarioEntity;
import com.mabmw.autentificar.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class usuarioServiceImpl implements usuarioService{
    ResponseDto rsp = new ResponseDto();
    @Autowired
    userRepository userRepo;
    @Override
    public ResponseDto getAllUser() {
        try{
            List<UsuarioEntity> usuariosList = userRepo.findAll();
            rsp.setCodigo(200);
            rsp.setMensaje("[INFO]: Usuarios encontrados.");
            rsp.setRespuesta(usuariosList);
        }catch (Exception exp){
            rsp.setCodigo(500);
            rsp.setMensaje("[Error]: No se ha podido devolver la lista usuarios");
            rsp.setRespuesta(exp.getMessage());
        }
        return rsp;
    }

    @Override
    public ResponseDto getbyEmail(String email){
        UsuarioEntity ue = userRepo.findAll()
                .stream()
                .filter(e -> e.getNombre().equals(email))
                .findFirst()
                .orElse(null);
        rsp.setCodigo(200);
        rsp.setMensaje("User encontrado");
        rsp.setRespuesta(ue);
     return  rsp;
    }
}
