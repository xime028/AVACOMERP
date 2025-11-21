package com.avacom.erp.modules.almacen.service;

import com.avacom.erp.modules.almacen.dto.ActualizarSerialRequest;
import com.avacom.erp.modules.almacen.dto.CrearSerialRequest;
import com.avacom.erp.modules.almacen.dto.SerialDto;
import com.avacom.erp.modules.almacen.dto.SerialFiltroRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SerialService {

    SerialDto crear(CrearSerialRequest request);

    SerialDto actualizar(Long id, ActualizarSerialRequest request);

    SerialDto obtener(Long id);

    List<SerialDto> listar();

    List<SerialDto> buscar(SerialFiltroRequest filtro);

    List<SerialDto> listarPorProducto(Long idProducto);

    void eliminar(Long id);

    SerialDto cambiarEstado(@PathVariable Long idSerial, @RequestParam("estado") String nuevoEstado);

    List<SerialDto> listarDisponiblesPorProducto(Long idProducto);

    List<SerialDto> obtenerPorProducto(Long idProducto);
}