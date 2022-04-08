$(document).ready(function() {

    $("#selectedSubject").change(function () {

        $("#selectedClass").children().remove();

        let url = "/teacher/classes";
        let id = $(".teacherSelectDiv").attr("id");
        let selectedSubject = $("#selectedSubject").val();

        $.ajax({
            url: url,
            method: 'post',
            data: {
                'id': id,
                'selectedSubject': selectedSubject
            },
            dataType: 'json',
            success: function (result) {
                let data = result.data;
                data = data.replace("[", "");
                data = data.replace("]", "");
                let array = data.split(", ");

                let options = "";
                for(let i = 0; i < array.length; i++)
                    options += "<option>"+ array[i] + "</option>";
                $("#selectedClass").append(options);
            }
        });
    });


    $('#selectedSubject').triggerHandler('change');
});

$(document).on("click", ".accordion", function (){
    $(this).toggleClass("active");
    $(this).next().slideToggle(200);
})


$(document).on("click", ".btn-post", function (){

    $(".pupilContent").children().remove();

    let selectedClass = $("#selectedClass").val();
    let selectedSubject = $("#selectedSubject").val();

    let url = "/teacher";
    $.ajax({
        url: url,
        method: 'post',
        data: {
            "selectedClass": selectedClass,
            "selectedSubject": selectedSubject
        },
        success: function (result) {
            let text_json = JSON.parse(result);

            for(let i = 0; i < text_json.data.length; i++) {

                let name = text_json.data[i].firstName + " " + text_json.data[i].lastName + " " + text_json.data[i].classP;
                let tableTr = ``;


                if(text_json.data[i].data != null){
                    for(let d = 0; d < text_json.data[i].data[selectedSubject].length; d++){

                        for(let key in text_json.data[i].data[selectedSubject][d]) {

                            tableTr += `
                                <tr>
                                
                                <td class="dates names" scope="col">`+ key +`</td>
                                <td class="names" scope="col">`+ text_json.data[i].data[selectedSubject][d][key] +`</td>
                                <td class="names" scope="col">
                                
                                <span>
                    <button class="btnDeleteMark btn btn-danger btn-sm">Удалить</button>
                    </span>
                                </td>
                                
                                </tr>
                            `
                        }
                    }
                }

                let html = `
                <div id="` + text_json.data[0].id + `" class="accordion-contianer">
                <button class="accordion">`+ name +`</button>
                <div class="panel">
                <span style="margin-top: 10px" class="labelAndSelect">
                    <label for="date">Дата:</label>
                    <input class="md-form md-outline input-with-post-icon datepicker"
                           type="date" id="date" name="date" min="2022-01-01" max="2023-12-31"/>
                    <label for="mark">Оценка</label>
                    <input type="text" style="width: 40px" id="mark" name="mark">
                        <a style="vertical-align: baseline;" class="btn btn-dark btn-sm btn-mark">Поставить оценку</a>
                    </span>
                    <table class="table table-primary mt-5">
                        <thead class="table-dark">
                        <tr>
                            <th class="names" scope="col">Дата</th>
                            <th class="names" scope="col">Оценка</th>
                            <th class="names" scope="col">Действие</th>
                        </tr>
                        </thead>
                        <tbody>` + tableTr + `</tbody>
                    </table>
                </div>
            </div>
    `
                $(".pupilContent").append(html);
            }
        }
    });
});


$(document).on("click", ".btn-mark", function (){

    let button = $(this);

    let id = button.parents().find(".accordion-contianer").attr("id");
    let date = button.parents().find("#date").val();
    let mark = button.parents().find("#mark").val();
    let selectedSubject = $("#selectedSubject").val();

    let url = "/teacher/pupil/mark";


    $.ajax({
        url: url,
        method: 'post',
        data: {
            "id": id,
            "selectedSubject": selectedSubject,
            "date": date,
            "mark": mark
        },
        success: function (result) {
            let text_json = JSON.parse(result);

            let element = null;

            let elems = $(".dates");
            for(let i = 0; i < elems.length; i++) {
                if($(elems[i]).text() === text_json.date) {
                    element = $(elems[i]);
                }
            }

            let html = `
            <tr>
                                
                <td class="names dates" scope="col">`+ text_json.date +`</td>
                <td class="names" scope="col">`+ text_json.mark +`</td>
                <td class="names" scope="col">
                     <span>
                    <button class="btnDeleteMark btn btn-danger btn-sm">Удалить</button>
                    </span>
                </td>
                                
            </tr>
            `;

            if(element != null) element.parent().replaceWith(html);
            else button.closest("span").next().find("tbody").append(html);
        }
    });
});


$(document).on("click", ".btnDeleteMark", function (){

    let button = $(this);
    let id = button.parents().find(".accordion-contianer").attr("id");
    let date = button.parent().parent().parent().find(".dates").text();
    let selectedSubject = $("#selectedSubject").val();
    let root = button.parent().parent().parent();

    console.log(id);
    console.log(date);
    console.log(selectedSubject);
    console.log(root)

    let url = "/teacher/pupils/delete/mark";

    $.ajax({
        url: url,
        method: 'post',
        data:{
            "id": id,
            "selectedSubject": selectedSubject,
            "date": date
        },
        success: function () {
            root.remove();
        }
    });


});
