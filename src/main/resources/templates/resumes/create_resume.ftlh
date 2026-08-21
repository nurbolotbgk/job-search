<#import "../layout.ftlh" as layout>
<#import "/spring.ftl" as spring>

<@layout.layout title="Создание резюме">

    <div class="row justify-content-center">

        <div class="col-md-10 col-lg-8">

            <div class="card shadow-sm">

                <div class="card-body p-4">

                    <h2 class="text-center mb-4">
                        Создание резюме
                    </h2>


                    <form action="/resumes/create"
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

                                    <option value="${category.id}">
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


                        <div class="mb-3">

                            <label class="form-label">
                                Количество лет
                            </label>

                            <input type="number"
                                   name="workExperience.years"
                                   class="form-control"
                                   min="0">

                        </div>


                        <div class="mb-3">

                            <label class="form-label">
                                Название компании
                            </label>

                            <input type="text"
                                   name="workExperience.companyName"
                                   class="form-control">

                        </div>


                        <div class="mb-3">

                            <label class="form-label">
                                Должность
                            </label>

                            <input type="text"
                                   name="workExperience.position"
                                   class="form-control">

                        </div>


                        <div class="mb-3">

                            <label class="form-label">
                                Обязанности
                            </label>

                            <textarea
                                    name="workExperience.responsibilities"
                                    class="form-control"
                                    rows="3">
                        </textarea>

                        </div>



                        <hr class="my-4">


                        <h4 class="mb-3">
                            Образование
                        </h4>


                        <div class="mb-3">

                            <label class="form-label">
                                Учебное заведение
                            </label>

                            <input type="text"
                                   name="education.institution"
                                   class="form-control">

                        </div>


                        <div class="mb-3">

                            <label class="form-label">
                                Программа
                            </label>

                            <input type="text"
                                   name="education.program"
                                   class="form-control">

                        </div>


                        <div class="mb-3">

                            <label class="form-label">
                                Дата начала обучения
                            </label>

                            <@spring.formInput
                            "resumeFormDto.education.startDate"
                            'type="date"
         class="form-control"'
                            />

                        </div>


                        <div class="mb-3">

                            <label class="form-label">
                                Дата окончания обучения
                            </label>

                            <@spring.formInput
                            "resumeFormDto.education.endDate"
                            'type="date"
         class="form-control"'
                            />

                            <@spring.bind "resumeFormDto.education.datesValid"/>

                            <@spring.showErrors
                            "<br>"
                            "text-danger"
                            />

                        </div>


                        <div class="mb-3">

                            <label class="form-label">
                                Степень
                            </label>

                            <input type="text"
                                   name="education.degree"
                                   class="form-control">

                        </div>



                        <hr class="my-4">


                        <h4 class="mb-3">
                            Контакт
                        </h4>


                        <div class="mb-3">

                            <label class="form-label">
                                Тип контакта
                            </label>

                            <select name="contact.typeId"
                                    class="form-select">

                                <option value="1">
                                    Telegram
                                </option>

                                <option value="2">
                                    Mail
                                </option>

                                <option value="3">
                                    Facebook
                                </option>

                                <option value="4">
                                    Whatsapp
                                </option>

                                <option value="5">
                                    Linkedin
                                </option>

                            </select>

                        </div>


                        <div class="mb-4">

                            <label class="form-label">
                                Контактные данные
                            </label>

                            <input type="text"
                                   name="contact.contactValue"
                                   class="form-control">

                        </div>



                        <div class="d-flex gap-2">

                            <button type="submit"
                                    class="btn btn-primary">
                                Создать резюме
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