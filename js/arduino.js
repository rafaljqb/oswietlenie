function clearButtons() {
    $("#button_on").removeClass('btn-primary');
    $("#button_on").removeClass('btn-default');
    $("#button_off").removeClass('btn-primary');
    $("#button_off").removeClass('btn-default');
}

function lightOn() {
    $('#light_image').attr('src', 'img/on.png');
    clearButtons();
    $("#button_on").addClass('btn-default');
    $("#button_off").addClass('btn-primary');
}

function lightOff() {
    $('#light_image').attr('src', 'img/off.png');
    clearButtons();
    $("#button_off").addClass('btn-default')
    $("#button_on").addClass('btn-primary');
}

$(document).ready(function () {
    var onButton = $("#button_on");
    var offButton = $("#button_off");

   var socket = io.connect('http://192.168.1.29:3700', {
        'reconnection delay': 2000,
        'force new connection': true
    });

    socket.on('light', function (data) {
        console.log("Arduino response:", data);
        if (data.state) {
            lightOn();
        } else {
            lightOff();
        }
    });

    onButton.click(function () {
        lightOn();
        socket.emit('light', {state: true});
    });
    offButton.click(function () {
        lightOff();
        socket.emit('light', {state: false});
    });

});
