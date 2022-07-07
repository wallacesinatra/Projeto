package br.com.wallace.projetocast.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.wallace.projetocast.entities.Curso;
import br.com.wallace.projetocast.repository.CursoRepository;
import br.com.wallace.projetocast.request.CursoGetResponse;
import br.com.wallace.projetocast.request.CursoPostRequest;
import br.com.wallace.projetocast.request.CursoPutRequest;

@Controller
@RequestMapping("api/curso")
public class CursoController {

	@Autowired
	CursoRepository repository;

	// inserir dados

	@PostMapping
	public ResponseEntity<String> inserir(@RequestBody CursoPostRequest request) {
		try {

			Curso curso = new Curso();

			if (request.getDataIni().isBefore(LocalDate.now())) {
				return ResponseEntity.status(HttpStatus.OK).body("Data final menor que data de inicio");
			} else {

				curso.setDescricao(request.getDescricao());
				curso.setDataIni(request.getDataIni());
				curso.setDataTer(request.getDataTer());
				curso.setQuantidade(request.getQuantidade());
				curso.setCategoria(request.getCategoria());

				repository.save(curso);
			}

			return ResponseEntity.status(HttpStatus.OK).body("Curso cadastrado");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro" + e.getMessage());
		}

	}

	// listar dados

	@GetMapping
	public ResponseEntity<List<CursoGetResponse>> listar() {

		List<CursoGetResponse> response = new ArrayList<>();

		for (Curso curso : repository.findAll()) {

			CursoGetResponse get = new CursoGetResponse();

			get.setIdCurso(curso.getIdCurso());
			get.setDescricao(curso.getDescricao());
			get.setDataIni(curso.getDataIni());
			get.setDataTer(curso.getDataTer());
			get.setQuantidade(curso.getQuantidade());
			get.setCategoria(curso.getCategoria());

			response.add(get);
		}

		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	// buscar id

	@GetMapping("/{idcurso}")
	public ResponseEntity<CursoGetResponse> listarId(@PathVariable("idcurso") Integer idcurso) {

		Optional<Curso> list = repository.findById(idcurso);

		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			CursoGetResponse get = new CursoGetResponse();

			Curso curso = list.get();

			get.setIdCurso(curso.getIdCurso());
			get.setDescricao(curso.getDescricao());
			get.setDataIni(curso.getDataIni());
			get.setDataTer(curso.getDataTer());
			get.setQuantidade(curso.getQuantidade());
			get.setCategoria(curso.getCategoria());

			return ResponseEntity.status(HttpStatus.OK).body(get);

		}

	}

	// buscar por descricao

	@GetMapping("/descricao")
	public ResponseEntity<List<CursoGetResponse>> listDescricao(String descricao) {

		List<CursoGetResponse> response = new ArrayList<>();

		for (Curso curso : repository.findByDescricao(descricao)) {

			CursoGetResponse get = new CursoGetResponse();

			get.setIdCurso(curso.getIdCurso());
			get.setDescricao(curso.getDescricao());
			get.setDataIni(curso.getDataIni());
			get.setDataTer(curso.getDataTer());
			get.setQuantidade(curso.getQuantidade());
			get.setCategoria(curso.getCategoria());

			response.add(get);
		}

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	// buscar por periodo

	@GetMapping(value = "/periodo/{dataIni}/{dataTer}")
	public ResponseEntity<List<CursoGetResponse>> listarPeriodo(
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataIni,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataTer) {

		List<CursoGetResponse> response = new ArrayList<>();

		for (Curso curso : repository.findByDataIniBetween(dataIni, dataTer)) {

			CursoGetResponse get = new CursoGetResponse();

			get.setIdCurso(curso.getIdCurso());
			get.setDescricao(curso.getDescricao());
			get.setDataIni(curso.getDataIni());
			get.setDataTer(curso.getDataTer());
			get.setQuantidade(curso.getQuantidade());
			get.setCategoria(curso.getCategoria());

			response.add(get);
		}

		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	// metodo deletar

	@DeleteMapping("/{idcurso}")
	public ResponseEntity<String> deletar(@PathVariable("idcurso") Integer idcurso) {
		try {

			Optional<Curso> list = repository.findById(idcurso);

			if (list.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Curso não encontrado");
			} else {

				Curso curso = list.get();
				repository.delete(curso);

				return ResponseEntity.status(HttpStatus.OK).body("Curso excluido");
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro :" + e.getMessage());
		}

	}

	// metodo atualizar

	@PutMapping
	public ResponseEntity<String> atualizar(@RequestBody CursoPutRequest request) {
		try {

			Optional<Curso> list = repository.findById(request.getIdCurso());

			if (list.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Curso não encontrado");
			} else {
				Curso curso = list.get();

				curso.setDescricao(request.getDescricao());
				curso.setDataIni(request.getDataIni());
				curso.setDataTer(request.getDataTer());
				curso.setQuantidade(request.getQuantidade());
				curso.setCategoria(request.getCategoria());

				repository.save(curso);

				return ResponseEntity.status(HttpStatus.OK).body("Atualizado");
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro :" + e.getMessage());
		}

	}

}
