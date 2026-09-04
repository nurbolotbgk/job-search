<#import "../layout.ftlh" as layout>
<#import "/spring.ftl" as spring>

<@layout.layout title="Ошибка">

    <div class="row justify-content-center">
        <div class="col-md-8">

            <div class="card shadow-sm text-center">
                <div class="card-body p-5">

                    <h1 class="display-1 text-danger">
                        ${status}
                    </h1>

                    <h3>
                        ${reason}
                    </h3>

                    <p class="text-muted mt-3">
                        <@spring.message "error.message.not.found"/>
                    </p>

                    <#if details??>
                        <p class="small text-muted">
                            ${details.requestURL!}
                        </p>
                    </#if>

                    <a href="/"
                       class="btn btn-primary mt-3">
                        <@spring.message "error.back.home"/>
                    </a>

                </div>
            </div>

        </div>
    </div>

</@layout.layout>