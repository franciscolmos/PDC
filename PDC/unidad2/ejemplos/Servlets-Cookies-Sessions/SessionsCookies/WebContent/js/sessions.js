 jQuery(document).ready(function() {
	 $("input:visible:enabled:first").focus();
	 
	 $("a").on("click", function() {
    	$("#delAttrName").val($(this).closest("tr").find("input[name=hAttrName]").val());
    	$("#form").submit();
	 });
 });