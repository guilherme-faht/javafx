package br.com.faht.javafx.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by guilherme.faht on 14/03/2018.
 */
@AllArgsConstructor
public enum Reasons {

    NO_ACTION_DEFINED("Não foi definida uma ação para o componente"),
    INVALID_GENERATOR_EVENT("O gerador de eventos deve ser um Button ou um MenuItem");

    @Getter
    private String description;
}
