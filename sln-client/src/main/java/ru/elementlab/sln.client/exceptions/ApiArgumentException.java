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
 * Описывает ошибку, вызванную неправильными параметрами, указанными при обращении к API
 */
public class ApiArgumentException  extends ApiException{

    public ApiArgumentException(int code, String message) {
        super(code, message);
    }
}
