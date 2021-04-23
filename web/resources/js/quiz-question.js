var TIME_HDN = 'input[type="hidden"][name="timer"]';
var TIME_SPAN = '#time-remain';
var FINISH_FORM = '#finish-form';
function setTimeLabel(timeInSeconds) {
    var minutes = Math.floor(timeInSeconds/60);
    var seconds = timeInSeconds % 60;
    
    var time = "";
    
    time += (minutes < 10 ? '0' : '') + minutes;
    time += ":";
    
    time += (seconds < 10 ? '0' : '') + seconds;
    
    $(TIME_SPAN).text(time);
}

$(function() {
    setInterval(function() {
        var currentTime = $(TIME_HDN).val() - 1;
        
        $(TIME_HDN).val(currentTime);
        setTimeLabel(currentTime);
        
        if ($(TIME_HDN).val() === '0') {
            $(FINISH_FORM).submit();
        }
    }, 1000);
});