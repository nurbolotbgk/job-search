<#import "/spring.ftl" as spring>
<#macro layout title="Job Search">
    <!doctype html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">

        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">

        <title>${title}</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
              rel="stylesheet">

        <link rel="stylesheet"
              href="/static/css/style.css">
    </head>

    <body>

    <#assign known = SPRING_SECURITY_CONTEXT??>

    <#assign isApplicant = false>
    <#assign isEmployer = false>

    <#if known>

        <#assign
        user = SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        >

        <#list user.authorities as auth>

            <#if auth.authority == "ROLE_APPLICANT">
                <#assign isApplicant = true>
            </#if>

            <#if auth.authority == "ROLE_EMPLOYER">
                <#assign isEmployer = true>
            </#if>

        </#list>

    </#if>


    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">

            <a class="navbar-brand" href="/">
                Job Search
            </a>

            <button class="navbar-toggler"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarNav"
                    aria-controls="navbarNav"
                    aria-expanded="false"
                    aria-label="Toggle navigation">

                <span class="navbar-toggler-icon"></span>
            </button>


            <div class="collapse navbar-collapse" id="navbarNav">

                <ul class="navbar-nav me-auto">

                    <li class="nav-item">
                        <a class="nav-link" href="/">
                            <@spring.message "nav.home"/>
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="/vacancies">
                            Вакансии
                        </a>
                    </li>


                    <#if isApplicant>

                        <li class="nav-item">
                            <a class="nav-link" href="/companies">
                                Компании
                            </a>
                        </li>

                    </#if>


                    <#if isEmployer>

                        <li class="nav-item">
                            <a class="nav-link" href="/resumes">
                                Резюме
                            </a>
                        </li>

                    </#if>

                </ul>

            </div>


            <#if known>

                <p class="text-light m-2 m-sm-0">
                    ${name}

                    <#list user.authorities as auth>
                        <span class="text-warning">
                            ${auth}
                        </span>
                    </#list>
                </p>

                <a href="/profile"
                   class="btn btn-outline-light m-1">
                    Личный кабинет
                </a>

                <form action="/auth/logout"
                      method="post"
                      class="d-inline">

                    <#if _csrf??>
                        <input type="hidden"
                               name="${_csrf.parameterName}"
                               value="${_csrf.token}">
                    </#if>

                    <button type="submit"
                            class="btn btn-outline-danger m-1">
                        Logout
                    </button>

                </form>

            <#else>

                <a href="/auth/login"
                   class="btn btn-outline-light m-1">
                    Login
                </a>

                <a href="/auth/register"
                   class="btn btn-warning m-1">
                    Регистрация
                </a>

            </#if>

        </div>
    </nav>


    <main class="main-content">
        <div class="container">
            <#nested>
        </div>
    </main>


    <footer class="footer">
        <div class="container text-center">
            <span>
                Job Search © 2026
            </span>
        </div>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js">
    </script>

    </body>
    </html>
</#macro>