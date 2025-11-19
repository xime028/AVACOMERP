package com.avacom.erp.modules.usuarios.service;

import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;
import com.avacom.erp.modules.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuario = usuarioRepository.findByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        String rolNombre = usuario.getRol().getNombre();
        if (!rolNombre.startsWith("ROLE_")) {
            rolNombre = "ROLE_" + rolNombre;
        }

        List<GrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority(rolNombre));

        return User.builder()
                .username(usuario.getUsuario())
                .password(usuario.getContrasenaHash())
                .authorities(authorities)
                .accountLocked(false)
                .disabled(!"ACTIVO".equalsIgnoreCase(usuario.getEstado()))
                .build();
    }
}