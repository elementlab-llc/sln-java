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

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class ProfessionalResult {

    /**
     * Код результата скрининга
     */
    @JsonProperty("Code")
    public String code ;

    /**
     *  Текст предупреждения
     */
    @JsonProperty("Alert")
    public String alert;

    /**
     * Текст реферата для профессиональных медиков, в формате XML.
     */
    @JsonProperty("ProfessionalMonograph")
    public String professionalMonograph;

    /**
     * Список лекарственных средств, относящихся к данному результату скрининга.
     */
    @JsonProperty("Drugs")
    public List<Drug> drugs;

    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getProfessionalMonograph() {
        return professionalMonograph;
    }

    public void setProfessionalMonograph(String professionalMonograph) {
        this.professionalMonograph = professionalMonograph;
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }
}