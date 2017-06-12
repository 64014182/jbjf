(function ($) {
    $(function () {
        $('.table-expandable').each(function () {
            var table = $(this);
            table.children('thead').children('tr').append('<th></th>');
            table.children('tbody').children('tr').filter(':odd').hide();
//            table.children('tbody').children('tr').children('td').children('div .table-expandable-arrow').click(function () {
//                
//            });
            table.children('tbody').children('tr').filter(':even').each(function () {
                var element = $(this);
                element.append('<td><div class="table-expandable-arrow"></div></td>');
            });
            $('.table-expandable-arrow').click(function(){
            	var element = $(this);
            	var trElement = $(this).parent().parent();
            	trElement.next('tr').toggle('slow');
            	trElement.find(".table-expandable-arrow").toggleClass("up");
            });
        });
    });
})(jQuery); 