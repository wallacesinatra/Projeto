package br.com.wallace.projetocast.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wallace.projetocast.entities.Categoria;
import br.com.wallace.projetocast.entities.Curso;
import br.com.wallace.projetocast.repository.CursoRepository;
import br.com.wallace.projetocast.request.CursoPostRequest;

@Service
public class CursoService {

	@PersistenceContext
	EntityManager entity;

	@Autowired
	CursoRepository repository;

	public void cadastra(CursoPostRequest request) {

		Curso curso = new Curso();
		Categoria categoria = new Categoria(request.getCategoria());

		curso.setDescricao(request.getDescricao());
		curso.setDataIni(LocalDate.parse(request.getDataIni()));
		curso.setDataTer(LocalDate.parse(request.getDataTer()));
		curso.setQuantidade(Integer.parseInt(request.getQuantidade()));
		curso.setCategoria(categoria);

		regraValidaCurso(curso);
		regraCadastro(curso);
		regraCadastroData(curso.getDataIni(), curso.getDataTer());

		repository.save(curso);
	}

	public void regraCadastro(Curso curso) {
		if (curso.getDataIni().isBefore(LocalDate.now())) {
			throw new RuntimeException("Inicio curso menor que data de hoje");
		}
	}

	public void regraCadastroData(LocalDate dataIni, LocalDate dataTer) {

		Integer busca = repository.busca(dataIni, dataTer);

		if (busca > 0) {
			throw new RuntimeException("Existe(m) curso(s) planejados(s) dentro do período informado.");
		}

	}

	public void regraValidaCurso(Curso curso) {

		for (Curso aux : repository.findAll()) {
			if (aux.getDescricao().equals(curso.getDescricao())) {
				throw new RuntimeException("Curso Existente");
			}
		}

	}

	public List<Curso> busca(String descricao, LocalDate dataIni, LocalDate dataTer) {

		CriteriaBuilder criteria = entity.getCriteriaBuilder();
		CriteriaQuery<Curso> criteriaQuery = criteria.createQuery(Curso.class);

		Root<Curso> curso = criteriaQuery.from(Curso.class);
		List<Predicate> predList = new ArrayList<>();

		if (descricao != "") {
			Predicate descricaoPredicate = criteria.equal(curso.get("descricao"), descricao);
			predList.add(descricaoPredicate);
		}

		if (dataIni != null) {
			Predicate dataIniPredicate = criteria.greaterThanOrEqualTo(curso.get("dataIni"), dataIni);
			predList.add(dataIniPredicate);
		}

		if (dataTer != null) {
			Predicate dataTerPredicate = criteria.lessThanOrEqualTo(curso.get("dataTer"), dataTer);
			predList.add(dataTerPredicate);
		}

		Predicate[] predicateArray = new Predicate[predList.size()];

		predList.toArray(predicateArray);

		criteriaQuery.where(predicateArray);

		TypedQuery<Curso> query = entity.createQuery(criteriaQuery);

		return query.getResultList();
	}

	public Optional<Curso> buscaId(Integer idcurso) {

		Optional<Curso> list = repository.findById(idcurso);
		if (list.isEmpty()) {
			throw new RuntimeException("Id inexistente");
		}
		return list;
	}

	public void deletar(Integer idcurso) {
		validaId(idcurso);
		regraDelete(repository.findById(idcurso));
		repository.deleteById(idcurso);

	}

	public void regraDelete(Optional<Curso> curso) {
		Curso aux = curso.get();
		if (aux.getDataTer().isBefore(LocalDate.now())) {
			throw new RuntimeException("Curso não pode ser deletado, pois ja foi finalizado");
		}

	}

	public void alterar(Curso curso) {
		validaId(curso.getIdCurso());
		regraCadastro(curso);
		repository.consultaDatasEditar(curso.getDataIni(), curso.getDataTer(), curso.getIdCurso());
		repository.save(curso);
	}

	public void validaId(Integer idcurso) {

		Optional<Curso> list = repository.findById(idcurso);
		if (list.isEmpty()) {
			throw new RuntimeException("Curso nao existe");
		}

	}

}
