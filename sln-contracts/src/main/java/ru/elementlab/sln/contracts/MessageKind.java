/*
 *
 * Module Name:  sln-contracts
 * Project:      sln
 *
 * Copyright (c) Element Lab LLC
 *
 *  THIS CODE AND INFORMATION IS PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 *  EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A PARTICULAR PURPOSE.
 *
 */

package ru.elementlab.sln.contracts;

/**
 * Тип сообщения
 */
public enum MessageKind {

    /**
     * Информация
     */
    Information,
    /**
     * Предупреждение
     */
    Warning,
    /**
     * Ошибка
     */
    Error,
    /**
     * Критическая ошибка
     */
    Critical,
}
