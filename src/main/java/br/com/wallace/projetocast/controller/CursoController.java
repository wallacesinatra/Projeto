package br.com.wallace.projetocast.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.wallace.projetocast.entities.Curso;
import br.com.wallace.projetocast.request.CursoPostRequest;
import br.com.wallace.projetocast.service.CursoService;

@Controller
@RequestMapping("api/curso")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CursoController {

	@Autowired
	CursoService service;

	// inserir dados

	@PostMapping
	public ResponseEntity<String> inserir(@RequestBody CursoPostRequest request) {
		System.out.println(request.getDescricao());
		System.out.println(request.getCategoria());
		try {
			service.cadastra(request);
			return ResponseEntity.ok().body("Dados gravados com sucesso");
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
		}
	}

	// listar dados

	@GetMapping
	public ResponseEntity<List<Curso>> listar(@RequestParam(required = false) String descricao,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataIni,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataTer) {
		List<Curso> curso = service.busca(descricao, dataIni, dataTer);
		return ResponseEntity.ok().body(curso);
	}

	// buscar id

	@GetMapping("/{idcurso}")
	public ResponseEntity<?> listarId(@PathVariable("idcurso") Integer idcurso) {
		try {
			Optional<Curso> curso = service.buscaId(idcurso);
			return ResponseEntity.ok().body(curso);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body("Erro: " + e.getMessage());
		}

	}

	// metodo deletar

	@DeleteMapping("/{idcurso}")
	public ResponseEntity<String> deletar(@PathVariable("idcurso") Integer idcurso) {
		try {
			service.deletar(idcurso);
			return ResponseEntity.status(HttpStatus.OK).body("Curso excluido");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
		}

	}

	// metodo atualizar

	@PutMapping
	public ResponseEntity<String> atualizar(@RequestBody Curso curso) {
		try {

			service.alterar(curso);

			return ResponseEntity.status(HttpStatus.OK).body("Atualizado");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
		}

	}

}
