package com.avacom.erp.modules.almacen.service;

import com.avacom.erp.modules.almacen.dto.MovimientoSerialDto;
import com.avacom.erp.modules.almacen.dto.MovimientoSerialFiltroRequest;
import com.avacom.erp.modules.almacen.entity.SerialEntity;
import com.avacom.erp.modules.usuarios.entity.UsuarioEntity;

import java.util.List;

public interface MovimientoSerialService {

    /**
     * Uso interno desde otros módulos para registrar movimientos.
     */
    MovimientoSerialDto registrarMovimiento(SerialEntity serial,
                                            String tipo,
                                            String modulo,
                                            Long idReferencia,
                                            UsuarioEntity usuario);

    /**
     * Listar historial filtrado.
     */
    List<MovimientoSerialDto> buscar(MovimientoSerialFiltroRequest filtro);

    List<MovimientoSerialDto> listarPorSerial(Long idSerial);
}