window.onload = function(){
    this.getSession();
}

function userSelect() {
let select_new = document.getElementById('new');
let select_old = document.getElementById('old');
let select_pending = document.getElementById('pending');

select_new.addEventListener('click',(event) => {
    location.href='NewRequest.html';
})

select_old.addEventListener('click',(event) => {
    location.href='RequestList.html';
})

select_pending.addEventListener('click',(event) => {
    location.href='RequestList.html';
})
}

function managerSelect() {
    let select_new = document.getElementById('new');
    let select_old = document.getElementById('old');
    let select_pending = document.getElementById('pending');

    let select_stats = document.getElementById('new title');
    let description = document.getElementById('new description');

    select_stats.innerText = 'View Statistics';
    description.innerText = 'look at an overview of all requests';

    select_new.addEventListener('click',(event) => {
        location.href='RequestStat.html';
    })
    
    select_old.addEventListener('click',(event) => {
        location.href='RequestList.html';
    })
    
    select_pending.addEventListener('click',(event) => {
        location.href='RequestList.html';
    })
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
            if (session.Manager) {
                managerSelect();
            } else {
                userSelect();
            }
        }
    }
}