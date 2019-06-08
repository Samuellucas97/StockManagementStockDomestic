package model.notification;

/**
 * Interface de notificação de observadores
 */
public interface INotificacaoObserver {

    /**
     * Adiciona a fila de notificações
     * @param notificacao Mensagem a ser notificada
     */
    public void notificar(String notificacao);

}
