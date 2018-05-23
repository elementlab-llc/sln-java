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
 * Ошибка, возникающая при отсутствии запрошенного ресурса.
 */
public class ApiResourceNotFoundException extends ApiException {

    public ApiResourceNotFoundException(int code, String message) {
        super(code, message);
    }
}
