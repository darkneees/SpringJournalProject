$(document).on("click", ".btnDeletePupil", function (){

    let id = $(this).parents(".pupilCont").attr('id');
    let root = $(this).parents(".pupilCont");
    let url = "/admin/pupils/delete/" + id;
;
    $.ajax({
        url: url,
        method: 'post',
        dataType: 'json',
        success: function () {
            root.remove();
        }
    });
});
