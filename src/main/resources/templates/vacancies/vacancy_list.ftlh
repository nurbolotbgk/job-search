<#import "../layout.ftlh" as layout>
<#import "/spring.ftl" as spring>

<@layout.layout title="Вакансии">

    <div class="d-flex justify-content-between align-items-center mb-4">

        <div>
            <h2 class="mb-1">
                <@spring.message "vacancies.title"/>
            </h2>

            <p class="text-muted mb-0">
                <@spring.message "vacancies.subtitle"/>
            </p>
        </div>

        <#if SPRING_SECURITY_CONTEXT??>

            <#assign currentUser =
            SPRING_SECURITY_CONTEXT.authentication.principal>

            <#list currentUser.authorities as auth>

                <#if auth.authority == "ROLE_EMPLOYER">

                    <a href="/vacancies/create"
                       class="btn btn-primary">
                        <@spring.message "vacancies.create"/>
                    </a>

                </#if>

            </#list>

        </#if>

    </div>

    <div class="row">

        <form method="get"
              action="/vacancies"
              class="mb-4">

            <div class="row">

                <div class="col-md-4">

                    <label class="form-label">
                        <@spring.message "vacancies.sort"/>
                    </label>

                    <select name="sort"
                            class="form-select"
                            onchange="this.form.submit()">

                        <option value="date_desc"
                                <#if sort == "date_desc">selected</#if>>
                            <@spring.message "vacancies.sort.date.desc"/>
                        </option>

                        <option value="date_asc"
                                <#if sort == "date_asc">selected</#if>>
                            <@spring.message "vacancies.sort.date.asc"/>
                        </option>

                        <option value="responses_desc"
                                <#if sort == "responses_desc">selected</#if>>
                            <@spring.message "vacancies.sort.responses.desc"/>
                        </option>

                        <option value="responses_asc"
                                <#if sort == "responses_asc">selected</#if>>
                            <@spring.message "vacancies.sort.responses.asc"/>
                        </option>

                    </select>

                </div>

            </div>

        </form>

        <form id="vacancy-filter" class="mb-4">

            <div class="row">

                <div class="col-md-4">

                    <label class="form-label">
                        Название вакансии
                    </label>

                    <input type="text"
                           name="name"
                           class="form-control"
                           placeholder="Например: Java">

                </div>

                <div class="col-md-4">

                    <label class="form-label">
                        Минимальная зарплата
                    </label>

                    <input type="number"
                           name="minSalary"
                           class="form-control"
                           min="0">

                </div>

                <div class="col-md-4 d-flex align-items-end">

                    <button type="submit"
                            class="btn btn-primary">
                        Найти
                    </button>

                </div>

            </div>

        </form>

        <#list vacancies as vacancy>

            <div class="col-12 mb-3">

                <div class="card shadow-sm">

                    <div class="card-body">

                        <div class="d-flex justify-content-between">

                            <div>

                                <h4 class="card-title">
                                    ${vacancy.name!}
                                </h4>

                                <p class="text-muted mb-2">
                                    <#if vacancy.companyName??>
                                        ${vacancy.companyName}
                                    <#else>
                                        <@spring.message "vacancies.company.not.specified"/>
                                    </#if>
                                </p>

                            </div>

                            <div>

                                <span class="badge text-bg-success fs-6">
                                    <#if vacancy.salary??>
                                        ${vacancy.salary}
                                    <#else>
                                        <@spring.message "vacancies.salary.not.specified"/>
                                    </#if>
                                </span>

                            </div>

                        </div>

                        <p class="mt-3">
                            <#if vacancy.description??>
                                ${vacancy.description}
                            <#else>
                                <@spring.message "vacancies.description.empty"/>
                            </#if>
                        </p>

                        <p class="text-muted mb-0">
                            <@spring.message "vacancies.experience"/>

                            <#if vacancy.experience??>
                                ${vacancy.experience}
                            <#else>
                                <@spring.message "vacancies.experience.not.specified"/>
                            </#if>
                        </p>

                    </div>

                    <div class="card-footer bg-white">

                        <a href="/vacancies/${vacancy.id}"
                           class="btn btn-outline-primary">
                            <@spring.message "vacancies.details"/>
                        </a>

                    </div>

                </div>

            </div>

        <#else>

            <div class="col-12">

                <div class="alert alert-info">
                    <@spring.message "vacancies.empty"/>
                </div>

            </div>

        </#list>


        <#if totalPages?? && totalPages gt 1>

            <nav class="mt-4">

                <ul class="pagination justify-content-center">

                    <#if currentPage gt 0>

                        <li class="page-item">

                            <a class="page-link"
                               href="/vacancies?page=${currentPage - 1}&sort=${sort}">
                                <@spring.message "pagination.previous"/>
                            </a>

                        </li>

                    </#if>


                    <#list 0..(totalPages - 1) as i>

                        <li class="page-item <#if i == currentPage>active</#if>">

                            <a class="page-link"
                               href="/vacancies?page=${i}&sort=${sort}">
                                ${i + 1}
                            </a>

                        </li>

                    </#list>


                    <#if currentPage lt totalPages - 1>

                        <li class="page-item">

                            <a class="page-link"
                               href="/vacancies?page=${currentPage + 1}&sort=${sort}">
                                <@spring.message "pagination.next"/>
                            </a>

                        </li>

                    </#if>

                </ul>

            </nav>

        </#if>

    </div>

    <script>
        'use strict';

        document.addEventListener('DOMContentLoaded', function () {

            const filterForm =
                document.getElementById('vacancy-filter');

            filterForm.addEventListener('submit', function (event) {

                event.preventDefault();

                const formData =
                    new FormData(filterForm);

                const filter =
                    Object.fromEntries(formData);



                console.log(filter);
            });

        });
    </script>

</@layout.layout>