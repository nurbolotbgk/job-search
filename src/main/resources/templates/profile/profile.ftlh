<#import "../layout.ftlh" as layout>

<@layout.layout title="Личный кабинет">

    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">

            <div class="card shadow-sm">
                <div class="card-body p-4">

                    <h2 class="text-center mb-4">
                        Личный кабинет
                    </h2>

                    <div class="mb-3">
                        <strong>Имя:</strong>
                        ${user.name!}
                    </div>

                    <div class="mb-3">
                        <strong>Фамилия:</strong>
                        ${user.surname!}
                    </div>

                    <div class="mb-3">
                        <strong>Возраст:</strong>
                        ${user.age!}
                    </div>

                    <div class="mb-3">
                        <strong>Email:</strong>
                        ${user.email!}
                    </div>

                    <div class="mb-3">
                        <strong>Номер телефона:</strong>
                        ${user.phoneNumber!}
                    </div>

                    <a href="/profile/edit"
                       class="btn btn-primary">
                        Редактировать профиль
                    </a>

                </div>
            </div>

        </div>
    </div>

</@layout.layout>