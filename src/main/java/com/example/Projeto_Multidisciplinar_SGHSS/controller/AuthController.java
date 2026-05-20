package com.example.Projeto_Multidisciplinar_SGHSS.controller;

import com.example.Projeto_Multidisciplinar_SGHSS.dto.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Módulo de Autenticação & Segurança", description = "Endpoint para controle de acesso, geração de tokens JWT e validação de perfis (RBAC)")
public class AuthController {

    @PostMapping("/login")
    @Operation(summary = "Realizar autenticação no sistema", description = "Valida as credenciais do profissional de saúde e retorna um token JWT estático contendo os perfis de acesso autorizados.")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        Map<String, String> resposta = new HashMap<>();

        // Simulação de validação de credenciais corporativas da rede VidaPlus
        if ("medico@vidaplus.com".equals(loginRequest.getUsuario()) && "senha123".equals(loginRequest.getSenha())) {
            resposta.put("status", "Autenticado com sucesso");
            resposta.put("usuario", loginRequest.getUsuario());
            resposta.put("perfil", "ROLE_MEDICO");
            // Token JWT simulado contendo a assinatura lúdica do sistema
            resposta.put("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.SGHSS_VIDAPLUS_MEDICO_TOKEN_EVIDENCIA");

            return new ResponseEntity<>(resposta, HttpStatus.OK);
        } else if ("admin@vidaplus.com".equals(loginRequest.getUsuario()) && "admin123".equals(loginRequest.getSenha())) {
            resposta.put("status", "Autenticado com sucesso");
            resposta.put("usuario", loginRequest.getUsuario());
            resposta.put("perfil", "ROLE_ADMIN");
            resposta.put("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.SGHSS_VIDAPLUS_ADMIN_TOKEN_EVIDENCIA");

            return new ResponseEntity<>(resposta, HttpStatus.OK);
        }

        // Credenciais inválidas
        resposta.put("erro", "Usuário ou senha inválidos no sistema central da VidaPlus");
        return new ResponseEntity<>(resposta, HttpStatus.UNAUTHORIZED);// 401 Unauthorized
    }
}