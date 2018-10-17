<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Trading System</title>
</head>
<body>
		<p> Trading System History </p>
		<p> Before update a position </p>
		<h4>User ID: ${id_session} </h4>
		<p><h4>User Name: ${user_session}</h4></p>
		<p>Ticker: ${ticker_session}</p>
		<p>Total Quantity of Stock: ${quantity_session}</p>
		<p>Total Available Fund: ${Avalablefund_session}</p>
		<p>Price: ${executeprice_session}</p>
		
		<br>
		<p> After update a position </p>
		<h4>User ID: ${id_session} </h4>
		<p><h4>User Name: ${user_session}</h4></p>
		<p>Ticker: ${ticker_session}</p>
		<p>Total Quantity of Stock: ${quant_session}</p>
		<p>Total Available Fund: ${fund_session}</p>
		
		
</body>
</html>