var global;

$(document).on('click', '.btn-mark', function (){

    let firstName = $("#firstName").val();
    let lastName = $("#lastName").val();
    let classP = $("#classP").val();

    let url = "/"

    $.ajax({
        url:url,
        method: 'post',
        data: {
            "firstName": firstName,
            "lastName": lastName,
            "classP": classP
        },
        success: function (result) {
            global = result;

            let subjects = "";
            let marks = "";
            let str = "";

            for(key in result.data.data) {
                subjects = `<tr><td class="names">` + key + `</td></tr>`
                for(let i = 0; i < result.data.data[key].length; i++) {
                    for(k in result.data.data[key][i]) {
                        str += result.data.data[key][i][k] + " ";
                    }
                }
                marks = `<tr><td class="names">` + str + `</td></tr>`
            }

            let html = `
                <div style="display: none;" id="listPupils">
        <table class="table table-primary mt-5">
            <thead class="table-dark">
            <tr>
                <th style="width: 25%;" class="names" scope="col">Предмет</th>
                <th style="width: 75%;" class="names" scope="col">Оценки</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td class="names">` + subjects + `</td>
                <td class="names">` + marks + `</td>
            </tr>
            </tbody>
        </table>
    </div>
`
            console.log(html);
        }
    });

});