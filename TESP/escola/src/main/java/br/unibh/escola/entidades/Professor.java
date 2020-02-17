package br.unibh.escola.entidades;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_PROFESSOR")
@NamedQueries({ @NamedQuery(name="Professor.findByName", query = "select a from Professor a where a.nome like :nome")
})
public class Professor extends Pessoa{
	
	@NotNull
	@DecimalMin(value = "500.00")
	@DecimalMax(value = "5000.00")
	@Column(nullable=false)
	private BigDecimal salario;
	
	@Transient
	public static Double BONUS = 0.1D;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="professor")
	private List<Disciplina> disciplinas;
	
	public Professor() {
	}
	
	public Professor(Long id, String nome, String cpf, BigDecimal salario) {
		super(id, nome, cpf);
		this.salario = salario;
	}

	public Professor(Long id, String nome, String cpf) {
		super(id, nome, cpf);
	}
	
	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	@Override
	public String toString() {
		return super.toString() +
		"Professor [salario=" + salario + "]";
	}

	public String toNome() {
		return super.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((salario == null) ? 0 : salario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professor other = (Professor) obj;
		if (salario == null) {
			if (other.salario != null)
				return false;
		} else if (!salario.equals(other.salario))
			return false;
		return true;
	}
	


}
