<?xml version="1.0" encoding="UTF-8" ?>
<%@ include file="/common/global.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Insert title here</title>
	<script type="text/javascript" src="${ctx }/js/common/jquery.js"></script>
	<script type="text/javascript" src="${ctx }/js/common/plugins/jquery.jsonp-2.1.2.min.js"></script>
	<script type="text/javascript">
	$(function(){
		$('#test').click(testjsonp);
	});
	function testjsonp() {
		/*$.getJSON("http://localhost:9999/exercise-admin/ajax/jsonp.action?symbol=IBM&callback=showPrice", function(data) {
		    //alert("Symbol: " + data.symbol + ", Price: " + data.price);
		    alert(data);
		});*/
		$.jsonp({
	      //"url": "http://localhost:9999/exercise-admin/JsonpTest?callback=?",
	      url: "http://127.0.0.1:9999/exercise/JsonpServlet?callback=?",
	      data: {
			action : 'countActivityOfTactics',
			fieldType : 'badmintoon',
			tacticsId : 7
	      },
	      success: function(data) {
	          // handle user profile here 
	          alert(data);
	    	  //alert("Symbol: " + data.symbol + ", Price: " + data.price);
	    	  
	      },
	      error: function(d,msg) {
		      alert('error');
	      }
	    });
	}
	</script>
</head>

<body>
<button id="test">测试JSONP</button>
</body>
</html>