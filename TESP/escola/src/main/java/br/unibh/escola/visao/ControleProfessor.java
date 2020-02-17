package br.unibh.escola.visao;

import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import br.unibh.escola.entidades.Professor;
import br.unibh.escola.negocio.ServicoProfessor;

@ManagedBean(name = "professormb")
@ViewScoped
public class ControleProfessor {
	@Inject
	private Logger log;

	@Inject
	private ServicoProfessor sa;
	
	private Professor professor;
	private String nomeArg;
	private Long id;
	private List<Professor> professores;

	public Professor getProfessor() {
		return professor;
	}

	public String getNomeArg() {
		return nomeArg;
	}

	public void setNomeArg(String nomeArg) {
		this.nomeArg = nomeArg;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Professor> getprofessores() {
		return professores;
	}

	@PostConstruct
	public void inicializaLista() {
		log.info("Executando o MB de Professor");
		try {
			professores = sa.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void gravar() {
		FacesMessage facesMsg;
		try {
			if (professor.getId() == null) {
				professor = sa.insert(professor);
			} else {
				professor = sa.update(professor);
			}
		} catch (Exception e) {
			facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: "
					+ e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("messagePanel",
					facesMsg);
			return;
		}
		facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Professor gravado com sucesso!", "");
		FacesContext.getCurrentInstance().addMessage("messagePanel", facesMsg);
	}

	public void pesquisar() {
		try {
			professores = sa.findByName(nomeArg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void novo() {
		professor = new Professor();
	}

	public void cancelar() {
		professor = null;
	}

	public void editar() {
		try {
			professor = sa.find(id);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		professor = null;
	}

	public void excluir() {
		try {
			sa.delete(sa.find(id));
			professores = sa.findByName(nomeArg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		professor = null;
	}
}
