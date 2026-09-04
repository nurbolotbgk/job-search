<#import "../layout.ftlh" as layout>
<#import "/spring.ftl" as spring>

<@layout.layout title="Новый пароль">

    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">

            <div class="card shadow-sm">
                <div class="card-body p-4">

                    <h2 class="text-center mb-4">
                        <@spring.message "reset.password.title"/>
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

                    <form action="/auth/reset_password"
                          method="post">

                        <#if _csrf??>
                            <input type="hidden"
                                   name="${_csrf.parameterName}"
                                   value="${_csrf.token}">
                        </#if>

                        <input type="hidden"
                               name="token"
                               value="${token!}">

                        <div class="mb-3">

                            <label for="password"
                                   class="form-label">
                                <@spring.message "reset.password.label"/>
                            </label>

                            <input type="password"
                                   id="password"
                                   name="password"
                                   class="form-control"
                                   required>

                        </div>

                        <button type="submit"
                                class="btn btn-primary w-100">
                            <@spring.message "reset.password.button"/>
                        </button>

                    </form>

                </div>
            </div>

        </div>
    </div>

</@layout.layout>