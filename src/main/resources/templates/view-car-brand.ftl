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
		<h3>You selected an ${selectedCar.name}</h3>
		<p>Select a type:</p>
		<form id="carTypeForm" method="get" action="${context_url}/brand/type">
			<div class="controls">
				<select data-placeholder="Please Select" id="entity-instance-select-static" name="selectedType" form="carTypeForm">
					<#list types as type>
							<option value=${type}>${type}</option>
					</#list>
		      	</select>
		      </div>
		  <input id="selectType" type="submit" class="btn" value="Select" />
	      </form>
	</body>
</html>	
<@footer/>