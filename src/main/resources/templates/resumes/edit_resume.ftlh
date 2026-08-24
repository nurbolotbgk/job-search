<#import "../layout.ftlh" as layout>
<#import "/spring.ftl" as spring>

<@layout.layout title="Редактирование резюме">

    <div class="row justify-content-center">
        <div class="col-md-10 col-lg-8">

            <div class="card shadow-sm">
                <div class="card-body p-4">

                    <h2 class="text-center mb-4">
                        Редактирование резюме
                    </h2>

                    <form action="/resumes/${resumeId}/edit"
                          method="post">

                        <#if _csrf??>
                            <input type="hidden"
                                   name="${_csrf.parameterName}"
                                   value="${_csrf.token}">
                        </#if>


                        <h4 class="mb-3">
                            Основная информация
                        </h4>


                        <div class="mb-3">

                            <label class="form-label">
                                Название резюме
                            </label>

                            <@spring.formInput
                            "resumeFormDto.name"
                            'class="form-control"'
                            />

                            <@spring.showErrors
                            "<br>"
                            "text-danger"
                            />

                        </div>


                        <div class="mb-3">

                            <label class="form-label">
                                Категория
                            </label>

                            <select class="form-select"
                                    name="categoryId">

                                <#list categories as category>

                                    <option value="${category.id}"
                                            <#if resumeFormDto.categoryId??
                                    && resumeFormDto.categoryId == category.id>
                                    selected
                                            </#if>>

                                        ${category.name}

                                    </option>

                                </#list>

                            </select>

                            <@spring.bind
                            "resumeFormDto.categoryId"
                            />

                            <@spring.showErrors
                            "<br>"
                            "text-danger"
                            />

                        </div>


                        <div class="mb-3">

                            <label class="form-label">
                                Желаемая зарплата
                            </label>

                            <@spring.formInput
                            "resumeFormDto.salary"
                            'type="number"
                                 step="0.01"
                                 class="form-control"'
                            />

                            <@spring.showErrors
                            "<br>"
                            "text-danger"
                            />

                        </div>


                        <div class="form-check mb-4">

                            <input type="checkbox"
                                   class="form-check-input"
                                   id="active"
                                   name="active"
                                   value="true"

                                    <#if resumeFormDto.active??
                                    && resumeFormDto.active>
                                        checked
                                    </#if>
                            >

                            <label class="form-check-label"
                                   for="active">
                                Активное резюме
                            </label>

                        </div>


                        <hr class="my-4">


                        <h4 class="mb-3">
                            Опыт работы
                        </h4>


                        <#list resumeFormDto.workExperiences as workExperience>

                            <div class="card mb-4">
                                <div class="card-body">

                                    <h5 class="mb-3">
                                        Опыт работы №${workExperience_index + 1}
                                    </h5>


                                    <input type="hidden"
                                           name="workExperiences[${workExperience_index}].id"
                                           value="${workExperience.id!}">


                                    <div class="mb-3">

                                        <label class="form-label">
                                            Количество лет
                                        </label>

                                        <@spring.formInput
                                        "resumeFormDto.workExperiences[${workExperience_index}].years"
                                        'type="number"
                                             min="0"
                                             class="form-control"'
                                        />

                                        <@spring.showErrors
                                        "<br>"
                                        "text-danger"
                                        />

                                    </div>


                                    <div class="mb-3">

                                        <label class="form-label">
                                            Название компании
                                        </label>

                                        <@spring.formInput
                                        "resumeFormDto.workExperiences[${workExperience_index}].companyName"
                                        'class="form-control"'
                                        />

                                        <@spring.showErrors
                                        "<br>"
                                        "text-danger"
                                        />

                                    </div>


                                    <div class="mb-3">

                                        <label class="form-label">
                                            Должность
                                        </label>

                                        <@spring.formInput
                                        "resumeFormDto.workExperiences[${workExperience_index}].position"
                                        'class="form-control"'
                                        />

                                        <@spring.showErrors
                                        "<br>"
                                        "text-danger"
                                        />

                                    </div>


                                    <div class="mb-3">

                                        <label class="form-label">
                                            Обязанности
                                        </label>

                                        <@spring.formTextarea
                                        "resumeFormDto.workExperiences[${workExperience_index}].responsibilities"
                                        'class="form-control"
                                             rows="3"'
                                        />

                                    </div>

                                </div>
                            </div>

                        </#list>


                        <button type="submit"
                                formaction="/resumes/${resumeId}/edit/add-experience"
                                formnovalidate
                                class="btn btn-outline-primary mb-4">

                            + Добавить опыт работы

                        </button>


                        <hr class="my-4">


                        <h4 class="mb-3">
                            Образование
                        </h4>


                        <#list resumeFormDto.educations as education>

                            <div class="card mb-4">
                                <div class="card-body">

                                    <h5 class="mb-3">
                                        Образование №${education_index + 1}
                                    </h5>


                                    <input type="hidden"
                                           name="educations[${education_index}].id"
                                           value="${education.id!}">


                                    <div class="mb-3">

                                        <label class="form-label">
                                            Учебное заведение
                                        </label>

                                        <@spring.formInput
                                        "resumeFormDto.educations[${education_index}].institution"
                                        'class="form-control"'
                                        />

                                        <@spring.showErrors
                                        "<br>"
                                        "text-danger"
                                        />

                                    </div>


                                    <div class="mb-3">

                                        <label class="form-label">
                                            Программа
                                        </label>

                                        <@spring.formInput
                                        "resumeFormDto.educations[${education_index}].program"
                                        'class="form-control"'
                                        />

                                    </div>


                                    <div class="mb-3">

                                        <label class="form-label">
                                            Дата начала обучения
                                        </label>

                                        <@spring.formInput
                                        "resumeFormDto.educations[${education_index}].startDate"
                                        'type="date"
                                             class="form-control"'
                                        />

                                        <div class="form-text">
                                            Формат: день, месяц, год
                                        </div>

                                    </div>


                                    <div class="mb-3">

                                        <label class="form-label">
                                            Дата окончания обучения
                                        </label>

                                        <@spring.formInput
                                        "resumeFormDto.educations[${education_index}].endDate"
                                        'type="date"
                                             class="form-control"'
                                        />

                                        <div class="form-text">
                                            Формат: день, месяц, год
                                        </div>

                                        <@spring.bind
                                        "resumeFormDto.educations[${education_index}].datesValid"
                                        />

                                        <@spring.showErrors
                                        "<br>"
                                        "text-danger"
                                        />

                                    </div>


                                    <div class="mb-3">

                                        <label class="form-label">
                                            Степень
                                        </label>

                                        <@spring.formInput
                                        "resumeFormDto.educations[${education_index}].degree"
                                        'class="form-control"'
                                        />

                                    </div>

                                </div>
                            </div>

                        </#list>


                        <button type="submit"
                                formaction="/resumes/${resumeId}/edit/add-education"
                                formnovalidate
                                class="btn btn-outline-primary mb-4">

                            + Добавить образование

                        </button>


                        <hr class="my-4">


                        <h4 class="mb-3">
                            Контакты
                        </h4>


                        <#list resumeFormDto.contacts as contact>

                            <div class="card mb-4">
                                <div class="card-body">

                                    <h5 class="mb-3">
                                        Контакт №${contact_index + 1}
                                    </h5>


                                    <input type="hidden"
                                           name="contacts[${contact_index}].id"
                                           value="${contact.id!}">


                                    <div class="mb-3">

                                        <label class="form-label">
                                            Тип контакта
                                        </label>

                                        <select name="contacts[${contact_index}].typeId"
                                                class="form-select">

                                            <option value="1"
                                                    <#if contact.typeId??
                                            && contact.typeId == 1>
                                            selected
                                                    </#if>>
                                                Telegram
                                            </option>

                                            <option value="2"
                                                    <#if contact.typeId??
                                            && contact.typeId == 2>
                                            selected
                                                    </#if>>
                                                Mail
                                            </option>

                                            <option value="3"
                                                    <#if contact.typeId??
                                            && contact.typeId == 3>
                                            selected
                                                    </#if>>
                                                Facebook
                                            </option>

                                            <option value="4"
                                                    <#if contact.typeId??
                                            && contact.typeId == 4>
                                            selected
                                                    </#if>>
                                                Whatsapp
                                            </option>

                                            <option value="5"
                                                    <#if contact.typeId??
                                            && contact.typeId == 5>
                                            selected
                                                    </#if>>
                                                Linkedin
                                            </option>

                                        </select>

                                        <@spring.bind
                                        "resumeFormDto.contacts[${contact_index}].typeId"
                                        />

                                        <@spring.showErrors
                                        "<br>"
                                        "text-danger"
                                        />

                                    </div>


                                    <div class="mb-3">

                                        <label class="form-label">
                                            Контактные данные
                                        </label>

                                        <@spring.formInput
                                        "resumeFormDto.contacts[${contact_index}].contactValue"
                                        'class="form-control"'
                                        />

                                        <@spring.showErrors
                                        "<br>"
                                        "text-danger"
                                        />

                                    </div>

                                </div>
                            </div>

                        </#list>


                        <button type="submit"
                                formaction="/resumes/${resumeId}/edit/add-contact"
                                formnovalidate
                                class="btn btn-outline-primary mb-4">

                            + Добавить контакт

                        </button>


                        <hr class="my-4">


                        <div class="d-flex gap-2">

                            <button type="submit"
                                    class="btn btn-primary">
                                Сохранить
                            </button>

                            <a href="/profile"
                               class="btn btn-outline-secondary">
                                Отмена
                            </a>

                        </div>


                    </form>

                </div>
            </div>

        </div>
    </div>

</@layout.layout>