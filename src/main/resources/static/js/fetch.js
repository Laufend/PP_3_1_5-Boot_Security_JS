function fetch_add() {
    let formdata = $("#js_new_user_form").serialize();
    let url = "/api/add_user?" + formdata;
    fetch(url
        , {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify()
    })
        .then((response) => {
            change_admin_tab(1);
        })
        .catch(function (err) {
            console.error('Fetch Error :-S', err);
        });
}

function fetch_delete(id) {
    let url = "/api/delete_user/" + id;
    fetch(url
        , {
            method: 'DELETE',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(id)
        })
        .then((response) => {
            $("#deletingModal").modal("hide");
            change_admin_tab(1);
        })
        .catch(function (err) {
            console.error('Fetch Error :-S', err);
        });
}
function fetch_edit(id) {
    let formdata = $("#editForm").serialize();
    let url = "/api/edit_user/" + id + "/?" + formdata;
    fetch(url
        , {
            method: 'PUT',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(id)
        })
        .then((response) => {
            $("#editingModal").modal("hide");
            change_admin_tab(1);
        })
        .catch(function (err) {
            console.error('Fetch Error :-S', err);
        });
}