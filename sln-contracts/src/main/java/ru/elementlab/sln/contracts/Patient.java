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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Описывает общую информацию о пациенте.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Patient {

    /**
     * Дата рождения.
     * <remarks>
     *      Значение не обязательно, но может требоваться при выполнении некоторых видов скрининга
     * </remarks>
     */
    @JsonProperty("BirthDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date birthDate;

    /**
     * Ожидаемая дата рождения (не обязательно).
     */
    @JsonProperty("ExpectedBirthDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date expectedBirthDate;

    /**
     * Значение не обязательно, но может требоваться при выполнении некоторых видов скрининга
     */
    @JsonProperty("Gender")
    public Gender gender;

    /**
     * Вид спорта, которым занимается пациент.
     */
    @JsonProperty("Sport")
    public PatientSport sport;

    /**
     * Масса тела. Значение указывается в килограммах.
     * <remarks>
     *     Значение не обязательно, но может требоваться при выполнении некоторых видов скрининга
     * </remarks>
     */
    @JsonProperty("Weight")
    public BigDecimal weight;

    /**
     * Площадь поверхности тела. Значение в квадратных метрах.
     */
    @JsonProperty("BodySurfaceArea")
    public BigDecimal bodySurfaceArea;

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getExpectedBirthDate() {
        return expectedBirthDate;
    }

    public void setExpectedBirthDate(Date expectedBirthDate) {
        this.expectedBirthDate = expectedBirthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public PatientSport getSport() {
        return sport;
    }

    public void setSport(PatientSport sport) {
        this.sport = sport;
    }

    public BigDecimal getBodySurfaceArea() {
        return bodySurfaceArea;
    }

    public void setBodySurfaceArea(BigDecimal bodySurfaceArea) {
        this.bodySurfaceArea = bodySurfaceArea;
    }
}
