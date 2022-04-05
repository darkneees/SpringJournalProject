function changeColor(){
    let elems = document.getElementsByClassName("borderMark");
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

$(document).on("click", ".btn-post", function (){
    let id = $(this).attr('id');
    let date = $("#date").val()
    let mark = $("#mark").val()
    let selectedSubject = $("#selectedSubject").val();
    let selectedClass = $("#selectedClass").val();

    // /teacher/mark/{id}/{subject}
    url = "/teacher/mark/" + id + "/" + selectedSubject;

    $.ajax({
        url: url,
        method: 'post',
        data: {
            "selectedSubject": selectedSubject,
            "selectedClass": selectedClass,
            "date": date,
            "mark": mark
        },
        success: function (){
            $.ajax({
                url: 'teacher',
                method: 'post',
                dataType: 'html',
                data: {
                    "selectedSubject": selectedSubject,
                    "selectedClass": selectedClass
                },
                success: function (result){
                    document.open();
                    document.write(result);
                    document.close();
                }
            });
        }
    });

})