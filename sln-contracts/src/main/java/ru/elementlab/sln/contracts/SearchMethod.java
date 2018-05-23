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

/**
 * Способ поиска
 */
public enum SearchMethod {
    /**
     *  Нечеткий поиск по тексту
     */
    Text,
    /**
     * Точный поиск по штрих-коду
     */
    Barcode,
    /**
     * Точный поиск по названию концепта
     */
    ExactName
}