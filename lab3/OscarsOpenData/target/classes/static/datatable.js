const headers=
    ["ID zapisa","Naziv ceremonije","Datum ceremonije","Lokacija ceremonije",
        "Kategorija nagrade","Film","Ime dobitnika","Prezime dobitnika"
        , "Datum rođenja dobitnika","Ime lika u filmu",	"Redatelj"]

load();
function load(){
    document.addEventListener("DOMContentLoaded", function(){
        initializeBasicListeners();
        loadData();
    });

}

function initializeBasicListeners(){
    document.getElementById("btnClear").addEventListener("click", function (){
        document.getElementById("keyword").value="";
    });
    document.getElementById("openInsertPopup").addEventListener("click", function(){
       document.getElementById("insertRecordPopup").style.display="block";
    });
    document.getElementById("cancelInsertBtn").addEventListener("click", function (){
        document.getElementById("insertRecordPopup").style.display="none";

    });
    document.getElementById("insertRecordForm").addEventListener("submit",function(event){
        event.preventDefault();
        document.getElementById("insertRecordPopup").style.display="none";
        var formData = {};
        document.querySelectorAll('#insertRecordForm input[type="text"]').forEach(function(input) {
            if(input.name.length > 0)
                formData[input.name.replace("Insert","")] = input.value;
        });
        createRecord(formData);
    });

    document.getElementById('filterform').addEventListener('submit', function (event) {
        event.preventDefault();
        loadData();
    });
}

async function createRecord(record){

    try {
        const data = await fetch("dataquery/create", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(record)
        })
            .then(response => response.json())
            .then(data => console.log('Success:', data))
            .catch(error => console.error('Error:', error));
        loadData();
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}
async function updateRecord(record, id){
    try {
        const data = await fetch("dataquery/update/" + id, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(record)
        })
            .then(response => response.json())
            .then(data => console.log('Success:', data))
            .catch(error => console.error('Error:', error));
        loadData();
    } catch (error) {
        console.error('Error fetching data in updateRecord:', error);
    }
}
async function deleteRecord(id) {
    fetch('dataquery/delete/'+id, {
        method: 'DELETE',
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to delete record');
            }
            loadData();
        })
        .catch(error => {
            console.error('Error:', error);
        });
}
async function loadData() {
    var formData = new FormData(document.getElementById('filterform'));
    var keyword = formData.get("keyword")
    var selectOption=formData.get("selectOption");

    if(selectOption==="id"){
        try {
            const data = await fetchData("/getById/"+keyword);
            if(data!=null){
            var arr = []
            arr.push(data.response)
            updateDatatableWithData(arr);
            } else {
                alert("Record with id: " +keyword +" does not exist, please enter valid id ");
            }
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    } else {
    try {
        const data = await fetchData("?keyword="+keyword+"&selectOption="+selectOption);
        updateDatatableWithData(data.response);
    } catch (error) {
        console.error('Error fetching data:', error);
    }
    }
}

function fillUpdateFormWithInitialData(data){
    document.querySelectorAll(".updateTextFields")
        .forEach(function(a){
            a.querySelectorAll('input[type="text"]')
                .forEach(function(e){
                    var fieldName = e.name.replace("Update","");
                    if (data.hasOwnProperty(fieldName)) {
                        e.value = data[fieldName];
                    }
                })
        })

}
async function generateFormForUpdate(id){

    try {
        const data = await fetchData("/getById/"+id);
        fillUpdateFormWithInitialData(data.response);
       // console.log("GET data with id: "+id+" resulted with : " +JSON.stringify(data));
        document.getElementById("updateRecordPopup").style.display="block";
        const submithandler= function(event){
            event.preventDefault();

            var formData = {};
            var inputs =  document.querySelectorAll(".updateTextFields input[type='text']")

            inputs.forEach(function(input) {
                if(input.name.length > 0)
                    formData[input.name.replace("Update","")] = input.value;
            });
            updateRecord(formData,id);
            document.getElementById("updateRecordPopup").style.display="none";

        };

        //Listener for UPDATE-SUBMIT
        const oldForm = document.getElementById("updateRecordForm");
        const newForm = oldForm.cloneNode(true);
        oldForm.parentNode.replaceChild(newForm, oldForm);
        document.getElementById("cancelUpdateBtn").addEventListener("click", function (){
            document.getElementById("updateRecordPopup").style.display="none";
        });
        document.getElementById("updateRecordForm").addEventListener("submit",submithandler);
    } catch (error) {
        console.error('Error fetching data: in generateFormForUpdate()', error);
    }

}



function updateDatatableWithData(data) {
    document.getElementById("filteredDataTable").innerHTML=""; //dal se obrisu listeneri?
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
        //adding update buttons
        const updateButton = document.createElement('td');
        updateButton.innerHTML="<input type='button' value='Edit'>";
        updateButton.id=dataRow.querySelector(':first-child').innerHTML; //id gumba je id zapisa
        updateButton.addEventListener("click", function(){
            console.log("Pressed Edit for record with id: "+this.id)
            generateFormForUpdate(this.id);
        });
        dataRow.appendChild(updateButton);

        //adding delete buttons
        const deleteButton = document.createElement('td');
        deleteButton.innerHTML="<input type='button' value='Delete'>";
        deleteButton.id=dataRow.querySelector(':first-child').innerHTML; //id gumba je id zapisa
        deleteButton.addEventListener("click", function(){
            console.log("Pressed Delete for record with id: "+this.id)
            deleteRecord(this.id);
        });
        dataRow.appendChild(deleteButton);

        document.getElementById("filteredDataTable").appendChild(dataRow);
    });
}

async function fetchData(path) {
    const response = await fetch("/dataquery" + path);
    if (!response.ok ) {
        console.log(response);
        return null;
    }
    return response.json();
}