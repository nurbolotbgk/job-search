<#import "../layout.ftlh" as layout>
<#import "/spring.ftl" as spring>

<@layout.layout title="Редактирование резюме">

    <div class="row justify-content-center">
        <div class="col-md-10 col-lg-8">

            <div class="card shadow-sm">
                <div class="card-body p-4">

                    <h2 class="text-center mb-4">
                        <@spring.message "resume.edit.title"/>
                    </h2>

                    <form action="/resumes/${resumeId}/edit"
                          method="post"
                          id="resumeEditForm">

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


                        <div id="workExperienceContainer">

                            <#list resumeFormDto.workExperiences as workExperience>

                                <div class="card mb-4 work-experience-item">
                                    <div class="card-body">

                                        <div class="d-flex justify-content-between align-items-center mb-3">

                                            <h5 class="mb-0">
                                                <@spring.message "resume.experience.number"/>
                                                <span class="work-experience-number">
                                                    ${workExperience_index + 1}
                                                </span>
                                            </h5>

                                            <button type="button"
                                                    class="btn btn-outline-danger btn-sm remove-work-experience">
                                                Удалить
                                            </button>

                                        </div>


                                        <input type="hidden"
                                               class="work-id"
                                               name="workExperiences[${workExperience_index}].id"
                                               value="${workExperience.id!}">


                                        <div class="mb-3">

                                            <label class="form-label">
                                                <@spring.message "resume.experience.years"/>
                                            </label>

                                            <@spring.formInput
                                            "resumeFormDto.workExperiences[${workExperience_index}].years"
                                            'type="number"
                                             min="0"
                                             class="form-control work-years"'
                                            />

                                            <@spring.showErrors
                                            "<br>"
                                            "text-danger"
                                            />

                                        </div>


                                        <div class="mb-3">

                                            <label class="form-label">
                                                <@spring.message "resume.experience.company"/>
                                            </label>

                                            <@spring.formInput
                                            "resumeFormDto.workExperiences[${workExperience_index}].companyName"
                                            'class="form-control work-company"'
                                            />

                                            <@spring.showErrors
                                            "<br>"
                                            "text-danger"
                                            />

                                        </div>


                                        <div class="mb-3">

                                            <label class="form-label">
                                                <@spring.message "resume.experience.position"/>
                                            </label>

                                            <@spring.formInput
                                            "resumeFormDto.workExperiences[${workExperience_index}].position"
                                            'class="form-control work-position"'
                                            />

                                            <@spring.showErrors
                                            "<br>"
                                            "text-danger"
                                            />

                                        </div>


                                        <div class="mb-3">

                                            <label class="form-label">
                                                <@spring.message "resume.experience.responsibilities"/>
                                            </label>

                                            <@spring.formTextarea
                                            "resumeFormDto.workExperiences[${workExperience_index}].responsibilities"
                                            'class="form-control work-responsibilities"
                                             rows="3"'
                                            />

                                        </div>

                                    </div>
                                </div>

                            </#list>

                        </div>


                        <button type="button"
                                id="addWorkExperience"
                                class="btn btn-outline-primary mb-4">

                            <@spring.message "resume.experience.add"/>

                        </button>


                        <hr class="my-4">


                        <h4 class="mb-3">
                            <@spring.message "resume.section.education"/>
                        </h4>


                        <div id="educationContainer">

                            <#list resumeFormDto.educations as education>

                                <div class="card mb-4 education-item">
                                    <div class="card-body">

                                        <div class="d-flex justify-content-between align-items-center mb-3">

                                            <h5 class="mb-0">
                                                <@spring.message "resume.education.number"/>
                                                <span class="education-number">
                                                    ${education_index + 1}
                                                </span>
                                            </h5>

                                            <button type="button"
                                                    class="btn btn-outline-danger btn-sm remove-education">
                                                Удалить
                                            </button>

                                        </div>


                                        <input type="hidden"
                                               class="education-id"
                                               name="educations[${education_index}].id"
                                               value="${education.id!}">


                                        <div class="mb-3">

                                            <label class="form-label">
                                                <@spring.message "resume.education.institution"/>
                                            </label>

                                            <@spring.formInput
                                            "resumeFormDto.educations[${education_index}].institution"
                                            'class="form-control education-institution"'
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
                                            'class="form-control education-program"'
                                            />

                                        </div>


                                        <div class="mb-3">

                                            <label class="form-label">
                                                <@spring.message "resume.education.start"/>
                                            </label>

                                            <@spring.formInput
                                            "resumeFormDto.educations[${education_index}].startDate"
                                            'type="date"
                                             class="form-control education-start-date"'
                                            />

                                            <div class="form-text">
                                                <@spring.message "resume.education.date.format"/>
                                            </div>

                                        </div>


                                        <div class="mb-3">

                                            <label class="form-label">
                                                <@spring.message "resume.education.end"/>
                                            </label>

                                            <@spring.formInput
                                            "resumeFormDto.educations[${education_index}].endDate"
                                            'type="date"
                                             class="form-control education-end-date"'
                                            />

                                            <div class="form-text">
                                                <@spring.message "resume.education.date.format"/>
                                            </div>

                                            <@spring.bind
                                            "resumeFormDto.educations[${education_index}].datesValid"
                                            />

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
                                            'class="form-control education-degree"'
                                            />

                                        </div>

                                    </div>
                                </div>

                            </#list>

                        </div>


                        <button type="button"
                                id="addEducation"
                                class="btn btn-outline-primary mb-4">

                            <@spring.message "resume.education.add"/>

                        </button>


                        <hr class="my-4">



                        <h4 class="mb-3">
                            <@spring.message "resume.section.contacts"/>
                        </h4>


                        <div id="contactContainer">

                            <#list resumeFormDto.contacts as contact>

                                <div class="card mb-4 contact-item">
                                    <div class="card-body">

                                        <div class="d-flex justify-content-between align-items-center mb-3">

                                            <h5 class="mb-0">
                                                <@spring.message "resume.contact.number"/>
                                                <span class="contact-number">
                                                    ${contact_index + 1}
                                                </span>
                                            </h5>

                                            <button type="button"
                                                    class="btn btn-outline-danger btn-sm remove-contact">
                                                Удалить
                                            </button>

                                        </div>


                                        <input type="hidden"
                                               class="contact-id"
                                               name="contacts[${contact_index}].id"
                                               value="${contact.id!}">


                                        <div class="mb-3">

                                            <label class="form-label">
                                                <@spring.message "resume.contact.type"/>
                                            </label>

                                            <select name="contacts[${contact_index}].typeId"
                                                    class="form-select contact-type">

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
                                            'class="form-control contact-value"'
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
                                id="addContact"
                                class="btn btn-outline-primary mb-4">

                            <@spring.message "resume.contact.add"/>

                        </button>


                        <hr class="my-4">


                        <div class="d-flex gap-2">

                            <button type="submit"
                                    class="btn btn-primary">
                                <@spring.message "resume.edit.save"/>
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


        const workContainer =
            document.getElementById('workExperienceContainer');

        const addWorkButton =
            document.getElementById('addWorkExperience');


        function updateWorkExperienceNumbers() {

            const items =
                workContainer.querySelectorAll('.work-experience-item');

            items.forEach(function (item, index) {

                const number =
                    item.querySelector('.work-experience-number');

                number.textContent = index + 1;


                const id =
                    item.querySelector('.work-id');

                const years =
                    item.querySelector('.work-years');

                const company =
                    item.querySelector('.work-company');

                const position =
                    item.querySelector('.work-position');

                const responsibilities =
                    item.querySelector('.work-responsibilities');


                if (id) {
                    id.name =
                        `workExperiences[${index}].id`;
                }

                years.name =
                    `workExperiences[${index}].years`;

                company.name =
                    `workExperiences[${index}].companyName`;

                position.name =
                    `workExperiences[${index}].position`;

                responsibilities.name =
                    `workExperiences[${index}].responsibilities`;

            });

        }


        function addRemoveWorkHandler(button) {

            button.addEventListener('click', function () {

                const block =
                    button.closest('.work-experience-item');

                block.remove();

                updateWorkExperienceNumbers();

            });

        }


        document
            .querySelectorAll('.remove-work-experience')
            .forEach(function (button) {

                addRemoveWorkHandler(button);

            });


        addWorkButton.addEventListener('click', function () {

            const index =
                workContainer
                    .querySelectorAll('.work-experience-item')
                    .length;


            const block =
                document.createElement('div');

            block.className =
                'card mb-4 work-experience-item';


            block.innerHTML = `
                <div class="card-body">

                    <div class="d-flex justify-content-between align-items-center mb-3">

                        <h5 class="mb-0">
                            Опыт работы №
                            <span class="work-experience-number">
                                ${index + 1}
                            </span>
                        </h5>

                        <button type="button"
                                class="btn btn-outline-danger btn-sm remove-work-experience">
                            Удалить
                        </button>

                    </div>


                    <div class="mb-3">

                        <label class="form-label">
                            Количество лет
                        </label>

                        <input type="number"
                               min="0"
                               class="form-control work-years"
                               name="workExperiences[${index}].years">

                    </div>


                    <div class="mb-3">

                        <label class="form-label">
                            Название компании
                        </label>

                        <input type="text"
                               class="form-control work-company"
                               name="workExperiences[${index}].companyName">

                    </div>


                    <div class="mb-3">

                        <label class="form-label">
                            Должность
                        </label>

                        <input type="text"
                               class="form-control work-position"
                               name="workExperiences[${index}].position">

                    </div>


                    <div class="mb-3">

                        <label class="form-label">
                            Обязанности
                        </label>

                        <textarea class="form-control work-responsibilities"
                                  rows="3"
                                  name="workExperiences[${index}].responsibilities"></textarea>

                    </div>

                </div>
            `;


            workContainer.appendChild(block);


            const removeButton =
                block.querySelector('.remove-work-experience');

            addRemoveWorkHandler(removeButton);

            updateWorkExperienceNumbers();

        });



        const educationContainer =
            document.getElementById('educationContainer');

        const addEducationButton =
            document.getElementById('addEducation');


        function updateEducationNumbers() {

            const items =
                educationContainer.querySelectorAll('.education-item');

            items.forEach(function (item, index) {

                const number =
                    item.querySelector('.education-number');

                number.textContent = index + 1;


                const id =
                    item.querySelector('.education-id');

                const institution =
                    item.querySelector('.education-institution');

                const program =
                    item.querySelector('.education-program');

                const startDate =
                    item.querySelector('.education-start-date');

                const endDate =
                    item.querySelector('.education-end-date');

                const degree =
                    item.querySelector('.education-degree');


                if (id) {
                    id.name =
                        `educations[${index}].id`;
                }

                institution.name =
                    `educations[${index}].institution`;

                program.name =
                    `educations[${index}].program`;

                startDate.name =
                    `educations[${index}].startDate`;

                endDate.name =
                    `educations[${index}].endDate`;

                degree.name =
                    `educations[${index}].degree`;

            });

        }


        function addRemoveEducationHandler(button) {

            button.addEventListener('click', function () {

                const block =
                    button.closest('.education-item');

                block.remove();

                updateEducationNumbers();

            });

        }


        document
            .querySelectorAll('.remove-education')
            .forEach(function (button) {

                addRemoveEducationHandler(button);

            });


        addEducationButton.addEventListener('click', function () {

            const index =
                educationContainer
                    .querySelectorAll('.education-item')
                    .length;


            const block =
                document.createElement('div');

            block.className =
                'card mb-4 education-item';


            block.innerHTML = `
                <div class="card-body">

                    <div class="d-flex justify-content-between align-items-center mb-3">

                        <h5 class="mb-0">
                            Образование №
                            <span class="education-number">
                                ${index + 1}
                            </span>
                        </h5>

                        <button type="button"
                                class="btn btn-outline-danger btn-sm remove-education">
                            Удалить
                        </button>

                    </div>


                    <div class="mb-3">

                        <label class="form-label">
                            Учебное заведение
                        </label>

                        <input type="text"
                               class="form-control education-institution"
                               name="educations[${index}].institution">

                    </div>


                    <div class="mb-3">

                        <label class="form-label">
                            Программа
                        </label>

                        <input type="text"
                               class="form-control education-program"
                               name="educations[${index}].program">

                    </div>


                    <div class="mb-3">

                        <label class="form-label">
                            Дата начала
                        </label>

                        <input type="date"
                               class="form-control education-start-date"
                               name="educations[${index}].startDate">

                    </div>


                    <div class="mb-3">

                        <label class="form-label">
                            Дата окончания
                        </label>

                        <input type="date"
                               class="form-control education-end-date"
                               name="educations[${index}].endDate">

                    </div>


                    <div class="mb-3">

                        <label class="form-label">
                            Степень
                        </label>

                        <input type="text"
                               class="form-control education-degree"
                               name="educations[${index}].degree">

                    </div>

                </div>
            `;


            educationContainer.appendChild(block);


            const removeButton =
                block.querySelector('.remove-education');

            addRemoveEducationHandler(removeButton);

            updateEducationNumbers();

        });


        const contactContainer =
            document.getElementById('contactContainer');

        const addContactButton =
            document.getElementById('addContact');


        function updateContactNumbers() {

            const items =
                contactContainer.querySelectorAll('.contact-item');

            items.forEach(function (item, index) {

                const number =
                    item.querySelector('.contact-number');

                number.textContent = index + 1;


                const id =
                    item.querySelector('.contact-id');

                const type =
                    item.querySelector('.contact-type');

                const value =
                    item.querySelector('.contact-value');


                if (id) {
                    id.name =
                        `contacts[${index}].id`;
                }

                type.name =
                    `contacts[${index}].typeId`;

                value.name =
                    `contacts[${index}].contactValue`;

            });

        }


        function addRemoveContactHandler(button) {

            button.addEventListener('click', function () {

                const block =
                    button.closest('.contact-item');

                block.remove();

                updateContactNumbers();

            });

        }


        document
            .querySelectorAll('.remove-contact')
            .forEach(function (button) {

                addRemoveContactHandler(button);

            });


        addContactButton.addEventListener('click', function () {

            const index =
                contactContainer
                    .querySelectorAll('.contact-item')
                    .length;


            const block =
                document.createElement('div');

            block.className =
                'card mb-4 contact-item';


            block.innerHTML = `
                <div class="card-body">

                    <div class="d-flex justify-content-between align-items-center mb-3">

                        <h5 class="mb-0">
                            Контакт №
                            <span class="contact-number">
                                ${index + 1}
                            </span>
                        </h5>

                        <button type="button"
                                class="btn btn-outline-danger btn-sm remove-contact">
                            Удалить
                        </button>

                    </div>


                    <div class="mb-3">

                        <label class="form-label">
                            Тип контакта
                        </label>

                        <select class="form-select contact-type"
                                name="contacts[${index}].typeId">

                            <option value="1">
                                Telegram
                            </option>

                            <option value="2">
                                Mail
                            </option>

                            <option value="3">
                                Facebook
                            </option>

                            <option value="4">
                                Whatsapp
                            </option>

                            <option value="5">
                                Linkedin
                            </option>

                        </select>

                    </div>


                    <div class="mb-3">

                        <label class="form-label">
                            Значение контакта
                        </label>

                        <input type="text"
                               class="form-control contact-value"
                               name="contacts[${index}].contactValue">

                    </div>

                </div>
            `;


            contactContainer.appendChild(block);


            const removeButton =
                block.querySelector('.remove-contact');

            addRemoveContactHandler(removeButton);

            updateContactNumbers();

        });



        updateWorkExperienceNumbers();
        updateEducationNumbers();
        updateContactNumbers();

    </script>

</@layout.layout>