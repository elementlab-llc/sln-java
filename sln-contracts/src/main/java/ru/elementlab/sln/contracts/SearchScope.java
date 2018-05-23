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
 * Область поиска
 */
public enum SearchScope {

    /**
     * Лекарственные средства
     */
    Drugs,
    /**
     * Лекарственные средства и субстанции
     */
    DrugsAndSubstances,
    /**
     * Аллергены (лекарственные средства, классы аллергенов и ингредиенты)
     */
    Allergens,
    /**
     * Диагнозы в соответствии со справочником МКБ-10
     */
    Diseases,
    /**
     * Состояния пациента
     */
    Conditions,
    /**
     * Виды спорта
     */
    Sports,
    /**
     * Лекарственные средства, субстанции и БАДы
     */
    DrugsSubstancesAndDietarySupplements,
}
