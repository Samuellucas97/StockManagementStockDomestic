package service;

import service.raqueamento.IRankingUsuarioStrategy;
import java.util.List;

import dao.MoradorJpaController;
import exception.DAOException;
import exception.ServiceException;
import java.util.ArrayList;
import java.util.Iterator;
import model.tiposUsuario.Morador;
import model.Usuario;
import service.validacao.ValidacaoMorador;

/**
 * Classe que representa as funcionalidades desempenhadas pelo morador
 * @see Morador
 * @see Usuario
 */
public class MoradorService extends UsuarioService{

    /// ATRIBUTOS ********************************************************************************
    
    MoradorService(){
        this.usuarioDAO = MoradorJpaController.getInstance();
        this.validacaoUsuario = new ValidacaoMorador();
    }
    
    /// MÉTODOS **********************************************************************************

    @Override
    public String adicionar(Usuario usuario) throws ServiceException{

        int cont = 3;
        while (cont > 0){
            --cont;
            try {
                validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), true );
                Morador morador = (Morador) usuario;
                usuarioDAO.adicionar(morador);
                break;
            } catch (DAOException ex) {
                if( cont == 0)
                    throw new ServiceException( "Operação não concluida!" );
            }
        }
        
        return "OK";
    }

	@Override
	public String alterar(Usuario usuario, Usuario usuarioAlterado) throws ServiceException {

            int cont = 3;
            while (cont > 0){
                --cont;
                try {
                    validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), false );  /// Verifica se existe e se houve problemna na passagem de informacao
                    validacaoUsuario.validacao(usuarioAlterado, usuarioDAO.consultarTodos(), false );   /// Verifica se existe e se houve problemna na passagem de informacao                   
                    
                    //validar os atributos segundo as regras de negocio
                    
                    Morador moradorAlterado = (Morador) usuarioAlterado;
                    usuarioDAO.alterar(moradorAlterado);
                    break;
                } catch (DAOException ex) {
                    if( cont == 0)
                        throw new ServiceException( "Operação não concluida!" );
                }
            }
        
        return "OK";
	}

	@Override
	public String remover(Usuario usuario) throws ServiceException{

            int cont = 3;
            
            if (!usuario.getClass().equals(Morador.class)) 
                throw new ServiceException("Tipo de usuário inválido!");
            
            Morador morador = (Morador) usuario;
            
                try {
                    validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), true );
                    throw new ServiceException("Usuario não existente!");
                
                } catch(ServiceException ex){
                    if(ex.getMessage().equals("Usuario existente!")){
                        while (cont > 0){
                            cont--;
                            try {                                
                                usuarioDAO.remover(morador);
                                break;
                            } catch (DAOException ex1) {
                                if(cont == 0)
                                    throw new ServiceException( "Operação não concluida!" );
                            }
                        }
                    }
                    else{
                        throw new ServiceException(ex.getMessage());
                    }                
                }

            return "OK";
	}

	@Override
	public String consultar(Usuario usuario) throws ServiceException{

            String mensagem = "";
            
            if (!usuario.getClass().equals(Morador.class)) 
                throw new ServiceException("Tipo de usuário inválido!");
            
            try {
                validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), true );
            } catch(ServiceException ex){
                if(ex.getMessage().equals("Usuario existente!") ){
                    Morador moradorResultado = (Morador) usuarioDAO.consultar(usuario.getLogin());
                    if(moradorResultado != null)
                       return moradorResultado.toString();
                    else
                        mensagem = "Operacao invalida!";
                }
                else
                    mensagem = "Morador não existe!";
            }
	    throw new ServiceException(mensagem);
	
        }

	@Override
	public List<String> consultarTodos() throws ServiceException {
            
            List<Morador> moradores = new ArrayList<>();
            List<Usuario> usuarios = usuarioDAO.consultarTodos();
            
            if(usuarios.isEmpty()) 
                throw new ServiceException("Não há moradores!");
            
            for(Usuario usuario: usuarios){
                moradores.add((Morador) usuario);
            }
            
            List<String> retornos = new ArrayList<>();
            
            for(Morador morador: moradores){
                retornos.add(morador.toString());
            }
            
            return retornos;
            
	}

	@Override
	public List<String> consultaEspecifica(List<String> params, List<String> keys) throws ServiceException {
            boolean entrou = false;
            List<String> moradoresDados = this.consultarTodos();
            List<String> moradoresResultado = new ArrayList<>();
            List<String> teste = new ArrayList<>();
            
            
            for (Iterator<String> itParam = params.iterator(), itKey = keys.iterator(); 
                    itParam.hasNext() && itKey.hasNext();) {
                String param = itParam.next();
                String key = itKey.next();
                for(String morador: moradoresDados){
                    String atributos[] = morador.split(".");
                    for(String atributoValor : atributos){
                        if(atributoValor.matches(param)){
                            if(atributoValor.matches(key)){
                                teste.add(morador);
                                break;
                            }
                        }
                    }    
                }
                
                if(!entrou && moradoresResultado.isEmpty()){
                    entrou = true;
                    moradoresResultado.addAll(teste);
                }
                else 
                    moradoresResultado.retainAll(teste);
               
                teste.clear();
            }
            
            return moradoresResultado;
            
	}
        
    @Override
    public List<String> ranquear(IRankingUsuarioStrategy rankingUsuario) {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public String verNotificacao(Usuario usuario) throws ServiceException {
        return usuario.getNoficacao();
    }

    @Override
    public void notificar(String notificacao, Usuario usuario) throws ServiceException {
        usuario.notificar(notificacao);
    }
   
}
