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
import ru.elementlab.sln.contracts.DuplicateTherapy;
import ru.elementlab.sln.contracts.PatientResult;
import ru.elementlab.sln.extension.MonographHelper;

import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class MonographHelperTest {

    private void testPatientMonograph(String inputFilename, String expectedOutputFilename)
            throws IOException {
        try {
            String out = new MonographHelper().getPatientMonographHtml(createPatientResult(inputFilename), null);
            Assertions.assertNotNull("Monograph transform failed for '" + inputFilename + "' ", out);
            //TODO
//            String expectedResult = readFileAsString(expectedOutputFilename);
        } catch (TransformerException e) {
            Assertions.fail(e.getMessage());
        }
    }

    private void testProfessionalMonograph(String inputFilename, String expectedOutputFilename)
            throws IOException {
        try {
            String out = new MonographHelper().getProfessionalMonographHtml(createProfessionalResult(inputFilename), null);
            Assertions.assertNotNull("Monograph transform failed for '" + inputFilename + "' ", out);
            //TODO
//            String expectedResult = readFileAsString(expectedOutputFilename);
        } catch (TransformerException e) {
            e.printStackTrace();
            Assertions.fail(e.getMessage());
        }
    }

    private DuplicateTherapy createProfessionalResult(String inputFilename) throws IOException {
        DuplicateTherapy result = new DuplicateTherapy();
        result.setProfessionalMonograph(readFileAsString(inputFilename));
        return result;
    }


    private PatientResult createPatientResult(String inputFilename) throws IOException {

        PatientResult result = new PatientResult();
        result.setPatientMonograph(readFileAsString(inputFilename));
        return result;
    }


    private String readFileAsString(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }


    @Test
    public void testPatientMonograph()
            throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        testPatientMonograph(classLoader.getResource("patient-monograph-input.xml").getPath(), classLoader.getResource("patient-monograph-output.html").getPath());
    }

    @Test
    public void testProfessionalMonograph()
            throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        testProfessionalMonograph(classLoader.getResource("professional-monograph-input.xml").getPath(), classLoader.getResource("professional-monograph-output.html").getPath());
    }

}