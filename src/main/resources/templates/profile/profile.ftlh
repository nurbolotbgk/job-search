<#import "../layout.ftlh" as layout>
<#import "/spring.ftl" as spring>

<@layout.layout title="Личный кабинет">

    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">

            <div class="card shadow-sm">
                <div class="card-body p-4">

                    <h2 class="text-center mb-4">
                        <@spring.message "profile.title"/>
                    </h2>

                    <div class="text-center mb-4">

                        <#if user.avatar?? && user.avatar?has_content>

                            <img src="/images/${user.avatar}"
                                 alt="<@spring.message "profile.photo.alt"/>"
                                 class="rounded-circle"
                                 width="150"
                                 height="150"
                                 style="object-fit: cover;">

                        <#else>

                            <div class="text-muted">
                                <@spring.message "profile.photo.empty"/>
                            </div>

                        </#if>

                    </div>


                    <div class="mb-3">
                        <strong><@spring.message "profile.name"/></strong>
                        ${user.name!}
                    </div>

                    <div class="mb-3">
                        <strong><@spring.message "profile.surname"/></strong>
                        ${user.surname!}
                    </div>

                    <div class="mb-3">
                        <strong><@spring.message "profile.age"/></strong>
                        ${user.age!}
                    </div>

                    <div class="mb-3">
                        <strong><@spring.message "profile.email"/></strong>
                        ${user.email!}
                    </div>

                    <div class="mb-3">
                        <strong><@spring.message "profile.phone"/></strong>
                        ${user.phoneNumber!}
                    </div>

                    <a href="/profile/edit"
                       class="btn btn-primary">
                        <@spring.message "profile.edit"/>
                    </a>


                    <#if user.roleId == 1>

                        <hr class="my-4">

                        <div class="d-flex justify-content-between align-items-center mb-3">

                            <h4 class="mb-0">
                                <@spring.message "profile.my.resumes"/>
                            </h4>

                            <a href="/resumes/create"
                               class="btn btn-success">
                                <@spring.message "profile.create.resume"/>
                            </a>

                        </div>


                        <#if resumes?? && resumes?size gt 0>

                            <#list resumes as resume>

                                <div class="card mb-3">
                                    <div class="card-body">

                                        <h5 class="card-title">
                                            ${resume.name!}
                                        </h5>

                                        <p class="card-text mb-2">
                                            <@spring.message "profile.desired.salary"/>
                                            ${resume.salary!}
                                        </p>


                                        <#if resume.active?? && resume.active>

                                            <span class="badge bg-success">
                                                <@spring.message "profile.resume.active"/>
                                            </span>

                                        <#else>

                                            <span class="badge bg-secondary">
                                                <@spring.message "profile.resume.inactive"/>
                                            </span>

                                        </#if>


                                        <div class="mt-3">

                                            <a href="/resumes/${resume.id}/edit"
                                               class="btn btn-outline-primary btn-sm">
                                                <@spring.message "profile.resume.edit"/>
                                            </a>

                                        </div>

                                    </div>
                                </div>

                            </#list>


                            <#if totalPages?? && totalPages gt 1>

                                <nav class="mt-4">
                                    <ul class="pagination justify-content-center">

                                        <#if currentPage gt 0>

                                            <li class="page-item">
                                                <a class="page-link"
                                                   href="/profile?page=${currentPage - 1}">
                                                    <@spring.message "pagination.previous"/>
                                                </a>
                                            </li>

                                        </#if>


                                        <#list 0..(totalPages - 1) as i>

                                            <li class="page-item <#if i == currentPage>active</#if>">

                                                <a class="page-link"
                                                   href="/profile?page=${i}">
                                                    ${i + 1}
                                                </a>

                                            </li>

                                        </#list>


                                        <#if currentPage lt totalPages - 1>

                                            <li class="page-item">
                                                <a class="page-link"
                                                   href="/profile?page=${currentPage + 1}">
                                                    <@spring.message "pagination.next"/>
                                                </a>
                                            </li>

                                        </#if>

                                    </ul>
                                </nav>

                            </#if>

                        <#else>

                            <p class="text-muted">
                                <@spring.message "profile.resumes.empty"/>
                            </p>

                        </#if>


                    <#elseif user.roleId == 2>

                        <hr class="my-4">

                        <div class="d-flex justify-content-between align-items-center mb-3">

                            <h4 class="mb-0">
                                <@spring.message "profile.my.vacancies"/>
                            </h4>

                            <a href="/vacancies/create"
                               class="btn btn-success">
                                <@spring.message "profile.create.vacancy"/>
                            </a>

                        </div>


                        <#if vacancies?? && vacancies?size gt 0>

                            <#list vacancies as vacancy>

                                <div class="card mb-3">
                                    <div class="card-body">

                                        <h5 class="card-title">
                                            ${vacancy.name!}
                                        </h5>

                                        <p class="card-text mb-2">
                                            ${vacancy.description!}
                                        </p>

                                        <p class="card-text mb-2">
                                            <strong>
                                                <@spring.message "profile.vacancy.salary"/>
                                            </strong>
                                            ${vacancy.salary!}
                                        </p>

                                        <p class="card-text mb-2">
                                            <strong>
                                                <@spring.message "profile.vacancy.experience"/>
                                            </strong>

                                            ${vacancy.expFrom!}
                                            -
                                            ${vacancy.expTo!}
                                        </p>


                                        <#if vacancy.active?? && vacancy.active>

                                            <span class="badge bg-success">
                                                <@spring.message "profile.vacancy.active"/>
                                            </span>

                                        <#else>

                                            <span class="badge bg-secondary">
                                                <@spring.message "profile.vacancy.inactive"/>
                                            </span>

                                        </#if>


                                        <div class="mt-3">

                                            <a href="/vacancies/${vacancy.id}/edit"
                                               class="btn btn-outline-primary btn-sm">
                                                <@spring.message "profile.vacancy.edit"/>
                                            </a>

                                        </div>

                                    </div>
                                </div>

                            </#list>


                            <#if totalPages?? && totalPages gt 1>

                                <nav class="mt-4">

                                    <ul class="pagination justify-content-center">

                                        <#if currentPage gt 0>

                                            <li class="page-item">
                                                <a class="page-link"
                                                   href="/profile?page=${currentPage - 1}">
                                                    <@spring.message "pagination.previous"/>
                                                </a>
                                            </li>

                                        </#if>


                                        <#list 0..(totalPages - 1) as i>

                                            <li class="page-item <#if i == currentPage>active</#if>">

                                                <a class="page-link"
                                                   href="/profile?page=${i}">
                                                    ${i + 1}
                                                </a>

                                            </li>

                                        </#list>


                                        <#if currentPage lt totalPages - 1>

                                            <li class="page-item">
                                                <a class="page-link"
                                                   href="/profile?page=${currentPage + 1}">
                                                    <@spring.message "pagination.next"/>
                                                </a>
                                            </li>

                                        </#if>

                                    </ul>

                                </nav>

                            </#if>

                        <#else>

                            <p class="text-muted">
                                <@spring.message "profile.vacancies.empty"/>
                            </p>

                        </#if>

                    </#if>

                </div>
            </div>

        </div>
    </div>

</@layout.layout>