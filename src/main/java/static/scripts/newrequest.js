
let submit_button = document.getElementById('Submit');

submit_button.addEventListener('click', (event) => {

    if(event.cancelable){
        event.preventDefault();
    }

    let input_boxes = document.getElementsByTagName('input');

    let reason = document.getElementById('Description')

    let request = {'title': input_boxes[0].value, 'amount': input_boxes[1].value, 'reason': reason.value};

    newRequest(request);
})

function newRequest(request) {
    let url = 'http://localhost:7000/user/newrequest';

    let xhr = new XMLHttpRequest();
    
    xhr.withCredentials = true;

    xhr.open('POST',url);

    xhr.send(JSON.stringify(request));

    xhr.onreadystatechange = function () {
        if(xhr.readyState === 4 && xhr.status === 200) {
            location.href = 'OptionSelect.html';

        } else if (xhr.readyState === 4 && xhr.status === 400) {
            location.reload;
        }
    }
}