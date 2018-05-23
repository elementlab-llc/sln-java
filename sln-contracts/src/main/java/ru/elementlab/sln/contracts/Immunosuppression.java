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
 * Предупреждение, связанное с иммуносупрессией.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Immunosuppression extends ProfessionalResult {

    /**
     * Уровень риска.
     */
    @JsonProperty("Severity")
    public ImmunosuppressionSeverityLevel severity;

    /**
     * Иммуносупрессорный эффект.
     */
    @JsonProperty("Effect")
    public ImmunosuppressionEffect effect;

    /**
     * Ингредиент, вызывающий иммуносупрессорный эффект.
     */
    @JsonProperty("Ingredient")
    public ScreenableIngredient ingredient;

    /**
     * Пути введения препарата, относящиеся к этому предупреждению.
     */
    @JsonProperty("Routes")
    public List<Concept> routes;

    public ImmunosuppressionSeverityLevel getSeverity() {
        return severity;
    }

    public void setSeverity(ImmunosuppressionSeverityLevel severity) {
        this.severity = severity;
    }

    public ImmunosuppressionEffect getEffect() {
        return effect;
    }

    public void setEffect(ImmunosuppressionEffect effect) {
        this.effect = effect;
    }

    public ScreenableIngredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(ScreenableIngredient ingredient) {
        this.ingredient = ingredient;
    }

    public List<Concept> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Concept> routes) {
        this.routes = routes;
    }
}