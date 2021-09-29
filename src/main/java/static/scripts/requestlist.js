window.onload = function(){
    this.getSession();
}

let all = document.getElementById('all');
let reviewed = document.getElementById('reviewed');
let pending = document.getElementById('Pending');

function eventListeners(Id, Manager) {
    all.addEventListener('click',(event) => {
        removeRequests();
        getRequests(Manager);
    });

    reviewed.addEventListener('click',(event) => {
        removeRequests();
        getReviewedRequests(Manager);
    });

    pending.addEventListener('click',(event) => {
        removeRequests();
        getPendingRequests(Manager);
    });

}

function removeRequests() {
    let requests = document.getElementsByClassName('request');
    while(requests.length > 0) {
        requests.item(0).parentNode.firstChild.remove();
    }
}

function getRequests(manager) {
    if(manager == true){
        var url = 'http://localhost:7000/manager/requests';
    } else {
        var url = 'http://localhost:7000/user/myrequests';
    }

    let xhr = new XMLHttpRequest(); 

    xhr.open('GET',url);

    xhr.withCredentials = true;

    xhr.send();

    xhr.onreadystatechange = function () {
        if(xhr.readyState === 4 && xhr.status === 200) {
            let requests = JSON.parse(xhr.response);

            for(let request of requests){
                buildRequestCard(request);
            }
        }
    }
}

function getReviewedRequests(manager) {
    if(manager == true){
        var url = 'http://localhost:7000/manager/requests/reviewed';
    } else {
        var url = 'http://localhost:7000/user/myrequests/reviewed';
    }

    let xhr = new XMLHttpRequest(); 

    xhr.open('GET',url);

    xhr.withCredentials = true;

    xhr.send();

    xhr.onreadystatechange = function () {
        if(xhr.readyState === 4 && xhr.status === 200) {
            let requests = JSON.parse(xhr.response);

            for(let request of requests){
                buildRequestCard(request);
            }
        }
    }
}

function getPendingRequests(manager) {
    if(manager == true){
        var url = 'http://localhost:7000/manager/requests/pending';
    } else {
        var url = 'http://localhost:7000/user/myrequests/pending';
    }

    let xhr = new XMLHttpRequest(); 

    xhr.open('GET',url);

    xhr.withCredentials = true;

    xhr.send();

    xhr.onreadystatechange = function () {
        if(xhr.readyState === 4 && xhr.status === 200) {
            let requests = JSON.parse(xhr.response);

            for(let request of requests){
                buildRequestCard(request);
            }
        }
    }
}

function buildRequestCard(request) {
    let requestcontainer = document.getElementById('requestcontainer');

    let new_div = document.createElement('div');
    new_div.className = 'request';
    new_div.id = request.id;
    requestcontainer.appendChild(new_div);

    let newTitle = document.createElement('p');
    newTitle.innerText = request.title;
    newTitle.className = 'title';
    new_div.appendChild(newTitle);

    let newRequestId = document.createElement('p');
    newRequestId.innerText = 'Request Id: '+request.id;
    newRequestId.className = 'request_id';
    new_div.appendChild(newRequestId);

    let newUser = document.createElement('p');
    newUser.innerText = 'User Id: ' +request.user_Id;
    newUser.className = 'user';
    new_div.appendChild(newUser);

    let newAmount = document.createElement('p');
    newAmount.innerText = request.amount;
    newAmount.className = 'amount';
    new_div.appendChild(newAmount);

    let newApproval = document.createElement('p');
    newApproval.innerText = request.approval;
    if (request.approval == 0){
        newApproval.innerText = 'PENDING';
        newApproval.className = 'pending';
    } else if (request.approval == 1) {
        newApproval.innerText = 'APPROVED';
        newApproval.className = 'approved';
    } else {
        newApproval.innerText = 'DENIED';
        newApproval.className = 'denied';
    }
    //newApproval.className = 'approval';
    new_div.appendChild(newApproval);

    let newReason = document.createElement('p');
    newReason.innerText = request.reason;
    newReason.className = 'reason';
    new_div.appendChild(newReason);

    new_div.addEventListener('click',(event) =>{
        requestId(request.id);
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
            var session = JSON.parse(xhr.response);
            getRequests(session.Manager);
            eventListeners(session.Id, session.Manager);
        }
    }
}

function requestId(Id) {
    let url = 'http://localhost:7000/requestId/'+ Id;

    let xhr = new XMLHttpRequest();

    xhr.open('POST',url);

    xhr.withCredentials = true;

    xhr.send();

    xhr.onreadystatechange = function() {
        if(xhr.readyState == 4 && xhr.status == 200) {
            location.href = 'Request.html';
        }
    }
}