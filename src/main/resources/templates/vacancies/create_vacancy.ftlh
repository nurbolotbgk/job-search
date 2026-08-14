<#import "../layout.ftlh" as layout>
<#import "/spring.ftl" as spring>

<@layout.layout title="Создание вакансии">

    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">

            <div class="card shadow-sm">
                <div class="card-body p-4">

                    <h2 class="text-center mb-4">
                        Создание вакансии
                    </h2>

                    <form action="/vacancies/create" method="post">

                        <div class="mb-3">
                            <label class="form-label">
                                Название вакансии
                            </label>

                            <@spring.formInput
                            "vacancyFormDto.name"
                            'class="form-control"'/>

                            <@spring.showErrors "<br>" "text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                Описание
                            </label>

                            <@spring.formTextarea
                            "vacancyFormDto.description"
                            'class="form-control" rows="4"'/>

                            <@spring.showErrors "<br>" "text-danger"/>
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

                            <@spring.bind "vacancyFormDto.categoryId"/>
                            <@spring.showErrors "<br>" "text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                Зарплата
                            </label>

                            <@spring.formInput
                            "vacancyFormDto.salary"
                            'type="number" class="form-control"'/>

                            <@spring.showErrors "<br>" "text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                Опыт от
                            </label>

                            <@spring.formInput
                            "vacancyFormDto.expFrom"
                            'type="number" class="form-control"'/>

                            <@spring.showErrors "<br>" "text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                Опыт до
                            </label>

                            <@spring.formInput
                            "vacancyFormDto.expTo"
                            'type="number" class="form-control"'/>

                            <@spring.showErrors "<br>" "text-danger"/>
                        </div>

                        <div class="form-check mb-4">

                            <input type="checkbox"
                                   class="form-check-input"
                                   id="active"
                                   name="active"
                                   value="true"
                                   checked>

                            <label class="form-check-label"
                                   for="active">
                                Активная вакансия
                            </label>

                        </div>

                        <button type="submit"
                                class="btn btn-primary">
                            Создать вакансию
                        </button>

                        <a href="/profile"
                           class="btn btn-outline-secondary">
                            Отмена
                        </a>

                    </form>

                </div>
            </div>

        </div>
    </div>

</@layout.layout>