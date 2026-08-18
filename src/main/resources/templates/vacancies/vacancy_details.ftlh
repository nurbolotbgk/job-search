<#import "../layout.ftlh" as layout>

<@layout.layout title="Вакансия">

    <div class="row justify-content-center">

        <div class="col-md-8">

            <div class="card shadow-sm">

                <div class="card-body p-4">

                    <h2 class="card-title mb-4">
                        ${vacancy.name!}
                    </h2>


                    <div class="mb-3">
                        <strong>Компания:</strong>

                        <a href="/companies/${employer.id}">
                            ${employer.name!"Не указана"}
                        </a>
                    </div>


                    <div class="mb-3">
                        <strong>Описание:</strong>

                        <p class="mt-2">
                            ${vacancy.description!"Описание отсутствует"}
                        </p>
                    </div>


                    <div class="mb-3">
                        <strong>Зарплата:</strong>
                        ${vacancy.salary!"Не указана"}
                    </div>


                    <div class="mb-3">
                        <strong>Опыт работы:</strong>

                        ${vacancy.expFrom!"Не указан"}

                        <#if vacancy.expTo??>
                            - ${vacancy.expTo}
                        </#if>
                    </div>


                    <#if vacancy.createdDate??>

                        <div class="mb-3">
                            <strong>Дата публикации:</strong>
                            ${vacancy.createdDate}
                        </div>

                    </#if>


                    <#if vacancy.active?? && vacancy.active>

                        <span class="badge bg-success mb-3">
                            Активна
                        </span>

                    <#else>

                        <span class="badge bg-secondary mb-3">
                            Неактивна
                        </span>

                    </#if>


                    <hr>


                    <div class="d-flex gap-2">

                        <a href="/vacancies"
                           class="btn btn-outline-secondary">
                            Назад к вакансиям
                        </a>

                        <a href="/companies/${employer.id}"
                           class="btn btn-outline-primary">
                            Другие вакансии компании
                        </a>

                    </div>

                </div>

            </div>

        </div>

    </div>

</@layout.layout>