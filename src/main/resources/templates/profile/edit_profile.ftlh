<#import "../layout.ftlh" as layout>
<#import "/spring.ftl" as spring>

<@layout.layout title="Редактирование профиля">

    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">

            <div class="card shadow-sm">
                <div class="card-body p-4">

                    <h2 class="text-center mb-4">
                        <@spring.message "profile.edit.title"/>
                    </h2>

                    <form action="/profile/edit"
                          method="post"
                          enctype="multipart/form-data">

                        <#if _csrf??>
                            <input type="hidden"
                                   name="${_csrf.parameterName}"
                                   value="${_csrf.token}">
                        </#if>

                        <div class="mb-3">

                            <label class="form-label">
                                <@spring.message "profile.name"/>
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
                                <@spring.message "profile.surname"/>
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
                                <@spring.message "profile.age"/>
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
                                <@spring.message "profile.phone"/>
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
                                <@spring.message "profile.edit.photo"/>
                            </label>

                            <input type="file"
                                   class="form-control"
                                   name="file"
                                   accept="image/*">

                        </div>


                        <div class="d-flex gap-2">

                            <button type="submit"
                                    class="btn btn-primary">
                                <@spring.message "profile.edit.save"/>
                            </button>

                            <a href="/profile"
                               class="btn btn-outline-secondary">
                                <@spring.message "profile.edit.cancel"/>
                            </a>

                        </div>

                    </form>

                </div>
            </div>

        </div>
    </div>

</@layout.layout>