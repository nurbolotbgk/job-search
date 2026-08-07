<#import "../layout.ftlh" as layout>

<@layout.layout title="Регистрация">

    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">

            <div class="card shadow-sm">
                <div class="card-body p-4">

                    <h2 class="text-center mb-4">
                        Регистрация
                    </h2>

                    <form action="/auth/register" method="post">

                        <div class="mb-3">
                            <label class="form-label">
                                Имя
                            </label>

                            <input type="text"
                                   class="form-control"
                                   name="name">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                Фамилия
                            </label>

                            <input type="text"
                                   class="form-control"
                                   name="surname">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                Возраст
                            </label>

                            <input type="number"
                                   class="form-control"
                                   name="age">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                Email
                            </label>

                            <input type="email"
                                   class="form-control"
                                   name="email">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                Пароль
                            </label>

                            <input type="password"
                                   class="form-control"
                                   name="password">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                Номер телефона
                            </label>

                            <input type="text"
                                   class="form-control"
                                   name="phoneNumber"
                                   placeholder="+996700123456">
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