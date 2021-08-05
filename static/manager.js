const URL = 'http://localhost:8080/project1/manager/';

async function checkSessions(){
    let response = await fetch(URL+'checkmanager');
    if (response.status == 404){
        location.href = './index.html';
    } else if (response.status == 201) {
        let user = await response.json();
        console.log(user);
        document.getElementById("userId").innerText = user.userId;
        document.getElementById("userName").innerText = user.firstName +" "+ user.lastName;
    }
}

async function findStatus(){
    let status_filter = document.getElementById("status").value;

    let response;
    let data;

    switch(status_filter){
        case "all":
            response = await fetch(URL + 'allreimbursements');

            if(response.status===200) {
                data = await response.json();
             populateReimbTable(data);
            }else{
                console.log('Error, try agian');
            }
            break;

        case "pending":
            response = await fetch(URL + 'pendingreimbursements');

            if(response.status===200) {
                data = await response.json();
             populateReimbTable(data);
            }else{
                console.log('Error, try agian');
            }
            break;
    }
}

function populateReimbTable(data){
    let tbody = document.getElementById("reimbStatusTable");

    reimbStatusTable.innerHTML = "";
    
    for(let reimb of data){
        let row = document.createElement("tr")
    
            
        for(let cell in reimb){
            let td = document.createElement("td");
            td.innerText=reimb[cell];
            row.appendChild(td);
        }
          
        tbody.appendChild(row);
    }
    
}


let approveR = document.getElementById("approve");
approveR.onclick = approveReimb;

function gettingApproved(){
    let reimb_id_r = document.getElementById("reimb_id_r").value;

    let reimb = {
        reimbId : reimb_id_r,
    }

    return reimb;
}

async function approveReimb(){
    let reimb = gettingApproved(); 

    let response = await fetch(URL+'approve', {
        method:'PUT',
        body:JSON.stringify(reimb),
    })

    if(response.status === 201){
        console.log('Successfully approved reimbursement.');
        document.getElementById("reimb_id_r").value = 0;
    }else{
        console.log("Something went wrong")
    }
}

let denyR = document.getElementById("deny");
denyR.onclick = denyReimb;

function gettingDenied(){
    let reimb_id_d = document.getElementById("reimb_id_d").value;

    let reimb = {
        reimbId : reimb_id_d,
    }

    return reimb;
}

async function denyReimb(){
    let reimb = gettingDenied();

    let response = await fetch(URL+'deny', {
        method:'PUT',
        body:JSON.stringify(reimb),
    })

    if(response.status === 201){
        console.log('Successfully denied reimbursement.');
        document.getElementById("reimb_id_d").value = 0;
    }else{
        console.log("Something went wrong")
    }
}



let logoutButton = document.getElementById("logoutButton");
logoutButton.onclick = logout;

async function logout(){
        document.location.href = './index.html';
}