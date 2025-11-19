package com.avacom.erp.modules.usuarios.service;

import com.avacom.erp.modules.usuarios.dto.*;

import java.util.List;

public interface UsuarioService {

    List<UsuarioDto> listarTodos();

    List<UsuarioDto> buscarPorFiltro(UsuarioFiltroRequest filtro);

    UsuarioDto crear(CrearUsuarioRequest request);

    UsuarioDto actualizar(Long id, ActualizarUsuarioRequest request);

    UsuarioDto obtenerPorId(Long id);

    void eliminar(Long id);
}