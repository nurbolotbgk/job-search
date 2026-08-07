<#import "../layout.ftlh" as layout>

<@layout.layout title="Личный кабинет">

    <div class="bg-white border rounded-4 p-4 shadow-sm">

        <div class="row align-items-center mb-5">

            <div class="col-md-3 text-center mb-4 mb-md-0">

                <#if user.avatar?? && user.avatar?has_content>
                    <img src="/api/images/download?filename=${user.avatar}"
                         alt="Аватар"
                         class="rounded-4 border"
                         style="width: 150px; height: 150px; object-fit: cover;">
                <#else>
                    <div class="rounded-4 border bg-light d-flex align-items-center justify-content-center mx-auto"
                         style="width: 150px; height: 150px; font-size: 48px;">
                        ${user.name?substring(0, 1)}
                    </div>
                </#if>

            </div>

            <div class="col-md-5">

                <h2 class="mb-2">
                    ${user.name} ${user.surname}
                </h2>

                <p class="text-muted mb-1">
                    Возраст: ${user.age}
                </p>

                <p class="text-muted mb-1">
                    ${user.email}
                </p>

                <p class="text-muted mb-3">
                    ${user.phoneNumber}
                </p>

                <a href="/profile/edit"
                   class="btn btn-outline-secondary">
                    Редактировать
                </a>

            </div>

            <div class="col-md-4">

                <div class="d-grid gap-3">

                    <#if user.roleId == 1>
                        <a href="/resumes/create"
                           class="btn btn-primary btn-lg">
                            Создать резюме
                        </a>

                        <a href="#"
                           class="btn btn-outline-primary btn-lg">
                            Мои отклики
                        </a>
                    <#else>
                        <a href="/vacancies/create"
                           class="btn btn-primary btn-lg">
                            Создать вакансию
                        </a>

                        <a href="#"
                           class="btn btn-outline-primary btn-lg">
                            Отклики на вакансии
                        </a>
                    </#if>

                </div>

            </div>

        </div>

        <hr class="mb-4">

        <#if user.roleId == 1>

            <div class="d-flex justify-content-between align-items-center mb-3">
                <h3 class="mb-0">Мои резюме</h3>

                <a href="/resumes"
                   class="btn btn-sm btn-outline-secondary">
                    Все резюме
                </a>
            </div>

            <div class="alert alert-light border">
                У вас пока нет резюме
            </div>

        <#else>

            <div class="d-flex justify-content-between align-items-center mb-3">
                <h3 class="mb-0">Мои вакансии</h3>

                <a href="/vacancies"
                   class="btn btn-sm btn-outline-secondary">
                    Все вакансии
                </a>
            </div>

            <div class="alert alert-light border">
                У вас пока нет вакансий
            </div>

        </#if>

    </div>

</@layout.layout>