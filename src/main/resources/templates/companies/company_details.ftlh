<#import "../layout.ftlh" as layout>
<#import "/spring.ftl" as spring>

<@layout.layout title="Компания">

    <div class="mb-4">

        <h2>
            <#if company.name??>
                ${company.name}
            <#else>
                <@spring.message "company.details.default.name"/>
            </#if>
        </h2>

        <p class="mb-2">
            <strong>
                <@spring.message "companies.email"/>
            </strong>
            ${company.email!}
        </p>

        <p class="mb-2">
            <strong>
                <@spring.message "companies.phone"/>
            </strong>

            <#if company.phoneNumber??>
                ${company.phoneNumber}
            <#else>
                <@spring.message "companies.phone.not.specified"/>
            </#if>
        </p>

    </div>


    <hr>

    <h3 class="mb-4">
        <@spring.message "company.details.vacancies"/>
    </h3>


    <#if vacancies?? && vacancies?size gt 0>

        <#list vacancies as vacancy>

            <div class="card mb-3">

                <div class="card-body">

                    <h5 class="card-title">
                        ${vacancy.name!}
                    </h5>

                    <p>
                        <#if vacancy.description??>
                            ${vacancy.description}
                        <#else>
                            <@spring.message "vacancy.details.description.empty"/>
                        </#if>
                    </p>

                    <p>
                        <strong>
                            <@spring.message "vacancy.details.salary"/>
                        </strong>

                        <#if vacancy.salary??>
                            ${vacancy.salary}
                        <#else>
                            <@spring.message "vacancy.details.salary.not.specified"/>
                        </#if>
                    </p>

                    <a href="/vacancies/${vacancy.id}"
                       class="btn btn-outline-primary">
                        <@spring.message "vacancies.details"/>
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
                                <@spring.message "pagination.previous"/>
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
                                <@spring.message "pagination.next"/>
                            </a>

                        </li>

                    </#if>

                </ul>

            </nav>

        </#if>


    <#else>

        <div class="alert alert-info">
            <@spring.message "company.details.no.vacancies"/>
        </div>

    </#if>

</@layout.layout>