<#import "../layout.ftlh" as layout>
<#import "/spring.ftl" as spring>

<@layout.layout title="Резюме">

    <div class="row justify-content-center">

        <div class="col-md-8">

            <div class="card shadow-sm">

                <div class="card-body p-4">

                    <h2 class="mb-4">
                        ${resume.name!}
                    </h2>


                    <div class="mb-3">
                        <strong>
                            <@spring.message "resume.details.applicant"/>
                        </strong>

                        ${applicant.name!}
                        ${applicant.surname!}
                    </div>


                    <div class="mb-3">
                        <strong>
                            <@spring.message "resumes.salary"/>
                        </strong>

                        <#if resume.salary??>
                            ${resume.salary}
                        <#else>
                            <@spring.message "resumes.not.specified"/>
                        </#if>
                    </div>


                    <#if resume.workExperiences?? && resume.workExperiences?size gt 0>

                        <hr>

                        <h4>
                            <@spring.message "resume.section.experience"/>
                        </h4>

                        <#list resume.workExperiences as work>

                            <div class="mb-3">

                                <strong>
                                    ${work.position!}
                                </strong>

                                <div>
                                    <@spring.message "resume.details.company"/>
                                    ${work.companyName!}
                                </div>

                                <div>
                                    <@spring.message "resume.details.experience"/>

                                    <#if work.years??>
                                        ${work.years}
                                    <#else>
                                        <@spring.message "resumes.not.specified"/>
                                    </#if>
                                </div>

                                <#if work.responsibilities?? && work.responsibilities?has_content>

                                    <div>
                                        <@spring.message "resume.details.responsibilities"/>
                                        ${work.responsibilities}
                                    </div>

                                </#if>

                            </div>

                        </#list>

                    </#if>


                    <#if resume.educations?? && resume.educations?size gt 0>

                        <hr>

                        <h4>
                            <@spring.message "resume.section.education"/>
                        </h4>

                        <#list resume.educations as education>

                            <div class="mb-3">

                                <strong>
                                    ${education.institution!}
                                </strong>

                                <#if education.program?? && education.program?has_content>

                                    <div>
                                        <@spring.message "resume.details.program"/>
                                        ${education.program}
                                    </div>

                                </#if>


                                <#if education.startDate??>

                                    <div>
                                        <@spring.message "resume.details.start"/>
                                        ${education.startDate}
                                    </div>

                                </#if>


                                <#if education.endDate??>

                                    <div>
                                        <@spring.message "resume.details.end"/>
                                        ${education.endDate}
                                    </div>

                                </#if>


                                <#if education.degree?? && education.degree?has_content>

                                    <div>
                                        <@spring.message "resume.details.degree"/>
                                        ${education.degree}
                                    </div>

                                </#if>

                            </div>

                        </#list>

                    </#if>


                    <#if resume.contacts?? && resume.contacts?size gt 0>

                        <hr>

                        <h4>
                            <@spring.message "resume.section.contacts"/>
                        </h4>

                        <#list resume.contacts as contact>

                            <div class="mb-2">
                                ${contact.contactValue!}
                            </div>

                        </#list>

                    </#if>


                    <hr>

                    <a href="/resumes"
                       class="btn btn-outline-secondary">
                        <@spring.message "resume.details.back"/>
                    </a>

                </div>

            </div>

        </div>

    </div>

</@layout.layout>