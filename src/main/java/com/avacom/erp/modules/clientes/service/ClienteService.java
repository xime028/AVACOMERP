package com.avacom.erp.modules.clientes.service;

import com.avacom.erp.modules.clientes.dto.*;

import java.util.List;

public interface ClienteService {

    ClienteDto crear(CrearClienteRequest request);

    ClienteDto actualizar(Long id, ActualizarClienteRequest request);

    ClienteDetalleDto obtenerDetalle(Long id);

    List<ClienteDto> listarTodos();

    List<ClienteDto> buscarPorFiltro(ClienteFiltroRequest filtro);

    void eliminar(Long id);
}