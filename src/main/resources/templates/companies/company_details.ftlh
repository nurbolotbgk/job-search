<#import "../layout.ftlh" as layout>

<@layout.layout title="Компания">

    <div class="mb-4">

        <h2>
            ${company.name!"Компания"}
        </h2>

        <p class="mb-2">
            <strong>Email:</strong>
            ${company.email!}
        </p>

        <p class="mb-2">
            <strong>Телефон:</strong>
            ${company.phoneNumber!"Не указан"}
        </p>

    </div>


    <hr>

    <h3 class="mb-4">
        Вакансии компании
    </h3>


    <#if vacancies?? && vacancies?size gt 0>

        <#list vacancies as vacancy>

            <div class="card mb-3">

                <div class="card-body">

                    <h5 class="card-title">
                        ${vacancy.name!}
                    </h5>

                    <p>
                        ${vacancy.description!"Описание отсутствует"}
                    </p>

                    <p>
                        <strong>Зарплата:</strong>
                        ${vacancy.salary!"Не указана"}
                    </p>

                    <a href="/vacancies/${vacancy.id}"
                       class="btn btn-outline-primary">
                        Подробнее
                    </a>

                </div>

            </div>

        </#list>


        <#if totalPages?? && totalPages gt 1>

            <nav class="mt-4">

                <ul class="pagination justify-content-center">

                    <#if currentPage gt 0>

                        <li class="page-item">

                            <a class="page-link"
                               href="/companies/${company.id}?page=${currentPage - 1}">
                                Назад
                            </a>

                        </li>

                    </#if>


                    <#list 0..(totalPages - 1) as i>

                        <li class="page-item <#if i == currentPage>active</#if>">

                            <a class="page-link"
                               href="/companies/${company.id}?page=${i}">
                                ${i + 1}
                            </a>

                        </li>

                    </#list>


                    <#if currentPage lt totalPages - 1>

                        <li class="page-item">

                            <a class="page-link"
                               href="/companies/${company.id}?page=${currentPage + 1}">
                                Вперёд
                            </a>

                        </li>

                    </#if>

                </ul>

            </nav>

        </#if>


    <#else>

        <div class="alert alert-info">
            У компании пока нет вакансий.
        </div>

    </#if>

</@layout.layout>