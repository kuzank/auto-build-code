/**
 * Desc
 */

function bookNew() {
    var $ = layui.jquery;
    var layer = layui.layer;
    $.ajax({
        type: "GET",
        url: "/book/createPage",
        dataType: 'html',
        success: function (data) {
            debugger;
            compare = layer.open({
                area: ['850px', '580px'],
                type: 1,
                content: data //这里content是一个普通的String
            });
        }
    });
}