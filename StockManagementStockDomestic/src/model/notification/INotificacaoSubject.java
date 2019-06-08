package model.notification;

/**
 * Interface de notificação de sujeitos
 */
public interface INotificacaoSubject {
    
    /**
     * Registra observador
     * @param obs   Observador a ser registrado   
     */
    public void registrarObserver(INotificacaoObserver obs);

    /**
     * Desregistra observador
     * @param obs   Observador a ser desregistrado   
     */
    public void desregistrarObserver(INotificacaoObserver obs);

    /**
     * Notifica a todos os observadores
     * @param notificacao 
     */
    public void notificarObservers(String notificacao);

}
