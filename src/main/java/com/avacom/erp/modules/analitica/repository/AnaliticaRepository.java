package com.avacom.erp.modules.analitica.repository;

import com.avacom.erp.modules.analitica.dto.KpiEntradasSalidasDto;
import com.avacom.erp.modules.analitica.dto.KpiInventarioDto;
import com.avacom.erp.modules.analitica.dto.KpiServicioTecnicoDto;
import com.avacom.erp.modules.analitica.dto.KpiUsuariosActividadDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Repository
public class AnaliticaRepository {

    @PersistenceContext
    private EntityManager em;

    // ---------- Contadores simples ----------

    public long contarClientes() {
        Number n = (Number) em.createNativeQuery("select count(*) from clientes").getSingleResult();
        return n.longValue();
    }

    public long contarUsuarios() {
        Number n = (Number) em.createNativeQuery("select count(*) from usuarios").getSingleResult();
        return n.longValue();
    }

    public long contarProductos() {
        Number n = (Number) em.createNativeQuery("select count(*) from productos").getSingleResult();
        return n.longValue();
    }

    public long contarCapacitaciones() {
        Number n = (Number) em.createNativeQuery("select count(*) from capacitaciones").getSingleResult();
        return n.longValue();
    }

    public long contarOrdenesServicio() {
        Number n = (Number) em.createNativeQuery("select count(*) from ordenes_servicios").getSingleResult();
        return n.longValue();
    }

    public long contarGarantiasActivas() {
        // Asumimos estado = 'ACTIVA' (case insensitive)
        Number n = (Number) em.createNativeQuery(
                        "select count(*) from garantias where upper(estado) = 'ACTIVA'")
                .getSingleResult();
        return n.longValue();
    }

    public long contarArchivos() {
        Number n = (Number) em.createNativeQuery("select count(*) from archivos").getSingleResult();
        return n.longValue();
    }

    // ---------- KPI Inventario ----------

    public KpiInventarioDto obtenerKpiInventario() {
        Number totalProductos = (Number) em.createNativeQuery("select count(*) from productos")
                .getSingleResult();

        Number stockTotal = (Number) em.createNativeQuery("select coalesce(sum(stock),0) from productos")
                .getSingleResult();

        Number stockReservadoTotal = (Number) em.createNativeQuery("select coalesce(sum(stock_reservado),0) from productos")
                .getSingleResult();

        Number productosSinStock = (Number) em.createNativeQuery("select count(*) from productos where coalesce(stock,0) <= 0")
                .getSingleResult();

        return KpiInventarioDto.builder()
                .totalProductos(totalProductos.longValue())
                .stockTotal(stockTotal.longValue())
                .stockReservadoTotal(stockReservadoTotal.longValue())
                .productosSinStock(productosSinStock.longValue())
                .build();
    }

    // ---------- KPI Entradas / Salidas ----------

    public KpiEntradasSalidasDto obtenerKpiEntradasSalidas(OffsetDateTime desde, OffsetDateTime hasta) {
        Number totalEntradas = (Number) em.createNativeQuery(
                        "select count(*) from entradas where fecha between :desde and :hasta")
                .setParameter("desde", desde)
                .setParameter("hasta", hasta)
                .getSingleResult();

        Number totalSalidas = (Number) em.createNativeQuery(
                        "select count(*) from salidas where fecha_salida between :desde and :hasta")
                .setParameter("desde", desde)
                .setParameter("hasta", hasta)
                .getSingleResult();

        Number totalItemsEntradas = (Number) em.createNativeQuery(
                        """
                        select coalesce(sum(ei.cantidad),0)
                        from entradas_items ei
                        join entradas e on e.id = ei.id_entrada
                        where e.fecha between :desde and :hasta
                        """)
                .setParameter("desde", desde)
                .setParameter("hasta", hasta)
                .getSingleResult();

        Number totalItemsSalidas = (Number) em.createNativeQuery(
                        """
                        select coalesce(sum(si.cantidad),0)
                        from salidas_items si
                        join salidas s on s.id = si.id_salida
                        where s.fecha_salida between :desde and :hasta
                        """)
                .setParameter("desde", desde)
                .setParameter("hasta", hasta)
                .getSingleResult();

        return KpiEntradasSalidasDto.builder()
                .fechaDesde(desde)
                .fechaHasta(hasta)
                .totalEntradas(totalEntradas.longValue())
                .totalSalidas(totalSalidas.longValue())
                .totalItemsEntradas(totalItemsEntradas.longValue())
                .totalItemsSalidas(totalItemsSalidas.longValue())
                .build();
    }

