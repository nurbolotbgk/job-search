<#import "../layout.ftlh" as layout>

<@layout.layout title="Компании">

    <div class="mb-4">
        <h2 class="mb-1">Компании</h2>
        <p class="text-muted mb-0">
            Список работодателей
        </p>
    </div>

    <div class="row">

        <#list companies as company>

            <div class="col-12 mb-3">

                <div class="card shadow-sm">
                    <div class="card-body">

                        <h5 class="card-title">
                            ${company.name!}
                        </h5>

                        <p class="mb-2">
                            <strong>Email:</strong>
                            ${company.email!}
                        </p>

                        <p class="mb-2">
                            <strong>Телефон:</strong>
                            ${company.phoneNumber!"Не указан"}
                        </p>

                        <a href="/companies/${company.id}"
                           class="btn btn-outline-primary">
                            Подробнее
                        </a>

                    </div>
                </div>

            </div>

        <#else>

            <div class="col-12">
                <div class="alert alert-info">
                    Компании пока отсутствуют
                </div>
            </div>

        </#list>

        <#if totalPages?? && totalPages gt 1>

            <nav class="mt-4">
                <ul class="pagination justify-content-center">

                    <#if currentPage gt 0>
                        <li class="page-item">
                            <a class="page-link"
                               href="/companies?page=${currentPage - 1}">
                                Назад
                            </a>
                        </li>
                    </#if>

                    <#list 0..(totalPages - 1) as i>
                        <li class="page-item <#if i == currentPage>active</#if>">

                            <a class="page-link"
                               href="/companies?page=${i}">
                                ${i + 1}
                            </a>

                        </li>
                    </#list>

                    <#if currentPage lt totalPages - 1>
                        <li class="page-item">
                            <a class="page-link"
                               href="/companies?page=${currentPage + 1}">
                                Вперёд
                            </a>
                        </li>
                    </#if>

                </ul>
            </nav>

        </#if>

    </div>

</@layout.layout>