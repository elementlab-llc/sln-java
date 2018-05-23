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

import java.util.Collection;

/**
 * Допинг-контроль
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DopingAlert extends ProfessionalResult {

    /**
     * Код класса предупреждения.
     */
    @JsonProperty("AlertClass")
    public String alertClass;
    /**
     * Тип предупреждения.
     */
    @JsonProperty("Kind")
    public String kind;
    /**
     * Уровень риска.
     */
    @JsonProperty("Severity")
    public DopingAlertSeverityLevel severity;
    /**
     * Субстанция, вызывающая положительный результат при допинг-контроле.
     */
    @JsonProperty("Substance")
    public Concept substance;
    /**
     * Список видов спорта, для которых запрещено использование субстанции и содержащих её препаратов.
     */
    @JsonProperty("Sports")
    public Collection<Concept> sports;
    /**
     * true, если использование субстанции разрешено во внесоревновательный период.
     */
    @JsonProperty("AllowedOutOfCompetition")
    public boolean allowedOutOfCompetition;
    /**
     * true, если использование субстанции разрешено женщинам.
     */
    @JsonProperty("AllowedForFemales")
    public boolean allowedForFemales;
    /**
     * Описание раздела списка ВАДА
     */
    @JsonProperty("ListSection")
    public WadaListReference listSection;

    public String getAlertClass() {
        return alertClass;
    }

    public void setAlertClass(String alertClass) {
        this.alertClass = alertClass;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public DopingAlertSeverityLevel getSeverity() {
        return severity;
    }

    public void setSeverity(DopingAlertSeverityLevel severity) {
        this.severity = severity;
    }

    public Concept getSubstance() {
        return substance;
    }

    public void setSubstance(Concept substance) {
        this.substance = substance;
    }

    public Collection<Concept> getSports() {
        return sports;
    }

    public void setSports(Collection<Concept> sports) {
        this.sports = sports;
    }

    public boolean isAllowedOutOfCompetition() {
        return allowedOutOfCompetition;
    }

    public void setAllowedOutOfCompetition(boolean allowedOutOfCompetition) {
        this.allowedOutOfCompetition = allowedOutOfCompetition;
    }

    public boolean isAllowedForFemales() {
        return allowedForFemales;
    }

    public void setAllowedForFemales(boolean allowedForFemales) {
        this.allowedForFemales = allowedForFemales;
    }

    public WadaListReference getListSection() {
        return listSection;
    }

    public void setListSection(WadaListReference listSection) {
        this.listSection = listSection;
    }
}
