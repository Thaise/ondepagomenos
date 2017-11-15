package br.edu.usj.ondepagomenos.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.NoResultException;

import br.edu.usj.ondepagomenos.dao.Perfil;
import br.edu.usj.ondepagomenos.dao.Usuario;
import br.edu.usj.ondepagomenos.model.UsuarioModel;
import br.edu.usj.ondepagomenos.util.MD5Utils;
import br.edu.usj.ondepagomenos.util.MensagemUtils;

@SessionScoped
@Named
public class LoginBean implements Serializable {

	private Usuario usuario;
	@EJB
	private UsuarioModel usuarioModel;
	private static boolean estaLogado = false;
	private Usuario usuarioLogado;

	@PostConstruct
	public void init() {
		usuario = new Usuario();
	}

	public String logaUsuario() {
		try {
			usuario.setSenha(MD5Utils.converteMD5(usuario.getSenha()));
			usuarioLogado = usuarioModel.buscaUsuario(usuario);
			return "/admin/admin.jsf";
		} catch (Exception e) {
			if (e.getCause() instanceof NoResultException) {
				MensagemUtils.mostraMensagem("Usuário não encontrado",true);
			} else {
				MensagemUtils.mostraMensagem("Erro ao verificar usuário", true);
			}
		}

		return null;
	}

	public String sair() {
		setUsuario(new Usuario());
		setUsuarioLogado(new Usuario());
		estaLogado = false;
		return cancelar();

	}

	public String cancelar() {
		setUsuario(new Usuario());
		return "/index.jsf";
	}

	public boolean isEhAdministrador() {
		return usuarioLogado != null ? usuarioLogado.getPerfil().equals(Perfil.ADMINISTRADOR) : false;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public static boolean isEstaLogado() {
		return estaLogado;
	}

	public static void setEstaLogado(boolean estaLogado) {
		LoginBean.estaLogado = estaLogado;
	}

}
