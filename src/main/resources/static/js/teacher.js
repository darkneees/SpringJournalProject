$(document).ready(function() {

    $("#selectedSubject").change(function () {

        $("#selectedClass").children().remove();

        let url = "/teacher/classes";
        let id = $(".teacherSelectDiv").attr("id");
        let selectedSubject = $("#selectedSubject").val();

        if(selectedSubject != null) {
            $.ajax({
                url: url,
                method: 'post',
                data: {
                    'id': id,
                    'selectedSubject': selectedSubject
                },
                dataType: 'json',
                success: function (result) {
                    let options = "";
                    for(let i = 0; i < result.data.length; i++)
                        options += "<option>"+ result.data[i] + "</option>";

                    $("#selectedClass").append(options);
                }
            });
        }
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
    let url = $(this).data("value");

    $.ajax({
        url: url,
        method: 'post',
        data: {
            "selectedClass": selectedClass,
            "selectedSubject": selectedSubject
        },
        success: function (result) {
            console.log(result)
            for(let i = 0; i < result.data.length; i++) {
                let name = result.data[i].firstName + " " + result.data[i].lastName + " " + result.data[i].classP;
                let tableTr = ``;
                if(result.data[i].data != null && result.data[i].data[selectedSubject] != null){
                    for(let d = 0; d < result.data[i].data[selectedSubject].length; d++){
                        for(let key in result.data[i].data[selectedSubject][d]) {
                            tableTr += `
                                <tr>
                                <td class="dates names" scope="col">`+ key +`</td>
                                <td class="names" scope="col">`+ result.data[i].data[selectedSubject][d][key] +`</td>
                                <td class="names" scope="col"><span><button data-value="/teacher/pupils/delete/mark/` + result.data[i].id +`" class="btnDeleteMark btn btn-danger btn-sm">Удалить</button></span></td>
                                </tr>
                            `
                        }
                    }
                }

                let html = `
                <div class="accordion-contianer">
                <button class="accordion">`+ name +`</button>
                <div class="panel">
                <span style="margin-top: 10px" class="labelAndSelect">
                    <label for="date">Дата:</label>
                    <input class="md-form md-outline input-with-post-icon datepicker"
                           type="date" id="date" name="date" min="2022-01-01" max="2023-12-31"/>
                    <label for="mark">Оценка</label>
                    <input type="text" style="width: 40px" id="mark" name="mark">
                        <a data-value="/teacher/pupil/mark/` + result.data[i].id +`" style="vertical-align: baseline;" class="btn btn-dark btn-sm btn-mark">Поставить оценку</a>
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
    let root = button.parent().parent();

    let date = root.find("#date").val();
    let mark = root.find("#mark").val();
    let selectedSubject = $("#selectedSubject").val();
    let url = $(this).data("value");


    $.ajax({
        url: url,
        method: 'post',
        data: {
            "selectedSubject": selectedSubject,
            "date": date,
            "mark": mark
        },
        success: function (result) {
            let element = null;
            let elems = $(".dates");
            for(let i = 0; i < elems.length; i++) {
                if($(elems[i]).text() === result.date) {
                    element = $(elems[i]);
                }
            }

            let html = `
            <tr>
                                
                <td class="names dates" scope="col">`+ result.date +`</td>
                <td class="names" scope="col">`+ result.mark +`</td>
                <td class="names" scope="col">
                     <span>
                    <button data-value="/teacher/pupils/delete/mark/` + result.id +`" class="btnDeleteMark btn btn-danger btn-sm">Удалить</button>
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
    let date = button.parent().parent().parent().find(".dates").text();
    let selectedSubject = $("#selectedSubject").val();
    let root = button.parent().parent().parent();
    let url = $(this).data("value");

    $.ajax({
        url: url,
        method: 'post',
        data:{
            "selectedSubject": selectedSubject,
            "date": date
        },
        success: function () {
            root.remove();
        }
    });


});
