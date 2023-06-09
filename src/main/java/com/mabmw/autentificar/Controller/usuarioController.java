package com.mabmw.autentificar.Controller;

import com.mabmw.autentificar.dto.ResponseDto;
import com.mabmw.autentificar.service.usuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import com.mabmw.autentificar.entity.AuthenticationReq;
import com.mabmw.autentificar.entity.TokenInfo;
import com.mabmw.autentificar.service.JwtUtilService;

@RestController
@RequestMapping("example")
public class usuarioController {
    @Autowired
    usuarioService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService usuarioDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public ResponseDto getAll(){
        var auth =  SecurityContextHolder.getContext().getAuthentication();
        return userService.getAllUser();
    }

    @GetMapping("/publico/")
    public ResponseDto testPublic(){
        var auth =  SecurityContextHolder.getContext().getAuthentication();
        ResponseDto rsp = new ResponseDto();
        rsp.setRespuesta(200);
        rsp.setMensaje("Bienvenido a esta api");
        rsp.setRespuesta(auth.getAuthorities());
        return rsp;
    }

    @PostMapping("/publico/auth")
    public ResponseDto Auth(@RequestBody AuthenticationReq authenticationReq){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationReq.getUsuario(),
                        authenticationReq.getClave()));

        final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(
                authenticationReq.getUsuario());

        final String jwt = jwtUtilService.generateToken(userDetails);

        ResponseDto rsp = new ResponseDto();
        rsp.setCodigo(200);
        rsp.setMensaje("Token Generado");
        rsp.setRespuesta(new TokenInfo(jwt));
        return rsp; // (new TokenInfo(jwt));
    }
}
