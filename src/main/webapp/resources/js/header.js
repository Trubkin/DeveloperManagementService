var prefix = '/developer-management-service-1.0-SNAPSHOT';

document.addEventListener('DOMContentLoaded', enableButtons(), false);

function enableButtons() {
    $.ajax({
        type: 'GET',
        url: prefix + '/account/',
        dataType: 'json',
        async: true,
        success: function (result) {
            switch(result.roleId) {
                case 1:
                    document.getElementById("adminButton").disabled = false;
                    console.log("1");
                    return;
                case 2:
                    document.getElementById("managerButton").disabled = false;
                    console.log("2");
                    return;
                case 3:
                    document.getElementById("devButton").disabled = false;
                    console.log("3");
                    return;
                default:
                    console.log("!");
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            // если запрос неудачный (например 403 ошибка), никакие кнопки в шапке активировать не нужно
        }
    });
}