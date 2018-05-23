/*
 *
 * Module Name:  sln-contracts
 * Project:      sln
 *
 * Copyright (c) Element Lab LLC
 *
 *  THIS CODE AND INFORMATION IS PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 *  EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A PARTICULAR PURPOSE.
 *
 */

package ru.elementlab.sln.contracts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Содержит данные, необходимые для идентификации и аутентификации пользователя сервиса.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientCredentials {

    /**
     * Идентификатор пользователя сервиса (логин)
     */
    @JsonProperty("ClientId")
    public String clientId;

    /**
     * Секретный ключ, соответствующий идентификатору пользователя (пароль)
     */
    @JsonProperty("ClientSecret")
    public String clientSecret;

    /**
     * Инициализирует экземпляр класса ClientCredentials
     *
     * @param clientId     Идентификатор пользователя сервиса (логин)
     * @param clientSecret Секретный ключ, соответствующий идентификатору пользователя (пароль)
     */
    public ClientCredentials(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

}