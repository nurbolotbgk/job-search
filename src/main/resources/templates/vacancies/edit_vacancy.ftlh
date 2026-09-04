<#import "../layout.ftlh" as layout>
<#import "/spring.ftl" as spring>

<@layout.layout title="Редактирование вакансии">

    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">

            <div class="card shadow-sm">
                <div class="card-body p-4">

                    <h2 class="text-center mb-4">
                        <@spring.message "vacancy.edit.title"/>
                    </h2>

                    <form action="/vacancies/${vacancyId}/edit" method="post">

                        <#if _csrf??>
                            <input type="hidden"
                                   name="${_csrf.parameterName}"
                                   value="${_csrf.token}">
                        </#if>

                        <div class="mb-3">
                            <label class="form-label">
                                <@spring.message "vacancy.create.name"/>
                            </label>

                            <@spring.formInput
                            "vacancyFormDto.name"
                            'class="form-control"'/>

                            <@spring.showErrors
                            "<br>"
                            "text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                <@spring.message "vacancy.create.description"/>
                            </label>

                            <@spring.formTextarea
                            "vacancyFormDto.description"
                            'class="form-control" rows="4"'/>

                            <@spring.showErrors
                            "<br>"
                            "text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                <@spring.message "vacancy.create.category"/>
                            </label>

                            <select class="form-select"
                                    name="categoryId">

                                <#list categories as category>

                                    <option value="${category.id}"
                                            <#if vacancyFormDto.categoryId?? &&
                                    vacancyFormDto.categoryId == category.id>
                                    selected
                                            </#if>>

                                        ${category.name}

                                    </option>

                                </#list>

                            </select>

                            <@spring.bind "vacancyFormDto.categoryId"/>

                            <@spring.showErrors
                            "<br>"
                            "text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                <@spring.message "vacancy.create.salary"/>
                            </label>

                            <@spring.formInput
                            "vacancyFormDto.salary"
                            'type="number" step="0.01" class="form-control"'/>

                            <@spring.showErrors
                            "<br>"
                            "text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                <@spring.message "vacancy.edit.exp.from"/>
                            </label>

                            <@spring.formInput
                            "vacancyFormDto.expFrom"
                            'type="number" class="form-control"'/>

                            <@spring.showErrors
                            "<br>"
                            "text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                <@spring.message "vacancy.edit.exp.to"/>
                            </label>

                            <@spring.formInput
                            "vacancyFormDto.expTo"
                            'type="number" class="form-control"'/>

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
                                    <#if vacancyFormDto.active?? && vacancyFormDto.active>
                            checked
                                    </#if>>

                            <label class="form-check-label"
                                   for="active">
                                <@spring.message "vacancy.create.active"/>
                            </label>

                        </div>

                        <div class="d-flex gap-2">

                            <button type="submit"
                                    class="btn btn-primary">
                                <@spring.message "vacancy.edit.save"/>
                            </button>

                            <a href="/profile"
                               class="btn btn-outline-secondary">
                                <@spring.message "vacancy.create.cancel"/>
                            </a>

                        </div>

                    </form>

                </div>
            </div>

        </div>
    </div>

</@layout.layout>