    // ---------- KPI Servicio Técnico ----------

    public KpiServicioTecnicoDto obtenerKpiServicioTecnico(OffsetDateTime desde, OffsetDateTime hasta) {
        Number totalOrdenes = (Number) em.createNativeQuery(
                        "select count(*) from ordenes_servicios where fecha_ingreso between :desde and :hasta")
                .setParameter("desde", desde)
                .setParameter("hasta", hasta)
                .getSingleResult();

        Number abiertas = (Number) em.createNativeQuery(
                        "select count(*) from ordenes_servicios where fecha_ingreso between :desde and :hasta and upper(estado) = 'ABIERTA'")
                .setParameter("desde", desde)
                .setParameter("hasta", hasta)
                .getSingleResult();

        Number enProceso = (Number) em.createNativeQuery(
                        "select count(*) from ordenes_servicios where fecha_ingreso between :desde and :hasta and upper(estado) = 'EN_PROCESO'")
                .setParameter("desde", desde)
                .setParameter("hasta", hasta)
                .getSingleResult();

        Number cerradas = (Number) em.createNativeQuery(
                        "select count(*) from ordenes_servicios where fecha_ingreso between :desde and :hasta and upper(estado) = 'CERRADA'")
                .setParameter("desde", desde)
                .setParameter("hasta", hasta)
                .getSingleResult();

        // Promedio de horas de cierre (solo órdenes CERRADAS)
        Object avgHorasObj = em.createNativeQuery(
                        """
                        select avg(extract(epoch from (fecha_actualizacion - fecha_ingreso)) / 3600.0)
                        from ordenes_servicios
                        where fecha_ingreso between :desde and :hasta
                          and fecha_actualizacion is not null
                          and upper(estado) = 'CERRADA'
                        """)
                .setParameter("desde", desde)
                .setParameter("hasta", hasta)
                .getSingleResult();

        double promedioHoras = 0.0;
        if (avgHorasObj != null) {
            if (avgHorasObj instanceof BigDecimal bd) {
                promedioHoras = bd.doubleValue();
            } else if (avgHorasObj instanceof Number n) {
                promedioHoras = n.doubleValue();
            }
        }

        return KpiServicioTecnicoDto.builder()
                .fechaDesde(desde)
                .fechaHasta(hasta)
                .totalOrdenes(totalOrdenes.longValue())
                .ordenesAbiertas(abiertas.longValue())
                .ordenesEnProceso(enProceso.longValue())
                .ordenesCerradas(cerradas.longValue())
                .promedioHorasCierre(promedioHoras)
                .build();
    }

    // ---------- KPI Usuarios / Auditoría ----------

    public KpiUsuariosActividadDto obtenerKpiUsuariosActividad(OffsetDateTime desde, OffsetDateTime hasta) {
        Number totalUsuarios = (Number) em.createNativeQuery("select count(*) from usuarios")
                .getSingleResult();

        Number usuariosActivos = (Number) em.createNativeQuery(
                        "select count(*) from usuarios where upper(estado) = 'ACTIVO'")
                .getSingleResult();

        Number usuariosInactivos = (Number) em.createNativeQuery(
                        "select count(*) from usuarios where upper(estado) <> 'ACTIVO'")
                .getSingleResult();

        Number eventosAuditoria = (Number) em.createNativeQuery(
                        "select count(*) from auditoria_eventos where fecha between :desde and :hasta")
                .setParameter("desde", desde)
                .setParameter("hasta", hasta)
                .getSingleResult();

        Number eventosExitosos = (Number) em.createNativeQuery(
                        "select count(*) from auditoria_eventos where fecha between :desde and :hasta and exito = true")
                .setParameter("desde", desde)
                .setParameter("hasta", hasta)
                .getSingleResult();

        Number eventosFallidos = (Number) em.createNativeQuery(
                        "select count(*) from auditoria_eventos where fecha between :desde and :hasta and exito = false")
                .setParameter("desde", desde)
                .setParameter("hasta", hasta)
                .getSingleResult();

        return KpiUsuariosActividadDto.builder()
                .fechaDesde(desde)
                .fechaHasta(hasta)
                .totalUsuarios(totalUsuarios.longValue())
                .usuariosActivos(usuariosActivos.longValue())
                .usuariosInactivos(usuariosInactivos.longValue())
                .eventosAuditoria(eventosAuditoria.longValue())
                .eventosExitosos(eventosExitosos.longValue())
                .eventosFallidos(eventosFallidos.longValue())
                .build();
    }
}