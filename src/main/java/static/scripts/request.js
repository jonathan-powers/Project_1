window.onload = function(){
    this.getSession();
    this.getRequest();
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
            if(session.Manager == true){
                console.log('create buttons');
                managerButtons();
            } else {
                console.log('not manager');
            }
        }
    }
}

function getRequest() {
    let url = 'http://localhost:7000/request';

    let xhr = new XMLHttpRequest(); 

    xhr.open('GET',url);

    xhr.withCredentials = true;

    xhr.send();

    xhr.onreadystatechange = function () {
        if(xhr.readyState === 4 && xhr.status === 200) {
            let request = JSON.parse(xhr.response);

            buildRequestCard(request);
        }
    }
}

function buildRequestCard(request) {
    let header = document.getElementById('request num');
    header.innerText = 'Request Id: '+request.id;

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
    newUser.innerText = request.user_Id;
    newUser.className = 'user';
    new_div.appendChild(newUser);

    let newAmount = document.createElement('p');
    newAmount.innerText = request.amount;
    newAmount.className = 'amount';
    new_div.appendChild(newAmount);

    let newApproval = document.createElement('p');
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
}

function managerButtons() {
    console.log('function ran');
    let body = document.getElementById('body');

    let approve = document.createElement('div');
    approve.id = "approve";
    let approveText = document.createElement('h3');
    approveText.innerText = 'APPROVE';
    approve.appendChild(approveText);
    body.appendChild(approve);


    let deny = document.createElement('div');
    deny.id = "deny";
    let denyText = document.createElement('h3');
    denyText.innerText = 'DENY';
    deny.appendChild(denyText);
    body.appendChild(deny);

    approve.addEventListener('click',(event) =>{
        
        let url = 'http://localhost:7000/request';

        let xhr = new XMLHttpRequest(); 

        xhr.open('GET',url);

        xhr.withCredentials = true;

        xhr.send();

        xhr.onreadystatechange = function () {
            if(xhr.readyState === 4 && xhr.status === 200) {
                let request = JSON.parse(xhr.response);

                approve(request);
                
                function approve(request) {
                    let url = 'http://localhost:7000/request/approve';
                
                    let xhr = new XMLHttpRequest();
                        
                    xhr.withCredentials = true;
                    
                    xhr.open('PUT',url);
                    
                    xhr.send(JSON.stringify(request));

                    xhr.onreadystatechange = function () {
                        if(xhr.readyState === 4 && xhr.status === 200) {
                            location.href = 'RequestList.html';
                        }
                    }
                }
            }
        }   
    })

    deny.addEventListener('click',(event) =>{    

        let url = 'http://localhost:7000/request';

        let xhr = new XMLHttpRequest(); 

        xhr.open('GET',url);

        xhr.withCredentials = true;

        xhr.send();

        xhr.onreadystatechange = function () {
            if(xhr.readyState === 4 && xhr.status === 200) {
                let request = JSON.parse(xhr.response);

                deny(request);
                
                function deny(request) {
                    let url = 'http://localhost:7000/request/deny';
                
                    let xhr = new XMLHttpRequest();
                
                    xhr.withCredentials = true;
                
                    xhr.open('PUT',url);
                
                    xhr.send(JSON.stringify(request));

                    xhr.onreadystatechange = function () {
                        if(xhr.readyState === 4 && xhr.status === 200) {
                            location.href = 'RequestList.html';
                        }
                    }
                }
            }
        }
    })
}