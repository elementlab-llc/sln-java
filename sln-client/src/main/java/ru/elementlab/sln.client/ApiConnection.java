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
import ru.elementlab.sln.client.exceptions.*;
import ru.elementlab.sln.contracts.ClientCredentials;
import ru.elementlab.sln.contracts.CreatedToken;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;
import java.net.HttpURLConnection;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;
import java.util.logging.Logger;

public class ApiConnection {


    private ClientCredentials credentials;
    private MultivaluedMap<String, Object> _token;
    private String baseURI;
    private Client client;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Инициализирует экземпляр класса <see cref="ApiConnection"/>
     *
     * @param url         Адрес сервиса скрининга
     * @param credentials Данные, необходимые для идентификации и аутентификации пользователя сервиса
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
    public ApiConnection(String url, ClientCredentials credentials) throws KeyManagementException, NoSuchAlgorithmException {
        this(url, credentials, null);
    }

    /**
     * Инициализирует экземпляр класса <see cref="ApiConnection"/>
     *
     * @param url         Адрес сервиса скрининга
     * @param credentials Данные, необходимые для идентификации и аутентификации пользователя сервиса
     * @param client      rs клиент с необходимыми настройками
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public ApiConnection(String url, ClientCredentials credentials, Client client) throws NoSuchAlgorithmException, KeyManagementException {
        logger.info("creating ApiConnection with params \n url = " + url + " \n credentials = " + credentials.getClientId() + " | " + credentials.clientSecret);
        if (url == null || url.isEmpty())
            throw new IllegalArgumentException(ApiErrors.E_SERVICE_URL_COULD_NOT_BE_EMPTY);

        if (!url.endsWith("/"))
            url += "/";
        this.baseURI = url;
        this.credentials = credentials;
        this._token = new MultivaluedHashMap<>();

        if (client == null) {
            logger.info("Client is null... Creating default client");
            this.client = createDefaultClient();
        } else
            this.client = client;



    }


    /**
     * Выполняет авторизованный GET запрос к API
     *
     * @param resourceUrl   Относительный адрес ресурса
     * @param urlParameters Параметры для формирования полного URL запроса
     * @param <TResponse>   Тип данных результата
     * @return Результат запроса
     */
    public <TResponse> TResponse get(String resourceUrl, Class<TResponse> expectedResponse, Map<String, String> urlParameters) throws ApiException {
        return call(HttpMethod.GET, resourceUrl, expectedResponse, urlParameters, null);
    }

    /**
     * Выполняет авторизованный POST запрос к API
     *
     * @param resourceUrl   Относительный адрес ресурса
     * @param urlParameters Параметры для формирования полного URL запроса
     * @param payload       Данные для передачи в теле запроса
     * @param <TResponse>   Тип данных результата
     * @return Результат запроса
     */
    public <TResponse> TResponse post(String resourceUrl, Class<TResponse> expectedResponse, Map<String, String> urlParameters, Object payload) throws ApiException {
        return call(HttpMethod.POST, resourceUrl, expectedResponse, urlParameters, payload);
    }

    /**
     * Выполняет авторизованный запрос к API
     *
     * @param method        Метод HTTP
     * @param url           Относительный адрес ресурса
     * @param urlParameters Параметры для формирования полного URL запроса
     * @param payload       Данные для передачи в теле запроса
     * @param <TResponse>   Тип результата
     * @return Результат запроса
     */
    private <TResponse> TResponse call(String method, String url, Class<TResponse> expectedResponse, Map<String, String> urlParameters, Object payload) throws ApiException {
        TResponse result = null;


        boolean isRetry = false;
        Response responseMessage;
        while (true) {
            WebTarget webTarget = createWebTarget(url, urlParameters);
            this._token = getOrCreateToken(isRetry);

            logger.info("call method : " + method + " with params" +
                    "\n expectedResponse : " + expectedResponse +
                    "\n url : " + webTarget.getUri() +
                    "\n payload : " + ((payload == null) ? null : payload));
            responseMessage = webTarget.request().headers(_token).accept(MediaType.APPLICATION_JSON_TYPE).method(
                    method,
                    Entity.entity((payload == null) ? "" : payload, MediaType.APPLICATION_JSON_TYPE),
                    Response.class);

            // выполним повторный запрос при истекшем токене
            if (responseMessage.getStatus() == Status.UNAUTHORIZED.getStatusCode() && !isRetry)
                isRetry = true;
            else
                break;
        }
        result = ReadResponse(responseMessage, expectedResponse);
        return result;
    }

    private MultivaluedMap<String, Object> getOrCreateToken(boolean force) {
        if (_token == null || force) {
            CreatedToken result = sendAnonymous(HttpMethod.POST, CreatedToken.class, ApiClient.ApiUrls.CREATE_TOKEN_URL, null, credentials);
            this._token.putSingle("Authorization", "Bearer " + result.getToken());
            logger.info("Created token " + _token.get("Authorization"));
        }
        return this._token;
    }

    /**
     * Выполняет анонимный запрос к API
     *
     * @param method        Метод HTTP
     * @param url           Относительный адрес ресурса
     * @param urlParameters Параметры для формирования полного URL запроса
     * @param payload       Данные для передачи в теле запроса
     * @param <TResponse>   Тип результата
     * @return Результат запроса
     */
    private <TResponse> TResponse sendAnonymous(String method, Class<TResponse> expectedResponse, String url, Map<String, String> urlParameters, Object payload) {
        TResponse result;

        WebTarget webTarget = createWebTarget(url, urlParameters);

        logger.info("Trying to sendAnonymous with params \n method : " + method +
                "\n expectedResponse: " + expectedResponse.getName() +
                "\n url: " + webTarget.getUri() +
                "\n payload: " + payload.getClass());

        Response responseMessage = webTarget.request().headers(_token).accept(MediaType.APPLICATION_JSON_TYPE).method(
                method,
                Entity.entity(payload, MediaType.APPLICATION_JSON_TYPE),
                Response.class);
        result = ReadResponse(responseMessage, expectedResponse);

        return result;
    }

