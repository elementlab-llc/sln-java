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
 * Дополнительные параметры скрининга анти-допинга
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DopingAlertsOptions {

    /**
     *  true, если при скрининге не нужно учитывать указанный пол пациента
     */
    @JsonProperty("IgnoreGender")

    public boolean ignoreGender;
    /**
     * true, если при скрининге не нужно учитывать указанный соревновательный период
     */
    @JsonProperty("IgnoreCompetitionPeriod")
    public boolean ignoreCompetitionPeriod;


    public boolean isIgnoreGender() {
        return ignoreGender;
    }

    public void setIgnoreGender(boolean ignoreGender) {
        this.ignoreGender = ignoreGender;
    }

    public boolean isIgnoreCompetitionPeriod() {
        return ignoreCompetitionPeriod;
    }

    public void setIgnoreCompetitionPeriod(boolean ignoreCompetitionPeriod) {
        this.ignoreCompetitionPeriod = ignoreCompetitionPeriod;
    }
}