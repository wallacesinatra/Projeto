package br.com.wallace.projetocast.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.wallace.projetocast.entities.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

	@Query("select count(*) from Curso curso where (:dataIni <= curso.dataIni and :dataTer >= curso.dataIni)" + "or"
			+ "(:dataIni <= curso.dataTer and :dataTer >= curso.dataTer)" + "or"
			+ "(:dataIni >= curso.dataIni and :dataTer <= curso.dataTer)" + "or"
			+ "(:dataIni <= curso.dataIni and :dataTer >= curso.dataTer)")
	Integer busca(LocalDate dataIni, LocalDate dataTer);
	

	@Query(value = "select count(c.idCurso) from Curso c where (c.dataIni between :dataIni and :dataTer "
			+ "or (c.dataTer between :dataIni and :dataTer)" + "or (c.dataIni <= :dataIni and c.dataTer >= :dataTer)"
			+ "or (c.dataIni >= :dataIni and c.dataTer <= :dataTer))" + "and (c.idCurso != :idCurso)")
	public Integer consultaDatasEditar(@Param("dataIni") LocalDate dataIni, @Param("dataTer") LocalDate dataTer,
			@Param("idCurso") Integer idCurso);

}
