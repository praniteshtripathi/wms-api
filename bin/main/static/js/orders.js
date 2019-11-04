$(document).ready(function () {
    $("#listVehicleAssignment").click(function (event) {
        	getOrderWithVehicles();
    });
    
    $("#listAllOrder").click(function (event) {
    	getOrderWithOutVehicles();
     });

    

});

function getOrderWithVehicles() {
	$.ajax({
        type: "GET",
        url: "/api/alltransportOrdersWithVehicles",
        cache: false,
        timeout: 600000,
        success: function (data) {
        	var tr='';
        	var table='';
            var trHead='<tr><td><b> Name</b> </td>'+
            	'<td> <b>Category	 </b></td>'+
            	'<td> <b>State </b></td>'
            	+'<td><b>Intended Vehicle </b></td>'+
            	'<td><b>Processing Vehicle</b></td></tr>';
            $.each(data, function () {
    		var td='';
            	$.each(this, function (name, value) {
            		
            		if(name=='name'){
            			td=td+'<td>'+value+'</td>';	
            		}else if(name=='category'){
            			td=td+'<td>'+value+'</td>';	
            		}else if(name=='state'){
            			td=td+'<td>'+value+'</td>';	
            		}else if(name=='intendedVehicle'&&value==null){
            			value='Automatic'
            			td=td+'<td>'+value+'</td>';	
            		}else if(name=='processingVehicle'){
            			td=td+'<td>'+value+'</td>';	
            		}	
            	});
            	tr=tr+'<tr>'+td+'</tr>';       	
            });
            table=+'<table border="1">'+trHead+tr+'</table>';
            $('#orderswithproceedvehicle').html(table);
            console.log(table);
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });

}

function getOrderWithOutVehicles() {
	$.ajax({
        type: "GET",
        url: "/api/alltransportOrders",
        cache: false,
        timeout: 600000,
        success: function (data) {
        	var tr='';
        	var table='';
            var trHead='<tr><td><b> Name</b> </td>'+
            	'<td> <b>Category	 </b></td>'+
            	'<td> <b>State </b></td>'
            	+'<td><b>Intended Vehicle </b></td>'+
            	'</tr>';
            $.each(data, function () {
    		var td='';
            	$.each(this, function (name, value) {
            		
            		if(name=='name'){
            			td=td+'<td>'+value+'</td>';	
            		}else if(name=='category'){
            			td=td+'<td>'+value+'</td>';	
            		}else if(name=='state'){
            			td=td+'<td>'+value+'</td>';	
            		}else if(name=='intendedVehicle'&&value==null){
            			value='Automatic'
                			td=td+'<td>'+value+'</td>';	
                	}
            	});
            	tr=tr+'<tr>'+td+'</tr>';       	
            });
            table=+'<table border="1">'+trHead+tr+'</table>';
            $('#listofallorder').html(table);
            console.log(table);
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });

}

