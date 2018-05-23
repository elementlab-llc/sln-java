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

import ru.elementlab.sln.client.exceptions.ApiErrors;
import ru.elementlab.sln.client.exceptions.ApiException;
import ru.elementlab.sln.contracts.*;

import javax.ws.rs.client.Client;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ApiClient {

    private static final int FIND_CONCEPT_LIMIT = 50;

    private ApiConnection connection;

    /**
     * Инициализирует экземпляр класса <see cref="ApiClient"/>
     *
     * @param connection
     */
    public ApiClient(ApiConnection connection) {
        this.connection = connection;
    }

    public ApiClient(String baseUrl, ClientCredentials credentials) throws NoSuchAlgorithmException, KeyManagementException {
        this(new ApiConnection(baseUrl, credentials));
    }

    public ApiClient(String baseUrl, ClientCredentials credentials, Client client) throws KeyManagementException, NoSuchAlgorithmException {
        this(new ApiConnection(baseUrl, credentials, client));
    }

    /**
     * Возвращает список типов концептов
     *
     * @return Список типов концептов
     */
    public ConceptType[] GetConceptTypes() throws ApiException {
        return get(ApiUrls.GET_CONCEPT_TYPES, ConceptType[].class, null);
    }


    /**
     * Возвращает список типов концептов из "внешних" справочников
     *
     * @return Список типов концептов
     */
    public ConceptType[] GetExternalConceptTypes() throws ApiException {
        return get(ApiUrls.GET_EXTERNAL_CONCEPT_TYPES, ConceptType[].class, null);
    }

    /**
     * Возвращает список концептов указанного типа
     *
     * @param type     Тип концептов
     * @param start    Индекс первого элемента списка
     * @param pageSize Максимальное количество возвращаемых концептов
     * @return Список концептов
     */
    public ListConceptsResult listConcepts(String type, int start, int pageSize) throws ApiException {
        if (type == null || type.isEmpty())
            throw new IllegalArgumentException("listConcepts param type is null");

        // запрашивается на 1 концепт больше, чтобы понять - есть ли еще концепты

        HashMap<String, String> urlParameters = new HashMap<String, String>();
        urlParameters.put("type", type);
        urlParameters.put("start", String.valueOf(start));
        urlParameters.put("pageSize", String.valueOf(pageSize + 1));

        ConceptInfo[] concepts = get(ApiUrls.LIST_CONCEPTS_OF_TYPE, ConceptInfo[].class, urlParameters);
        ListConceptsResult result = new ListConceptsResult(Arrays.asList(concepts), start, concepts.length > pageSize);
        return result;
    }

    /**
     * Выполняет поиск концептов с типами, соответствующими указанной области поиска
     *
     * @param query  Запрос для поиска
     * @param method Метод поиска
     * @param scope  Область поиска концептов
     * @return Список найденных концептов
     */
    public FoundConceptInfo[] findConcepts(String query, SearchMethod method, SearchScope scope) throws ApiException {
        if (query == null || query.isEmpty())
            return new FoundConceptInfo[0];

        HashMap<String, String> urlParameters = new HashMap<String, String>();
        urlParameters.put("q", query);
        urlParameters.put("scope", scope.name());
        urlParameters.put("searchBy", method.name());
        urlParameters.put("limit", String.valueOf(FIND_CONCEPT_LIMIT));

        return get(ApiUrls.FIND_CONCEPTS_BY_SCOPE, FoundConceptInfo[].class, urlParameters);
    }


    /**
     * Выполняет поиск концептов
     *
     * @param query  Запрос для поиска
     * @param method Метод поиска
     * @param types  Список типов концептов
     * @return Список найденных концептов
     */
    public FoundConceptInfo[] findConcepts(String query, SearchMethod method, String[] types) throws ApiException {
        if (query == null || query.isEmpty())
            return new FoundConceptInfo[0];

        HashMap<String, String> urlParameters = new HashMap<String, String>();
        urlParameters.put("q", query);
        urlParameters.put("type", String.join(",", types));
        urlParameters.put("searchBy", method.name());
        urlParameters.put("limit", String.valueOf(FIND_CONCEPT_LIMIT));

        return get(ApiUrls.FIND_CONCEPTS_BY_TYPE, FoundConceptInfo[].class, urlParameters);
    }


    /**
     * Возвращает информацию о концепте
     *
     * @param conceptType Тип концепта
     * @param conceptCode Код концепта
     * @return Описание концепта
     */
    public ConceptInfo getConcept(String conceptType, String conceptCode)  throws ApiException {
        if (conceptType == null || conceptType.isEmpty())
            throw new IllegalArgumentException(ApiErrors.E_MISSING_CONCEPT_TYPE + " conceptType");
        if (conceptCode == null || conceptCode.isEmpty())
            throw new IllegalArgumentException(ApiErrors.E_MISSING_CONCEPT_CODE + " conceptCode");

        HashMap<String, String> urlParameters = new HashMap<String, String>();
        urlParameters.put("type", conceptType);
        urlParameters.put("code", conceptCode);
        return get(ApiUrls.GET_CONCEPT, ConceptInfo.class, urlParameters);
    }

    /**
     * Возвращает список инструкций для заданного концепта
     *
     * @param conceptType Тип концепта
     * @param conceptCode Код концепта
     * @return Информация об имеющихся инструкциях
     */
    public Instruction[] listInstructions(String conceptType, String conceptCode) throws ApiException {
        if (conceptType == null || conceptType.isEmpty())
            throw new IllegalArgumentException(ApiErrors.E_MISSING_CONCEPT_TYPE + " conceptType");
        if (conceptCode == null || conceptCode.isEmpty())
            throw new IllegalArgumentException(ApiErrors.E_MISSING_CONCEPT_CODE + " conceptCode");

        HashMap<String, String> urlParameters = new HashMap<String, String>();
        urlParameters.put("type", conceptType);
        urlParameters.put("code", conceptCode);

        return get(ApiUrls.LIST_INSTRUCTIONS, Instruction[].class, urlParameters);

    }

    /**
     * Возвращает содержимое инструкции
     *
     * @param instructionCode Код инструкции
     * @return Объект, описывающий содержимое инструкции
     */
    public InstructionContent getGetInstruction(String instructionCode)  throws ApiException {

        if (instructionCode == null || instructionCode.isEmpty())
            throw new IllegalArgumentException(ApiErrors.E_MISSING_INSTRUCTION_CODE + "instructionCode");

        HashMap<String, String> urlParameters = new HashMap<String, String>();
        urlParameters.put("code", instructionCode);

        return get(ApiUrls.GET_INSTRUCTION_CONTENT, InstructionContent.class, urlParameters);

    }

    /**
     * Выполняет скрининг лекарственных препаратов
     *
     * @param request Параметры для выполнения скрининга
     * @return Результат скрининга
     */
    public ScreeningSummary screen(ScreenRequest request)  throws ApiException {
        if (request == null)
            throw new IllegalArgumentException(ApiErrors.E_MISSING_SCREEN_REQUEST);

        return post(ApiUrls.SCREENING, ScreeningSummary.class, null, request);

    }

    private <TResponse> TResponse get(String url, Class<TResponse> expectedResponse, Map<String, String> urlParameters) throws ApiException {
        return this.connection.get(url, expectedResponse, urlParameters);
    }

    private <TResponse> TResponse post(String url, Class<TResponse> expectedResponse, Map<String, String> urlParameters, Object request) throws ApiException {
        return this.connection.post(url, expectedResponse, urlParameters, request);
    }


    protected class ApiUrls {

        protected static final String CREATE_TOKEN_URL = "account/token";
        protected static final String GET_CONCEPT_TYPES = "concepts/types";
        protected static final String GET_EXTERNAL_CONCEPT_TYPES = "concepts/types/external";
        protected static final String LIST_CONCEPTS_OF_TYPE = "concepts/list/{type}";
        protected static final String FIND_CONCEPTS_BY_SCOPE = "concepts/find/{scope}";
        protected static final String FIND_CONCEPTS_BY_TYPE = "concepts/find";
        protected static final String GET_CONCEPT = "concepts/{type}";
        protected static final String SCREENING = "screening";
        protected static final String LIST_INSTRUCTIONS = "instructions";
        protected static final String GET_INSTRUCTION_CONTENT = "instructions/{code}";

    }

}
