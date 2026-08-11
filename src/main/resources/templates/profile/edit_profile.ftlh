<#import "../layout.ftlh" as layout>
<#import "/spring.ftl" as spring>

<@layout.layout title="Редактирование профиля">

    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">

            <div class="card shadow-sm">
                <div class="card-body p-4">

                    <h2 class="text-center mb-4">
                        Редактирование профиля
                    </h2>

                    <form action="/profile/edit" method="post" enctype="multipart/form-data">

                        <div class="mb-3">
                            <label class="form-label">
                                Имя
                            </label>

                            <@spring.formInput
                            "editUserDto.name"
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
                            "editUserDto.surname"
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
                            "editUserDto.age"
                            'type="number" class="form-control"'/>

                            <@spring.showErrors
                            "<br>"
                            "text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                Номер телефона
                            </label>

                            <@spring.formInput
                            "editUserDto.phoneNumber"
                            'class="form-control"'/>

                            <@spring.showErrors
                            "<br>"
                            "text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                Фото профиля
                            </label>

                            <input type="file"
                                   class="form-control"
                                   name="file"
                                   accept="image/*">
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