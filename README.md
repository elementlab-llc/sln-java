[api-instance]: https://int.drugscreening.ru/v1
[api-help]: https://int.drugscreening.ru/v1/help
[common-help]: https://github.com/elementlab-llc/sln
[apache-cxf]: https://cxf.apache.org
[jersey]: https://jersey.github.io


# Клиент для REST API Сервиса скрининга лекарственных назначений (СЛН-Сервис)

Здесь представлен пример клиента для СЛН-Сервиса.

Клиент написан с использованием Java 8 и JAX-RS. Таким образом вы сами  выбираете какую реализизацию использовать [Apache CXF][apache-cxf] ,  [Jersey][jersey] и [т.п.]( https://en.wikipedia.org/wiki/Java_API_for_RESTful_Web_Services#Implementations )

## Работа с клиентом

Данный клиент покрывает всю функциональность, предоставляемую СЛН-сервисом:
- Получение типов концептов
- Поиск концептов
- Получение концепта
- Выполнение скрининга

С описанием всех ресурсов REST API, их параметров и результатов можно получить
на [странице справки][api-help].

Общая информация об СЛН-Сервисе доступна в репозитории [sln][common-help].

### Создание клиента с настройками по умолчанию

```java
ClientCredentials credentials = new ClientCredentials("login", "password");
String url = new String("https://testint.drugscreening.ru/v1/");
ApiClient apiClient = new ApiClient(url, credentials, client);
```

### Создание клиента с пользовательскими настройками
Вы можете создать RS-клиент с произвольными настройками, и передать его на вход клиенту API
```java
{
    ClientCredentials credentials = new ClientCredentials("login", "password");
    url = new String("https://testint.drugscreening.ru/v1/");
    SSLContext ctx = SSLContext.getInstance("TLSv1.2");
    ctx.init(null, null, new SecureRandom());
    HttpsURLConnection.setDefaultSSLSocketFactory(ctx.getSocketFactory());

    Client client = ClientBuilder.newBuilder()
                .sslContext(ctx).withConfig(ClientBuilder.newBuilder().getConfiguration()).build();

    client.register(JacksonJsonProvider.class);
    client.register(ApiTestFilter.class);
    client.register(new LoggingFeature());
    ApiClient apiClient = new ApiClient(url, credentials, client);
}
```



Получение и обновление токена не требуется, т.к. клиент сам следит за наличием токена и его получением или обновлением при необходимости.

### Получение списка типов концептов
```java
ConceptType[] result = apiClient.GetConceptTypes();
```

### Получение всех концептов определенного типа

```java
// получение списка концептов типа "DispensableDrug"
ListConceptsResult result = apiClient.listConcepts("DispensableDrug", 1, 20);

```

### Поиск концептов

```java
// среди концептов, типы которых задаются областью поиска
FoundConceptInfo[] result = apiClient.findConcepts("аспирин", SearchMethod.Text, SearchScope.Drugs);
```
или
```java
// среди концептов указанных типов
FoundConceptInfo[] result = apiClient.findConcepts("аспирин", SearchMethod.Text, new String[]{"DispensableDrug", "Substance"});
```

#### Примеры

Поиск среди всех препаратов и субстанций:
```java
FoundConceptInfo[] result = apiClient.findConcepts("аспирин", SearchMethod.Text, SearchScope.DrugsAndSubstances);
```

Поиск среди всех концептов, которые могут быть указаны в качестве аллергенов:
```java
FoundConceptInfo[] result = apiClient.findConcepts("пыль", SearchMethod.Text, SearchScope.Allergens);
```

Поиск диагнозов и заболеваний:
```java
FoundConceptInfo[] result = apiClient.findConcepts("ангина", SearchMethod.Text, SearchScope.Diseases);
```

Кроме поиска по тексту, он может быть выполнен:
- по точному совпадению штрихкода `SearchMethod.Barcode`.
Доступен только для Лекарственных препаратов (DispensableDrug) и БАДов (DietarySupplement).
- по точному совпадению названия `SearchMethod.ExactName`.

Например, следующий код вернет все препараты, штрихкод которых равен 4008500120002:
```java
// поиск по штрихкоду
FoundConceptInfo[] result = apiClient.findConcepts("4008500120002", SearchMethod.Barcode, "DispensableDrug", "Substance");
```

### Получение концепта

```java
ConceptInfo result = apiClient.getConcept("DispensableDrug", "DD0000800");
```

### Скрининг
```java
{
        // выполнение скрининга
        ScreenRequest request = new ScreenRequest();

// запрашивается выполнение трех видов скринингов
        ScreeningType[] screeningTypes = new ScreeningType[]{ScreeningType.DrugDrugInteractions, ScreeningType.AllergicReactions
                ScreeningType.DiseaseContraindications};
        request.setScreeningTypes(ScreeningType.getValues(screeningTypes));
// информация о пациенте
        Patient patient = new Patient();
        {
            patient.setGender(Gender.Female);
            patient.setWeight(new BigDecimal(58.1));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            patient.setBirthDate(format.parse("1995-03-05"));
        }
        request.setPatient(patient);

// список препаратов, субстанций и БАДов
        ArrayList<Drug> drugs = new ArrayList<>();
        {
            Drug firstDrug = new Drug();
            firstDrug.setCode("SUB000007");
            firstDrug.setType("Substance");
            firstDrug.setName("Боластерон");
            firstDrug.setScreen(true);
            drugs.add(firstDrug);
            Drug secondDrug = new Drug();
            secondDrug.setCode("DD0000800");
            secondDrug.setType("DispensableDrug");
            secondDrug.setName("Аспирин табл. 100мг");
            secondDrug.setScreen(true);
            drugs.add(secondDrug);
        }
        request.setDrugs(drugs);
// Список аллергий, имеющихся у пациента.
// В качестве аллергенов могут быть указаны концепты
// следующих типов:
// DispensableDrug, GenericDrug, GenericName, DrugName,
// AllergenClass и ScreenableIngredient

        List<Allergy> allergies = new ArrayList<>();
        {
            Allergy allergy = new Allergy();
            allergy.setType("AllergenClass");
            allergy.setCode("ALGC0029");
            allergy.setScreen(true);
            allergy.setName("Салицилаты");
            allergies.add(allergy);
        }
        request.setAllergies(allergies);
// Список заболеваний пациента.
// На данный момент поддерживаются только
// диагнозы в соответствии с МКБ-10
        List<Disease> diseases = new ArrayList<>();
        {
            Disease disease = new Disease();
            Disease.setPrimary(true);
            disease.setCode("D69.5");
            disease.setType("ICD10CM");
            disease.setName("Вторичная тромбоцитопения");
            disease.setScreen(true);
        }
        diseases.add(disease);

        ScreeningOptions options = new ScreeningOptions();
        {
            DopingAlertsOptions alert = new DopingAlertsOptions();
            alert.setIgnoreCompetitionPeriod(false);
            alert.setIgnoreGender(true);
            options.setDopingAlerts(alert);
            options.setIncludeInsignificantInactiveIngredients(false);
            options.setIncludeMonographs(false);
        }
        request.setOptions(options);

        ScreeningSummary result = apiClient.screen(request);
// получение обобщенного уровня риска для взаимодействия
        InteractionSeverityLevel severity = result.getDrugDrugInteractions().getItems().get(1).getSeverity();
// получение реферата в формате HTML

        String html = result.getAllergicReactions().getItems().get(1).getProfessionalMonograph();

}
```

Для преобразования реферата в HTML используется XSL-шаблон, хранящийся в ресурсах.
```java
MonographHelper monographHelper  = new MonographHelper(customXsltTransform);
String patientMonographHtml = monographHelper.getPatientMonographHtml(patientResult , null);
```


Вместо него можно использовать свой шаблон. Для этого необходимо указать путь до шаблона в конструкторе MonographHelper:
```java
MonographHelper monographHelper  = new MonographHelper(customXsltTransform);
```

#### Внешние справочники

При выполнении скрининга допустимо указывать идентификаторы лекарственных препаратов из "внешних" справочников.
На данный момент ограниченно поддерживаются справочники:
- РЛС (Россия, https://www.rlsnet.ru)
- CBZ (Centralna Baza Zdravil, Словения, http://www.cbz.si)

В связи с тем, что эти справочники ведутся и постоянно обновляются сторонними организациям,
не все их позиции известны сервису. Поэтому для некоторых идентификаторов сервисом может
быть возвращено сообщение `DrugNotFound`.

```java
// Получение типов концептов внешних справочников
ConceptType[] externalTypes = apiClient.GetExternalConceptTypes();

Drug drug = new Drug();
{
    drug.setCode("11835");
    drug.setType("urn:rlsnet:nomen");
    drug.setName("Аспирин® табл. 100 мг (10 бл., 2 кор.), Пр: Bayer Bitterfeld GmbH (Германия)");
    drug.setScreen(true);
}

Drug drug = new Drug();
{
    drug.setCode("048836");
    drug.setType("urn:slovenia:cbz");
    drug.setName("ASPIRIN direkt 500 mg žvečljive tablete");
    drug.setScreen(true);
}
```

Значение поля `Name` не обязательно должно быть точно таким же, как в исходном справочнике.
Тем не менее, следует иметь в виду, что переданное название будет использовано в текстах ответа сервиса.

### Инструкции к лекарственным препаратам

```java
// получение списка инструкций для препарата
Instruction[] instructions = apiClient.listInstructions("DispensableDrug", "DD0009391");

// получение описания инструкции и содержимого в формате XML
String instruction = apiClient.getGetInstruction(("a32d6a19-a1c6-47fd-ad95-1a6e58e92212");

// преобразование содержимого инструкции в формат HTML
String instructionHtml = new InstructionHelper().getContentAsHtml(instruction);
```

Для преобразования инструкции в HTML используется XSL-шаблон, хранящийся в ресурсах.
Вместо него можно использовать свой шаблон. Для этого необходимо указать путь до шаблона в конструкторе InstructionHelper.
```java
InstructionHelper instructionHelper = new InstructionHelper(customXsltTransform);
```

---------------------

### Рекомендации по использованию API
Для улучшения работы сервиса и сокращения времени, требуемого на обработку запроса, рекомендуется придерживаться определенных правил.

#### Количество лекарственных препаратов, аллергий и диагнозов
Так как сервис выполняет скрининг по принципу «каждый с каждым», увеличение количества
входных параметров приводит к значительному росту времени обработки. Например, если
запрошен скрининг взаимодействий препарат-препарат для 10 лекарственных средств, сервисом
будет выполнено 45 проверок, а для 100 препаратов - уже 4950. Если учесть, что каждая
проверка может занимать от 1 до 20 миллисекунд, то время, необходимое только для выполнения
одного этого вида скрининга, может составить около 1 секунды для 10 препаратов и до 2-х
минут для 100 препаратов. Если же запрошено выполнение всех видов скрининга, время обработки
запроса по 100 препаратом приблизится к 10-15 минутам.

#### Изделия медицинского назначения, не являющиеся лекарственными средствами

В список препаратов следует включать только лекарственные средства (имеющие регистрационное
удостоверение лекарственного средства), БАДы и субстанции, которые требуется проверить.
Не следует включать в запрос изделия медицинского назначения, такие, как
«Вата» или «Шприц медицинский».

#### Назначения без расписания приема

Поле `Drug.Schedule` желательно целиком исключить из запроса для тех препаратов,
у которых не определена дата начала приема.

#### Последовательные назначения
Необходимо объединять в одно назначение несколько назначений одного и
того же препарата, если периоды приема следуют друг за другом без перерыва.

Например, если препарат первый раз назначен с 10 по 12 февраля, а затем
с 13 по 16 февраля, в запросе этот препарат следует указывать только один
раз с периодом приема с 10 по 16 февраля.
