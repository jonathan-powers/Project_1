let submit_button = document.getElementById('Submit');

submit_button.addEventListener('click', (event) => {

    if(event.cancelable){
        event.preventDefault();
    }

    let input_boxes = document.getElementsByTagName('input');

    let cred = {'email': input_boxes[0].value, 'password': input_boxes[1].value};
    
    console.log(cred)
    login(cred);
})

function login(cred) {
    let url = 'http://localhost:7000/login';

    let xhr = new XMLHttpRequest(); 

    xhr.open('POST',url);

    xhr.withCredentials=true;

    xhr.send(JSON.stringify(cred));

    xhr.onreadystatechange = function () {
        if(xhr.readyState === 4 && xhr.status === 200) {
            let user = JSON.parse(xhr.response);
            let header = xhr.getAllResponseHeaders();
            console.log(header)
            console.log(user);
            location.href = 'OptionSelect.html';

        } else if (xhr.readyState === 4 && xhr.status === 400) {
            location.reload;
        }
    }
}