/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

/// CLASSES PROPRIAS
import dao.UtensiliosDeCozinhaJpaController;
import exception.DAOException;
import exception.ServiceException;
import java.util.ArrayList;
import java.util.Iterator;
import model.Material;
import service.raqueamento.IRankingMaterialStrategy;
import service.validacao.ValidacaoUtensiliosDeCozinha;
import model.tiposMaterial.UtensiliosDeCozinha;

/**
 * Representa a operações que ocorre sobre um utensiliosDeCozinha
 * @see MaterialService
 */
public class UtensiliosDeCozinhaService extends MaterialService{

    /// CONSTRUTOR *******************************************************************************
    
    public UtensiliosDeCozinhaService(){
        this.materialDAO = UtensiliosDeCozinhaJpaController.getInstance();
        this.validacaoMaterial = new ValidacaoUtensiliosDeCozinha();
    }
    
    /// MÉTODOS **********************************************************************************
    
    @Override
    public String adicionar(Material material) throws ServiceException {
    
        int cont = 3;
        while (cont > 0){
            --cont;
            try {
                validacaoMaterial.validacao(material);
                UtensiliosDeCozinha utensiliosDeCozinha = (UtensiliosDeCozinha) material;
                materialDAO.adicionar(utensiliosDeCozinha);
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
                UtensiliosDeCozinha utensiliosDeCozinhaAlterado = (UtensiliosDeCozinha) materialAlterado;
                materialDAO.alterar(utensiliosDeCozinhaAlterado);
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

        if (!material.getClass().equals(UtensiliosDeCozinha.class)) 
            throw new ServiceException("Tipo de material inválido!");

        UtensiliosDeCozinha utensiliosDeCozinha = (UtensiliosDeCozinha) material;

        try {
            validacaoMaterial.validacao(material);
            throw new ServiceException("Material não existente!");
        } 
        catch(ServiceException ex){
            if(ex.getMessage().equals("Material existente!")){
                while (cont > 0){
                    cont--;
                    try {                                
                        materialDAO.remover(utensiliosDeCozinha);
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

        if (!material.getClass().equals(UtensiliosDeCozinha.class)) 
            throw new ServiceException("Tipo de usuário inválido!");

        try {
            validacaoMaterial.validacao(material);
        } catch(ServiceException ex){
            if(ex.getMessage().equals("Usuario existente!") ){
                UtensiliosDeCozinha utensiliosDeCozinhaResultado;
                utensiliosDeCozinhaResultado = (UtensiliosDeCozinha) materialDAO.consultar(material.getId());
                if(utensiliosDeCozinhaResultado != null)
                   return utensiliosDeCozinhaResultado.toString();
                else
                    mensagem = "Operacao invalida!";
            }
            else
                mensagem = "UtensiliosDeCozinha não existe!";
        }
        throw new ServiceException(mensagem);

    }

    @Override
    public List<String> consultarTodos() throws ServiceException {
            List<UtensiliosDeCozinha> utensiliosDeCozinhas = new ArrayList<>();
            List<Material> materials = materialDAO.consultarTodos();
            
            if(materials.isEmpty()) 
                throw new ServiceException("Não há utensiliosDeCozinhas!");
            
            for(Material material: materials){
                utensiliosDeCozinhas.add((UtensiliosDeCozinha) material);
            }
            
            List<String> retornos = new ArrayList<>();
            
            for(UtensiliosDeCozinha utensiliosDeCozinha: utensiliosDeCozinhas){
                retornos.add(utensiliosDeCozinha.toString());
            }
            
            return retornos;
    
    }

    @Override
    public List<String> consultaEspecifica(List<String> params, List<String> keys) throws ServiceException {
        
        boolean entrou = false;
        List<String> utensiliosDeCozinhasDados = this.consultarTodos();
        List<String> utensiliosDeCozinhasResultado = new ArrayList<>();
        List<String> teste = new ArrayList<>();


        for (Iterator<String> itParam = params.iterator(), itKey = keys.iterator(); 
                itParam.hasNext() && itKey.hasNext();) {
            String param = itParam.next();
            String key = itKey.next();
            for(String utensiliosDeCozinha: utensiliosDeCozinhasDados){
                String atributos[] = utensiliosDeCozinha.split(".");
                for(String atributoValor : atributos){
                    if(atributoValor.matches(param)){
                        if(atributoValor.matches(key)){
                            teste.add(utensiliosDeCozinha);
                            break;
                        }
                    }
                }    
            }

            if(!entrou && utensiliosDeCozinhasResultado.isEmpty()){
                entrou = true;
                utensiliosDeCozinhasResultado.addAll(teste);
            }
            else 
                utensiliosDeCozinhasResultado.retainAll(teste);

            teste.clear();
        }

        return utensiliosDeCozinhasResultado;

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
