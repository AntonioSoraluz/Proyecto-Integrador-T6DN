package com.creceperu.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.creceperu.app.model.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura,Integer>{
	@Query(value = "SELECT id_factura FROM Factura ORDER BY id_factura DESC LIMIT 1", nativeQuery = true)
    String getLastGeneratedCode();
	List<Factura> findByRuc(String ruc);
	@Query("SELECT f.id_factura, DATE_FORMAT(f.fecharegistro, '%Y-%m-%d %H:%i:%s'), DATE_FORMAT(f.fechavencimiento, '%Y-%m-%d %H:%i:%s'), f.monto FROM Factura f WHERE f.estado='Listada' and f.ruc = :ruc")
    List<Object[]> findFacturaDataByRuc(@Param("ruc") String ruc);
}
