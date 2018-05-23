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

import ru.elementlab.sln.contracts.InstructionContent;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.ResourceBundle;
import java.util.UUID;

public class InstructionHelper {


    private static final String PROPS = "instruction";
    private String instructionStylesheet;
    private StreamSource stylesheet;


    /**
     * Устанавливает XSLT-шаблон по умолчанию для формирования реферата в формате HTML
     */
    public InstructionHelper() {
        this.instructionStylesheet = getDefaultStylesheet();
        this.stylesheet = loadStylesheet(instructionStylesheet);
    }

    /**
     * Устанавливает XSLT-шаблон, используемый для формирования реферата в формате HTML
     *
     * @param stylesheet
     */
    public InstructionHelper(String stylesheet) {
        this.instructionStylesheet = stylesheet;
        this.stylesheet = loadStylesheet(instructionStylesheet);
    }

    /**
     * Возвращает содержимое инструкции в формате HTML
     *
     * @param content  Объект, описывающий содержимое инструкции
     * @param uniqueId Префикс, добавляемый ко всем ссылкам для их уникальности
     * @return Инструкция в формате HTML
     * @throws TransformerException
     */
    public String getContentAsHtml(InstructionContent content, String uniqueId) throws TransformerException {

        String xml = content.getContent();
        if (xml == null || xml.isEmpty())
            return null;

        if (uniqueId == null || uniqueId.isEmpty())
            uniqueId = UUID.randomUUID().toString();

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer xsltTransformer = transformerFactory.newTransformer(stylesheet);

        xsltTransformer.setParameter(InstructionParams.documentuniqueid.name(), uniqueId);

        StringWriter result = new StringWriter();
        xsltTransformer.transform(new StreamSource(new StringReader(xml)), new StreamResult(result));

        return new String(result.toString().getBytes(Charset.defaultCharset()));
    }

    /**
     * @return defaultStylesheet for Monograph transformation
     */
    private String getDefaultStylesheet() {

        ResourceBundle messages = ResourceBundle.getBundle(PROPS);
        String stylesheet = messages.getString(InstructionParams.Stylesheet.name());

        return stylesheet;
    }


    private StreamSource loadStylesheet(String stylesheet) {

        StreamSource style = new StreamSource(MonographHelper.class.getClassLoader().getResource(stylesheet).getPath());
        if (style == null)
            throw new RuntimeException("Could`t load StyleSheet " + stylesheet);

        return style;

    }


    private enum InstructionParams {
        Stylesheet,
        documentuniqueid
    }

}
