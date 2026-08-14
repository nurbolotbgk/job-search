<#import "../layout.ftlh" as layout>
<#import "/spring.ftl" as spring>

<@layout.layout title="Создание резюме">

    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">

            <div class="card shadow-sm">
                <div class="card-body p-4">

                    <h2 class="text-center mb-4">
                        Создание резюме
                    </h2>

                    <form action="/resumes/create" method="post">

                        <div class="mb-3">
                            <label class="form-label">
                                Название резюме
                            </label>

                            <@spring.formInput
                            "resumeFormDto.name"
                            'class="form-control"'/>

                            <@spring.showErrors
                            "<br>"
                            "text-danger"/>
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

                            <@spring.bind "resumeFormDto.categoryId"/>

                            <@spring.showErrors
                            "<br>"
                            "text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                Желаемая зарплата
                            </label>

                            <@spring.formInput
                            "resumeFormDto.salary"
                            'type="number" step="0.01" class="form-control"'/>

                            <@spring.showErrors
                            "<br>"
                            "text-danger"/>
                        </div>

                        <div class="form-check mb-4">

                            <input type="checkbox"
                                   class="form-check-input"
                                   id="active"
                                   name="active"
                                   value="true"
                                    <#if resumeFormDto.active?? && resumeFormDto.active>
                            checked
                                    </#if>>

                            <label class="form-check-label"
                                   for="active">
                                Активное резюме
                            </label>

                        </div>

                        <div class="d-flex gap-2">

                            <button type="submit"
                                    class="btn btn-primary">
                                Создать резюме
                            </button>

                            <a href="/resumes"
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