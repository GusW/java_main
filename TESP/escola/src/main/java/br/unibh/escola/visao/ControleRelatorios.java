package br.unibh.escola.visao;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@ManagedBean(name = "relatoriosmb")
@ViewScoped
public class ControleRelatorios {
	private Connection conexao = null;

	public void geraRelatorio(String relatorio, String opcao) {
		URL arquivo = getClass().getResource(
				"/relatorios/" + relatorio + ".jasper");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) facesContext
				.getExternalContext().getResponse();
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(arquivo);
			Context initContext = new InitialContext();
			DataSource ds = (javax.sql.DataSource) initContext
					.lookup("java:jboss/datasources/escola");
			conexao = (java.sql.Connection) ds.getConnection();
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, map, conexao);
			byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
			if (opcao.equals("inline")) {
				response.setHeader("Content-Disposition", "inline; filename="
						+ relatorio + ".pdf");
			} else {
				response.setHeader("Content-Disposition",
						"attachment; filename=" + relatorio + ".pdf");
			}
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
			facesContext.responseComplete();
			facesContext.renderResponse();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conexao != null)
				try {
					conexao.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			conexao = null;
		}
	}
}