function initPage(){
    changeRole();
}

function changeRole(){

    let role = document.getElementById("selectRole");

    if(role.value === "Ученик") {

        document.getElementById("username_div").style.display = "none"
        document.getElementById("username").required = false;

        document.getElementById("password_div").style.display = "none"
        document.getElementById("password").required = false;

        document.getElementById("classP_div").style.display = "block";
        document.getElementById("classP").require = true;

    } else {
        document.getElementById("username_div").style.display = "block"
        document.getElementById("username").required = true;

        document.getElementById("password_div").style.display = "block"
        document.getElementById("password").required = true;

        document.getElementById("classP_div").style.display = "none";
        document.getElementById("classP").require = false;

    }
}