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

    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">

            <a class="navbar-brand fw-bold" href="/">
                Job Search
            </a>

            <button class="navbar-toggler"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#mainNavbar"
                    aria-controls="mainNavbar"
                    aria-expanded="false"
                    aria-label="Открыть меню">

                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse"
                 id="mainNavbar">

                <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                    <li class="nav-item">
                        <a class="nav-link"
                           href="/vacancies">
                            Вакансии
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link"
                           href="/resumes">
                            Резюме
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link"
                           href="/profile">
                            Личный кабинет
                        </a>
                    </li>

                </ul>

                <div class="d-flex gap-2">

                    <a href="/auth/register"
                       class="btn btn-outline-light">
                        Регистрация
                    </a>

                </div>

            </div>
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