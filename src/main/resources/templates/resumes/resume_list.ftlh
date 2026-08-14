<#import "../layout.ftlh" as layout>

<@layout.layout title="Резюме">

    <div class="mb-4">

        <h2 class="mb-1">
            Резюме
        </h2>

        <p class="text-muted mb-0">
            Список резюме соискателей
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
                                    <strong>Категория:</strong>
                                    ${resume.categoryName!"Не указана"}
                                </p>

                                <p class="mb-2">
                                    <strong>Желаемая зарплата:</strong>
                                    ${resume.salary!"Не указана"}
                                </p>

                                <#if resume.active?? && resume.active>

                                    <span class="badge bg-success">
                                    Активно
                                </span>

                                <#else>

                                    <span class="badge bg-secondary">
                                    Неактивно
                                </span>

                                </#if>

                            </div>


                            <div>

                                <a href="/resumes/${resume.id}"
                                   class="btn btn-outline-primary">
                                    Подробнее
                                </a>

                            </div>

                        </div>

                    </div>

                </div>

            </div>

        <#else>

            <div class="col-12">

                <div class="alert alert-info">
                    Резюме пока отсутствуют
                </div>

            </div>

        </#list>

    </div>

</@layout.layout>