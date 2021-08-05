const URL = 'http://localhost:8080/project1/employee/';

let user;

async function checkSessions(){
    let response = await fetch(URL+'checkemployee');
    if (response.status == 404){
        location.href = './index.html';
    } else if (response.status == 201) {
        user = await response.json();
        console.log(user);
        document.getElementById("userId").innerText = user.userId;
        document.getElementById("username").innerText = user.firstName +" "+ user.lastName;
        if(user.roleId===2){
            location.href = './manager.html';
        }
    }
}

let userReimb = document.getElementById("userReimb");
userReimb.onclick = getUserReimb;

async function getUserReimb(){
    let response = await fetch(URL + 'userreimbursments');

    if(response.status===200) {
        let data = await response.json();
        populateReimbTable(data);
    }else{
        console.log('Error, try agian');
    }
}

function populateReimbTable(data){
    let tbody = document.getElementById("reimbTable");

    reimbTable.innerHTML = "";

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


let addReimb = document.getElementById("addReimb");
addReimb.onclick = addUserReimb;

function getNewReimb(){
    let id = user.userId;
    let amount = document.getElementById("amount").value;
    let description = document.getElementById("description").value;
    let request_type = document.getElementById("request_type").value;
    let reimbId;
    if(request_type==='Lodging') {
        reimbId=1;
    }if(request_type==='Food') {
        reimbId=2;
    }if(request_type==='Travel') {
        reimbId=3;
    }if(request_type==='Other') {
        reimbId=4;
    }

    let newReimb = {
        reimbAuthorId : id,
        reimbAmount: amount,
        reimbDescription: description,
        reimbTypeId: reimbId
    }

    return newReimb;
}

async function addUserReimb(){
    let newReimb = getNewReimb();

    let response = await fetch(URL+'addreimbursement', {
        method:'POST',
        body:JSON.stringify(newReimb),
    })

    if(response.status === 201){
        console.log('Successfully created a new reimbursement.');
    }else{
        console.log("Something went wrong")
    }
}

let logoutButton = document.getElementById("logoutButton");
logoutButton.onclick = logout;

async function logout(){
        document.location.href = './index.html';
}