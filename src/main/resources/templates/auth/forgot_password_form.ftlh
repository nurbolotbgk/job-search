<#import "../layout.ftlh" as layout>

<@layout.layout title="Восстановление пароля">

    <div class="row justify-content-center">

        <div class="col-md-8 col-lg-6">

            <div class="card shadow-sm">

                <div class="card-body p-4">

                    <h2 class="text-center mb-4">
                        Восстановление пароля
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

                    <p class="text-muted">
                        Укажите email. На него будет отправлена ссылка
                        для восстановления пароля.
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
                                Email
                            </label>

                            <input type="email"
                                   name="email"
                                   class="form-control"
                                   required>

                        </div>

                        <button type="submit"
                                class="btn btn-primary w-100">
                            Отправить ссылку
                        </button>

                    </form>

                </div>

            </div>

        </div>

    </div>

</@layout.layout>