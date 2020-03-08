package br.unibh.escola.visao;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unibh.escola.entidades.Aluno;
import br.unibh.escola.entidades.Disciplina;
import br.unibh.escola.entidades.Professor;
import br.unibh.escola.entidades.Sala;
import br.unibh.escola.negocio.ServicoAluno;
import br.unibh.escola.negocio.ServicoDisciplina;
import br.unibh.escola.negocio.ServicoProfessor;
import br.unibh.escola.negocio.ServicoSala;

@ManagedBean(name = "disciplinamb")
@ViewScoped
public class ControleDisciplina {
	@Inject
	private Logger log;
	@Inject
	EntityManager em;
	@Inject
	private ServicoDisciplina sa;
	@Inject
	private ServicoSala ss;
	@Inject
	private ServicoAluno sl;
	@Inject
	private ServicoProfessor sp;

	private Disciplina disciplina;
	private String disciplinaNome;
	private String cursoNome;
	private Long id;
	private List<Disciplina> disciplinas;

	public List<Professor> getProfessores() {
		try {
			return sp.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void setProfessores(List<Professor> professores) {
	}

	public List<Sala> getSalas() {
		try {
			return ss.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Aluno> getAlunos() {
		try {
			return sl.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public String getDisciplinaNome() {
		return disciplinaNome;
	}

	public void setDisciplinaNome(String disciplinaNome) {
		this.disciplinaNome = disciplinaNome;
	}

	public String getCursoNome() {
		return cursoNome;
	}

	public void setCursoNome(String cursoNome) {
		this.cursoNome = cursoNome;
	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setSalas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	@PostConstruct
	public void inicializaLista() {
		log.info("Executando o MB de Disciplina");
		try {
			disciplinas = sa.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void gravar() {
		FacesMessage facesMsg;
		try {
			if (disciplina.getId() == null) {
				disciplina = sa.insert(disciplina);
			} else {
				disciplina = sa.update(disciplina);
			}
		} catch (Exception e) {
			facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: "
					+ e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("messagePanel",
					facesMsg);
			return;
		}
		facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Disciplina gravada com sucesso!", "");
		FacesContext.getCurrentInstance().addMessage("messagePanel", facesMsg);
	}

	public void pesquisar() {
		try {
			disciplinas = sa.findByNomeECurso(disciplinaNome, cursoNome);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void novo() {
		disciplina = new Disciplina();

	}

	public void cancelar() {
		disciplina = null;
	}

	public void editar() {
		try {
			disciplina = sa.find(id);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		disciplina = null;
	}

	public void excluir() {
		try {
			sa.delete(sa.find(id));
			disciplinas = sa.findByNomeECurso(disciplinaNome, cursoNome);
		} catch (Exception e) {
			e.printStackTrace();
		}
		disciplina = null;
	}

	public Disciplina find(long k) throws Exception {
		log.info("encontrando disciplina " + k);
		Query query = em
				.createQuery("select d from Disciplina d join fetch d.alunos where d.id = :id");
		query.setParameter("id", k);
		return (Disciplina) query.getSingleResult();
	}

}