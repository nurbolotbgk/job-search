<#import "../layout.ftlh" as layout>

<@layout.layout title="Резюме">

    <div class="d-flex justify-content-between align-items-center mb-4">

        <div>
            <h2 class="mb-1">Резюме</h2>
            <p class="text-muted mb-0">
                Список резюме соискателей
            </p>
        </div>

        <a href="/resumes/create"
           class="btn btn-primary">
            Создать резюме
        </a>

    </div>

    <div class="row">

        <#list resumes as resume>

            <div class="col-md-6 col-lg-4 mb-4">

                <div class="card h-100 shadow-sm">

                    <div class="card-body">

                        <h5 class="card-title">
                            ${resume.name!}
                        </h5>

                        <p class="text-muted">
                            Категория: ${resume.categoryName!"Не указана"}
                        </p>

                        <p>
                            Желаемая зарплата:
                            <strong>${resume.salary!"Не указана"}</strong>
                        </p>

                        <p>
                            ${resume.description!"Описание отсутствует"}
                        </p>

                    </div>

                    <div class="card-footer bg-white">

                        <a href="/resumes/${resume.id}"
                           class="btn btn-outline-primary">
                            Подробнее
                        </a>

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