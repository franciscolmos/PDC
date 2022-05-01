 jQuery(document).ready(function() {
	 $("input:visible:enabled:first").focus();
	 
	 $("a").on("click", function() {
    	$("#delCookieName").val($(this).closest("tr").find("input[name=hCookieName]").val());
    	$("#form").submit();
	 });
 });
