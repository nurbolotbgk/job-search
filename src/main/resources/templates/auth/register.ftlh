<#import "../layout.ftlh" as layout>
<#import "/spring.ftl" as spring>

<@layout.layout title="Регистрация">

    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">

            <div class="card shadow-sm">
                <div class="card-body p-4">

                    <h2 class="text-center mb-4">
                        <@spring.message "register.title"/>
                    </h2>

                    <form action="/auth/register" method="post" enctype="multipart/form-data">

                        <#if _csrf??>
                            <input type="hidden"
                                   name="${_csrf.parameterName}"
                                   value="${_csrf.token}">
                        </#if>

                        <div class="mb-3">
                            <label class="form-label" id="nameLabel">
                                <@spring.message "register.name"/>
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
                                <@spring.message "register.surname"/>
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
                                <@spring.message "register.age"/>
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
                                <@spring.message "register.email"/>
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
                                <@spring.message "register.password"/>
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
                                <@spring.message "register.phone"/>
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
                                <@spring.message "register.account.type"/>
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
                                    <@spring.message "register.role.applicant"/>
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
                                    <@spring.message "register.role.employer"/>
                                </label>

                            </div>

                        </div>

                        <div class="mb-3">
                            <label for="file" class="form-label">
                                <@spring.message "register.avatar"/>
                            </label>

                            <input type="file"
                                   class="form-control"
                                   id="file"
                                   name="file"
                                   accept="image/*">
                        </div>

                        <button type="submit"
                                class="btn btn-primary w-100">
                            <@spring.message "register.button"/>
                        </button>

                    </form>

                </div>
            </div>

        </div>
    </div>

    <script>
        const applicantRadio = document.getElementById('applicant');
        const employerRadio = document.getElementById('employer');
        const nameLabel = document.getElementById('nameLabel');

        const applicantName = '<@spring.message "register.name"/>';
        const companyName = '<@spring.message "register.company.name"/>';

        function updateNameLabel() {
            if (employerRadio.checked) {
                nameLabel.textContent = companyName;
            } else {
                nameLabel.textContent = applicantName;
            }
        }

        applicantRadio.addEventListener('change', updateNameLabel);
        employerRadio.addEventListener('change', updateNameLabel);

        updateNameLabel();
    </script>

</@layout.layout>