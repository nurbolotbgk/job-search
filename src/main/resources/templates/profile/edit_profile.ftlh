<#import "../layout.ftlh" as layout>

<@layout.layout title="Редактирование профиля">

    <div class="row justify-content-center">

        <div class="col-md-8 col-lg-6">

            <div class="card shadow-sm">

                <div class="card-body p-4">

                    <h2 class="text-center mb-4">
                        Редактирование профиля
                    </h2>

                    <form action="/profile/edit" method="post">

                        <input type="hidden"
                               name="id"
                               value="${user.id!}">

                        <input type="hidden"
                               name="roleId"
                               value="${user.roleId!}">

                        <input type="hidden"
                               name="avatar"
                               value="${user.avatar!}">

                        <div class="mb-3">
                            <label class="form-label">
                                Имя
                            </label>

                            <input type="text"
                                   class="form-control"
                                   name="name"
                                   value="${user.name!}">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                Фамилия
                            </label>

                            <input type="text"
                                   class="form-control"
                                   name="surname"
                                   value="${user.surname!}">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                Возраст
                            </label>

                            <input type="number"
                                   class="form-control"
                                   name="age"
                                   value="${user.age!}">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                Email
                            </label>

                            <input type="email"
                                   class="form-control"
                                   name="email"
                                   value="${user.email!}">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                Номер телефона
                            </label>

                            <input type="text"
                                   class="form-control"
                                   name="phoneNumber"
                                   value="${user.phoneNumber!}">
                        </div>

                        <div class="d-flex gap-2">

                            <button type="submit"
                                    class="btn btn-primary">
                                Сохранить
                            </button>

                            <a href="/profile"
                               class="btn btn-outline-secondary">
                                Отмена
                            </a>

                        </div>

                    </form>

                </div>

            </div>

        </div>

    </div>

</@layout.layout>