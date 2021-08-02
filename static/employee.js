const URL = 'http://localhost:8080/project1/employee/';

let allReimButton = document.getElementById("allReimButton");
let allPendingReimButton = document.getElementById("allPendingReimButton");
let submitNewReimButton = document.getElementById("submitNewReimButton");
let getOneReimButton = document.getElementById("getOneReim");
let addReimButton = document.getElementById("addReimButton");
let approveReimButton = document.getElementById("approve");
let denyReimButton = document.getElementById("deny");
let myReimButton = document.getElementById("myReimButton");
let logoutButton = document.getElementById("logoutButton");


allReimButton.onclick = allReimList;
allPendingReimButton.onclick = allPendingReimList;
submitNewReimButton.onclick = showAddNewDiv;
getOneReimButton.onclick = getOneReim;
addReimButton.onclick = addReim;
approveReimButton.onclick = approveReim;
denyReimButton.onclick = denyReim;
myReimButton.onclick = getMyReimList;
logoutButton.onclick = logout;


async function checkSessions(){
    let response = await fetch(URL+'check');
    if (response.status == 404){
        location.href = './index.html';
    } else if (response.status == 201) {
        let user = await response.json();
        console.log(user);
        document.getElementById("userId").innerText = user.userId;
        document.getElementById("userName").innerText = user.firstName +" "+ user.lastName;

        if (user.roleId == "2"){
            document.getElementById("allReimbButton").hidden=false;
            document.getElementById("allPendingReimButton").hidden=false;
            document.getElementById("reimLookup").hidden=false;

        }else{
            document.getElementById("myReimButton").hidden=false;
            document.getElementById("submitNewReimButton").hidden=false;
        }   
    }
}


async function allReimList(){
    let response = await fetch(URL+'allreim');

    if(response.status === 200){
        let data = await response.json();
        console.log(data);
        populateAllReimList(data);
        document.getElementById("allreimtable").hidden = false;
        document.getElementById("pendingtable").hidden = true;      
        document.getElementById("singleReimListtable").hidden = true;      
        document.getElementById("onereimtable").hidden = true
    }else{
        console.log('There is no Reimbursement!')
    }
}

function populateAllReimList(data){
    let allReimListBody = document.getElementById("allReimListBody");

    allReimListBody.innerHTML = ""

    for(let reim of data){
        let row = document.createElement("tr")

        let tdReimId = document.createElement("td");
        tdReimId.innerText = reim["reimId"];
        row.appendChild(tdReimId);

        let tdReimAmount = document.createElement("td");
        tdReimAmount.innerText = "$ "+reim["reimAmount"];
        row.appendChild(tdReimAmount);

        let tdReimSubmitted = document.createElement("td");
        tdReimSubmitted.innerText = reim["reimSubmitted"];
        row.appendChild(tdReimSubmitted);

        let tdReimResolved = document.createElement("td");
        tdReimResolved.innerText = reim["reimResolved"];
        row.appendChild(tdReimResolved);

        let tdReimDescription = document.createElement("td");
        tdReimDescription.innerText = reim["reimDescription"];
        row.appendChild(tdReimDescription);

        let tdReimAuthor = document.createElement("td");
        tdReimAuthor.innerText = reim["reimAuthorId"].firstName + " "+ reim["reimAuthorId"].lastName;
        row.appendChild(tdReimAuthor);

        let tdReimResolver = document.createElement("td");
        tdReimResolver.innerText = reim["reimResolverId"].firstName + " "+ reim["reimResolverId"].lastName;
        row.appendChild(tdReimResolver);

        let tdReimStatus = document.createElement("td");
        tdReimStatus.innerText = reim["reimStatusId"].status;
        row.appendChild(tdReimStatus);

        let tdReimType = document.createElement("td");
        tdReimType.innerText = reim["reimTypeId"].type;
        row.appendChild(tdReimType);

        allReimListBody.appendChild(row);  
    }
}

async function allPendingReimList(){
    let response = await fetch(URL+'pendingreim');

    if(response.status === 200){
        let data = await response.json();
        populatePendingReimList(data);
        document.getElementById("allreimtable").hidden = true;
        document.getElementById("pendingtable").hidden = false;      
        document.getElementById("singleReimListtable").hidden = true;      
        document.getElementById("onereimtable").hidden = true

    }else{
        console.log('There is no Pending Reimbursement!')
    }
}

function populatePendingReimList(data){
    let allPendingListBody = document.getElementById("allPendingListBody");

    allPendingListBody.innerHTML = "";

    for(let pending of data){
        let row = document.createElement("tr");

    
        let tdReimId = document.createElement("td");
        tdReimId.innerText = pending["reimId"];
        row.appendChild(tdReimId);

        let tdReimAmount = document.createElement("td");
        tdReimAmount.innerText = "$ " + pending["reimAmount"];
        row.appendChild(tdReimAmount);

        let tdReimSubmitted = document.createElement("td");
        tdReimSubmitted.innerText = pending["reimSubmitted"];
        row.appendChild(tdReimSubmitted);


        let tdReimAuthor = document.createElement("td");
        tdReimAuthor.innerText = pending["reimAuthorId"].firstName + " "+ pending["reimAuthorId"].lastName;
        row.appendChild(tdReimAuthor);


        let tdReimType = document.createElement("td");
        tdReimType.innerText = pending["reimTypeId"].type;
        row.appendChild(tdReimType);
        
        allPendingListBody.appendChild(row);
    }
}

