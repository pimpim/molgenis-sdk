<#include "molgenis-header.ftl">
<#include "molgenis-footer.ftl">
<@header/>
	<!DOCTYPE html>
<html>
	<head>
		<title>Form</title>
		<meta charset="utf-8">
		<link rel="stylesheet" href="/css/bootstrap.min.css" type="text/css">
		<script src="/js/jquery-1.8.3.min.js"></script>
		<script src="/js/bootstrap.min.js"></script>
	</head>
	<body>
		<label class="control-label" for="entity-instance-select-static">Entity instance:</label>
			<form id="viewCarsForm">
				<div class="controls">
					<table id="table-cars" class="table table-border">
						<tr>
							<th>id</th>
							<th>name</th>
							<th>type</th>
						</tr>
						<#list cars as car>
							<tr>
								<td>${car.id}</td>
								<td>${car.name}</td>
								<td>
								<#if car.listOfTypes??>
									<#list car.listOfTypes as type>
										${type} <#if (car.listOfTypes?size - 1 gt type_index)>,</#if>
									</#list>
								</#if>
								</td>
							</tr>
						</#list>
					</table>
			    </div>
			    <div>
			   
			    	<dl><dt>Car Id : </dt><dd><input type="text" id="car-id" class="input-small" placeholder="ID"></dd></dl>
			    	<dl><dt>Car name : </dt><dd><input type="text" id="car-name" class="input-small" placeholder="Name"></dd></dl>
			    	<button id="add-car" class="btn btn">Add</button>
			    </div>
			     <div id="add-type-modal" class="modal hide">
				    <div class="modal-header">
			     		<button type="button" class="close" data-dismiss="#orderdata-modal" data-backdrop="true" aria-hidden="true">&times;</button>
				    </div>
				    <div class="modal-body">
				    	Car Id : <input type="text" id="car-type-input" class="input-small" placeholder="ID">
				    </div>
			     	<div class="modal-footer">
			      		<a href="#" id="modal-btn-close" class="btn" aria-hidden="true">Cancel</a>
	    				<a href="#" id="add-car-type" class="btn btn-primary" aria-hidden="true">Add type</a>
				     </div>
		    	</div>
		    	
	      	</form>
      	<script>
      		// if document is ready with loading
      		$(function() {
      			var modal = $('#add-type-modal');
  				$('#modal-btn-close').click(function() {
			  		modal.hide();
			    	return false;
				});
      			
      			$('#add-car').click(function(){
      				if($('#car-id').val() =='' ){
      					createAlertMessage('Please fill in the car-id');
      				}
      				else if($('#car-name').val()==''){
	      				createAlertMessage('Please fill in the car-name');
  					}
      				else{
      					modal.show();
      				}
					return false;
      			});
      			
      			
      			$('#add-car-type').click(function(){
      				var listOfTypes = [];
      				listOfTypes.push($('#car-type-input').val());
      				var car = {
      					id : $('#car-id').val(),
      					name : $('#car-name').val(),
      					listOfTypes : listOfTypes
      				};
      				$.ajax({
						type : 'POST',
						data : JSON.stringify(car),
						url : '${context_url}/view/allcars/add',
						contentType : 'application/json',
					 	success: function (addedCar) {
							var carId = addedCar.id;
							var carName = addedCar.name;
							var type = '';
							if(addedCar.listOfTypes) type = addedCar.listOfTypes;
							
							var newRow = $('<tr />');
							newRow.append('<td>' + carId + '</td>');
							newRow.append('<td>' + carName + '</td>');
							newRow.append('<td>' + type + '</td>');
							$('#table-cars').append(newRow);
							
							
						},
						complete :function() {
							$('#car-id').val('');
							$('#car-name').val('');
							$('#car-type-input').val('');
							modal.hide();
						}
						
					});
      			});
      			
      		});
      		
      		function createAlertMessage(text){
      			$('#error-message').remove();
      			var alertDiv = $('<div />');
      			alertDiv.attr({
      				'id':'error-message',
      				'class':'alert alert-error',
      				
      			})
      			var button = $('<button>').attr({
	      			'type':'button',
	      			'class':'close',
	      			'data-dismiss':'alert'
	      		}).append('&times;');
	      		var firstElement = $('body').children(':eq(0)');
	      		alertDiv.append(button).append(text);
	      		$('body').prepend(alertDiv);
      		}
      		
      	</script>
	</body>
</html>	
	
<@footer/>