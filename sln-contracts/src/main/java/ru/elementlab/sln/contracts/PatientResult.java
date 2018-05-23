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
 * Описывает результат скрининга, имеющий адаптированные (пациентские) предупреждение и реферат.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientResult extends ProfessionalResult {

    /**
     *  Текст предупреждения для пациента.
     *  <remarks>
     *      Этот текст может отсутствовать для некоторых результатов скрининга.
     *  </remarks>
     */
    @JsonProperty("PatientAlert")
    public String patientAlert;

    /**
     * Текст реферата для пациента, в формате XML.
     * <remarks>
     *     Этот текст может отсутствовать для некоторых результатов скрининга.
     *  </remarks>
     */
    @JsonProperty("PatientMonograph")
    public String patientMonograph;

    public String getPatientAlert() {
        return patientAlert;
    }

    public void setPatientAlert(String patientAlert) {
        this.patientAlert = patientAlert;
    }

    public String getPatientMonograph() {
        return patientMonograph;
    }

    public void setPatientMonograph(String patientMonograph) {
        this.patientMonograph = patientMonograph;
    }
}
