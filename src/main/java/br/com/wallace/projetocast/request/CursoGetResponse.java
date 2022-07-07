package br.com.wallace.projetocast.request;

import java.time.LocalDate;

import br.com.wallace.projetocast.entities.Categoria;

//inf banco pra tela
public class CursoGetResponse {

	private Integer idCurso;
	private String descricao;
	private LocalDate dataIni;
	private LocalDate dataTer;
	private Integer quantidade;
	private Categoria categoria;

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

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
