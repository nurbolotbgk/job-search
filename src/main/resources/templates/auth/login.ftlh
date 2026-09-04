<#import "../layout.ftlh" as layout>
<#import "/spring.ftl" as spring>

<@layout.layout title="Вход">

    <div class="row justify-content-center">

        <div class="col-md-8 col-lg-6">

            <div class="card shadow-sm">

                <div class="row g-0 align-items-center">

                    <div class="col-lg-4 d-none d-lg-block">

                        <img src="https://mdbootstrap.com/img/new/ecommerce/vertical/004.jpg"
                             alt="Login image"
                             class="img-fluid h-100"
                             style="object-fit: cover; border-radius: 0.375rem 0 0 0.375rem;">

                    </div>

                    <div class="col-lg-8">

                        <div class="card-body p-4 p-md-5">

                            <h2 class="text-center mb-4">
                                <@spring.message "login.title"/>
                            </h2>

                            <#if RequestParameters.error??>
                                <div class="alert alert-danger">
                                    <@spring.message "login.error"/>
                                </div>
                            </#if>

                            <#if RequestParameters.logout??>
                                <div class="alert alert-success">
                                    <@spring.message "login.logout"/>
                                </div>
                            </#if>

                            <#if messageKey??>
                                <div class="alert alert-success">
                                    <@spring.message messageKey/>
                                </div>
                            </#if>

                            <form action="/auth/login" method="post">

                                <#if _csrf??>
                                    <input type="hidden"
                                           name="${_csrf.parameterName}"
                                           value="${_csrf.token}">
                                </#if>

                                <div class="mb-3">

                                    <label for="email"
                                           class="form-label">
                                        <@spring.message "login.email"/>
                                    </label>

                                    <input type="email"
                                           id="email"
                                           class="form-control"
                                           name="username"
                                           placeholder="example@mail.com"
                                           required>

                                </div>

                                <div class="mb-4">

                                    <label for="password"
                                           class="form-label">
                                        <@spring.message "login.password"/>
                                    </label>

                                    <input type="password"
                                           id="password"
                                           class="form-control"
                                           name="password"
                                           required>

                                </div>

                                <button type="submit"
                                        class="btn btn-primary w-100">
                                    <@spring.message "login.button"/>
                                </button>

                                <p class="text-center mt-3">
                                    <a href="/auth/forgot_password">
                                        <@spring.message "login.forgot.password"/>
                                    </a>
                                </p>

                            </form>

                            <p class="text-center mt-4 mb-0">
                                <@spring.message "login.no.account"/>

                                <a href="/auth/register">
                                    <@spring.message "login.register"/>
                                </a>
                            </p>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    </div>

</@layout.layout>