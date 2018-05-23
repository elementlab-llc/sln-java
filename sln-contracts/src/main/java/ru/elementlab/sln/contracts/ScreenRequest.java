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

/**
 * Данные запроса на выполнение скрининга
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScreenRequest {

    private static final String _screeningTypes = ScreeningType.getValues();

    /**
     * Виды скринингов, которые необходимо выполнить
     */
    @JsonProperty("ScreeningTypes")
    public String screeningTypes;

    /**
     * Общая информация о пациенте.
     */
    @JsonProperty("Patient")
    public Patient patient;

    /**
     * Перечень назначенных лекарственных средств
     */
    @JsonProperty("Drugs")
    public List<Drug> drugs;

    /**
     * Перечень известных аллергий, имеющихся у пациента.
     */
    @JsonProperty("Allergies")
    public List<Allergy> allergies;

    /**
     * Список заболеваний, имеющихся у пациента.
     */
    @JsonProperty("Diseases")
    public List<Disease> diseases;

    /**
     * Параметры для более тонкой настройки процесса выполнения скрининга.
     */
    @JsonProperty("Options")
    public ScreeningOptions options;


    public String getScreeningTypes() {
        return screeningTypes == null ? _screeningTypes : screeningTypes;
    }

    public void setScreeningTypes(String screeningTypes) {
        this.screeningTypes = screeningTypes;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }

    public List<Allergy> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<Allergy> allergies) {
        this.allergies = allergies;
    }

    public List<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }

    public ScreeningOptions getOptions() {
        return options;
    }

    public void setOptions(ScreeningOptions options) {
        this.options = options;
    }
}
