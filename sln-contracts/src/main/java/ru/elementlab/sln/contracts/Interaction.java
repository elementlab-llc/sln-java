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
 * Описывает взаимодействие между лекарственными средствами, а также их взаимодействие с пищей и алкоголем.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Interaction extends PatientResult {

    /**
     * Уровень риска.
     */
    @JsonProperty("Severity")
    public InteractionSeverityLevel severity;

    /**
     * Степень необходимого вмешательства при возникновении взаимодействия.
     */
    @JsonProperty("Management")
    public InteractionManagementLevel management;

    /**
     * Степень изученности данного взаимодействия.
     */
    @JsonProperty("Documentation")
    public InteractionDocumentationLevel documentation;

    /**
     * Уровень противопоказания в соответствии с инструкцией.
     */
    @JsonProperty("LabeledAvoidance")
    public CodedValueWithLevel labeledAvoidance;

    /**
     * Скорость возникновения взаимодействия.
     */
    @JsonProperty("Onset")
    public InteractionOnset onset;


    public InteractionSeverityLevel getSeverity() {
        return severity;
    }

    public void setSeverity(InteractionSeverityLevel severity) {
        this.severity = severity;
    }

    public InteractionManagementLevel getManagement() {
        return management;
    }

    public void setManagement(InteractionManagementLevel management) {
        this.management = management;
    }

    public InteractionDocumentationLevel getDocumentation() {
        return documentation;
    }

    public void setDocumentation(InteractionDocumentationLevel documentation) {
        this.documentation = documentation;
    }

    public CodedValueWithLevel getLabeledAvoidance() {
        return labeledAvoidance;
    }

    public void setLabeledAvoidance(CodedValueWithLevel labeledAvoidance) {
        this.labeledAvoidance = labeledAvoidance;
    }

    public InteractionOnset getOnset() {
        return onset;
    }

    public void setOnset(InteractionOnset onset) {
        this.onset = onset;
    }
}