package com.avacom.erp.modules.analitica.service;

import com.avacom.erp.modules.analitica.dto.*;
import com.avacom.erp.modules.analitica.repository.AnaliticaRepository;
import com.avacom.erp.modules.analitica.validator.AnaliticaFechaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnaliticaServiceImpl implements AnaliticaService {

    private final AnaliticaRepository analiticaRepository;
    private final AnaliticaFechaValidator fechaValidator;

    @Override
    public ResumenGeneralDto obtenerResumenGeneral() {
        long totalClientes = analiticaRepository.contarClientes();
        long totalUsuarios = analiticaRepository.contarUsuarios();
        long totalProductos = analiticaRepository.contarProductos();
        long totalCapacitaciones = analiticaRepository.contarCapacitaciones();
        long totalOrdenesServicio = analiticaRepository.contarOrdenesServicio();
        long totalGarantiasActivas = analiticaRepository.contarGarantiasActivas();
        long totalArchivos = analiticaRepository.contarArchivos();

        return ResumenGeneralDto.builder()
                .totalClientes(totalClientes)
                .totalUsuarios(totalUsuarios)
                .totalProductos(totalProductos)
                .totalCapacitaciones(totalCapacitaciones)
                .totalOrdenesServicio(totalOrdenesServicio)
                .totalGarantiasActivas(totalGarantiasActivas)
                .totalArchivos(totalArchivos)
                .build();
    }

    @Override
    public KpiInventarioDto obtenerKpiInventario() {
        return analiticaRepository.obtenerKpiInventario();
    }

    @Override
    public KpiEntradasSalidasDto obtenerKpiEntradasSalidas(AnaliticaFiltroFechaRequest filtro) {
        AnaliticaFiltroFechaRequest f = normalizarFiltro(filtro);
        return analiticaRepository.obtenerKpiEntradasSalidas(f.getFechaDesde(), f.getFechaHasta());
    }

    @Override
    public KpiServicioTecnicoDto obtenerKpiServicioTecnico(AnaliticaFiltroFechaRequest filtro) {
        AnaliticaFiltroFechaRequest f = normalizarFiltro(filtro);
        return analiticaRepository.obtenerKpiServicioTecnico(f.getFechaDesde(), f.getFechaHasta());
    }

    @Override
    public KpiUsuariosActividadDto obtenerKpiUsuariosActividad(AnaliticaFiltroFechaRequest filtro) {
        AnaliticaFiltroFechaRequest f = normalizarFiltro(filtro);
        return analiticaRepository.obtenerKpiUsuariosActividad(f.getFechaDesde(), f.getFechaHasta());
    }

    private AnaliticaFiltroFechaRequest normalizarFiltro(AnaliticaFiltroFechaRequest filtro) {
        AnaliticaFiltroFechaRequest result = filtro != null ? filtro : new AnaliticaFiltroFechaRequest();
        OffsetDateTime ahora = OffsetDateTime.now();

        if (result.getFechaHasta() == null) {
            result.setFechaHasta(ahora);
        }
        if (result.getFechaDesde() == null) {
            // por defecto, últimos 30 días
            result.setFechaDesde(result.getFechaHasta().minusDays(30));
        }

        fechaValidator.validarRango(result);
        return result;
    }
}