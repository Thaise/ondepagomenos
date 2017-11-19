package br.edu.usj.ondepagomenos.webfilter;

import java.io.IOException;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.usj.ondepagomenos.bean.LoginBean;
import br.edu.usj.ondepagomenos.entidades.Perfil;

@WebFilter(urlPatterns = {"/admin/usuario/*"})
public class AdministradorFilter implements Filter {

	@Inject
	private LoginBean loginBean;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (loginBean.getUsuarioLogado() != null && loginBean.getUsuarioLogado().getPerfil().equals(Perfil.ADMINISTRADOR)) {
			chain.doFilter(request, response);
		} else {
			HttpServletResponse res = (HttpServletResponse) response;
			HttpServletRequest req = (HttpServletRequest) request;
			res.sendRedirect(req.getContextPath() + "/admin/admin.jsf");
		}
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
