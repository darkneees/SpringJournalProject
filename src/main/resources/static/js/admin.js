$(document).ready(function() {
  $(".accordion").on("click", function() {
    $(this).toggleClass("active");
    $(this).next().slideToggle(200);
  });

  $("#selectRole").change(function () {

    let elem = $(this);
    let admins = $("#listAdmins");
    let teachers = $("#listTeachers");
    let pupils = $("#listPupils");

    if(elem.val() === "ROLE_ADMIN") {

      admins.css("display", "block");
      teachers.css("display", "none");
      pupils.css("display", "none");

    } else if(elem.val() === "ROLE_TEACHER") {

      admins.css("display", "none");
      teachers.css("display", "block");
      pupils.css("display", "none");

    } else {
      admins.css("display", "none");
      teachers.css("display", "none");
      pupils.css("display", "block");
    }
  });

  $('#selectRole').triggerHandler('change');

});

$(document).on("click", ".btnEdit", function (){

  let id = $(this).parents(".cont").attr('id');
  let url = '/admin/edit/' + id;

  $.ajax({
    url: url,
    method: 'get',
    success: function (){
      window.location = url;
    }
  });
});

$(document).on("click", ".btnEditPupil", function (){

  let id = $(this).parents(".pupilCont").attr('id');
  let url = '/admin/editPupil/' + id;

  $.ajax({
    url: url,
    method: 'get',
    success: function (){
      window.location = url;
    }
  });
});

$(document).on("click", ".btnDeletePupil", function (){

  let id = $(this).parents(".pupilCont").attr('id');
  let root = $(this).parents(".pupilCont");
  let url = "/admin/deletePupil/" + id;

  $.ajax({
    url: url,
    method: 'post',
    dataType: 'json',
    success: function () {
      root.remove();
    }
  });
});


$(document).on("click", ".btnDelete", function (){

  let id = $(this).parents(".cont").attr('id');
  let root = $(this).parents(".cont");
  let url = "/admin/delete/" + id;

  $.ajax({
    url: url,
    method: 'post',
    dataType: 'json',
    success: function () {
      root.remove();
    }
  });
});

$(document).on("click", ".btnDeleteSubject", function (){
  let id = $(this).parents(".cont").attr('id');
  let str = '/admin/delete/subject/' + id;
  let subject = $(this).closest("td").next("td").text();
  let elem = $(this).parent().parent();

  $.ajax({
    url: str,
    method: 'post',
    data: {
      "subject": subject
    },
    success: function (){
      elem.remove();
    }
  });
});

$(document).on("click", ".btnDeleteClass", function (){

  let id = $(this).parents(".cont").attr('id');
  let selectedSubject = $(this).parent().parent().prev().prev().text()
  let selectedClass = $(this).parent().find(":selected").val();
  let url = '/admin/delete/class/' + id;

  let button = $(this);

  $.ajax({
    url: url,
    method: 'post',
    data: {
      "selectedClass": selectedClass,
      "selectedSubject": selectedSubject,
    },
    dataType: 'json',
    success: function (data){
      if(data.data === "null")
        button.parent().parent().parent().remove();
      else {
        button.parent().parent().prev().text(data.data)
        let options = button.next().children();
        for(let i = 0; i < options.length; i++){
          if(options[i].text === selectedClass) options[i].remove();
        }
      }
    }
  })
});

$(document).on("click", ".buttonAddClass", function (){

  let id = $(this).parents(".cont").attr('id');
  let selectSubject = $(this).parent().find("#selectSubject").val();
  let classP = $(this).parent().find("#classP").val();
  let url = '/admin/add/class/' + id;

  console.log(selectSubject)

  $.ajax({
    url: url,
    method: 'post',
    data: {
      'selectSubject': selectSubject,
      'classP': classP
    },
    dataType: 'json',
    success: function (data){
      let str = data.data.replace("[", "");
      str = str.replace("]", "");
      str = str.replaceAll(",", "");
      str = str.split(" ");
      let options = "";

      for(let i = 0; i < str.length; i++)
        options += "<option>"+ str[i] + "</option>";

      let element = null;

      let elems = $(".subject");
      for(let i = 0; i < elems.length; i++) {
        if($(elems[i]).text() === selectSubject) {
          element = $(elems[i]);
        }
      }

      let html = `
  <tr>
    <td>
      <button class="btnDeleteSubject btn btn-danger btn-sm">Удалить</button>
    </td>
    <td class="subject">` + selectSubject + `</td>
    <td>` + data.data + `</td>
    <td>
                        <span>
                            <button class="btnDeleteClass btn btn-danger btn-sm">Удалить</button>
                            <select class="form-select selectForm" id="selectedClass" name="selectedClass">
                                ` + options + `
                            </select>
                        </span>
    </td>
  </tr`

      if(element != null) element.parent().replaceWith(html);
      else $(".teachers").append(html);
    }
  });

})