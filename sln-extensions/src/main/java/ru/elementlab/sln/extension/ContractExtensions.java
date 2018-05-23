/*
 *
 * Module Name:  sln-extensions
 * Project:      sln
 *
 * Copyright (c) Element Lab LLC
 *
 *  THIS CODE AND INFORMATION IS PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 *  EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A PARTICULAR PURPOSE.
 *
 */

package ru.elementlab.sln.extension;

import ru.elementlab.sln.contracts.*;


public class ContractExtensions {

    /**
     * Возвращает обобщенный уровень риска
     *
     * @param alert Результат скрининга
     * @return Обобщенный уровень риска
     */
    public static GenericSeverityLevel GetGenericSeverity(Interaction alert) {
        switch (alert.severity.level) {
            case 10000:
                return GenericSeverityLevel.Major;
            case 20000:
                return GenericSeverityLevel.Moderate;
            case 30000:
                return GenericSeverityLevel.Minor;
            default:
                throw new IllegalArgumentException("Unknown Drug Interaction Severity Level: " + alert.severity.level);
        }
    }

    /**
     * Возвращает обобщенный уровень риска
     *
     * @param alert Результат скрининга
     * @return Обобщенный уровень риска
     */
    public static GenericSeverityLevel GetGenericSeverity(AllergicReaction alert) {
        return GenericSeverityLevel.Major;
    }

    /**
     * Возвращает обобщенный уровень риска
     *
     * @param alert Результат скрининга
     * @return Обобщенный уровень риска
     */
    public static GenericSeverityLevel GetGenericSeverity(Contraindication alert) {

        switch (alert.severity.level) {
            case 1:
            case 2:
                return GenericSeverityLevel.Major;
            case 3:
                return GenericSeverityLevel.Moderate;
            case 4:
                return GenericSeverityLevel.Minor;
            case 5:
                return GenericSeverityLevel.None;
            default:
                throw new IllegalArgumentException("Unknown Contraindication Severity Level: " + alert.severity.level);
        }
    }

    /**
     * Возвращает обобщенный уровень риска
     *
     * @param alert Результат скрининга
     * @return Обобщенный уровень риска
     */
    public static GenericSeverityLevel GetGenericSeverity(DuplicateTherapy alert) {
        return GenericSeverityLevel.Moderate;
    }

    /**
     * Возвращает обобщенный уровень риска для предупреждения по допингу
     *
     * @param alert Результат скрининга
     * @return Обобщенный уровень риска
     */
    public static GenericSeverityLevel GetGenericSeverity(DopingAlert alert) {
        switch (alert.severity.level) {
            case 10000:
                return GenericSeverityLevel.Major;
            case 20000:
                return GenericSeverityLevel.Moderate;
            case 30000:
                return GenericSeverityLevel.Minor;
            default:
                throw new IllegalArgumentException("Unknown Doping alert Severity Level: " + alert.severity.level);
        }
    }

    /**
     * Возвращает обобщенный уровень риска
     *
     * @param alert Результат скрининга
     * @return Обобщенный уровень риска
     */
    public static GenericSeverityLevel GetGenericSeverity(GeneticTest alert) {
        switch (alert.severity.level) {
            case 10000:
                return GenericSeverityLevel.Major;
            case 20000:
                return GenericSeverityLevel.Moderate;
            case 30000:
                return GenericSeverityLevel.Minor;
            default:
                throw new IllegalArgumentException("Unknown Genetic Test Severity Level:" + alert.severity.level);
        }
    }
}

