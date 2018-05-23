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
 * Параметры для тонкой настройки скрининга
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScreeningOptions {

    /**
     * Признак необходимости учета вспомогательных компонентов в составе лекарственных средств при выполнении скрининга.
     * <remarks>
     *      Необязательный параметр. По умолчанию true
     * </remarks>
     */
    @JsonProperty("IncludeInsignificantInactiveIngredients")
    public boolean includeInsignificantInactiveIngredients = true;

    /**
     *  true, если необходимо включить тексты рефератов в результат скрининга.
     * <remarks>
     *      Необязательный параметр. По умолчанию true
     * </remarks>
     */
    @JsonProperty("IncludeMonographs")
    public boolean includeMonographs = true;

    /**
     *  Дополнительные параметры скрининга анти-допинга
     */
    @JsonProperty("DopingAlerts")
    protected DopingAlertsOptions dopingAlerts;


    public boolean isIncludeInsignificantInactiveIngredients() {
        return includeInsignificantInactiveIngredients;
    }

    public void setIncludeInsignificantInactiveIngredients(boolean includeInsignificantInactiveIngredients) {
        this.includeInsignificantInactiveIngredients = includeInsignificantInactiveIngredients;
    }

    public boolean isIncludeMonographs() {
        return includeMonographs;
    }

    public void setIncludeMonographs(boolean includeMonographs) {
        this.includeMonographs = includeMonographs;
    }

    public DopingAlertsOptions getDopingAlerts() {
            if (dopingAlerts == null)
                dopingAlerts = new DopingAlertsOptions();
        return dopingAlerts;
    }

    public void setDopingAlerts(DopingAlertsOptions dopingAlerts) {
        this.dopingAlerts = dopingAlerts;
    }
}
