

function changeColor(){
    let elems = document.getElementsByClassName("borderMark");

    console.log(elems)

    for(let i = 0; i < elems.length; i++){
        switch (elems[i].textContent) {
            case '2': elems[i].style.backgroundColor = "#FF0000";
            break;
            case '3': elems[i].style.backgroundColor = "#FFA500";
            break
            case '4': elems[i].style.backgroundColor = "#00FF00";
            break
            case '5': elems[i].style.backgroundColor = "#00FF00";
            break;
        }
    }
}