async function getMyReimList(){
    let response = await fetch(URL+'myreim');

    if(response.status === 200){
        let data = await response.json();
        console.log(data);
        populateMyReimList(data);
        document.getElementById("allreimtable").hidden = true; 
        document.getElementById("pendingtable").hidden = true;      
        document.getElementById("singleReimListtable").hidden = false;      
        document.getElementById("onereimtable").hidden = true
    }else{
        console.log('There is no Reimbursement!')
    }
}

function populateMyReimList(data){
    let myReimListBody = document.getElementById("allMyReimListBody");

    myReimListBody.innerHTML = ""

    for(let cell of data){
        let row = document.createElement("tr")

        let tdReimId = document.createElement("td");
        tdReimId.innerText = cell["reimId"];
        row.appendChild(tdReimId);

        let tdReimAmount = document.createElement("td");
        tdReimAmount.innerText = "$ " + cell["reimAmount"];
        row.appendChild(tdReimAmount);

        let tdReimSubmitted = document.createElement("td");
        tdReimSubmitted.innerText = cell["reimSubmitted"];
        row.appendChild(tdReimSubmitted);



        let tdReimDescription = document.createElement("td");
        tdReimDescription.innerText = cell["reimDescription"];
        row.appendChild(tdReimDescription);

        let tdReimAuthor = document.createElement("td");
        tdReimAuthor.innerText = cell["reimAuthorId"].firstName + " "+ cell["reimAuthorId"].lastName;
        row.appendChild(tdReimAuthor);


        let tdReimStatus = document.createElement("td");
        tdReimStatus.innerText = cell["reimStatusId"].status;
        row.appendChild(tdReimStatus);

        let tdReimType = document.createElement("td");
        tdReimType.innerText = cell["reimTypeId"].type;
        row.appendChild(tdReimType);

        myReimListBody.appendChild(row);
    }
}






async function approveReim(){
    let input = document.getElementById("reimRequest").value;

    let response = await fetch(URL+"approve/"+input, {
        method:'PUT'
    });
    if(response.status === 201){
        alert("You approved the ticket.")
        console.log("Reimbursement successfully approved.");
    }else{
        console.log("Something went wrong to approve reimbursement.")
    }
}

async function denyReim(){
    let input = document.getElementById("reimRequest").value;

    let response = await fetch(URL+"deny/"+input, {
        method:'PUT'
    });
    if(response.status === 201){
        alert("You reject the ticket.")
        console.log("Reimbursement has been rejected.");
    }else{
        console.log("Something went wrong to submit new reimbursement.")
    }
}



async function getOneReim(){
    let input = document.getElementById("reimRequest").value;

    let response = await fetch(URL+"reim/"+input);

    if(response.status===200){
        let data = await response.json();
        populateOnePendingReim(data);
        document.getElementById("allReimListBody").hidden = true;
        document.getElementById("pendingtable").hidden = true;      
        document.getElementById("singleReimListtable").hidden = true;      
        document.getElementById("onereimtable").hidden = false
    }else{
        console.log("Invalid Reimbursement.")
    }
}

function populateOnePendingReim(data){
    let getOneReimBody = document.getElementById("getOneReimBody");
    getOneReimBody.innerHTML = "";

    let row = document.createElement("tr");
    for (let cell in data){
        let td = document.createElement("td");
        if(cell=='reimAuthorId' && data[cell]){
            td.innerText = data[cell].firstName + " " + data[cell].lastName;
        }
        else if(cell=='reimResolverId' && data[cell]){
            td.innerText = data[cell].firstName + " " + data[cell].lastName;
        }
        else if(cell=='reimStatusId' && data[cell]){
            td.innerText = data[cell].status;
        }
        else if(cell=='reimTypeId' && data[cell]){
            td.innerText = data[cell].type;
        }
        else{
            td.innerText = data[cell];
        } 
        row.appendChild(td);
    }
    getOneReimBody.appendChild(row);
}



function getUserReim(){
    let reimAmount = document.getElementById("reimAmount").value;
    let reimDesc = document.getElementById("reimDescription").value;
    let reimAuthorId = document.getElementById("userId").innerText;
    let reimTypeId = document.getElementById("reimTypeId").value;
    let timestamp = new Date().getTime();
    let reimId = getRandomInt(1000,10000);

    let newReim = {
        reimId:reimId,
        reimAmount:reimAmount,
        reimSubmitted:timestamp,
        reimResolved:null,
        reimDescription:reimDesc,
        reimAuthorId:reimAuthorId,
        reimResolverId:undefined,
        reimStatusId:1,
        reimTypeId:reimTypeId
    }
    return newReim;
}


async function addReim(){
    let newReim = getUserReim();

    let response = await fetch(URL+'addnewreim', {
        method:'POST',
        body:JSON.stringify(newReim),
    })

    if(response.status === 201){
        alert("You have successfully submitted a new reimbursement.");
        document.getElementById("addNewReimForm").reset();
        console.log("Reimbursement successfully submitted.");
    }else{
        console.log("Something went wrong to submit new reimbursement.")
    }
}


async function logout(){
        document.location.href = './index.html';
}



function getRandomInt(min, max){
    return Math.floor(Math.random()*(max-min)+min);
}

function showAddNewDiv(){
    document.getElementById("addNewReim").hidden=false;
}