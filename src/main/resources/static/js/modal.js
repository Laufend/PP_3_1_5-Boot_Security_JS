function modal_form_setting(form, id) {
    fetch("/api/user/" + id)
        .then(response => response.json())
        .then(content => {
            form.elements.id.value = content.id;
            form.elements.username.value = content.username;
            form.elements.password.value = content.password;
            form.elements.surname.value = content.surname;
            form.elements.age.value = content.age;
            form.elements.salary.value = content.salary;
            form.elements.specialization.value = content.specialization;
        });
}
function modalEdit(id) {
    let form = document.getElementById("editForm");
    modal_form_setting(form, id);
}
function modalDelete(id) {
    let form = document.getElementById("deleteForm");
    modal_form_setting(form, id);
}