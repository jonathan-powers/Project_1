window.onload = function(){
    this.getSession();
    getStats();
}

function getSession() {
    let url = 'http://localhost:7000/session';

    let xhr = new XMLHttpRequest();

    xhr.open('GET',url);

    xhr.withCredentials = true;

    xhr.send();

    xhr.onreadystatechange = function () {
        if(xhr.readyState === 4 && xhr.status === 200) {
            const session = JSON.parse(xhr.response);

            console.log(session.Id);
            console.log(session.Manager);
        }
    }
}

function getStats() {
    let url = 'http://localhost:7000/manager/statistics';

    let xhr = new XMLHttpRequest();

    xhr.open('GET',url);

    xhr.withCredentials = true;

    xhr.send();

    xhr.onreadystatechange = function() {
        if(xhr.readyState === 4 && xhr.status === 200) {
            let stats = JSON.parse(xhr.response);

            let average = document.getElementById('averagevalue');
            average.innerText = 'Average amount requested: $' + stats.Average;

            let max = document.getElementById('maxvalue');
            max.innerText = 'Maximum amount requested: $' + stats.Max;
            
            let most = document.getElementById('mostvalue');
            most.innerText = 'User with most expenses: ' + stats.Most;
        }
    }
}