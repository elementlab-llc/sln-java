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
 * Описывает концепт, для которого может быть выполнен скрининг.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class ScreenableConcept extends Concept {

    /**
     * Признак, указывающий на необходимость выполнения скрининга в отношении этого концепта.
     * Если значение true, то скрининг будет выполнен.
     * <remarks>
     *      <p>Значение по умолчанию <b>true</b>.</p>
     *       <p>Если скрининг ориентирован на пары концептов (например, скрининг взаимодействий ЛС), то будут обработаны только те пары, в которых хотя бы у одного концепта этот признак имеет значение true.</p>
     * </remarks>
     */
    @JsonProperty("Screen")
    public boolean screen;

    /**
     * Произвольный код. Не используется сервисами скрининга.
     */
    @JsonProperty("CustomCode")
    public String customCode;

    /**
     * Произвольное название. Не используется сервисами скрининга.
     */
    @JsonProperty("CustomName")
    public String customName;


    public boolean isScreen() {
        return screen;
    }

    public void setScreen(boolean screen) {
        this.screen = screen;
    }

    public String getCustomCode() {
        return customCode;
    }

    public void setCustomCode(String customCode) {
        this.customCode = customCode;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }
}
