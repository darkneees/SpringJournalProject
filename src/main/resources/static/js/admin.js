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

$(document).on("click", ".btnEditPupil", function (){

  let url = $(this).data("value");

  $.ajax({
    url: url,
    method: 'get',
    success: function (){
      window.location = url;
    }
  });
});

$(document).on("click", ".btnDeletePupil", function (){

  let root = $(this).parents(".pupilCont");
  let url = $(this).data("value");

  $.ajax({
    url: url,
    method: 'delete',
    dataType: 'json',
    success: function () {
      root.remove();
    }
  });
});


$(document).on("click", ".btnDelete", function (){

  let root = $(this).parents(".cont");
  let url = $(this).data("value");

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
  let url = $(this).data("value");
  let subject = $(this).closest("td").next("td").text();
  let elem = $(this).parent().parent();

  $.ajax({
    url: url,
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

  let selectedSubject = $(this).parent().parent().prev().prev().text()
  let selectedClass = $(this).parent().find(":selected").val();
  let url = $(this).data("value");

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
        button.parent().parent().prev().text('[' + data.data.join(", ") + ']');
        let options = button.next().children();
        for(let i = 0; i < options.length; i++){
          if(options[i].text === selectedClass) options[i].remove();
        }
      }
    }
  })
});

$(document).on("click", ".buttonAddClass", function (){

  let selectSubject = $(this).parent().find("#selectSubject").val();
  let classP = $(this).parent().find("#classP").val();
  let url = $(this).data("value");


  $.ajax({
    url: url,
    method: 'post',
    data: {
      'selectSubject': selectSubject,
      'classP': classP
    },
    dataType: 'json',
    success: function (result){

      let options = "";
      for(let i = 0; i < result.data.length; i++)
        options += "<option>"+ result.data[i] + "</option>";

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
      <button data-value="` + url.replace("add", "delete").replace("class", "subject") + `" class="btnDeleteSubject btn btn-danger btn-sm">Удалить</button>
    </td>
    <td class="subject">` + selectSubject + `</td>
    <td>[` + result.data.join(", ") + `]</td>
    <td>
      <span>
          <button data-value="` + url.replace("add", "delete") + `" class="btnDeleteClass btn btn-danger btn-sm">Удалить</button>
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
});