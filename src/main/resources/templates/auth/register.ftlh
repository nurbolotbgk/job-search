<#import "../layout.ftlh" as layout>
<#import "/spring.ftl" as spring>

<@layout.layout title="Регистрация">

    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">

            <div class="card shadow-sm">
                <div class="card-body p-4">

                    <h2 class="text-center mb-4">
                        Регистрация
                    </h2>

                    <form action="/auth/register" method="post">

                        <#if _csrf??>
                            <input type="hidden"
                                   name="${_csrf.parameterName}"
                                   value="${_csrf.token}">
                        </#if>

                        <div class="mb-3">
                            <label class="form-label">
                                Имя
                            </label>

                            <@spring.formInput
                            "userDto.name"
                            'class="form-control"'/>

                            <@spring.showErrors
                            "<br>"
                            "text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                Фамилия
                            </label>

                            <@spring.formInput
                            "userDto.surname"
                            'class="form-control"'/>

                            <@spring.showErrors
                            "<br>"
                            "text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                Возраст
                            </label>

                            <@spring.formInput
                            "userDto.age"
                            'type="number" class="form-control"'/>

                            <@spring.showErrors
                            "<br>"
                            "text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                Email
                            </label>

                            <@spring.formInput
                            "userDto.email"
                            'type="email" class="form-control"'/>

                            <@spring.showErrors
                            "<br>"
                            "text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                Пароль
                            </label>

                            <@spring.formPasswordInput
                            "userDto.password"
                            'class="form-control"'/>

                            <@spring.showErrors
                            "<br>"
                            "text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                Номер телефона
                            </label>

                            <@spring.formInput
                            "userDto.phoneNumber"
                            'class="form-control" placeholder="+996700123456"'/>

                            <@spring.showErrors
                            "<br>"
                            "text-danger"/>
                        </div>

                        <div class="mb-4">

                            <label class="form-label">
                                Тип учётной записи
                            </label>

                            <div class="form-check">

                                <input class="form-check-input"
                                       type="radio"
                                       id="applicant"
                                       name="roleId"
                                       value="1"
                                       checked>

                                <label class="form-check-label"
                                       for="applicant">
                                    Соискатель
                                </label>

                            </div>

                            <div class="form-check">

                                <input class="form-check-input"
                                       type="radio"
                                       id="employer"
                                       name="roleId"
                                       value="2">

                                <label class="form-check-label"
                                       for="employer">
                                    Работодатель
                                </label>

                            </div>

                        </div>

                        <button type="submit"
                                class="btn btn-primary w-100">
                            Зарегистрироваться
                        </button>

                    </form>

                </div>
            </div>

        </div>
    </div>

</@layout.layout>