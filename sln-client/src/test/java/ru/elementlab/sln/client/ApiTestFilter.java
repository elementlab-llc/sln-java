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

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiTestFilter implements ClientRequestFilter {

    @Override
    public void filter(ClientRequestContext clientRequestContext) throws IOException {

        String url = clientRequestContext.getUri().toString().replace(ApiClientTest.url, "");
        String resMessage = createMessage(url);
        Response.ResponseBuilder response = Response.status(Response.Status.OK).entity(resMessage).type(MediaType.APPLICATION_JSON);
        clientRequestContext.abortWith(response.build());
    }

    private String createMessage(String url) throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        if (url.contains("?"))
            url = url.substring(0, url.indexOf("?"));

        if (url.equalsIgnoreCase(ApiClient.ApiUrls.FIND_CONCEPTS_BY_TYPE))
            return readFileAsString(classLoader.getResource(ApiClient.ApiUrls.FIND_CONCEPTS_BY_TYPE + ".json").getPath());
        else if (url.equalsIgnoreCase(ApiClient.ApiUrls.GET_CONCEPT_TYPES))
            return readFileAsString(classLoader.getResource(ApiClient.ApiUrls.GET_CONCEPT_TYPES + ".json").getPath());
        else if (url.equalsIgnoreCase(ApiClient.ApiUrls.GET_EXTERNAL_CONCEPT_TYPES))
            return readFileAsString(classLoader.getResource(ApiClient.ApiUrls.GET_EXTERNAL_CONCEPT_TYPES + ".json").getPath());
        else if (url.equalsIgnoreCase(ApiClient.ApiUrls.LIST_INSTRUCTIONS))
            return readFileAsString(classLoader.getResource(ApiClient.ApiUrls.LIST_INSTRUCTIONS + ".json").getPath());
        else if (url.equalsIgnoreCase(ApiClient.ApiUrls.SCREENING))
            return readFileAsString(classLoader.getResource(ApiClient.ApiUrls.SCREENING + ".json").getPath());
        else if (url.equalsIgnoreCase(ApiClient.ApiUrls.CREATE_TOKEN_URL))
            return readFileAsString(classLoader.getResource(ApiClient.ApiUrls.CREATE_TOKEN_URL + ".json").getPath());

        else {
            String regexpExpr = "([a-z0-9_\\.-]+)";

            String sample_url = ApiClient.ApiUrls.GET_INSTRUCTION_CONTENT.replaceAll("\\{.*?}", regexpExpr);
            Pattern pattern = Pattern.compile(sample_url, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(url);

            if (matcher.matches())
                return readFileAsString(classLoader.getResource(ApiClient.ApiUrls.GET_INSTRUCTION_CONTENT.replaceAll("[\\{|}]", "") + ".json").getPath());

            sample_url = ApiClient.ApiUrls.LIST_CONCEPTS_OF_TYPE.replaceAll("\\{.*?}", regexpExpr);
            pattern = Pattern.compile(sample_url, Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(url);

            if (matcher.matches())
                return readFileAsString(classLoader.getResource(ApiClient.ApiUrls.LIST_CONCEPTS_OF_TYPE.replaceAll("[\\{|}]", "") + ".json").getPath());

            sample_url = ApiClient.ApiUrls.FIND_CONCEPTS_BY_SCOPE.replaceAll("\\{.*?}", regexpExpr);
            pattern = Pattern.compile(sample_url, Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(url);

            if (matcher.matches())
                return readFileAsString(classLoader.getResource(ApiClient.ApiUrls.FIND_CONCEPTS_BY_SCOPE.replaceAll("[\\{|}]", "") + ".json").getPath());
            else
                return readFileAsString(classLoader.getResource(ApiClient.ApiUrls.GET_CONCEPT.replaceAll("[\\{|}]", "") + ".json").getPath());

        }


    }

    private String readFileAsString(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }
}