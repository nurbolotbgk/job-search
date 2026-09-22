<#import "../layout.ftlh" as layout>
<#import "/spring.ftl" as spring>

<@layout.layout title="Создание резюме">

    <div class="row justify-content-center">

        <div class="col-md-10 col-lg-8">

            <div class="card shadow-sm">

                <div class="card-body p-4">

                    <h2 class="text-center mb-4">
                        <@spring.message "resume.create.title"/>
                    </h2>


                    <form id="resume-form">

                        <#if _csrf??>
                            <input type="hidden"
                                   name="${_csrf.parameterName}"
                                   value="${_csrf.token}">
                        </#if>


                        <h4 class="mb-3">
                            <@spring.message "resume.section.main"/>
                        </h4>


                        <div class="mb-3">

                            <label class="form-label">
                                <@spring.message "resume.name"/>
                            </label>

                            <@spring.formInput
                            "resumeFormDto.name"
                            'class="form-control"'
                            />

                            <@spring.showErrors
                            "<br>"
                            "text-danger"
                            />

                        </div>


                        <div class="mb-3">

                            <label class="form-label">
                                <@spring.message "resume.category"/>
                            </label>

                            <select class="form-select"
                                    name="categoryId">

                                <#list categories as category>

                                    <option value="${category.id}"
                                            <#if resumeFormDto.categoryId??
                                    && resumeFormDto.categoryId == category.id>
                                    selected
                                            </#if>>

                                        ${category.name}

                                    </option>

                                </#list>

                            </select>

                            <@spring.bind
                            "resumeFormDto.categoryId"
                            />

                            <@spring.showErrors
                            "<br>"
                            "text-danger"
                            />

                        </div>


                        <div class="mb-3">

                            <label class="form-label">
                                <@spring.message "resume.salary"/>
                            </label>

                            <@spring.formInput
                            "resumeFormDto.salary"
                            'type="number"
                                 step="0.01"
                                 class="form-control"'
                            />

                            <@spring.showErrors
                            "<br>"
                            "text-danger"
                            />

                        </div>


                        <div class="form-check mb-4">

                            <input type="checkbox"
                                   class="form-check-input"
                                   id="active"
                                   name="active"
                                   value="true"

                                    <#if resumeFormDto.active??
                                    && resumeFormDto.active>
                                        checked
                                    </#if>
                            >

                            <label class="form-check-label"
                                   for="active">
                                <@spring.message "resume.active"/>
                            </label>

                        </div>


                        <hr class="my-4">


                        <h4 class="mb-3">
                            <@spring.message "resume.section.experience"/>
                        </h4>


                        <div id="work-experiences-container">

                            <#list resumeFormDto.workExperiences as workExperience>

                                <div class="card mb-4 work-experience-item">

                                    <div class="card-body">

                                        <h5 class="mb-3">
                                            <@spring.message "resume.experience.number"/>
                                            <span class="work-experience-number">
                                                ${workExperience_index + 1}
                                            </span>
                                        </h5>

                                        <div class="mb-3">
                                            <label class="form-label">
                                                <@spring.message "resume.experience.years"/>
                                            </label>

                                            <@spring.formInput
                                            "resumeFormDto.workExperiences[${workExperience_index}].years"
                                            'type="number"
                                            min="0"
                                            class="form-control"'
                                            />

                                            <@spring.showErrors "<br>" "text-danger"/>
                                        </div>

                                        <div class="mb-3">
                                            <label class="form-label">
                                                <@spring.message "resume.experience.company"/>
                                            </label>

                                            <@spring.formInput
                                            "resumeFormDto.workExperiences[${workExperience_index}].companyName"
                                            'class="form-control"'
                                            />

                                            <@spring.showErrors "<br>" "text-danger"/>
                                        </div>

                                        <div class="mb-3">
                                            <label class="form-label">
                                                <@spring.message "resume.experience.position"/>
                                            </label>

                                            <@spring.formInput
                                            "resumeFormDto.workExperiences[${workExperience_index}].position"
                                            'class="form-control"'
                                            />

                                            <@spring.showErrors "<br>" "text-danger"/>
                                        </div>

                                        <div class="mb-3">
                                            <label class="form-label">
                                                <@spring.message "resume.experience.responsibilities"/>
                                            </label>

                                            <@spring.formTextarea
                                            "resumeFormDto.workExperiences[${workExperience_index}].responsibilities"
                                            'class="form-control"
                                             rows="3"'
                                            />
                                        </div>

                                    </div>

                                </div>

                            </#list>

                        </div>


                        <button type="button"
                                id="add-work-experience"
                                class="btn btn-outline-primary mb-4">
                            <@spring.message "resume.experience.add"/>
                        </button>


                        <hr class="my-4">


                        <h4 class="mb-3">
                            <@spring.message "resume.section.education"/>
                        </h4>

                        <div id="educations-container">
                        <#list resumeFormDto.educations as education>

                            <div class="card mb-4 education-item">

                                <div class="card-body">

                                    <h5 class="mb-3">
                                        <@spring.message "resume.education.number"/>${education_index + 1}
                                    </h5>


                                    <div class="mb-3">

                                        <label class="form-label">
                                            <@spring.message "resume.education.institution"/>
                                        </label>

                                        <@spring.formInput
                                        "resumeFormDto.educations[${education_index}].institution"
                                        'class="form-control"'
                                        />

                                        <@spring.showErrors
                                        "<br>"
                                        "text-danger"
                                        />

                                    </div>


                                    <div class="mb-3">

                                        <label class="form-label">
                                            <@spring.message "resume.education.program"/>
                                        </label>

                                        <@spring.formInput
                                        "resumeFormDto.educations[${education_index}].program"
                                        'class="form-control"'
                                        />

                                    </div>


                                    <div class="mb-3">
                                        <label class="form-label">
                                            <@spring.message "resume.education.start"/>
                                            <span class="text-muted">
                                                <@spring.message "resume.education.date.hint"/>
                                            </span>
                                        </label>

                                        <input type="date"
                                               class="form-control"
                                               name="educations[${education_index}].startDate">

                                        <div class="form-text">
                                            <@spring.message "resume.education.date.format"/>
                                        </div>

                                        <@spring.showErrors
                                        "<br>"
                                        "text-danger"
                                        />
                                    </div>


                                    <div class="mb-3">
                                        <label class="form-label">
                                            <@spring.message "resume.education.end"/>
                                            <span class="text-muted">
                                                <@spring.message "resume.education.date.hint"/>
                                            </span>
                                        </label>

                                        <input type="date"
                                               class="form-control"
                                               name="educations[${education_index}].endDate">

                                        <div class="form-text">
                                            <@spring.message "resume.education.date.format"/>
                                        </div>

                                        <@spring.showErrors
                                        "<br>"
                                        "text-danger"
                                        />
                                    </div>

                                    <div class="mb-3">

                                        <label class="form-label">
                                            <@spring.message "resume.education.degree"/>
                                        </label>

                                        <@spring.formInput
                                        "resumeFormDto.educations[${education_index}].degree"
                                        'class="form-control"'
                                        />

                                    </div>

                                </div>

                            </div>

                        </#list>
                        </div>


                        <button type="button"
                                id="add-education"
                                class="btn btn-outline-primary mb-4">

                            <@spring.message "resume.education.add"/>

                        </button>


                        <hr class="my-4">


                        <h4 class="mb-3">
                            <@spring.message "resume.section.contacts"/>
                        </h4>

                        <div id="contacts-container">
                        <#list resumeFormDto.contacts as contact>

                            <div class="card mb-4 contact-item">

                                <div class="card-body">

                                    <h5 class="mb-3">
                                        <@spring.message "resume.contact.number"/>${contact_index + 1}
                                    </h5>


                                    <div class="mb-3">

                                        <label class="form-label">
                                            <@spring.message "resume.contact.type"/>
                                        </label>

                                        <select name="contacts[${contact_index}].typeId"
                                                class="form-select">

                                            <option value="1"
                                                    <#if contact.typeId??
                                            && contact.typeId == 1>
                                            selected
                                                    </#if>>
                                                Telegram
                                            </option>

                                            <option value="2"
                                                    <#if contact.typeId??
                                            && contact.typeId == 2>
                                            selected
                                                    </#if>>
                                                Mail
                                            </option>

                                            <option value="3"
                                                    <#if contact.typeId??
                                            && contact.typeId == 3>
                                            selected
                                                    </#if>>
                                                Facebook
                                            </option>

                                            <option value="4"
                                                    <#if contact.typeId??
                                            && contact.typeId == 4>
                                            selected
                                                    </#if>>
                                                Whatsapp
                                            </option>

                                            <option value="5"
                                                    <#if contact.typeId??
                                            && contact.typeId == 5>
                                            selected
                                                    </#if>>
                                                Linkedin
                                            </option>

                                        </select>

                                        <@spring.bind
                                        "resumeFormDto.contacts[${contact_index}].typeId"
                                        />

                                        <@spring.showErrors
                                        "<br>"
                                        "text-danger"
                                        />

                                    </div>


                                    <div class="mb-3">

                                        <label class="form-label">
                                            <@spring.message "resume.contact.value"/>
                                        </label>

                                        <@spring.formInput
                                        "resumeFormDto.contacts[${contact_index}].contactValue"
                                        'class="form-control"'
                                        />

                                        <@spring.showErrors
                                        "<br>"
                                        "text-danger"
                                        />

                                    </div>

                                </div>

                            </div>

                        </#list>
                        </div>

                        <button type="button"
                                id="add-contact"
                                class="btn btn-outline-primary mb-4">
                            <@spring.message "resume.contact.add"/>
                        </button>


                        <hr class="my-4">


                        <div class="d-flex gap-2">

                            <button type="submit"
                                    class="btn btn-primary">

                                <@spring.message "resume.create.button"/>

                            </button>

                            <a href="/profile"
                               class="btn btn-outline-secondary">

                                <@spring.message "resume.cancel"/>

                            </a>

                        </div>


                    </form>

                </div>

            </div>

        </div>

    </div>
    <script>
        'use strict';

        document.addEventListener('DOMContentLoaded', function () {

            const workContainer =
                document.getElementById('work-experiences-container');

            const educationContainer =
                document.getElementById('educations-container');

            const contactsContainer =
                document.getElementById('contacts-container');

            const addWorkButton =
                document.getElementById('add-work-experience');

            const addEducationButton =
                document.getElementById('add-education');

            const addContactButton =
                document.getElementById('add-contact');

            const form =
                document.getElementById('resume-form');



            addWorkButton.addEventListener('click', function () {

                const index =
                    workContainer.querySelectorAll('.work-experience-item').length;

                const block =
                    document.createElement('div');

                block.className =
                    'card mb-4 work-experience-item';

                block.innerHTML =
                    '<div class="card-body">' +

                    '<h5 class="mb-3">' +
                    'Опыт работы №' + (index + 1) +
                    '</h5>' +

                    '<div class="mb-3">' +

                    '<label class="form-label">' +
                    'Количество лет' +
                    '</label>' +

                    '<input type="number" ' +
                    'min="0" ' +
                    'class="form-control" ' +
                    'name="workExperiences[' +
                    index +
                    '].years">' +

                    '</div>' +


                    '<div class="mb-3">' +

                    '<label class="form-label">' +
                    'Компания' +
                    '</label>' +

                    '<input type="text" ' +
                    'class="form-control" ' +
                    'name="workExperiences[' +
                    index +
                    '].companyName">' +

                    '</div>' +


                    '<div class="mb-3">' +

                    '<label class="form-label">' +
                    'Должность' +
                    '</label>' +

                    '<input type="text" ' +
                    'class="form-control" ' +
                    'name="workExperiences[' +
                    index +
                    '].position">' +

                    '</div>' +


                    '<div class="mb-3">' +

                    '<label class="form-label">' +
                    'Обязанности' +
                    '</label>' +

                    '<textarea class="form-control" ' +
                    'rows="3" ' +
                    'name="workExperiences[' +
                    index +
                    '].responsibilities">' +
                    '</textarea>' +

                    '</div>' +

                    '</div>';

                workContainer.append(block);
            });


            addEducationButton.addEventListener('click', function () {

                const index =
                    educationContainer.querySelectorAll('.education-item').length;

                const block =
                    document.createElement('div');

                block.className =
                    'card mb-4 education-item';

                block.innerHTML =
                    '<div class="card-body">' +

                    '<h5 class="mb-3">' +
                    'Образование №' + (index + 1) +
                    '</h5>' +


                    '<div class="mb-3">' +

                    '<label class="form-label">' +
                    'Учебное заведение' +
                    '</label>' +

                    '<input type="text" ' +
                    'class="form-control" ' +
                    'name="educations[' +
                    index +
                    '].institution">' +

                    '</div>' +


                    '<div class="mb-3">' +

                    '<label class="form-label">' +
                    'Программа' +
                    '</label>' +

                    '<input type="text" ' +
                    'class="form-control" ' +
                    'name="educations[' +
                    index +
                    '].program">' +

                    '</div>' +


                    '<div class="mb-3">' +

                    '<label class="form-label">' +
                    'Дата начала' +
                    '</label>' +

                    '<input type="date" ' +
                    'class="form-control" ' +
                    'name="educations[' +
                    index +
                    '].startDate">' +

                    '</div>' +


                    '<div class="mb-3">' +

                    '<label class="form-label">' +
                    'Дата окончания' +
                    '</label>' +

                    '<input type="date" ' +
                    'class="form-control" ' +
                    'name="educations[' +
                    index +
                    '].endDate">' +

                    '</div>' +


                    '<div class="mb-3">' +

                    '<label class="form-label">' +
                    'Степень' +
                    '</label>' +

                    '<input type="text" ' +
                    'class="form-control" ' +
                    'name="educations[' +
                    index +
                    '].degree">' +

                    '</div>' +

                    '</div>';

                educationContainer.append(block);
            });



            addContactButton.addEventListener('click', function () {

                const index =
                    contactsContainer.querySelectorAll('.contact-item').length;

                const block =
                    document.createElement('div');

                block.className =
                    'card mb-4 contact-item';

                block.innerHTML =
                    '<div class="card-body">' +

                    '<h5 class="mb-3">' +
                    'Контакт №' + (index + 1) +
                    '</h5>' +


                    '<div class="mb-3">' +

                    '<label class="form-label">' +
                    'Тип контакта' +
                    '</label>' +

                    '<select class="form-select" ' +
                    'name="contacts[' +
                    index +
                    '].typeId">' +

                    '<option value="1">Telegram</option>' +
                    '<option value="2">Mail</option>' +
                    '<option value="3">Facebook</option>' +
                    '<option value="4">Whatsapp</option>' +
                    '<option value="5">Linkedin</option>' +

                    '</select>' +

                    '</div>' +


                    '<div class="mb-3">' +

                    '<label class="form-label">' +
                    'Контакт' +
                    '</label>' +

                    '<input type="text" ' +
                    'class="form-control" ' +
                    'name="contacts[' +
                    index +
                    '].contactValue">' +

                    '</div>' +

                    '</div>';

                contactsContainer.append(block);
            });



            form.addEventListener('submit', async function (event) {

                event.preventDefault();



                const workExperiences = [];

                document
                    .querySelectorAll('.work-experience-item')
                    .forEach(function (item) {

                        workExperiences.push({

                            years:
                                Number(
                                    item
                                        .querySelector('[name$=".years"]')
                                        .value
                                ),

                            companyName:
                            item
                                .querySelector('[name$=".companyName"]')
                                .value,

                            position:
                            item
                                .querySelector('[name$=".position"]')
                                .value,

                            responsibilities:
                            item
                                .querySelector('[name$=".responsibilities"]')
                                .value
                        });
                    });



                const educations = [];

                document
                    .querySelectorAll('.education-item')
                    .forEach(function (item) {

                        const startDate =
                            item
                                .querySelector('[name$=".startDate"]')
                                .value;

                        const endDate =
                            item
                                .querySelector('[name$=".endDate"]')
                                .value;

                        educations.push({

                            institution:
                            item
                                .querySelector('[name$=".institution"]')
                                .value,

                            program:
                            item
                                .querySelector('[name$=".program"]')
                                .value,

                            startDate:
                                startDate === ''
                                    ? null
                                    : startDate,

                            endDate:
                                endDate === ''
                                    ? null
                                    : endDate,

                            degree:
                            item
                                .querySelector('[name$=".degree"]')
                                .value
                        });
                    });



                const contacts = [];

                document
                    .querySelectorAll('.contact-item')
                    .forEach(function (item) {

                        contacts.push({

                            typeId:
                                Number(
                                    item
                                        .querySelector('[name$=".typeId"]')
                                        .value
                                ),

                            contactValue:
                            item
                                .querySelector('[name$=".contactValue"]')
                                .value
                        });
                    });




                const data = {

                    name:
                    document
                        .querySelector('[name="name"]')
                        .value,

                    categoryId:
                        Number(
                            document
                                .querySelector('[name="categoryId"]')
                                .value
                        ),

                    salary:
                        Number(
                            document
                                .querySelector('[name="salary"]')
                                .value
                        ),

                    active:
                    document
                        .getElementById('active')
                        .checked,

                    workExperiences:
                    workExperiences,

                    educations:
                    educations,

                    contacts:
                    contacts
                };


                console.log(data);



                const headers = {
                    'Content-Type': 'application/json'
                };


                <#if _csrf??>

                headers['${_csrf.headerName}'] =
                    '${_csrf.token}';

                </#if>



                try {

                    const response =
                        await fetch('/resumes/create', {

                            method: 'POST',

                            headers: headers,

                            body: JSON.stringify(data)
                        });


                    if (!response.ok) {

                        const errorText = await response.text();

                        console.error('STATUS:', response.status);
                        console.error('RESPONSE:', errorText);

                        throw new Error(
                            'Ошибка создания резюме. Status: ' + response.status
                        );
                    }


                    window.location.href =
                        '/profile';

                } catch (error) {

                    console.error('FETCH ERROR:', error);

                    alert(
                        'Не удалось сохранить резюме: ' + error.message
                    );
                }

            });

        });
    </script>
</@layout.layout>