package br.com.arnia.projetoarnia.sistema.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import br.com.arnia.projetoarnia.sistema.bean.Usuario;
import br.com.arnia.projetoarnia.sistema.bean.UsuarioRole;
import br.com.linkcom.neo.authorization.AuthorizationDAO;
import br.com.linkcom.neo.authorization.Permission;
import br.com.linkcom.neo.authorization.Role;
import br.com.linkcom.neo.authorization.User;

public class AuthenticationDAOImpl implements AuthorizationDAO {
	
	private UsuarioDAO usuarioDAO;
	private UsuarioRoleDAO usuarioRoleDAO;
	
	
	public AuthenticationDAOImpl() {
		
	}
	
	public AuthenticationDAOImpl(UsuarioDAO usuarioDAO, UsuarioRoleDAO usuarioRoleDAO) {
        this.usuarioDAO = usuarioDAO;
        this.usuarioRoleDAO = usuarioRoleDAO;
    }
	
	
	
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	public void setUsuarioRoleDAO(UsuarioRoleDAO usuarioRoleDAO) {
		this.usuarioRoleDAO = usuarioRoleDAO;
	}

	@Override
	public User findUserByLogin(String login) {
		return usuarioDAO.findByLogin(login);
	}

	@Override
	public Role[] findAllRoles() {
		return new Role[0];
	}

	@Override
	public Permission findPermission(Role arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role[] findUserRoles(User user) {
	    Usuario usuario = (Usuario) user;
	    List<UsuarioRole> associacoes = usuarioRoleDAO.findByUsuario(usuario.getCdUsuario());
	    List<Role> roles = new ArrayList<Role>();
	    for (UsuarioRole ur : associacoes) {
	        roles.add(ur.getPapel());
	    }
	    return roles.toArray(new Role[0]);
	}

	@Override
	public Permission savePermission(String arg0, Role arg1, Map<String, String> arg2) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
