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
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Содержит все результаты, полученные при выполнении запрошенных видов скрининга.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScreeningSummary {

    /**
     * Уникальный идентификатор результата скрининга
     */
    @JsonProperty("Id")
    public String id;

    /**
     * Список сообщений, относящихся ко всем выполненным видам скрининга.
     */
    @JsonProperty("Messages")
    public Message[] messages;

    /**
     * Обнаруженные взаимодействия между лекарственными средствами.
     */
    @JsonProperty("DrugDrugInteractions")
    public ResultsCollection<Interaction> drugDrugInteractions;

    /**
     * Обнаруженные взаимодействия между лекарственными средствами и пищей.
     */
    @JsonProperty("DrugFoodInteractions")
    public ResultsCollection<Interaction> drugFoodInteractions;

    /**
     * Обнаруженные взаимодействия между лекарственными средствами и алкоголем.
     */
    @JsonProperty("DrugAlcoholInteractions")
    public ResultsCollection<Interaction> drugAlcoholInteractions;

    /**
     * Обнаруженные аллергические реакции.
     */
    @JsonProperty("AllergicReactions")
    public ResultsCollection<AllergicReaction> allergicReactions;

    /**
     * Обнаруженные противопоказания по возрастным характеристикам.
     */
    @JsonProperty("AgeContraindications")
    public ResultsCollection<AgeContraindication> ageContraindications;

    /**
     * Обнаруженные противопоказания по половой принадлежности.
     */
    @JsonProperty("GenderContraindications")
    public ResultsCollection<GenderContraindication> genderContraindications;

    /**
     * Обнаруженные противопоказания при лактации.
     */
    @JsonProperty("LactationContraindications")
    public ResultsCollection<LactationContraindication> lactationContraindications;

    /**
     * Обнаруженные противопоказания при беременности.
     */
    @JsonProperty("PregnancyContraindications")
    public ResultsCollection<PregnancyContraindication> pregnancyContraindications;

    /**
     * Обнаруженные противопоказания к диагнозам.
     */
    @JsonProperty("DiseaseContraindications")
    public ResultsCollection<DiseaseContraindication> diseaseContraindications;

    /**
     * Обнаруженные дублирования терапии.
     */
    @JsonProperty("DuplicateTherapies")
    public ResultsCollection<DuplicateTherapy> duplicateTherapies;

    /**
     * Обнаруженные предупреждения, связанные с допинг-контролем.
     */
    @JsonProperty("DopingAlerts")
    public ResultsCollection<DopingAlert> dopingAlerts;

    /**
     * Обнаруженные предупреждения о необходимости фармакогенетического тестирования.
     */
    @JsonProperty("GeneticTests")
    public ResultsCollection<GeneticTest> geneticTests;

    /**
     * Обнаруженные предупреждения о необходимости фармакогенетического тестирования.
     */
    @JsonProperty("Immunosuppression")
    public ResultsCollection<Immunosuppression> immunosuppressions;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Message[] getMessages() {
        return messages;
    }

    public void setMessages(Message[] messages) {
        this.messages = messages;
    }

    public ResultsCollection<Interaction> getDrugDrugInteractions() {
        return drugDrugInteractions;
    }

    public void setDrugDrugInteractions(ResultsCollection<Interaction> drugDrugInteractions) {
        this.drugDrugInteractions = drugDrugInteractions;
    }

    public ResultsCollection<Interaction> getDrugFoodInteractions() {
        return drugFoodInteractions;
    }

    public void setDrugFoodInteractions(ResultsCollection<Interaction> drugFoodInteractions) {
        this.drugFoodInteractions = drugFoodInteractions;
    }

    public ResultsCollection<Interaction> getDrugAlcoholInteractions() {
        return drugAlcoholInteractions;
    }

    public void setDrugAlcoholInteractions(ResultsCollection<Interaction> drugAlcoholInteractions) {
        this.drugAlcoholInteractions = drugAlcoholInteractions;
    }

    public ResultsCollection<AllergicReaction> getAllergicReactions() {
        return allergicReactions;
    }

    public void setAllergicReactions(ResultsCollection<AllergicReaction> allergicReactions) {
        this.allergicReactions = allergicReactions;
    }

    public ResultsCollection<AgeContraindication> getAgeContraindications() {
        return ageContraindications;
    }

    public void setAgeContraindications(ResultsCollection<AgeContraindication> ageContraindications) {
        this.ageContraindications = ageContraindications;
    }

    public ResultsCollection<GenderContraindication> getGenderContraindications() {
        return genderContraindications;
    }

    public void setGenderContraindications(ResultsCollection<GenderContraindication> genderContraindications) {
        this.genderContraindications = genderContraindications;
    }

    public ResultsCollection<LactationContraindication> getLactationContraindications() {
        return lactationContraindications;
    }

    public void setLactationContraindications(ResultsCollection<LactationContraindication> lactationContraindications) {
        this.lactationContraindications = lactationContraindications;
    }

    public ResultsCollection<PregnancyContraindication> getPregnancyContraindications() {
        return pregnancyContraindications;
    }

    public void setPregnancyContraindications(ResultsCollection<PregnancyContraindication> pregnancyContraindications) {
        this.pregnancyContraindications = pregnancyContraindications;
    }

    public ResultsCollection<DiseaseContraindication> getDiseaseContraindications() {
        return diseaseContraindications;
    }

    public void setDiseaseContraindications(ResultsCollection<DiseaseContraindication> diseaseContraindications) {
        this.diseaseContraindications = diseaseContraindications;
    }

    public ResultsCollection<DuplicateTherapy> getDuplicateTherapies() {
        return duplicateTherapies;
    }

    public void setDuplicateTherapies(ResultsCollection<DuplicateTherapy> duplicateTherapies) {
        this.duplicateTherapies = duplicateTherapies;
    }

    public ResultsCollection<DopingAlert> getDopingAlerts() {
        return dopingAlerts;
    }

    public void setDopingAlerts(ResultsCollection<DopingAlert> dopingAlerts) {
        this.dopingAlerts = dopingAlerts;
    }

    public ResultsCollection<GeneticTest> getGeneticTests() {
        return geneticTests;
    }

    public void setGeneticTests(ResultsCollection<GeneticTest> geneticTests) {
        this.geneticTests = geneticTests;
    }

    public ResultsCollection<Immunosuppression> getImmunosuppressions() {
        return immunosuppressions;
    }

    public void setImmunosuppressions(ResultsCollection<Immunosuppression> immunosuppressions) {
        this.immunosuppressions = immunosuppressions;
    }
}
