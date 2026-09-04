<#import "../layout.ftlh" as layout>
<#import "/spring.ftl" as spring>

<@layout.layout title="Восстановление пароля">

    <div class="row justify-content-center">

        <div class="col-md-8 col-lg-6">

            <div class="card shadow-sm">

                <div class="card-body p-4">

                    <h2 class="text-center mb-4">
                        <@spring.message "forgot.password.title"/>
                    </h2>

                    <#if errorKey??>
                        <div class="alert alert-danger">
                            <@spring.message errorKey/>
                        </div>
                    </#if>

                    <#if messageKey??>
                        <div class="alert alert-success">
                            <@spring.message messageKey/>
                        </div>
                    </#if>

                    <p class="text-muted">
                        <@spring.message "forgot.password.description"/>
                    </p>

                    <form action="/auth/forgot_password"
                          method="post">

                        <#if _csrf??>
                            <input type="hidden"
                                   name="${_csrf.parameterName}"
                                   value="${_csrf.token}">
                        </#if>

                        <div class="mb-3">

                            <label class="form-label">
                                <@spring.message "forgot.password.email"/>
                            </label>

                            <input type="email"
                                   name="email"
                                   class="form-control"
                                   required>

                        </div>

                        <button type="submit"
                                class="btn btn-primary w-100">
                            <@spring.message "forgot.password.button"/>
                        </button>

                    </form>

                </div>

            </div>

        </div>

    </div>

</@layout.layout>