package com.avacom.erp.modules.usuarios.service;

import com.avacom.erp.modules.usuarios.dto.RolDto;

import java.util.List;

public interface RolService {

    List<RolDto> listarTodos();

    RolDto crear(RolDto dto);

    RolDto obtenerPorId(Long id);

    RolDto actualizar(Long id, RolDto dto);

    void eliminar(Long id);
}