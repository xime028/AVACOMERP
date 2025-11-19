package com.avacom.erp.modules.analitica.service;

import com.avacom.erp.modules.analitica.dto.*;

public interface AnaliticaService {

    ResumenGeneralDto obtenerResumenGeneral();

    KpiInventarioDto obtenerKpiInventario();

    KpiEntradasSalidasDto obtenerKpiEntradasSalidas(AnaliticaFiltroFechaRequest filtro);

    KpiServicioTecnicoDto obtenerKpiServicioTecnico(AnaliticaFiltroFechaRequest filtro);

    KpiUsuariosActividadDto obtenerKpiUsuariosActividad(AnaliticaFiltroFechaRequest filtro);
}