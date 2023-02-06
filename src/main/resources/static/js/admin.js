function admin_table() {
    let tab =  document.getElementById("js-tab-admin");
    let innerHTML = `
 <div class="inner-admin-panel border" id="admin_table">
    <h6>All Users</h6>
    <div class="bg-white">
        <table class="table table-striped caption-top">
        <tr>
                <th>Id</th>
                <th>Username</th>
                <th>Surname</th>
                <th>Age</th>
                <th>Salary</th>
                <th>Roles</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
`;
    fetch("/api/get_users_table")
        .then(response => response.json())
        .then(table => {
            for (let n in table) {
                let user = table[n];
                let user_roles = "";
                for (let i in user.authorities) {
                    user_roles += user.authorities[i].role + " ";
                }
                innerHTML += `
<tr>
    <td>${user.id}</td>
    <td>${user.username}</td>
    <td>${user.surname}</td>
    <td>${user.age}</td>
    <td>${user.salary}</td>
    <td>${user_roles}</td>
    <td>
        <a class="btn btn-info"  href="#" id="${user.id}" type="button" data-toggle="modal" data-target="#editingModal" onclick="modalEdit(${user.id})">Edit</a>
    </td>
    <td>
        <a class="btn btn-danger" href="#" type="button" data-toggle="modal" data-target="#deletingModal" onclick="modalDelete(${user.id})">Delete</a>
    </td>
</tr>
                        `;

            }
            innerHTML+="</table>";
            tab.innerHTML = innerHTML;
        });

    document.getElementById("tab-2").classList.remove("active");
    document.getElementById("tab-1").classList.add("active");
}
function add_user_form() {
    let tab =  document.getElementById("js-tab-admin");
    let innerHTML = `
<div class="inner-admin-panel-add border">
    <h6>Add new user</h6>
    <div class="border">
        <form method="get" action="#" onsubmit="fetch_add(this); return false;" id="js_new_user_form">
            <div class="form-group">
                <label for="login">Login</label>
                <input type="text" class="form-control" name="username" id="username">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="text" class="form-control" name="password" id="password">
            </div>
            <div class="form-group">
                <label for="surname">Surname</label>
                <input type="text" class="form-control" name="surname" id="surname">
            </div>
            <div class="form-group">
                <label for="age">Age</label>
                <input type="text" class="form-control" name="age" id="age">
            </div>
            <div class="form-group">
                <label for="salary">Salary</label>
                <input type="text" class="form-control" name="salary" id="salary">
            </div>
            <div class="form-group">
                <label for="specialization">Specialization</label>
                <input type="text" class="form-control" name="specialization" id="specialization">
            </div>
            <div class="form-group">
                <label for="authorities">Roles</label>
                <select multiple class="form-control" name="authorities" id="authorities">
                    <option value="ROLE_USER" selected>USER</option>
                    <option value="ROLE_ADMIN">ADMIN</option>
                </select>
            </div>
            <input type="submit" class="btn btn-success btn-lg" value="Add new user">
        </form>
    </div>
</div>`;
    tab.innerHTML = innerHTML;
    document.getElementById("tab-1").classList.remove("active");
    document.getElementById("tab-2").classList.add("active");
}

function change_admin_tab(tab_id) {
    if (tab_id == 2) {
        add_user_form();
    }
    if (tab_id == 1) {
        admin_table();
    }
}