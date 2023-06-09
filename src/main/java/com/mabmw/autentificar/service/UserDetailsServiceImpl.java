package com.mabmw.autentificar.service;

import com.mabmw.autentificar.dto.ResponseDto;
import com.mabmw.autentificar.entity.UsuarioEntity;
import com.mabmw.autentificar.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    ResponseDto rsp = new ResponseDto();
    @Autowired
    userRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = getbyEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        UsuarioEntity ue =(UsuarioEntity)user.getRespuesta();
        return User
                .withUsername(username)
                .password(ue.getPass())
                .roles("ADMIN")
                .build();
    }

    public ResponseDto getbyEmail(String email){
        UsuarioEntity ue = userRepo.findAll()
                .stream()
                .filter(e -> e.getNombre().equals(email))
                .findFirst()
                .orElse(null);
        rsp.setCodigo(200);
        rsp.setMensaje("User encontrado");
        ue.setPass(new BCryptPasswordEncoder().encode(ue.getPass()));
        rsp.setRespuesta(ue);
        return  rsp;
    }
}
