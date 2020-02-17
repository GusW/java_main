package br.unibh.escola.visao;

import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import br.unibh.escola.entidades.Aluno;
import br.unibh.escola.negocio.ServicoAluno;

@ManagedBean(name = "alunomb")
@ViewScoped
public class ControleAluno {
	@Inject
	private Logger log;

	@Inject
	private ServicoAluno sa;

	private Aluno aluno;
	private String nomeArg;
	private Long id;
	private List<Aluno> alunos;

	public Aluno getAluno() {
		return aluno;
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

	public List<Aluno> getalunos() {
		return alunos;
	}

	@PostConstruct
	public void inicializaLista() throws Exception {
		log.info("Executando o MB de Aluno");
		alunos = sa.findAll();
	}

	public void gravar() throws Exception{
		FacesMessage facesMsg;
		try {
			if (aluno.getId() == null) {
				aluno = sa.insert(aluno);
			} else {
				aluno = sa.update(aluno);
			}
		} catch (Exception e) {
			facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: "
					+ e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("messagePanel",
					facesMsg);
			return;
		}
		facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Aluno gravado com sucesso!", "");
		FacesContext.getCurrentInstance().addMessage("messagePanel", facesMsg);
	}

	public void pesquisar() throws Exception{
			alunos = sa.findByName(nomeArg);
	}

	public void novo() {
		aluno = new Aluno();
	}

	public void cancelar() {
		aluno = null;
	}

	public void editar() throws Exception{
		try {
			aluno = sa.find(id);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		aluno = null;
	}

	public void excluir() {
		try {
			sa.delete(sa.find(id));
			alunos = sa.findByName(nomeArg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		aluno = null;
	}
}
