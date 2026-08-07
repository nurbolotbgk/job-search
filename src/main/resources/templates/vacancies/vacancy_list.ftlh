<#import "../layout.ftlh" as layout>

<@layout.layout title="Вакансии">

    <div class="d-flex justify-content-between align-items-center mb-4">

        <div>
            <h2 class="mb-1">Вакансии</h2>
            <p class="text-muted mb-0">
                Актуальные предложения работодателей
            </p>
        </div>

        <a href="/vacancies/create"
           class="btn btn-primary">
            Создать вакансию
        </a>

    </div>

    <div class="row">

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
                            Опыт: ${vacancy.experience!"Не указан"}
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

    </div>

</@layout.layout>