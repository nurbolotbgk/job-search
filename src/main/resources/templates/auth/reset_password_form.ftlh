<#import "../layout.ftlh" as layout>

<@layout.layout title="Новый пароль">

    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">

            <div class="card shadow-sm">
                <div class="card-body p-4">

                    <h2 class="text-center mb-4">
                        Новый пароль
                    </h2>

                    <#if error??>
                        <div class="alert alert-danger">
                            ${error}
                        </div>
                    </#if>

                    <#if message??>
                        <div class="alert alert-success">
                            ${message}
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
                                Новый пароль
                            </label>

                            <input type="password"
                                   id="password"
                                   name="password"
                                   class="form-control"
                                   required>

                        </div>

                        <button type="submit"
                                class="btn btn-primary w-100">
                            Изменить пароль
                        </button>

                    </form>

                </div>
            </div>

        </div>
    </div>

</@layout.layout>