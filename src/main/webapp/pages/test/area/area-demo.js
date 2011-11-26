$(function(){

    demo1();
    demo2();
    demo3();
    demo31();
    demo4();
    
});

function demo1(){
    // demo1
    $('#areaDiv1').area2select();
    $('#getAreaId1').unbind('click').click(function(){
        var sv = $('#areaDiv1').getAreaId($('#vt1').val());
        $('<div>文字=' + sv.text + "，value=" + sv.value + "</div>").appendTo('#results1');
    });
    $('#clearResult1').click(function(){
        $('#results1').html('');
    });
}

function demo2(){
    // demo2
    $('#areaDiv2').area2select({
        defaultValue: 5698
    });
    $('#getAreaId2').unbind('click').click(function(){
        var sv = $('#areaDiv2').getAreaId($('#vt2').val());
        $('<div>文字=' + sv.text + "，value=" + sv.value + "</div>").appendTo('#results2');
    });
    $('#clearResult2').click(function(){
        $('#results2').html('');
    });
}

function demo3(){
    // demo3
    $('#areaDiv3').area2select({
        layer: 2,
        callback: function(){
            $('#results3').append('加载完了' + $('#areaDiv3 select:last option:selected').text() + "<br/>");
        }
    });
    $('#getAreaId3').unbind('click').click(function(){
        var sv = $('#areaDiv3').getAreaId($('#vt3').val());
        $('<div>文字=' + sv.text + "，value=" + sv.value + "</div>").appendTo('#results3');
    });
    $('#clearResult3').click(function(){
        $('#results3').html('');
    });
}

function demo31(){
    // demo4
    $('#areaDiv31').area2select({
        layer: 2,
        defaultValue: 6579,
        attrs: {
            title: '通过插件设置的title'
        },
        callback: function(){
            $('#results31').append('加载完了');
        }
    });
    $('#getAreaId31').unbind('click').click(function(){
        var sv = $('#areaDiv31').getAreaId($('#vt31').val());
        $('<div>文字=' + sv.text + "，value=" + sv.value + "</div>").appendTo('#results31');
    });
    $('#clearResult31').click(function(){
        $('#results31').html('');
    });
}

function demo4(){
    // demo5
    $('#areaDiv4').area2select({
        parentName: '上海市'
    });
    $('#getAreaId4').unbind('click').click(function(){
        var sv = $('#areaDiv4').getAreaId($('#vt4').val());
        $('<div>文字=' + sv.text + "，value=" + sv.value + "</div>").appendTo('#results4');
    });
    $('#clearResult4').click(function(){
        $('#results4').html('');
    });
}
