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
 * Предупреждение, связанное с фармакогенетическим тестированием.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneticTest extends ProfessionalResult {

    /**
     * Уровень риска.
     */
    @JsonProperty("Severity")
    public GeneticTestSeverityLevel severity;

    /**
     * Степень необходимого вмешательства.
     */
    @JsonProperty("Management")
    public CodedValueWithLevel management;

    /**
     * Степень изученности.
     */
    @JsonProperty("Documentation")
    public CodedValueWithLevel documentation;


    public GeneticTestSeverityLevel getSeverity() {
        return severity;
    }

    public void setSeverity(GeneticTestSeverityLevel severity) {
        this.severity = severity;
    }

    public CodedValueWithLevel getManagement() {
        return management;
    }

    public void setManagement(CodedValueWithLevel management) {
        this.management = management;
    }

    public CodedValueWithLevel getDocumentation() {
        return documentation;
    }

    public void setDocumentation(CodedValueWithLevel documentation) {
        this.documentation = documentation;
    }
}