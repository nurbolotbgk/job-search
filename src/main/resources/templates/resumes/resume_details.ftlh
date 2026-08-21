<#import "../layout.ftlh" as layout>

<@layout.layout title="Резюме">

    <div class="row justify-content-center">

        <div class="col-md-8">

            <div class="card shadow-sm">

                <div class="card-body p-4">

                    <h2 class="mb-4">
                        ${resume.name!}
                    </h2>

                    <div class="mb-3">
                        <strong>Соискатель:</strong>
                        ${applicant.name!}
                        ${applicant.surname!}
                    </div>

                    <div class="mb-3">
                        <strong>Желаемая зарплата:</strong>
                        ${resume.salary!"Не указана"}
                    </div>

                    <#if resume.workExperiences?? && resume.workExperiences?size gt 0>

                        <hr>

                        <h4>Опыт работы</h4>

                        <#list resume.workExperiences as work>

                            <div class="mb-3">

                                <strong>${work.position!}</strong>

                                <div>
                                    Компания: ${work.companyName!}
                                </div>

                                <div>
                                    Опыт: ${work.years!"Не указан"}
                                </div>

                                <#if work.responsibilities?? && work.responsibilities?has_content>
                                    <div>
                                        Обязанности:
                                        ${work.responsibilities}
                                    </div>
                                </#if>

                            </div>

                        </#list>

                    </#if>


                    <#if resume.educations?? && resume.educations?size gt 0>

                        <hr>

                        <h4>Образование</h4>

                        <#list resume.educations as education>

                            <div class="mb-3">

                                <strong>
                                    ${education.institution!}
                                </strong>

                                <#if education.program?? && education.program?has_content>
                                    <div>
                                        Программа:
                                        ${education.program}
                                    </div>
                                </#if>

                                <#if education.startDate??>
                                    <div>
                                        Начало:
                                        ${education.startDate}
                                    </div>
                                </#if>

                                <#if education.endDate??>
                                    <div>
                                        Окончание:
                                        ${education.endDate}
                                    </div>
                                </#if>

                                <#if education.degree?? && education.degree?has_content>
                                    <div>
                                        Степень:
                                        ${education.degree}
                                    </div>
                                </#if>

                            </div>

                        </#list>

                    </#if>


                    <#if resume.contacts?? && resume.contacts?size gt 0>

                        <hr>

                        <h4>Контакты</h4>

                        <#list resume.contacts as contact>

                            <div class="mb-2">
                                ${contact.contactValue!}
                            </div>

                        </#list>

                    </#if>


                    <hr>

                    <a href="/resumes"
                       class="btn btn-outline-secondary">
                        Назад к резюме
                    </a>

                </div>

            </div>

        </div>

    </div>

</@layout.layout>