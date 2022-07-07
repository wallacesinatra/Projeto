package br.com.wallace.projetocast.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wallace.projetocast.entities.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer>{

		// Derived query
		List<Curso> findByDescricao(String descricao);
		
		// Derived query
		List<Curso>findByDataIniBetween(LocalDate dataIni, LocalDate dataTer);
		
		// Derived query
		//List<Curso> findByDataInicioBetween(LocalDate dataIni, LocalDate dataTer);
}
