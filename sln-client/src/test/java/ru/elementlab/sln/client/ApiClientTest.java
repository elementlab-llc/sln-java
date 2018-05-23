/*
 *
 * Module Name:  sln-client
 * Project:      sln
 *
 * Copyright (c) Element Lab LLC
 *
 *  THIS CODE AND INFORMATION IS PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 *  EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A PARTICULAR PURPOSE.
 *
 */

package ru.elementlab.sln.client;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.feature.LoggingFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.elementlab.sln.contracts.*;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ApiClientTest {

    public static final String url = new String("https://testint.drugscreening.ru/v1/");
    private static ApiClient apiClient;
    Logger logger = Logger.getLogger(ApiClientTest.class.getName());


    @BeforeAll
    public static void configure() throws KeyManagementException, NoSuchAlgorithmException {


        ClientCredentials credentials = new ClientCredentials("login", "password");
        SSLContext ctx = SSLContext.getInstance("TLSv1.2");
        ctx.init(null, null, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(ctx.getSocketFactory());
        Client client = ClientBuilder.newBuilder()
                .sslContext(ctx).withConfig(ClientBuilder.newBuilder().getConfiguration()).build();
        client.register(JacksonJsonProvider.class);
        client.register(ApiTestFilter.class);
        client.register(new LoggingFeature());
        apiClient = new ApiClient(url, credentials, client);

    }


    @Test
    public void getExternalConceptTypesTest() {
        ConceptType[] result = apiClient.GetExternalConceptTypes();
        for (int i = 0; i < result.length; i++)
            logger.info(result[i].getName() + " " + result[i].getType());
        Assertions.assertNotNull(result);

    }

    @Test
    public void getConceptTypesTest() {
        ConceptType[] result = apiClient.GetConceptTypes();
        for (int i = 0; i < result.length; i++)
            logger.info(result[i].getName() + " " + result[i].getType());
        Assertions.assertNotNull(result);
    }


    @Test
    public void getListConceptsTest() {
        ListConceptsResult result = apiClient.listConcepts("ICD10CM", 1, 20);
        logger.info("ListConcepts result size  " + result.getConcepts().size() + " and isHasMoreItems : " + result.isHasMoreItems());
        for (ConceptInfo items : result.getConcepts()) {
            logger.info("ConceptInfo  " +
                    "\n " + items.getName() + " " + items.getType() + " " + items.getCode() +
                    "\n " + items.getResourceUrl() +
                    "\n " + items.getExtendedProperties()
            );
        }
        Assertions.assertNotNull(result);
    }

    @Test
    public void getFindConceptsTest() {
        FoundConceptInfo[] result = apiClient.findConcepts("варфарек", SearchMethod.Text, SearchScope.Allergens);
        for (int i = 0; i < result.length; i++)
            logger.info("FoundConceptInfo  " + i + " :" +
                    "\n " + result[i].getName() + " " + result[i].getType() + " " + result[i].getCode() +
                    "\n " + result[i].getResourceUrl() + " " + result[i].getDisplayName() + " " + result[i].getScore() +
                    "\n " + result[i].getExtendedProperties()
            );
        Assertions.assertNotNull(result);
    }

    @Test
    public void getFindConceptsOnTypesTest() {
        FoundConceptInfo[] result = apiClient.findConcepts("язва", SearchMethod.Text, new String[]{"ICD10CM"});
        for (int i = 0; i < result.length; i++)
            logger.info("FoundConceptInfo  " + i + " :" +
                    "\n " + result[i].getName() + " " + result[i].getType() + " " + result[i].getCode() +
                    "\n " + result[i].getResourceUrl() + " " + result[i].getDisplayName() + " " + result[i].getScore() +
                    "\n " + result[i].getExtendedProperties()
            );
        Assertions.assertNotNull(result);
    }

    @Test
    public void getGetConceptTest() {
        ConceptInfo result = apiClient.getConcept("DispensableDrug", "DD0001300");

        logger.info("ConceptInfo: " +
                "\n " + result.getCode() + " " + result.getName() + " " + result.getType() +
                "\n " + result.getResourceUrl() +
                "\n " + result.getExtendedProperties());

        Assertions.assertNotNull(result);
    }

    @Test
    public void listInstructionsTest() {
        Instruction[] result = apiClient.listInstructions("DispensableDrug", "DD0001300");
        for (int i = 0; i < result.length; i++)
            logger.info("Instruction " + i + " :" +
                    "\n " + result[i].getCode() + " " + result[i].getName() +
                    "\n " + result[i].getContentUrl()
            );
        Assertions.assertNotNull(result);
    }


    @Test
    public void getGetInstructionTest() {
        InstructionContent result = apiClient.getGetInstruction("abb0f317-032d-4f40-8c5c-92695249de00");

        logger.info("ConceptInfo: " +
                "\n " + result.getCode() + " " + result.getName() + " " + result.getResourceUrl() +
                "\n " + result.getContent());

        Assertions.assertNotNull(result);
    }

    @Test
    public void getScreenTest() throws ParseException {
        ScreenRequest request = new ScreenRequest();
        request.setScreeningTypes(ScreeningType.DrugDrugInteractions.name());
        ArrayList<Drug> drugs = new ArrayList<>();
        Drug drug = new Drug();
        drug.setCode("DD0000801");
        drug.setType("DispensableDrug");
        drug.setName("Аспирин табл. 500мг");
        drug.setScreen(true);
        drugs.add(drug);

        Drug drug2 = new Drug();
        drug2.setCode("DD0009390");
        drug2.setType("DispensableDrug");
        drug2.setName("Варфарекс табл. 5мг");
        drug2.setScreen(true);
        drugs.add(drug2);

        request.setDrugs(drugs);

        List<Disease> diseases = new ArrayList<>();
        Disease disease = new Disease();
        disease.setPrimary(true);
        disease.setCode("K25.0");
        disease.setType("ICD10CM");
        disease.setName("Язва желудка острая с кровотечением");
        disease.setScreen(true);
        diseases.add(disease);

        request.setDiseases(diseases);

        List<Allergy> allergies = new ArrayList<>();
        Allergy allergy = new Allergy();
        allergy.setType("AllergenClass");
        allergy.setCode("ALGC0073");
        allergy.setScreen(true);
        allergy.setName("Варфарин и родственные препараты");
        allergies.add(allergy);

        request.setAllergies(allergies);

        ScreeningOptions options = new ScreeningOptions();
        DopingAlertsOptions alert = new DopingAlertsOptions();
        alert.setIgnoreCompetitionPeriod(false);
        alert.setIgnoreGender(true);

        options.setDopingAlerts(alert);
        options.setIncludeInsignificantInactiveIngredients(false);
        options.setIncludeMonographs(false);

        request.setOptions(options);

        Patient patient = new Patient();
        patient.setGender(Gender.Female);
        patient.setWeight(new BigDecimal(58.1));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        patient.setBirthDate(format.parse("1995-03-05"));

        request.setPatient(patient);

        ScreeningSummary result = apiClient.screen(request);

        Assertions.assertNotNull(result);
    }
}