    /**
     * Создание запроса с указанными параметрами
     *
     * @param url           Относительный адрес ресурса
     * @param urlParameters Параметры для формирования полного URL запроса
     * @return
     */
    private WebTarget createWebTarget(String url, Map<String, String> urlParameters) {

        logger.info("Creating WebTarget from \n url : " + url);
        WebTarget webTarget;
        try {
            if (url.contains("{")) {
                logger.info("url need urlParameters replacement:");
                for (Map.Entry<String, String> entry : urlParameters.entrySet()) {
                    if (entry.getKey() != null && entry.getValue() != null) {
                        logger.info(entry.getKey() + ": " + entry.getValue());
                        if (url.contains("{" + entry.getKey() + "}")) {
                            logger.info("url param {" + entry.getKey() + "} replaced with " + entry.getValue());
                            url = url.replace("{" + entry.getKey() + "}", entry.getValue());
                            urlParameters.remove(entry);
                        }
                    }
                }
            }

            URI u = new URI(baseURI + url);
            webTarget = client.target(u);
            if (urlParameters != null && !urlParameters.isEmpty()) {
                logger.info("with urlParameters : ");
                for (Map.Entry<String, String> entry : urlParameters.entrySet()) {
                    if (entry.getKey() != null && entry.getValue() != null) {
                        logger.info(entry.getKey() + ": " + entry.getValue());

                        webTarget = webTarget.queryParam(entry.getKey(), entry.getValue());
                    }
                }
            }

        } catch (Exception e) {
            throw new IllegalArgumentException("Error occurred while creating the client: " + e);
        }

        return webTarget;
    }


    /**
     * Обработка ответного сообщения
     *
     * @param responseMessage  входящее сообщение c результатами вызова API
     * @param expectedResponse Тип результата
     * @param <TResponse>      Тип результата
     * @return Результат запроса
     */
    private <TResponse> TResponse ReadResponse(Response responseMessage, Class<TResponse> expectedResponse) {
        TResponse response = null;


        if (isSuccessStatusCode(responseMessage.getStatus())) {
            response = responseMessage.readEntity(expectedResponse);
        } else {
            ErrorInfo errorInfo = responseMessage.readEntity(ErrorInfo.class);
            String message = (errorInfo != null ? errorInfo.getMessage() : responseMessage.getStatusInfo().getReasonPhrase());
            logger.info(errorInfo.getMessage() + " | " + responseMessage.getStatusInfo().getReasonPhrase());
            RuntimeException error = CreateError(responseMessage.getStatus(), message);
            if (error != null)
                throw error;
        }
        return response;
    }

    private boolean isSuccessStatusCode(int responseStatus) {
        if (responseStatus == Response.Status.OK.getStatusCode()
                || responseStatus == Response.Status.CREATED.getStatusCode()) {
            return true;
        } else
            return false;
    }


    static RuntimeException CreateError(int statusCode, String message) {
        switch (statusCode) {
            case HttpURLConnection.HTTP_OK:
                return null;
            case HttpURLConnection.HTTP_MULT_CHOICE:
            case HttpURLConnection.HTTP_MOVED_PERM:
            case HttpURLConnection.HTTP_SEE_OTHER:
            case HttpURLConnection.HTTP_NOT_MODIFIED:
            case HttpURLConnection.HTTP_USE_PROXY:
                return new ApiRedirectException(statusCode, message);

            case HttpURLConnection.HTTP_BAD_REQUEST:
                return new ApiArgumentException(statusCode, message);

            case HttpURLConnection.HTTP_UNAUTHORIZED:
            case HttpURLConnection.HTTP_FORBIDDEN:
            case HttpURLConnection.HTTP_PROXY_AUTH:
                return new ApiSecurityException(statusCode, message);

            case HttpURLConnection.HTTP_NOT_FOUND:
                return new ApiResourceNotFoundException(statusCode, message);

            case HttpURLConnection.HTTP_CLIENT_TIMEOUT:
            case HttpURLConnection.HTTP_BAD_METHOD:
            case HttpURLConnection.HTTP_NOT_ACCEPTABLE:
            case HttpURLConnection.HTTP_UNSUPPORTED_TYPE:
            case HttpURLConnection.HTTP_NOT_IMPLEMENTED:
            case HttpURLConnection.HTTP_INTERNAL_ERROR:
                return new ApiException(statusCode, message);
            default:
                return new ApiException(statusCode, message);
        }
    }


    private Client createDefaultClient() throws KeyManagementException, NoSuchAlgorithmException {
        Client client;
        Configuration clientConfig = ClientBuilder.newBuilder().getConfiguration();
        if (baseURI.startsWith("https")) {
            client = createDefaultSLLClient(clientConfig);
        } else {
            client = ClientBuilder.newClient(clientConfig);
        }
        return client;
    }


    private Client createDefaultSLLClient(Configuration clientConfig) throws KeyManagementException, NoSuchAlgorithmException {

        SSLContext ctx = SSLContext.getInstance("TLSv1.2");
        ctx.init(null, null, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(ctx.getSocketFactory());
        Client client = ClientBuilder.newBuilder()
                .sslContext(ctx).withConfig(clientConfig).build();
        client.register(JacksonJsonProvider.class);
        client.register(ApiDefaultLogger.class);
        return client;
    }


}
