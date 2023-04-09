$(document).ready(function() {
    $("#quantity" + productId).val(1);
    $(".btn-minus").on("click", function (evt) {
        evt.preventDefault();
        productId = $(this).attr("pid");
        let qtyInput = $("#quantity" + productId);

        let newQty = parseInt(qtyInput.val());
        if (newQty < 1) {(qtyInput.val(1))}

    });

    $(".btn-plus").on("click", function (evt) {
        evt.preventDefault();
        productId = $(this).attr("pid");
       let qtyInput = $("#quantity" + productId)

       let newQty = parseInt(qtyInput.val());
        if (newQty > 9) {qtyInput.val(9)}
    });
});