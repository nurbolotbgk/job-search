<#import "../layout.ftlh" as layout>

<@layout.layout title="Вакансии">

    <div class="d-flex justify-content-between align-items-center mb-4">

        <div>
            <h2 class="mb-1">Вакансии</h2>
            <p class="text-muted mb-0">
                Актуальные предложения работодателей
            </p>
        </div>

        <#if SPRING_SECURITY_CONTEXT??>

            <#assign currentUser =
            SPRING_SECURITY_CONTEXT.authentication.principal>

            <#list currentUser.authorities as auth>

                <#if auth.authority == "ROLE_EMPLOYER">

                    <a href="/vacancies/create"
                       class="btn btn-primary">
                        Создать вакансию
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
                        Сортировка
                    </label>

                    <select name="sort"
                            class="form-select"
                            onchange="this.form.submit()">

                        <option value="date_desc"
                                <#if sort == "date_desc">selected</#if>>
                            По дате — сначала новые
                        </option>

                        <option value="date_asc"
                                <#if sort == "date_asc">selected</#if>>
                            По дате — сначала старые
                        </option>

                        <option value="responses_desc"
                                <#if sort == "responses_desc">selected</#if>>
                            По откликам — сначала больше
                        </option>

                        <option value="responses_asc"
                                <#if sort == "responses_asc">selected</#if>>
                            По откликам — сначала меньше
                        </option>

                    </select>

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
                                    ${vacancy.companyName!"Компания не указана"}
                                </p>

                            </div>

                            <div>

                                <span class="badge text-bg-success fs-6">
                                    ${vacancy.salary!"Зарплата не указана"}
                                </span>

                            </div>

                        </div>

                        <p class="mt-3">
                            ${vacancy.description!"Описание отсутствует"}
                        </p>

                        <p class="text-muted mb-0">
                            Опыт:
                            ${vacancy.experience!"Не указан"}
                        </p>

                    </div>

                    <div class="card-footer bg-white">

                        <a href="/vacancies/${vacancy.id}"
                           class="btn btn-outline-primary">
                            Подробнее
                        </a>

                    </div>

                </div>

            </div>

        <#else>

            <div class="col-12">

                <div class="alert alert-info">
                    Вакансии пока отсутствуют
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
                                Назад
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
                                Вперёд
                            </a>

                        </li>

                    </#if>

                </ul>

            </nav>

        </#if>

    </div>

</@layout.layout>