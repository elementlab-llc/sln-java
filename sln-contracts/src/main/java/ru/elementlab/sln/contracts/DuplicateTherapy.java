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

import java.util.Collection;

/**
 * Дупликативная терапия
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DuplicateTherapy extends ProfessionalResult {

    /**
     * Вид дупликативной терапии.
     */
    @JsonProperty("Kind")
    public String kind;

    /**
     * Класс АТХ, к которому относятся препараты, вызывающие дублирование.
     * Значение указывается для дупликативной терапии вида "DuplicateTherapyAtATCClass".
     */
    @JsonProperty("ATCClass")
    public String aTCClass;

    /**
     *  Ингредиенты, общие для лекарственных средств. Значение указывается для
     *  дупликативной терапии вида "DuplicateTherapyAtIngredient".
     */
    @JsonProperty("CommonIngredients")
    public Collection<String> commonIngredients;

    /**
     * Класс дупликативной терапии. Значение указывается
     * только для дупликативной терапии вида "DuplicateTherapyAtClass".
     */
    @JsonProperty("Class")
    public Concept conceptClass;


    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getaTCClass() {
        return aTCClass;
    }

    public void setaTCClass(String aTCClass) {
        this.aTCClass = aTCClass;
    }

    public Collection<String> getCommonIngredients() {
        return commonIngredients;
    }

    public void setCommonIngredients(Collection<String> commonIngredients) {
        this.commonIngredients = commonIngredients;
    }



}