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
 * Сообщение.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {

    /**
     * Код сообщения.
     */
    @JsonProperty("Code")
    public String сode;

    /**
     * Тип сообщения.
     */
    @JsonProperty("Kind")
    public MessageKind kind;

    /**
     * Текст сообщения.
     */
    @JsonProperty("Text")
    public String text;

    /**
     * Данные, связанные с этим сообщением.
     */
    @JsonProperty("Data")
    public Object data;

    public String getСode() {
        return сode;
    }

    public void setСode(String сode) {
        this.сode = сode;
    }

    public MessageKind getKind() {
        return kind;
    }

    public void setKind(MessageKind kind) {
        this.kind = kind;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
