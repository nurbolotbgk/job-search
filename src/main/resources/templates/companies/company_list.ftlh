<#import "../layout.ftlh" as layout>
<#import "/spring.ftl" as spring>

<@layout.layout title="Компании">

    <div class="mb-4">

        <h2 class="mb-1">
            <@spring.message "companies.title"/>
        </h2>

        <p class="text-muted mb-0">
            <@spring.message "companies.subtitle"/>
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

                        <a href="/companies/${company.id}"
                           class="btn btn-outline-primary">
                            <@spring.message "companies.details"/>
                        </a>

                    </div>
                </div>

            </div>

        <#else>

            <div class="col-12">
                <div class="alert alert-info">
                    <@spring.message "companies.empty"/>
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
                                <@spring.message "pagination.previous"/>
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
                                <@spring.message "pagination.next"/>
                            </a>

                        </li>

                    </#if>

                </ul>

            </nav>

        </#if>

    </div>

</@layout.layout>