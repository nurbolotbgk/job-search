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

                        <#if currentUser?? && currentUser.roleId == 1>
                            <button type="button"
                                    class="btn btn-success"
                                    data-bs-toggle="modal"
                                    data-bs-target="#responseModal">
                                <@spring.message "vacancy.details.respond"/>
                            </button>
                        </#if>

                    </div>

                </div>

            </div>

        </div>

    </div>

    <#if currentUser?? && currentUser.roleId == 1>

        <div class="modal fade"
             id="responseModal"
             tabindex="-1"
             aria-hidden="true">

            <div class="modal-dialog">

                <div class="modal-content">

                    <div class="modal-header">
                        <h5 class="modal-title">
                            <@spring.message "vacancy.response.title"/>
                        </h5>

                        <button type="button"
                                class="btn-close"
                                data-bs-dismiss="modal">
                        </button>
                    </div>

                    <div class="modal-body">

                        <#if resumes?? && resumes?size gt 0>

                            <label for="resumeId"
                                   class="form-label">
                                <@spring.message "vacancy.response.select.resume"/>
                            </label>

                            <select id="resumeId"
                                    class="form-select">

                                <#list resumes as resume>
                                    <option value="${resume.id}">
                                        ${resume.name}
                                    </option>
                                </#list>

                            </select>

                        <#else>

                            <div class="alert alert-warning mb-0">
                                <@spring.message "vacancy.response.no.resumes"/>
                            </div>

                        </#if>

                    </div>

                    <#if resumes?? && resumes?size gt 0>

                        <div class="modal-footer">

                            <button type="button"
                                    class="btn btn-secondary"
                                    data-bs-dismiss="modal">
                                <@spring.message "vacancy.response.cancel"/>
                            </button>

                            <button type="button"
                                    id="sendResponseButton"
                                    class="btn btn-success">
                                <@spring.message "vacancy.response.send"/>
                            </button>

                        </div>

                    </#if>

                </div>

            </div>

        </div>

    </#if>

    <#if currentUser?? && currentUser.roleId == 1 && resumes?? && resumes?size gt 0>

        <script>
            document.getElementById('sendResponseButton')
                .addEventListener('click', function () {

                    const resumeId =
                        document.getElementById('resumeId').value;

                    const vacancyId = ${vacancy.id};

                    fetch('/responded-applicants/createResponse', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                            <#if _csrf??>
                            ,
                            '${_csrf.headerName}': '${_csrf.token}'
                            </#if>
                        },
                        body: JSON.stringify({
                            resumeId: Number(resumeId),
                            vacancyId: Number(vacancyId)
                        })
                    })
                        .then(response => {

                            if (response.ok) {
                                alert('Отклик успешно отправлен');
                                location.reload();
                            } else {
                                alert('Не удалось отправить отклик');
                            }

                        })
                        .catch(error => {
                            console.error(error);
                            alert('Ошибка при отправке отклика');
                        });

                });
        </script>

    </#if>

</@layout.layout>