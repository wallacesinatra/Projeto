package br.com.wallace.projetocast.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wallace.projetocast.entities.Categoria;
import br.com.wallace.projetocast.entities.Curso;
import br.com.wallace.projetocast.repository.CursoRepository;
import br.com.wallace.projetocast.request.CursoPostRequest;

@Service
public class CursoService {

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

		List<Curso> list = repository.findByDataIniLessThanEqualAndDataTerGreaterThanEqual(dataIni, dataTer);

		if (list.size() > 0) {
			throw new RuntimeException("Existe(m) curso(s) planejados(s) dentro do período informado");
		}

	}

	public List<Curso> busca() {
		return repository.findAll();
	}

	public Optional<Curso> buscaId(Integer idcurso) {

		Optional<Curso> list = repository.findById(idcurso);
		if (list.isEmpty()) {
			throw new RuntimeException("Id inexistente");
		}
		return list;
	}

	public List<Curso> buscaDescricao(String descricao) {

		List<Curso> listCurso = repository.findByDescricao(descricao);

		if (listCurso.isEmpty()) {
			throw new RuntimeException("Descricao nao existe");
		} else {
			for (Curso curso : repository.findByDescricao(descricao)) {

				listCurso.add(curso);
			}
		}

		return listCurso;

	}

	public List<Curso> buscaPeriodo(LocalDate dataIni, LocalDate dataTer) {

		List<Curso> listCurso = repository.findByDataIniBetween(dataIni, dataTer);

		for (Curso curso : repository.findByDataIniBetween(dataIni, dataTer)) {
			listCurso.add(curso);
		}

		return listCurso;

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
		repository.save(curso);
	}

	public void validaId(Integer idcurso) {

		Optional<Curso> list = repository.findById(idcurso);
		if (list.isEmpty()) {
			throw new RuntimeException("Curso nao existe");
		}

	}

}
