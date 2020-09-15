$('.cart_quantity_down').on('click', function(e) {
    e.preventDefault();
    let $this = $(this);
    let $input = $this.closest('div').find('input');
    let value = parseInt($input.val());
    let href = $this.attr('href');
    let $total = $("#totalprice" + href);
    let $price = $("#price" + href)
    if (value >= 1) {
        value = value - 1;
    } else {
        value = 0;
    }

    $input.val(value);

    let val = $price.html();

    let total = value * val;

    $total.html(parseInt(total));


    // let a = $(this);
    // let cartId = a.attr("href");
    let cartId = $('#cartId').html();
    $.ajax({
        type:"PUT",
        dataType: 'json',
        contentType: 'application/json',
        url:"/cart/quantity/" + cartId,
        data: JSON.stringify(value)
    })
    e.preventDefault();
});

$('.cart_quantity_up').on('click', function(e) {
    e.preventDefault();
    let $this = $(this);
    let $input = $this.closest('div').find('input');
    let value = parseInt($input.val());
    let href = $this.attr('href');
    let $price = $("#price" + href)
    let $total = $("#totalprice" + href);
    if (value >= 0) {
        value = value + 1;
    } else {
        value = 0;
    }

    $input.val(value);

    let val = $price.html();

    let total = value * val;

    $total.html(parseInt(total));
    // let a = $(this);
    // let cartId = a.attr("href");
    let cartId = $('#cartId').html();
    $.ajax({
        type:"PUT",
        dataType: 'json',
        contentType: 'application/json',
        url:"/cart/quantity/" + cartId,
        data: JSON.stringify(value)
    })
    e.preventDefault();

});

$(document).ready(function () {
    //sư kiện nào thực hiện Ajax
    $('.cart_quantity_delete').click(function(event){
        // lay du lieu
        let a = $(this);
        let cartId = a.attr("href");
        // goi ajax
        $.ajax({
            type:"DELETE",
            //tên API
            url:"/cart/delete/"+ cartId,
            //xử lý khi thành công
            success: function (data) {
                a.parent().parent().remove();
            }

        });
        //chặn sự kiện mặc định của thẻ
        event.preventDefault();
    });
})