/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import java.util.Queue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/// CLASSES PRÓPRIAS
import model.notification.INotificacaoObserver;
import model.notification.INotificacaoSubject;
import exception.ServiceException;

/**
 * Representa um usuário abstrato
 * @see INotificacaoObserver
 * @see INotificacaoSubject
 * @see ServiceException
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Usuario implements Serializable, INotificacaoObserver, INotificacaoSubject {

    /// ATRIBUTOS ********************************************************************************
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    protected Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    private StatusSM status;

    @Column(nullable = false)
    private int quantidadeDeMovimentacoes;

    @Column(nullable = true)
    private String causa;

    @OneToMany
    private List<INotificacaoObserver> observerList;

    @OneToMany
    private Queue<String> notificacoes;

    @Column(nullable = true)
    private int quantidadeTentativasIncorretasDeAcesso;
    
    /// CONSTRUTOR *******************************************************************************
    
    public Usuario(){ /** vazio **/ }
    
    /// GETTERS E SETTERS ************************************************************************
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public StatusSM getStatus() {
        return status;
    }

    public void setStatus(StatusSM statusSM) {
        this.status = statusSM;
    }

    public int getQuantidadeDeMovimentacoes() {
        return quantidadeDeMovimentacoes;
    }

    public void setQuantidadeDeMovimentacoes(int quantidadeDeMovimentacoes) {
        this.quantidadeDeMovimentacoes = quantidadeDeMovimentacoes;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public List<INotificacaoObserver> getObserverList() {
        return observerList;
    }

    public Queue<String> getNotificacoes() {
        return notificacoes;
    }

    public int getQuantidadeTentativasIncorretasDeAcesso() {
        return quantidadeTentativasIncorretasDeAcesso;
    }

    public void setQuantidadeTentativasIncorretasDeAcesso(int quantidadeTentativasIncorretasDeAcesso) {
        this.quantidadeTentativasIncorretasDeAcesso = quantidadeTentativasIncorretasDeAcesso;
    }

    public String getNoficacao() throws ServiceException{
        if(notificacoes.isEmpty()) throw new ServiceException("Não há notificações cadastradas!");
            return notificacoes.remove();
    }
    
    /// MÉTODOS **********************************************************************************
    
    /**
     * Trata-se, basicamente de um método toString() porém que deve-se obrigatoriamente ser implementado 
     * @return  String contendo todos os atributos
     */
    protected abstract String ImplementYourToString(); 

    @Override
    public String toString(){
        String myObjectInString = "";

        myObjectInString+= " " +  this.causa.toString();
        myObjectInString+= " " + this.login.toString();
        myObjectInString+= " " + this.nome.toString();
        myObjectInString+= " " + this.senha.toString();
        myObjectInString+= " " + String.valueOf(this.id);
        myObjectInString+= " " + this.notificacoes.toString();
        myObjectInString+= " " + this.observerList.toString();
        myObjectInString+= " " + String.valueOf(this.quantidadeDeMovimentacoes);
        myObjectInString+= " " + String.valueOf(this.quantidadeTentativasIncorretasDeAcesso);
        myObjectInString+= " " + this.status.toString();

        myObjectInString += ImplementYourToString();
        return myObjectInString;
    }	

    /**
     * Adiciona uma notificação à fila de notificações do usuário
     * @param notificacao   Conteúdo da notificação
     */
    @Override
    public void notificar(String notificacao) {
        notificacoes.add(notificacao);
    }

    /**
     * Registra um observador do usuário
     * @param  obs  Observador a ser registrado
     */
    @Override
    public void registrarObserver(INotificacaoObserver obs) {
        observerList.add(obs);
    }

    /**
     * Desregistra um observador do usuário
     * @param obs 
     */
    @Override
    public void desregistrarObserver(INotificacaoObserver obs) {
        observerList.remove(obs);
    }

    
    /**
     * Notifica todos usuários com um mensagem
     * @param notificacao Mensagem a ser notificada
     */
    @Override
    public void notificarObservers(String notificacao) {

        for( INotificacaoObserver i: observerList )
            i.notificar(notificacao);
    }
	
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
}
