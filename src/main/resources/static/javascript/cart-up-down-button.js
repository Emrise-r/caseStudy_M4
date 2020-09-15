// $(".cart_quantity_up").click(function() {
//     $("cart_quantity_input").val($("cart_quantity_input").value + 1);
// })
//
// $(".cart_quantity_down").click(function() {
//     $("cart_quantity_input").val($("cart_quantity_input").value - 1);
// })

let $input = $(".cart_quantity_input");
document.getElementById("up")
    .addEventListener("click", $input
        .val((parseInt($input.val()) + 1))).preventDefault();

let $input = $(".cart_quantity_input");
document.getElementById("down")
    .addEventListener("click", $input
        .val((parseInt($input.val()) - 1))).preventDefault();

$('.cart_quantity_down').on('click', function(e) {
    e.preventDefault();
    let $this = $(this);
    let $input = $this.closest('div').find('input');
    let value = parseInt($input.val());

    if (value >= 1) {
        value = value - 1;
    } else {
        value = 0;
    }

    $input.val(value);

});

$('.cart_quantity_up').on('click', function(e) {
    e.preventDefault();
    let $this = $(this);
    let $input = $this.$('.cart_quantity_input');
    let value = parseInt($input.val());

    if (value >= 1) {
        value = value + 1;
    } else {
        value = 0;
    }

    $input.val(value);

});