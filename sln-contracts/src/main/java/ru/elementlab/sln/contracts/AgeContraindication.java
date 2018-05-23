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
 * Описание противопоказания по возрастному признаку
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgeContraindication extends Contraindication {

    /**
     * Нижняя граница возраста
     */
    @JsonProperty("AgeLow")
    public Duration ageLow;

    /**
     * Верхняя граница возраста
     */
    @JsonProperty("AgeHigh")
    public Duration ageHigh;


    public Duration getAgeLow() {
        return ageLow;
    }

    public void setAgeLow(Duration ageLow) {
        this.ageLow = ageLow;
    }

    public Duration getAgeHigh() {
        return ageHigh;
    }

    public void setAgeHigh(Duration ageHigh) {
        this.ageHigh = ageHigh;
    }
}
