package br.com.wallace.projetocast.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wallace.projetocast.entities.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

	// Derived query
	List<Curso> findByDescricao(String descricao);

	// Derived query
	List<Curso> findByDataIniBetween(LocalDate dataIni, LocalDate dataTer);

	// Derived query
	List<Curso> findByDataIniLessThanEqualAndDataTerGreaterThanEqual(LocalDate dataIni, LocalDate dataTer);

//	@Query(value = "select * from curso.curso where dataIni between :dataini and :datater"
//			+ "or (dataTer) between :dataini and :datater"
//			+ "or (dataIni <= :dataini and dataTer >= :datater)"
//			+ "or (dataIni) >= :dataini and dataTer <= :datater")
//	List<Curso> datas(@Param("dataIni") LocalDate dataIni, @Param("dataTer") LocalDate dataTer);

}
