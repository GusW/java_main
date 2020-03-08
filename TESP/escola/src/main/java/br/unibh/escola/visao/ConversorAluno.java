package br.unibh.escola.visao;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.naming.InitialContext;

import br.unibh.escola.entidades.Aluno;
import br.unibh.escola.negocio.ServicoAluno;


public class ConversorAluno implements Converter {
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component,
			String value) {
		try {
			ServicoAluno ss = (ServicoAluno) new InitialContext()
					.lookup("java:global/escola/ServicoAluno");
			return ss.find(new Long(value));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component,
			Object value) {
		if (value != null) {
			Aluno s = (Aluno) value;
			if (s.getId() != null)
				return s.getId().toString();
		}
		return null;
	}
}