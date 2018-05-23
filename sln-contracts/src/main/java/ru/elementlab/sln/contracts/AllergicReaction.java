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

import java.util.ArrayList;
import java.util.List;

/**
 * Описание аллергической реакции
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AllergicReaction extends ProfessionalResult {

    /**
     * Вид аллергической реакции. Возможны следующие значения:
     * AllergicReactionAtClass - реакция из-за отношения аллергена и препарата к одному классу аллергенов;
     * AllergicReactionAtIngredient - реакция, связанная с тем, что назначаемый препарат содержит ингредиент, на который у пациента имеется аллегия.
     * AllergicReactionAtComponent - реакция из-за наличия в препарате компонентов, на которые у пациента имеется аллергия.
     */
    @JsonProperty("Kind")
    public String kind;

    /**
     * Список аллергий (аллергенов), из-за которых возможно возникновение аллергической реакции.
     */
    @JsonProperty("Allergies")
    public ArrayList<Allergy> allergies;

    /**
     * Класс аллергенов, с которым связана аллергическая реакция.
     */
    @JsonProperty("AllergenClass")
    public AllergenClass allergenClass;

    /**
     * Ингредиент, с которым связана эта аллергическая реакция.
     */
    @JsonProperty("Ingredient")
    public ScreenableIngredient ingredient;

    /**
     * Список названий компонентов, с которыми связана эта аллергическая реакция.
     */
    @JsonProperty("Components")
    public List<String> components;


    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public ArrayList<Allergy> getAllergies() {
        return allergies;
    }

    public void setAllergies(ArrayList<Allergy> allergies) {
        this.allergies = allergies;
    }

    public AllergenClass getAllergenClass() {
        return allergenClass;
    }

    public void setAllergenClass(AllergenClass allergenClass) {
        this.allergenClass = allergenClass;
    }

    public ScreenableIngredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(ScreenableIngredient ingredient) {
        this.ingredient = ingredient;
    }

    public List<String> getComponents() {
        return components;
    }

    public void setComponents(List<String> components) {
        this.components = components;
    }
}
