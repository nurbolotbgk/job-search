<#macro layout title="Job Search">
    <!doctype html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>${title}</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
              rel="stylesheet">
    </head>

    <body class="bg-light">

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
        <div class="container">

            <a class="navbar-brand" href="/">Job Search</a>

            <button class="navbar-toggler"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarMenu">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarMenu">

                <ul class="navbar-nav me-auto">

                    <li class="nav-item">
                        <a class="nav-link" href="/vacancies">
                            Вакансии
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="/resumes">
                            Резюме
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="/profile">
                            Личный кабинет
                        </a>
                    </li>

                </ul>

                <div class="d-flex">
                    <a class="btn btn-outline-light"
                       href="/auth/register">
                        Регистрация
                    </a>
                </div>

            </div>
        </div>
    </nav>

    <main class="container pb-5">
        <#nested>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js">
    </script>

    </body>
    </html>
</#macro>