<#include "molgenis-header.ftl">
<#include "molgenis-footer.ftl">
<@header/>
		<h3>Select car</h3>
		<label class="control-label" for="entity-instance-select-static">Entity instance:</label>
		
			<form id="carForm" method="get" action="${context_url}/brand/">
			<div class="controls">
				<select data-placeholder="Please Select" id="entity-instance-select-static" name="carId" form="carForm">
			<#list cars as car>
					<option value="${car.id}">${car.name}</option>
			</#list>
		      	</select>
		      	</div>
		      	<input id="selectButton" type="submit" class="btn" value="Select" />
		      	<a href="${context_url}/view/allcars">view all cars</a>
	      	</form>
	      	
	    
      	<script>
      		// if document is ready with loading
      		$(function() {
      			$('#selectButton').click(function() {
      				var carId = $('#entity-instance-select-ajax').val();
      				$.ajax({
						type : 'GET',
						url : '${context_url}/select/' + carId,
					 	success: function (selectedCar) {
							console.log("selected car " + selectedCar.name);
						}
					});
      			});
      		});
      	</script>
<@footer/>