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
 * Базовый тип, предоставляющий информацию, общую для всех концептов, имеющихся в системе.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Concept {

    /**
     * Тип концепта.
     */
    @JsonProperty("Type")
    public String type;

    /**
     * Код, идентифицирующий концепт.
     * <example>Например, для лекарственных средств идентификатором является уникальное целое число.
     * Для диагнозов из справочника МКБ-10 идентификатором является код, указанный в справочнике.</example>
     */
    @JsonProperty("Code")
    public String code;

    /**
     * Наименование концепта.
     */
    @JsonProperty("Name")
    public String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
