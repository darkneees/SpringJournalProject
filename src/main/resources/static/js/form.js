$(document).ready(function() {

    $("select").change(function () {

        let role = $("#selectRole");

        let username_div = $("#username_div");
        let username = $("#username");

        let password_div = $("#password_div");
        let password = $("#password");

        let classP_div = $("#classP_div");
        let classP = $("#classP");

        if(role.val() === "Ученик") {

            username_div.css("display", "none");
            username.prop('required', false)

            password_div.css("display", "none");
            password.prop('required', false)

            classP_div.css("display", "block");
            classP_div.prop('required', true)

        } else {
            username_div.css("display", "block");
            username.prop('required', true)

            password_div.css("display", "block");
            password.prop('required', true)

            classP_div.css("display", "none");
            classP.prop('required', false)
        }
    });

    $('select').triggerHandler('change');

});


