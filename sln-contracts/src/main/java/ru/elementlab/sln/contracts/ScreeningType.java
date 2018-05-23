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
 * Перечисляет все возможные виды скрининга.
 */
public enum ScreeningType {


    /**
     * Скрининг взаимодействий лекарственных средств друг с другом.
     */
    DrugDrugInteractions,

    /**
     * Скрининг взаимодействий лекарственных средств с пищей.
     */
    DrugFoodInteractions,
    /**
     * Скрининг взаимодействий лекарственных средств с алкоголем.
     */
    DrugAlcoholInteractions,

    /**
     * Скрининг возможных аллергических реакций.
     */
    AllergicReactions,
    /**
     * Скрининг на наличие дупликативной терапии.
     */
    DuplicateTherapy,
    /**
     * Скрининг противопоказаний по возрастным характеристикам.
     */
    AgeContraindications,

    /**
     * Скрининг противопоказаний по половой принадлежности.
     */
    GenderContraindications,
    /**
     * Скрининг противопоказаний при  лактации.
     */
    LactationContraindications,
    /**
     * Скрининг противопоказаний при беременности
     */
    PregnancyContraindications,
    /**
     * Скрининг противопоказаний при диагнозе
     */
    DiseaseContraindications,
    /**
     * Допинг-контроль
     */
    DopingAlerts,
    /**
     * Фармакогенетический скрининг
     */
    GeneticTesting,
    /**
     * Иммуносупрессия (в разработке, доступен только на тестовом сервере)
     */
    Immunosuppression;



    public static String getValues(){
        String result = new String("");
        for( ScreeningType screeningType : ScreeningType.values()){
            result+= (result.equals("") ? screeningType : ","+screeningType);
        }
        return result;
    }

    public static String getValues(ScreeningType[] screaning){
        String result = new String("");
        for( ScreeningType screeningType : screaning){
            result+= (result.equals("") ? screeningType : ","+screeningType);
        }
        return result;
    }




}