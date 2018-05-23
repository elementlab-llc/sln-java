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

import ru.elementlab.sln.contracts.PatientResult;
import ru.elementlab.sln.contracts.ProfessionalResult;

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

/**
 * Хэлпер для трансформации XML-монографий в HTML
 */
public class MonographHelper {

    static final String PROPS = "monograph";

    private String monographStylesheetName;
    private StreamSource monographStylesheet;

    /**
     * Устанавливает XSLT-шаблон по умолчанию для формирования реферата в формате HTML
     */
    public MonographHelper() {
        this.monographStylesheetName = getDefaultStylesheet();
        this.monographStylesheet = loadStylesheet(monographStylesheetName);
    }


    /**
     * Устанавливает XSLT-шаблон, используемый для формирования реферата в формате HTML
     *
     * @param stylesheet XSLT-шаблон
     */
    public MonographHelper(String stylesheet) {
        this.monographStylesheetName = stylesheet;
        this.monographStylesheet = loadStylesheet(monographStylesheetName);

    }

    /**
     * Возвращает текст профессионального реферата в формате HTML
     *
     * @param result       Результат скрининга
     * @param uniquePrefix
     * @return Содержимое профессионального реферата в формате HTML
     * @throws TransformerException
     */
    public String getProfessionalMonographHtml(ProfessionalResult result, String uniquePrefix) throws TransformerException {

        if (result == null || result.getProfessionalMonograph() == null || result.getProfessionalMonograph().isEmpty())
            return null;

        if (uniquePrefix == null || uniquePrefix.isEmpty())
            uniquePrefix = UUID.randomUUID().toString();

        return GetMonographHtml(result.getProfessionalMonograph(), uniquePrefix);
    }

    /**
     * Возвращает текст пациентского реферата в формате HTML.
     *
     * @param result       Результат скрининга
     * @param uniquePrefix
     * @return Содержимое пациентского реферата в формате HTML
     * @throws TransformerException
     */
    public String getPatientMonographHtml(PatientResult result, String uniquePrefix) throws TransformerException {
        if (result == null || result.getPatientMonograph() == null || result.getPatientMonograph().isEmpty())
            return null;

        if (uniquePrefix == null || uniquePrefix.isEmpty())
            uniquePrefix = UUID.randomUUID().toString();

        return GetMonographHtml(result.getPatientMonograph(), uniquePrefix);
    }

    /**
     * Преобразует монографию из XML в HTML
     *
     * @param xml      Монография в формате XML
     * @param uniqueId Уникальный идентификатор монографии
     * @return Монография в формате HTML
     * @throws TransformerException
     */
    private String GetMonographHtml(String xml, String uniqueId) throws TransformerException {
        if (xml.isEmpty())
            xml = "<monograph></monograph>";

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer xsltTransformer = transformerFactory.newTransformer(monographStylesheet);

        ResourceBundle messages = ResourceBundle.getBundle(PROPS);
        xsltTransformer.setParameter(MonographParams.documentuniqueid.name(), uniqueId);
        xsltTransformer.setParameter(MonographParams.managementlevelstitle.name(), messages.getString(MonographParams.managementlevelstitle.name()));
        xsltTransformer.setParameter(MonographParams.severitylevelstitle.name(), messages.getString(MonographParams.severitylevelstitle.name()));
        xsltTransformer.setParameter(MonographParams.documentationlevelstitle.name(), messages.getString(MonographParams.documentationlevelstitle.name()));
        xsltTransformer.setParameter(MonographParams.onsetstitle.name(), messages.getString(MonographParams.onsetstitle.name()));
        xsltTransformer.setParameter(MonographParams.placentaltransferstitle.name(), messages.getString(MonographParams.placentaltransferstitle.name()));
        xsltTransformer.setParameter(MonographParams.breastfeedingaapstitle.name(), messages.getString(MonographParams.breastfeedingaapstitle.name()));
        xsltTransformer.setParameter(MonographParams.breastfeedingratingstitle.name(), messages.getString(MonographParams.breastfeedingratingstitle.name()));
        xsltTransformer.setParameter(MonographParams.breastfeedingexcretedstitle.name(), messages.getString(MonographParams.breastfeedingexcretedstitle.name()));
        xsltTransformer.setParameter(MonographParams.alerttitle.name(), messages.getString(MonographParams.alerttitle.name()));
        xsltTransformer.setParameter(MonographParams.effecttitle.name(), messages.getString(MonographParams.effecttitle.name()));
        xsltTransformer.setParameter(MonographParams.mechanismtitle.name(), messages.getString(MonographParams.mechanismtitle.name()));
        xsltTransformer.setParameter(MonographParams.managementtitle.name(), messages.getString(MonographParams.managementtitle.name()));
        xsltTransformer.setParameter(MonographParams.discussiontitle.name(), messages.getString(MonographParams.discussiontitle.name()));
        xsltTransformer.setParameter(MonographParams.commenttitle.name(), messages.getString(MonographParams.commenttitle.name()));
        xsltTransformer.setParameter(MonographParams.whattitle.name(), messages.getString(MonographParams.whattitle.name()));
        xsltTransformer.setParameter(MonographParams.whytitle.name(), messages.getString(MonographParams.whytitle.name()));
        xsltTransformer.setParameter(MonographParams.instructionstitle.name(), messages.getString(MonographParams.instructionstitle.name()));
        xsltTransformer.setParameter(MonographParams.drugstitle.name(), messages.getString(MonographParams.drugstitle.name()));
        xsltTransformer.setParameter(MonographParams.referencestitle.name(), messages.getString(MonographParams.referencestitle.name()));

        StringWriter result = new StringWriter();
        xsltTransformer.transform(new StreamSource(new StringReader(xml)), new StreamResult(result));

        return new String(result.toString().getBytes(Charset.defaultCharset()));
    }

    /**
     * @return
     */
    private String getDefaultStylesheet() {

        ResourceBundle messages = ResourceBundle.getBundle(PROPS);
        String stylesheet = messages.getString(MonographParams.Stylesheet.name());

        return stylesheet;
    }

    /**
     * @param stylesheet
     * @return
     */
    private StreamSource loadStylesheet(String stylesheet) {

        StreamSource style = new StreamSource(MonographHelper.class.getClassLoader().getResource(stylesheet).getPath());
        if (style == null)
            throw new RuntimeException("Could`t load StyleSheet " + stylesheet);

        return style;

    }


    private enum MonographParams {
        Stylesheet,
        documentuniqueid,
        managementlevelstitle,
        severitylevelstitle,
        documentationlevelstitle,
        onsetstitle,
        placentaltransferstitle,
        breastfeedingaapstitle,
        breastfeedingratingstitle,
        breastfeedingexcretedstitle,
        alerttitle,
        effecttitle,
        mechanismtitle,
        managementtitle,
        discussiontitle,
        commenttitle,
        whattitle,
        whytitle,
        instructionstitle,
        drugstitle,
        referencestitle
    }
}
