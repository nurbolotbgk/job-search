<#import "../layout.ftlh" as layout>
<#import "/spring.ftl" as spring>

<@layout.layout title="Чат">

    <div class="row justify-content-center">

        <div class="col-md-8">

            <div class="card shadow-sm">

                <div class="card-header">
                    <h4 class="mb-0">
                        Чат
                    </h4>
                </div>


                <div class="card-body">

                    <#if messages?? && messages?size gt 0>

                        <#list messages as message>

                            <div class="border rounded p-3 mb-3">

                                <div class="d-flex justify-content-between">

                                    <strong>
                                        ${message.senderName!}
                                    </strong>

                                    <small class="text-muted">
                                        ${message.timestamp!}
                                    </small>

                                </div>

                                <div class="mt-2">
                                    ${message.content!}
                                </div>

                            </div>

                        </#list>

                    <#else>

                        <div class="alert alert-light border">
                            Сообщений пока нет.
                        </div>

                    </#if>


                    <hr>


                    <form action="/messages/${respondedApplicantId}"
                          method="post">

                        <#if _csrf??>
                            <input type="hidden"
                                   name="${_csrf.parameterName}"
                                   value="${_csrf.token}">
                        </#if>

                        <div class="mb-3">

                            <label for="content"
                                   class="form-label">
                                Сообщение
                            </label>

                            <textarea id="content"
                                      name="content"
                                      class="form-control"
                                      rows="3"
                                      required></textarea>

                        </div>


                        <div class="d-flex gap-2">

                            <button type="submit"
                                    class="btn btn-primary">
                                Отправить
                            </button>

                            <a href="/profile"
                               class="btn btn-outline-secondary">
                                Назад
                            </a>

                        </div>

                    </form>

                </div>

            </div>

        </div>

    </div>

</@layout.layout>