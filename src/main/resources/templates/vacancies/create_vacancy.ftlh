<#import "../layout.ftlh" as layout>
<#import "/spring.ftl" as spring>

<@layout.layout title="Создание вакансии">

    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">

            <div class="card shadow-sm">
                <div class="card-body p-4">

                    <h2 class="text-center mb-4">
                        <@spring.message "vacancy.create.title"/>
                    </h2>

                    <form action="/vacancies/create" method="post">

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

                            <@spring.showErrors "<br>" "text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                <@spring.message "vacancy.create.description"/>
                            </label>

                            <@spring.formTextarea
                            "vacancyFormDto.description"
                            'class="form-control" rows="4"'/>

                            <@spring.showErrors "<br>" "text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                <@spring.message "vacancy.create.category"/>
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
                                <@spring.message "vacancy.create.salary"/>
                            </label>

                            <@spring.formInput
                            "vacancyFormDto.salary"
                            'type="number" class="form-control"'/>

                            <@spring.showErrors "<br>" "text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                <@spring.message "vacancy.create.exp.from"/>
                            </label>

                            <@spring.formInput
                            "vacancyFormDto.expFrom"
                            'type="number" class="form-control"'/>

                            <@spring.showErrors "<br>" "text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                <@spring.message "vacancy.create.exp.to"/>
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
                                <@spring.message "vacancy.create.active"/>
                            </label>

                        </div>

                        <button type="submit"
                                class="btn btn-primary">
                            <@spring.message "vacancy.create.button"/>
                        </button>

                        <a href="/profile"
                           class="btn btn-outline-secondary">
                            <@spring.message "vacancy.create.cancel"/>
                        </a>

                    </form>

                </div>
            </div>

        </div>
    </div>

</@layout.layout>