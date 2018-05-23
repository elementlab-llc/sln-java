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
import ru.elementlab.sln.contracts.InstructionContent;
import ru.elementlab.sln.extension.InstructionHelper;

import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class InstructionHelperTest {

    private void testInstruction(String inputFilename, String expectedOutputFilename)
            throws IOException {
        try {
            String out = new InstructionHelper().getContentAsHtml(createInstructionContent(inputFilename), null);
//            String expectedResult = readFileAsString(expectedOutputFilename);
            Assertions.assertNotNull("Instruction transform failed for '" + inputFilename + "' ", out);
            //TODO
//            Assertions.assertEquals("Instruction transform failed for '" + inputFilename + "' ",expectedResult, out);
        } catch (TransformerException e) {
            Assertions.fail(e.getMessage());
        }
    }


    private InstructionContent createInstructionContent(String inputFilename) throws IOException {

        InstructionContent result = new InstructionContent();
        result.setContent(readFileAsString(inputFilename));
        return result;
    }


    private String readFileAsString(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }


    @Test
    public void testFullInstruction()
            throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        testInstruction(classLoader.getResource("instruction-input.xml").getPath(), classLoader.getResource("instruction-output.html").getPath());
    }


}