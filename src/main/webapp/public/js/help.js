/**
 * Created by zzc on 2016-2-19.
 */
$(document).ready(function() {
    $('.help_cont section').click(function () {
        $('.help_cont p').hide();
        $(this).children('p').show();
    });
});