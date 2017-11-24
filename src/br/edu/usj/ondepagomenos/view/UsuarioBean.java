package br.edu.usj.ondepagomenos.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import br.edu.usj.ondepagomenos.entidades.Perfil;
import br.edu.usj.ondepagomenos.entidades.Usuario;
import br.edu.usj.ondepagomenos.model.AbstractModel;
import br.edu.usj.ondepagomenos.model.UsuarioModel;
import br.edu.usj.ondepagomenos.util.MD5Utils;
import br.edu.usj.ondepagomenos.util.MensagemUtils;

@ManagedBean
@ViewScoped
public class UsuarioBean extends AbstractBean<Usuario> {

	private Usuario usuario;
	private List<Perfil> perfis = new ArrayList<Perfil>(Arrays.asList(Perfil.values()));
	@EJB
	private UsuarioModel usuarioModel;
	@Inject
	private LoginBean loginBean;

	@PostConstruct
	public void init() {
		limpar();

		getIdAtualizacao();
	}

	public boolean isHabilitaInativacao() {
		return !loginBean.getUsuarioLogado().equals(usuario);
	}

	public String insereOuAtualiza() {
		try {
			if (usuario.getIdUsuario() == null) {
				if (!usuarioJaExiste()) {
					insere();
				} else {
					MensagemUtils.mostraMensagem("Já existe um usuário com o mesmo login e senha", true);
				}
			} else {
				atualiza();
			}
			return cancelar();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private boolean usuarioJaExiste() {
		try {
			Usuario novo = new Usuario();
			novo.setLogin(usuario.getLogin());
			novo.setSenha(MD5Utils.converteMD5(usuario.getSenha()));
			return usuarioModel.buscaUsuario(novo) != null;
		} catch (Exception e) {
			if (e.getCause() instanceof NoResultException) {
				System.out.println("Usuário liberado para cadastro");
			} else {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public void limpar() {
		usuario = new Usuario();
	}

	public String inativar() {
		return mudarStatus(1);
	}

	public String ativar() {
		return mudarStatus(0);
	}

	public String mudarStatus(int status) {
		try {
			usuario.setFlExcluido(status);
			usuarioModel.atualiza(usuario);
			MensagemUtils.mostraMensagem("Usuário " + (status == 1 ? "ativado" : "inativado") + " com sucesso", false);
			return cancelar();
		} catch (Exception e) {
			MensagemUtils.mostraMensagem("Erro ao alterar Usuário", true);
		}
		return null;
	}

	@Override
	public String cancelar() {
		return "/admin/usuario/pesquisa_usuario.jsf";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	@Override
	protected AbstractModel<Usuario> getModel() {
		return usuarioModel;
	}

	@Override
	protected Usuario getEntidade() {
		return usuario;
	}

	@Override
	protected void setEntidade(Usuario entidade) {
		setUsuario(entidade);
	}

}
