package br.edu.usj.ondepagomenos.view.relatorio;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.usj.ondepagomenos.dao.Usuario;
import br.edu.usj.ondepagomenos.model.UsuarioModel;
import br.edu.usj.ondepagomenos.util.MensagemUtils;

@ViewScoped
@ManagedBean
public class UsuarioReportBean implements Serializable {

	private String nome;
	private int flInativo = 0;
	private List<Usuario> usuarios;
	@EJB
	private UsuarioModel usuarioModel;
	private Usuario paraInativar;

	@PostConstruct
	public void init() {
		buscaUsuario();
	}

	public String atualiza(Integer id) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("cadastro_usuario.jsf?id=" + id);
		return null;
	}

	public void buscaUsuario() {
		try {
			usuarios = usuarioModel.buscaUsuarios(nome, flInativo);
		} catch (Exception e) {
			MensagemUtils.mostraMensagem("Erro ao realizar pesquisa", true);
		}
	}

	public void selecionaParaInativar(Usuario c) {
		setParaInativar(c);
	}

	public void inativar() {
		try {
			if(paraInativar.getFlExcluido() == 0){
				paraInativar.setFlExcluido(1);
			}else{
				paraInativar.setFlExcluido(0);
			}
			usuarioModel.atualiza(paraInativar);
			buscaUsuario();
			MensagemUtils.mostraMensagem("Usuario excluida com sucesso", false);
		} catch (Exception e) {
			MensagemUtils.mostraMensagem("Erro ao excluir Usuario", true);
		}
	}

	public void limpar() {
		nome = null;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> categorias) {
		this.usuarios = categorias;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getFlInativo() {
		return flInativo;
	}

	public void setFlInativo(int flInativo) {
		this.flInativo = flInativo;
	}

	public void setParaInativar(Usuario paraInativar) {
		this.paraInativar = paraInativar;
	}

	public Usuario getParaInativar() {
		return paraInativar;
	}

}
