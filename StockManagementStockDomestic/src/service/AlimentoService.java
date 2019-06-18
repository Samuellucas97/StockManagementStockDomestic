/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

/// CLASSES PROPRIAS
import dao.AlimentoJpaController;
import exception.DAOException;
import exception.ServiceException;
import java.util.ArrayList;
import java.util.Iterator;
import model.Material;
import service.raqueamento.IRankingMaterialStrategy;
import service.validacao.ValidacaoAlimento;
import model.tiposMaterial.Alimento;

/**
 * Representa a operações que ocorre sobre um alimento
 * @see MaterialService
 */
public class AlimentoService extends MaterialService{

    /// CONSTRUTOR *******************************************************************************
    
    public AlimentoService(){
        this.materialDAO = AlimentoJpaController.getInstance();
        this.validacaoMaterial = new ValidacaoAlimento();
    }
    
    /// MÉTODOS **********************************************************************************
    
    @Override
    public String adicionar(Material material) throws ServiceException {
    
        int cont = 3;
        while (cont > 0){
            --cont;
            try {
                validacaoMaterial.validacao(material);
                Alimento alimento = (Alimento) material;
                materialDAO.adicionar(alimento);
                break;
            } catch (DAOException ex) {
                if( cont == 0)
                    throw new ServiceException( "Operação não concluida!" );
            }
        }
        
        return "OK";
    }

    @Override
    public String alterar(Material material, Material materialAlterado) throws ServiceException {

        int cont = 3;
        while (cont > 0){
            --cont;
            try {
                validacaoMaterial.validacao(material);  /// Verifica se existe e se houve problemna na passagem de informacao
                validacaoMaterial.validacao(materialAlterado);   /// Verifica se existe e se houve problemna na passagem de informacao                   
                Alimento alimentoAlterado = (Alimento) materialAlterado;
                materialDAO.alterar(alimentoAlterado);
                break;
            } catch (DAOException ex) {
                if( cont == 0)
                    throw new ServiceException( "Operação não concluida!" );
            }
        }
        
        return "OK";
    }

    @Override
    public String remover(Material material) throws ServiceException {

        int cont = 3;

        if (!material.getClass().equals(Alimento.class)) 
            throw new ServiceException("Tipo de material inválido!");

        Alimento alimento = (Alimento) material;

        try {
            validacaoMaterial.validacao(material);
            throw new ServiceException("Material não existente!");
        } 
        catch(ServiceException ex){
            if(ex.getMessage().equals("Material existente!")){
                while (cont > 0){
                    cont--;
                    try {                                
                        materialDAO.remover(alimento);
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
    public String consultar(Material material) throws ServiceException {
        
        String mensagem = "";

        if (!material.getClass().equals(Alimento.class)) 
            throw new ServiceException("Tipo de usuário inválido!");

        try {
            validacaoMaterial.validacao(material);
        } catch(ServiceException ex){
            if(ex.getMessage().equals("Usuario existente!") ){
                Alimento alimentoResultado;
                alimentoResultado = (Alimento) materialDAO.consultar(material.getId());
                if(alimentoResultado != null)
                   return alimentoResultado.toString();
                else
                    mensagem = "Operacao invalida!";
            }
            else
                mensagem = "Alimento não existe!";
        }
        throw new ServiceException(mensagem);

    }

    @Override
    public List<String> consultarTodos() throws ServiceException {
            List<Alimento> alimentos = new ArrayList<>();
            List<Material> materials = materialDAO.consultarTodos();
            
            if(materials.isEmpty()) 
                throw new ServiceException("Não há alimentos!");
            
            for(Material material: materials){
                alimentos.add((Alimento) material);
            }
            
            List<String> retornos = new ArrayList<>();
            
            for(Alimento alimento: alimentos){
                retornos.add(alimento.toString());
            }
            
            return retornos;
    
    }

    @Override
    public List<String> consultaEspecifica(List<String> params, List<String> keys) throws ServiceException {
        
        boolean entrou = false;
        List<String> alimentosDados = this.consultarTodos();
        List<String> alimentosResultado = new ArrayList<>();
        List<String> teste = new ArrayList<>();


        for (Iterator<String> itParam = params.iterator(), itKey = keys.iterator(); 
                itParam.hasNext() && itKey.hasNext();) {
            String param = itParam.next();
            String key = itKey.next();
            for(String alimento: alimentosDados){
                String atributos[] = alimento.split(".");
                for(String atributoValor : atributos){
                    if(atributoValor.matches(param)){
                        if(atributoValor.matches(key)){
                            teste.add(alimento);
                            break;
                        }
                    }
                }    
            }

            if(!entrou && alimentosResultado.isEmpty()){
                entrou = true;
                alimentosResultado.addAll(teste);
            }
            else 
                alimentosResultado.retainAll(teste);

            teste.clear();
        }

        return alimentosResultado;

    }

    @Override
    public List<String> ranquear(IRankingMaterialStrategy rankingMaterial) throws ServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String validacao(Material material) throws ServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
