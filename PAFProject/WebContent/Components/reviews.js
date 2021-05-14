//hide alert
$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();
	$("#hidReviewIDSave").val("");
	$("#formItem")[0].reset();
});

$(document).on("click", "#btnSave", function(event) {

	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
		// If valid------------------------
		var type = ($("#reviewID").val() == "") ? "POST" : "PUT";
		 $.ajax(
		 {
			 url : "ReviewAPI",
			 type : type,
			 data : $("#formItem").serialize(),
			 dataType : "text",
			 complete : function(response, status)
		 {
		 onItemSaveComplete(response.responseText, status);
		 }
		 });
		});

function onItemSaveComplete(response, status)
{
if (status == "success")
 {
	 var resultSet = JSON.parse(response);
	 
	 if (resultSet.status.trim() == "success")
	 {
		 $("#alertSuccess").text("Successfully saved.");
		 $("#alertSuccess").show();
		 $("#reviewGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
	 $("#alertError").text(resultSet.data);
	 $("#alertError").show();
 }
 } else if (status == "error")
 {
	 $("#alertError").text("Error while saving.");
	 $("#alertError").show();
 } else
 {
	 $("#alertError").text("Unknown error while saving..");
	 $("#alertError").show();
 } 

	 $("#reviewID").val("");
	 $("#formItem")[0].reset();
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#reviewID").val($(this).closest("tr").find('td:eq(0)').text());
 $("#reviewType").val($(this).closest("tr").find('td:eq(1)').text());
 $("#reviewDesc").val($(this).closest("tr").find('td:eq(2)').text());
 $("#reviewValue").val($(this).closest("tr").find('td:eq(3)').text());
});

function onItemSaveComplete(response, status)
{
if (status == "success")
	{
	var resultSet = JSON.parse(response);
	if (resultSet.status.trim() == "success")
	{
	$("#alertSuccess").text("Successfully saved.");
	$("#alertSuccess").show();
	$("#reviewGrid").html(resultSet.data);
	} else if (resultSet.status.trim() == "error")
	{
	$("#alertError").text(resultSet.data);
	$("#alertError").show();
	}
	} else if (status == "error")
	{
	$("#alertError").text("Error while saving.");
	$("#alertError").show();
	} else
	{
	$("#alertError").text("Unknown error while saving..");
	$("#alertError").show();
	}
	$("#reviewID").val("");
	$("#formItem")[0].reset();
}



// CLIENT-MODEL================================================================
function validateItemForm()
{
	// REVIEW TYPE
	if ($("#reviewType").val().trim() == "")
	 {
	 return "Insert reviewType.";
	 }
	// REVIEW DESCRIPTION
	if ($("#reviewDesc").val().trim() == "")
	 {
	 return "Insert reviewDesc.";
	 } 
	// RATING VALUE-------------------------------
	if ($("#reviewValue").val().trim() == "")
	 {
	 return "Insert reviewValue.";
	 }

	return true;

}

$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "ReviewAPI",
		 type : "DELETE",
		 data : "reviewID=" + $(this).data("itemid"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onItemDeleteComplete(response.responseText, status);
		 }
		 });
		});

function onItemDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#reviewGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}