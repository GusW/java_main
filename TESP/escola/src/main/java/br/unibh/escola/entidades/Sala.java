package br.unibh.escola.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name = "TB_SALA")
@NamedQueries({ @NamedQuery(name = "Sala.findByCapacidade", query = "select a from Sala a where a.capacidade >= :capacidade") })
public class Sala {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@NotNull
	@NotBlank
	@Column(nullable=false)
	@Size(max = 10)
	private String codigo;

	@NotNull
	@Column(nullable=false)
	private int capacidade;

	private boolean possuiQuadroBranco;

	private boolean possuiDataShow;

	private boolean possuiComputador;

	@Size(max = 255)
	private String observacao;

	private int status;

	@Temporal(TemporalType.DATE)
	private Date dataTerminoManutencao;
	
	private String possui;
	private int dia;
	private int mes;
	private int ano;

	@OneToMany(fetch=FetchType.EAGER, mappedBy="sala")
	private List<Disciplina> disciplinas;
	
	private int versao;
	
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public Sala() {
	}

	public Sala(Long id, String codigo, int capacidade,
			boolean possuiQuadroBranco, boolean possuiDataShow,
			boolean possuiComputador, String observacao, int status,
			Date dataTerminoManutencao) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.capacidade = capacidade;
		this.possuiQuadroBranco = possuiQuadroBranco;
		this.possuiDataShow = possuiDataShow;
		this.possuiComputador = possuiComputador;
		this.observacao = observacao;
		this.status = status;
		this.dataTerminoManutencao = dataTerminoManutencao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public boolean isPossuiQuadroBranco() {
		return possuiQuadroBranco;
	}
	
	public void setPossuiQuadroBranco(boolean possuiQuadroBranco) {
		this.possuiQuadroBranco = possuiQuadroBranco;
	}

	public String quadroToString(boolean possuiQuadroBranco) {
		if (isPossuiQuadroBranco()){
			possui = "sim";
		} else {
			possui = "nao";
		}
		return possui;
	}
	
	public boolean isPossuiDataShow() {
		return possuiDataShow;
	}

	public void setPossuiDataShow(boolean possuiDataShow) {
		this.possuiDataShow = possuiDataShow;
	}

	public String dataToString(boolean possuiDataShow) {
		if (isPossuiDataShow()){
			possui = "sim";
		} else {
			possui = "nao";
		}
		return possui;
	}
	
	public boolean isPossuiComputador() {
		return possuiComputador;
	}

	public void setPossuiComputador(boolean possuiComputador) {
		this.possuiComputador = possuiComputador;
	}

	public String pcToString(boolean possuiComputador) {
		if (isPossuiComputador()){
			possui = "sim";
		} else {
			possui = "nao";
		}
		return possui;
	}
	
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String statusToString(int status) {
		if (getStatus() == 1){
			possui = "Ativo";
		} else if (getStatus() == 2){
			possui = "Em Manutencao";
		} else {
			possui = "Desativado";
		}
		return possui;
	}
	
	public Date getDataTerminoManutencao() {
		return dataTerminoManutencao;
	}

	public void setDataTerminoManutencao(Date dataTerminoManutencao) {
		this.dataTerminoManutencao = dataTerminoManutencao;
	}

	@SuppressWarnings("deprecation")
	public String terminoToString(Date dataTerminoManutencao) {
		if (getDataTerminoManutencao() == null){
			return null;
		}
		dia = getDataTerminoManutencao().getDate();
		mes = getDataTerminoManutencao().getMonth();
		ano = getDataTerminoManutencao().getYear() + 1900;
		return dia + "/" + mes + "/" + ano;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		result = prime * result + capacidade;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime
				* result
				+ ((dataTerminoManutencao == null) ? 0 : dataTerminoManutencao
						.hashCode());
		result = prime * result + dia;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + mes;
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + ((possui == null) ? 0 : possui.hashCode());
		result = prime * result + (possuiComputador ? 1231 : 1237);
		result = prime * result + (possuiDataShow ? 1231 : 1237);
		result = prime * result + (possuiQuadroBranco ? 1231 : 1237);
		result = prime * result + status;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sala other = (Sala) obj;
		if (ano != other.ano)
			return false;
		if (capacidade != other.capacidade)
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (dataTerminoManutencao == null) {
			if (other.dataTerminoManutencao != null)
				return false;
		} else if (!dataTerminoManutencao.equals(other.dataTerminoManutencao))
			return false;
		if (dia != other.dia)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mes != other.mes)
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (possui == null) {
			if (other.possui != null)
				return false;
		} else if (!possui.equals(other.possui))
			return false;
		if (possuiComputador != other.possuiComputador)
			return false;
		if (possuiDataShow != other.possuiDataShow)
			return false;
		if (possuiQuadroBranco != other.possuiQuadroBranco)
			return false;
		if (status != other.status)
			return false;
		return true;
	}	
}
