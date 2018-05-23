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


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ru.elementlab.sln.contracts.*;
import ru.elementlab.sln.extension.ContractExtensions;
import ru.elementlab.sln.extension.GenericSeverityLevel;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class GenericSeverityCalculationsTest {


    @ParameterizedTest
    @ValueSource(ints = {0, 6})
    public void testGenericSeverityForInvalidContraindicationSeverity(final int level) {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    AgeContraindication result = new AgeContraindication();
                    ContraindicationSeverityLevel severity = new ContraindicationSeverityLevel();
                    severity.setLevel(level);
                    result.setSeverity(severity);
                    ContractExtensions.GetGenericSeverity(result);
                });

    }

    @ParameterizedTest
    @ValueSource(ints = {0, 4000})
    public void testGenericSeverityForInvalidDopingAlertSeverity(final int level) {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    DopingAlert result = new DopingAlert();
                    DopingAlertSeverityLevel severity = new DopingAlertSeverityLevel();
                    severity.setLevel(level);
                    result.setSeverity(severity);
                    ContractExtensions.GetGenericSeverity(result);

                });
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 4000})
    public void testGenericSeverityForInvalidGeneticTestSeverity(final int level) {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    GeneticTest result = new GeneticTest();
                    GeneticTestSeverityLevel severity = new GeneticTestSeverityLevel();
                    severity.setLevel(level);
                    result.setSeverity(severity);
                    ContractExtensions.GetGenericSeverity(result);
                });
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 4000})
    public void testGenericSeverityForInvalidInteractionSeverity(final int level) {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Interaction result = new Interaction();
                    InteractionSeverityLevel severity = new InteractionSeverityLevel();
                    severity.setLevel(level);
                    result.setSeverity(severity);
                    ContractExtensions.GetGenericSeverity(result);
                });

    }

    @Test
    public void testGenericSeverityForAllergicReactions() {

        AllergicReaction result = new AllergicReaction();
        GenericSeverityLevel generic = ContractExtensions.GetGenericSeverity(result);
        Assertions.assertEquals(GenericSeverityLevel.Major, generic);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void testGenericSeverityForContraindications(int level) {

        AgeContraindication result = new AgeContraindication();
        ContraindicationSeverityLevel severity = new ContraindicationSeverityLevel();
        severity.setLevel(level);
        result.setSeverity(severity);

        GenericSeverityLevel generic = ContractExtensions.GetGenericSeverity(result);
        switch (level) {
            case 1:
            case 2:
                Assertions.assertEquals(generic, GenericSeverityLevel.Major);
                break;
            case 3:
                Assertions.assertEquals(generic, GenericSeverityLevel.Moderate);
                break;
            case 4:
                Assertions.assertEquals(generic, GenericSeverityLevel.Minor);
                break;
            case 5:
                Assertions.assertEquals(generic, GenericSeverityLevel.None);
                break;
        }

    }


    @ParameterizedTest
    @ValueSource(ints = {10000, 20000, 30000})
    public void testGenericSeverityForDopingAlerts(int level) {
        DopingAlert result = new DopingAlert();
        DopingAlertSeverityLevel severity = new DopingAlertSeverityLevel();
        severity.setLevel(level);
        result.setSeverity(severity);
        GenericSeverityLevel generic = ContractExtensions.GetGenericSeverity(result);
        switch (level) {
            case 10000:
                Assertions.assertEquals(generic, GenericSeverityLevel.Major);
                break;
            case 20000:
                Assertions.assertEquals(generic, GenericSeverityLevel.Moderate);
                break;
            case 30000:
                Assertions.assertEquals(generic, GenericSeverityLevel.Minor);
                break;
        }
    }

    @Test
    public void testGenericSeverityForDuplicateTherapy() {
        DuplicateTherapy result = new DuplicateTherapy();
        GenericSeverityLevel generic = ContractExtensions.GetGenericSeverity(result);
        Assertions.assertEquals(generic, GenericSeverityLevel.Moderate);
    }


    @ParameterizedTest
    @ValueSource(ints = {10000, 20000, 30000})
    public void testGenericSeverityForGeneticTests(int level) {
        GeneticTest result = new GeneticTest();
        GeneticTestSeverityLevel severity = new GeneticTestSeverityLevel();
        severity.setLevel(level);
        result.setSeverity(severity);
        GenericSeverityLevel generic = ContractExtensions.GetGenericSeverity(result);
        switch (level) {
            case 10000:
                Assertions.assertEquals(generic, GenericSeverityLevel.Major);
                break;
            case 20000:
                Assertions.assertEquals(generic, GenericSeverityLevel.Moderate);
                break;
            case 30000:
                Assertions.assertEquals(generic, GenericSeverityLevel.Minor);
                break;
        }
    }


    @ParameterizedTest
    @ValueSource(ints = {10000, 20000, 30000})
    public void testGenericSeverityForInteractions(int level) {
        Interaction result = new Interaction();
        InteractionSeverityLevel severity = new InteractionSeverityLevel();
        severity.setLevel(level);
        result.setSeverity(severity);
        GenericSeverityLevel generic = ContractExtensions.GetGenericSeverity(result);
        switch (level) {
            case 10000:
                Assertions.assertEquals(generic, GenericSeverityLevel.Major);
                break;
            case 20000:
                Assertions.assertEquals(generic, GenericSeverityLevel.Moderate);
                break;
            case 30000:
                Assertions.assertEquals(generic, GenericSeverityLevel.Minor);
                break;
        }
    }

}
