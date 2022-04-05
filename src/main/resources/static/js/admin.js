$(document).ready(function() {
  $(".accordion").on("click", function() {
    $(this).toggleClass("active");
    $(this).next().slideToggle(200);
  });
});

$(document).on("click", ".btnDeleteSubject", function (){

  let id_class = $(this).attr('id');
  console.log(id_class);

  let str = '/admin/delete/subject/' + id_class;
  console.log(str);

  let elem = $(this).parent().parent();


  $.ajax({
    url: str,
    method: 'post',
    success: function (){
      console.log("success");
      elem.remove();
    }
  });
});

$(document).on("click", ".btnDeleteClass", function (){

  let id_class = $(this).attr('id');
  let button = $(this);
  let id = id_class.substring(0, id_class.indexOf("/"));
  let selectedSubject = id_class.substring(id_class.indexOf("/") + 1);
  let selectedClass = button.parent().find("select").children().val();

  let url = '/admin/delete/class/' + id_class;

  $.ajax({
    url: url,
    method: 'post',
    data: {
      "selectedClass": selectedClass
    },
    dataType: 'json',
    success: function (data){
      console.log(data);
      if(data.data === "null")
        button.parent().parent().parent().remove();
      else {
        button.parent().parent().parent().find(".subjects").text(data.data)
        let options = button.parent().find("select").children();
        console.log(options)
        for(let i = 0; i < options.length; i++){
          if(options[i].text === selectedClass) options[i].remove();
        }
      }
    }
  })
});