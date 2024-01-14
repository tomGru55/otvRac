
load();

function load(){
    document.addEventListener("DOMContentLoaded", function(){
        start();
    });

}
function start(){
console.log("started")
document.getElementById('openPopupBtn').addEventListener('click', function() {
    document.getElementById('popupContainer').style.display = 'block';
});

document.getElementById('closePopupBtn').addEventListener('click', function() {
    document.getElementById('popupContainer').style.display = 'none';
});
}