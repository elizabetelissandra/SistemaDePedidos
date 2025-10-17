package br.com.arnia.projetoarnia.sistema.dao;

import java.util.List;

import br.com.arnia.projetoarnia.sistema.bean.UsuarioRole;
import br.com.linkcom.neo.persistence.GenericDAO;

public class UsuarioRoleDAO extends GenericDAO<UsuarioRole> {

    public List<UsuarioRole> findByUsuario(Integer cdusuario) {
        return query()
            .where("usuarioRole.usuario.cdusuario = ?", cdusuario)
            .list();
    }
}
