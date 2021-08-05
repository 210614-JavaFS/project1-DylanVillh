const URL = "http://localhost:8080/project1/"

let loginButton = document.getElementById("login");

loginButton.onclick = userLogin;


async function userLogin(){

    let user = getUser();
    
    let response = await fetch(URL + "login", {
         method:'POST',
         body:JSON.stringify(user),
         credentials:"include"
     });

     if (response.status === 201){
         console.log("You have successfully logged in as an Employee.");
         console.log(user);      
        document.location.href = './employee.html';
     }else {
        alert("Login Failed. Please try again.")
        document.location.href = './index.html';
     }
}


function getUser(){
    let username = document.getElementById("username").value
    let password = document.getElementById("password").value

    let user = {
        username:username,
        password:password
    }
    return user;
}