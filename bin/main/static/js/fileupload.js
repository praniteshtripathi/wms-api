
function fileValidation(){
    var fileInput = document.getElementById('file');
    var filePath = fileInput.value;
    var allowedExtensions = /(\.xls|\.xlsx)$/i;
    if(!allowedExtensions.exec(filePath)){
        alert('Please upload file having extensions .xlsx,xls only.');
        fileInput.value = '';
        return false;
    }else{
    	return true;
    }
}


$(document).ready(function () {

    $("#btnSubmit").click(function (event) {

        event.preventDefault();
        if(fileValidation()==true){
        	 fire_ajax_submit();
        }
       

    });

});

function fire_ajax_submit() {

    // Get form
    var form = $('#fileUploadForm')[0];

    var data = new FormData(form);
    
    data.append("CustomField", "This is some extra data, testing");

    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/api/upload",
        data: data,
        processData: false, 
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
        	var tr='';
        	var table='';
            var trHead='<tr><td><b> Deadline</b> </td>'+
            	'<td> <b>Name	 </b></td>'+
            	'<td> <b>Intended Vehicle </b></td>'
            	+'<td><b> Target </b></td>'+
            	'<td><b> Order Upload Status </b></td></tr>';
            $.each(data, function () {
    		var td='';
            	$.each(this, function (name, value) {
            		td=td+'<td>'+value+'</td>';		
            	});
            	tr=tr+'<tr>'+td+'</tr>';       	
            });
            table='<span style="color:white">File uploaded with below information</span>'
            +'<table border="1">'+trHead+tr+'</table>';
            document.getElementById("uploadedOrders").style.display = 'block';
            $('#uploadedOrders').html(table);
           
            $("#btnSubmit").prop("disabled", false);
        },
        error: function (e) {
            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);

        }
    });

}