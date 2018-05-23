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
 * Описание противопоказания
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Contraindication extends ProfessionalResult {


    /**
     * Уровень риска, связанный с противопоказанием.
     */
    @JsonProperty("Severity")
    public ContraindicationSeverityLevel severity;


    /**
     * Состояние, для которого имеется противопоказание.
     */
    @JsonProperty("Condition")
    public Condition condition;


    public ContraindicationSeverityLevel getSeverity() {
        return severity;
    }

    public void setSeverity(ContraindicationSeverityLevel severity) {
        this.severity = severity;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        condition = condition;
    }
}