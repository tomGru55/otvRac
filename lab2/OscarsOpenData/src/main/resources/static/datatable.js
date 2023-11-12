const headers=
    ["nazivCeremonije","datumCeremonije","lokacijaCeremonije",
        "kategorijaNagrade","film","dobitnikIme","dobitnikPrezime"
        , "dobitnikDatumrod","imeLikaUFilmu",	"redatelj"]
load();
function load(){
    document.addEventListener("DOMContentLoaded", function(){
        startScript();
    });

}

function startScript() {
    document.getElementById("btnClear").addEventListener("click", function (){
        document.getElementById("keyword").value="";
    });
    const keyword="";
    const selectOption="wildcard";
    loadData(keyword,selectOption);
    formRenewData();
}

async function loadData(keyword,  selectOption) {

    try {
        const data = await fetchData("?keyword="+keyword+"&selectOption="+selectOption);
        updateDatatableWithData(data);
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}
function formRenewData(){
document.getElementById('filterform').addEventListener('submit', function (event) {
    event.preventDefault();

    var formData = new FormData(this);
    var keyword = formData.get("keyword")
    var selectOption=formData.get("selectOption");
    console.log(keyword + " " + selectOption);
    loadData(keyword,selectOption);
});
}
function updateDatatableWithData(data) {
    console.log(data);
    document.getElementById("filteredDataTable").innerHTML="";
    const headerRow = document.createElement('tr')
    headers.forEach(d => {
                    const th= document.createElement("td");
                    th.innerHTML=d;
                    headerRow.appendChild(th);
    });

    document.getElementById("filteredDataTable").appendChild(headerRow);


    data.forEach(element => {
        const dataRow= document.createElement('tr');
        Object.values(element).forEach(d =>{
            const th = document.createElement('td');
            th.innerHTML = d;
            dataRow.appendChild(th);
        });
        document.getElementById("filteredDataTable").appendChild(dataRow);
    });
}

async function fetchData(path) {
    const response = await fetch("/dataquery" + path);
    if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
    }
    return response.json();
}