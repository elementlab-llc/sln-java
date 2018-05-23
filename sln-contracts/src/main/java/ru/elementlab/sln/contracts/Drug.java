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
 * Описывает лекарственное средство. Тип концептов - DispensableDrug.
 * <br/>
 * <br/>
 * Также допустимо указывать коды ЛС в соответствии с внешними справочниками:
 * <table class="help-page-table" style="width:auto">
 * <thead>
 * <tr>
 * <th>Значение поля Type</th>
 * <th>Значение поля Code</th>
 * </tr></thead>
 * <tbody>
 * <tr>
 * <td>urn:rlsnet:nomen</td>
 * <td>Идентификатор из таблицы NOMEN базы данных РЛС (Россия)</td>
 * </tr>
 * <tr>
 * <td>urn:slovenia:cbz</td>
 * <td>Код препарата из Centralna baza zdravil (Словения)</td>
 * </tr>
 * </tbody>
 * </table>
 * </summary>
 * <summary lang="en">
 * This concept identifies an input drug (DispensableDrug), substance or dietary supplement.
 * <br/>
 * <br/>
 * It is also possible to specify the drug codes in accordance with external directories:
 * <table class="help-page-table" style="width:auto">
 * <thead>
 * <tr>
 * <th>Type</th>
 * <th>Code</th>
 * </tr></thead>
 * <tbody>
 * <tr>
 * <td>urn:rlsnet:nomen</td>
 * <td>ID field value from table NOMEN in RLS database (Russia)</td>
 * </tr>
 * <tr>
 * <td>urn:slovenia:cbz</td>
 * <td>Drug code from Centralna baza zdravil (Slovenia)</td>
 * </tr>
 * </tbody>
 * </table>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Drug extends ScreenableConcept {

    /**
     * Схема приема лекарственного средства. Необязательный параметр, может быть null.
     */
    @JsonProperty("Schedule")
    public AdministrationSchedule schedule;

    public AdministrationSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(AdministrationSchedule schedule) {
        this.schedule = schedule;
    }
}
