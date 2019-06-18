package service.raqueamento;

import java.util.List;

/// CLASSE PRÓPRIA
import model.Usuario;

/**
 * Interface de algoritmos de ranqueamento de usuários
 * @see Usuario
 * @see List
 */
public interface IRankingUsuarioStrategy {

    /**
     * Ordena lista de usuarios
     * @param usuarios Lista de usuários a serem ordenados
     * @return  Lista de usuários reordenada 
     */
    public List<Usuario> ranquear(List<Usuario> usuarios);

}
