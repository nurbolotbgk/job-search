<#import "../layout.ftlh" as layout>
<#import "/spring.ftl" as spring>

<@layout.layout title="Резюме">

    <div class="mb-4">

        <h2 class="mb-1">
            <@spring.message "resumes.title"/>
        </h2>

        <p class="text-muted mb-0">
            <@spring.message "resumes.subtitle"/>
        </p>

    </div>


    <div class="row">

        <#list resumes as resume>

            <div class="col-12 mb-3">

                <div class="card shadow-sm">

                    <div class="card-body">

                        <div class="d-flex justify-content-between align-items-start">

                            <div>

                                <h5 class="card-title mb-3">
                                    ${resume.name!}
                                </h5>

                                <p class="mb-2">
                                    <strong>
                                        <@spring.message "resumes.category"/>
                                    </strong>

                                    <#if resume.categoryName??>
                                        ${resume.categoryName}
                                    <#else>
                                        <@spring.message "resumes.not.specified"/>
                                    </#if>
                                </p>

                                <p class="mb-2">
                                    <strong>
                                        <@spring.message "resumes.salary"/>
                                    </strong>

                                    <#if resume.salary??>
                                        ${resume.salary}
                                    <#else>
                                        <@spring.message "resumes.not.specified"/>
                                    </#if>
                                </p>

                                <#if resume.active?? && resume.active>

                                    <span class="badge bg-success">
                                        <@spring.message "resumes.active"/>
                                    </span>

                                <#else>

                                    <span class="badge bg-secondary">
                                        <@spring.message "resumes.inactive"/>
                                    </span>

                                </#if>

                            </div>


                            <div>

                                <a href="/resumes/${resume.id}"
                                   class="btn btn-outline-primary">
                                    <@spring.message "resumes.details"/>
                                </a>

                            </div>

                        </div>

                    </div>

                </div>

            </div>

        <#else>

            <div class="col-12">

                <div class="alert alert-info">
                    <@spring.message "resumes.empty"/>
                </div>

            </div>

        </#list>


        <#if totalPages?? && totalPages gt 1>

            <nav class="mt-4">

                <ul class="pagination justify-content-center">

                    <#if currentPage gt 0>

                        <li class="page-item">

                            <a class="page-link"
                               href="/resumes?page=${currentPage - 1}">
                                <@spring.message "pagination.previous"/>
                            </a>

                        </li>

                    </#if>


                    <#list 0..(totalPages - 1) as i>

                        <li class="page-item <#if i == currentPage>active</#if>">

                            <a class="page-link"
                               href="/resumes?page=${i}">
                                ${i + 1}
                            </a>

                        </li>

                    </#list>


                    <#if currentPage lt totalPages - 1>

                        <li class="page-item">

                            <a class="page-link"
                               href="/resumes?page=${currentPage + 1}">
                                <@spring.message "pagination.next"/>
                            </a>

                        </li>

                    </#if>

                </ul>

            </nav>

        </#if>

    </div>

</@layout.layout>