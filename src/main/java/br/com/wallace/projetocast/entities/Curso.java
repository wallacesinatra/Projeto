package br.com.wallace.projetocast.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cursos")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcurso")
	private Integer idCurso;

	@Column(name = "descricao", nullable = false)
	private String descricao;

	@Column(name = "data_inicio", nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataIni;

	@Column(name = "data_termino", nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataTer;

	@Column(name = "quantidade")
	private Integer quantidade;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "fk_categoria")
	private Categoria categoria;

	public Curso() {
		// TODO Auto-generated constructor stub
	}

	public Curso(Integer idCurso, String descricao, LocalDate dataIni, LocalDate dataTer, Integer quantidade,
			Categoria categoria) {
		super();
		this.idCurso = idCurso;
		this.descricao = descricao;
		this.dataIni = dataIni;
		this.dataTer = dataTer;
		this.quantidade = quantidade;
		this.categoria = categoria;
	}

	public Integer getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataIni() {
		return dataIni;
	}

	public void setDataIni(LocalDate dataIni) {
		this.dataIni = dataIni;
	}

	public LocalDate getDataTer() {
		return dataTer;
	}

	public void setDataTer(LocalDate dataTer) {
		this.dataTer = dataTer;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
