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
 * Описывает вид спорта, которым занимается пациент, с возможностью уточнения периода соревнований.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientSport {

    /**
     * Код вида спорта.
     */
    @JsonProperty("Code")
    public String code;

    /**
     * Наименование вида спорта.
     */
    @JsonProperty("Name")
    public String name;

    /**
     * Соревновательный период.
     * <remarks>
     *      Значение по умолчанию Unspecified
     * </remarks>
     */
    @JsonProperty("Period")
    public CompetitionPeriod period;

    /**
     * Код концепта RoleInSports, описывающий роль пациента в спорте (Спортсмен, Тренер и т.п.)
     */
    @JsonProperty("Role")
    public String role;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompetitionPeriod getPeriod() {
        return period;
    }

    public void setPeriod(CompetitionPeriod period) {
        this.period = period;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
