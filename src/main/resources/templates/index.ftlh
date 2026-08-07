<#import "layout.ftlh" as main>

<@main.layout title="Главная">

    <div class="page-card text-center">

        <h1 class="page-title">
            Job Search
        </h1>

        <p class="lead">
            Сервис для поиска вакансий и сотрудников
        </p>

        <div class="d-flex justify-content-center gap-3 mt-4">

            <a href="/vacancies"
               class="btn btn-primary">
                Смотреть вакансии
            </a>

            <a href="/resumes"
               class="btn btn-outline-primary">
                Смотреть резюме
            </a>

        </div>

    </div>

</@main.layout>