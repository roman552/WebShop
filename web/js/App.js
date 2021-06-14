

function toggleMenuActive(elementId){
    const activeElement = document.getElementById(elementId);
    const passiveElements = document.getElementsByClassName("nav-el");
    
    for (var i = 0; i < passiveElements.length; i++) {
        if(activeElement === passiveElements[i]){
            passiveElements[i].classList.add("nav-el-active");
            
        }else {
            if(passiveElements[i].classList.contains("nav-el-active")){
                passiveElements[i].classList.remove("nav-el-active");
            }
        }
    }
}
document.getElementById("addMoneyToConsumer").onclick = function () {
    toggleMenuActive("addMoneyToConsumer");
}

document.getElementById("addProduct").onclick = function () {
    toggleMenuActive("addProduct");
}

document.getElementById("editList").onclick = function () {
    toggleMenuActive("editList");
}

document.getElementById("listConsumers").onclick = function () {
    toggleMenuActive("listConsumers");
}

document.getElementById("adminPanel").onclick = function () {
    toggleMenuActive("adminPanel");
}

document.getElementById("logout").onclick = function () {
    toggleMenuActive("logout");
}