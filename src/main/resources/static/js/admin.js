$(document).ready(function() {
  $(".accordion").on("click", function() {
    $(this).toggleClass("active");
    $(this).next().slideToggle(200);
  });
});