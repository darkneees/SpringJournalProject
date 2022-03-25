function changeRole(){

    let role = document.getElementById("selectRole");
    if(role.value === "PUPIL") {
        document.getElementById("username_div").style.visibility = "hidden";
        document.getElementById("password_div").style.visibility = "hidden";
        document.getElementById("classP").style.visibility = "visible";
    } else {
        document.getElementById("username_div").style.visibility = "visible";
        document.getElementById("password_div").style.visibility = "visible";
        document.getElementById("classP").style.visibility = "hidden";
    }
}