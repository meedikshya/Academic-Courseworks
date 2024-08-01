


/*popups on clicking add to cart*/
/*
let popup = document.getElementById("popup");

function openpopup() {

    popup.classList.add("open-popup");
    event.preventDefault();//this tag stops the page from getting refreshed
}
function closepopup() {
    popup.classList.remove("open-popup");
}

let popup2 = document.getElementById("popup2");

function openpopup2() {
    popup.classList.add("open-popup");
    event.preventDefault();//this tag stops the page from getting refreshed
}
function closepopup2() {
    popup.classList.remove("open-popup");
}
*/

/*timer i.e sale ends in timer*/
let daysItem = document.querySelector("#days");
let hoursItem = document.querySelector("#hours");
let minutesItem = document.querySelector("#minutes");
let secondsItem = document.querySelector("#seconds");

let countDown = () => {
    let futureDate = new Date("1 March 2023");
    let currentDate = new Date();
    let myDate = futureDate - currentDate;

    let days = Math.floor(myDate / 1000 / 60 / 60 / 24);
    let hours = Math.floor(myDate / 1000 / 60 / 60) % 24;
    let minutes = Math.floor(myDate / 1000 / 60) % 60;
    let seconds = Math.floor(myDate / 1000) % 60;

    if (days < 10) {
        daysItem.innerHTML = "0" + days;
    }
    else {
        daysItem.innerHTML = days;
    }

    if (hours < 10) {
        hoursItem.innerHTML = "0" + hours;
    }
    else {
        hoursItem.innerHTML = hours;
    }

    if (minutes < 10) {
        minutesItem.innerHTML = "0" + minutes;
    }
    else {
        minutesItem.innerHTML = minutes;
    }

    if (seconds < 10) {
        secondsItem.innerHTML = "0" + seconds;
    }
    else {
        secondsItem.innerHTML = seconds;
    }
}

countDown()

setInterval(countDown, 1000)



/*alert*/
function openpopup() {
    alert("Added To Cart Successfully!")
}