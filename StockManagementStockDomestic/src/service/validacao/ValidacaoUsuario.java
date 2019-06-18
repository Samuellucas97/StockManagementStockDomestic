package service.validacao;

import exception.ServiceException;
import java.util.List;
import model.StatusSM;
import model.Usuario;
import model.notification.INotificacaoObserver;

/**
 * Representa uma valicao de usuario
 */
public abstract class ValidacaoUsuario {

    /**
     * Método responsável por válidar todos os atributos da classe usuário!
     * @param usuario Usuário que irá passar pela validação.  
     * @param usuarios Lista de usuários do sistema que irão ser utilizados na
     * checagem de informações do usuario verificado.
     * @return Retorna uma String que representa a situação da validação. 
     * @throws ServiceException Dá suporte a indicação de problemas relacionados
     * a validação.
     */
    public String validacao(Usuario usuario, List<Usuario> usuarios, boolean checarLogin) throws ServiceException{

        verificacaoCaracterLogin(usuario.getLogin(), usuarios, checarLogin);            

        if(5 <= usuario.getSenha().length() && 
                usuario.getSenha().length() <= 12 &&
                !usuario.getSenha().matches("[a-zA-Z[0-9]]")){
            throw new 
            ServiceException("Senha inválida!");
        }

        if(3 <= usuario.getNome().length() &&
                usuario.getNome().substring(0, 1).matches("[ \\t\\n\\x0B\\f\\r]") &&
                !usuario.getNome().matches("[a-zA-Z[0-9][ \\t\\n\\x0B\\f\\r]]")){
            throw new 
            ServiceException("Nome inválido!");
        }


        if(usuario.getQuantidadeTentativasIncorretasDeAcesso() < 0 ||
           usuario.getQuantidadeTentativasIncorretasDeAcesso() > 2){
            throw new 
            ServiceException("Quantidade de tentativas incorretas "
                    + "de acesso inválida!");
        }


        if(usuario.getId() < 0){
            throw new ServiceException("Usuario criado com ID errado!");
        }


        if(usuario.getQuantidadeDeMovimentacoes() < 0){
            throw new ServiceException("Quantidade de movimentações"
                    + " inválida!");
        }

        if((usuario.getStatus() == StatusSM.NaoBloqueado && 
                !usuario.getCausa().equals(""))
                ||
                (usuario.getStatus() != StatusSM.NaoBloqueado &&
                usuario.getCausa().equals(""))){
            throw new 
            ServiceException("Usuario possui status ou causa indevida!");
        }

        if(usuario.getNotificacoes()!= null && 
                !usuario.getNotificacoes().isEmpty()){
            for(String notificacao: usuario.getNotificacoes()){
                if(notificacao.length() <= 0 ||
                   !notificacao.contains("Notificação:"))
                    throw new 
                    ServiceException("Usuario possui notificações invalidas!");
            }
        }

        if(usuario.getObserverList()!= null &&
           !usuario.getObserverList().isEmpty()){
            for(INotificacaoObserver usuarioObserver : usuario.getObserverList()){
                if(usuarioObserver == null)
                    throw new 
                    ServiceException("Usuario possui observadores invalidos!");
            }
        }

        validacaoImplementacao(usuario);

        return "OK";
    }
        
    /**
     * Verifica os caracteres do login 
     * @param palavra   Palavra   
     * @param usuarios  Lista de usuario
     * @throws ServiceException 
     */
    private void verificacaoCaracterLogin(String palavra, List<Usuario> usuarios, boolean checarLogin) throws ServiceException{

        if(!palavra.matches("[a-zA-Z[0-9]]")){
            throw new 
            ServiceException("Login inválido!");
        }
        
        if( !checarLogin )
            return ; 
        
        for (Usuario usuario : usuarios) {
            if(palavra.matches(usuario.getLogin()))
                throw new 
                ServiceException("Usuario existente!");
        }

    }
    
    /**
     * Método responsável por validar todos os atributos da classe especilizada
     * de usuário!
     * @param usuario Usuário que irá passar pela validação.
     * @throws ServiceException Dá suporte a indicação de problemas relacionados
     * a validação.
     */
    protected abstract void validacaoImplementacao(Usuario usuario) throws ServiceException;
}
