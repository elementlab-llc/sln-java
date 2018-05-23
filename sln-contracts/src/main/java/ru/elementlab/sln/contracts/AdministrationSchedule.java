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

import java.util.Date;

/**
 * Описывает расписание и схему приема лекарственного средства.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdministrationSchedule {

    /**
     * Дата первого приема. Обязательный параметр.
     */
    @JsonProperty("FirstAdministration")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date firstAdministration;

    /**
     * Дата последнего приема. Необязательный параметр, может быть null.
     */
    @JsonProperty("LastAdministration")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date lastAdministration;

    /**
     * Схема приема в виде текста. Необязательный параметр.
     */
    @JsonProperty("Schema")
    public String schema;

    public Date getFirstAdministration() {
        return firstAdministration;
    }

    public void setFirstAdministration(Date firstAdministration) {
        this.firstAdministration = firstAdministration;
    }

    public Date getLastAdministration() {
        return lastAdministration;
    }

    public void setLastAdministration(Date lastAdministration) {
        this.lastAdministration = lastAdministration;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
}
