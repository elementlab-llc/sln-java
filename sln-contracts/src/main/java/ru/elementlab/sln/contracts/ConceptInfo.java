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
 * Описание концепта
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConceptInfo extends Concept {

    /**
     * Адрес ресурса, обратившись к которому можно получить описание этого концепта.
     */
    @JsonProperty("ResourceUrl")
    public String resourceUrl;
    /**
     * Дополнительные свойства концепта
     */
    @JsonProperty("ExtendedProperties")
    public Object extendedProperties;


    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public Object getExtendedProperties() {
        return extendedProperties;
    }

    public void setExtendedProperties(Object extendedProperties) {
        this.extendedProperties = extendedProperties;
    }
}
