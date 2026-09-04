<#import "../layout.ftlh" as layout>
<#import "/spring.ftl" as spring>

<@layout.layout title="Вакансия">

    <div class="row justify-content-center">

        <div class="col-md-8">

            <div class="card shadow-sm">

                <div class="card-body p-4">

                    <h2 class="card-title mb-4">
                        ${vacancy.name!}
                    </h2>


                    <div class="mb-3">
                        <strong>
                            <@spring.message "vacancy.details.company"/>
                        </strong>

                        <a href="/companies/${employer.id}">
                            <#if employer.name??>
                                ${employer.name}
                            <#else>
                                <@spring.message "vacancy.details.company.not.specified"/>
                            </#if>
                        </a>
                    </div>


                    <div class="mb-3">
                        <strong>
                            <@spring.message "vacancy.details.description"/>
                        </strong>

                        <p class="mt-2">
                            <#if vacancy.description??>
                                ${vacancy.description}
                            <#else>
                                <@spring.message "vacancy.details.description.empty"/>
                            </#if>
                        </p>
                    </div>


                    <div class="mb-3">
                        <strong>
                            <@spring.message "vacancy.details.salary"/>
                        </strong>

                        <#if vacancy.salary??>
                            ${vacancy.salary}
                        <#else>
                            <@spring.message "vacancy.details.salary.not.specified"/>
                        </#if>
                    </div>


                    <div class="mb-3">
                        <strong>
                            <@spring.message "vacancy.details.experience"/>
                        </strong>

                        <#if vacancy.expFrom??>
                            ${vacancy.expFrom}
                        <#else>
                            <@spring.message "vacancy.details.experience.not.specified"/>
                        </#if>

                        <#if vacancy.expTo??>
                            - ${vacancy.expTo}
                        </#if>
                    </div>


                    <#if vacancy.createdDate??>

                        <div class="mb-3">
                            <strong>
                                <@spring.message "vacancy.details.publication.date"/>
                            </strong>

                            ${vacancy.createdDate}
                        </div>

                    </#if>


                    <#if vacancy.active?? && vacancy.active>

                        <span class="badge bg-success mb-3">
                            <@spring.message "vacancy.details.active"/>
                        </span>

                    <#else>

                        <span class="badge bg-secondary mb-3">
                            <@spring.message "vacancy.details.inactive"/>
                        </span>

                    </#if>


                    <hr>


                    <div class="d-flex gap-2">

                        <a href="/vacancies"
                           class="btn btn-outline-secondary">
                            <@spring.message "vacancy.details.back"/>
                        </a>

                        <a href="/companies/${employer.id}"
                           class="btn btn-outline-primary">
                            <@spring.message "vacancy.details.other.vacancies"/>
                        </a>

                    </div>

                </div>

            </div>

        </div>

    </div>

</@layout.layout>