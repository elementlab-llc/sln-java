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
 * Описывает результат получения списка концептов
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListConceptsResult {

    @JsonProperty("FirstItemIndex")
    public int firstItemIndex;

    /**
     * Указывает на наличие большего количества концептов
     */
    @JsonProperty("HasMoreItems")
    public boolean hasMoreItems;

    /**
     * Список концептов
     */
    @JsonProperty("Concepts")
    public List<ConceptInfo> concepts;

    /**
     * Инициализирует объект коллекции концептов
     *
     * @param source         Исходный список
     * @param firstItemIndex Индекс первого запрошенного концепта
     * @param hasMoreItems   Признак наличия большего количества концептов<
     */
    public ListConceptsResult(List<ConceptInfo> source, int firstItemIndex, boolean hasMoreItems) {
        concepts = source;
        this.firstItemIndex = firstItemIndex;
        this.hasMoreItems = hasMoreItems;
    }

    public int getFirstItemIndex() {
        return firstItemIndex;
    }

    public boolean isHasMoreItems() {
        return hasMoreItems;
    }

    public List<ConceptInfo> getConcepts() {
        return concepts;
    }
}
