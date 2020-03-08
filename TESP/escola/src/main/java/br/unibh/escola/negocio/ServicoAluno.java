package br.unibh.escola.negocio;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import br.unibh.escola.entidades.Aluno;

@Stateless
@LocalBean
public class ServicoAluno implements DAO<Aluno, Long> {
	@Inject
	EntityManager em;
	@Inject
	private Logger log;

	@Override
	public Aluno insert(Aluno t) throws Exception {
		log.info("Persistindo " + t);
		em.persist(t);
		return t;
	}

	@Override
	public Aluno update(Aluno t) throws Exception {
		log.info("Atualizando " + t);
		return em.merge(t);
	}

	@Override
	public void delete(Aluno t) throws Exception {
		log.info("Removendo " + t);
		Object c = em.merge(t);
		em.remove(c);
	}

	@Override
	public Aluno find(Long k) throws Exception {
		log.info("Encontrando aluno " + k);
		return em.find(Aluno.class, k);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> findAll() throws Exception {
		log.info("Encontrando todos os alunos");
		return em.createQuery("from Aluno").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> findByName(String name) throws Exception {
		log.info("Encontrando alunos " + name);
		return em.createNamedQuery("Aluno.findByName")
				.setParameter("nome", name + "%").getResultList();
	}
}