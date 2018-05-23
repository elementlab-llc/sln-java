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

package ru.elementlab.sln.client.exceptions;

/**
 * Базовый класс для всех ошибок, связанных с обращениями к API.
 */
public class ApiException extends RuntimeException {

    public int statusCode;

    public ApiException(int code, String message) {
        super(message);
        this.statusCode = code;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
